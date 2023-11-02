package DTO;

import java.util.Objects;

public class KhachHangDTO {
	private String maKH;
	private String tenKH;
	private String gioiTinh;
	private String diaChi;
	private String sdt;
	private int trangThai;
	
	public KhachHangDTO(String maKH, String tenKH, String gioiTinh, String diaChi, String sdt, int trangThai) {
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.trangThai = trangThai;
	}
	public KhachHangDTO() {}
	
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public int getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		String regexStr = "^[0][0-9]{9}$";
		if (sdt==regexStr)
			this.sdt = sdt;
	}
	@Override
	public int hashCode() {
		return Objects.hash(diaChi, gioiTinh, maKH, sdt, tenKH);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHangDTO other = (KhachHangDTO) obj;
		return Objects.equals(diaChi, other.diaChi) && Objects.equals(gioiTinh, other.gioiTinh)
				&& Objects.equals(maKH, other.maKH) && Objects.equals(sdt, other.sdt)
				&& Objects.equals(tenKH, other.tenKH);
	}
	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", gioiTinh=" + gioiTinh + ", diaChi=" + diaChi
				+ ", sdt=" + sdt + "]";
	}
	
	
	
	
}
