package com.hoanghiep.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.hoanghiep.controllers.HoDanController;
import com.hoanghiep.controllers.KhuPhoController;
import com.hoanghiep.controllers.NguoiController;
import com.hoanghiep.models.HoDan;
import com.hoanghiep.models.KhuPho;
import com.hoanghiep.models.Nguoi;
import com.hoanghiep.utils.ConnectDB;
import javax.swing.JTextPane;

public class AddKhuPho extends JFrame {
	KhuPhoController kpController = new KhuPhoController();
	HoDanController hdController = new HoDanController();
	NguoiController nguoiController = new NguoiController();
	public AddKhuPho(String title) {
		super(title);
		addControls();
		addEvents();
	}

//=============================================================
	public void showWindow() {

		this.setSize(750, 550);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

//=============================================================
	JTextField txtMaKhuPho, txtName, txtMaHoDan, txtSoNha, txtMaNguoi, txtHoVaTen, txtTuoi, txtNamSinh, txtNgheNghiep;
	JButton btnNhapNgTiep, btnNhapHoDanTiep, btnHoanThanh, btnThoat;
	JTextPane textPane;
	JTextArea textArea;

	private void addControls() {
		Container con = getContentPane();
		con.setLayout(new BorderLayout());

		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new BoxLayout(pnNorth, BoxLayout.Y_AXIS));

		JPanel pnDetail = new JPanel();
		pnDetail.setLayout(new BoxLayout(pnDetail, BoxLayout.X_AXIS));

		JPanel pnLeft = new JPanel();
		pnLeft.setLayout(new BoxLayout(pnLeft, BoxLayout.Y_AXIS));

		JPanel pnRight = new JPanel();
		pnRight.setLayout(new BoxLayout(pnRight, BoxLayout.Y_AXIS));

		// pnLeft
		JPanel pnMaKhu = new JPanel();
		JLabel lblMaKhu = new JLabel("Mã Khu Phố: ");
		txtMaKhuPho = new JTextField(25);
		pnMaKhu.add(lblMaKhu);
		pnMaKhu.add(txtMaKhuPho);
		pnLeft.add(pnMaKhu);

		JPanel pnName = new JPanel();
		JLabel lblName = new JLabel("Tên Khu Phố: ");
		txtName = new JTextField(25);
		pnName.add(lblName);
		pnName.add(txtName);
		pnLeft.add(pnName);

		JPanel pnHoDan = new JPanel();
		pnHoDan.setLayout(new BoxLayout(pnHoDan, BoxLayout.Y_AXIS));

		JPanel pnDan = new JPanel();
		JLabel lblDan = new JLabel("Mã hộ dân: ");
		txtMaHoDan = new JTextField(25);
		pnDan.add(lblDan);
		pnDan.add(txtMaHoDan);
		pnHoDan.add(pnDan);

		JPanel pnNha = new JPanel();
		JLabel lblNha = new JLabel("Số Nhà: ");
		txtSoNha = new JTextField(25);
		pnNha.add(lblNha);
		pnNha.add(txtSoNha);
		pnHoDan.add(pnNha);

		JPanel pnNg = new JPanel();
		pnNg.setLayout(new BoxLayout(pnNg, BoxLayout.Y_AXIS));

		JPanel pn1 = new JPanel();
		JLabel lblMaNguoi = new JLabel("Mã người: ");
		txtMaNguoi = new JTextField(25);
		pn1.add(lblMaNguoi);
		pn1.add(txtMaNguoi);
		pnNg.add(pn1);

		JPanel pn2 = new JPanel();
		JLabel lblTen = new JLabel("Họ và tên: ");
		txtHoVaTen = new JTextField(25);
		pn2.add(lblTen);
		pn2.add(txtHoVaTen);
		pnNg.add(pn2);

		JPanel pn3 = new JPanel();
		JLabel lblTuoi = new JLabel("Tuổi: ");
		txtTuoi = new JTextField(25);
		pn3.add(lblTuoi);
		pn3.add(txtTuoi);
		pnNg.add(pn3);

		JPanel pn4 = new JPanel();
		JLabel lblN = new JLabel("Năm Sinh: ");
		txtNamSinh = new JTextField(25);
		pn4.add(lblN);
		pn4.add(txtNamSinh);
		pnNg.add(pn4);

		JPanel pn5 = new JPanel();
		JLabel lblNghe = new JLabel("Nghề nghiệp: ");
		txtNgheNghiep = new JTextField(25);
		pn5.add(lblNghe);
		pn5.add(txtNgheNghiep);
		pnNg.add(pn5);

		JPanel pnLuuNguoi = new JPanel();
		pnLuuNguoi.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnNhapNgTiep = new JButton("Tiếp tục nhập thành viên");
		pnLuuNguoi.add(btnNhapNgTiep);
		pnNg.add(pnLuuNguoi);

		pnNg.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.YELLOW), "Thông tin thành viên"));

		JPanel pnLuuHoDan = new JPanel();
		pnLuuHoDan.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnNhapHoDanTiep = new JButton("Tiếp tục nhập hộ dân");
		pnLuuHoDan.add(btnNhapHoDanTiep);

		pnHoDan.add(pnNg);

		pnHoDan.add(pnLuuHoDan);

		lblMaKhu.setPreferredSize(lblName.getPreferredSize());
		lblNha.setPreferredSize(lblName.getPreferredSize());
		lblDan.setPreferredSize(lblName.getPreferredSize());
		lblMaNguoi.setPreferredSize(lblName.getPreferredSize());
		lblTuoi.setPreferredSize(lblName.getPreferredSize());
		lblN.setPreferredSize(lblName.getPreferredSize());
		lblTen.setPreferredSize(lblName.getPreferredSize());
		lblNghe.setPreferredSize(lblName.getPreferredSize());

		pnHoDan.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.GREEN), "Thông tin hộ dân"));
		pnLeft.add(pnHoDan);

		JPanel pnAddButton = new JPanel();
		btnHoanThanh = new JButton("Thêm khu phố");
		btnThoat = new JButton("Thoát");
		pnAddButton.add(btnHoanThanh);
		pnAddButton.add(btnThoat);
		pnLeft.add(pnAddButton);

		pnDetail.add(pnLeft);

		// pnRight
		JPanel pnTT = new JPanel();
		pnRight.add(pnTT);
		pnTT.setLayout(new BorderLayout(20, 20));

		// textPane = new JTextPane();
		textArea = new JTextArea();
		pnTT.add(textArea);

		pnRight.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Thông tin vừa nhập"));
		pnDetail.add(pnRight);
		//

		TitledBorder bdrDetailInfor = new TitledBorder(BorderFactory.createLineBorder(Color.RED), "Thêm khu phố");
		pnNorth.setBorder(bdrDetailInfor);
		pnNorth.add(pnDetail, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(70, 130, 180));
		panel.setBounds(0, 500, 997, 30);
		JLabel lblFromSeasideTo = new JLabel("Lập trình nâng cao - Quản lý khu phố - Hoàng Tuấn Hiệp - 20182504");
		lblFromSeasideTo.setForeground(new Color(240, 255, 255));
		lblFromSeasideTo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		panel.add(lblFromSeasideTo);

		pnNorth.add(panel);

		con.add(pnNorth, BorderLayout.NORTH);

	}

