package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;

import DAO.ThongKeDAO;
import GUI.MainKhachHangGUI;

public class TKTourDuocChonFrame extends JFrame {
	
	public TKTourDuocChonFrame() {
		//create dataset
		int i;
		ArrayList<String> listTK=ThongKeDAO.getInstance().statGetTour();
		int num=ThongKeDAO.getInstance().statGetTour().size();
		ThongKeDAO.getInstance().statAddTour(listTK);
		System.out.println(num);
		Object data[][]=ThongKeDAO.getInstance().statChartTour(num);
		DefaultPieDataset dataset = new DefaultPieDataset( );
		for(i=0;i<num; ) {
			int sl = (Integer)data[i][1];
			dataset.setValue(data[i][0].toString(),sl);  
			i++;
		}
		//create chart
		JFreeChart chart = ChartFactory.createPieChart("Thống kê tour được chọn",dataset,true,true,false);
		//create panel
		ChartPanel panel = new ChartPanel(chart);  
	    setContentPane(panel);  
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
