package GUI;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import BUS.HoaDonBUS;
import DAO.HoaDonDAO;
import DTO.HoaDonDTO;
import GUI.MainAdminGUI;

public class HoaDonGUI extends JFrame implements ActionListener, KeyListener {

	private JPanel contentPane;
	private JTextField searchtf;
	private DefaultTableModel model;
	private ArrayList<HoaDonDTO> hd;
	private Object data[][];
	private JTable table;
	private String[] col= {"ID","Tên tài khoản","Tổng tiền"};
	private int curRow;
	private JButton returnbt;
	private TableRowSorter<DefaultTableModel> sort;
	private JButton delbt;
	
	public HoaDonGUI() {
		
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 818, 581);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titlelb = new JLabel("QUẢN LÝ HOÁ ĐƠN");
		titlelb.setForeground(Color.LIGHT_GRAY);
		titlelb.setFont(new Font("Tahoma", Font.BOLD, 25));
		titlelb.setBounds(28, 22, 256, 37);
		contentPane.add(titlelb);
		
		//SEARCH TEXTFIELD
		searchtf = new JTextField();
		searchtf.setBackground(Color.GRAY);
		searchtf.setBounds(121, 70, 309, 25);
		contentPane.add(searchtf);
		searchtf.setColumns(10);
		searchtf.addKeyListener(this);
		
		JLabel searchlb = new JLabel("Tìm kiếm");
		searchlb.setFont(new Font("Tahoma", Font.PLAIN, 19));
		searchlb.setForeground(Color.LIGHT_GRAY);
		searchlb.setBounds(28, 70, 150, 25);
		contentPane.add(searchlb);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 106, 754, 414);
		contentPane.add(scrollPane);
		
		//TABLE 
		hd = HoaDonBUS.getInstance().importToTable(hd);
		data = new Object[hd.size()][col.length];
		for(int i=0;i<hd.size();i++) {
			data[i][0]=(hd.get(i).getMaHoaDon()).trim();
			data[i][1]=(hd.get(i).getTaiKhoan()).trim();
			data[i][2]=(Integer.toString(hd.get(i).getTongTien()).trim());
		}
		model = new DefaultTableModel(data,col);
		sort=new TableRowSorter<DefaultTableModel>(model);
		table = new JTable(model);
		table.setRowSorter(sort);
		scrollPane.setViewportView(table);
		table.setBackground(Color.LIGHT_GRAY);
		
		//hiển thị chi tiết hoá đơn
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				curRow = table.rowAtPoint(e.getPoint());
				if (curRow >=0) {
					String mahd = data[curRow][0].toString();
					Desktop dt = Desktop.getDesktop();
					File pdf = new File("src/pdf/"+mahd+".pdf");
					try {
						dt.open(pdf);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		//RETURN BUTTON
		returnbt = new JButton("Trở về");
		returnbt.setForeground(Color.LIGHT_GRAY);
		returnbt.setBackground(Color.GRAY);
		returnbt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		returnbt.setBounds(688, 70, 94, 25);
		contentPane.add(returnbt);
		returnbt.addActionListener(this);
		
		//DELETE BUTTON
		delbt = new JButton("Xoá");
		delbt.setForeground(Color.LIGHT_GRAY);
		delbt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		delbt.setBackground(Color.GRAY);
		delbt.setBounds(584, 70, 94, 25);
		contentPane.add(delbt);
		delbt.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//RETURN MAIN MENU
		if(e.getSource()==returnbt) {
			setVisible(false);
			new MainAdminGUI().setVisible(true);
		}
		//DELETE EVENT
		if(e.getSource()==delbt) {
			int del = HoaDonBUS.getInstance().deleteHD();
			model.removeRow(curRow);
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
					HoaDonGUI frame = new HoaDonGUI();
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
