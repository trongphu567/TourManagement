package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import DTO.KhachHangDTO;

import database.JDBCUtil;



public class KhachHangDAO implements DAOInterface<KhachHangDTO> {

	public static KhachHangDAO getInstace() {
		return new KhachHangDAO();
	}
	
	@Override
	public int insert(KhachHangDTO t) {
		int ketQua=0;
		
		try {
			Connection con=JDBCUtil.getConnection();
			Statement st=con.createStatement();
			String sql="INSERT INTO KHACHHANG "+
					" VALUES ('"+t.getMaKH()+"',N'"+t.getTenKH()+"',N'"+t.getGioiTinh()+"',N'"+t.getDiaChi()+
					"','"+t.getSdt()+"','"+t.getTrangThai()+"')";
			ketQua=st.executeUpdate(sql);
			JDBCUtil.closeConnection(con);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return ketQua;
	}

	@Override
	public int update(KhachHangDTO t) {
		int ketQua=0;
		
		try {
			Connection con=JDBCUtil.getConnection();
			Statement st=con.createStatement();
			String sql="UPDATE KHACHHANG "+
					" SET TenKH=N'"+t.getTenKH()+"',GioiTinh=N'"+t.getGioiTinh()+"',DiaChi=N'"+t.getDiaChi()+
					"',SoDT='"+t.getSdt()+"',TrangThai='"+t.getTrangThai()+"'"
					+"WHERE MaKH='"+t.getMaKH()+"'";
			
			ketQua=st.executeUpdate(sql);
			JDBCUtil.closeConnection(con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public int delete(KhachHangDTO t) {
		int ketQua=0;
		
		try {
			Connection con=JDBCUtil.getConnection();
			Statement st=con.createStatement();
			String sql="UPDATE KHACHHANG "+
			" SET TrangThai=0"+
			" WHERE MaKH='"+t.getMaKH()+"'";
			ketQua=st.executeUpdate(sql);
			JDBCUtil.closeConnection(con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return ketQua;
	}

	@Override
	public int delete_all() {
		int ketQua=0;
		
		try {
			Connection con=JDBCUtil.getConnection();
			Statement st=con.createStatement();
			String sql="DELETE FROM KHACHHANG";
			ketQua=st.executeUpdate(sql);
			JDBCUtil.closeConnection(con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public ArrayList<KhachHangDTO> selectAll() {
		ArrayList<KhachHangDTO> ketQua=new ArrayList<KhachHangDTO>();
		
		try {
			Connection con=JDBCUtil.getConnection();
			Statement st=con.createStatement();
			String sql="SELECT * FROM KHACHHANG "+
						" WHERE TrangThai=1";
			ResultSet rs=st.executeQuery(sql);
			
			while (rs.next()) {
				String maKH=rs.getString("MaKH");
				String tenKH=rs.getString("TenKH");
				String gioiTinh=rs.getString("GioiTinh");
				String diaChi=rs.getString("DiaChi");
				String sdt=rs.getString("SoDT");
				int trangThai=rs.getInt("TrangThai");
				KhachHangDTO newkh=new KhachHangDTO(maKH, tenKH, gioiTinh, diaChi, sdt,trangThai);
				ketQua.add(newkh);
			}
			JDBCUtil.closeConnection(con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public ArrayList<KhachHangDTO> selectById() {
		ArrayList<KhachHangDTO> ketQua=new ArrayList<KhachHangDTO>();
		
		try {
			Connection con=JDBCUtil.getConnection();
			Statement st=con.createStatement();
			String sql="SELECT MaKH FROM KHACHHANG ";
			
			ResultSet rs=st.executeQuery(sql);
			
			while (rs.next()) {
				String maKH=rs.getString("MaKH");
				KhachHangDTO newkh=new KhachHangDTO(maKH, "null", "null", "null", "null", 1);
				ketQua.add(newkh);
			}
			JDBCUtil.closeConnection(con);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public ArrayList<KhachHangDTO> selectByCondition(String condition,String detail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int check(KhachHangDTO t) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
