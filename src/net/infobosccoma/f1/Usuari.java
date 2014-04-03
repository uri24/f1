package net.infobosccoma.f1;


import java.io.Serializable;

public class Usuari implements Serializable{

	private String nom, cognoms, imatge;
	
	
	public Usuari() {
		}

	public Usuari(String nom, String cognoms, String imatge) {
		this.nom = nom;
		this.cognoms = cognoms;
		this.imatge = imatge;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCognoms() {
		return cognoms;
	}
	public void setCognoms(String cognoms) {
		this.cognoms = cognoms;
	}
	public String getImatge() {
		return imatge;
	}
	public void setImatge(String imatge) {
		this.imatge = imatge;
	}
	@Override
	public String toString() {
		return "usuari [nom=" + nom + ", cognoms=" + cognoms + ", imatge="
				+ imatge + "]";
	}
	
	
}
