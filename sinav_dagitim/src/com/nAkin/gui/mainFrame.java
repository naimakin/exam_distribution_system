package com.nAkin.gui;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import com.nAkin.CreatePlan.SinavProgramiOlustur;

@SuppressWarnings("rawtypes")
public class mainFrame {

	private JFrame frame;
	private JTable table;
	private JButton tarihSec, programOlustur;
	private JComboBox sinavTur;
	
	innerClass listener = new innerClass();
	private JTextField sinavTarih;

	@SuppressWarnings("unchecked")
	public mainFrame(String isim) {

		sistem_temasi();

		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(350, 150, 750, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "S\u0131nav Program\u0131", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "S\u0131nav T\u00FCr\u00FC ve Tarihi", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "S\u0131nav Atama", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
					.addContainerGap())
		);

		JLabel lblSeilenDersinSnav = new JLabel("   Seçilen Dersin Sınav Programını Oluşturmak İçin Tıklayınız");

		programOlustur = new JButton("Sınav Programı Oluştur");
		programOlustur.addActionListener(listener);
		
		JLabel username = new JLabel(isim);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(42)
					.addComponent(lblSeilenDersinSnav)
					.addContainerGap(66, Short.MAX_VALUE))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap(108, Short.MAX_VALUE)
					.addComponent(programOlustur)
					.addGap(138))
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(331, Short.MAX_VALUE)
					.addComponent(username)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSeilenDersinSnav)
					.addGap(18)
					.addComponent(programOlustur)
					.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
					.addComponent(username)
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);

		table = new JTable();
		table.setEnabled(false);
		table.setColumnSelectionAllowed(true);
		table.setToolTipText("");
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, "", "", "", "", "", "", null},
				{null, null, "", null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Dersin Kodu", "Dersin Ad\u0131", "S\u0131nav G\u00FCn\u00FC", "S\u0131nav Saati", "S\u0131nav Salonu", "S\u0131nav G\u00F6zetmenleri", "Dersin Hocasi", "Dersin S\u0131n\u0131f\u0131"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(67);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setPreferredWidth(114);
		table.getColumnModel().getColumn(6).setPreferredWidth(89);

		JScrollPane scroll = new JScrollPane(table);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
						.addContainerGap())
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
				.addComponent(scroll, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
				);
		panel.setLayout(gl_panel);

		JLabel lblSnavTr = new JLabel("Sınav Türü : ");

		sinavTur = new JComboBox();
		sinavTur.setFont(new Font("Tahoma", Font.PLAIN, 11));
		sinavTur.setModel(new DefaultComboBoxModel(new String[] {"Vize Sinavi", "Final Sinavi"}));
		sinavTur.setToolTipText("");
		sinavTur.setMaximumRowCount(2);

		JLabel lblSnavBalangTarihi = new JLabel("Sınavların Baş. Günü : ");

		tarihSec = new JButton("Tarih Seç");
		tarihSec.addActionListener(listener);

		sinavTarih = new JTextField();
		sinavTarih.setText("P.tesi Seçiniz");
		sinavTarih.setHorizontalAlignment(SwingConstants.CENTER);
		sinavTarih.setEnabled(false);
		sinavTarih.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSnavBalangTarihi)
								.addComponent(lblSnavTr))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
												.addComponent(sinavTarih, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(tarihSec, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addComponent(sinavTur, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
												.addContainerGap())
				);
		gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSnavTr)
								.addComponent(sinavTur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(21)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblSnavBalangTarihi)
										.addComponent(sinavTarih, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(tarihSec))
										.addContainerGap(89, Short.MAX_VALUE))
				);
		panel_1.setLayout(gl_panel_1);
		frame.getContentPane().setLayout(groupLayout);
		// lisans istiyorum burada eğerki lisans geçtiyse sinav organizsyon programını çalıştırmıyor
			frame.setVisible(true); // çalışmasnı burası saglar. Vakit geçtiyse buraya gelmesini engelledim.Burada lisans vardı kaldırdım ya demin if koşulu felan vardı hani orası okişte
			//jarı anlatayımmı şimdi ? anlat
	}

	class innerClass implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == tarihSec){
				sinavTarih.setText(new Takvim(null).setPickedDate());
			}
			if (e.getSource() == programOlustur){
				new SinavProgramiOlustur(sinavTarih.getText(), sinavTur.getSelectedItem().toString(), table);
			}
		}
	}
//lisans koydugum metod burası burada sistem saatiyle karşılaştırıyorum eğee kieşitse çalışmasını engelliyorum
	public boolean kayitIste(){
		SimpleDateFormat DayFormat = new SimpleDateFormat("dd");
		SimpleDateFormat MonthFormat = new SimpleDateFormat("MM");
		Date date = new Date();
		String gun = DayFormat.format(date);
		String ay = MonthFormat.format(date);
		//yalnızca bir alttaki satırdada ona göre uyarı verirsen daha güzel olur
		JOptionPane.showMessageDialog(null, "Kullanabileceginiz Son Tarih(Lisansın Geçerli Oldugu Tarih) : 03.08.2013");
		if (ay.equals("08") && gun.equals("03")){//buradakileri uzatman lazım---> 03.08.2013 tarihine kadar lisans ona göre ayarlama yapabilirsin tamam istersen uzat yada kaldır.Kaldırdım zaten
			return false;
		}
		return true;
	}

	public void sistem_temasi()
	{
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}
