package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ClientRepository;
import entity.Adresse;
import entity.Client;


@Service("clientService")
public class ClientService {
    @Autowired
    private ClientRepository repository;
    public void test() {
        Client c = new Client();
        c.setNom("Nom");
        c.setPrenom("Prenom");
        Adresse a = new Adresse();
        a.setNumero_rue("78 rue QQCH");
        a.setVille("Ville");
        c.setAdresse(a);
        repository.save(c);
    }
}
