package BUS;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import DAO.MainKhachHangDAO;
import DAO.NhanVienDAO;
import DTO.NhanVienDTO;

public class NhanVienBUS {
	
	public static NhanVienBUS getInstance() {
		return new NhanVienBUS();
	}
	
	public int addNV(String id, NhanVienDTO nv) {
		int flag;
		boolean res = NhanVienDAO.getInstance().hasExistedID(id);
		if(res==false) {
			NhanVienDAO.getInstance().insert(nv);
			flag=1;
		}
		else { 
			JOptionPane.showMessageDialog(null,"ID đã tồn tại");
			flag=0;
			}
		return flag;
	}
	
	public int updateNV(String oldID, String newID, NhanVienDTO nv) {
		int flag;
		boolean res = NhanVienDAO.getInstance().hasExistedID(newID);
		if(res==false) {
			NhanVienDAO.getInstance().update(oldID,newID,nv);
			flag=1;
		}
		else { 
			JOptionPane.showMessageDialog(null,"ID đã tồn tại");
			flag=0;
			}
		return flag;
	}
	
	public void deleteNV(NhanVienDTO nv) {
		NhanVienDAO.getInstance().delete(nv);
	}
	
	public ArrayList<NhanVienDTO> importToTable(ArrayList<NhanVienDTO> nvlist) {
		nvlist = NhanVienDAO.getInstance().selectAll();
		return nvlist;
	}
}

