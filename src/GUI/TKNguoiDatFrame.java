package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DAO.ThongKeDAO;
import java.awt.Color;
import GUI.MainKhachHangGUI;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class TKNguoiDatFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TKNguoiDatFrame frame = new TKNguoiDatFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TKNguoiDatFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 547);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("THỐNG KÊ KHÁCH ĐẶT TOUR");
		lblNewLabel.setBounds(25, 11, 322, 27);
		lblNewLabel.setForeground(SystemColor.window);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		//get count
		ArrayList<String> listAcc=ThongKeDAO.getInstance().getAccount();
		Object[][] count=ThongKeDAO.getInstance().countHD(listAcc);
		ThongKeDAO.getInstance().sortCount(count);
		
		String col[]= {"TÀI KHOẢN","SỐ LẦN THANH TOÁN"};
		DefaultTableModel model = new DefaultTableModel(count,col);
		table = new JTable(model);
		table.setBackground(SystemColor.window);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 43, 432, 444);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
		
		JButton returnbt = new JButton("Trở về");
		returnbt.setBackground(Color.LIGHT_GRAY);
		returnbt.setForeground(Color.DARK_GRAY);
		returnbt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		returnbt.setBounds(378, 15, 79, 23);
		contentPane.add(returnbt);
		returnbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==returnbt) {
					setVisible(false);
				}
			}
		});
	}
}
