package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numero_client;
	private String nom;
	private String prenom;
	@Embedded
	private Adresse adresse;
	
	/*@ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "client_cours",
    joinColumns = { @JoinColumn(name = "client_id") },
    inverseJoinColumns = { @JoinColumn(name = "compte_id") })*/
	//@OneToMany(fetch = FetchType.LAZY, mappedBy="client", cascade = CascadeType.ALL)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="numero_client")
    /*@JoinTable(name = "client_compte",
    joinColumns = { @JoinColumn(name = "numero_client") },
    inverseJoinColumns = { @JoinColumn(name = "numero_compte") })*/
	private List<Compte> comptes;
	
	public Long getNumero_client() {
		return numero_client;
	}
	public void setNumero_client(Long numero_client) {
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
    @Override
    public String toString() {
        return "Client [numero_client=" + numero_client + ", nom=" + nom
                + ", prenom=" + prenom + ", adresse=" + adresse + ", comptes="
                + comptes + "]";
    }
	
	
	
}
