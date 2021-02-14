package exception;

public class CompteInexistantException extends CompteBancaireException {
    public CompteInexistantException() {
        super("Compte Inexistant");
    }
}
