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
        

        CompteService compteService = (CompteService)appContext.getBean("compteService");
        compteService.deleteCompte(c.getComptes().get(0));
        c = clientService.getClientbyId(c.getNumero_client());

        System.out.println(c);
        

        clientService.deleteClient(c);
        System.out.println(clientService.getAllClient());
        
        appContext.close();
    }

}
