package com.hoanghiep.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.hoanghiep.controllers.KhuPhoController;
import com.hoanghiep.models.KhuPho;
import com.hoanghiep.utils.ConnectDB;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Main extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable tblKhuPho;
	PreparedStatement preStm = null;
	Connection conn = ConnectDB.getConnection();
	KhuPhoController kpController = new KhuPhoController();
	/**
	 * Launch the application.
	 */
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Main mainWindow = new Main();
//					mainWindow.showAllKhuPho();
//					mainWindow.setVisible(true);
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	public Main() {
		super();
		addControls();
		showAllKhuPho();
	}
	public void showWindow() {
		this.setBounds(50, 50, 1015, 574);;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}
//========================================================================
	ArrayList<KhuPho> dsKP = new ArrayList<>();
	DefaultTableModel dtmTable;
	public void showAllKhuPho() {
		KhuPhoController khuPhoController = new KhuPhoController();
		dsKP = khuPhoController.getAllKhuPho();
		
		dtmTable.setRowCount(0);
		for(KhuPho nxb : dsKP) {
			
			Vector<Object> vec = new Vector<>();
			vec.add(nxb.getMaKhuPho());
			vec.add(nxb.getTenKhuPho());
			dtmTable.addRow(vec);
		}
		
	}
	/**
	 * Create the frame.
	 */
	private void addControls() {
		setTitle("Ph???n m???m qu???n l?? khu ph???");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1015, 574);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(70, 130, 180));
		panel.setBounds(0, 500, 997, 30);
		contentPane.add(panel);
		
		JLabel lblFromSeasideTo = new JLabel("L???p tr??nh n??ng cao - Qu???n l?? khu ph??? - Ho??ng Tu???n Hi???p - 20182504");
		lblFromSeasideTo.setForeground(new Color(240, 255, 255));
		lblFromSeasideTo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		panel.add(lblFromSeasideTo);
		
		JLabel lblNhpMKhu = new JLabel("Nh???p m?? khu ph??? ????? th??m s???a ho???c x??a");
		lblNhpMKhu.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNhpMKhu.setBounds(22, 47, 308, 30);
		contentPane.add(lblNhpMKhu);
		
		textField = new JTextField();
		textField.setBounds(326, 49, 200, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("S???a");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_2.setBounds(540, 41, 109, 40);
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String ma = textField.getText();
				if(ma == null) {
					JOptionPane.showMessageDialog(null, "B???n c???n nh???p m?? khu ph???","L???i", JOptionPane.ERROR_MESSAGE);

				} else {
				EditKhuPho kp = new EditKhuPho("Ch???nh s???a th??ng tin khu ph???", ma);
				kp.showWindow();
				}
			}
		});
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("X??a");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_3.setBounds(666, 41, 104, 40);
		btnNewButton_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
					
//					String ma = tblKhuPho.getValueAt(tblKhuPho.getSelectedRow(), 0) + "";
//					
//					try {	
//						String sql = "delete from khupho where maKhuPho = ?";
//						preStm = conn.prepareStatement(sql);
//						preStm.setString(1, ma);	
//						ResultSet rs = preStm.executeQuery();
//						if(rs != null) {
//							showAllKhuPho();
//						}
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
					
					String ma = textField.getText();
//					try {
//						String sql = "delete from khupho where maKhuPho = ?";
//						
//						preStm = conn.prepareStatement(sql);
//						preStm.setString(1, ma);
//						int rs = preStm.executeUpdate();
//						if(rs >= 0) {
//							showAllKhuPho();
//						}
//					} catch(Exception e) {
//						e.printStackTrace();
//					}
					
				boolean flag = kpController.deleteKhuPho(ma);
				if(flag) {
					showAllKhuPho();
				} else {
					JOptionPane.showMessageDialog(null, "Kh??ng th??? x??a khu ph???", "L???i", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Xem");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_4.setBounds(782, 41, 98, 40);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("L??m m???i");
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_5.setBounds(890, 41, 107, 40);
		btnNewButton_5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
					showAllKhuPho();
			}
		});
		contentPane.add(btnNewButton_5);
		
		JButton btnThm = new JButton("Th??m Khu Ph???");
		btnThm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddKhuPho kp = new AddKhuPho("Th??m Khu Ph???");
				kp.showWindow();
			}
		});
		btnThm.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnThm.setBounds(22, 103, 176, 30);
		contentPane.add(btnThm);
		
		JPanel pnCenter = new JPanel();
		pnCenter.setBounds(32, 143, 900, 350);
		pnCenter.setLayout(new BorderLayout());
		dtmTable = new DefaultTableModel();
		dtmTable.addColumn("Ma?? Khu Ph???");
		dtmTable.addColumn("T??n Khu Ph???");
		tblKhuPho = new JTable(dtmTable);
		JScrollPane sc = new JScrollPane(tblKhuPho, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenter.add(sc, BorderLayout.CENTER);

		TitledBorder bdrTable = new TitledBorder(BorderFactory.createLineBorder(Color.BLUE),
				"Danh sa??ch khu ph???");
		pnCenter.setBorder(bdrTable);
		contentPane.add(pnCenter);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 997, 36);
		contentPane.add(menuBar);
		
		JMenu mnNavigation = new JMenu("Navigate");
		mnNavigation.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnNavigation);
		
		JMenuItem mntmThmKhuPh = new JMenuItem("Th??m khu ph???");
		mntmThmKhuPh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnNavigation.add(mntmThmKhuPh);
		
		JMenu mnAdmin = new JMenu("Admin");
		mnAdmin.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnAdmin);
		
		JMenuItem mntmHongHip = new JMenuItem("Ho??ng Hi???p");
		mntmHongHip.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmHongHip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Sinh vi??n: Ho??ng Tu???n Hi???p - 20182504", "B??i cu???i k??? m??n l???p tr??nh n??ng cao", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		mnAdmin.add(mntmHongHip);
		
		JMenu mnExit = new JMenu("Exit ");
		mnExit.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnExit);
		
		JMenuItem mntmThot = new JMenuItem("Tho??t");
		mntmThot.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmThot.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ConnectDB.shutDownConnection();
				System.exit(0);
				
			}
		});
		mnExit.add(mntmThot);
		
		
	}
}
