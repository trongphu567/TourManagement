package GUI;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import BUS.KhachHangBUS;
import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import DTO.TourDTO;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.naming.ldap.SortControl;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;

public class QLKhachHangGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField_makh;
	private JTextField textField_tenkh;
	private JTextField textField_gt;
	private JTextField textField_diachi;
	private JTextField textField_sdt;
	private JTable table;
	private JTextField textField_ten;
	Object [][]data;
	String col[]= {"Mã khách hàng","Tên khách hàng","Giới tính","Địa chỉ","Số ĐT"};
	ArrayList<KhachHangDTO> list;
	DefaultTableModel model;
	int selectrow;
	private JButton btn_them;
	private JButton btn_xoa;
	private JButton btn_sua;
	private JButton btn_reset;
	private KhachHangDTO khachHangDTO;
	private JButton btn_search;
	private JComboBox comboBox_loaitk;
	private KhachHangBUS khBUS=new KhachHangBUS();
	final DefaultTableModel tb = new DefaultTableModel(col,0);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLKhachHangGUI frame = new QLKhachHangGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QLKhachHangGUI() {
		setTitle("Quản Lý Khách Hàng");
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		mnMenu.setFont(new Font("Segoe UI", Font.BOLD, 13));
		menuBar.add(mnMenu);
		
		JMenuItem export_excel = new JMenuItem("In ra excel");
		export_excel.setIcon(new ImageIcon(QLKhachHangGUI.class.getResource("/icons/excel.png")));
		export_excel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		export_excel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				KhachHangBUS KhachHangBUS=new KhachHangBUS();
				KhachHangBUS.xuatkh_excel();
			}
		});
		
		
		mnMenu.add(export_excel);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(150, 255, 255));

		setContentPane(contentPane);
		
		KhachHangBUS KhachHangBUS=new KhachHangBUS();
		
		JLabel label_khachhang = new JLabel("Quản Lý Khách Hàng");
		label_khachhang.setBounds(240, 10, 300, 34);
		label_khachhang.setBackground(Color.WHITE);
		label_khachhang.setIcon(new ImageIcon(QLKhachHangGUI.class.getResource("/icons/customer.png")));
		label_khachhang.setForeground(Color.RED);
		label_khachhang.setHorizontalAlignment(SwingConstants.CENTER);
		label_khachhang.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JPanel panel_input = new JPanel();
		panel_input.setBounds(10, 50, 766, 343);
		panel_input.setBackground(Color.CYAN);
		panel_input.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_input.setLayout(null);
		
		JLabel label_makh = new JLabel("Mã khách hàng:");
		label_makh.setBounds(10, 13, 190, 25);
		label_makh.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_input.add(label_makh);
		
		textField_makh = new JTextField();
		textField_makh.setBounds(159, 9, 190, 34);
		textField_makh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_input.add(textField_makh);
		textField_makh.setColumns(10);
		
		JLabel label_tenkh = new JLabel("Tên khách hàng:");
		label_tenkh.setBounds(383, 9, 134, 25);
		label_tenkh.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_input.add(label_tenkh);
		
		textField_tenkh = new JTextField();
		textField_tenkh.setBounds(552, 9, 190, 34);
		textField_tenkh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_tenkh.setColumns(10);
		panel_input.add(textField_tenkh);
		
		JLabel label_gt = new JLabel("Giới tính:");
		label_gt.setBounds(10, 76, 190, 25);
		label_gt.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_input.add(label_gt);
		
		textField_gt = new JTextField();
		textField_gt.setBounds(159, 72, 190, 34);
		textField_gt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_gt.setColumns(10);
		panel_input.add(textField_gt);
		
		JLabel label_diachi = new JLabel("Địa chỉ:");
		label_diachi.setBounds(383, 76, 128, 25);
		label_diachi.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_input.add(label_diachi);
		
		textField_diachi = new JTextField();
		textField_diachi.setBounds(552, 72, 190, 34);
		textField_diachi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_diachi.setColumns(10);
		panel_input.add(textField_diachi);
		
		JLabel label_sdt = new JLabel("SDT:");
		label_sdt.setBounds(10, 137, 116, 25);
		label_sdt.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_input.add(label_sdt);
		
		textField_sdt = new JTextField();
		textField_sdt.setBounds(159, 133, 190, 34);
		textField_sdt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_sdt.setColumns(10);
		panel_input.add(textField_sdt);
		
		btn_them = new JButton("Thêm");
		btn_them.setBounds(43, 189, 135, 34);
		
		btn_them.setIcon(new ImageIcon(QLKhachHangGUI.class.getResource("/icons/add.png")));
		btn_them.setBackground(Color.WHITE);
		btn_them.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_them.addActionListener(this);
		panel_input.add(btn_them);
		
		btn_sua = new JButton("Sửa");
		btn_sua.setBounds(229, 189, 135, 34);
		btn_sua.setIcon(new ImageIcon(QLKhachHangGUI.class.getResource("/icons/update.png")));
		btn_sua.setBackground(Color.WHITE);
		btn_sua.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_sua.addActionListener(this);
		panel_input.add(btn_sua);
		
		btn_xoa = new JButton("Xoá");
		btn_xoa.setBounds(423, 189, 135, 34);
		btn_xoa.setIcon(new ImageIcon(QLKhachHangGUI.class.getResource("/icons/delete.png")));
		btn_xoa.setBackground(Color.WHITE);
		btn_xoa.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_xoa.addActionListener(this);
		panel_input.add(btn_xoa);
		
		btn_reset = new JButton("Reset");
		btn_reset.setBounds(601, 189, 135, 34);
		btn_reset.setIcon(new ImageIcon(QLKhachHangGUI.class.getResource("/icons/reset.png")));
		btn_reset.setBackground(Color.WHITE);
		btn_reset.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_reset.addActionListener(this);
		panel_input.add(btn_reset);
		
		JLabel label_timKiem = new JLabel("Tìm kiếm:");
		label_timKiem.setBounds(10, 233, 128, 25);
		label_timKiem.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_input.add(label_timKiem);
		
		JLabel label_loai = new JLabel("Loại:");
		label_loai.setBounds(58, 268, 80, 25);
		label_loai.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_input.add(label_loai);
		
		comboBox_loaitk = new JComboBox();
		comboBox_loaitk.setBounds(159, 263, 190, 34);
		comboBox_loaitk.setModel(new DefaultComboBoxModel(new String[] {"-----", "Mã khách hàng", "Tên khách hàng", "Giới tính", "Địa chỉ"}));
		comboBox_loaitk.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_input.add(comboBox_loaitk);
		
		JLabel label_ten = new JLabel("Tên:");
		label_ten.setBounds(58, 311, 100, 25);
		label_ten.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_input.add(label_ten);
		
		textField_ten = new JTextField();
		textField_ten.setBounds(159, 307, 190, 34);
		textField_ten.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_input.add(textField_ten);
		textField_ten.setColumns(10);
		
		btn_search = new JButton("Tìm kiếm");
		btn_search.setBounds(397, 259, 150, 34);
		btn_search.setBackground(Color.ORANGE);
		btn_search.setIcon(new ImageIcon(QLKhachHangGUI.class.getResource("/icons/search.png")));
		btn_search.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_search.addActionListener(this);
		panel_input.add(btn_search);
		
		JButton btn_back = new JButton("Quay lại");
		btn_back.setBounds(573, 259, 150, 34);
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==btn_back) {
					setVisible(false);
					new MainAdminGUI();
				}
			}
		});
		btn_back.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_back.setIcon(new ImageIcon(QLKhachHangGUI.class.getResource("/icons/back.png")));
		
		panel_input.add(btn_back);
		
		//TABLE
		//import from database
		
		list=KhachHangDAO.getInstace().selectAll();
		data=new Object[list.size()][col.length+1];
		for(int i=0;i<list.size();i++) {
			data[i][0]=(list.get(i).getMaKH()).trim();
			data[i][1]=(list.get(i).getTenKH()).trim();
			data[i][2]=(list.get(i).getGioiTinh()).trim();
			data[i][3]=(list.get(i).getDiaChi()).trim();
			data[i][4]=(list.get(i).getSdt()).trim();
			data[i][5]=(list.get(i).getTrangThai());
		}
		model = new DefaultTableModel(data,col);
		table= new JTable(model);
		JTableHeader tableHeader=table.getTableHeader();
		tableHeader.setFont(new Font("Arial",Font.BOLD,14));
		
		TableColumnModel columnModel=table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(80);
		columnModel.getColumn(1).setPreferredWidth(150);
		columnModel.getColumn(2).setPreferredWidth(50);
		columnModel.getColumn(3).setPreferredWidth(160);
		columnModel.getColumn(4).setPreferredWidth(100);
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				selectrow=table.getSelectedRow();
				String kh=model.getValueAt(selectrow, 0).toString();
				textField_makh.setText(model.getValueAt(selectrow, 0).toString());
				textField_tenkh.setText(model.getValueAt(selectrow, 1).toString());
				textField_gt.setText(model.getValueAt(selectrow, 2).toString());
				textField_diachi.setText(model.getValueAt(selectrow, 3).toString());
				textField_sdt.setText(model.getValueAt(selectrow, 4).toString());
				System.out.println(kh);
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		table.setBounds(0, -16, 756, 230);
		table.setDefaultEditor(Object.class, null);
		JScrollPane jScrollPane=new JScrollPane(table);
		jScrollPane.setBounds(10, 399, 766, 148);
		contentPane.setLayout(null);
		contentPane.add(label_khachhang);
		contentPane.add(jScrollPane);
		contentPane.add(panel_input);
		
		setVisible(true);
		}
	//check condition
	public int checkEmpty() {
		int flag=1;
		if (textField_makh.getText().isEmpty() || textField_tenkh.getText().isEmpty() || textField_gt.getText().isEmpty() 
				 || textField_diachi.getText().isEmpty() || textField_sdt.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Chưa nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
			flag=0;
		}
		if (textField_sdt.getText().matches("^[0][0-9]{9}$")==false) {
			JOptionPane.showMessageDialog(null, "Số điện thoại phải có 10 số và bắt đầu bằng 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
			flag=0;
		
		}
		return flag;
	}
	public int checkDuplicateAdd() {
		int flag=1;
		for (int i=0;i<list.size();i++)
			if (textField_makh.getText().equals(list.get(i).getMaKH().trim())) {
				JOptionPane.showMessageDialog(null, "Khách hàng cần thêm bị trùng mã khách hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
				flag=0;
			}
		return flag;
	}
	
	public int checkDuplicateEdit() {
		int flag=1;
		String makh=list.get(selectrow).getMaKH().trim();
		if (textField_makh.getText().equals(makh)==false)
			for (int i=0;i<list.size();i++) {
				if (textField_makh.getText().equals(list.get(i).getMaKH().trim())) {
					JOptionPane.showMessageDialog(null, "Mã khách hàng cần sửa bị trùng", "Lỗi", JOptionPane.ERROR_MESSAGE);
					flag=0;
				}
			}
		return flag;
	}
	public int checkDelete() {
		int flag=1;
		if (textField_makh.getText().isEmpty() || textField_tenkh.getText().isEmpty() || textField_gt.getText().isEmpty() ||
				textField_diachi.getText().isEmpty() || textField_sdt.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Chưa chọn dữ liệu cần xoá", "Lỗi", JOptionPane.ERROR_MESSAGE);
			flag=0;
		}
		return flag;
	}
	
	public void actionPerformed(ActionEvent e) {
		//Add event
		if (e.getSource()==btn_them) {
			if (checkEmpty()==1 && checkDuplicateAdd()==1 ) {
				KhachHangDTO kh=new KhachHangDTO(textField_makh.getText(), textField_tenkh.getText(), textField_gt.getText(), textField_diachi.getText(), textField_sdt.getText(),1);
				khBUS.addKH(kh);
				list=KhachHangDAO.getInstace().selectAll();
				Object[] row= {textField_makh.getText(),textField_tenkh.getText(),textField_gt.getText(),textField_diachi.getText(),textField_sdt.getText(),1};
				model.addRow(row);
				for (int i=0;i<list.size();i++)
					list.get(i).toString();
			}
			
		}
		//Edit event
		if (e.getSource()==btn_sua) {
			if (checkEmpty()==1 && checkDuplicateEdit()==1) {
				KhachHangDTO kh=new KhachHangDTO(textField_makh.getText(), textField_tenkh.getText(), textField_gt.getText(), textField_diachi.getText(), textField_sdt.getText(), 1);
				list.set(selectrow, kh);
				khBUS.editKH(kh);
				model.setValueAt(textField_makh.getText(),selectrow,0);
				model.setValueAt(textField_tenkh.getText(), selectrow, 1);
				model.setValueAt(textField_gt.getText(), selectrow, 2);
				model.setValueAt(textField_diachi.getText(), selectrow, 3);
				model.setValueAt(textField_sdt.getText(), selectrow, 4);
				model.setValueAt(1, selectrow, 5);
			}
		}
		//Delete event
		if (e.getSource()==btn_xoa) {
			if (checkDelete()==1) {
				String maKH=textField_makh.getText().trim();
				KhachHangDTO kh=new KhachHangDTO(maKH,textField_tenkh.getText(),textField_gt.getText(),textField_diachi.getText(),textField_sdt.getText(),1);
				int index=0;
				for (int i=0;i<list.size();i++)
					if (list.get(i).getMaKH().trim().equals(maKH))
						index=i;
				list.remove(index);
				for (int i=0;i<list.size();i++)
					list.get(i).toString();
				khBUS.deleteKH(kh);
				model.removeRow(index);
			}
		}
		//reset event
		if (e.getSource()==btn_reset) {
			textField_makh.setText("");
			textField_tenkh.setText("");
			textField_gt.setText("");
			textField_diachi.setText("");
			textField_sdt.setText("");
		}
		//search event
		if (e.getSource()==btn_search) {
			
			String name_box_search=comboBox_loaitk.getSelectedItem().toString();
			String name_search=textField_ten.getText();
			KhachHangBUS KhachHangBUS=new KhachHangBUS();
			if(name_box_search.equalsIgnoreCase("-----"))
				JOptionPane.showMessageDialog(null,"Bạn chưa chọn loại để tìm kiếm!!!");
			else {
				if(name_box_search.equalsIgnoreCase("Mã khách hàng")) 
					{
						KhachHangBUS.hienthikh_makh(table, name_search);
					}
				if(name_box_search.equalsIgnoreCase("Tên khách hàng")) 
					{
						KhachHangBUS.hienthikh_tenkh(table, name_search);
					}
				if(name_box_search.equalsIgnoreCase("Giới tính")) 
					{
						KhachHangBUS.hienthikh_gioiTinh(table, name_search);
					}
				if(name_box_search.equalsIgnoreCase("Địa chỉ")) 
					{
						KhachHangBUS.hienthikh_diachi(table, name_search);
					}
			}
		}
	}
}
