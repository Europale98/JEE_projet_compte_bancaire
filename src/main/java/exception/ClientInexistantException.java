package exception;

public class ClientInexistantException extends CompteBancaireException {
    public ClientInexistantException() {
        super("Numéro client ou Mot de passe incorrect");
    }
}
