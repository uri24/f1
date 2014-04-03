package net.infobosccoma.f1;

import android.graphics.Bitmap;

public class Escuderia {

	private String nom, motor, pilot1, pilot2;
	private Bitmap imatge;

	public Escuderia() {
	}

	public Escuderia(String nom, String motor, String pilot1, String pilot2, Bitmap imatge) {
		this.nom = nom;
		this.motor = motor;
		this.pilot1 = pilot1;
		this.pilot2 = pilot2;
		this.imatge = imatge;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public String getPilot1() {
		return pilot1;
	}

	public void setPilot1(String pilot1) {
		this.pilot1 = pilot1;
	}

	public String getPilot2() {
		return pilot2;
	}

	public void setPilot2(String pilot2) {
		this.pilot2 = pilot2;
	}

	public Bitmap getImatge() {
		return imatge;
	}

	public void setImatge(Bitmap imatge) {
		this.imatge = imatge;
	}

	@Override
	public String toString() {
		return "Escuderia [nom=" + nom + ", motor=" + motor + ", pilot1="
				+ pilot1 + ", pilot2=" + pilot2 + "]";
	}
}
