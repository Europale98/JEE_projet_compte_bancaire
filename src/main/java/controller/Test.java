package controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import entity.Client;
import exception.CompteInexistantException;
import exception.DeficitImpossibleException;
import service.ClientService;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("service");
        appContext.scan("dao");
        appContext.scan("entity");
        appContext.scan("controller");
        appContext.refresh();

        ClientService clientService = (ClientService) appContext.getBean("clientService");
        // individuService.test();

        Client c = null;
        try {
            c = clientService.createClient("Nom", "Prenom", "mdp", "rue rue", "ville", 10);
        } catch (DeficitImpossibleException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(c);
        
        System.out.println("Verif mdp true : " + clientService.verificationMotDePasse(c.getNumeroClient(), "mdp"));
        System.out.println("Verif mdp false : " + clientService.verificationMotDePasse(c.getNumeroClient(), "mdpcazdsub"));

        try {
            c = clientService.creerCompteClient(c, 300);
        } catch (DeficitImpossibleException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(c);

        /*
         * c = clientService.fermerCompteClient(c, c.getComptes().get(0));
         * System.out.println(c);
         * 
         * c = clientService.fermerCompteClient(c, c.getComptes().get(0));
         * System.out.println(c);
         * System.out.println(clientService.getAllClient());
         */

        /*
         * clientService.deleteClient(c);
         * System.out.println(clientService.getAllClient());
         */

        // CompteService compteService =
        // (CompteService)appContext.getBean("compteService");
        try {
            c = clientService.effectuerCreditCompte(c, c.getComptes().get(0), 10);
        } catch (CompteInexistantException e1) {
        }
        try {
            c = clientService.effectuerDebitCompte(c, c.getComptes().get(0), 30);
        } catch (DeficitImpossibleException | CompteInexistantException e) {
            System.out.println(e.getMessage());
        }
        try {
            c = clientService.effectuerVirementCompte(c, c.getComptes().get(0), c.getComptes().get(1).getNumeroCompte(), 5);
        } catch (DeficitImpossibleException | CompteInexistantException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("-----> " + c.getComptes().size());
        System.out.println(c);

        /*c = clientService.fermerCompteClient(c, c.getComptes().get(1));
        System.out.println(c);

        c = clientService.suppressionHistoriqueVirement(c, c.getComptes().get(0));
        System.out.println("-----> " + c.getComptes().size());
        System.out.println(c);

        c = clientService.updateClient(c, "Nom2", c.getPrenom(), "mdp2", "rue rue2", c.getAdresse().getVille());
        System.out.println("-----> " + c.getComptes().size());
        System.out.println(c);
        c = clientService.fermerCompteClient(c, c.getComptes().get(0));
        System.out.println(c);*/
        
        appContext.close();
    }

}
