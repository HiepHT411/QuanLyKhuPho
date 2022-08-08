package com.hoanghiep.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.hoanghiep.controllers.KhuPhoController;
import com.hoanghiep.models.HoDan;
import com.hoanghiep.models.KhuPho;
import com.hoanghiep.models.Nguoi;
import com.hoanghiep.utils.ConnectDB;
import javax.swing.JList;

public class EditKhuPho extends JFrame {
	
	JTextField txtMaKhuPho, txtName, txtMaHoDan, txtSoNha, txtMaNguoi, txtHoVaTen, txtTuoi, txtNamSinh, txtNgheNghiep;
	JButton btnLuuHoDan, btnLuuNguoi, btnHoDanTruoc, btnHoDanSau, btnThanhVienTruoc, btnThanhVienSau;
	JButton btnBack, btnSaveAll;
	DefaultTableModel dtmTable ;
	JTable tblPeople;
	
	String maKhuPho;
	KhuPhoController kpController =new KhuPhoController();
	
	List<HoDan> listHD = new ArrayList<>(); 
	KhuPho kp = new KhuPho();
	List<Nguoi> ListNg = new ArrayList<>();
	
	public EditKhuPho(String title, String ma) {
		super(title);
		this.maKhuPho = ma;
		listHD = kpController.getHoDanVoiMaKhuPho(this.maKhuPho); 
		kp = kpController.getKhuPhoVoiMaKhuPho(this.maKhuPho);
		ListNg = kpController.getNguoiVoiMaHoDan(listHD.get(0).getMaHoDan());
		
		addControls();

		addEvents();
		
		setDanhSachNguoi();

		
	}

