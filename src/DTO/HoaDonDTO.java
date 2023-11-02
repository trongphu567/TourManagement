package DTO;

public class HoaDonDTO {
	private String maHoaDon;
    private String tenTaiKhoan;
    private int tongTien;

    public HoaDonDTO(String maHoaDon, String tenTaiKhoan, int tongTien) {
        this.maHoaDon = maHoaDon;
        this.tenTaiKhoan = tenTaiKhoan;
        this.tongTien = tongTien;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
    
    @Override
	public String toString() {
		return "HoaDon [id=" + maHoaDon + ", tenTK=" + tenTaiKhoan + ", tongTien=" + tongTien + "]";
	}
}
