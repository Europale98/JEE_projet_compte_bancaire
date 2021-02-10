package entity;

public class Virement {

	private int numero_virement;
	private String date;
	private String type;
	private long montant;
	private int numero_debiteur;
	private int numero_crediteur;
	
	public int getNumero_virement() {
		return numero_virement;
	}
	public void setNumero_virement(int numero_virement) {
		this.numero_virement = numero_virement;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getMontant() {
		return montant;
	}
	public void setMontant(long montant) {
		this.montant = montant;
	}
	public int getNumero_debiteur() {
		return numero_debiteur;
	}
	public void setNumero_debiteur(int numero_debiteur) {
		this.numero_debiteur = numero_debiteur;
	}
	public int getNumero_crediteur() {
		return numero_crediteur;
	}
	public void setNumero_crediteur(int numero_crediteur) {
		this.numero_crediteur = numero_crediteur;
	}
	
	
}
