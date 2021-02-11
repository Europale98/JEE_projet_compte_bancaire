package entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;

import entity.Virement.typeVirement;

@Entity
public class Compte implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numero_compte;
	private double montant;
	/*@ManyToOne
	@JoinColumn(name="numero_client", nullable=false)
	private Client client;*/
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "debiteur", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Virement> historique_debit;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "crediteur", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
	private List<Virement> historique_credit;
    
    public Compte() {
        historique_credit = new ArrayList<Virement>();
        historique_debit = new ArrayList<Virement>();
    }
    
    public void estDebite(double montant) {
        this.montant -= montant;
        historique_debit.add(new Virement(new Timestamp(System.currentTimeMillis()), typeVirement.DEBIT, montant, this));
    }
    public void estCredite(double montant) {
        this.montant += montant;
        historique_credit.add(new Virement(new Timestamp(System.currentTimeMillis()), typeVirement.CREDIT, montant, this));
    }
    public void effectuerVirement(double montant, Compte compte) {
        this.montant -= montant;
        Virement v = new Virement(new Timestamp(System.currentTimeMillis()), montant, this, compte);
        historique_debit.add(v);
        compte.recupererVirement(v);
    }
    public void recupererVirement(Virement v) {
        this.montant += v.getMontant();
        historique_debit.add(v);
    }
    
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
    /*public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }*/
	
    public List<Virement> getHistorique_debit() {
        return historique_debit;
    }
    public List<Virement> getHistorique_credit() {
        return historique_credit;
    }
    public void setHistorique_debit(List<Virement> historique_debit) {
        this.historique_debit = historique_debit;
    }
    public void setHistorique_credit(List<Virement> historique_credit) {
        this.historique_credit = historique_credit;
    }
    @Override
    public String toString() {
        return "Compte [numero_compte=" + numero_compte + ", montant=" + montant
                + ", historique_debit=" + historique_debit
                + ", historique_credit=" + historique_credit + "]";
    }
	
	
    
}
