package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import DAO.ThongKeDAO;

public class TKThuNhapTourFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TKThuNhapTourFrame frame = new TKThuNhapTourFrame();
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
	public TKThuNhapTourFrame() {
		//create dataset
				int i;
				int num=ThongKeDAO.getInstance().statGetTour().size();
				//System.out.println(num);
				Object data[][]=ThongKeDAO.getInstance().statChartTour(num);
				DefaultPieDataset dataset = new DefaultPieDataset( );
				for(i=0;i<num; ) {
					int tt = (Integer)data[i][2];
					dataset.setValue(data[i][0].toString(),tt);  
					i++;
				}
				//create chart
				JFreeChart chart = ChartFactory.createPieChart("Thống kê thu nhập tour",dataset,true,true,false);
				//create panel
				ChartPanel panel = new ChartPanel(chart);  
			    setContentPane(panel);  
	}

}
