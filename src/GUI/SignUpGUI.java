package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import database.JDBCUtil;
import DAO.TaiKhoanDAO;

public class SignUpGUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txt_username;
	private JTextField txt_pass;
	private JTextField txt_repass;
	private JButton btn_signup;
	private JButton btn_back;
	private LoginGUI loginGUI;
	public static String usrnameSU;
	public static int count;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpGUI frame = new SignUpGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public SignUpGUI() {
		setTitle("Đăng ký tài khoản");
		setBounds(100, 100, 600, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_header = new JLabel("Đăng ký tài khoản");
		label_header.setForeground(Color.RED);
		label_header.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_header.setBounds(180, 10, 232, 24);
		//label_header.setIcon(new ImageIcon(SignUpGUI.class.getResource("/icons/account.png")));
		contentPane.add(label_header);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 44, 576, 373);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label_username = new JLabel("Tên đăng nhập");
		label_username.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_username.setBounds(24, 51, 141, 26);
		panel.add(label_username);
		
		JLabel label_pass = new JLabel("Mật khẩu");
		label_pass.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_pass.setBounds(24, 135, 141, 26);
		panel.add(label_pass);
		
		JLabel label_repass = new JLabel("Nhập lại mật khẩu");
		label_repass.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_repass.setBounds(24, 226, 158, 26);
		panel.add(label_repass);
		
		txt_username = new JTextField();
		txt_username.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt_username.setBounds(197, 44, 246, 42);
		panel.add(txt_username);
		txt_username.setColumns(10);
		
		txt_pass = new JTextField();
		txt_pass.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt_pass.setColumns(10);
		txt_pass.setBounds(197, 128, 246, 42);
		panel.add(txt_pass);
		
		txt_repass = new JTextField();
		txt_repass.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt_repass.setColumns(10);
		txt_repass.setBounds(197, 219, 246, 42);
		panel.add(txt_repass);
		
		btn_signup = new JButton("Đăng ký");
		btn_signup.setBackground(new Color(14, 200, 255));
		btn_signup.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_signup.setBounds(105, 309, 141, 42);
		//btn_signup.setIcon(new ImageIcon(SignUpGUI.class.getResource("/icons/signup.png")));
		btn_signup.addActionListener(this);
		panel.add(btn_signup);
		
		btn_back = new JButton("Quay lại");
		btn_back.setBackground(new Color(255, 73, 40));
		btn_back.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_back.setBounds(290, 309, 158, 42);
		//btn_back.setIcon(new ImageIcon(SignUpGUI.class.getResource("/icons/back.png")));
		btn_back.addActionListener(this);
		panel.add(btn_back);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource()==btn_signup) {
			
			String username=txt_username.getText();
			String password=txt_pass.getText();
			String repass=txt_repass.getText();
			String maTK="KH0"+count;
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
			          "Arial", Font.BOLD, 14)));
			
			if (username.equals("") || password.equals("") || repass.equals(""))
				JOptionPane.showMessageDialog(null,"Hãy nhập vào đầy đủ thông tin","Lỗi", JOptionPane.ERROR_MESSAGE);
			else if (!password.equals(repass))
				JOptionPane.showMessageDialog(null, "Mật khẩu không khớp", "Lỗi", JOptionPane.ERROR_MESSAGE);
			else {
				
				try {
					count=TaiKhoanDAO.getInstance().numOfTK();
					String n = String.format("%02d",count+1);
					String sql="insert into TAIKHOAN values ('KH"+n+"','"+username+"','"+password+"',N'"+"Khách Hàng"+"','"+"1"+"')";
					//System.out.println(count+" "+sql);
					Connection con=JDBCUtil.getConnection();
					Statement st=con.createStatement();
					st.executeUpdate(sql);
					LoginGUI.isLogin=0;
					usrnameSU=txt_username.getText().trim();
					JOptionPane.showMessageDialog(null, "Tạo tài khoản thành công");
					setVisible(false);
					new LoginGUI();
					JDBCUtil.closeConnection(con);
					
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		else if (ae.getSource()==btn_back)
		{
			setVisible(false);
			loginGUI=new LoginGUI();
		}
	
	}

}
