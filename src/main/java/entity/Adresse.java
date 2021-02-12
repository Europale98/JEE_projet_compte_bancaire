package entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Adresse {

    @Column(name = "numero_rue")
    private String numeroRue;
    private String ville;

    public String getNumeroRue() {
        return numeroRue;
    }

    public void setNumeroRue(String numeroRue) {
        this.numeroRue = numeroRue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Adresse [numero_rue=" + numeroRue + ", ville=" + ville + "]";
    }

}
