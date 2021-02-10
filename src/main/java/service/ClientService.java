package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ClientRepository;
import entity.Client;


@Service("clientService")
public class ClientService {
    @Autowired
    private ClientRepository repository;
    public void test() {
        Client c = new Client();
        c.setNom("Nom");
        c.setPrenom("Prenom");
        repository.save(c);
    }
}
