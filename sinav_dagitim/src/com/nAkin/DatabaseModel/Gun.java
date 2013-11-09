package com.nAkin.DatabaseModel;

public class Gun {
	private String gunAd, gercekTarih;
	private int gunNo, ayNo;
	public tSinav sinav1, sinav2, sinav3, sinav4;
	
	public Gun(){
		
	}
	
	public Gun(int gunNo,int ayNo, String gunAd){
		setGunNo(gunNo);
		setAyNo(ayNo);
		setGunAd(gunAd);
	}

	public String getGunAd() {
		return gunAd;
	}

	public void setGunAd(String gunAd) {
		this.gunAd = gunAd;
	}

	public int getGunNo() {
		return gunNo;
	}

	public void setGunNo(int gunNo) {
		this.gunNo = gunNo;
	}

	public String getGercekTarih() {
		return gercekTarih;
	}

	public void setGercekTarih(String gercekTarih) {
		this.gercekTarih = gercekTarih;
	}

	public int getAyNo() {
		return ayNo;
	}

	public void setAyNo(int ayNo) {
		this.ayNo = ayNo;
	}
}
