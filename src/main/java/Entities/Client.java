package Entities;

import java.util.List;

public class Client {

	private int numero_client;
	private String nom;
	private String prenom;
	private Adresse adresse;
	private List<Compte> comptes;
	
	public int getNumero_client() {
		return numero_client;
	}
	public void setNumero_client(int numero_client) {
		this.numero_client = numero_client;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public List<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}
	
	
	
}
