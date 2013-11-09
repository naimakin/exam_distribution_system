package com.nAkin.DatabaseModel;

public class tSinav {
	private int DersKod, Gozetmen1ID = 0,Gozetmen2ID = 0, DersSinif;
	private String SinifKod1, SinifKod2, HocaAd, Tarih, Saat, DersAd;
	
	public tSinav(){
		
	}

	public int getDersKod() {
		return DersKod;
	}

	public void setDersKod(int dersKod) {
		DersKod = dersKod;
	}

	public int getDersSinif() {
		return DersSinif;
	}

	public void setDersSinif(int dersSinif) {
		DersSinif = dersSinif;
	}

	public String getTarih() {
		return Tarih;
	}

	public void setTarih(String tarih) {
		Tarih = tarih;
	}

	public String getSaat() {
		return Saat;
	}

	public void setSaat(String saat) {
		Saat = saat;
	}

	public String getHocaAd() {
		return HocaAd;
	}

	public void setHocaAd(String hocaAd) {
		HocaAd = hocaAd;
	}

	public int getGozetmen1ID() {
		return Gozetmen1ID;
	}

	public void setGozetmen1ID(int gozetmen1id) {
		Gozetmen1ID = gozetmen1id;
	}

	public int getGozetmen2ID() {
		return Gozetmen2ID;
	}

	public void setGozetmen2ID(int gozetmen2id) {
		Gozetmen2ID = gozetmen2id;
	}

	public String getSinifKod1() {
		return SinifKod1;
	}

	public void setSinifKod1(String sinifKod1) {
		SinifKod1 = sinifKod1;
	}

	public String getSinifKod2() {
		return SinifKod2;
	}

	public void setSinifKod2(String sinifKod2) {
		SinifKod2 = sinifKod2;
	}

	public String getDersAd() {
		return DersAd;
	}

	public void setDersAd(String dersAd) {
		DersAd = dersAd;
	}
}
