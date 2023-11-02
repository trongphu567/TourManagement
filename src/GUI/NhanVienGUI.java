package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.TableRowSorter;

import BUS.MainKhachHangBUS;
import BUS.NhanVienBUS;

import java.awt.event.MouseAdapter;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SpringLayout;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import javax.swing.border.LineBorder;

public class NhanVienGUI extends JFrame implements ActionListener, KeyListener {

	private JPanel contentPane;
	private JTextField idtf;
	private JTextField tentf;
	private JTextField nsinhtf;
	private JTable tb;
	private DefaultTableModel model;
	private JTextField dchitf;
	private JTextField dttf;
	private JTextField searchtf;
	private JButton addbt = new JButton(" Thêm");
	private JButton resbt = new JButton(" Reset");
	private JButton delbt = new JButton(" Xoá");
	private JButton editbt = new JButton(" Sửa");
	private JRadioButton femrd;
	private JRadioButton malerd;
	private TableRowSorter<DefaultTableModel> sort;
	public ArrayList<NhanVienDTO> listNV;
	private ButtonGroup g1=new ButtonGroup();
	private JComboBox cvu;
	private String col[]= {"ID","Họ tên","Giới tính","Ngày sinh","Địa chỉ","Sđt","Chức vụ"};
	private String chuc[]= {"Hướng dẫn viên","Kế toán","Quản trị viên"};
	private int rowIndex;
	public static String pidHienTai;
	public static String pidCu;
	private Object[][] data;
	private JPanel uppnl = new JPanel();

