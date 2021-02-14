package exception;

public class ClientInexistantException extends CompteBancaireException {
    public ClientInexistantException() {
        super("Client inexistant");
    }
}
