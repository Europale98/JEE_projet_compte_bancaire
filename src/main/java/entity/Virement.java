package entity;

public class Virement {

	private int numero_virement;
	private String date;
	private String type;
	private long montant;
	private Compte debiteur;
	private Compte crediteur;
	
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
    public Compte getDebiteur() {
        return debiteur;
    }
    public Compte getCrediteur() {
        return crediteur;
    }
    public void setDebiteur(Compte debiteur) {
        this.debiteur = debiteur;
    }
    public void setCrediteur(Compte crediteur) {
        this.crediteur = crediteur;
    }
	
	
}
