package GUI;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import DAO.MainKhachHangDAO;
import DAO.ThongKeDAO;
import DTO.TourDTO;
import GUI.DatTourGUI;
import BUS.MainKhachHangBUS;
import GUI.SignUpGUI;

public class MainKhachHangGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JButton btn_thanhtoan;
	private JButton btn_huytour;
	private JTextField tongtf;
	public static ArrayList<TourDTO> tourSan;
	private Object[][] data1;
	private String[] column1={"Mã tour", "Tên tour", "Số ngày", "Ngày bắt đầu", "Ngày kết thúc", "Tổng tiền"};;
	private DefaultTableModel model1;
	private Object[][] data2;
	private String[] column2={"Mã tour", "Tên tour", "Số ngày", "Thành tiền"};
	private DefaultTableModel model2;
	private int curRow1;
	private int curRow2;
	public static int numRow2=0;
	public static int tongTour=0;
	public static int numFile=1;
	public static String nguoiDat;
	private ArrayList<String> hoten= new ArrayList<String>();
	private ArrayList<String> gioitinh= new ArrayList<String>();
	private ArrayList<String> ngaysinh= new ArrayList<String>();
	private ArrayList<String> diachi= new ArrayList<String>();
	private ArrayList<String> sdt= new ArrayList<String>();
	private ArrayList<String> email= new ArrayList<String>();
	private ArrayList<String> tentour= new ArrayList<String>();
	private ArrayList<Integer> songuoi= new ArrayList<Integer>();
	private ArrayList<String> thanhtoan= new ArrayList<String>();
	private ArrayList<Integer> thanhtien= new ArrayList<Integer>();
	private JTextField ngdatf;
	private JButton btn_thoat;
	public static int soHD;
	public static Object[][] TKTour;
	public static int numTK;
	public MainKhachHangGUI() {
		
		//add new tour in TKTOUR
		ArrayList<String> list=ThongKeDAO.getInstance().statGetTour();
		System.out.println(list);
		ThongKeDAO.getInstance().statAddTour(list);
		
		setTitle("Phần mềm quản lý tour du lịch");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel_header = new JPanel();
		panel_header.setBounds(0, 0, 796, 54);
		panel_header.setBackground(new Color(0, 0, 102));
		contentPane.add(panel_header);
		panel_header.setLayout(null);
		
		/*ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/app.png"));
		Image i2=i1.getImage().getScaledInstance(55,54, Image.SCALE_DEFAULT);
		ImageIcon i3=new ImageIcon(i2);
		JLabel label_icon = new JLabel(i3);
		label_icon.setBounds(10, 0, 55, 54);
		panel_header.add(label_icon);*/
		
		JLabel label_header = new JLabel("Phiếu đặt tour");
		label_header.setForeground(Color.WHITE);
		label_header.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_header.setBounds(80, 0, 426, 54);
		panel_header.add(label_header);
		
		JPanel panel_dstour = new JPanel();
		panel_dstour.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_dstour.setBounds(10, 59, 766, 227);
		contentPane.add(panel_dstour);
		panel_dstour.setLayout(null);
		
		JLabel label_dstour = new JLabel("Danh sách tour có sẵn");
		label_dstour.setHorizontalAlignment(SwingConstants.CENTER);
		label_dstour.setBounds(229, 0, 287, 45);
		panel_dstour.add(label_dstour);
		label_dstour.setForeground(Color.RED);
		label_dstour.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		//TABLE TOUR SAN
		//import database
		tourSan = MainKhachHangBUS.getInstance().importToTable(tourSan);
		data1=new Object[tourSan.size()][column1.length];
		for(int i=0;i<tourSan.size();i++) {
			data1[i][0]=(tourSan.get(i).getMatour()).trim();
			data1[i][1]=(tourSan.get(i).getTentour()).trim();
			data1[i][2]=(tourSan.get(i).getSongay()).trim();
			data1[i][3]=(tourSan.get(i).getNgaykh()).trim();
			data1[i][4]=(tourSan.get(i).getNgaykt()).trim();
			data1[i][5]=(tourSan.get(i).getTongtien());
		}
		model1 = new DefaultTableModel(data1,column1);
		table = new JTable(model1);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 60, 756, 133);
		panel_dstour.add(scrollPane);
		
		JPanel panel_tourdadat = new JPanel();
		panel_tourdadat.setBounds(10, 292, 766, 261);
		contentPane.add(panel_tourdadat);
		panel_tourdadat.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_tourdadat.setLayout(null);
		
		JLabel label_tourdadat = new JLabel("Các tour đã đặt");
		label_tourdadat.setForeground(Color.RED);
		label_tourdadat.setHorizontalAlignment(SwingConstants.CENTER);
		label_tourdadat.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_tourdadat.setBounds(276, 0, 200, 34);
		panel_tourdadat.add(label_tourdadat);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 45, 746, 102);
		panel_tourdadat.add(scrollPane_1);
		
		JLabel tonglb = new JLabel("TỔNG:");
		tonglb.setFont(new Font("Tahoma", Font.BOLD, 15));
		tonglb.setBounds(461, 158, 95, 19);
		panel_tourdadat.add(tonglb);
		
		tongtf = new JTextField();
		tongtf.setEditable(false);
		tongtf.setBounds(531, 158, 225, 20);
		panel_tourdadat.add(tongtf);
		tongtf.setColumns(10);
		
		//TABLE TOUR DAT
		model2 = new DefaultTableModel(data2,column2);
		table_1 = new JTable(model2);
		scrollPane_1.setViewportView(table_1);
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		//Open DatTourGUI
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				curRow1=table.rowAtPoint(e.getPoint());
					if (curRow1 >=0) {
						DatTourGUI.thanhTien=(int) data1[curRow1][5];
						DatTourGUI datTour = new DatTourGUI();
						datTour.setVisible(true);
						datTour.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								if(DatTourGUI.status==1) {
									numRow2++;
									Object[] newRow= {data1[curRow1][0],data1[curRow1][1],
											data1[curRow1][2],DatTourGUI.tongTourRieng.get(numRow2-1)};
									model2.addRow(newRow);
									tongTour+=DatTourGUI.tongTourRieng.get(numRow2-1);
									tongtf.setText(Integer.toString(tongTour)+"VND");
									getData(datTour);
									try {
										writeFile();
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								}
							}
						});	
					}
				}
			});
		
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				curRow2=table_1.rowAtPoint(e.getPoint());
			}
		});
		
		//CANCEL BUTTON
		btn_huytour = new JButton("Huỷ Tour");
		//btn_huytour.setIcon(new ImageIcon(MainKhachHangView.class.getResource("/icons/huytour.png")));
		btn_huytour.setBackground(Color.YELLOW);
		btn_huytour.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_huytour.setBounds(112, 206, 164, 44);
		panel_tourdadat.add(btn_huytour);
		btn_huytour.addActionListener(this);
		
		//CONFIRM BUTTON
		btn_thanhtoan = new JButton("Thanh Toán");
		btn_thanhtoan.setForeground(Color.BLACK);
		//btn_thanhtoan.setIcon(new ImageIcon(MainKhachHangView.class.getResource("/icons/payment.png")));
		btn_thanhtoan.setBackground(Color.CYAN);
		btn_thanhtoan.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_thanhtoan.setBounds(313, 206, 164, 44);
		panel_tourdadat.add(btn_thanhtoan);
		btn_thanhtoan.addActionListener(this);
		
		JLabel ngdatlb = new JLabel("NGƯỜI ĐẶT: ");
		ngdatlb.setFont(new Font("Tahoma", Font.BOLD, 15));
		ngdatlb.setBounds(172, 158, 112, 19);
		panel_tourdadat.add(ngdatlb);
		
		//USERNAME TEXT FIELD
		//get current username
		if(LoginGUI.isLogin==1)
			nguoiDat = LoginGUI.usrnameLG;	
		else if(LoginGUI.isLogin==0)
			nguoiDat = SignUpGUI.usrnameSU;
		
		ngdatf = new JTextField();
		ngdatf.setEditable(false);
		ngdatf.setColumns(10);
		ngdatf.setBounds(287, 158, 164, 20);
		panel_tourdadat.add(ngdatf);
		ngdatf.addActionListener(this);
		ngdatf.setText(nguoiDat);
		
		btn_thoat = new JButton("Thoát");
		btn_thoat.setBackground(Color.WHITE);
		btn_thoat.setIcon(new ImageIcon(MainKhachHangGUI.class.getResource("/icons/exit.png")));
		btn_thoat.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_thoat.setBounds(531, 206, 164, 45);
		btn_thoat.addActionListener(this);
		panel_tourdadat.add(btn_thoat);
		
	}
	
	public void getData(DatTourGUI tour) {
		hoten.add(tour.getHoten());
		gioitinh.add(tour.getGioi());
		ngaysinh.add(tour.getNsinh());
		diachi.add(tour.getDchi());
		sdt.add(tour.getSdt());
		email.add(tour.getMail());
		tentour.add(data1[curRow1][1].toString());
		songuoi.add(tour.getSonguoi());
		thanhtoan.add(tour.getThanhtoan());
		thanhtien.add(tour.getThanhtien());
	}
	//WRITE TO PDF
	public void writeFile() throws IOException {
		try {
			//write new data
			//BaseFont bf = BaseFont.createFont("/src/font/UTMAvo.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			soHD=MainKhachHangBUS.getInstance().countHD();
			String nfile = String.format("%02d",soHD+1);
			String src="src/pdf/HD"+nfile+".pdf";
			Document tmp= new Document();
			PdfWriter writer=null;
			try {
				writer = PdfWriter.getInstance(tmp, new FileOutputStream(src));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			tmp.open();
			Paragraph com = new Paragraph("--Cong ty Du lich Java--");
			com.setAlignment(Element.ALIGN_CENTER);
			tmp.add(com);
			Paragraph title = new Paragraph("HOA DON");
			title.setAlignment(Element.ALIGN_CENTER);
			tmp.add(title);
			DateTimeFormatter fma = DateTimeFormatter.ofPattern("dd/MM/uuuu");  
			LocalDateTime now = LocalDateTime.now(); 
			Paragraph date = new Paragraph("TP.HCM "+fma.format(now).toString());
			date.setAlignment(Element.ALIGN_RIGHT);
			tmp.add(date);
			int i;
			for(i=0;i<=numRow2-1;) {
				Paragraph infoKH = new Paragraph("-THONG TIN KHACH HANG-");
				tmp.add(infoKH); 
				Paragraph hoTen = new Paragraph("Ho ten: "+hoten.get(i));
				tmp.add(hoTen);
				Paragraph gioiTinh = new Paragraph("Gioi tinh: "+gioitinh.get(i));
				tmp.add(gioiTinh);
				Paragraph ngaySinh = new Paragraph("Ngay sinh: "+ngaysinh.get(i));
				tmp.add(ngaySinh);
				Paragraph diaChi = new Paragraph("Dia chi: "+diachi.get(i));
				tmp.add(diaChi);
				Paragraph Sdt = new Paragraph("Sdt: "+sdt.get(i));
				tmp.add(Sdt);
				Paragraph Email = new Paragraph("Email: "+email.get(i));
				tmp.add(Email);
				Paragraph infoTour = new Paragraph("-THONG TIN TOUR DA DAT-");
				tmp.add(infoTour);
				Paragraph tenTour = new Paragraph("Ten tour: "+tentour.get(i));
				tmp.add(tenTour);
				Paragraph soNguoi = new Paragraph("So luong nguoi: "+songuoi.get(i));
				tmp.add(soNguoi);
				Paragraph thanhToan = new Paragraph("Phuong thuc thanh toan: "+thanhtoan.get(i));
				tmp.add(thanhToan);
				Paragraph thanhTien = new Paragraph("Thanh tien: "+thanhtien.get(i)+"VND");
				tmp.add(thanhTien);
				Paragraph endRow = new Paragraph("------------------------------");
				endRow.setAlignment(Element.ALIGN_CENTER);
				tmp.add(endRow);
				i++;
			}
			Paragraph tongTour = new Paragraph("TONG: "+tongtf.getText());
			tongTour.setAlignment(Element.ALIGN_RIGHT);
			tmp.add(tongTour);
			tmp.close();
		}
		catch (DocumentException e1) {
			e1.printStackTrace();
		}
	}
	public void clearList() {
		hoten.clear();
		gioitinh.clear();
		ngaysinh.clear();
		diachi.clear();
		sdt.clear();
		email.clear();
		tentour.clear();
		songuoi.clear();
		thanhtoan.clear();
		thanhtien.clear();
	}
	
	//COUNT IN TABLE2
	public Object[][] countTour(ArrayList<String> getTour){
		int i, j, k, sl=0, tien=0, init;
		//get tour from table
		ArrayList<String> listTour=new ArrayList<String>();
		for(i=0;i<numRow2; ) {
			listTour.add(table_1.getValueAt(i,0).toString());
			i++;
		}
		//initialize list count
		Object[][] list=new Object[getTour.size()][3];
		for(i=0;i<getTour.size(); ) {
			list[i][0]=getTour.get(i);	//maTour
			init=0;
			Integer in=Integer.valueOf(init);
			list[i][1]=in;		//soluongTour
			list[i][2]=in;		//tienTour
			i++;
		}
		//count
		for(i=0;i<listTour.size(); ){
			for(j=0;j<list.length; ){
				if(listTour.get(i).equals(list[j][0])==true) {
					sl = (Integer)list[j][1];
					sl++;
					Integer in = Integer.valueOf(sl);
					list[j][1]=in;					
					tien = (Integer)list[j][2];
					tien+=(Integer)table_1.getValueAt(i,3);
					Integer in2 = Integer.valueOf(tien);
					list[j][2]=in2;					//thanhtienTour
				}
			j++;
			}
		i++;		
		}
		/*
		int n;
		for(n=0;n<getTour.size(); ) {
			System.out.println("["+n+"][0]:"+list[n][0]);
			System.out.println("["+n+"][1]:"+list[n][1]);
			System.out.println("["+n+"][2]:"+list[n][2]);
			n++;
		}*/
		return list;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//CONFIRM EVENT-PRINT HOADON
		if(e.getSource()==btn_thanhtoan) {
			if(numRow2==0)
				JOptionPane.showMessageDialog(null,"Hiện không có tour nào");
			else{
				ArrayList<String> getTour = ThongKeDAO.getInstance().getTour();
				TKTour=countTour(getTour);
				ThongKeDAO.getInstance().statCountTour();
				Desktop dt = Desktop.getDesktop();
				soHD=MainKhachHangBUS.getInstance().countHD();
				String nfile = String.format("%02d",soHD+1);
				File pdf = new File("src/pdf/HD"+nfile+".pdf");
				String id = "HD"+nfile;
				int flag = MainKhachHangBUS.getInstance().addHD(id);
				if(flag==1) {
					
					try {
						dt.open(pdf);
					} catch (IOException e1) {
						e1.printStackTrace();
					} 
			
					numFile++;
					clearList();
					model2.setNumRows(0); 
					numRow2=0;
					tongTour=0;					
				}
			}
		}
		
		//CANCEL EVENT
		if(e.getSource()==btn_huytour) {
			model2.setNumRows(0); 
			clearList();
			numRow2=0;
			tongTour=0;
		}
		if (e.getSource()==btn_thoat) {
			int select=JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát không", "Xác nhận", JOptionPane.YES_NO_OPTION);
			if (select==JOptionPane.YES_OPTION) {
				setVisible(false);
				new LoginGUI();
			}
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainKhachHangGUI frame = new MainKhachHangGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
