package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.ClientRepository;
import entity.Adresse;
import entity.Client;
import entity.Compte;


@Service("clientService")
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public Client createClient() {
        Client c = new Client();
        c.setNom("Nom");
        c.setPrenom("Prenom");
        Adresse a = new Adresse();
        a.setNumero_rue("78 rue QQCH");
        a.setVille("Ville");
        c.setAdresse(a);
        Compte cm = new Compte();
        cm.setMontant(100);
        Compte cm1 = new Compte();
        cm1.setMontant(200);
        ArrayList<Compte> listcompte = new ArrayList<>();
        listcompte.add(cm);
        listcompte.add(cm1);
        c.setComptes(listcompte);
        c = repository.save(c);
        return c;
    }

    public void deleteClient(Client c) {
        repository.delete(c);
    }

    public List<Client> getAllClient() {
        return (List<Client>) repository.findAll();
    }
    
    public Client getClientbyId(Long id) {
        return repository.findById(id).get();
    }
    
    public void test() {
        Client c = new Client();
        c.setNom("Nom");
        c.setPrenom("Prenom");
        Adresse a = new Adresse();
        a.setNumero_rue("78 rue QQCH");
        a.setVille("Ville");
        c.setAdresse(a);
        Compte cm = new Compte();
        cm.setMontant(100);
        //cm.setClient(c);
        Compte cm1 = new Compte();
        cm1.setMontant(200);
        //cm1.setClient(c);
        ArrayList<Compte> listcompte = new ArrayList<>();
        listcompte.add(cm);
        listcompte.add(cm1);
        c.setComptes(listcompte);
        repository.save(c);
        System.out.println(repository.findById(c.getNumero_client()).get().toString());
        repository.delete(c);
        System.out.println(repository.findById(c.getNumero_client()).isPresent());
    }
}
