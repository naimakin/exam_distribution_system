package com.nAkin.CreatePlan;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.nAkin.Database.DatabaseProses;
import com.nAkin.DatabaseModel.Gun;
import com.nAkin.DatabaseModel.tDers;
import com.nAkin.DatabaseModel.tGozetmen;
import com.nAkin.DatabaseModel.tSinav;
import com.nAkin.DatabaseModel.tSinif;

public class SinavProgramiOlustur {

	DatabaseProses database = new DatabaseProses();
	Gun pazartesi, sali, carsamba, persembe, cuma;
	String secilenGun="", secilenAy="", secilenYil="";
	int [] aylar = {31,28,31,30,31,30,31,31,30,31,30,31};
	
	ArrayList<tSinif> siniflar = database.SiniflariGetir();
	ArrayList<tDers> dersler = database.DersleriGetir();
	ArrayList<tGozetmen> gozetmenler = database.GozetmenleriGetir();

	public SinavProgramiOlustur(){

	}

	public SinavProgramiOlustur(String SecilenTarih, String SinavTur, JTable table){
		secilenGun = SecilenTarih.substring(0,SecilenTarih.indexOf('.'));
		String kalan = SecilenTarih.substring(SecilenTarih.indexOf('.')+1, SecilenTarih.length());
		secilenAy = kalan.substring(0, kalan.indexOf('.'));
		secilenYil = kalan.substring(kalan.indexOf('.')+1, kalan.length());

		ArrayList<tDers> allClassLessons = dersleriHarmanla(dersler);
		tSinav sinav;

		if (SinavTur.equals("Vize Sinavi")){
			VizeGunleriOlustur(secilenGun);
		}
		if (SinavTur.equals("Final Sinavi")){
			FinalGunleriOlustur(secilenGun);
		}
		int i = 0;

		for (tDers ders : allClassLessons){
			sinav = new tSinav();

			DerseSinavOlustur(ders, sinav);
			if (i == 0){
				GuneSinavYerlestir(pazartesi, sinav);
				i++;
			}
			else if (i == 1){
				GuneSinavYerlestir(sali, sinav);
				i++;
			}
			else if (i == 2){
				GuneSinavYerlestir(carsamba, sinav);
				i++;
			}
			else if (i == 3){
				GuneSinavYerlestir(persembe, sinav);
				i++;
			}
			else if (i == 4){
				GuneSinavYerlestir(cuma, sinav);
				i++;
			}
			else{
				i = 0;
				GuneSinavYerlestir(pazartesi, sinav);
				i++;
			}
			sinav = null;
		}
		tGozetmen gozetmen1 = null, gozetmen2 = null;
		for (;;){
			if (gozetmenler.size() > 0){
				gozetmen1 = gozetmenler.get(0);
				gozetmenler.remove(0);
			}
			if (gozetmenler.size() > 0){
				gozetmen2 = gozetmenler.get(0);
				gozetmenler.remove(0);
			}
			if (gozetmenler.size() == 0)
				break;
			SinavaGozetmenEkle(pazartesi, gozetmen1, gozetmen2);
			SinavaGozetmenEkle(sali, gozetmen1, gozetmen2);
			SinavaGozetmenEkle(carsamba, gozetmen1, gozetmen2);
			SinavaGozetmenEkle(persembe, gozetmen1, gozetmen2);
			SinavaGozetmenEkle(cuma, gozetmen1, gozetmen2);

			gozetmen1 = null;
			gozetmen2 = null;
		}
		SinavinSinifiniBelirle(pazartesi.sinav1, dersler, siniflar);
		SinavinSinifiniBelirle(pazartesi.sinav2, allClassLessons, siniflar);
		SinavinSinifiniBelirle(pazartesi.sinav3, allClassLessons, siniflar);
		SinavinSinifiniBelirle(pazartesi.sinav4, allClassLessons, siniflar);

		SinavinSinifiniBelirle(sali.sinav1, allClassLessons, siniflar);
		SinavinSinifiniBelirle(sali.sinav2, allClassLessons, siniflar);
		SinavinSinifiniBelirle(sali.sinav3, allClassLessons, siniflar);
		SinavinSinifiniBelirle(sali.sinav4, allClassLessons, siniflar);

		SinavinSinifiniBelirle(carsamba.sinav1, allClassLessons, siniflar);
		SinavinSinifiniBelirle(carsamba.sinav2, allClassLessons, siniflar);
		SinavinSinifiniBelirle(carsamba.sinav3, allClassLessons, siniflar);
		SinavinSinifiniBelirle(carsamba.sinav4, allClassLessons, siniflar);

		SinavinSinifiniBelirle(persembe.sinav1, allClassLessons, siniflar);
		SinavinSinifiniBelirle(persembe.sinav2, allClassLessons, siniflar);
		SinavinSinifiniBelirle(persembe.sinav3, allClassLessons, siniflar);
		SinavinSinifiniBelirle(persembe.sinav4, allClassLessons, siniflar);

		SinavinSinifiniBelirle(cuma.sinav1, allClassLessons, siniflar);
		SinavinSinifiniBelirle(cuma.sinav2, allClassLessons, siniflar);
		SinavinSinifiniBelirle(cuma.sinav3, allClassLessons, siniflar);
		SinavinSinifiniBelirle(cuma.sinav4, allClassLessons, siniflar);

		String Basari = "", Hata = "";
		if (database.SinavEkle(pazartesi.sinav1) & database.SinavEkle(pazartesi.sinav2) & database.SinavEkle(pazartesi.sinav3) &database.SinavEkle(pazartesi.sinav4))
			Basari = "Pazartesi Kayıtları Başarılı Şekilde Eklendi\n";
		else
			Hata = "Pazartesi Sınavları Eklenemedi\n";
		if (database.SinavEkle(sali.sinav1) & database.SinavEkle(sali.sinav2) & database.SinavEkle(sali.sinav3) &database.SinavEkle(sali.sinav4))
			Basari = Basari+"Salı Kayıtları Başarılı Şekilde Eklendi\n";
		else
			Hata = Hata+"Salı Sınavları Eklenemedi\n";	
		if (database.SinavEkle(carsamba.sinav1) & database.SinavEkle(carsamba.sinav2) & database.SinavEkle(carsamba.sinav3) &database.SinavEkle(carsamba.sinav4))
			Basari = Basari+"Çarşamba Kayıtları Başarılı Şekilde Eklendi\n";
		else
			Hata = Hata+"Çarşamba Sınavları Eklenemedi\n";
		if (database.SinavEkle(persembe.sinav1) & database.SinavEkle(persembe.sinav2) & database.SinavEkle(persembe.sinav3) &database.SinavEkle(persembe.sinav4))
			Basari = Basari+"Perşembe Kayıtları Başarılı Şekilde Eklendi\n";
		else
			Hata = Hata+"Perşembe Sınavları Eklenemedi\n";	
		if (database.SinavEkle(cuma.sinav1) & database.SinavEkle(cuma.sinav2) & database.SinavEkle(cuma.sinav3) &database.SinavEkle(cuma.sinav4))
			Basari = Basari+"Cuma Kayıtları Başarılı Şekilde Eklendi\n";
		else
			Hata = Hata+"Cuma Sınavları Eklenemedi\n";
		JOptionPane.showMessageDialog(null, "Basarili Islemler\n"+Basari+"\n\nHatali Islemler\n"+Hata);


		SinavlariTabloyaBas(table);
	}

