package GUI;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import GUI.HoaDonGUI;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainAdminGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainAdminGUI frame = new MainAdminGUI();
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
	public MainAdminGUI() {
		setTitle("Phần mềm quản lý tour du lịch");
		
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_header = new JPanel();
		panel_header.setBackground(new Color(0,0,102));
		panel_header.setBounds(0, 0, 796, 60);
		contentPane.add(panel_header);
		panel_header.setLayout(null);
		
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/app.png"));
		Image i2=i1.getImage().getScaledInstance(55,54, Image.SCALE_DEFAULT);
		ImageIcon i3=new ImageIcon(i2);
		
		JLabel icon_header = new JLabel(i3);
		icon_header.setBounds(10, 10, 55, 44);
		panel_header.add(icon_header);
		
		JLabel lblPhaan = new JLabel("Phần mềm quản lý tour du lịch");
		lblPhaan.setForeground(Color.WHITE);
		lblPhaan.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPhaan.setBounds(75, 0, 426, 54);
		panel_header.add(lblPhaan);
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0,0,102));
		panel.setBounds(0, 60, 220, 614);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btn_qltaikhoan = new JButton("Quản lý tài khoản");
		btn_qltaikhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==btn_qltaikhoan) {
					new QLTaiKhoanGUI();
					setVisible(false);
				}
			}
		});
		btn_qltaikhoan.setForeground(Color.WHITE);
		btn_qltaikhoan.setBackground(new Color(0, 0, 102));
		btn_qltaikhoan.setIcon(new ImageIcon(MainAdminGUI.class.getResource("/icons/user.png")));
		btn_qltaikhoan.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_qltaikhoan.setBounds(0, 0, 220, 50);
		panel.add(btn_qltaikhoan);
		
		JButton btn_quanlytour = new JButton("Quản lý tour");
		btn_quanlytour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==btn_quanlytour) {
					new QLTourGUI();
					setVisible(false);
				}
			}
		});
		btn_quanlytour.setForeground(Color.WHITE);
		btn_quanlytour.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_quanlytour.setBackground(new Color(0, 0, 102));
		btn_quanlytour.setIcon(new ImageIcon(MainAdminGUI.class.getResource("/icons/tour.png")));
		btn_quanlytour.setBounds(0, 50, 220, 50);
		panel.add(btn_quanlytour);
		
		JButton btn_quanlynv = new JButton("Quản lý nhân viên");
		btn_quanlynv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==btn_quanlynv) {
					new NhanVienGUI();
					setVisible(false);
					
					
				}
			}
		});
		btn_quanlynv.setForeground(Color.WHITE);
		btn_quanlynv.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_quanlynv.setIcon(new ImageIcon(MainAdminGUI.class.getResource("/icons/employer.png")));
		btn_quanlynv.setBackground(new Color(0, 0, 102));
		btn_quanlynv.setBounds(0, 100, 220, 50);
		panel.add(btn_quanlynv);
		
		JButton btn_quanlykh = new JButton("Quản lý khách hàng");
		btn_quanlykh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==btn_quanlykh) {
					new QLKhachHangGUI();
					setVisible(false);
				}
					
			}
		});
		btn_quanlykh.setForeground(Color.WHITE);
		btn_quanlykh.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_quanlykh.setBackground(new Color(0, 0, 102));
		btn_quanlykh.setIcon(new ImageIcon(MainAdminGUI.class.getResource("/icons/customer.png")));
		btn_quanlykh.setBounds(0, 150, 220, 50);
		panel.add(btn_quanlykh);
		
		
		JButton btn_thongke = new JButton("Thống kê");
		btn_thongke.setIcon(new ImageIcon(MainAdminGUI.class.getResource("/icons/stat.png")));
		btn_thongke.setForeground(Color.WHITE);
		btn_thongke.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_thongke.setBackground(new Color(0, 0, 102));
		btn_thongke.setBounds(0, 250, 220, 50);
		btn_thongke.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==btn_thongke) {
					new ThongKeGUI();
					setVisible(false);
				}
			}
		});
		panel.add(btn_thongke);
		
		JButton btn_about = new JButton("Về chúng tôi");
		btn_about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
				          "Arial", Font.BOLD, 14))); 
				JOptionPane.showMessageDialog(null, "Phần mềm Quản lý Tour du lịch\nCác tác giả: \n"
						+ "Trần Trọng Phú \nNguyễn Thị Bích Trâm \n"
						+ "Nguyễn Duy Hiển \nNgô Nguyễn Mai Nghi \nPhú Tuấn Anh \n"
						+ "Năm hoàn thành: 2023");
			}
		});
		btn_about.setIcon(new ImageIcon(MainAdminGUI.class.getResource("/icons/aboutme.png")));
		btn_about.setForeground(Color.WHITE);
		btn_about.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_about.setBackground(new Color(0, 0, 102));
		btn_about.setBounds(0, 350, 220, 50);
		panel.add(btn_about);
		
		JButton btn_quanlyhd = new JButton("Quản lý hoá đơn");
		btn_quanlyhd.setIcon(new ImageIcon(MainAdminGUI.class.getResource("/icons/bill.png")));
		btn_quanlyhd.setForeground(Color.WHITE);
		btn_quanlyhd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_quanlyhd.setBackground(new Color(0, 0, 102));
		btn_quanlyhd.setBounds(0, 200, 220, 50);
		panel.add(btn_quanlyhd);
		btn_quanlyhd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==btn_quanlyhd) {
					setVisible(false);
					new HoaDonGUI().setVisible(true);
				}
			}
		});
		
		JButton btn_thoattk = new JButton("Thoát tài khoản");
		btn_thoattk.setIcon(new ImageIcon(MainAdminGUI.class.getResource("/icons/exit.png")));
		btn_thoattk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==btn_thoattk) {
					int select=JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát không", "Xác nhận", JOptionPane.YES_NO_OPTION);
					if (select==JOptionPane.YES_OPTION) {
						setVisible(false);
						new LoginGUI();
					}
					
				}
			}
		});
		btn_thoattk.setForeground(Color.WHITE);
		btn_thoattk.setBackground(new Color(0, 0, 102));
		btn_thoattk.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_thoattk.setBounds(0, 300, 220, 50);
		panel.add(btn_thoattk);
		
		ImageIcon icon=new ImageIcon(ClassLoader.getSystemResource("icons/background.jpg"));
		Image iconImage=icon.getImage().getScaledInstance(586,600, Image.SCALE_DEFAULT);
		ImageIcon i=new ImageIcon(iconImage);
		
		JLabel label_image = new JLabel(i);
		label_image.setBounds(180, 60, 630, 600);
		getContentPane().add(label_image);
		
		
		setVisible(true);
	}
}
