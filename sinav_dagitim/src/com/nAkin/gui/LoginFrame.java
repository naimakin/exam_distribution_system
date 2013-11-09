package com.nAkin.gui;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import com.nAkin.Database.DatabaseProses;

public class LoginFrame {

	private JFrame frame;
	private JTextField username;
	private JTextField password;
	JButton giris;
	
	innerClass listener = new innerClass();
	DatabaseProses database = new DatabaseProses();
	
	public static void main(String[] args) {
		new LoginFrame();
	}

	public LoginFrame() {
		
		sistem_temasi();
		
		frame = new JFrame();
		frame.setBounds(450, 250, 308, 202);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblKullancAd = new JLabel("Kullanıcı Adı :");
		
		JLabel lblParola = new JLabel("Parola : ");
		
		username = new JTextField();
		username.setColumns(10);
		
		password = new JTextField();
		password.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("NAİM AKIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 13));
		
		giris = new JButton("Giriş");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblKullancAd)
									.addGap(18)
									.addComponent(username, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblParola, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(giris, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(password, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))))
							.addContainerGap(39, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKullancAd)
						.addComponent(username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblParola)
						.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(giris)
					.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
		
		giris.addActionListener(listener);
		
		frame.setVisible(true);
	}
	
	class innerClass implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == giris){
				String isim = "No";
				if (password.getText().equals("") | username.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Boş Veri Var Tekrar Deneyiniz.");
				else
					isim = database.uyeMi(password.getText(), username.getText());
				if (!isim.equals("No")){
					new mainFrame(isim);
					frame.setVisible(false);
				}
				else{
					JOptionPane.showMessageDialog(null, "Yanlis Giris Yaptiniz.Tekrar Deneyiniz");
				}
			}
		}
		
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
