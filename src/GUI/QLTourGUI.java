package GUI;

import DAO.TourDAO;

import java.lang.Object;
import java.text.Format;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import javax.swing.JFormattedTextField;
import DTO.TourDTO;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
//import junit.framework.TestFailure;
//import junit.framework.Test;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.io.*;

public class QLTourGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField_maTour;
	private JTextField textField_songay;
	private JTextField textField_diadiem;
	private JTextField textField_total;
	private JTextField textField_search;
	private JTable table;
	DefaultTableModel model;
	private JTextField textField_ngaykh;
	private JTextField textField_ngaykt;
	private JComboBox comboBox_transport;
	private JComboBox comboBox_place;
	private TourDAO tourDAO = new TourDAO();
	
	Connection conn;
	
	final String column[] = {"Mã tour", "Tên tour", "Địa điểm", "Số ngày", "Ngày khởi hành", "Ngày kết thúc", "Phương tiện", "Nơi ở", "Tổng tiền" };
	final DefaultTableModel tb = new DefaultTableModel(column,0);
	private JTextField textField_tenTour;
	
	public QLTourGUI() {
		initComponents();
		loadBang(tourDAO.selectAll());// hàm hiển thị thông tin từ sql server lên table
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		//UIManager.setLookAndFeel(new FlatLightLaf());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLTourGUI frame = new QLTourGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
	}

	
	/**
	 * Create the frame.
	 * @return 
	 */
	public void initComponents() {
		setTitle("Quản lý tour");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblQunLTour = new JLabel("Quản lý tour");
		lblQunLTour.setIcon(new ImageIcon(QLTourGUI.class.getResource("/icons/tour.png")));
		lblQunLTour.setForeground(Color.RED);
		lblQunLTour.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLTour.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblQunLTour.setBounds(215, 0, 290, 44);
		contentPane.add(lblQunLTour);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 46, 776, 393);
		contentPane.add(panel);
		panel.setLayout(null);
		
    //NHẬP THÔNG TIN VÀ BUTTON
		JLabel label_maTour = new JLabel("Mã Tour:");
		label_maTour.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_maTour.setBounds(10, 10, 132, 34);
		panel.add(label_maTour);

		textField_maTour = new JTextField();
		textField_maTour.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_maTour.setBounds(121, 11, 142, 34);
		panel.add(textField_maTour);
		textField_maTour.setColumns(10);
		
		JLabel label_tenTour = new JLabel("Tên Tour:");
		label_tenTour.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_tenTour.setBounds(289, 14, 109, 27);
		panel.add(label_tenTour);
		
		textField_tenTour = new JTextField();
		textField_tenTour.setColumns(10);
		textField_tenTour.setBounds(406, 20, 162, 29);
		panel.add(textField_tenTour);

		JLabel label_soNgay = new JLabel("Số ngày:");
		label_soNgay.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_soNgay.setBounds(10, 77, 80, 34);
		panel.add(label_soNgay);
		
		textField_songay = new JTextField();
		textField_songay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_songay.setBounds(121, 78, 142, 34);
		panel.add(textField_songay);
		textField_songay.setColumns(10);
		
		JLabel lblaim = new JLabel("Địa điểm:");
		lblaim.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblaim.setBounds(289, 77, 102, 34);
		panel.add(lblaim);
		
		textField_diadiem = new JTextField();
		textField_diadiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_diadiem.setBounds(406, 80, 162, 34);
		panel.add(textField_diadiem);
		textField_diadiem.setColumns(10);
		
		JLabel label_ngaykh = new JLabel("Ngày khởi hành:");
		label_ngaykh.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_ngaykh.setBounds(4, 140, 128, 34);
		panel.add(label_ngaykh);
		
//		DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
//		JFormattedTextField textField_ngaykh = new JFormattedTextField(format);
		textField_ngaykh = new JTextField();
		textField_ngaykh.setBounds(121, 140, 142, 29);
		panel.add(textField_ngaykh);
		textField_ngaykh.setColumns(10);
		
		JLabel label_ngaykt = new JLabel("Ngày kết thúc:");
		label_ngaykt.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_ngaykt.setBounds(289, 138, 118, 34);
		panel.add(label_ngaykt);    
		
		//DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
