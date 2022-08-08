package com.hoanghiep.models;

public class KhuPho {
	private String maKhuPho;
	private String tenKhuPho;
	
	public KhuPho() {
		super();
	}
	
	public KhuPho(String maKhuPho, String tenKhuPho) {
		super();
		this.maKhuPho = maKhuPho;
		this.tenKhuPho = tenKhuPho;
	}

	public String getMaKhuPho() {
		return maKhuPho;
	}

	public void setMaKhuPho(String maKhuPho) {
		this.maKhuPho = maKhuPho;
	}

	public String getTenKhuPho() {
		return tenKhuPho;
	}

	public void setTenKhuPho(String tenKhuPho) {
		this.tenKhuPho = tenKhuPho;
	}

	@Override
	public String toString() {
		return "KhuPho [maKhuPho=" + maKhuPho + ", tenKhuPho=" + tenKhuPho + "]";
	}
	
	
}