//=================================================================
	Connection conn2 = ConnectDB.getConnection();
	PreparedStatement preStm = null;

	Nguoi nguoi = new Nguoi();
	ArrayList<Nguoi> listNguoi = new ArrayList<>();
	HoDan hoDan = new HoDan();
	ArrayList<HoDan> listHoDan = new ArrayList<>();
	KhuPho khuPho = new KhuPho();

	private void addEvents() {
		btnNhapNgTiep.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtMaHoDan.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Bạn cần nhập mã hộ dân", "Cảnh báo",
							JOptionPane.ERROR_MESSAGE);
				} else {

					nguoi.setMaNguoi(txtMaNguoi.getText() + "");
					nguoi.setHoVaTen(txtHoVaTen.getText() + "");
					nguoi.setTuoi(Integer.parseInt(txtTuoi.getText()));
					nguoi.setNamSinh(Integer.parseInt(txtNamSinh.getText()));
					nguoi.setNgheNghiep(txtNgheNghiep.getText() + "");
					nguoi.setMaHoDan(txtMaHoDan.getText() + "");
					System.out.println(nguoi);
					listNguoi.add(nguoi);
//				for(Nguoi n : listNguoi) {
//					nguoi.setMaHoDan(txtMaHoDan.getText());
//				}

					if (nguoiController.addNguoi(nguoi)) {
						System.out.println("insert nguoi successfully");
					} else {
						System.out.println("error!!!");
					}

					StringBuilder text = new StringBuilder();
					text.append("Mã khu phố: " + txtMaKhuPho.getText());
					text.append("\nTên khu phố: " + txtName.getText());
					text.append("\nMã hộ dân: " + txtMaHoDan.getText());
					text.append("\nSố nhà: " + txtSoNha.getText());
					text.append("\nMã người: " + txtMaNguoi.getText());
					text.append("\nHọ và tên: " + txtHoVaTen.getText());
					text.append("\nTuổi: " + Integer.parseInt(txtTuoi.getText()));
					text.append("\nNăm sinh: " + Integer.parseInt(txtNamSinh.getText()));
					text.append("\nNghề nghiệp: " + txtNgheNghiep.getText());
					textArea.setText(text.toString());

					txtMaNguoi.setText("");
					txtHoVaTen.setText("");
					txtTuoi.setText("");
					txtNamSinh.setText("");
					txtNgheNghiep.setText("");
				}
			}
		});

		btnNhapHoDanTiep.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtMaKhuPho.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Bạn cần nhập mã khu phố", "Cảnh báo",
							JOptionPane.ERROR_MESSAGE);
				} else {

					hoDan.setMaHoDan(txtMaHoDan.getText() + "");
					hoDan.setSoNha(Integer.parseInt(txtSoNha.getText()));
					hoDan.setSoThanhVien(listNguoi.size());
					hoDan.setMaKhuPho(txtMaKhuPho.getText() + "");
					System.out.println(hoDan);
					listHoDan.add(hoDan);
//				for(HoDan hd : listHoDan) {
//					hd.setMaKhuPho(txtMaKhuPho.getText());
//				}
					listNguoi.remove(listNguoi);

					if (hdController.addHoDan(hoDan)) {
						System.out.println("insert hoDan successfully");
					} else {
						System.out.println("error!!!");
					}

					StringBuilder text = new StringBuilder();
					text.append("Mã hộ dân: " + txtMaHoDan.getText());
					text.append("\nSố nhà: " + txtSoNha.getText());
					textArea.setText(text.toString());
					txtMaHoDan.setText("");
					txtSoNha.setText("");
				}
			}

		});

		btnHoanThanh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				khuPho = new KhuPho(txtMaKhuPho.getText(), txtName.getText());

				System.out.println(khuPho);

				if (kpController.addKhuPho(khuPho)) {
					System.out.println("insert khuPho successfully");
				} else {
					System.out.println("error!!!");
				}

				StringBuilder text = new StringBuilder();
				text.append("Mã khu phố: " + txtMaKhuPho.getText());
				text.append("\nTên khu phố: " + txtName.getText());

				txtMaKhuPho.setText("");
				txtName.setText("");
				textArea.setText(text.toString());

				listNguoi.removeAll(listNguoi);
				listHoDan.removeAll(listHoDan);
				JOptionPane.showMessageDialog(null, "Nhập khu phố mới thành công", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);

			}
		});

		btnThoat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddKhuPho.this.dispose();

			}
		});
	}
}
