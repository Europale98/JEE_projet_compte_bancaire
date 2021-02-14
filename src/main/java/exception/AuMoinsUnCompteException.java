package exception;

public class AuMoinsUnCompteException extends CompteBancaireException {
    public AuMoinsUnCompteException() {
        super("Avoir au moins un compte est obligatoire");
    }
}
