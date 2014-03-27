package net.infobosccoma.f1;

import java.io.File;

public class usuari {

	private String nom, cognoms;
	private File imatge;
	
	public usuari() {
		}

	public usuari(String nom, String cognoms, File imatge) {
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
	public File getImatge() {
		return imatge;
	}
	public void setImatge(File imatge) {
		this.imatge = imatge;
	}
	@Override
	public String toString() {
		return "usuari [nom=" + nom + ", cognoms=" + cognoms + ", imatge="
				+ imatge + "]";
	}
	
	
}
