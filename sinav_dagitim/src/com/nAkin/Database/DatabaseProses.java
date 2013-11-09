package com.nAkin.Database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.nAkin.DatabaseModel.tDers;
import com.nAkin.DatabaseModel.tGozetmen;
import com.nAkin.DatabaseModel.tSinav;
import com.nAkin.DatabaseModel.tSinif;

public class DatabaseProses {
	
	private static String driver = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost/sinavdagitim";
	private static String username = "root";
	private static String password = "12345";
	
	Connection connection;
	Statement statement;
	ResultSet result;
	
	private boolean Connect(){
		try
		{
			Class.forName(driver);
			connection = DriverManager.getConnection(URL,username,password);
			statement = connection.createStatement();
		}
		catch (ClassNotFoundException e) 
		{
			JOptionPane.showMessageDialog(null, "Hata (1) : "+e.getMessage());
			return false;
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Hata (2) : "+e.getMessage());
			return false;
		}
		return true;
	}
	
	public ArrayList<tSinif> SiniflariGetir(){
		ArrayList<tSinif> siniflar = new ArrayList<>();
		if (Connect()){
			String SQL = "SELECT * FROM tSinif";
			try{
				result = statement.executeQuery(SQL);
				tSinif sinif;
				while (result.next()){
					sinif = new tSinif();
					sinif.setSinifNo(result.getString("salonKod"));
					sinif.setSinifLimit(result.getInt("salonLimit"));
					
					siniflar.add(sinif);
					sinif = null;
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Hata(3) : "+e.getMessage());
			}
		}
		return siniflar;
	}

	public ArrayList<tDers> DersleriGetir(){
		ArrayList<tDers> dersler = new ArrayList<>();
		if (Connect()){
			String SQL = "SELECT * FROM tDers";
			try{
				result = statement.executeQuery(SQL);
				tDers ders;
				while (result.next()){
					ders = new tDers();
					ders.setDersKod(result.getInt("dersKod"));
					ders.setDersAd(result.getString("dersAd"));
					ders.setDersHocasi(result.getString("dersHocasi"));
					ders.setDersSinif(result.getInt("dersSinif"));
					ders.setDersOgrencisi(result.getInt("dersOgrenciSayisi"));
					
					dersler.add(ders);
					ders = null;
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Hata(4) : "+e.getMessage());
			}
		}
		return dersler;
	}
	
	public ArrayList<tGozetmen> GozetmenleriGetir(){
		ArrayList<tGozetmen> gozetmenler = new ArrayList<>();
		if (Connect()){
			String SQL = "SELECT * FROM tGozetmen";
			try{
				result = statement.executeQuery(SQL);
				tGozetmen gozetmen;
				while (result.next()){
					gozetmen = new tGozetmen();
					gozetmen.setGozetmenID(result.getInt("gozetmenID"));
					gozetmen.setGozetmenAd(result.getString("gozetmenAd"));
					
					gozetmenler.add(gozetmen);
					gozetmen = null;
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Hata(5) : "+e.getMessage());
			}
		}
		return gozetmenler;
	}
	
	public ArrayList<tSinav> SinavlariGetir(){
		ArrayList<tSinav> sinavlar = new ArrayList<>();
		if(Connect()){
			String SQL = "SELECt * FROM tSinav";
			try{
				result = statement.executeQuery(SQL);
				tSinav sinav;
				while(result.next()){
					sinav = new tSinav();
					sinav.setDersKod(result.getInt("dersKod"));
					sinav.setSinifKod1(result.getString("sinifKod1"));
					sinav.setSinifKod2(result.getString("sinifKod2"));
					sinav.setGozetmen1ID(result.getInt("gozetmen1ID"));
					sinav.setGozetmen2ID(result.getInt("gozetmen2ID"));
					sinav.setHocaAd(result.getString("hocaAd"));
					sinav.setTarih(result.getString("tarih"));
					sinav.setSaat(result.getString("saat"));
					sinav.setDersSinif(result.getInt("dersSinifi"));
					
					sinavlar.add(sinav);
					sinav = null;
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Hata(6) : "+e.getMessage());
			}
		}
		return sinavlar;
	}

	public boolean SinavEkle(tSinav sinav){
		boolean eklendiMi = false;
		if (Connect()){
			String SQL = "INSERT INTO tSinav(dersKod,sinifKod1,sinifKod2,gozetmen1ID,gozetmen2ID,hocaAd,tarih,saat,dersSinifi) "
					+ "VALUES("+sinav.getDersKod()+",'"+sinav.getSinifKod1()+"','"+sinav.getSinifKod2()+"',"
					+ sinav.getGozetmen1ID()+","+sinav.getGozetmen2ID()+",'"+sinav.getHocaAd()+"','"+sinav.getTarih()+"','"
							+ ""+sinav.getSaat()+"',"+sinav.getDersSinif()+")";
			try{
				statement.executeUpdate(SQL);
				eklendiMi = true;
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Hata(6) : "+e.getMessage());
			}
		}
		return eklendiMi;
	}
	
	public String uyeMi(String password, String username){
		String isim = "No";
		if (Connect()){
			String SQL = "SELECT * FROM tUser";
			try{
				ResultSet result = statement.executeQuery(SQL);
				while (result.next()){
					if (result.getString("username").equals(username) && result.getString("password").equals(password)){
						isim = result.getString("name");
						break;
					}
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Hata (7) : "+e.getMessage());
			}
		}
		return isim;
	}
}
