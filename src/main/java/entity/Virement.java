package entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"date", "crediteur_numero_compte", "debiteur_numero_compte"}))
public class Virement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_virement")
    private Long numeroVirement;
    private Timestamp date;
    @Enumerated(EnumType.STRING)
    private typeVirement type;
    private double montant;
    @ManyToOne
    private Compte debiteur;
    @ManyToOne
    private Compte crediteur;

    public enum typeVirement {
        DEBIT, CREDIT, VIREMENT;

        typeVirement() {
        }
    }

    public Virement() {
        super();
    }

    public Virement(Timestamp date, typeVirement type, double montant, Compte compte) {
        super();
        this.date = date;
        this.type = type;
        this.montant = montant;
        if (type == typeVirement.CREDIT) {
            this.crediteur = compte;
            this.debiteur = null;
        } else if (type == typeVirement.DEBIT) {
            this.debiteur = compte;
            this.crediteur = null;
        }
    }

    public Virement(Timestamp date, double montant, Compte debiteur, Compte crediteur) {
        super();
        this.date = date;
        this.type = typeVirement.VIREMENT;
        this.montant = montant;
        this.crediteur = crediteur;
        this.debiteur = debiteur;
    }

    public Long getNumeroVirement() {
        return numeroVirement;
    }

    public void setNumeroVirement(Long numeroVirement) {
        this.numeroVirement = numeroVirement;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public typeVirement getType() {
        return type;
    }

    public void setType(typeVirement type) {
        this.type = type;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
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
    
    @Override
    public String toString() {
        return "Virement [numero_virement=" + numeroVirement + ", date=" + date + ", type=" + type + ", montant="
                + montant + ", debiteur=" + ((debiteur != null) ? debiteur.getNumeroCompte() : "none") + ", crediteur="
                + ((crediteur != null) ? crediteur.getNumeroCompte() : "none") + "]";
    }

}
