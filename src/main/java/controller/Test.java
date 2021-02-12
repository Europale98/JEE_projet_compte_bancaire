package controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import entity.Client;
import service.ClientService;
import service.CompteService;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("service");
        appContext.scan("dao");
        appContext.scan("entity");
        appContext.scan("controller");
        appContext.refresh();
        
        ClientService clientService = (ClientService)appContext.getBean("clientService");
        //individuService.test();

        Client c = clientService.createClient("Nom", "Prenom", "rue rue", "ville", 100);
        System.out.println(c);
        
        c =  clientService.creerCompteClient(c, 300);
        System.out.println(c);
        
        /*c = clientService.fermerCompteClient(c, c.getComptes().get(0));
        System.out.println(c);
        
        c = clientService.fermerCompteClient(c, c.getComptes().get(0));
        System.out.println(c);
        System.out.println(clientService.getAllClient());*/
        
        /*clientService.deleteClient(c);
        System.out.println(clientService.getAllClient());*/

        //CompteService compteService = (CompteService)appContext.getBean("compteService");
        c = clientService.effectuerCreditCompte(c, c.getComptes().get(0), 10);
        c = clientService.effectuerDebitCompte(c, c.getComptes().get(0), 30);
        c = clientService.effectuerVirementCompte(c, c.getComptes().get(0),c.getComptes().get(1).getNumeroCompte(), 5);

        System.out.println("-----> " + c.getComptes().size());
        System.out.println(c);
        
        c = clientService.fermerCompteClient(c, c.getComptes().get(1));
        System.out.println(c);
        
        c = clientService.suppressionHistoriqueVirement(c, c.getComptes().get(0));
        System.out.println("-----> " + c.getComptes().size());
        System.out.println(c);
        
        c = clientService.updateClient(c, "Nom2", c.getPrenom(), "rue rue2", c.getAdresse().getVille());
        System.out.println("-----> " + c.getComptes().size());
        System.out.println(c);
        
        appContext.close();
    }

}
