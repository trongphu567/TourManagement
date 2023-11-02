package DTO;

public class TourDTO {
	private String matour;
	private String tentour;
	private String diadiem;
	private String songay;
	private String ngaykh;
	private String ngaykt;
	private String phuongtien;
	private String noio;
	private int tongtien;
	private int trangthai;
	
	public TourDTO(String matour, String tentour, String diadiem , String songay , String ngaykh , String ngaykt , String phuongtien, String noio, int tongtien, int trangthai) {
		this.matour = matour;
		this.tentour = tentour;
		this.diadiem = diadiem;
		this.songay = songay;
		this.ngaykh = ngaykh;
		this.ngaykt = ngaykt;
		this.phuongtien = phuongtien;
		this.noio = noio;
		this.tongtien = tongtien;
		this.trangthai = trangthai;
	}
	
	public TourDTO() {}

	public String getMatour() {
		return matour;
	}
	public String getTentour() {
		return tentour;
	}
	public String getDiadiem() {
		return diadiem;
	}
	public String getSongay() {
		return songay;
	}
	public String getNgaykh() {
		return ngaykh;
	}
	public String getNgaykt() {
		return ngaykt;
	}
	public String getPhuongtien() {
		return phuongtien;
	}
	public String getNoio() {
		return noio;
	}
	public int getTongtien() {
		return tongtien;
	}
	
	public int getTrangthai() {
		return trangthai;
	}
	
	public void setMatour(String matour) {
		this.matour = matour;
	}

	public void setTentour(String tentour) {
		this.tentour = tentour;
	}

	public void setDiadiem(String diadiem) {
		this.diadiem = diadiem;
	}

	public void setSongay(String songay) {
		this.songay = songay;
	}

	public void setNgaykh(String ngaykh) {
		this.ngaykh = ngaykh;
	}

	public void setNgaykt(String ngaykt) {
		this.ngaykt = ngaykt;
	}

	public void setPhuongtien(String phuongtien) {
		this.phuongtien = phuongtien;
	}

	public void setNoio(String noio) {
		this.noio = noio;
	}

	public void setTongtien(int tongtien) {
		this.tongtien = tongtien;
	}
	
	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}
	
	@Override
	public String toString() {
		return "Tour [matour=" + matour + ", tentour=" + tentour + ", diadiem=" + diadiem + ", songay=" + songay + ", ngaykh=" + ngaykh + ", ngaykt=" + ngaykt + ", phuongtien=" + phuongtien + ", noio=" + noio + ", tongtien=" + tongtien + ", trangthai=" + trangthai +"]";
	}
}
