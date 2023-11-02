package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.JDBCUtil;
import DTO.NhanVienDTO;

public class NhanVienDAO {
	
	public static NhanVienDAO getInstance() 
	{ 
		return new NhanVienDAO();
	}
	
	public int insert(NhanVienDTO t) {
		Connection c=JDBCUtil.getConnection();
		String sql="INSERT INTO NHANVIEN VALUES(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1,t.getID());
			st.setString(2,t.getHoten());
			st.setString(3,t.getGioitinh());
			st.setString(4,t.getNgaysinh());
			st.setString(5,t.getDiachi());
			st.setString(6,t.getSdt());
			st.setString(7,t.getChucvu());
			st.setInt(8,1);
			st.execute();
			st.close();
		} 
		catch (SQLException e) {
			System.out.println("Can't create statement");
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(c);
		return 0;
	}
	
	public int update(String oldID, String newID, NhanVienDTO t) {
		Connection c=JDBCUtil.getConnection();
		String sql = "UPDATE NHANVIEN SET "
				+ "ID=?, HoTen=?, GioiTinh=?, NgaySinh=?, DiaChi=?, SoDT=?, ChucVu=?, TrangThai=?"
				+ " WHERE ID=?";
		try {
			System.out.println("oldid="+oldID+" newID="+newID);
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1,newID);
			st.setString(2,t.getHoten());
			st.setString(3,t.getGioitinh());
			st.setString(4,t.getNgaysinh());
			st.setString(5,t.getDiachi());
			st.setString(6,t.getSdt());
			st.setString(7,t.getChucvu());
			st.setInt(8,1);
			st.setString(9,oldID);
			st.executeUpdate();
			st.close();
		} 
		catch (SQLException e1) {
			System.out.println("Can't execute");
			e1.printStackTrace();
		}
		JDBCUtil.closeConnection(c);
		return 0;
	}

	public int delete(NhanVienDTO t) {
		Connection c=JDBCUtil.getConnection();
		String sql ="UPDATE NHANVIEN SET TrangThai=? WHERE ID=?";
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1,0);
			st.setString(2,t.getID());
			st.executeUpdate();
			st.close();
		} 
		catch (SQLException e1) {
			System.out.println("Can't execute");
			e1.printStackTrace();
		}
		JDBCUtil.closeConnection(c);
		return 0;
	}

	public ArrayList<NhanVienDTO> selectAll() {
		ArrayList<NhanVienDTO> list=new ArrayList<NhanVienDTO>();
		Connection c = JDBCUtil.getConnection();
		String sql="SELECT * FROM NHANVIEN WHERE TrangThai=1";
		try {
			Statement st = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				String id=rs.getString("ID");
				String hoTen=rs.getString("HoTen");
				String gioiTinh=rs.getString("GioiTinh");
				String ngaySinh=rs.getString("NgaySinh");
				String diaChi=rs.getString("DiaChi");
				String sdt=rs.getString("SoDT");
				String chucVu=rs.getString("ChucVu");
				NhanVienDTO nv=new NhanVienDTO(id,hoTen,gioiTinh,ngaySinh,diaChi,sdt,chucVu);
				list.add(nv);
			}
		}
		catch(SQLException e) 
			{e.printStackTrace();}
		JDBCUtil.closeConnection(c);
		return list;
	}
	
	public boolean hasExistedID(String id) {
		boolean flag = false;
		Connection c=JDBCUtil.getConnection();
		String sql = "SELECT * FROM NHANVIEN WHERE ID="+id;
		try {
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(sql);
		flag = rs.next();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
}