	public NhanVienGUI() {
		setTitle("Nhân viên");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//TABLE
		//import from database
		listNV = NhanVienBUS.getInstance().importToTable(listNV);
		data=new Object[listNV.size()][col.length];
		for(int i=0;i<listNV.size();i++) {
			data[i][0]=(listNV.get(i).getID()).trim();
			data[i][1]=(listNV.get(i).getHoten()).trim();
			data[i][2]=(listNV.get(i).getGioitinh()).trim();
			data[i][3]=(listNV.get(i).getNgaysinh()).trim();
			data[i][4]=(listNV.get(i).getDiachi()).trim();
			data[i][5]=(listNV.get(i).getSdt()).trim();
			data[i][6]=(listNV.get(i).getChucvu()).trim();
		}
		model = new DefaultTableModel(data,col);
		sort=new TableRowSorter<DefaultTableModel>(model);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel titlepnl = new JPanel();
		titlepnl.setBackground(Color.WHITE);
		contentPane.add(titlepnl);
		
		JLabel title = new JLabel("QUẢN LÝ NHÂN SỰ");
		title.setForeground(SystemColor.activeCaption);
		title.setFont(new Font("UTM Aptima", Font.BOLD, 25));
		title.setBackground(SystemColor.activeCaption);
		titlepnl.add(title);
		uppnl.setBackground(Color.WHITE);
		
		contentPane.add(uppnl);
		contentPane.add(uppnl);
		GridBagLayout gbl_uppnl = new GridBagLayout();
		gbl_uppnl.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_uppnl.rowHeights = new int[]{0, 0, 0};
		gbl_uppnl.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_uppnl.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		uppnl.setLayout(gbl_uppnl);
		String chuc[]= {"Hướng dẫn viên","Kế toán","Quản trị viên"};
		
		JPanel info = new JPanel();
		info.setBackground(SystemColor.window);
		GridBagConstraints gbc_info = new GridBagConstraints();
		gbc_info.gridwidth = 4;
		gbc_info.fill = GridBagConstraints.BOTH;
		gbc_info.insets = new Insets(0, 30, 0, 60);
		gbc_info.gridx = 0;
		gbc_info.gridy = 1;
		uppnl.add(info, gbc_info);
		info.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Th\u00F4ng tin nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(153, 180, 209)));
		GridBagLayout gbl_info = new GridBagLayout();
		gbl_info.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_info.rowHeights = new int[]{20, 11, 0, 0, 0, 0};
		gbl_info.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_info.rowWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		info.setLayout(gbl_info);
		
		JLabel htenlb = new JLabel("Họ tên");
		GridBagConstraints gbc_htenlb = new GridBagConstraints();
		gbc_htenlb.anchor = GridBagConstraints.WEST;
		gbc_htenlb.insets = new Insets(0, 0, 5, 5);
		gbc_htenlb.gridx = 1;
		gbc_htenlb.gridy = 0;
		info.add(htenlb, gbc_htenlb);
		htenlb.setBackground(SystemColor.inactiveCaption);
		htenlb.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		//TENTF
		tentf = new JTextField();
		tentf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tentf.setForeground(new Color(0, 0, 0));
		GridBagConstraints gbc_tentf = new GridBagConstraints();
		gbc_tentf.fill = GridBagConstraints.HORIZONTAL;
		gbc_tentf.gridwidth = 4;
		gbc_tentf.insets = new Insets(0, 0, 5, 25);
		gbc_tentf.gridx = 2;
		gbc_tentf.gridy = 0;
		info.add(tentf, gbc_tentf);
		tentf.setColumns(10);
		tentf.addActionListener(this);
		
		JLabel idlb = new JLabel("Mã ID");
		GridBagConstraints gbc_idlb = new GridBagConstraints();
		gbc_idlb.fill = GridBagConstraints.HORIZONTAL;
		gbc_idlb.insets = new Insets(0, 0, 5, 5);
		gbc_idlb.gridx = 1;
		gbc_idlb.gridy = 1;
		info.add(idlb, gbc_idlb);
		idlb.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		idtf = new JTextField();
		idtf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_idtf = new GridBagConstraints();
		gbc_idtf.fill = GridBagConstraints.HORIZONTAL;
		gbc_idtf.insets = new Insets(0, 0, 5, -25);
		gbc_idtf.gridx = 2;
		gbc_idtf.gridy = 1;
		info.add(idtf, gbc_idtf);
		idtf.setColumns(8);
		
		JLabel lblNewLabel_1_2_1_1_2 = new JLabel("Sđt");
		GridBagConstraints gbc_lblNewLabel_1_2_1_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_2_1_1_2.insets = new Insets(0, 15, 5, -10);
		gbc_lblNewLabel_1_2_1_1_2.gridx = 3;
		gbc_lblNewLabel_1_2_1_1_2.gridy = 1;
		info.add(lblNewLabel_1_2_1_1_2, gbc_lblNewLabel_1_2_1_1_2);
		lblNewLabel_1_2_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		//SDT TF
		dttf = new JTextField();
		dttf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_dttf = new GridBagConstraints();
		gbc_dttf.fill = GridBagConstraints.HORIZONTAL;
		gbc_dttf.gridwidth = 2;
		gbc_dttf.insets = new Insets(0, -15, 5, 25);
		gbc_dttf.gridx = 4;
		gbc_dttf.gridy = 1;
		info.add(dttf, gbc_dttf);
		dttf.setColumns(10);
		
		JLabel nsinhlb = new JLabel("Ngày sinh");
		GridBagConstraints gbc_nsinhlb = new GridBagConstraints();
		gbc_nsinhlb.fill = GridBagConstraints.HORIZONTAL;
		gbc_nsinhlb.insets = new Insets(0, 0, 5, 10);
		gbc_nsinhlb.gridx = 1;
		gbc_nsinhlb.gridy = 2;
		info.add(nsinhlb, gbc_nsinhlb);
		nsinhlb.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
	
		nsinhtf = new JTextField();
		nsinhtf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_nsinhtf = new GridBagConstraints();
		gbc_nsinhtf.fill = GridBagConstraints.HORIZONTAL;
		gbc_nsinhtf.insets = new Insets(0, 0, 5, -25);
		gbc_nsinhtf.gridx = 2;
		gbc_nsinhtf.gridy = 2;
		info.add(nsinhtf, gbc_nsinhtf);
		nsinhtf.setColumns(8);
		
		//FEMRD
		JLabel lblNewLabel_1_2_1 = new JLabel("Giới");
		GridBagConstraints gbc_lblNewLabel_1_2_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_2_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_2_1.insets = new Insets(0, 45, 5, 15);
		gbc_lblNewLabel_1_2_1.gridx = 3;
		gbc_lblNewLabel_1_2_1.gridy = 2;
		info.add(lblNewLabel_1_2_1, gbc_lblNewLabel_1_2_1);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		femrd = new JRadioButton("NỮ");
		femrd.setBackground(SystemColor.inactiveCaptionBorder);
		GridBagConstraints gbc_femrd = new GridBagConstraints();
		gbc_femrd.insets = new Insets(0, -5, 5, 5);
		gbc_femrd.gridx = 4;
		gbc_femrd.gridy = 2;
		info.add(femrd, gbc_femrd);
		femrd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		g1.add(femrd);
		//MALERD
		malerd = new JRadioButton("NAM");
		GridBagConstraints gbc_malerd = new GridBagConstraints();
		gbc_malerd.insets = new Insets(0, -5, 5, 25);
		gbc_malerd.gridx = 5;
		gbc_malerd.gridy = 2;
		info.add(malerd, gbc_malerd);
		malerd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		g1.add(malerd); 
		
		JLabel dchilb = new JLabel("Địa chỉ");
		GridBagConstraints gbc_dchilb = new GridBagConstraints();
		gbc_dchilb.fill = GridBagConstraints.HORIZONTAL;
		gbc_dchilb.insets = new Insets(0, 0, 5, 5);
		gbc_dchilb.gridx = 1;
		gbc_dchilb.gridy = 3;
		info.add(dchilb, gbc_dchilb);
		dchilb.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		//DCHI TF
		dchitf = new JTextField();
		dchitf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_dchitf = new GridBagConstraints();
		gbc_dchitf.fill = GridBagConstraints.HORIZONTAL;
		gbc_dchitf.gridwidth = 4;
		gbc_dchitf.insets = new Insets(0, 0, 5, 25);
		gbc_dchitf.gridx = 2;
		gbc_dchitf.gridy = 3;
		info.add(dchitf, gbc_dchitf);
		dchitf.setColumns(10);
		
		JLabel cvulb = new JLabel("Chức vụ");
		GridBagConstraints gbc_cvulb = new GridBagConstraints();
		gbc_cvulb.insets = new Insets(0, 0, 2, 5);
		gbc_cvulb.fill = GridBagConstraints.HORIZONTAL;
		gbc_cvulb.gridx = 1;
		gbc_cvulb.gridy = 4;
		info.add(cvulb, gbc_cvulb);
		cvulb.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cvu = new JComboBox(chuc);
		cvu.setBackground(SystemColor.inactiveCaption);
		cvu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_cvu = new GridBagConstraints();
		gbc_cvu.gridwidth = 4;
		gbc_cvu.fill = GridBagConstraints.HORIZONTAL;
		gbc_cvu.insets = new Insets(0, 0, 5, 25);
		gbc_cvu.gridx = 2;
		gbc_cvu.gridy = 4;
		info.add(cvu, gbc_cvu);
		
		JPanel func = new JPanel();
		func.setBackground(Color.WHITE);
		GridBagConstraints gbc_func = new GridBagConstraints();
		gbc_func.fill = GridBagConstraints.HORIZONTAL;
		gbc_func.gridx = 4;
		gbc_func.gridy = 1;
		gbc_func.insets = new Insets(0, 0, 0, 25);
		uppnl.add(func, gbc_func);
		func.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Ch\u1EE9c n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(153, 180, 209)));
		GridBagLayout gbl_func = new GridBagLayout();
		gbl_func.columnWidths = new int[]{203, 0};
		gbl_func.rowHeights = new int[]{49, 49, 49, 49, 0};
		gbl_func.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_func.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		func.setLayout(gbl_func);
		
		//ADD BUTTON
		addbt.setBackground(SystemColor.inactiveCaption);
		addbt.setHorizontalAlignment(SwingConstants.LEFT);
		addbt.setIcon(new ImageIcon(NhanVienGUI.class.getResource("/icons/add_nv.png")));
		GridBagConstraints gbc_addbt = new GridBagConstraints();
		gbc_addbt.fill = GridBagConstraints.BOTH;
		gbc_addbt.insets = new Insets(0, 0, 5, 0);
		gbc_addbt.gridx = 0;
		gbc_addbt.gridy = 0;
		func.add(addbt, gbc_addbt);
		addbt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addbt.addActionListener(this);
		
		//EDIT BUTTON
		editbt.setBackground(SystemColor.inactiveCaption);
		editbt.setHorizontalAlignment(SwingConstants.LEFT);
		editbt.setIcon(new ImageIcon(NhanVienGUI.class.getResource("/icons/edit_nv.png")));
		GridBagConstraints gbc_editbt = new GridBagConstraints();
		gbc_editbt.fill = GridBagConstraints.BOTH;
		gbc_editbt.insets = new Insets(0, 0, 5, 0);
		gbc_editbt.gridx = 0;
		gbc_editbt.gridy = 1;
		func.add(editbt, gbc_editbt);
		editbt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		editbt.addActionListener(this);
		
		//DEL BUTTON
		delbt.setBackground(SystemColor.inactiveCaption);
		delbt.setHorizontalAlignment(SwingConstants.LEFT);
		delbt.setIcon(new ImageIcon(NhanVienGUI.class.getResource("/icons/del_nv.png")));
		GridBagConstraints gbc_delbt = new GridBagConstraints();
		gbc_delbt.fill = GridBagConstraints.BOTH;
		gbc_delbt.insets = new Insets(0, 0, 5, 0);
		gbc_delbt.gridx = 0;
		gbc_delbt.gridy = 2;
		func.add(delbt, gbc_delbt);
		delbt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		delbt.addActionListener(this);
		
		//RESET BUTTON
		resbt.setBackground(SystemColor.inactiveCaption);
		resbt.setHorizontalAlignment(SwingConstants.LEFT);
		resbt.setIcon(new ImageIcon(NhanVienGUI.class.getResource("/icons/reset_nv.png")));
		GridBagConstraints gbc_resbt = new GridBagConstraints();
		gbc_resbt.fill = GridBagConstraints.BOTH;
		gbc_resbt.gridx = 0;
		gbc_resbt.gridy = 3;
		func.add(resbt, gbc_resbt);
		resbt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		resbt.addActionListener(this);
		
		//COMBOBOX CHUCVU
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		//RETURN BUTTON
		JButton returnbt = new JButton("Trở về");
		returnbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==returnbt) {
					new MainAdminGUI();
					setVisible(false);
				}
					
			}
		});
		GridBagConstraints gbc_returnbt = new GridBagConstraints();
		gbc_returnbt.insets = new Insets(0, 0, 5, 5);
		gbc_returnbt.gridx = 18;
		gbc_returnbt.gridy = 0;
		panel.add(returnbt, gbc_returnbt);
		returnbt.setIcon(null);
		returnbt.setFont(new Font("UTM AbeatbyKai", Font.PLAIN, 12));
		returnbt.setBackground(SystemColor.inactiveCaption);
		
		JLabel lblNewLabel = new JLabel("Tìm kiếm");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 19;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		//SEARCH TF
		searchtf = new JTextField();
		GridBagConstraints gbc_searchtf = new GridBagConstraints();
		gbc_searchtf.insets = new Insets(0, 0, 5, 28);
		gbc_searchtf.gridwidth = 6;
		gbc_searchtf.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchtf.gridx = 20;
		gbc_searchtf.gridy = 0;
		panel.add(searchtf, gbc_searchtf);
		searchtf.setColumns(10);
		tb= new JTable(model);
		tb.setRowSorter(sort);
		searchtf.addKeyListener(this);
		
		//throw table on text field
		tb.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
				rowIndex=tb.getSelectedRow();
				pidCu=(String)tb.getValueAt(rowIndex,0);
				idtf.setText(tb.getValueAt(rowIndex, 0).toString());
				tentf.setText(tb.getValueAt(rowIndex, 1).toString());
				if((tb.getValueAt(rowIndex, 2).toString().contains("NAM"))==true)
					malerd.setSelected(true);
				else femrd.setSelected(true);
				nsinhtf.setText(tb.getValueAt(rowIndex, 3).toString());
				dchitf.setText(tb.getValueAt(rowIndex, 4).toString());
				dttf.setText(tb.getValueAt(rowIndex, 5).toString());
				String chuc=tb.getValueAt(rowIndex, 6).toString();
				if(chuc.contains("Hướng dẫn viên")==true)	
					cvu.setSelectedItem("Hướng dẫn viên");
				else if(chuc.contains("Kế toán")==true)
					cvu.setSelectedItem("Kế toán"); 
				else cvu.setSelectedItem("Quản trị viên");		
				}
		});
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 26;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		gbc_scrollPane.insets= new Insets(0, 30, 0, 28);
		panel.add(scrollPane, gbc_scrollPane);
		tb.setBackground(SystemColor.inactiveCaption);
		scrollPane.setViewportView(tb);
		
		setVisible(true);
	}
	
	//check conditions
		public int checkField() {
			int flag=1;
			if(tentf.getText().isEmpty()|| dchitf.getText().isEmpty()||
					idtf.getText().isEmpty()||dttf.getText().isEmpty() ||
					nsinhtf.getText().isEmpty() &&
					(!malerd.isSelected() || !femrd.isSelected()) ) {
				JOptionPane.showMessageDialog(null,"Trường không được rỗng!");
				flag=0;
			}
			else {
				int validDate=0;
				try {
					LocalDate.parse(nsinhtf.getText(),DateTimeFormatter.ofPattern("uuuu-MM-dd")
							.withResolverStyle(ResolverStyle.STRICT));
					validDate=1;
				}
				catch(DateTimeParseException e) {
					e.printStackTrace();
					validDate=0;
				}
				if(validDate==0) {
					JOptionPane.showMessageDialog(null,"Ngày sinh không hợp lệ!");
					flag=0;
				}
				if (dttf.getText().matches("^[0][0-9]{9}$")==false) {
					JOptionPane.showMessageDialog(null, "Số điện thoại phải có 10 số và bắt đầu bằng 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
					flag=0;
				
				}
			}
			return flag;
		}
		public int checkDupAdd() {
			int flag=1;
			for(int i=0;i<listNV.size();i++) {
				if(idtf.getText().equals(listNV.get(i).getID().trim())) {
					JOptionPane.showMessageDialog(null,"ID không thể trùng!");
					flag=0;}
			}
			return flag;
		}
		public int checkDupEdit() {
			int flag=1;
			String id_now=listNV.get(rowIndex).getID().trim();
			if(idtf.getText().equals(id_now)==false) 
				for(int i=0;i<listNV.size();i++) {
					if(idtf.getText().equals(listNV.get(i).getID().trim())) {
						JOptionPane.showMessageDialog(null,"ID không thể trùng!");
						flag=0;
					}
				}	
			return flag;
		}
		private String gender, chucvu;
		public void actionPerformed(ActionEvent e) {
			//ADD EVENT
			if(e.getSource()==addbt) {
				if(checkField()==1 && checkDupAdd()==1){
					if (malerd.isSelected())
						gender=malerd.getText();
					else gender=femrd.getText();
					chucvu=cvu.getSelectedItem().toString();
					NhanVienDTO nv=new NhanVienDTO(idtf.getText(),tentf.getText(),
							gender,nsinhtf.getText(),dchitf.getText(),dttf.getText(),chucvu);
					pidHienTai=idtf.getText();
					NhanVienBUS.getInstance().addNV(pidHienTai,nv);
					listNV= NhanVienBUS.getInstance().importToTable(listNV);
					Object[] nrow= {idtf.getText(),tentf.getText(),gender,nsinhtf.getText(),dchitf.getText(),dttf.getText(),chucvu};
					model.addRow(nrow);
				}
			}
			//EDIT EVENT
			if(e.getSource()==editbt) {
				if(checkField()==1 && checkDupEdit()==1) {
					if (malerd.isSelected())
						gender=malerd.getText();
					else gender=femrd.getText();
					chucvu=cvu.getSelectedItem().toString();
					NhanVienDTO nv=new NhanVienDTO(idtf.getText(),tentf.getText(),
							gender,nsinhtf.getText(),dchitf.getText(),dttf.getText(),chucvu);
					pidHienTai = idtf.getText().trim();
					NhanVienBUS.getInstance().updateNV(pidCu,pidHienTai,nv);
					listNV.set(rowIndex, nv);
					model.setValueAt(idtf.getText(),rowIndex,0);
					model.setValueAt(tentf.getText(),rowIndex,1);
					model.setValueAt(gender,rowIndex,2);
					model.setValueAt(nsinhtf.getText(),rowIndex,3);
					model.setValueAt(dchitf.getText(),rowIndex,4);
					model.setValueAt(dttf.getText(),rowIndex,5);
					model.setValueAt(chucvu,rowIndex,6);
				}
			}
			//DELETE EVENT
			if(e.getSource()==delbt) {
				//--CHECK CONDITION HERE--
				if (malerd.isSelected())
					gender=malerd.getText();
				else gender=femrd.getText();
				String id_now=idtf.getText().trim();
				chucvu=cvu.getSelectedItem().toString();
				NhanVienDTO nv=new NhanVienDTO(id_now,tentf.getText(),
						gender,nsinhtf.getText(),dchitf.getText(),dttf.getText(),chucvu);
				int index=0;
				for(int i=0;i<listNV.size();i++) 
					if(listNV.get(i).getID().trim().equals(id_now)==true)
						index=i;
				listNV.remove(index);
				NhanVienBUS.getInstance().deleteNV(nv);
				model.removeRow(rowIndex);
				}
			//RESET EVENT
			if(e.getSource()==resbt) {
				idtf.setText("");
				tentf.setText("");
				dttf.setText("");
				dchitf.setText("");
				nsinhtf.setText("");
				femrd.setSelected(true);
				cvu.setSelectedItem("Hướng dẫn viên");
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			//SEARCH EVENT
			if(e.getSource()==searchtf) {
				String keyword=searchtf.getText();
				sort.setRowFilter(RowFilter.regexFilter(keyword));
			}
		}
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NhanVienGUI frame = new NhanVienGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
