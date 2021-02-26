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
public class Virement implements StringXml {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_virement")
    private Long numeroVirement;
    private Timestamp date;
    @Enumerated(EnumType.STRING)
    private TypeVirement type;
    private double montant;
    @ManyToOne
    private Compte debiteur;
    @ManyToOne
    private Compte crediteur;

    public enum TypeVirement {
        DEBIT, CREDIT, VIREMENT;

        TypeVirement() {
        }
    }

    public Virement() {
        super();
    }

    public Virement(Timestamp date, TypeVirement type, double montant, Compte compte) {
        super();
        this.date = date;
        this.type = type;
        this.montant = montant;
        if (type == TypeVirement.CREDIT) {
            this.crediteur = compte;
            this.debiteur = null;
        } else if (type == TypeVirement.DEBIT) {
            this.debiteur = compte;
            this.crediteur = null;
        }
    }

    public Virement(Timestamp date, double montant, Compte debiteur, Compte crediteur) {
        super();
        this.date = date;
        this.type = TypeVirement.VIREMENT;
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

    public TypeVirement getType() {
        return type;
    }

    public void setType(TypeVirement type) {
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
                + montant + ", debiteur=" + ((debiteur != null) ? debiteur.getStringNumeroCompte() : "none") + ", crediteur="
                + ((crediteur != null) ? crediteur.getStringNumeroCompte() : "none") + "]";
    }

    @Override
    public String getString() {
        return "Virement " + date;
    }

}
