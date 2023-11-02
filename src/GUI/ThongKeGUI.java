package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import BUS.HoaDonBUS;
import DTO.HoaDonDTO;
import DAO.ThongKeDAO;
import java.awt.GridLayout;
import java.awt.SystemColor;

public class ThongKeGUI extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;
	private ArrayList<HoaDonDTO> hd;
	private Object data[][];
	private String[] col= {"ID","Tên tài khoản","Tổng tiền"};
	private int curRow;
	private JButton returnbt;
	private TableRowSorter<DefaultTableModel> sort;
	private JLabel khdatlb;
	private JLabel tourdatlb;
	private JLabel tntourlb;
	
	public ThongKeGUI() {
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 665, 397);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titlelb = new JLabel("THỐNG KÊ");
		titlelb.setBackground(SystemColor.inactiveCaptionBorder);
		titlelb.setForeground(SystemColor.inactiveCaptionBorder);
		titlelb.setFont(new Font("Tahoma", Font.BOLD, 27));
		titlelb.setBounds(28, 22, 256, 37);
		contentPane.add(titlelb);
		
		//RETURN BUTTON
		returnbt = new JButton("Trở về");
		returnbt.setForeground(SystemColor.textHighlight);
		returnbt.setBackground(SystemColor.inactiveCaptionBorder);
		returnbt.setFont(new Font("Tahoma", Font.BOLD, 17));
		returnbt.setBounds(530, 31, 94, 25);
		contentPane.add(returnbt);
		returnbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==returnbt) {
					setVisible(false);
					new MainAdminGUI().setVisible(true);
				}
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBounds(28, 70, 596, 246);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//TK KHACH DAT TOUR OPTION
		khdatlb = new JLabel("□ Thống kê khách hàng đặt tour");
		khdatlb.setForeground(SystemColor.textHighlight);
		khdatlb.setFont(new Font("Tahoma", Font.BOLD, 25));
		khdatlb.setBounds(25, 23, 521, 40);
		khdatlb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(khdatlb);
		//TKNGUOIDAT EVENT
		khdatlb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new TKNguoiDatFrame().setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				khdatlb.setForeground(Color.blue);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				khdatlb.setForeground(Color.decode("#0078d7"));
			}
		});
		
		//TK TOUR DUOC DAT OPTION
		tourdatlb = new JLabel("□ Thống kê tour được chọn");
		tourdatlb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tourdatlb.setForeground(SystemColor.textHighlight);
		tourdatlb.setFont(new Font("Tahoma", Font.BOLD, 25));
		tourdatlb.setBounds(25, 91, 521, 40);
		panel.add(tourdatlb);
		tourdatlb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TKTourDuocChonFrame frame = new TKTourDuocChonFrame();
				frame.setSize(400, 400); 
				frame.setVisible(true);
				frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				tourdatlb.setForeground(Color.blue);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				tourdatlb.setForeground(Color.decode("#0078d7"));
			}
		});
		
		//TK THU NHAP TOUR OPTION
		tntourlb = new JLabel("□ Thống kê thu nhập tour");
		tntourlb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tntourlb.setForeground(SystemColor.textHighlight);
		tntourlb.setFont(new Font("Tahoma", Font.BOLD, 25));
		tntourlb.setBounds(25, 163, 521, 40);
		panel.add(tntourlb);
		tntourlb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TKThuNhapTourFrame frame = new TKThuNhapTourFrame();
				frame.setSize(400, 400); 
				frame.setVisible(true);
				frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				tntourlb.setForeground(Color.blue);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				tntourlb.setForeground(Color.decode("#0078d7"));
			}
		});
		setVisible(true);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongKeGUI frame = new ThongKeGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
}
