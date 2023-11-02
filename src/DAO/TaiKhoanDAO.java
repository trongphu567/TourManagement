package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import DTO.TaiKhoanDTO;
import GUI.MainKhachHangGUI;
import GUI.SignUpGUI;
import database.JDBCUtil;
public class TaiKhoanDAO {
	public static TaiKhoanDAO getInstance() {
		return new TaiKhoanDAO();
	}
	
	
	public int insert(TaiKhoanDTO t) {
		int ketQua=0;
		
		try {
			Connection con=JDBCUtil.getConnection();
			Statement st=con.createStatement();
			String sql="INSERT INTO TAIKHOAN "+
					" VALUES ('"+t.getId()+"','"+t.getUsername()+"','"+t.getPassword()+"','"+t.getLoaiTK()+"','"+t.getTrangThai()+"')";
			ketQua=st.executeUpdate(sql);
			JDBCUtil.closeConnection(con);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	
	public int update(TaiKhoanDTO t) {
		int ketQua=0;
		
		try {
			Connection con=JDBCUtil.getConnection();
			Statement st=con.createStatement();
			String sql="UPDATE TAIKHOAN "+
					" SET TaiKhoan='"+t.getUsername()+"',MatKhau='"+t.getPassword()+"',Quyen='"+t.getLoaiTK()+
					"',TrangThai='"+t.getTrangThai()+"'"+
					"WHERE MaTK='"+t.getId()+"'";
			ketQua=st.executeUpdate(sql);
			JDBCUtil.closeConnection(con);
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	
	public int delete(TaiKhoanDTO t) {
		int ketQua=0;
		try {
			Connection con=JDBCUtil.getConnection();
			Statement st=con.createStatement();
			String sql="UPDATE TAIKHOAN "+" SET TrangThai=0"+
					" WHERE MaTK='"+t.getId()+"'";
			ketQua=st.executeUpdate(sql);
			JDBCUtil.closeConnection(con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	
	public int delete_all() {
		int ketQua=0;
		try {
			Connection con=JDBCUtil.getConnection();
			Statement st=con.createStatement();
			String sql="DELETE FROM TAIKHOAN";
			ketQua=st.executeUpdate(sql);
			JDBCUtil.closeConnection(con);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
		
	}

	
	public ArrayList<TaiKhoanDTO> selectAll() {
		ArrayList<TaiKhoanDTO> ketQua=new ArrayList<TaiKhoanDTO>();
		try {
			Connection con=JDBCUtil.getConnection();
			Statement st=con.createStatement();
			String sql="SELECT * FROM TAIKHOAN WHERE TrangThai=1";
			ResultSet rs=st.executeQuery(sql);
			
			while (rs.next()) {
				String id=rs.getString("MaTK");
				String username=rs.getString("TaiKhoan");
				String password=rs.getString("MatKhau");
				String loaiTK=rs.getString("Quyen");
				int trangThai=rs.getInt("TrangThai");
				TaiKhoanDTO newTK=new TaiKhoanDTO(id, username, password,loaiTK,trangThai);
				ketQua.add(newTK);
			}
			JDBCUtil.closeConnection(con);
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return ketQua;
	}

	public int check(TaiKhoanDTO t) {
		int ketQua=0;
		try {
			Connection con=JDBCUtil.getConnection();
			PreparedStatement st=con.prepareStatement("SELECT COUNT(*) FROM TAIKHOAN WHERE Quyen='Admin'");
			ResultSet rs=st.executeQuery();
			rs.next();
			int num=rs.getInt(1);
			if (num>=1) 
				ketQua=1;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}
	
	public int numOfTK() {
		SignUpGUI.count=0;
		Connection connection = JDBCUtil.getConnection();
		String sql = "SELECT * FROM TAIKHOAN WHERE MaTK LIKE '%KH%'";
		Statement statement;
        try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
			    SignUpGUI.count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        JDBCUtil.closeConnection(connection);
        return SignUpGUI.count;
	}
}