//		JFormattedTextField textField_ngaykt = new JFormattedTextField(format);
		textField_ngaykt = new JTextField();
		textField_ngaykt.setColumns(10);
		textField_ngaykt.setBounds(406, 145, 162, 29);
		panel.add(textField_ngaykt);
    
		JLabel lblPhngTin = new JLabel("Phương tiện:");
		lblPhngTin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPhngTin.setBounds(10, 201, 156, 34);
		panel.add(lblPhngTin);
		
		comboBox_transport = new JComboBox();
		comboBox_transport.setModel(new DefaultComboBoxModel(new String[] {"Máy bay", "Xe khách"}));
		comboBox_transport.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox_transport.setBounds(121, 201, 150, 34);
		panel.add(comboBox_transport);
		
		JLabel label_place = new JLabel("Chọn nơi ở:");
		label_place.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_place.setBounds(289, 201, 123, 34);
		panel.add(label_place);
		
		comboBox_place = new JComboBox();
		comboBox_place.setModel(new DefaultComboBoxModel(new String[] {"Khách sạn", "Homestay", "Villa"}));
		comboBox_place.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox_place.setBounds(406, 201, 162, 34);
		panel.add(comboBox_place);
    
		JLabel label_total = new JLabel("Tổng tiền:");
		label_total.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_total.setBounds(10, 255, 142, 34);
		panel.add(label_total);
		
		textField_total = new JTextField();
		textField_total.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_total.setBounds(121, 245, 440, 34);
		panel.add(textField_total);
		textField_total.setColumns(10);
		
		//Nút Thêm
		JButton btn_add = new JButton("Thêm");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_maTour.getText().equals("")||textField_tenTour.getText().equals("")||textField_songay.getText().equals("")||textField_ngaykh.getText().equals("")||textField_ngaykt.getText().equals(""))
					{	
						JOptionPane.showMessageDialog(null, "Hãy điền đầy đủ thông tin!");		
						return;
						
					}
				int validDate=0;
				try {
					LocalDate.parse(textField_ngaykt.getText(),DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT));
					LocalDate.parse(textField_ngaykh.getText(),DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT));
					validDate=1;
				}
				catch(DateTimeParseException de) {
					de.printStackTrace();
					validDate=0;
				}
				if(validDate==0) {
					JOptionPane.showMessageDialog(null,"Ngày không hợp lệ!");
					return;
				}
				try {
					if(Integer.parseInt(textField_total.getText()) <= 0) {
						JOptionPane.showMessageDialog(null,"Tổng tiền phải là số dương!!!");
						return;
				}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"Tổng tiền phải là số");
					return;
				}
				
				int ketQua = tourDAO.insert(new TourDTO(
						textField_maTour.getText(),
						textField_tenTour.getText(),
						textField_diadiem.getText(),
						textField_songay.getText(),
						textField_ngaykh.getText(),
						textField_ngaykt.getText(),
						comboBox_transport.getSelectedItem().toString(),
						comboBox_place.getSelectedItem().toString(),
						Integer.parseInt(textField_total.getText()),
						1
					));
				
				if (ketQua > 0 ) { 
					
						JOptionPane.showMessageDialog(null, "Thêm thông tin Tour thành công!");
						xoaTrang();
					}
	
				else JOptionPane.showMessageDialog(null, "Thêm thông tin Tour thất bại!");
				
			}
			
		});		
		btn_add.setBackground(new Color(118, 197, 190));
		btn_add.setIcon(new ImageIcon(QLTourGUI.class.getResource("/icons/add_tour.png")));
		btn_add.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_add.setBounds(604, 14, 132, 43);
		panel.add(btn_add);
		
		//Nút Sửa
		JButton btn_update = new JButton("Sửa");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_maTour.getText().equals("")||textField_tenTour.getText().equals("")||textField_songay.getText().equals("")||textField_ngaykh.getText().equals("")||textField_ngaykt.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Hãy điền đầy đủ thông tin!");
					return;
				}
				
				int validDate=0;
				try {
					LocalDate.parse(textField_ngaykt.getText(),DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT));
					LocalDate.parse(textField_ngaykh.getText(),DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT));
					validDate=1;
				}
				catch(DateTimeParseException de) {
					de.printStackTrace();
					validDate=0;
				}
				if(validDate==0) {
					JOptionPane.showMessageDialog(null,"Ngày không hợp lệ!");
					return;
				}
				try {
					if(Integer.parseInt(textField_total.getText()) <= 0) {
						JOptionPane.showMessageDialog(null,"Tổng tiền phải là số dương!!!");
						return;
				}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"Tổng tiền phải là số");
					return;
				}
				int ketQua = tourDAO.update(new TourDTO(
						textField_maTour.getText(),
						textField_tenTour.getText(),
						textField_diadiem.getText(),
						textField_songay.getText(),
						textField_ngaykh.getText(),
						textField_ngaykt.getText(),
						comboBox_transport.getSelectedItem().toString(),
						comboBox_place.getSelectedItem().toString(),
						Integer.parseInt(textField_total.getText()),
						1
					));
				if (ketQua > 0) { JOptionPane.showMessageDialog(null, "Sửa thông tin Tour thành công!"); xoaTrang(); }
				else JOptionPane.showMessageDialog(null, "Sửa thông tin Tour thất bại!");
			}
		});
		btn_update.setBackground(Color.GREEN);
		btn_update.setIcon(new ImageIcon(QLTourGUI.class.getResource("/icons/edit_tour.png")));
		btn_update.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_update.setBounds(604, 67, 132, 43);
		panel.add(btn_update);
		
		//Nút Xóa
		JButton btn_delete = new JButton("Xoá");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ketQua = tourDAO.delete(new TourDTO(
						textField_maTour.getText(),
						textField_tenTour.getText(),
						textField_diadiem.getText(),
						textField_songay.getText(),
						textField_ngaykh.getText(),
						textField_ngaykt.getText(),
						comboBox_transport.getSelectedItem().toString(),
						comboBox_place.getSelectedItem().toString(),
						Integer.parseInt(textField_total.getText()),
						1
					));
				if (ketQua > 0) { JOptionPane.showMessageDialog(null, "Xóa thông tin Tour thành công!"); xoaTrang(); }
				else JOptionPane.showMessageDialog(null, "Xóa thông tin Tour thất bại!");
			}
		});
		btn_delete.setBackground(Color.YELLOW);
		btn_delete.setIcon(new ImageIcon(QLTourGUI.class.getResource("/icons/del_tour.png")));
		btn_delete.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_delete.setBounds(604, 120, 132, 43);
		panel.add(btn_delete);
		
		//Nút Reset
		JButton btn_reset = new JButton("Reset");
		btn_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrang();
			}
		});
		btn_reset.setBackground(new Color(255, 255, 255));
		btn_reset.setIcon(new ImageIcon(QLTourGUI.class.getResource("/icons/reset_tour.png")));
		btn_reset.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_reset.setBounds(604, 173, 132, 39);
		panel.add(btn_reset);		
		
		//XEM TOÀN BỘ TOUR DU LỊCH
		JLabel lblTmKim = new JLabel("Tìm kiếm:");
		lblTmKim.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTmKim.setBounds(10, 310, 132, 31);
		panel.add(lblTmKim);
		
		JLabel lblNewLabel = new JLabel("Loại:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(46, 334, 96, 34);
		panel.add(lblNewLabel);
		
		JComboBox comboBox_loai = new JComboBox();
		comboBox_loai.setModel(new DefaultComboBoxModel(new String[] {"-----", "Mã Tour", "Tên Tour", "Địa điểm"}));
		comboBox_loai.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox_loai.setBounds(96, 334, 132, 34);
		panel.add(comboBox_loai);
		
		JLabel lblNewLabel_1 = new JLabel("Tên:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(257, 334, 61, 34);
		panel.add(lblNewLabel_1);
		
		textField_search = new JTextField();
		textField_search.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_search.setBounds(299, 335, 156, 34);
		panel.add(textField_search);
		textField_search.setColumns(10);
		
		//Search Button
		JButton btn_search = new JButton("Tìm kiếm");
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dk1=comboBox_loai.getSelectedItem().toString();
				String dk2=textField_search.getText();
				if(dk1=="-----"||dk2.equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ điều kiện tìm kiếm");
				}else {
					loadBang(tourDAO.selectByCondition(dk1,dk2));
				}
			}
		});
		btn_search.setBackground(Color.CYAN);
		btn_search.setIcon(new ImageIcon(QLTourGUI.class.getResource("/icons/search_tour.png")));
		btn_search.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_search.setBounds(485, 334, 141, 34);
		panel.add(btn_search);
		
		// Back Button
		JButton btn_back = new JButton("Quay lại");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==btn_back) {
					setVisible(false);
					new MainAdminGUI();
				}
			}
		});
		btn_back.setBackground(new Color(255, 128, 0));
		btn_back.setIcon(new ImageIcon(QLTourGUI.class.getResource("/icons/back.png")));
		btn_back.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_back.setBounds(636, 334, 130, 34);
		panel.add(btn_back);
		
		// Button Import
		JButton btn_import = new JButton("Import ");
		btn_import.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				String fileName = "D:\\Aloha.xlsx";
