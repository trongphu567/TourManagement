package BUS;

import DTO.TourDTO;
import DAO.MainKhachHangDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class MainKhachHangBUS {
	
	public static MainKhachHangBUS getInstance() {
		return new MainKhachHangBUS();
	}
	public int addHD(String id) {
		int flag;
		boolean res = MainKhachHangDAO.getInstance().hasExistedID(id);
		if(res==false) {
			MainKhachHangDAO.getInstance().insert();
			flag=1;
		}
		else { 
			JOptionPane.showMessageDialog(null,"ID đã tồn tại");
			flag=0;
			}
		return flag;
	}
	public int countHD() {
		int count = MainKhachHangDAO.getInstance().numOfHD();
		return count;
	}
	public ArrayList<TourDTO> importToTable(ArrayList<TourDTO> tourSan) {
		tourSan = MainKhachHangDAO.getInstance().selectAll();
		return tourSan;
	}
}
