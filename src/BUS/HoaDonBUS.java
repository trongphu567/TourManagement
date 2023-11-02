package BUS;

import java.util.ArrayList;

import DAO.HoaDonDAO;
import DAO.MainKhachHangDAO;
import DTO.HoaDonDTO;

public class HoaDonBUS {
	public static HoaDonBUS getInstance() {
		return new HoaDonBUS();
	}
	public ArrayList<HoaDonDTO> importToTable(ArrayList<HoaDonDTO> hd) {
		hd = HoaDonDAO.getInstance().selectAll();
		return hd;
	}
	public int deleteHD() {
		int del = HoaDonDAO.getInstance().delete();
		return del;
	}
}
