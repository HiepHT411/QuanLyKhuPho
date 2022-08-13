package com.hoanghiep.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hoanghiep.models.HoDan;
import com.hoanghiep.utils.ConnectDB;

public class HoDanController {

	Connection conn = ConnectDB.getConnection();
	PreparedStatement preStm = null;

//	public static void main(String[] args) {
//		HoDan hd = new HoDan("HD12", 3, 22, "KP12");
//
//		HoDanController hdController = new HoDanController();
//		System.out.println(hdController.updateHoDan("HD12", 33));
//	}

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

	public List<HoDan> getHoDanVoiMaKhuPho(String ma) {

		ArrayList<HoDan> list = new ArrayList<>();
		try {
			String sql = "select * from hodan where maKhuPho = '" + ma + "'";
			preStm = conn.prepareStatement(sql);
			ResultSet rs = preStm.executeQuery();

			while (rs.next()) {
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

	public boolean updateHoDan(String maHoDan, int soNha) {
		boolean flag = false;
		try {
			String sql = "update hodan set soNha = ? where maHoDan = ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(2, maHoDan);
			preStm.setString(1, String.valueOf(soNha));
			
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
