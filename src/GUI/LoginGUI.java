package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.FontUIResource;

import DAO.TaiKhoanDAO;

import DTO.TaiKhoanDTO;

public class LoginGUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField_dangnhap;
	private JPasswordField textField_matkhau;
	private JButton btn_login;
	private JButton btn_signup;
	public static int isLogin;
	public static String usrnameLG;

	
	public LoginGUI() {
		setTitle("Login");
		
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_image = new JPanel();
		panel_image.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		JLabel label = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("icons/banner.png")));
		label.setLocation(0, 0);
		label.setSize(766, 186);
		panel_image.add(label);
		panel_image.setBounds(10, 10, 766, 186);
		contentPane.add(panel_image);
		panel_image.setLayout(null);
		
		JPanel panel_login = new JPanel();
		panel_login.setBackground(Color.WHITE);
		panel_login.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_login.setBounds(10, 220, 766, 233);
		contentPane.add(panel_login);
		panel_login.setLayout(null);
		
		JLabel label_tendangnhap = new JLabel("Tên đăng nhập");
		label_tendangnhap.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_tendangnhap.setBounds(167, 26, 166, 34);
		panel_login.add(label_tendangnhap);
		
		textField_dangnhap = new JTextField();
		textField_dangnhap.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_dangnhap.setBounds(343, 26, 228, 36);
		panel_login.add(textField_dangnhap);
		textField_dangnhap.setColumns(10);
		
		JLabel label_matkhau = new JLabel("Mật khẩu");
		label_matkhau.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_matkhau.setBounds(167, 89, 166, 34);
		panel_login.add(label_matkhau);
		
		textField_matkhau = new JPasswordField();
		textField_matkhau.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_matkhau.setBounds(343, 89, 228, 36);
		panel_login.add(textField_matkhau);
		
		btn_login = new JButton("Đăng nhập");
		btn_login.setIcon(new ImageIcon(LoginGUI.class.getResource("/icons/login.png")));
		btn_login.setBackground(new Color(63, 255, 255));
		btn_login.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_login.setBounds(167, 144, 182, 41);
		panel_login.add(btn_login);
		btn_login.addActionListener(this);
		
		btn_signup = new JButton("Đăng ký");
		btn_signup.setIcon(new ImageIcon(LoginGUI.class.getResource("/icons/signup.png")));
		btn_signup.setBackground(new Color(248, 253, 39));
		btn_signup.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_signup.setBounds(380, 144, 149, 41);
		panel_login.add(btn_signup);
		btn_signup.addActionListener(this);
		
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btn_login) {
			String username=textField_dangnhap.getText();
			String password=textField_matkhau.getText();
			TaiKhoanDTO user=kiemtra_dangnhap(username, password);
			
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
			          "Arial", Font.BOLD, 14)));
			
			if (username.equals("") && password.equals(""))
				JOptionPane.showMessageDialog(null,"Hãy nhập vào tên đăng nhập và mật khẩu","Lỗi", JOptionPane.ERROR_MESSAGE);
			else if (!username.equals("") && password.equals(""))
				JOptionPane.showMessageDialog(null, "Hãy nhập vào mật khẩu","Lỗi",JOptionPane.ERROR_MESSAGE);
			else if (username.equals("") && !password.equals(""))
				JOptionPane.showMessageDialog(null, "Hãy nhập vào tên đăng nhập","Lỗi",JOptionPane.ERROR_MESSAGE);
			else {
				if (user==null) {
					JOptionPane.showMessageDialog(null, "Tài khoản không tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				if (user.getLoaiTK().equalsIgnoreCase("Admin")) {
					JOptionPane.showMessageDialog(null, "Đăng nhập thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
					MainAdminGUI mainAdminGUI=new MainAdminGUI();
					mainAdminGUI.setVisible(true);
					
				}
				if (user.getLoaiTK().equalsIgnoreCase("Khách Hàng")) {
					isLogin=1;
					usrnameLG=textField_dangnhap.getText().trim();
					JOptionPane.showMessageDialog(null, "Đăng nhập thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);	
					setVisible(false);
					MainKhachHangGUI mainKhachHangGUI=new MainKhachHangGUI();
					mainKhachHangGUI.setVisible(true);
					
				}
			}
		}
		if (e.getSource()==btn_signup) {
			setVisible(false);
			SignUpGUI signUpGUI=new SignUpGUI();
			signUpGUI.setVisible(true);
			
		}
	}
	public TaiKhoanDTO kiemtra_dangnhap(String username,String password) {
		TaiKhoanDAO usDAO=new TaiKhoanDAO();
		ArrayList<TaiKhoanDTO> list=usDAO.selectAll();
		
		for(int i=0;i<list.size();i++)
		{
			if(username.equalsIgnoreCase(list.get(i).getUsername())&&password.equalsIgnoreCase(list.get(i).getPassword()))
				return list.get(i);
		}
		return null;
	}
	public boolean themtk(TaiKhoanDTO tk) {
		TaiKhoanDAO usDAO=new TaiKhoanDAO();
		ArrayList<TaiKhoanDTO> list=usDAO.selectAll();
		for (int i = 0; i < list.size(); i++) {
			if (tk.getUsername().compareTo(list.get(i).getUsername()) == 0) 
				return false;	
		}
		usDAO.insert(tk);
		return true;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
