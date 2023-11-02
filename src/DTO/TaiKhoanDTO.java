package DTO;

import java.io.Serializable;

public class TaiKhoanDTO implements Comparable<TaiKhoanDTO>,Serializable{
	private String id;
	private String username;
	private String password;
	private String loaiTK;
	private int TrangThai;
	
	public TaiKhoanDTO() {
		this.id="AD01";
		this.username="admin";
		this.password="admin";
		this.loaiTK="Admin";
		this.TrangThai=1;
	}
	
	public TaiKhoanDTO(String id,String username, String password, String loaiTK,int TrangThai) {
		this.id=id;
		this.username = username;
		this.password = password;
		this.loaiTK = loaiTK;
		this.TrangThai=TrangThai;
	}

	public int getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(int trangThai) {
		TrangThai = trangThai;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoaiTK() {
		return loaiTK;
	}

	public void setLoaiTK(String loaiTK) {
		this.loaiTK = loaiTK;
	}

	@Override
	public String toString() {
		return "TaiKhoanDTO [id=" + id + ", username=" + username + ", password=" + password + ", loaiTK=" + loaiTK
				+ ", TrangThai=" + TrangThai + "]";
	}

	@Override
	public int compareTo(TaiKhoanDTO o) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	
	
	
	
}
