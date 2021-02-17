package controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import entity.Client;
import exception.AuMoinsUnCompteException;
import exception.ClientInexistantException;
import exception.CompteInexistantException;
import exception.DeficitImpossibleException;
import exception.MemeCompteException;
import exception.MontantImpossibleException;
import service.ApplicationContexte;
import service.ClientService;

public class Test {

    public static void main(String[] args) {
        ApplicationContexte appContext = ApplicationContexte.getInstance();

        ClientService clientService = appContext.getClientService();
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
            c = clientService.creerCompteClient(c.getNumeroClient(), 300);
        } catch (DeficitImpossibleException | ClientInexistantException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(c);
        
        try {
            c = clientService.effectuerCreditCompte(c.getNumeroClient(), c.getComptes().get(0).getNumeroCompte(), 10);
        } catch (CompteInexistantException | MontantImpossibleException e1) {
            System.out.println(e1.getMessage());
        }
        try {
            c = clientService.effectuerDebitCompte(c.getNumeroClient(), c.getComptes().get(0).getNumeroCompte(), 30);
        } catch (DeficitImpossibleException | CompteInexistantException | MontantImpossibleException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Virement");
        try {
            c = clientService.effectuerVirementCompte(c.getNumeroClient(), c.getComptes().get(0).getNumeroCompte(), c.getComptes().get(1).getNumeroCompte(), 5);
        } catch (DeficitImpossibleException | CompteInexistantException | MontantImpossibleException | MemeCompteException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(c);
        
        /*System.out.println("Histo suppr");
        try {
            c = clientService.suppressionHistoriqueVirement(c, c.getComptes().get(0).getNumeroCompte());
        } catch (CompteInexistantException e) {
            e.printStackTrace();
        }
        System.out.println(c);*/
        
        System.out.println("Supprimer");
        try {
            c = clientService.fermerCompteClient(c.getNumeroClient(), c.getComptes().get(0).getNumeroCompte());
        } catch (CompteInexistantException | AuMoinsUnCompteException | ClientInexistantException e2) {
            System.out.println("ICI");
            e2.printStackTrace();
        }
        System.out.println(c);
        
        
        
        System.out.println("Virement lui même");
        try {
            c = clientService.effectuerVirementCompte(c.getNumeroClient(), c.getComptes().get(0).getNumeroCompte(), c.getComptes().get(0).getNumeroCompte(), 27);
        } catch (DeficitImpossibleException | CompteInexistantException | MontantImpossibleException | MemeCompteException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(c);

        System.out.println("\nNouveau compte");
        Client c2 = null;
        try {
            c2 = clientService.createClient("N2", "P2", "m", "2", "2", 256789);
        } catch (DeficitImpossibleException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(c2);

        System.out.println("Virement entre comptes");
        try {
            c = clientService.effectuerVirementCompte(c.getNumeroClient(), c.getComptes().get(0).getNumeroCompte(), c2.getComptes().get(0).getNumeroCompte(), 27);
        } catch (DeficitImpossibleException | CompteInexistantException | MontantImpossibleException | MemeCompteException e) {
            System.out.println(e.getMessage());
        }
        try {
            c2 = clientService.getClientByNumero(c2.getNumeroClient());
        } catch (ClientInexistantException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        System.out.println(c);
        System.out.println(c2);

        System.out.println("\nsuprimme c2");
        clientService.deleteClientByNumero(c2.getNumeroClient());
        
        try {
            System.out.println(clientService.getClientByNumero(c.getNumeroClient()));
        } catch (ClientInexistantException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
        /*
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
        /*try {
            c = clientService.effectuerCreditCompte(c, c.getComptes().get(0).getNumeroCompte(), 10);
        } catch (CompteInexistantException | MontantImpossibleException e1) {
        }
        try {
            c = clientService.effectuerDebitCompte(c, c.getComptes().get(0).getNumeroCompte(), 30);
        } catch (DeficitImpossibleException | CompteInexistantException | MontantImpossibleException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("-----> " + c.getComptes().size());
        System.out.println(c);
        
        System.out.println(c.getComptes().get(0).getHistoriqueVirement());*/

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
        
    }

}
