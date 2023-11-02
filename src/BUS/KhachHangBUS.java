package BUS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import DTO.TourDTO;


public class KhachHangBUS {
	private ArrayList<KhachHangDTO> dskh;
	
	public KhachHangBUS() {}
	
	public void addKH(KhachHangDTO kh) {
		
		KhachHangDAO khDAO=new KhachHangDAO();
		khDAO.insert(kh);
	}
	public void editKH(KhachHangDTO kh) {
		KhachHangDAO khDAO=new KhachHangDAO();
		khDAO.update(kh);
	}
	public void deleteKH(KhachHangDTO kh) {
		KhachHangDAO khDAO=new KhachHangDAO();
		khDAO.delete(kh);
		
	}

	
	public void hienthikh(JTable table) {
		KhachHangDAO chucnang=new KhachHangDAO();
		ArrayList<KhachHangDTO> list=chucnang.selectAll();
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		model.setRowCount(0);
		for(int i=0;i<list.size();i++) {
			model.addRow(new Object[] {list.get(i).getMaKH(),list.get(i).getTenKH(),list.get(i).getGioiTinh(),list.get(i).getDiaChi(),list.get(i).getSdt(),list.get(i).getTrangThai()});
		}
	}
	public void hienthikh_makh(JTable table,String maKH) {
		KhachHangDAO chucnang=new KhachHangDAO();
		ArrayList<KhachHangDTO> list=chucnang.selectAll();
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		model.setRowCount(0);
		for(int i=0;i<list.size();i++) {
			if (maKH.equalsIgnoreCase(list.get(i).getMaKH()))
				model.addRow(new Object[] {list.get(i).getMaKH(),list.get(i).getTenKH(),list.get(i).getGioiTinh(),list.get(i).getDiaChi(),list.get(i).getSdt(),list.get(i).getTrangThai()});
		}
	}
	public void hienthikh_tenkh(JTable table,String tenKH) {
		KhachHangDAO chucnang=new KhachHangDAO();
		ArrayList<KhachHangDTO> list=chucnang.selectAll();
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		model.setRowCount(0);
		for(int i=0;i<list.size();i++) {
			if (tenKH.equalsIgnoreCase(list.get(i).getTenKH()))
				model.addRow(new Object[] {list.get(i).getMaKH(),list.get(i).getTenKH(),list.get(i).getGioiTinh(),list.get(i).getDiaChi(),list.get(i).getSdt(),list.get(i).getTrangThai()});
		}
	}
	public void hienthikh_gioiTinh(JTable table,String gioiTinh) {
		KhachHangDAO chucnang=new KhachHangDAO();
		ArrayList<KhachHangDTO> list=chucnang.selectAll();
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		model.setRowCount(0);
		for(int i=0;i<list.size();i++) {
			if (gioiTinh.equalsIgnoreCase(list.get(i).getGioiTinh()))
				model.addRow(new Object[] {list.get(i).getMaKH(),list.get(i).getTenKH(),list.get(i).getGioiTinh(),list.get(i).getDiaChi(),list.get(i).getSdt(),list.get(i).getTrangThai()});
		}
	}
	public void hienthikh_diachi(JTable table,String diaChi) {
		KhachHangDAO chucnang=new KhachHangDAO();
		ArrayList<KhachHangDTO> list=chucnang.selectAll();
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		model.setRowCount(0);
		for(int i=0;i<list.size();i++) {
			if (diaChi.equalsIgnoreCase(list.get(i).getDiaChi()))
				model.addRow(new Object[] {list.get(i).getMaKH(),list.get(i).getTenKH(),list.get(i).getGioiTinh(),list.get(i).getDiaChi(),list.get(i).getSdt(),list.get(i).getTrangThai()});
		}
	}
//	public boolean nhapkh_excel(String makh) {
//		int kq=1;
//		KhachHangDAO chucnang=new KhachHangDAO();
//		ArrayList<KhachHang> list=new ArrayList<>();
//		
//	}
	public boolean xuatkh_excel() {
		KhachHangDAO chucnang=new KhachHangDAO();
		ArrayList<KhachHangDTO> list=chucnang.selectAll();
		
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("DANH SÁCH KHÁCH HÀNG");
		XSSFRow row=null;
		
		row=sheet.createRow(0);
		
		Cell cell0=row.createCell(0);
		Cell cell1=row.createCell(1);
		Cell cell2=row.createCell(2);
		Cell cell3=row.createCell(3);
		Cell cell4=row.createCell(4);
		Cell cell5=row.createCell(5);
		Cell cell6=row.createCell(6);
		
		cell0.setCellValue("STT");
		cell1.setCellValue("Mã khách hàng");
		cell2.setCellValue("Tên khách hàng");
		cell3.setCellValue("Giới tính");
		cell4.setCellValue("Địa chỉ");
		cell5.setCellValue("Số ĐT");
		cell6.setCellValue("Trạng thái");
		
		for (int i=0;i<list.size();i++) {
			
			KhachHangDTO khachHangDTO=list.get(i);
			row=sheet.createRow(i+1);
			
			cell0=row.createCell(0, CellType.NUMERIC);
			cell0.setCellValue(i+1);
			
			cell1=row.createCell(1,CellType.STRING);
			cell1.setCellValue(khachHangDTO.getMaKH());
			
			cell2=row.createCell(2,CellType.STRING);
			cell2.setCellValue(khachHangDTO.getTenKH());
			
			cell3=row.createCell(3,CellType.STRING);
			cell3.setCellValue(khachHangDTO.getGioiTinh());
			
			cell4=row.createCell(4,CellType.STRING);
			cell4.setCellValue(khachHangDTO.getDiaChi());
			
			cell5=row.createCell(5,CellType.STRING);
			cell5.setCellValue(khachHangDTO.getSdt());
			
			cell6=row.createCell(6,CellType.NUMERIC);
			cell6.setCellValue(khachHangDTO.getTrangThai());
			
			
		}
		for (int i=0;i<7;i++) 
			sheet.autoSizeColumn(i);
		
		JFileChooser openFileChooser=new JFileChooser();
		openFileChooser.setDialogTitle("Save file");
		openFileChooser.removeChoosableFileFilter(openFileChooser.getFileFilter());
		FileNameExtensionFilter filter=new FileNameExtensionFilter("Excel file (.xlsx)", "xlsx");
		openFileChooser.setFileFilter(filter);
		
		if (openFileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
			File inputFile=openFileChooser.getSelectedFile();
			try {
				FileOutputStream outputStream=new FileOutputStream(inputFile);
				workbook.write(outputStream);
				outputStream.close();
				return true;
			}
			catch (Exception e) {
				return false;
			}
		}
		return false;
	}
	

	
}
