package Entities;

import java.util.List;

public class Compte {

	private int numero_compte;
	private long montant;
	private List<Virement> historique;
	public int getNumero_compte() {
		return numero_compte;
	}
	public void setNumero_compte(int numero_compte) {
		this.numero_compte = numero_compte;
	}
	public long getMontant() {
		return montant;
	}
	public void setMontant(long montant) {
		this.montant = montant;
	}
	public List<Virement> getHistorique() {
		return historique;
	}
	public void setHistorique(List<Virement> historique) {
		this.historique = historique;
	}

	
}
