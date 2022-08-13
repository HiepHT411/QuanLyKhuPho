package com.hoanghiep.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.hoanghiep.models.KhuPho;
import com.hoanghiep.utils.ConnectDB;

public class KhuPhoController {
	Connection conn = ConnectDB.getConnection();
	PreparedStatement preStm = null;

//	public static void main(String[] args) {
//		// testing
//		KhuPho khu = new KhuPho("KP111", "Test add khu pho");
//		KhuPhoController kp = new KhuPhoController();
//		
//		System.out.println(kp.addKhuPho(khu));
//		System.out.println(kp.getKhuPhoVoiMaKhuPho("KP02"));
//		System.out.println(kp.updateKhuPho("KP111", "Updated Name"));
//		kp = null;
//		khu = null;
//	}

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
	
	public boolean updateKhuPho(String maKhuPho, String ten) {
		boolean flag = false;
		try {
			String sql = "update khupho set tenKhuPho = ? where maKhuPho = ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(2, maKhuPho);
			preStm.setString(1, ten);
			
			int rs = preStm.executeUpdate();
			if (rs > 0) {
				flag = true;
				return flag;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
}
