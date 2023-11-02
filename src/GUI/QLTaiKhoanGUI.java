package GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.bouncycastle.oer.its.PublicVerificationKey;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import BUS.TaiKhoanBUS;
import DAO.KhachHangDAO;
import DAO.TaiKhoanDAO;
import DTO.KhachHangDTO;
import DTO.TaiKhoanDTO;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class QLTaiKhoanGUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField_matk;
	private JTextField textField_tentk;
	private JTextField textField_pass;
	private JTextField textField_ten;
	int selectrow;
	private JTable table;
	Object [][]data;
	String col[]= {"Mã tài khoản","Tài khoản","Mật khẩu","Quyền"};
	ArrayList<TaiKhoanDTO> list;
	DefaultTableModel model;
	private JRadioButton rdbtn_admin;
	private JRadioButton rdbtn_kh;
	private JButton btn_add;
	private JButton btn_update;
	private JButton btn_del;
	private JButton btn_reset;
	private JButton btn_back;
	private JButton btn_search;
	private JComboBox comboBox_loai;
	private KhachHangDTO khachHangDTO;
	private ButtonGroup btn_group;
	private TaiKhoanBUS tkBUS=new TaiKhoanBUS();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLTaiKhoanGUI frame = new QLTaiKhoanGUI();
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
	public QLTaiKhoanGUI() {
		setTitle("Quản lý tài khoản");
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//TABLE
		//import from database
		
		list=TaiKhoanDAO.getInstance().selectAll();
		data=new Object[list.size()][col.length+1];
		for (int i=0;i<list.size();i++) {
			data[i][0]=(list.get(i).getId()).trim();
			data[i][1]=(list.get(i).getUsername()).trim();
			data[i][2]=(list.get(i).getPassword()).trim();
			data[i][3]=(list.get(i).getLoaiTK()).trim();
			data[i][4]=(list.get(i).getTrangThai());
		}
		model=new DefaultTableModel(data,col);
		table=new JTable(model);
		JTableHeader tableHeader=table.getTableHeader();
		tableHeader.setFont(new Font("Arial",Font.BOLD,14));
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				selectrow=table.getSelectedRow();
				String tk=model.getValueAt(selectrow, 0).toString();
				textField_matk.setText(model.getValueAt(selectrow, 0).toString());
				textField_tentk.setText(model.getValueAt(selectrow, 1).toString());
				textField_pass.setText(model.getValueAt(selectrow, 2).toString());
				if (model.getValueAt(selectrow, 3).equals(rdbtn_admin.getText()))
					btn_group.setSelected(rdbtn_admin.getModel(), true);
				else
					btn_group.setSelected(rdbtn_kh.getModel(), true);
				System.out.println(tk);
			}
		});
		
		JTableHeader jTableHeader=table.getTableHeader();
		Font font_tableheader=new Font("Tahoma",Font.BOLD,14);
		jTableHeader.setFont(font_tableheader);
		
		table.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 41, 792, 159);
		
		JPanel panel_table = new JPanel();
		panel_table.setBounds(0, 0, 786, 200);
		panel_table.setLayout(null);
		panel_table.add(scrollPane);
		contentPane.add(panel_table);
		
		JLabel label_header = new JLabel("QUẢN LÝ TÀI KHOẢN");
		label_header.setForeground(Color.RED);
		label_header.setHorizontalAlignment(SwingConstants.CENTER);
		label_header.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_header.setBounds(111, 5, 564, 30);
		panel_table.add(label_header);
		
		JPanel panel_input = new JPanel();
		panel_input.setBounds(0, 191, 786, 200);
		contentPane.add(panel_input);
		panel_input.setLayout(null);
		
		JLabel label_matk = new JLabel("Mã tài khoản:");
		label_matk.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_matk.setHorizontalAlignment(SwingConstants.CENTER);
		label_matk.setBounds(20, 37, 109, 20);
		panel_input.add(label_matk);
		
		JLabel label_tentk = new JLabel("Tài khoản:");
		label_tentk.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_tentk.setHorizontalAlignment(SwingConstants.CENTER);
		label_tentk.setBounds(20, 89, 90, 20);
		panel_input.add(label_tentk);
		
		JLabel label_pw = new JLabel("Mật khẩu:");
		label_pw.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_pw.setHorizontalAlignment(SwingConstants.CENTER);
		label_pw.setBounds(20, 139, 90, 20);
		panel_input.add(label_pw);
		
		rdbtn_admin = new JRadioButton("Admin");
		rdbtn_admin.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtn_admin.setBounds(544, 75, 109, 34);
		panel_input.add(rdbtn_admin);
		
		rdbtn_kh = new JRadioButton("Khách Hàng");
		rdbtn_kh.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtn_kh.setBounds(544, 132, 130, 34);
		panel_input.add(rdbtn_kh);
		
		btn_group=new ButtonGroup();
		btn_group.add(rdbtn_admin);
		btn_group.add(rdbtn_kh);
		
		
		textField_matk = new JTextField();
		textField_matk.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_matk.setBounds(139, 31, 150, 34);
		panel_input.add(textField_matk);
		textField_matk.setColumns(10);
		
		textField_tentk = new JTextField();
		textField_tentk.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_tentk.setBounds(139, 83, 250, 34);
		panel_input.add(textField_tentk);
		textField_tentk.setColumns(10);
		
		textField_pass = new JTextField();
		textField_pass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_pass.setBounds(139, 133, 250, 34);
		panel_input.add(textField_pass);
		textField_pass.setColumns(10);
		
		JPanel panel_btn = new JPanel();
		panel_btn.setBounds(0, 401, 786, 45);
		contentPane.add(panel_btn);
		panel_btn.setLayout(null);
		
		btn_add = new JButton("Thêm");
		btn_add.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_add.setBounds(52, 3, 117, 33);
		btn_add.addActionListener(this);
		panel_btn.add(btn_add);
		ImageIcon addIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLTaiKhoanGUI.class.getResource("/icons/add_TK.png")));
		btn_add.setIcon(addIcon);
		
		btn_update = new JButton("Sửa");
		btn_update.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_update.setBounds(268, 3, 100, 33);
		btn_update.addActionListener(this);
		panel_btn.add(btn_update);
		ImageIcon eidtIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLTaiKhoanGUI.class.getResource("/icons/edit_TK.png")));
		btn_update.setIcon(eidtIcon);
		ImageIcon exitIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLTaiKhoanGUI.class.getResource("/icons/exit_TK.png")));
		
		btn_del = new JButton("Xóa");
		btn_del.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_del.addActionListener(this);
		btn_del.setBounds(464, 5, 100, 33);
		panel_btn.add(btn_del);
		ImageIcon deleteIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLTaiKhoanGUI.class.getResource("/icons/delete_TK.png")));
		btn_del.setIcon(deleteIcon);
		
		panel_table.setBackground(Color.LIGHT_GRAY);
		panel_input.setBackground(Color.lightGray);
		
		JLabel label_quyen = new JLabel("Chọn quyền:");
		label_quyen.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_quyen.setBounds(427, 25, 116, 44);
		panel_input.add(label_quyen);
		panel_btn.setBackground(Color.lightGray);
		
		btn_reset = new JButton("Reset");
		btn_reset.setIcon(new ImageIcon(QLTaiKhoanGUI.class.getResource("/icons/reset_TK.png")));
		btn_reset.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_reset.addActionListener(this);
		btn_reset.setBounds(635, 5, 117, 34);
		panel_btn.add(btn_reset);
		
		JPanel panel_search = new JPanel();
		panel_search.setBackground(Color.LIGHT_GRAY);
		panel_search.setBounds(0, 456, 786, 100);
		contentPane.add(panel_search);
		panel_search.setLayout(null);
		
		btn_back = new JButton("Thoát");
		btn_back.setBounds(642, 28, 119, 33);
		panel_search.add(btn_back);
		btn_back.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==btn_back) {
					setVisible(false);
					new MainAdminGUI();
				}
			}
		});
		btn_back.setIcon(exitIcon);
		
		JLabel label_search = new JLabel("Tìm kiếm:");
		label_search.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_search.setBounds(10, 28, 128, 33);
		panel_search.add(label_search);
		
		JLabel label_loai = new JLabel("Loại:");
		label_loai.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_loai.setBounds(131, 12, 64, 33);
		panel_search.add(label_loai);
		
		comboBox_loai = new JComboBox();
		comboBox_loai.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox_loai.setModel(new DefaultComboBoxModel(new String[] {"-----", "Mã tài khoản", "Tài khoản", "Mật khẩu"}));
		comboBox_loai.setBounds(205, 11, 150, 34);
		panel_search.add(comboBox_loai);
		
		JLabel label_ten = new JLabel("Tìm:");
		label_ten.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_ten.setBounds(131, 55, 64, 32);
		panel_search.add(label_ten);
		
		textField_ten = new JTextField();
		textField_ten.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_ten.setBounds(205, 55, 185, 34);
		panel_search.add(textField_ten);
		textField_ten.setColumns(10);
		
		btn_search = new JButton("Tìm kiếm");
		btn_search.setIcon(new ImageIcon(QLTaiKhoanGUI.class.getResource("/icons/search_TK.png")));
		btn_search.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_search.setBounds(482, 28, 138, 33);
		btn_search.addActionListener(this);
		panel_search.add(btn_search);
		
		setVisible(true);
	}
	
	//check condition
	public int checkEmpty() {
		int flag=1;
		if (textField_matk.getText().isEmpty() || textField_tentk.getText().isEmpty() || textField_pass.getText().isEmpty() 
				&& (!rdbtn_admin.isSelected() || !rdbtn_kh.isSelected())) {
			JOptionPane.showMessageDialog(null, "Chưa nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
			flag=0;
		}
		return flag;
		
	}
	public int checkDuplicateAdd() {
		int flag=1;
		for (int i=0;i<list.size();i++) 
			if (textField_matk.getText().equals(list.get(i).getId().trim())) {
				JOptionPane.showMessageDialog(null, "Tài khoản cần thêm bị trùng mã tài khoản", "Lỗi", JOptionPane.ERROR_MESSAGE);
				flag=0;
			}
		return flag;
	}
	
	public int checkDuplicateEdit() {
		int flag=1;
		String matk=list.get(selectrow).getId().trim();
		if (textField_matk.getText().equals(matk)==false)
			for (int i=0;i<list.size();i++) {
				if (textField_matk.getText().equals(list.get(i).getId().trim())) {
					JOptionPane.showMessageDialog(null, "Mã tài khoản cần sửa bị trùng", "Lỗi", JOptionPane.ERROR_MESSAGE);
					flag=0;
				}
			}
		return flag;
	}
	public int checkDelete() {
		int flag=1;
		if (textField_matk.getText().isEmpty() || textField_tentk.getText().isEmpty() || textField_pass.getText().isEmpty() &&
				(!rdbtn_admin.isSelected() || !rdbtn_kh.isSelected())) {
			JOptionPane.showMessageDialog(null, "Chưa chọn dữ liệu cần xoá", "Lỗi", JOptionPane.ERROR_MESSAGE);
			flag=0;
		}
		return flag;
	}
	String select="";

	@Override
	public void actionPerformed(ActionEvent e) {
		//Add event
		if (e.getSource()==btn_add) {
			if (checkEmpty()==1 && checkDuplicateAdd()==1) {
				if (rdbtn_admin.isSelected())
					select=rdbtn_admin.getText();
				else 
					select=rdbtn_kh.getText();
				TaiKhoanDTO tk=new TaiKhoanDTO(textField_matk.getText(), textField_tentk.getText(), textField_pass.getText(),select,1);
				
				tkBUS.addTK(tk);
				list=TaiKhoanDAO.getInstance().selectAll();
				Object row[]= {textField_matk.getText(),textField_tentk.getText(),textField_pass.getText(),select,1};
				model.addRow(row);
				for (int i=0;i<list.size();i++)
					list.get(i).toString();
				
			}
		}
		//edit event
		if (e.getSource()==btn_update) {
			if (checkEmpty()==1 && checkDuplicateEdit()==1) {
				if (rdbtn_admin.isSelected())
					select=rdbtn_admin.getText();
				else 
					select=rdbtn_kh.getText();
				TaiKhoanDTO tk=new TaiKhoanDTO(textField_matk.getText(), textField_tentk.getText(), textField_pass.getText(),select,1);
				list.set(selectrow, tk);
				tkBUS.editTK(tk);
				model.setValueAt(textField_matk.getText(), selectrow, 0);
				model.setValueAt(textField_tentk.getText(), selectrow, 1);
				model.setValueAt(textField_pass.getText(), selectrow, 2);
				model.setValueAt(select, selectrow, 3);
			}
		}
		//delete event
		if (e.getSource()==btn_del) {
			if (checkDelete()==1) {
				if (rdbtn_admin.isSelected())
					select=rdbtn_admin.getText();
				else 
					select=rdbtn_kh.getText();
				String maTK=textField_matk.getText().trim();
				TaiKhoanDTO tk=new TaiKhoanDTO(textField_matk.getText(), textField_tentk.getText(), textField_pass.getText(),select,1);
				int index=0;
				for (int i=0;i<list.size();i++)
					if (list.get(i).getId().trim().equals(maTK))
						index=i;
				list.remove(index);
				for (int i=0;i<list.size();i++)
					list.get(i).toString();
				tkBUS.deleteTK(tk);
				model.removeRow(index);
				
			}
		}
		
		//reset event
		if (e.getSource()==btn_reset) {
			textField_matk.setText("");
			textField_tentk.setText("");
			textField_pass.setText("");
			btn_group.clearSelection();
		}
		
		//search event 
		if (e.getSource()==btn_search) {
			String type_search=comboBox_loai.getSelectedItem().toString();
			String name=textField_ten.getText();
			if (type_search.equalsIgnoreCase("-----"))
				JOptionPane.showMessageDialog(null,"Bạn chưa chọn loại để tìm kiếm!!!");
			else {
				
				if(type_search.equalsIgnoreCase("Mã tài khoản")) 
				{
					tkBUS.hienthitk_matk(table, name);
				}
			if(type_search.equalsIgnoreCase("Tài khoản")) 
				{
					tkBUS.hienthitk_tentk(table, name);
				}
			if(type_search.equalsIgnoreCase("Mật khẩu")) 
				{
					tkBUS.hienthitk_mk(table, name);
				}
			}
		}
		
		
	}
}
