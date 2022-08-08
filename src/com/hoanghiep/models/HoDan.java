package com.hoanghiep.models;

public class HoDan {
	private String maHoDan;
	private int soThanhVien;
	private int soNha;
	private String maKhuPho;
	
	public HoDan() {
		super();
	}
	
	public HoDan(String maHoDan, int soThanhVien, int soNha, String maKhuPho) {
		super();
		this.maHoDan = maHoDan;
		this.soThanhVien = soThanhVien;
		this.soNha = soNha;
		this.maKhuPho = maKhuPho;
	}
	
	public String getMaHoDan() {
		return maHoDan;
	}
	public void setMaHoDan(String maHoDan) {
		this.maHoDan = maHoDan;
	}
	public int getSoThanhVien() {
		return soThanhVien;
	}
	public void setSoThanhVien(int soThanhVien) {
		this.soThanhVien = soThanhVien;
	}
	public int getSoNha() {
		return soNha;
	}
	public void setSoNha(int soNha) {
		this.soNha = soNha;
	}
	public String getMaKhuPho() {
		return maKhuPho;
	}
	public void setMaKhuPho(String maKhuPho) {
		this.maKhuPho = maKhuPho;
	}

	@Override
	public String toString() {
		return "HoDan [maHoDan=" + maHoDan + ", soThanhVien=" + soThanhVien + ", soNha=" + soNha + ", maKhuPho="
				+ maKhuPho + "]";
	}
	
	
}
