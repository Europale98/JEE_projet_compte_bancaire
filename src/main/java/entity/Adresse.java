package entity;

import javax.persistence.Embeddable;

@Embeddable
public class Adresse {

	private String numero_rue;
	private String ville;
	
	public String getNumero_rue() {
		return numero_rue;
	}
	public void setNumero_rue(String numero_rue) {
		this.numero_rue = numero_rue;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
    @Override
    public String toString() {
        return "Adresse [numero_rue=" + numero_rue + ", ville=" + ville + "]";
    }

	
}
