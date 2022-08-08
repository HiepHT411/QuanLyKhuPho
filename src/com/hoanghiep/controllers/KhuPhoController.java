package com.hoanghiep.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hoanghiep.models.HoDan;
import com.hoanghiep.models.KhuPho;
import com.hoanghiep.models.Nguoi;
import com.hoanghiep.utils.ConnectDB;

public class KhuPhoController {
	Connection conn = ConnectDB.getConnection();
	PreparedStatement preStm = null;

	public static void main(String[] args) {
		// testing
		Nguoi ng = new Nguoi("NG12", "Nguyen Van Test", 22, 2000, "Xe om", "HD04");
		HoDan hd = new HoDan("HD12", 3, 22, "KP12");

		KhuPhoController kp = new KhuPhoController();
		// kp.addNguoi(ng);
		//kp.addHoDan(hd);
		ng = null;
		hd = null;
		
//		System.out.println(kp.getNguoiVoiMaHoDan("HD03"));
//		System.out.println(kp.getHoDanVoiMaKhuPho("KP01"));
//		System.out.println(kp.getKhuPhoVoiMaKhuPho("KP02"));
		
	}

	public ArrayList<KhuPho> getAllKhuPho() {

		ArrayList<KhuPho> ds = new ArrayList<>();

		try {

			String sql = "select * from khupho";
			preStm = conn.prepareStatement(sql);
			ResultSet rs = preStm.executeQuery();

			while (rs.next()) {
				KhuPho nxb = new KhuPho();
				nxb.setMaKhuPho(rs.getString(1));
				nxb.setTenKhuPho(rs.getString(2));

				ds.add(nxb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	public boolean deleteKhuPho(String ma) {

		try {
			String sql = "delete from khupho where maKhuPho = ?";

			preStm = conn.prepareStatement(sql);
			preStm.setString(1, ma);
			int rs = preStm.executeUpdate();
			if (rs > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean addNguoi(Nguoi ng) {
		boolean flag = true;
		try {
			String sql1 = "insert into nguoi (maNguoi, hoVaTen, tuoi, namSinh, ngheNghiep, maHoDan) values (?, ? , ? ,? , ?, (select maHoDan from hodan where maHoDan = ?))";

			preStm = conn.prepareStatement(sql1);
			preStm.setString(1, ng.getMaNguoi());
			preStm.setString(2, ng.getHoVaTen());
			preStm.setString(3, String.valueOf(ng.getTuoi()));
			preStm.setString(4, String.valueOf(ng.getNamSinh()));
			preStm.setString(5, ng.getNgheNghiep());
			preStm.setString(6, ng.getMaHoDan());
			int rs = preStm.executeUpdate();
			if (rs < 0) {
				flag = false;
				return flag;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean addHoDan(HoDan hd) {
		boolean flag = true;
		try {
			String sql2 = "insert into hodan (maHoDan, soThanhVien, soNha, maKhuPho) values ( ? , ? , ? , (select maKhuPho from khupho where maKhuPho = ? ))";
			preStm = conn.prepareStatement(sql2);
			preStm.setString(1, hd.getMaHoDan());
			preStm.setLong(2, hd.getSoThanhVien());
			preStm.setLong(3, hd.getSoNha());
			preStm.setString(4, hd.getMaKhuPho());
			System.out.println(sql2);
			int rs = preStm.executeUpdate();
			if (rs < 0) {
				flag = false;
				return flag;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean addKhuPho(KhuPho kp) {
		boolean flag = true;
		try {
			String sql1 = "insert into khupho (maKhuPho, tenKhuPho) values (?, ?)";

			preStm = conn.prepareStatement(sql1);
			preStm.setString(1, kp.getMaKhuPho());
			preStm.setString(2, kp.getTenKhuPho());
			int rs = preStm.executeUpdate();

//			if (rs > 0) {
//				for (HoDan hd : listHoDan) {
//					String sql2 = "insert into hodan (maHoDan, soThanhVien, soNha, maKhuPho) values "
//							+ "(? , ? , ? , (select maKhuPho from khupho where maKhuPho = ?))";
//					preStm = conn.prepareStatement(sql2);
//					preStm.setString(1, hd.getMaHoDan());
//					preStm.setString(2, String.valueOf(hd.getSoThanhVien()));
//					preStm.setString(3, String.valueOf(hd.getSoNha()));
//					preStm.setString(4, hd.getMaKhuPho());
//
//				}
//			}
			if (rs < 0) {
				flag = false;
				return flag;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public List<Nguoi> getNguoiVoiMaHoDan(String ma) {
		ArrayList<Nguoi> list = new ArrayList<>(); 
		try {
			String sql = "select * from nguoi where maHoDan = '"+ ma+"'";
			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
		
			while(rs.next()) {
				Nguoi ng = new Nguoi();
				ng.setMaNguoi(rs.getString(1));
				ng.setHoVaTen(rs.getString(2));
				ng.setTuoi(Integer.parseInt(rs.getString(3)));
				ng.setNamSinh(Integer.parseInt(rs.getString(4)));
				ng.setMaHoDan(rs.getString(5));
				ng.setNgheNghiep(rs.getString(6));
				list.add(ng);
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
		return list;
	}

	public List<HoDan>getHoDanVoiMaKhuPho(String ma) {
		
		ArrayList<HoDan> list = new ArrayList<>(); 
		try {
			String sql = "select * from hodan where maKhuPho = '"+ ma+"'";
			preStm = conn.prepareStatement(sql);
			ResultSet rs = preStm.executeQuery();
		
			while(rs.next()) {
				HoDan hd = new HoDan();
				hd.setMaHoDan(rs.getString(1));
				hd.setSoThanhVien(Integer.parseInt(rs.getString(2)));
				hd.setSoNha(Integer.parseInt(rs.getString(3)));
				hd.setMaKhuPho(rs.getString(4));
				list.add(hd);
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
		return list;
	}

	public KhuPho getKhuPhoVoiMaKhuPho(String ma) {
		KhuPho kp = new KhuPho();
		kp.setMaKhuPho(ma);
		try {
			String sql = "select * from khupho where maKhuPho = '"+ ma+"'";
			preStm = conn.prepareStatement(sql);
			ResultSet rs = preStm.executeQuery();
			
			while(rs.next()) {
				kp.setTenKhuPho(rs.getString(2));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kp;
	}
}
