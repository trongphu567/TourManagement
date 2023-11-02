package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.JDBCUtil;
import DTO.TaiKhoanDTO;
import DTO.TourDTO;
import GUI.MainKhachHangGUI;

public class MainKhachHangDAO {
	public static MainKhachHangDAO getInstance() {
		return new MainKhachHangDAO();
	}
	
	public ArrayList<TourDTO> selectAll() {
		Connection c = JDBCUtil.getConnection();
		ArrayList<TourDTO> tourSan = new ArrayList<TourDTO>();
		try {
			Statement st = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String sql="SELECT * FROM TOUR WHERE TrangThai=1";		
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String matour=rs.getString("MaTour");
				String tentour=rs.getString("TenTour");
				String diadiem=rs.getString("DiaDiem");
				String songay=rs.getString("SoNgay");
				String ngaykh=rs.getString("NgayKH");
				String ngaykt=rs.getString("NgayKT");
				String phuongtien=rs.getString("PhuongTien");
				String noio=rs.getString("NoiO");
				int tongtien=rs.getInt("TongTien");
				int trangthai = rs.getInt("TrangThai");
				TourDTO newtour = new TourDTO(matour, tentour, diadiem, songay, ngaykh, ngaykt, phuongtien, noio, tongtien, trangthai);
				tourSan.add(newtour);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		JDBCUtil.closeConnection(c);
		return tourSan;
	}
	
	public int insert() {
		Connection c=JDBCUtil.getConnection();
		String sql="INSERT INTO HOADON VALUES(?,?,?,?)";
		try {
			
		PreparedStatement st = c.prepareStatement(sql);
		String nfile=String.format("%02d",MainKhachHangGUI.soHD+1);
		st.setString(1, "HD"+nfile);
		st.setString(2, MainKhachHangGUI.nguoiDat.trim());
		st.setInt(3, MainKhachHangGUI.tongTour);
		st.setInt(4, 1);
		st.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(c);
		return 0;
	}

	public boolean hasExistedID(String id) {
		boolean flag = false;
		Connection c=JDBCUtil.getConnection();
		String sql = "SELECT * FROM HOADON WHERE ID="+id;
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
	
	public int numOfHD() {
		MainKhachHangGUI.soHD=0;
		Connection connection = JDBCUtil.getConnection();
		String sql = "SELECT * FROM HOADON";
		Statement statement;
        try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
			    MainKhachHangGUI.soHD++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        JDBCUtil.closeConnection(connection);
        return MainKhachHangGUI.soHD;
	}
	
}
