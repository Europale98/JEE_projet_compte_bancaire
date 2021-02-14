package entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
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
import exception.DeficitImpossibleException;

@Entity
public class Compte implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_compte")
    private Long numeroCompte;
    private double montant;
    /*
     * @ManyToOne
     * 
     * @JoinColumn(name="numero_client", nullable=false)
     * private Client client;
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "debiteur", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Virement> historiqueDebit;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "crediteur", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Virement> historiqueCredit;

    public Compte() {
        historiqueCredit = new ArrayList<Virement>();
        historiqueDebit = new ArrayList<Virement>();
    }

    public void effectuerDebit(double montant) throws DeficitImpossibleException {
        if (this.montant < montant) {
            throw new DeficitImpossibleException();
        }
        this.montant -= montant;
        historiqueDebit.add(new Virement(new Timestamp(System.currentTimeMillis()), typeVirement.DEBIT, montant, this));
    }

    public void effectuerCredit(double montant) {
        this.montant += montant;
        historiqueCredit
                .add(new Virement(new Timestamp(System.currentTimeMillis()), typeVirement.CREDIT, montant, this));
    }

    public Virement effectuerVirement(double montant, Compte compte) throws DeficitImpossibleException {
        if (this.montant < montant) {
            throw new DeficitImpossibleException();
        }
        this.montant -= montant;
        Virement v = new Virement(new Timestamp(System.currentTimeMillis()), montant, this, compte);
        historiqueDebit.add(v);
        compte.recupererVirement(v);
        return v;
    }

    public void recupererVirement(Virement v) {
        this.montant += v.getMontant();
        historiqueCredit.add(v);
    }

    public void supprimerHistorique() {
        historiqueCredit.clear();
        historiqueDebit.clear();
    }

    public Long getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(Long numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) throws DeficitImpossibleException {
        if (montant < 0)
            throw new DeficitImpossibleException();
        this.montant = montant;
    }
    /*
     * public Client getClient() {
     * return client;
     * }
     * public void setClient(Client client) {
     * this.client = client;
     * }
     */

    public List<Virement> getHistoriqueDebit() {
        return historiqueDebit;
    }

    public List<Virement> getHistoriqueCredit() {
        return historiqueCredit;
    }
    
    public List<Virement> getHistoriqueVirement() {
        List<Virement> virementListe = new ArrayList<Virement>();
        virementListe.addAll(this.historiqueCredit);
        virementListe.addAll(this.historiqueDebit);
        virementListe.sort(new Comparator<Virement>() {
            @Override
            public int compare(Virement o1, Virement o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        return virementListe;
    }
    
    public Virement getVirementDebit(Virement d) {
        for (Virement v : this.historiqueDebit) {
            if (v.getDate().equals(d.getDate())
                    && v.getCrediteur().getNumeroCompte().equals(d.getCrediteur().getNumeroCompte())
                    && v.getDebiteur().getNumeroCompte().equals(d.getDebiteur().getNumeroCompte())) {
                return v;
            }
        }
        return null;
    }

    public void updateVirementCredit(Virement d) {
        if(d==null)
            return;
        for (Virement v : this.historiqueCredit) {
            if (v.getDate().equals(d.getDate())
                    && v.getCrediteur().getNumeroCompte().equals(d.getCrediteur().getNumeroCompte())
                    && v.getDebiteur().getNumeroCompte().equals(d.getDebiteur().getNumeroCompte())) {
                v.setNumeroVirement(d.getNumeroVirement());
            }
        }
    }

    public void setHistoriqueDebit(List<Virement> historiqueDebit) {
        this.historiqueDebit = historiqueDebit;
    }

    public void setHistoriqueCredit(List<Virement> historiqueCredit) {
        this.historiqueCredit = historiqueCredit;
    }

    @Override
    public String toString() {
        return "Compte [numero_compte=" + numeroCompte + ", montant=" + montant + ", historique_debit="
                + historiqueDebit + ", historique_credit=" + historiqueCredit + "]";
    }

}
