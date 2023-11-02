package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



import database.JDBCUtil;
import DTO.TourDTO;


public class TourDAO implements DAOInterface<TourDTO>{
	
	public static TourDAO getInstace() {
		return new TourDAO();
	}
	
	@Override
	public int insert(TourDTO t) {
		int ketQua=0;
		
		try {
			Connection con = JDBCUtil.getConnection();
			Statement s = con.createStatement();			
			
			String sql = "INSERT INTO TOUR VALUES"+
					"('"+t.getMatour()+"',N'"+t.getTentour()+"',N'"+t.getDiadiem()+"','"+t.getSongay()+"','"+t.getNgaykh()+
					"','"+t.getNgaykt()+"',N'"+t.getPhuongtien()+"',N'"+t.getNoio()+"','"+t.getTongtien()+ "','"+t.getTrangthai()+"')";
			ketQua = s.executeUpdate(sql);
			
			/*
			PreparedStatement s = con.prepareStatement(
					"Insert into Tour values (?, ?, ?, ?, ?, ?, ?, ?, ?, 1)");
			s.setString(1,t.getMatour());
			s.setString(2,t.getTentour());
			s.setString(3,t.getDiadiem());
			s.setInt(4,t.getSongay());
			s.setString(5,t.getNgaykh());
			s.setString(6,t.getNgaykt());
			s.setString(7,t.getPhuongtien());
			s.setString(8,t.getNoio());
			s.setInt(9,t.getTongtien());
			s.setString(10,t.getTrangthai());
			ketQua = s.executeUpdate();*/			
			
			JDBCUtil.closeConnection(con);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return ketQua;
	}

	@Override
	public int update(TourDTO t) {
		int ketQua=0;
		
		try {
			Connection con=JDBCUtil.getConnection();
//			Statement st=con.createStatement();
//			String sql="UPDATE TOUR "+
//					" SET tenTour='"+t.getTentour()+"',diadiem='"+t.getDiadiem()+"',songay='"+t.getSongay()+
//					"',ngaykh='"+t.getNgaykh()+"',ngaykt='"+t.getNgaykt()+"',phuongtien='"+t.getPhuongtien()+"',noio='"+t.getNoio()+"',tongtien='"+t.getTongtien()+"',trangthai='"+t.getTrangthai()
//			+ "Where matour = '" + t.getMatour();
//			ketQua=st.executeUpdate(sql);
			
			
			PreparedStatement s = con.prepareStatement(
					"Update Tour set tentour=?,diadiem=?,songay=?,ngaykh=?,ngaykt=?,phuongtien=?,noio=?,tongtien=? Where matour=?");
			
			s.setString(1,t.getTentour());
			s.setString(2,t.getDiadiem());
			s.setString(3,t.getSongay());
			s.setString(4,t.getNgaykh());
			s.setString(5,t.getNgaykt());
			s.setString(6,t.getPhuongtien());
			s.setString(7,t.getNoio());
			s.setInt(8,t.getTongtien());
			s.setString(9,t.getMatour());
			ketQua = s.executeUpdate();
			JDBCUtil.closeConnection(con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public int delete(TourDTO t) {
		int ketQua=0;
		
		try {
			Connection con=JDBCUtil.getConnection();
//			Statement st=con.createStatement();
//			String sql="DELETE FROM TOUR "+
//			" WHERE maTour='"+t.getMatour()+"'";
//			ketQua=st.executeUpdate(sql);
			PreparedStatement s = con.prepareStatement("Update Tour set trangthai=0 Where matour=?");
			s.setString(1,t.getMatour());
			ketQua = s.executeUpdate();
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
			String sql="DELETE FROM TOUR";
			ketQua=st.executeUpdate(sql);
			JDBCUtil.closeConnection(con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public ArrayList<TourDTO> selectAll() {
		ArrayList<TourDTO> ketQua = new ArrayList<TourDTO>();
		
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
			String sql = "SELECT * FROM TOUR Where trangthai=1";
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				String matour=rs.getString("matour");
				String tentour=rs.getString("tentour");
				String diadiem=rs.getString("diadiem");
				String songay=rs.getString("songay");
				String ngaykh=rs.getString("ngaykh");
				String ngaykt=rs.getString("ngaykt");
				String phuongtien=rs.getString("phuongtien");
				String noio=rs.getString("noio");
				int tongtien=rs.getInt("tongtien");
				int trangthai = rs.getInt("trangthai");
				TourDTO newtour = new TourDTO(matour, tentour, diadiem, songay, ngaykh, ngaykt, phuongtien, noio, tongtien, trangthai);
				ketQua.add(newtour);
			}
			JDBCUtil.closeConnection(con);
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return ketQua;
	}

	@Override
	public ArrayList<TourDTO> selectById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<TourDTO> selectByCondition(String condition,String detail) {
		// TODO Auto-generated method stub
		ArrayList<TourDTO> ketQua = new ArrayList<TourDTO>();
		//"Mã Tour", "Tên Tour", "Địa điểm"
		try {
			Connection con = JDBCUtil.getConnection();
			PreparedStatement s;// = con.prepareStatement("Update Tour set trangthai=0 Where matour=?");
			if(condition=="Mã Tour") {
				s = con.prepareStatement("Select * From Tour Where matour=?");
			}else if(condition=="Tên Tour"){
				s = con.prepareStatement("Select * From Tour Where tentour=?");
			}else {
				s = con.prepareStatement("Select * From Tour Where diadiem=?");
			}
			s.setString(1,detail);
			ResultSet rs = s.executeQuery();
			
			while (rs.next()) {
				String matour=rs.getString("matour");
				String tentour=rs.getString("tentour");
				String diadiem=rs.getString("diadiem");
				String songay=rs.getString("songay");
				String ngaykh=rs.getString("ngaykh");
				String ngaykt=rs.getString("ngaykt");
				String phuongtien=rs.getString("phuongtien");
				String noio=rs.getString("noio");
				int tongtien=rs.getInt("tongtien");
				int trangthai = rs.getInt("trangthai");
				TourDTO newtour = new TourDTO(matour, tentour, diadiem, songay, ngaykh, ngaykt, phuongtien, noio, tongtien, trangthai);
				ketQua.add(newtour);
			}
			JDBCUtil.closeConnection(con);
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return ketQua;
	}
	@Override
	public int check(TourDTO t) {
		return 0;
	}
}


