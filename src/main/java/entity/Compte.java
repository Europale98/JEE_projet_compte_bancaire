package entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numero_compte;
	private double montant;
	@ManyToOne
	@JoinColumn(name="numero_client", nullable=false)
	private Client client;
	/*private List<Virement> historique;*/
	public Long getNumero_compte() {
		return numero_compte;
	}
	public void setNumero_compte(Long numero_compte) {
		this.numero_compte = numero_compte;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
	/*public List<Virement> getHistorique() {
		return historique;
	}
	public void setHistorique(List<Virement> historique) {
		this.historique = historique;
	}*/

	
}
