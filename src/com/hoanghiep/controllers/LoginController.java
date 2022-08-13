package com.hoanghiep.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.hoanghiep.gui.Main;
import com.hoanghiep.models.UserItem;
import com.hoanghiep.utils.ConnectDB;

public class LoginController {

	public static boolean login(UserItem user) {
		boolean flag = false;
		Connection conn = ConnectDB.getConnection();
		try {

			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM useritem WHERE tenTaiKhoan= ? AND matKhau=? ");
			ps.setString(1, user.getTenTaiKhoan());
			ps.setString(2, user.getMatKhau());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Main main = new Main();
				main.showWindow();
//				main.showAllKhuPho();
//				main.setVisible(true);
//				main.pack();
//				main.setLocationRelativeTo(null);
//				main.setBounds(50, 50, 1015, 574);
				//frame.setVisible(false);
				flag = true;
			} else
				JOptionPane.showMessageDialog(null, "Error", "Please check user name / password",
						JOptionPane.ERROR_MESSAGE);

		} catch (SQLException p) {
			p.printStackTrace();
		}
		return flag;
	}
}
