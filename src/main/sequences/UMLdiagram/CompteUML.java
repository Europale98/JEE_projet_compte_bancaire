package UMLdiagram;

public enum CompteUML {
    CompteUML;
    
    public String creditUML() {
        String participant = "actor User\n"
                + "participant VirementServlet\n"
                + "participant ClientService\n"
                + "participant ClientRepository\n"
                + "participant CompteService\n"
                + "participant CompteRepository\n"
                + "database bdd_jee_projet";
        String action = "User -> VirementServlet ++ : credit\n"
                + "VirementServlet -> ClientService ++ : effectuerCreditCompte\n"
                + "ClientService -> ClientService ++ : verificationAppartenance\n"
                + "ClientService -> ClientService ++ : getClientByNumero\n"
                + "ClientService -> ClientRepository ++ : findById\n"
                + "ClientRepository -> bdd_jee_projet ++\n"
                + "return Client\n"
                + "ClientRepository -> Client **\n"
                + "return Client\n"
                + "return Client\n"
                + "ClientService -> Client ++ : getCompte\n"
                + "Client -> Compte **\n"
                + "return Compte\n"
                + "return true\n"
                + "ClientService -> CompteService ++ : effectuerCreditCompte\n"
                + "CompteService -> CompteService ++ : getCompteByNumero\n"
                + "CompteService -> CompteRepository ++ : findById\n"
                + "CompteRepository -> bdd_jee_projet ++\n"
                + "return Compte\n"
                + "CompteRepository -> Compte **\n"
                + "return Compte\n"
                + "return Compte\n"
                + "CompteService -> Compte ++ : effectuerCredit\n"
                + "Compte -> Compte : montant +\n"
                + "Compte -> Compte : historique Credit add Virement\n"
                + "return\n"
                + "CompteService -> CompteRepository ++: save\n"
                + "CompteRepository -> bdd_jee_projet ++\n"
                + "return Compte\n"
                + "return\n"
                + "return\n"
                + "ClientService -> ClientService ++ : getClientByNumero\n"
                + "ClientService -> ClientRepository ++ : findById\n"
                + "ClientRepository -> bdd_jee_projet ++\n"
                + "return Client\n"
                + "ClientRepository -> Client **\n"
                + "return Client\n"
                + "return Client\n"
                + "return Client\n"
                + "VirementServlet -> VirementServlet : session.setAttribute\n"
                + "return /infosCompte.jsp";
        return participant +"\n"+ action;
    }
}
