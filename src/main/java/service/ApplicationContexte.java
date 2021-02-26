package service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContexte {
    private static ApplicationContexte applicationContexte = null;
    AnnotationConfigApplicationContext appContext = null;
    private ClientService clientService = null;

    private ApplicationContexte() {
        appContext = new AnnotationConfigApplicationContext();
        appContext.scan("service");
        appContext.scan("dao");
        appContext.scan("entity");
        appContext.scan("controller");
        appContext.scan("trace");
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
    
    public static void close() {
        applicationContexte.appContext.close();
    }
}
