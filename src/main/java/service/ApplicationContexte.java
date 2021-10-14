package service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContexte {
    private static ApplicationContexte applicationContexte = null;
    private ClientService clientService = null;

    private ApplicationContexte() {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("service");
        appContext.scan("dao");
        appContext.scan("entity");
        appContext.scan("controller");
        appContext.refresh();
        
        clientService = (ClientService) appContext.getBean("clientService");

    }

    public static ApplicationContexte getInstance() {
        if (applicationContexte == null) {
            applicationContexte = new ApplicationContexte();
        }
        return applicationContexte;
    }
    
    public ClientService getClientService() {
        return clientService;
    }
}
