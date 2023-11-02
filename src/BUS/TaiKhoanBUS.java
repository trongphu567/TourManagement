package BUS;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.KhachHangDAO;
import DAO.TaiKhoanDAO;
import DTO.KhachHangDTO;
import DTO.TaiKhoanDTO;

public class TaiKhoanBUS {
	private ArrayList<TaiKhoanDTO> dstk;
	
	public TaiKhoanBUS() {}
	
	public void addTK(TaiKhoanDTO tk) {
		TaiKhoanDAO tkDAO=new TaiKhoanDAO();
		tkDAO.insert(tk);
	}
	public void editTK(TaiKhoanDTO tk) {
		TaiKhoanDAO tkDAO=new TaiKhoanDAO();
		tkDAO.update(tk);
	}
	public void deleteTK(TaiKhoanDTO tk) {
		TaiKhoanDAO tkDAO=new TaiKhoanDAO();
		tkDAO.delete(tk);
	}
	
	public void hienthitk(JTable table) {
		TaiKhoanDAO chucnang=new TaiKhoanDAO();
		ArrayList<TaiKhoanDTO> list=chucnang.selectAll();
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		model.setRowCount(0);
		for(int i=0;i<list.size();i++) {
			model.addRow(new Object[] {list.get(i).getId(),list.get(i).getUsername(),list.get(i).getPassword(),list.get(i).getLoaiTK(),list.get(i).getTrangThai()});
		}
	}
	public void hienthitk_matk(JTable table,String maTK) {
		TaiKhoanDAO chucnang=new TaiKhoanDAO();
		ArrayList<TaiKhoanDTO> list=chucnang.selectAll();
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		model.setRowCount(0);
		for(int i=0;i<list.size();i++) {
			if (maTK.equalsIgnoreCase(list.get(i).getId()))
				model.addRow(new Object[] {list.get(i).getId(),list.get(i).getUsername(),list.get(i).getPassword(),list.get(i).getLoaiTK(),list.get(i).getTrangThai()});
		}
	}
	public void hienthitk_tentk(JTable table,String tenTK) {
		TaiKhoanDAO chucnang=new TaiKhoanDAO();
		ArrayList<TaiKhoanDTO> list=chucnang.selectAll();
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		model.setRowCount(0);
		for(int i=0;i<list.size();i++) {
			if (tenTK.equalsIgnoreCase(list.get(i).getUsername()))
				model.addRow(new Object[] {list.get(i).getId(),list.get(i).getUsername(),list.get(i).getPassword(),list.get(i).getLoaiTK(),list.get(i).getTrangThai()});
		}
	}
	public void hienthitk_mk(JTable table,String mk) {
		TaiKhoanDAO chucnang=new TaiKhoanDAO();
		ArrayList<TaiKhoanDTO> list=chucnang.selectAll();
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		model.setRowCount(0);
		for(int i=0;i<list.size();i++) {
			if (mk.equalsIgnoreCase(list.get(i).getPassword()))
				model.addRow(new Object[] {list.get(i).getId(),list.get(i).getUsername(),list.get(i).getPassword(),list.get(i).getLoaiTK(),list.get(i).getTrangThai()});
		}
	}
	
	
}
