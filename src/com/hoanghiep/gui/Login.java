package com.hoanghiep.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.hoanghiep.controllers.LoginController;
import com.hoanghiep.models.UserItem;
import com.hoanghiep.utils.ConnectDB;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class Login {
	private JFrame frame;
	private JTextField tfusername;
	private JPasswordField tfpwd;
	JLabel Ustar = new JLabel("*");
	JLabel Pstar = new JLabel("*");
	UserItem user = new UserItem();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Login window = new Login();

					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		initialize();
		Ustar.setVisible(false);
		Pstar.setVisible(false);
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Times New Roman", Font.BOLD, 23));
		frame.setBounds(50, 50, 898, 624);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblLogin = new JLabel("LOGIN ");
		lblLogin.setFont(new Font("Times New Roman", Font.BOLD, 32));
		lblLogin.setBounds(469, 192, 212, 67);
		frame.getContentPane().add(lblLogin);

		JLabel lblUsername = new JLabel("Tài Khoản");
		lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblUsername.setBounds(388, 282, 155, 50);
		frame.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Mật Khẩu");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblPassword.setBounds(388, 389, 133, 38);
		frame.getContentPane().add(lblPassword);

		tfusername = new JTextField();
		tfusername.setFont(new Font("Times New Roman", Font.BOLD, 23));
		tfusername.setBounds(578, 289, 208, 38);
		frame.getContentPane().add(tfusername);
		tfusername.setColumns(10);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Quản trị");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chckbxNewCheckBox.setBounds(516, 459, 110, 30);
		frame.getContentPane().add(chckbxNewCheckBox);

		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Người dùng");
		chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chckbxNewCheckBox_1.setBounds(666, 459, 120, 30);
		frame.getContentPane().add(chckbxNewCheckBox_1);
		
		tfpwd = new JPasswordField();
		tfpwd.setFont(new Font("Times New Roman", Font.BOLD, 23));
		tfpwd.setBounds(578, 390, 208, 38);
		frame.getContentPane().add(tfpwd);
		frame.setTitle("Đăng nhập vào hệ thống quản lý khu phố");
		JButton btnLogin = new JButton("Đăng nhập");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Ustar.setVisible(false);
				Pstar.setVisible(false);
				if (tfusername.getText().equals("")) {
					Ustar.setVisible(true);
				}
				if (String.valueOf(tfpwd.getPassword()).equals("")) {
					Pstar.setVisible(true);
				} else {
					
					user.setTenTaiKhoan(tfusername.getText());
					user.setMatKhau(String.valueOf(tfpwd.getPassword()));
					
					if(chckbxNewCheckBox.isSelected()) {
						user.setVaiTro("admin");
					}
					if(LoginController.login(user)) {
						frame.setVisible(false);
					}

				}
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnLogin.setBounds(374, 506, 147, 50);
		frame.getContentPane().add(btnLogin);

		JButton btnCancel = new JButton("Thoát");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnCancel.setBounds(551, 506, 155, 51);
		frame.getContentPane().add(btnCancel);

		Ustar.setForeground(Color.RED);
		Ustar.setFont(new Font("Times New Roman", Font.BOLD, 28));
		Ustar.setBounds(796, 296, 46, 21);
		frame.getContentPane().add(Ustar);

		Pstar.setForeground(Color.RED);
		Pstar.setFont(new Font("Times New Roman", Font.BOLD, 28));
		Pstar.setBounds(796, 397, 46, 21);
		frame.getContentPane().add(Pstar);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("src/com/hoanghiep/gui\\login.png"));
		label.setBounds(100, 234, 314, 283);
		frame.getContentPane().add(label);

		JLabel lblAppName = new JLabel("Phần mềm quản lý khu phố");
		lblAppName.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblAppName.setBounds(109, 72, 636, 80);
		frame.getContentPane().add(lblAppName);

		JLabel lblMe = new JLabel("Sinh viên thực hiện: Hoàng Tuấn Hiệp - 20182504");
		lblMe.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblMe.setBounds(109, 110, 636, 80);
		frame.getContentPane().add(lblMe);

		
	}
}