	// =================================================================
	
	
	public void showWindow() {
		this.setSize(700, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}
	// =================================================================

	private void setDanhSachNguoi() {
			dtmTable.setRowCount(0);
			for(Nguoi ng : ListNg ) {
				
				Vector<Object> vec = new Vector<>();
				vec.add(ng.getMaNguoi());
				vec.add(ng.getHoVaTen());
				vec.add(ng.getTuoi());
				vec.add(ng.getNamSinh());
				vec.add(ng.getNgheNghiep());
				
				dtmTable.addRow(vec);
			}

	}

//=====================================================================================
	
	private void addControls() {
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		
		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new BorderLayout());
		
		JPanel pnDetail = new JPanel();
		pnDetail.setLayout(new BoxLayout(pnDetail, BoxLayout.X_AXIS));

		JPanel pnLeft = new JPanel();
		pnLeft.setLayout(new BoxLayout(pnLeft, BoxLayout.Y_AXIS));
		
		JPanel pnRight = new JPanel();
		pnRight.setLayout(new BoxLayout(pnRight, BoxLayout.Y_AXIS));

		//pnLeft
		JPanel pnMaKhu = new JPanel();
		JLabel lblMaKhu = new JLabel("Mã Khu Phố: ");
		txtMaKhuPho = new JTextField(25);
		txtMaKhuPho.setText(kp.getMaKhuPho());
		txtMaKhuPho.setEditable(false);
		pnMaKhu.add(lblMaKhu);
		pnMaKhu.add(txtMaKhuPho);
		pnLeft.add(pnMaKhu);

		JPanel pnName = new JPanel();
		JLabel lblName = new JLabel("Tên Khu Phố: ");
		txtName = new JTextField(25);
		txtName.setText(kp.getTenKhuPho());
		pnName.add(lblName);
		pnName.add(txtName);
		pnLeft.add(pnName);

		JPanel pnHoDan = new JPanel();
		pnHoDan.setLayout(new BoxLayout(pnHoDan, BoxLayout.Y_AXIS));
		
		JPanel pnDan = new JPanel();
		JLabel lblDan = new JLabel("Mã hộ dân: ");
		txtMaHoDan = new JTextField(25);
		txtMaHoDan.setText(listHD.get(0).getMaHoDan());
		txtMaHoDan.setEditable(false);
		pnDan.add(lblDan);
		pnDan.add(txtMaHoDan);
		pnHoDan.add(pnDan);

		JPanel pnNha = new JPanel();
		JLabel lblNha = new JLabel("Số Nhà: ");
		txtSoNha = new JTextField(25);
		txtSoNha.setText(String.valueOf(listHD.get(0).getSoNha()));
		pnNha.add(lblNha);
		pnNha.add(txtSoNha);
		pnHoDan.add(pnNha);
		
		JPanel pnLuuHoDan = new JPanel();
		btnLuuHoDan = new JButton("Lưu");
		pnLuuHoDan.add(btnLuuHoDan);
		pnHoDan.add(pnLuuHoDan);
		
		JPanel pnDanButton = new JPanel();
		btnHoDanTruoc = new JButton("Hộ dân trước");
		btnHoDanSau = new JButton("Hộ Dân Sau");
		pnDanButton.add(btnHoDanTruoc);
		pnDanButton.add(btnHoDanSau);
		pnHoDan.add(pnDanButton);

		lblMaKhu.setPreferredSize(lblName.getPreferredSize());
		lblNha.setPreferredSize(lblName.getPreferredSize());
		lblDan.setPreferredSize(lblName.getPreferredSize());
		
		pnHoDan.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.GREEN), "Thông tin hộ dân"));
		pnLeft.add(pnHoDan);
		pnDetail.add(pnLeft);
		
		//pnRight
		JPanel pn1 = new JPanel();
		JLabel lblMaNguoi = new JLabel("Mã người: ");
		txtMaNguoi = new JTextField(25);
		txtMaNguoi.setText(ListNg.get(0).getMaNguoi());
		txtMaNguoi.setEditable(false);
		pn1.add(lblMaNguoi);
		pn1.add(txtMaNguoi);
		pnRight.add(pn1);

		JPanel pn2 = new JPanel();
		JLabel lblTen = new JLabel("Họ và tên: ");
		txtHoVaTen = new JTextField(25);
		txtHoVaTen.setText(ListNg.get(0).getHoVaTen());
		pn2.add(lblTen);
		pn2.add(txtHoVaTen);
		pnRight.add(pn2);
		
		JPanel pn3 = new JPanel();
		JLabel lblTuoi = new JLabel("Tuổi: ");
		txtTuoi = new JTextField(25);
		txtTuoi.setText(String.valueOf(ListNg.get(0).getTuoi()));
		pn3.add(lblTuoi);
		pn3.add(txtTuoi);
		pnRight.add(pn3);
		
		JPanel pn4 = new JPanel();
		JLabel lblN = new JLabel("Năm Sinh: ");
		txtNamSinh = new JTextField(25);
		txtNamSinh.setText(String.valueOf(ListNg.get(0).getNamSinh()));
		pn4.add(lblN);
		pn4.add(txtNamSinh);
		pnRight.add(pn4);
		
		JPanel pn5 = new JPanel();
		JLabel lblNghe = new JLabel("Nghề nghiệp: ");
		txtNgheNghiep = new JTextField(25);
		txtNgheNghiep.setText(ListNg.get(0).getNgheNghiep());
		pn5.add(lblNghe);
		pn5.add(txtNgheNghiep);
		pnRight.add(pn5);
		
		JPanel pnLuuNguoi = new JPanel();
		btnLuuNguoi = new JButton("Lưu");
		pnLuuNguoi.add(btnLuuNguoi);
		pnRight.add(pnLuuNguoi);
		
		JPanel pnNguoiButton = new JPanel();
		btnThanhVienTruoc = new JButton("Thành viên trước");
		btnThanhVienSau = new JButton("Thành viên sau");
		pnNguoiButton.add(btnThanhVienTruoc);
		pnNguoiButton.add(btnThanhVienSau);
		pnRight.add(pnNguoiButton);
		
		lblMaNguoi.setPreferredSize(lblNghe.getPreferredSize());
		lblTuoi.setPreferredSize(lblNghe.getPreferredSize());
		lblN.setPreferredSize(lblNghe.getPreferredSize());
		lblTen.setPreferredSize(lblNghe.getPreferredSize());
		
		pnRight.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Thông tin thành viên"));
		pnDetail.add(pnRight);
		//pnNorth
		TitledBorder bdrDetailInfor = new TitledBorder(BorderFactory.createLineBorder(Color.RED), "Thông tin khu phố");
		pnDetail.setBorder(bdrDetailInfor);
		pnNorth.add(pnDetail, BorderLayout.CENTER);

		//pnCenter
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		dtmTable = new DefaultTableModel();
		dtmTable.addColumn("Mã Người");
		dtmTable.addColumn("Họ và Tên");
		dtmTable.addColumn("Tuổi");
		dtmTable.addColumn("Năm sinh");
		dtmTable.addColumn("Nghề nghiệp");
		tblPeople = new JTable(dtmTable);
		JScrollPane sc = new JScrollPane(tblPeople, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenter.add(sc, BorderLayout.CENTER);

		TitledBorder bdrTable = new TitledBorder(BorderFactory.createLineBorder(Color.YELLOW),
				"Danh sách công dân");
		pnCenter.setBorder(bdrTable);

		JPanel pnSouth = new JPanel();
		pnSouth.setLayout(new BoxLayout(pnSouth, BoxLayout.Y_AXIS));
		JPanel pnSouthButton = new JPanel();
		pnSouthButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnSaveAll = new JButton("Save");
		btnBack = new JButton("Back");
		pnSouthButton.add(btnSaveAll);
		pnSouthButton.add(btnBack);
		
		pnSouth.add(pnSouthButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(70, 130, 180));
		panel.setBounds(0, 500, 997, 30);
		JLabel lblFromSeasideTo = new JLabel("Lập trình nâng cao - Quản lý khu phố - Hoàng Tuấn Hiệp - 20182504");
		lblFromSeasideTo.setForeground(new Color(240, 255, 255));
		lblFromSeasideTo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		panel.add(lblFromSeasideTo);
		
		pnSouth.add(panel);

		con.add(pnNorth, BorderLayout.NORTH);
		con.add(pnCenter, BorderLayout.CENTER);
		con.add(pnSouth, BorderLayout.SOUTH);
	}

//=================================================================
	Connection conn2 = ConnectDB.getConnection();
	PreparedStatement preStm = null;

	private void addEvents() {
		btnSaveAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				
				JOptionPane.showMessageDialog(null, "Sửa khu phố thành công", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
			}
		});

		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//InsertService is = new InsertService();
				EditKhuPho.this.dispose();
			}
		});

		btnLuuHoDan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		btnLuuNguoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String selectedMaNXB = tblPeople.getValueAt(tblPeople.getSelectedRow(), 0) + "";

				try { // thao tác với db cần try catch block
					// String sql = "delete from tblpublisher where ma ='"+selectedMaNXB+"'";
					String sql = "delete from tblpublisher where MaNXB = ?";
					preStm = conn2.prepareStatement(sql);
					preStm.setString(1, selectedMaNXB);
					ResultSet rs = preStm.executeQuery();
					// int ketqua = stm.executeUpdate(sql); //trả về số dòng bị ảnh hưởng,
					// dung statement binh thuong
//					if (rs != null) {
//						showAllPeople();
//					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

}
