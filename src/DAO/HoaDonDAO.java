package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.JDBCUtil;
import DTO.HoaDonDTO;
import GUI.MainKhachHangGUI;

public class HoaDonDAO  {
	public static HoaDonDAO getInstance() {
		return new HoaDonDAO();
	}
	
	public int delete() {
		Connection c=JDBCUtil.getConnection();
		String nfile=String.format("%02d",MainKhachHangGUI.numFile);
		String sql="UPDATE HoaDon "+
				" SET "+
				" TrangThai=0"+
				" WHERE ID='"+"HD"+nfile+"\'";
		PreparedStatement st;
		try {
			st = c.prepareStatement(sql);
			st.executeUpdate();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(c);
		return 0;
	}
	
	public ArrayList<HoaDonDTO> selectAll() {
        ArrayList<HoaDonDTO> hoaDonList = new ArrayList<>();
        Connection connection = JDBCUtil.getConnection();
        String sql = "SELECT * FROM HOADON WHERE TrangThai=1";
        Statement statement;
        try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
			    String maHoaDon = rs.getString("ID");
			    String tenTaiKhoan = rs.getString("TenTK");
			    int tongTien = rs.getInt("TongTien");
			    HoaDonDTO newHD = new HoaDonDTO(maHoaDon, tenTaiKhoan,tongTien);
			    hoaDonList.add(newHD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        JDBCUtil.closeConnection(connection);
        return hoaDonList;
    }
    
	public HoaDonDTO selectByID(HoaDonDTO t) {
    	Connection connection = JDBCUtil.getConnection();
        String sql = "SELECT * FROM HOADON WHERE ID = ?";
        try {
        	PreparedStatement ps = connection.prepareStatement(sql);
        	//ps.setString(1, ID);
        	ResultSet rs = ps.executeQuery();
        	if (rs.next()) {
        		HoaDonDTO hd = new HoaDonDTO(sql, sql, 0);
        		hd.setMaHoaDon(rs.getString("MaHoaDon"));
        		hd.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
        		hd.setTongTien(rs.getInt("TongTien"));
        		return hd;
        	} else return null;
        }
        catch (SQLException e) {
			e.printStackTrace();
        }
        JDBCUtil.closeConnection(connection);
        return null;
	}
    
	public ArrayList<HoaDonDTO> selectByCondition(String keyword) {
		Connection connection = JDBCUtil.getConnection();
        ArrayList<HoaDonDTO> hoaDonList = new ArrayList<>();
        String sql = "SELECT * FROM HOADON WHERE MaHoaDon LIKE ? OR TenKhachHang LIKE ?";
        try {
        	PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	HoaDonDTO hoaDon = new HoaDonDTO(
            			rs.getString("MaHoaDon"),
                        rs.getString("TenKhachHang"),
                        rs.getInt("TongTien")
                    );
            hoaDonList.add(hoaDon);
            }
        }
        catch (SQLException e) {
			e.printStackTrace();
        }
        JDBCUtil.closeConnection(connection);
        return hoaDonList;
	}
	
	
}

