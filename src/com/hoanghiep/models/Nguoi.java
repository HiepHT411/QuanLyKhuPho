package com.hoanghiep.models;

public class Nguoi {
	private String maNguoi;
	private String hoVaTen;
	private int tuoi;
	private int namSinh;
	private String ngheNghiep;
	private String maHoDan;
	
	public Nguoi() {
		super();
	}

	public Nguoi(String maNguoi, String hoVaTen, int tuoi, int namSinh,
									String ngheNghiep, String maHoDan) {
		super();
		this.maNguoi = maNguoi;
		this.hoVaTen = hoVaTen;
		this.tuoi = tuoi;
		this.namSinh = namSinh;
		this.ngheNghiep = ngheNghiep;
		this.maHoDan = maHoDan;
	}

	public String getMaNguoi() {
		return maNguoi;
	}

	public void setMaNguoi(String maNguoi) {
		this.maNguoi = maNguoi;
	}

	public String getHoVaTen() {
		return hoVaTen;
	}

	public void setHoVaTen(String hoVaTen) {
		this.hoVaTen = hoVaTen;
	}

	public int getTuoi() {
		return tuoi;
	}

	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}

	public int getNamSinh() {
		return namSinh;
	}

	public void setNamSinh(int namSinh) {
		this.namSinh = namSinh;
	}

	public String getNgheNghiep() {
		return ngheNghiep;
	}

	public void setNgheNghiep(String ngheNghiep) {
		this.ngheNghiep = ngheNghiep;
	}

	public String getMaHoDan() {
		return maHoDan;
	}

	public void setMaHoDan(String maHoDan) {
		this.maHoDan = maHoDan;
	}

	@Override
	public String toString() {
		return "Nguoi [maNguoi=" + maNguoi + ", hoVaTen=" + hoVaTen + ", tuoi=" + tuoi + ", namSinh=" + namSinh
				+ ", ngheNghiep=" + ngheNghiep + ", maHoDan=" + maHoDan + "]";
	}
	
	
	
}
