package DAO;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import DTO.TourDTO;
import GUI.DatTourGUI;
import GUI.MainKhachHangGUI;
import database.JDBCUtil;

public class ThongKeDAO {
	
	public static ThongKeDAO getInstance() {
		return new ThongKeDAO();
	}
	//TK NGUOIDAT
	public ArrayList<String> getAccount() {
		ArrayList<String> listTK=new ArrayList<String>();
		Connection connection = JDBCUtil.getConnection();
		String sql = "SELECT * FROM TAIKHOAN WHERE MaTK LIKE '%KH%' AND TrangThai=1";
		Statement st;
        try {
        	st = connection.createStatement();
        	ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String tk = rs.getString("TaiKhoan").trim();
			    listTK.add(tk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        JDBCUtil.closeConnection(connection);
        return listTK;
	}
	public Object[][] countHD(ArrayList<String> listTK) {
		Connection connection = JDBCUtil.getConnection();
		ArrayList<String> listTemp=new ArrayList<String>();
		int init, i;
		//initialize listCount
		Object[][] listCount=new Object[listTK.size()][2];
		for(i=0;i<listTK.size();) {
			init=0;	
			Integer in=Integer.valueOf(init);
			listCount[i][0]=listTK.get(i);		//tenTK
			listCount[i][1]=in;					//soTourTK
			i++;
		}
		//initialize listTemp
		String sql = "SELECT * FROM HOADON WHERE TrangThai=1";
		Statement st;
        try {
        	st = connection.createStatement();
        	ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String tk = rs.getString("TenTK").trim();
				listTemp.add(tk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        //count tour
        int count;
        int k, q, n;
        for(k=0;k<listTemp.size();) {
        	for(q=0;q<listCount.length;) {
        		if(listCount[q][0].equals(listTemp.get(k))==true) {
        			count = (Integer)listCount[q][1];
        			count++;
        			Integer in=Integer.valueOf(count);
        			listCount[q][1]=in;
        		}
        		q++;
        	}
        	k++;
        }
      
        JDBCUtil.closeConnection(connection);
        return listCount;
	}
	public void sortCount(Object[][] count) {
		int i, j, n, max, n1;
		int index=0;
	    for (i=0;i<count.length-1; ) {
	    	index=i;
	    	max = (Integer)count[i][1];
	        for (j=i+1;j<count.length; ) {
	        	n1 = (Integer)count[j][1];
	        	if(max<n1) {
	        		max=n1;
	        		index=j;
	        	}
	        	j++;
	        }
	        Object tmp1=count[i][1];
	        count[i][1]=count[index][1];
	        count[index][1]=tmp1;
	        Object tmp2=count[i][0];
	        count[i][0]=count[index][0];
	        count[index][0]=tmp2;
	        i++;
	    }
	}
	
	//TK TOURDUOCCHON
	public ArrayList<String> getTour() {
		ArrayList<String> listTour=new ArrayList<String>();
		Connection connection = JDBCUtil.getConnection();
		String sql = "SELECT * FROM TOUR WHERE TrangThai=1";
		Statement st;
        try {
        	st = connection.createStatement();
        	ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String tour = rs.getString("MaTour").trim();
			    listTour.add(tour);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        JDBCUtil.closeConnection(connection);
        return listTour;
	}
	
	public ArrayList<String> statGetTour() {
		//store tours in IDlist to TKTOUR
		ArrayList<String> IDlist=new ArrayList<String>();
		Connection c = JDBCUtil.getConnection();
		int i;
		try {
			Statement st = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String sql="SELECT * FROM TOUR WHERE TrangThai=1";		
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				IDlist.add(rs.getString("MaTour"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return IDlist;
	}
	
	public void statAddTour(ArrayList<String> IDlist) {
		//add tours to TKTOUR
		ArrayList<String> list=IDlist;
		Connection c = JDBCUtil.getConnection();
		PreparedStatement ps=null;
		int i, flag=1;
		try {
			for(i=0;i<list.size(); ) {
				flag=1;
				//System.out.println("list:"+list.get(i));
				//compare list with existed tours, if new(flag=1) then insert
				Statement st=c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				String sql2="SELECT MaTour FROM TKTOUR";
				ResultSet rs=st.executeQuery(sql2);	
				while (rs.next()) {
					String id=rs.getString("MaTour");
					//System.out.println("id:"+id);
					if(list.get(i).equals(id)==true) {
						flag=0;
					}
				}
				//System.out.println("flag:"+flag);
				if(flag==1) {
					String sql="INSERT INTO TKTOUR VALUES(?,?,?)";
					ps=c.prepareStatement(sql);
					ps.setString(1,list.get(i));
					ps.setInt(2,0);
					ps.setInt(3,0);
					ps.execute();
					ps.close();
				}
			i++;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(c);
	}
	
	//statTour
	public void statCountTour() {
		int i;
		Object[][] data=MainKhachHangGUI.TKTour;
		Connection c = JDBCUtil.getConnection();
		try {
			Statement st=c.createStatement();
			String sql1="SELECT * FROM TKTOUR";
			String sql2="UPDATE TKTOUR "+
					" SET"+
					" SoLuong=?, TongTien=?"+
					" WHERE MaTour=?";
			ResultSet rs=st.executeQuery(sql1);
			while (rs.next()) {
				String id = rs.getString("MaTour");
				int sl = Integer.parseInt(rs.getString("SoLuong"));
				int tt = Integer.parseInt(rs.getString("TongTien"));
				for(i=0;i<data.length; ) {
					if(data[i][0].equals(id)==true) {
						sl+=(Integer)data[i][1];
						tt+=(Integer)data[i][2];
						PreparedStatement ps=c.prepareStatement(sql2);
						ps.setInt(1,sl);
						ps.setInt(2,tt);
						ps.setString(3,id);
						ps.execute();
						ps.close();
					}
				i++;
				}
			}
		}
		catch (SQLException e) {
				e.printStackTrace();
			}
		JDBCUtil.closeConnection(c);
	}
	//get TKTOUR for chart
	public Object[][] statChartTour(int numOfTour) {
		Object[][] data=new Object[numOfTour][3];
		int i=0, init;
		Connection c = JDBCUtil.getConnection();
		try {
			Statement st = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String sql="SELECT * FROM TKTOUR";		
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String maTour=rs.getString("MaTour");
				int soLuong=rs.getInt("SoLuong");
				int tongTien=rs.getInt("TongTien");
				data[i][0]=maTour;
				Integer in=Integer.valueOf(soLuong);
				data[i][1]=in;
				Integer in2=Integer.valueOf(tongTien);
				data[i][2]=in2;
				i++;
			}
		}
		catch (SQLException e) {
				e.printStackTrace();
			}
		JDBCUtil.closeConnection(c);
		return data;
	}
}
	