	private void SinavinSinifiniBelirle(tSinav sinav, ArrayList<tDers> allClassLessons, ArrayList<tSinif> siniflar){
		int dersinOgrenciSayisi = 0;
		for (tDers ders : allClassLessons){
			if (ders.getDersKod() == sinav.getDersKod())
				dersinOgrenciSayisi = ders.getDersOgrencisi();
		}
		int i = 0;
		try{
			for (tSinif sinif : siniflar){
				int aciktaKalanOgrenciSayisi = dersinOgrenciSayisi - sinif.getSinifLimit();
				/* 
				 * Negatif ise sinif limiti fazla geldi.Pozitif ise ogrenci sayisi fazla geldi.
				 * 
				 * Aradaki fark +15 ile -15 arasında ise o sinifa sinav atanacak.
				 * 
				 * */
				if (aciktaKalanOgrenciSayisi >= -15 && aciktaKalanOgrenciSayisi <= 15){
					sinav.setSinifKod1(sinif.getSinifNo());
					sinav.setSinifKod2("---");
				}
				else if (aciktaKalanOgrenciSayisi > 15){
					sinav.setSinifKod1(sinif.getSinifNo());
					siniflar.remove(i);
					sinav.setSinifKod2(EnKucukSinifiGetir(siniflar).getSinifNo());
					break;
				}
				else{
					sinav.setSinifKod1(EnKucukSinifiGetir(siniflar).getSinifNo());
					sinav.setSinifKod2("---");
					break;
				}
				i++;
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private tSinif EnKucukSinifiGetir(ArrayList<tSinif> siniflar){
		tSinif enKucuk = null;
		int limit = siniflar.get(0).getSinifLimit();
		for (int i = 0; i < siniflar.size(); i++){
			if (siniflar.get(i).getSinifLimit() <= limit){
				limit = siniflar.get(i).getSinifLimit();
				enKucuk = siniflar.get(i);
			}
		}
		return enKucuk;
	}

	private void SinavaGozetmenEkle(Gun gun, tGozetmen gozetmen1, tGozetmen gozetmen2){
		if (gun.sinav1.getGozetmen1ID() == 0){
			if (gozetmen1 != null)
				gun.sinav1.setGozetmen1ID(gozetmen1.getGozetmenID());
			if (gozetmen2 != null)
				gun.sinav1.setGozetmen2ID(gozetmen2.getGozetmenID());
		}
		else if (gun.sinav2.getGozetmen1ID() == 0){
			if (gozetmen1 != null)
				gun.sinav2.setGozetmen1ID(gozetmen1.getGozetmenID());
			if (gozetmen2 != null)
				gun.sinav2.setGozetmen2ID(gozetmen2.getGozetmenID());
		}
		else if (gun.sinav3.getGozetmen1ID() == 0){
			if (gozetmen1 != null)
				gun.sinav3.setGozetmen1ID(gozetmen1.getGozetmenID());
			if (gozetmen2 != null)
				gun.sinav3.setGozetmen2ID(gozetmen2.getGozetmenID());
		}
		else if (gun.sinav4.getGozetmen1ID() == 0){
			if (gozetmen1 != null)
				gun.sinav4.setGozetmen1ID(gozetmen1.getGozetmenID());
			if (gozetmen2 != null)
				gun.sinav4.setGozetmen2ID(gozetmen2.getGozetmenID());
		}
		else
			JOptionPane.showMessageDialog(null, gun.getGunAd()+" günündeki sinava gözetmen eklenemedi.");
	}

	private ArrayList<tDers> dersleriHarmanla(ArrayList<tDers> Lessons){
		// 1.sınıf - 2.sınıf - 3.sınıf - 4.sınıf olarak dersleri sıralar.
		ArrayList<tDers> FirstClassLessons = new ArrayList<>();
		ArrayList<tDers> SecondClassLessons = new ArrayList<>();
		ArrayList<tDers> ThirdClassLesonns = new ArrayList<>();
		ArrayList<tDers> FourthClassLessons = new ArrayList<>();
		ArrayList<tDers> allClassLessons = new ArrayList<>();

		for (tDers ders : Lessons){
			if (ders.getDersSinif() == 1)
				FirstClassLessons.add(ders);
			if (ders.getDersSinif() == 2)
				SecondClassLessons.add(ders);
			if (ders.getDersSinif() == 3)
				ThirdClassLesonns.add(ders);
			if (ders.getDersSinif() == 4)
				FourthClassLessons.add(ders);
		}
		for (int i=0; i<100; i++){
			if (FirstClassLessons.size() > 0){
				allClassLessons.add(FirstClassLessons.get(0));
				FirstClassLessons.remove(0);
			}
			if (SecondClassLessons.size() > 0){
				allClassLessons.add(SecondClassLessons.get(0));
		 		SecondClassLessons.remove(0);
			}
			if (ThirdClassLesonns.size() > 0){
				allClassLessons.add(ThirdClassLesonns.get(0));
				ThirdClassLesonns.remove(0);
			}
			if (FourthClassLessons.size() > 0){
				allClassLessons.add(FourthClassLessons.get(0));
				FourthClassLessons.remove(0);
			}
		}
		return allClassLessons;
	}

	private void DerseSinavOlustur(tDers ders, tSinav sinav){
		sinav.setDersKod(ders.getDersKod());
		sinav.setDersAd(ders.getDersAd());
		sinav.setHocaAd(ders.getDersHocasi());
		sinav.setDersSinif(ders.getDersSinif());
	}

	private void GuneSinavYerlestir(Gun gun, tSinav sinav){
		if (gun.sinav1 == null){
			gun.sinav1 = sinav;
			gun.sinav1.setSaat("08:00 - 10:00");
			gun.sinav1.setTarih(gun.getGunNo()+"."+gun.getAyNo()+"."+secilenYil);
		}
		else if (gun.sinav2 == null){
			gun.sinav2 = sinav;
			gun.sinav2.setSaat("10:00 - 12:00");
			gun.sinav2.setTarih(gun.getGunNo()+"."+gun.getAyNo()+"."+secilenYil);
		}
		else if (gun.sinav3 == null){
			gun.sinav3 = sinav;
			gun.sinav3.setSaat("13:00 - 15:00");
			gun.sinav3.setTarih(gun.getGunNo()+"."+gun.getAyNo()+"."+secilenYil);
		}
		else if (gun.sinav4 == null){
			gun.sinav4 = sinav;
			gun.sinav4.setSaat("15:00 - 17:00");
			gun.sinav4.setTarih(gun.getGunNo()+"."+gun.getAyNo()+"."+secilenYil);
		}
		else
			JOptionPane.showMessageDialog(null, gun.getGunNo()+" "+gun.getGunAd()+" zamanÄ±na "+sinav.getDersKod()+" kodlu dersin sinavÄ± yerleÅŸtirilemedi.");
	}

	private void VizeGunleriOlustur(String ilkGun){
		int maxGun = aylar[Integer.parseInt(secilenAy)-1];
		int fark = maxGun - Integer.parseInt(ilkGun);
		switch(fark){
		case 0:
			pazartesi = new Gun(Integer.parseInt(ilkGun), Integer.parseInt(secilenAy),  "Pazartesi");
			secilenAy = Integer.parseInt(secilenAy)+1+"";
			if (secilenAy.equals("13"))
				secilenAy = "01";
			sali = new Gun(1, Integer.parseInt(secilenAy), "Sali");
			carsamba = new Gun(2, Integer.parseInt(secilenAy), "Çarşamba");
			persembe = new Gun(3, Integer.parseInt(secilenAy), "Perşembe");
			cuma = new Gun(4, Integer.parseInt(secilenAy), "Cuma");
			break;
		case 1:
			pazartesi = new Gun(Integer.parseInt(ilkGun), Integer.parseInt(secilenAy), "Pazartesi");
			sali = new Gun(Integer.parseInt(ilkGun)+1, Integer.parseInt(secilenAy), "Sali");
			secilenAy = Integer.parseInt(secilenAy)+1+"";
			if (secilenAy.equals("13"))
				secilenAy = "01";
			carsamba = new Gun(1, Integer.parseInt(secilenAy), "Çarşamba");
			persembe = new Gun(2, Integer.parseInt(secilenAy), "Perşembe");
			cuma = new Gun(3, Integer.parseInt(secilenAy), "Cuma");
			break;
		case 2:
			pazartesi = new Gun(Integer.parseInt(ilkGun), Integer.parseInt(secilenAy), "Pazartesi");
			sali = new Gun(Integer.parseInt(ilkGun)+1, Integer.parseInt(secilenAy), "Sali");
			carsamba = new Gun(Integer.parseInt(ilkGun)+2, Integer.parseInt(secilenAy), "Çarşamba");
			secilenAy = Integer.parseInt(secilenAy)+1+"";
			if (secilenAy.equals("13"))
				secilenAy = "01";
			persembe = new Gun(1, Integer.parseInt(secilenAy), "Perşembe");
			cuma = new Gun(2, Integer.parseInt(secilenAy), "Cuma");
			break;
		case 3:
			pazartesi = new Gun(Integer.parseInt(ilkGun), Integer.parseInt(secilenAy), "Pazartesi");
			sali = new Gun(Integer.parseInt(ilkGun)+1, Integer.parseInt(secilenAy), "Sali");
			carsamba = new Gun(Integer.parseInt(ilkGun)+2, Integer.parseInt(secilenAy), "Çarşamba");
			persembe = new Gun(Integer.parseInt(ilkGun)+3, Integer.parseInt(secilenAy), "Perşembe");
			secilenAy = Integer.parseInt(secilenAy)+1+"";
			if (secilenAy.equals("13"))
				secilenAy = "01";
			cuma = new Gun(1, Integer.parseInt(secilenAy), "Cuma");
			break;
		default:
			pazartesi = new Gun(Integer.parseInt(ilkGun), Integer.parseInt(secilenAy), "Pazartesi");
			sali = new Gun(Integer.parseInt(ilkGun)+1, Integer.parseInt(secilenAy), "Sali");
			carsamba = new Gun(Integer.parseInt(ilkGun)+2, Integer.parseInt(secilenAy), "Çarşamba");
			persembe = new Gun(Integer.parseInt(ilkGun)+3, Integer.parseInt(secilenAy), "Perşembe");
			cuma = new Gun(Integer.parseInt(ilkGun)+4, Integer.parseInt(secilenAy), "Cuma");
			break;
		}
	}

	private void FinalGunleriOlustur(String ilkGun){
		int maxGun = aylar[Integer.parseInt(secilenAy)-1];
		int fark = maxGun - Integer.parseInt(ilkGun);

		switch(fark){
		case 0:
			//1.hafta
			pazartesi = new Gun(Integer.parseInt(ilkGun), Integer.parseInt(secilenAy), "Pazartesi");
			secilenAy = Integer.parseInt(secilenAy)+1+"";
			if (secilenAy.equals("13"))
				secilenAy = "01";
			sali = new Gun(2, Integer.parseInt(secilenAy), "Çarşamba");
			carsamba = new Gun(4, Integer.parseInt(secilenAy), "Cuma");
			//2.hafta
			persembe = new Gun(7, Integer.parseInt(secilenAy), "Pazartesi");
			cuma = new Gun(9, Integer.parseInt(secilenAy), "Çarşamba");
			break;
		case 1:
			//1.hafta
			pazartesi = new Gun(Integer.parseInt(ilkGun), Integer.parseInt(secilenAy), "Pazartesi");
			secilenAy = Integer.parseInt(secilenAy)+1+"";
			if (secilenAy.equals("13"))
				secilenAy = "01";
			sali = new Gun(1, Integer.parseInt(secilenAy), "Çarşamba");
			carsamba = new Gun(3, Integer.parseInt(secilenAy), "Cuma");
			//2.hafta
			persembe = new Gun(6, Integer.parseInt(secilenAy), "Pazartesi");
			cuma = new Gun(8, Integer.parseInt(secilenAy), "Çarşamba");
			break;
		case 2:
			//1.hafta
			pazartesi = new Gun(Integer.parseInt(ilkGun), Integer.parseInt(secilenAy), "Pazartesi");
			sali = new Gun(Integer.parseInt(ilkGun)+2, Integer.parseInt(secilenAy), "Çarşamba");
			secilenAy = Integer.parseInt(secilenAy)+1+"";
			if (secilenAy.equals("13"))
				secilenAy = "01";
			carsamba = new Gun(2, Integer.parseInt(secilenAy), "Cuma");
			//2.hafta
			persembe = new Gun(5, Integer.parseInt(secilenAy), "Pazartesi");
			cuma = new Gun(7, Integer.parseInt(secilenAy), "Çarşamba");
			break;
		case 3:
			//1.hafta
			pazartesi = new Gun(Integer.parseInt(ilkGun), Integer.parseInt(secilenAy), "Pazartesi");
			sali = new Gun(Integer.parseInt(ilkGun)+2, Integer.parseInt(secilenAy), "Çarşamba");
			secilenAy = Integer.parseInt(secilenAy)+1+"";
			if (secilenAy.equals("13"))
				secilenAy = "01";
			carsamba = new Gun(1, Integer.parseInt(secilenAy), "Cuma");
			//2.hafta
			persembe = new Gun(4, Integer.parseInt(secilenAy), "Pazartesi");
			cuma = new Gun(6, Integer.parseInt(secilenAy), "Çarşamba");			
			break;
		default:
			//1.hafta
			pazartesi = new Gun(Integer.parseInt(ilkGun), Integer.parseInt(secilenAy), "Pazartesi");
			sali = new Gun(Integer.parseInt(ilkGun)+2, Integer.parseInt(secilenAy), "Çarşamba");
			carsamba = new Gun(Integer.parseInt(ilkGun)+4, Integer.parseInt(secilenAy), "Cuma");
			secilenAy = Integer.parseInt(secilenAy)+1+"";
			if (secilenAy.equals("13"))
				secilenAy = "01";
			//2.hafta
			persembe = new Gun(2, Integer.parseInt(secilenAy), "Pazartesi");
			cuma = new Gun(4, Integer.parseInt(secilenAy), "Çarşamba");			
			break;
		}
	}

	private void SinavlariTabloyaBas(JTable table){
		Object title[] = {"Dersin Kodu","Dersin Adı","Sınav Günü","Sınav Saati","Sınav Salonları","Gözetmenler","Ders Hocası","Dersin Sınıfı"};
		Object data[][] = {
				{
					pazartesi.sinav1.getDersKod(), pazartesi.sinav1.getDersAd(), pazartesi.sinav1.getTarih(), pazartesi.sinav1.getSaat(), 
					pazartesi.sinav1.getSinifKod1()+" - "+pazartesi.sinav1.getSinifKod2(),pazartesi.sinav1.getGozetmen1ID()+" - "+pazartesi.sinav1.getGozetmen2ID(),
					pazartesi.sinav1.getHocaAd(), pazartesi.sinav1.getDersSinif()
				},
				{
					pazartesi.sinav2.getDersKod(), pazartesi.sinav2.getDersAd(), pazartesi.sinav2.getTarih(), pazartesi.sinav2.getSaat(), 
					pazartesi.sinav2.getSinifKod1()+" - "+pazartesi.sinav2.getSinifKod2(),pazartesi.sinav2.getGozetmen1ID()+" - "+pazartesi.sinav2.getGozetmen2ID(),
					pazartesi.sinav2.getHocaAd(), pazartesi.sinav2.getDersSinif()
				},
				{
					pazartesi.sinav3.getDersKod(), pazartesi.sinav3.getDersAd(), pazartesi.sinav3.getTarih(), pazartesi.sinav3.getSaat(), 
					pazartesi.sinav3.getSinifKod1()+" - "+pazartesi.sinav3.getSinifKod2(),pazartesi.sinav3.getGozetmen1ID()+" - "+pazartesi.sinav3.getGozetmen2ID(),
					pazartesi.sinav3.getHocaAd(), pazartesi.sinav3.getDersSinif()
				},
				{
					pazartesi.sinav4.getDersKod(), pazartesi.sinav4.getDersAd(), pazartesi.sinav4.getTarih(), pazartesi.sinav4.getSaat(), 
					pazartesi.sinav4.getSinifKod1()+" - "+pazartesi.sinav4.getSinifKod2(),pazartesi.sinav4.getGozetmen1ID()+" - "+pazartesi.sinav4.getGozetmen2ID(),
					pazartesi.sinav4.getHocaAd(), pazartesi.sinav4.getDersSinif()
				},
				{
					sali.sinav1.getDersKod(), sali.sinav1.getDersAd(), sali.sinav1.getTarih(), sali.sinav1.getSaat(), 
					sali.sinav1.getSinifKod1()+" - "+sali.sinav1.getSinifKod2(),sali.sinav1.getGozetmen1ID()+" - "+sali.sinav1.getGozetmen2ID(),
					sali.sinav1.getHocaAd(), sali.sinav1.getDersSinif()
				},
				{
					sali.sinav2.getDersKod(), sali.sinav2.getDersAd(), sali.sinav2.getTarih(), sali.sinav2.getSaat(), 
					sali.sinav2.getSinifKod1()+" - "+sali.sinav2.getSinifKod2(),sali.sinav2.getGozetmen1ID()+" - "+sali.sinav2.getGozetmen2ID(),
					sali.sinav2.getHocaAd(), sali.sinav2.getDersSinif()
				},
				{
					sali.sinav3.getDersKod(), sali.sinav3.getDersAd(), sali.sinav3.getTarih(), sali.sinav3.getSaat(), 
					sali.sinav3.getSinifKod1()+" - "+sali.sinav3.getSinifKod2(),sali.sinav3.getGozetmen1ID()+" - "+sali.sinav3.getGozetmen2ID(),
					sali.sinav3.getHocaAd(), sali.sinav3.getDersSinif()
				},
				{
					sali.sinav4.getDersKod(), sali.sinav4.getDersAd(), sali.sinav4.getTarih(), sali.sinav4.getSaat(), 
					sali.sinav4.getSinifKod1()+" - "+sali.sinav4.getSinifKod2(),sali.sinav4.getGozetmen1ID()+" - "+sali.sinav4.getGozetmen2ID(),
					sali.sinav4.getHocaAd(), sali.sinav4.getDersSinif()
				},
				{
					carsamba.sinav1.getDersKod(), carsamba.sinav1.getDersAd(), carsamba.sinav1.getTarih(), carsamba.sinav1.getSaat(), 
					carsamba.sinav1.getSinifKod1()+" - "+carsamba.sinav1.getSinifKod2(),carsamba.sinav1.getGozetmen1ID()+" - "+carsamba.sinav1.getGozetmen2ID(),
					carsamba.sinav1.getHocaAd(), carsamba.sinav1.getDersSinif()
				},
				{
					carsamba.sinav2.getDersKod(), carsamba.sinav2.getDersAd(), carsamba.sinav2.getTarih(), carsamba.sinav2.getSaat(), 
					carsamba.sinav2.getSinifKod1()+" - "+carsamba.sinav2.getSinifKod2(),carsamba.sinav2.getGozetmen1ID()+" - "+carsamba.sinav2.getGozetmen2ID(),
					carsamba.sinav2.getHocaAd(), carsamba.sinav2.getDersSinif()
				},
				{
					carsamba.sinav3.getDersKod(), carsamba.sinav3.getDersAd(), carsamba.sinav3.getTarih(), carsamba.sinav3.getSaat(), 
					carsamba.sinav3.getSinifKod1()+" - "+carsamba.sinav3.getSinifKod2(),carsamba.sinav3.getGozetmen1ID()+" - "+carsamba.sinav3.getGozetmen2ID(),
					carsamba.sinav3.getHocaAd(), carsamba.sinav3.getDersSinif()
				},
				{
					carsamba.sinav4.getDersKod(), carsamba.sinav4.getDersAd(), carsamba.sinav4.getTarih(), carsamba.sinav4.getSaat(), 
					carsamba.sinav4.getSinifKod1()+" - "+carsamba.sinav4.getSinifKod2(),carsamba.sinav4.getGozetmen1ID()+" - "+carsamba.sinav4.getGozetmen2ID(),
					carsamba.sinav4.getHocaAd(), carsamba.sinav4.getDersSinif()
				},
				{
					persembe.sinav1.getDersKod(), persembe.sinav1.getDersAd(), persembe.sinav1.getTarih(), persembe.sinav1.getSaat(), 
					persembe.sinav1.getSinifKod1()+" - "+persembe.sinav1.getSinifKod2(),persembe.sinav1.getGozetmen1ID()+" - "+persembe.sinav1.getGozetmen2ID(),
					persembe.sinav1.getHocaAd(), persembe.sinav1.getDersSinif()
				},
				{
					persembe.sinav2.getDersKod(), persembe.sinav2.getDersAd(), persembe.sinav2.getTarih(), persembe.sinav2.getSaat(), 
					persembe.sinav2.getSinifKod1()+" - "+persembe.sinav2.getSinifKod2(),persembe.sinav2.getGozetmen1ID()+" - "+persembe.sinav2.getGozetmen2ID(),
					persembe.sinav2.getHocaAd(), persembe.sinav2.getDersSinif()
				},
				{
					persembe.sinav3.getDersKod(), persembe.sinav3.getDersAd(), persembe.sinav3.getTarih(), persembe.sinav3.getSaat(), 
					persembe.sinav3.getSinifKod1()+" - "+persembe.sinav3.getSinifKod2(),persembe.sinav3.getGozetmen1ID()+" - "+persembe.sinav3.getGozetmen2ID(),
					persembe.sinav3.getHocaAd(), persembe.sinav3.getDersSinif()
				},
				{
					persembe.sinav4.getDersKod(), persembe.sinav4.getDersAd(), persembe.sinav4.getTarih(), persembe.sinav4.getSaat(), 
					persembe.sinav4.getSinifKod1()+" - "+persembe.sinav4.getSinifKod2(),persembe.sinav4.getGozetmen1ID()+" - "+persembe.sinav4.getGozetmen2ID(),
					persembe.sinav4.getHocaAd(), persembe.sinav4.getDersSinif()
				},
				{
					cuma.sinav1.getDersKod(), cuma.sinav1.getDersAd(), cuma.sinav1.getTarih(), cuma.sinav1.getSaat(), 
					cuma.sinav1.getSinifKod1()+" - "+cuma.sinav1.getSinifKod2(),cuma.sinav1.getGozetmen1ID()+" - "+cuma.sinav1.getGozetmen2ID(),
					cuma.sinav1.getHocaAd(), cuma.sinav1.getDersSinif()
				},
				{
					cuma.sinav2.getDersKod(), cuma.sinav2.getDersAd(), cuma.sinav2.getTarih(), cuma.sinav2.getSaat(), 
					cuma.sinav2.getSinifKod1()+" - "+cuma.sinav2.getSinifKod2(),cuma.sinav2.getGozetmen1ID()+" - "+cuma.sinav2.getGozetmen2ID(),
					cuma.sinav2.getHocaAd(), cuma.sinav2.getDersSinif()
				},
				{
					cuma.sinav3.getDersKod(), cuma.sinav3.getDersAd(), cuma.sinav3.getTarih(), cuma.sinav3.getSaat(), 
					cuma.sinav3.getSinifKod1()+" - "+cuma.sinav3.getSinifKod2(),cuma.sinav3.getGozetmen1ID()+" - "+cuma.sinav3.getGozetmen2ID(),
					cuma.sinav3.getHocaAd(), cuma.sinav3.getDersSinif()
				},
				{
					cuma.sinav4.getDersKod(), cuma.sinav4.getDersAd(), cuma.sinav4.getTarih(), cuma.sinav4.getSaat(), 
					cuma.sinav4.getSinifKod1()+" - "+cuma.sinav4.getSinifKod2(),cuma.sinav4.getGozetmen1ID()+" - "+cuma.sinav4.getGozetmen2ID(),
					cuma.sinav4.getHocaAd(), cuma.sinav4.getDersSinif()
				}
		};

		TableModel tableModel = new DefaultTableModel(data, title){
			private static final long serialVersionUID = -5635557101306916025L;

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int column) {
				Class returnValue;
				if ((column >= 0) && (column < getColumnCount())) {
					returnValue = getValueAt(0, column).getClass();
				} else {
					returnValue = Object.class;
				}
				return returnValue;
			}
		};
		table.setModel(tableModel);
	}
}