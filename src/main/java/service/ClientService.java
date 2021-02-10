package service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ClientRepository;
import entity.Adresse;
import entity.Client;
import entity.Compte;


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
        Compte cm = new Compte();
        cm.setMontant(100);
        cm.setClient(c);
        Compte cm1 = new Compte();
        cm1.setMontant(200);
        cm1.setClient(c);
        ArrayList<Compte> listcompte = new ArrayList<>();
        listcompte.add(cm);
        listcompte.add(cm1);
        c.setComptes(listcompte);
        repository.save(c);
    }
}
