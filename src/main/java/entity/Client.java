package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_client")
    private Long numeroClient;
    private String nom;
    private String prenom;
    private String motDePasse;
    @Embedded
    private Adresse adresse;

    /*
     * @ManyToOne(cascade = CascadeType.ALL)
     * 
     * @JoinTable(name = "client_cours",
     * joinColumns = { @JoinColumn(name = "client_id") },
     * inverseJoinColumns = { @JoinColumn(name = "compte_id") })
     */
    // @OneToMany(fetch = FetchType.LAZY, mappedBy="client", cascade =
    // CascadeType.ALL)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "numero_client")
    @Fetch(FetchMode.SELECT)
    /*
     * @JoinTable(name = "client_compte",
     * joinColumns = { @JoinColumn(name = "numero_client") },
     * inverseJoinColumns = { @JoinColumn(name = "numero_compte") })
     */
    private List<Compte> comptes;

    public void addCompte(Compte c) {
        if (this.comptes == null) {
            this.comptes = new ArrayList<>();
        }
        this.comptes.add(c);
    }

    public void fermeCompte(Compte c) {
        if (this.comptes != null)
            this.comptes.remove(c);
    }

    public Long getNumeroClient() {
        return numeroClient;
    }

    public void setNumeroClient(Long numeroClient) {
        this.numeroClient = numeroClient;
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

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
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
        return "Client [numeroClient=" + numeroClient + ", nom=" + nom + ", prenom=" + prenom + ", motDePasse="
                + motDePasse + ", adresse=" + adresse + ", comptes=" + comptes + "]";
    }

}
