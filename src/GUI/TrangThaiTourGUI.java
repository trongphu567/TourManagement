package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TrangThaiTourGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrangThaiTourGUI frame = new TrangThaiTourGUI();
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
	public TrangThaiTourGUI() {
		setTitle("Trạng thái tour");
		setBounds(100, 100, 800, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Danh sách các tour cần xử lý");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(256, 10, 300, 37);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 47, 786, 276);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 766, 256);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Mã tour", "Tên tour", "Số ngày", "Tổng tiền", "Trạng thái"
			}
		));
		JTableHeader tableHeader=table.getTableHeader();
		tableHeader.setFont(new Font("Arial",Font.BOLD,14));
		scrollPane.setViewportView(table);
		
		JButton btn_xuly = new JButton("Xử lý tour");
		btn_xuly.setBackground(Color.YELLOW);
		btn_xuly.setIcon(new ImageIcon(TrangThaiTourGUI.class.getResource("/icons/run.png")));
		btn_xuly.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_xuly.setBounds(196, 333, 180, 37);
		contentPane.add(btn_xuly);
		
		JButton btn_back = new JButton("Quay lại");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==btn_back) {
					setVisible(false);
					new MainAdminGUI();
				}
			}
		});
		btn_back.setBackground(Color.GREEN);
		btn_back.setIcon(new ImageIcon(TrangThaiTourGUI.class.getResource("/icons/exit.png")));
		btn_back.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_back.setBounds(410, 333, 146, 37);
		contentPane.add(btn_back);
		setVisible(true);
	}
}
