package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DatTourGUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField tentf;
	private JTextField nsinhtf;
	private JTextField dchitf;
	private JTextField dttf;
	private JTextField mailtf;
	private JTextField costtf;
	private JRadioButton namrd;
	private JRadioButton nurd;
	private ButtonGroup gioigr;
	private JComboBox paycb;
	private JComboBox peoplecb;
	private JButton confirmbt;
	private JButton cancelbt;
	public static int thanhTien;
	public static ArrayList<Integer> tongTourRieng = new ArrayList<>() ;
	public static int status=0;
	private int numtour=0;

	private String hoten;
	private String nsinh;
	private String dchi;
	private String sdt;
	private String mail;
	private String gioi;
	private int songuoi;
	private String thanhtoan;
	private int thanhtien;
	public String getHoten() {return hoten;}
	public void setHoten(String hoten) {this.hoten = hoten;}
	public String getNsinh() {return nsinh;}
	public void setNsinh(String nsinh) {this.nsinh = nsinh;}
	public String getDchi() {return dchi;}
	public void setDchi(String dchi) {this.dchi = dchi;}
	public String getSdt() {return sdt;}
	public void setSdt(String sdt) {this.sdt = sdt;}
	public String getMail() {return mail;}
	public void setMail(String mail) {this.mail = mail;}
	public String getGioi() {return gioi;}
	public void setGioi(String gioi) {this.gioi = gioi;}
	public int getSonguoi() {return songuoi;}
	public void setSonguoi(int songuoi) {this.songuoi = songuoi;}
	public String getThanhtoan() {return thanhtoan;}
	public void setThanhtoan(String thanhtoan) {this.thanhtoan = thanhtoan;}
	public int getThanhtien() {return thanhtien;}
	public void setThanhtien(int thanhtien) {this.thanhtien = thanhtien;}
	
	public DatTourGUI() {
		this.setTitle("Đặt Tour");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 885, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("ĐĂNG KÍ TOUR DU LỊCH");
		title.setAlignmentX(contentPane.CENTER_ALIGNMENT);
		title.setHorizontalAlignment(SwingConstants.LEFT);
		title.setForeground(Color.BLUE);
		title.setFont(new Font("Times New Roman", Font.BOLD, 25));
		contentPane.add(title);
		
		JPanel info1pn = new JPanel();
		contentPane.add(info1pn);
		GridBagLayout gbl_info1pn = new GridBagLayout();
		gbl_info1pn.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_info1pn.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_info1pn.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_info1pn.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		info1pn.setLayout(gbl_info1pn);
		
		JLabel infolb1 = new JLabel("1. THÔNG TIN CÁ NHÂN");
		infolb1.setForeground(Color.RED);
		infolb1.setFont(new Font("Times New Roman", Font.BOLD, 22));
		GridBagConstraints gbc_infolb1 = new GridBagConstraints();
		gbc_infolb1.gridwidth = 6;
		gbc_infolb1.fill = GridBagConstraints.BOTH;
		gbc_infolb1.insets = new Insets(0, 20, 5, 5);
		gbc_infolb1.gridx = 0;
		gbc_infolb1.gridy = 0;
		info1pn.add(infolb1, gbc_infolb1);
		
		JLabel tenlb = new JLabel("HỌ TÊN:");
		tenlb.setHorizontalAlignment(SwingConstants.CENTER);
		tenlb.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_tenlb = new GridBagConstraints();
		gbc_tenlb.insets = new Insets(0, 20, 5, 5);
		gbc_tenlb.anchor = GridBagConstraints.WEST;
		gbc_tenlb.gridx = 0;
		gbc_tenlb.gridy = 1;
		info1pn.add(tenlb, gbc_tenlb);
		
		//HOTEN TEXTFIELD
		tentf = new JTextField();
		GridBagConstraints gbc_tentf = new GridBagConstraints();
		gbc_tentf.insets = new Insets(0, 0, 5, 5);
		gbc_tentf.fill = GridBagConstraints.HORIZONTAL;
		gbc_tentf.gridx = 1;
		gbc_tentf.gridy = 1;
		info1pn.add(tentf, gbc_tentf);
		tentf.setColumns(10);
		
		JLabel gioilb = new JLabel("GIỚI TÍNH:");
		gioilb.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_gioilb = new GridBagConstraints();
		gbc_gioilb.anchor = GridBagConstraints.WEST;
		gbc_gioilb.insets = new Insets(0, 5, 5, 5);
		gbc_gioilb.gridx = 3;
		gbc_gioilb.gridy = 1;
		info1pn.add(gioilb, gbc_gioilb);
		
		//GIOI RADIO BUTTON
		gioigr=new ButtonGroup();
		namrd = new JRadioButton("NAM");
		namrd.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_namrd = new GridBagConstraints();
		gbc_namrd.anchor = GridBagConstraints.WEST;
		gbc_namrd.insets = new Insets(0, 0, 5, 5);
		gbc_namrd.gridx = 4;
		gbc_namrd.gridy = 1;
		info1pn.add(namrd, gbc_namrd);
		gioigr.add(namrd);
		
		nurd = new JRadioButton("NỮ");
		nurd.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_nurd = new GridBagConstraints();
		gbc_nurd.anchor = GridBagConstraints.WEST;
		gbc_nurd.insets = new Insets(0, 0, 5, 0);
		gbc_nurd.gridx = 5;
		gbc_nurd.gridy = 1;
		info1pn.add(nurd, gbc_nurd);
		gioigr.add(nurd);
		
		JLabel nsinhlb = new JLabel("NGÀY SINH:");
		nsinhlb.setHorizontalAlignment(SwingConstants.CENTER);
		nsinhlb.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_nsinhlb = new GridBagConstraints();
		gbc_nsinhlb.anchor = GridBagConstraints.WEST;
		gbc_nsinhlb.insets = new Insets(0, 20, 5, 5);
		gbc_nsinhlb.gridx = 0;
		gbc_nsinhlb.gridy = 2;
		info1pn.add(nsinhlb, gbc_nsinhlb);
		
		nsinhtf = new JTextField();
		GridBagConstraints gbc_nsinhtf = new GridBagConstraints();
		gbc_nsinhtf.insets = new Insets(0, 20, 5, 5);
		gbc_nsinhtf.fill = GridBagConstraints.HORIZONTAL;
		gbc_nsinhtf.gridx = 1;
		gbc_nsinhtf.gridy = 2;
		info1pn.add(nsinhtf, gbc_nsinhtf);
		nsinhtf.setColumns(10);
		
		JLabel dtlb = new JLabel("SĐT:");
		dtlb.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_dtlb = new GridBagConstraints();
		gbc_dtlb.anchor = GridBagConstraints.WEST;
		gbc_dtlb.insets = new Insets(0, 0, 5, 5);
		gbc_dtlb.gridx = 3;
		gbc_dtlb.gridy = 2;
		info1pn.add(dtlb, gbc_dtlb);
		
		dttf = new JTextField();
		GridBagConstraints gbc_dttf = new GridBagConstraints();
		gbc_dttf.gridwidth = 2;
		gbc_dttf.insets = new Insets(0, 0, 5, 20);
		gbc_dttf.fill = GridBagConstraints.HORIZONTAL;
		gbc_dttf.gridx = 4;
		gbc_dttf.gridy = 2;
		info1pn.add(dttf, gbc_dttf);
		dttf.setColumns(10);
		
		JLabel dchilb = new JLabel("ĐỊA CHỈ:");
		dchilb.setHorizontalAlignment(SwingConstants.CENTER);
		dchilb.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_dchilb = new GridBagConstraints();
		gbc_dchilb.insets = new Insets(0, 20, 0, 5);
		gbc_dchilb.anchor = GridBagConstraints.WEST;
		gbc_dchilb.gridx = 0;
		gbc_dchilb.gridy = 3;
		info1pn.add(dchilb, gbc_dchilb);
		
		dchitf = new JTextField();
		GridBagConstraints gbc_dchitf = new GridBagConstraints();
		gbc_dchitf.insets = new Insets(0, 0, 0, 5);
		gbc_dchitf.fill = GridBagConstraints.HORIZONTAL;
		gbc_dchitf.gridx = 1;
		gbc_dchitf.gridy = 3;
		info1pn.add(dchitf, gbc_dchitf);
		dchitf.setColumns(10);
		
		JLabel maillb = new JLabel("EMAIL:");
		maillb.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_maillb = new GridBagConstraints();
		gbc_maillb.anchor = GridBagConstraints.WEST;
		gbc_maillb.insets = new Insets(0, 0, 0, 5);
		gbc_maillb.gridx = 3;
		gbc_maillb.gridy = 3;
		info1pn.add(maillb, gbc_maillb);
		
		mailtf = new JTextField();
		GridBagConstraints gbc_mailtf = new GridBagConstraints();
		gbc_mailtf.gridwidth = 2;
		gbc_mailtf.insets = new Insets(0, 0, 0, 20);
		gbc_mailtf.fill = GridBagConstraints.HORIZONTAL;
		gbc_mailtf.gridx = 4;
		gbc_mailtf.gridy = 3;
		info1pn.add(mailtf, gbc_mailtf);
		mailtf.setColumns(10);
		
		JPanel info2pn = new JPanel();
		contentPane.add(info2pn);
		GridBagLayout gbl_info2pn = new GridBagLayout();
		gbl_info2pn.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_info2pn.rowHeights = new int[]{20, 38, 0, 0};
		gbl_info2pn.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_info2pn.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		info2pn.setLayout(gbl_info2pn);
		
		JLabel infolb2 = new JLabel("2. THÔNG TIN TOUR ĐẶT");
		infolb2.setForeground(Color.RED);
		infolb2.setFont(new Font("Times New Roman", Font.BOLD, 22));
		GridBagConstraints gbc_infolb2 = new GridBagConstraints();
		gbc_infolb2.fill = GridBagConstraints.VERTICAL;
		gbc_infolb2.gridwidth = 6;
		gbc_infolb2.insets = new Insets(0, 20, 5, 0);
		gbc_infolb2.anchor = GridBagConstraints.WEST;
		gbc_infolb2.gridx = 0;
		gbc_infolb2.gridy = 0;
		info2pn.add(infolb2, gbc_infolb2);
		
		JLabel peoplelb = new JLabel("SỐ LƯỢNG NGƯỜI:");
		peoplelb.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_peoplelb = new GridBagConstraints();
		gbc_peoplelb.insets = new Insets(0, 20, 5, 5);
		gbc_peoplelb.anchor = GridBagConstraints.WEST;
		gbc_peoplelb.gridx = 0;
		gbc_peoplelb.gridy = 1;
		info2pn.add(peoplelb, gbc_peoplelb);
		
		//SOLUONGNGUOI COMBOBOX
		Integer[] ppl = new Integer[30];
		int n=1;
		for(int i=0;i<30;i++) {
			ppl[i]=n;
			n++;
		}
		peoplecb = new JComboBox(ppl);
		GridBagConstraints gbc_peoplecb = new GridBagConstraints();
		gbc_peoplecb.insets = new Insets(0, 0, 5, 5);
		gbc_peoplecb.fill = GridBagConstraints.HORIZONTAL;
		gbc_peoplecb.gridx = 1;
		gbc_peoplecb.gridy = 1;
		info2pn.add(peoplecb, gbc_peoplecb);
		
		JLabel costlb = new JLabel("THÀNH TIỀN:");
		costlb.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_costlb = new GridBagConstraints();
		gbc_costlb.anchor = GridBagConstraints.EAST;
		gbc_costlb.insets = new Insets(0, 0, 5, 5);
		gbc_costlb.gridx = 3;
		gbc_costlb.gridy = 1;
		info2pn.add(costlb, gbc_costlb);
		
		//THANHTIEN TEXTFIELD
		costtf = new JTextField();
		costtf.setEditable(false);
		GridBagConstraints gbc_costtf = new GridBagConstraints();
		gbc_costtf.gridwidth = 2;
		gbc_costtf.insets = new Insets(0, 0, 5, 20);
		gbc_costtf.fill = GridBagConstraints.HORIZONTAL;
		gbc_costtf.gridx = 4;
		gbc_costtf.gridy = 1;
		info2pn.add(costtf, gbc_costtf);
		costtf.setColumns(10);
		
		//TINH THANH TIEN
		peoplecb.addActionListener (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ppl=(int) peoplecb.getSelectedItem();
				int tongrieng=ppl*thanhTien;
				costtf.setText(Integer.toString(tongrieng));
			}
		});
		
		JLabel paylb = new JLabel("PHƯƠNG THỨC THANH TOÁN:");
		paylb.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_paylb = new GridBagConstraints();
		gbc_paylb.insets = new Insets(0, 20, 0, 5);
		gbc_paylb.anchor = GridBagConstraints.EAST;
		gbc_paylb.gridx = 0;
		gbc_paylb.gridy = 2;
		info2pn.add(paylb, gbc_paylb);
		
		//THANHTOAN COMBOBOX
		String ttoan[]= {"THANH TOÁN TRỰC TIẾP","CHUYỂN KHOẢN NGÂN HÀNG","CHUYỂN KHOẢN MOMO"};
		paycb = new JComboBox(ttoan);
		GridBagConstraints gbc_paycb = new GridBagConstraints();
		gbc_paycb.gridwidth = 2;
		gbc_paycb.insets = new Insets(0, 0, 0, 5);
		gbc_paycb.fill = GridBagConstraints.HORIZONTAL;
		gbc_paycb.gridx = 1;
		gbc_paycb.gridy = 2;
		info2pn.add(paycb, gbc_paycb);
		
		//DATTOUR BUTTON
		confirmbt = new JButton("ĐẶT TOUR");
		confirmbt.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_confirmbt = new GridBagConstraints();
		gbc_confirmbt.insets = new Insets(0, 0, 0, 5);
		gbc_confirmbt.gridx = 3;
		gbc_confirmbt.gridy = 2;
		info2pn.add(confirmbt, gbc_confirmbt);
		confirmbt.addActionListener(this);
		
		//CANCEL BUTTON
		cancelbt = new JButton("CANCEL");
		cancelbt.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_cancelbt = new GridBagConstraints();
		gbc_cancelbt.insets = new Insets(0, 0, 0, 5);
		gbc_cancelbt.fill = GridBagConstraints.HORIZONTAL;
		gbc_cancelbt.gridx = 4;
		gbc_cancelbt.gridy = 2;
		info2pn.add(cancelbt, gbc_cancelbt);
		cancelbt.addActionListener(this);
	}
	public int checkField() {
		int flag=1;
		if(tentf.getText().isEmpty()|| dchitf.getText().isEmpty()||
			dttf.getText().isEmpty() || nsinhtf.getText().isEmpty() &&
				(!namrd.isSelected() || !nurd.isSelected()) ) {
			JOptionPane.showMessageDialog(null,"Trường không được rỗng!");
			flag=0;
		}
		else {
			int validDate=0;
			try {
				LocalDate.parse(nsinhtf.getText(),DateTimeFormatter.ofPattern("uuuu-MM-dd") //year-month of year-day of month	
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
		}
		return flag;
	}
	public void getInfo() {
		setHoten(tentf.getText());
		if(namrd.isSelected()==true) setGioi("Nam");
		else setGioi("Nu");
		setDchi(dchitf.getText());
		setNsinh(nsinhtf.getText());
		setSdt(dttf.getText());
		setMail(mailtf.getText());
		setSonguoi(Integer.parseInt(peoplecb.getSelectedItem().toString()));
		setThanhtoan(paycb.getSelectedItem().toString());
		setThanhtien(Integer.parseInt(costtf.getText().toString()));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//CONFIRM EVENT
		if(e.getSource()==confirmbt) {
			if(checkField()==1) {
				status=1;
				JOptionPane.showMessageDialog(null,"Đặt tour thành công");
				numtour++;
				tongTourRieng.add(Integer.parseInt(costtf.getText()));
				getInfo();
				dispose();
			}
		}
		//CANCEL EVENT
		if(e.getSource()==cancelbt) {
			status=0;
			dispose();
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatTourGUI frame = new DatTourGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

}
