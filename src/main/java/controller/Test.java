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

        Client c = clientService.createClient();
        System.out.println(c);
        
        /*CompteService compteService = (CompteService)appContext.getBean("compteService");
        compteService.deleteCompte(c.getComptes().get(0));
        c = clientService.getClientbyId(c.getNumero_client());
        System.out.println(c);

        clientService.deleteClient(c);
        System.out.println(clientService.getAllClient());*/
        
        
        c.getComptes().get(0).estCredite(10);
        c.getComptes().get(0).estDebite(20);
        c.getComptes().get(0).effectuerVirement(5, c.getComptes().get(1));
        c = clientService.update(c);

        System.out.println("-----> " + c.getComptes().size());
        System.out.println(c);
        c = clientService.getClientbyId(c.getNumero_client());
        System.out.println(c);
        System.out.println("-----> " + c.getComptes().size());
        c.getComptes().remove(1);
        System.out.println(c);
        c = clientService.update(c);
        c = clientService.getClientbyId(c.getNumero_client());
        System.out.println("-----> " + c.getComptes().size());
        System.out.println(c);
        
        c.getComptes().get(0).getHistorique_credit().remove(0);
        c = clientService.update(c);
        System.out.println("-----> " + c.getComptes().size());
        System.out.println(c);
        appContext.close();
    }

}