//				Import(table,fileName);
				JFileChooser jfileChooser_nhap = new JFileChooser("D:\\");  
				jfileChooser_nhap.addChoosableFileFilter(new FileNameExtensionFilter("Excel workbook(.xlsx)","xlsx"));
				jfileChooser_nhap.addChoosableFileFilter(new FileNameExtensionFilter("Excel 97-2003 workbook(.xls)","xls"));
				jfileChooser_nhap.setAcceptAllFileFilterUsed(false); // chi luu file excel,Loai bo viec chon nhung file ko lien quan
				int userSelection = jfileChooser_nhap.showOpenDialog(QLTourGUI.this); //show hop thoai mo va tra ve quyet dinh cua nguoi dung
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToOpen = jfileChooser_nhap.getSelectedFile();
					String fileName = fileToOpen.getAbsolutePath();
					Import(table,fileName);
				}
			}
		});
		btn_import.setBackground(new Color(255, 0, 0));
		btn_import.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_import.setBounds(604, 271, 132, 39);
		panel.add(btn_import);
		
		//Button Export
		JButton btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				String fileName = "D:\\Aloha.xlsx";
//				Export(table,fileName);
				JFileChooser jfileChooser_luu = new JFileChooser("D:\\");  // jfileChooser mặc định ở ổ D
				jfileChooser_luu.addChoosableFileFilter(new FileNameExtensionFilter("Excel workbook(.xlsx)","xlsx"));
				jfileChooser_luu.addChoosableFileFilter(new FileNameExtensionFilter("Excel 97-2003 workbook(.xls)","xls"));
				jfileChooser_luu.setAcceptAllFileFilterUsed(false); // chi luu file excel,Loai bo viec chon nhung file ko lien quan
				int userSelection = jfileChooser_luu.showSaveDialog(QLTourGUI.this); //show hop thoai luu va tra ve quyet dinh cua nguoi dung
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = jfileChooser_luu.getSelectedFile();
					String fileName = fileToSave.getAbsolutePath();
					String filter = jfileChooser_luu.getFileFilter().getDescription();
					if(!fileName.endsWith(".xlsx")&&!fileName.endsWith(".xls")) {
						if(filter == "Excel workbook(.xlsx)") {
							fileName += ".xlsx";
						}else {
							fileName += ".xls";
						}
					}
					Export(table,fileName);
				}
			}
		});
		btnExport.setBackground(new Color(255, 0, 0));
		btnExport.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExport.setBounds(604, 222, 132, 39);
		panel.add(btnExport);
    
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 449, 776, 177);
		contentPane.add(scrollPane);
		
		table = new JTable();
		final Object[] row = new Object[9];
		tb.setColumnIdentifiers(column);
		table.setModel(tb);
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //chỉ cho chọn 1 hàng
		setVisible(true);
	}

    
	
	//hàm dùng để load dữ liệu từ sql server lên bảng
	public void loadBang(ArrayList<TourDTO> tourList) {
		try {
			
			tb.setRowCount(0);   
			if( tourList.size() == 0) return; 
			for (int i=0; i<tourList.size(); i++)
			{
				Object[] data = { 
					tourList.get(i).getMatour(),
					tourList.get(i).getTentour(),
					tourList.get(i).getDiadiem(),
					tourList.get(i).getSongay(),
					tourList.get(i).getNgaykh(),
					tourList.get(i).getNgaykt(),
					tourList.get(i).getPhuongtien(),
					tourList.get(i).getNoio(),
					tourList.get(i).getTongtien(),
					tourList.get(i).getTrangthai()
				};
				tb.addRow(data); 
			}
			table.setModel(tb);
			
	
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() { 
			@Override
			public void valueChanged(ListSelectionEvent e) { 
				//if(table.getSelectedRow() >= 0) {
					textField_maTour.setEnabled(false);
					textField_maTour.setText(table.getValueAt(table.getSelectedRow(), 0).toString()+ "");
					textField_tenTour.setText(table.getValueAt(table.getSelectedRow(), 1).toString()+ "");
					textField_diadiem.setText(table.getValueAt(table.getSelectedRow(), 2).toString()+ "");
					textField_songay.setText(table.getValueAt(table.getSelectedRow(), 3).toString()+ "");
					textField_ngaykh.setText(table.getValueAt(table.getSelectedRow(), 4).toString()+ "");
					textField_ngaykt.setText(table.getValueAt(table.getSelectedRow(), 5).toString()+ "");
					comboBox_transport.setSelectedItem(table.getValueAt(table.getSelectedRow(),6).toString()+"");
					comboBox_place.setSelectedItem(table.getValueAt(table.getSelectedRow(),7).toString()+""); 
					textField_total.setText(table.getValueAt(table.getSelectedRow(), 8).toString()+ "");
				//}
			}
		});
	}
  
  //ham nay se xoa trang sau khi them sua xoa
  private void xoaTrang() {
	  textField_maTour.setEnabled(true);
      textField_maTour.setText("");
      textField_tenTour.setText("");
      textField_diadiem.setText("");
      textField_songay.setText("");
      textField_ngaykh.setText("");
      textField_ngaykt.setText("");
      comboBox_transport.setSelectedIndex(0);
      comboBox_place.setSelectedIndex(0);
      textField_total.setText("");
      loadBang(tourDAO.selectAll());
    }
  
  //ham export
  private void Export(JTable tableExcel,String fileName ) {
	  Workbook workbook = taoWorkbook(fileName);
	  if(workbook == null) {
		  JOptionPane.showMessageDialog(null,"Đây không phải file excel");
		  return;
	  }
	  Sheet sheet = workbook.createSheet("File Export");
	  TableModel tbModel = tableExcel.getModel();
	  for(int i = 0 ; i <= tbModel.getRowCount();i++) { //tinh tong so hang trong tableModel
		  Row row = sheet.createRow(i);
		  for(int j=0;j< tbModel.getColumnCount();j++) {
			  String value;
			  if(i>0) {
				  value = tbModel.getValueAt(i-1,j).toString();
			  }else {
				  value = tbModel.getColumnName(j);
			  }
			  row.createCell(j).setCellValue(value);
		  }
	  }  
	  for (short j=0; j<tbModel.getColumnCount(); j++) sheet.autoSizeColumn(j); // định dạng format - chỉnh kích thước column trong file excel
	try {
		FileOutputStream fileOut = new FileOutputStream(fileName);
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();
		JOptionPane.showMessageDialog(null, "Export thành công");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, "Export không thành công");
	}
	 
	  
  }
  private Workbook taoWorkbook(String fileName) {
	  Workbook workbook = null;
	  if(fileName.endsWith("xls")) {
		  workbook = new HSSFWorkbook();
	  }else if (fileName.endsWith("xlsx")) {
		  workbook = new XSSFWorkbook();
	  }
	  return workbook;
  }
  
  
  //ham Import
  private void Import(JTable tableExcel,String fileName ) {
	  try {
		  FileInputStream fileIn = new FileInputStream(fileName);
		  Workbook workbook = nhapWorkbook(fileName,fileIn);
		  ArrayList<TourDTO> t = new ArrayList<TourDTO>(); // arraylist<tour> để đổ dữ liệu vào loadBang
		  if(workbook == null) {
			  JOptionPane.showMessageDialog(null,"Đây không phải file excel");
			  return;
		  }
		  Sheet sheet = workbook.getSheet("File Export");
		  int row = sheet.getPhysicalNumberOfRows();
		  int column = sheet.getRow(0).getPhysicalNumberOfCells();
		  for(int i = 1; i< row;i++) {
			  ArrayList<String> argument =new ArrayList<String>();// argument là biến tạm lưu trữ các thông tin của thuộc tính trong tour
			  for(int j= 0; j < column; j++) {
				  argument.add(sheet.getRow(i).getCell(j).toString()); //thêm thông tin lần lượt theo dòng và cột
			  }
			  TourDTO tour = new TourDTO(argument.get(0), argument.get(1), argument.get(2),
					  			   argument.get(3), argument.get(4), argument.get(5),
					  			   argument.get(6), argument.get(7), 0, 1);
			  
			  try {
				  int tien = (int)(Float.parseFloat(argument.get(8))); tour.setTongtien(tien);//Excel hiểu số ở dạng số thực nên phải chuyển về số nguyên
			  }catch(Exception e) {
				  
			  }
			  t.add(tour);
		  }
		  JOptionPane.showMessageDialog(null, "Nhập thành công!!");
		  loadBang(t);
		  fileIn.close();
		  workbook.close();
	  }catch(Exception e) {
		  JOptionPane.showMessageDialog(null, "Nhập thất bại");
	  }
	
	  
  }
  
  private Workbook nhapWorkbook(String fileName,FileInputStream fileIn) {
	  Workbook workbook = null;
	  try {
		  if(fileName.endsWith("xls")) {
			  workbook = new HSSFWorkbook(fileIn);
		  }else if (fileName.endsWith("xlsx")) {
			  workbook = new XSSFWorkbook(fileIn);
		  }
		  return workbook;
	  }catch(Exception e){
		  return null;
	  }
  }
  
}
