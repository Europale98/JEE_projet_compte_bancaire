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

import entity.Virement.TypeVirement;
import exception.DeficitImpossibleException;
import exception.MontantImpossibleException;

@Entity
public class Compte implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_compte")
    private Long numeroCompte;
    private double montant;

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

    public void effectuerDebit(double montant) throws DeficitImpossibleException, MontantImpossibleException {
        if (montant < 0) {
            throw new MontantImpossibleException();
        }
        if (this.montant < montant) {
            throw new DeficitImpossibleException();
        }
        this.montant -= montant;
        historiqueDebit.add(new Virement(new Timestamp(System.currentTimeMillis()), TypeVirement.DEBIT, montant, this));
    }

    public void effectuerCredit(double montant) throws MontantImpossibleException {
        if (montant < 0) {
            throw new MontantImpossibleException();
        }
        this.montant += montant;
        historiqueCredit
                .add(new Virement(new Timestamp(System.currentTimeMillis()), TypeVirement.CREDIT, montant, this));
    }

    public Virement effectuerVirement(double montant, Compte compte) throws DeficitImpossibleException, MontantImpossibleException {
        if (montant < 0) {
            throw new MontantImpossibleException();
        }
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
    
    public void changeVirementPourSupression() {
        for(Virement v : historiqueCredit) {
            if(v.getType()==TypeVirement.VIREMENT) {
                v.setCrediteur(null);
                v.setType(TypeVirement.DEBIT);
            }
        }
        for(Virement v : historiqueDebit) {
            if(v.getType()==TypeVirement.VIREMENT) {
                v.setDebiteur(null);
                v.setType(TypeVirement.CREDIT);
            }
        }
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
        if(d==null)
            return null;
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
