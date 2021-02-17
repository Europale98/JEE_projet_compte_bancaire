package exception;

public class MemeCompteException extends CompteBancaireException {

    public MemeCompteException() {
        super("Même compte");
    }

}
