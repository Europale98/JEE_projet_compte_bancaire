package controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import service.ClientService;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("service");
        appContext.scan("dao");
        appContext.scan("entity");
        appContext.scan("controller");
        appContext.refresh();
        
        ClientService individuService = (ClientService)appContext.getBean("clientService");
        individuService.test();

        appContext.close();
    }

}
