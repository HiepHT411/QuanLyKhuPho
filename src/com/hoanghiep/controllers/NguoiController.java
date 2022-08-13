package com.hoanghiep.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hoanghiep.models.Nguoi;
import com.hoanghiep.utils.ConnectDB;

public class NguoiController {

	Connection conn = ConnectDB.getConnection();
	PreparedStatement preStm = null;
	
//	public static void main(String[] args) {
//		//testing
//		Nguoi ng = new Nguoi("NG12", "Nguyen Van Test", 22, 2000, "Xe om cong nghe", "HD04");
//
//		NguoiController ngController = new NguoiController();
//		
//		
//		ngController.addNguoi(ng);
//		System.out.println(ngController.getNguoiVoiMaHoDan("HD03"));
//		System.out.println(ngController.updateNguoi(ng));
//		
//		ng = null;
//		ngController = null;
//	}
	
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
	
	public boolean updateNguoi(Nguoi ng) {
		boolean flag = false;
		try {
			String sql = "update nguoi set hoVaTen= ? , tuoi = ?,  namSinh = ?, ngheNghiep = ? where maNguoi = ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(5, ng.getMaNguoi());
			preStm.setString(1, ng.getHoVaTen());
			preStm.setString(2, String.valueOf(ng.getTuoi()));
			preStm.setString(3, String.valueOf(ng.getNamSinh()));
			preStm.setString(4, ng.getNgheNghiep());
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
