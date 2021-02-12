package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.ClientRepository;
import entity.Adresse;
import entity.Client;
import entity.Compte;

@Service("clientService")
public class ClientServiceImpl implements ClientService {
    private static ClientServiceImpl serviceInstance = null;
    @Autowired
    private ClientRepository repository;
    @Autowired
    private CompteService compteService;

    private ClientServiceImpl() {
    }

    public static ClientServiceImpl getInstance() {
        if (serviceInstance == null) {
            serviceInstance = new ClientServiceImpl();
        }
        return serviceInstance;
    }

    @Override
    public Client getClientByNumero(Long numeroClient) {
        Optional<Client> c = repository.findById(numeroClient);
        if (c.isPresent())
            return c.get();
        else
            return null;
    }

    @Override
    public List<Client> getAllClient() {
        return (List<Client>) repository.findAll();
    }

    @Override
    public boolean verificationMotDePasse(Long numeroClient, String motDePasse) {
        List<Client> list = repository.findByNumeroClientAndMotDePasse(numeroClient, motDePasse);
        return !list.isEmpty();
    }

    @Override
    public Client createClient(String nom, String prenom, String motDePasse, String numeroRue, String ville,
            double montant) {
        Client c = new Client();
        c.setNom(nom);
        c.setPrenom(prenom);
        c.setMotDePasse(motDePasse);

        Adresse a = new Adresse();
        a.setNumeroRue(numeroRue);
        a.setVille(ville);
        c.setAdresse(a);

        Compte cm = new Compte();
        cm.setMontant(montant);
        c.addCompte(cm);
        c = repository.save(c);
        return this.getClientByNumero(c.getNumeroClient());
    }

    @Override
    public Client updateClient(Client c, String nom, String prenom, String motDePasse, String numeroRue, String ville) {
        if (nom != null)
            c.setNom(nom);
        if (prenom != null)
            c.setPrenom(prenom);
        if (motDePasse != null)
            c.setMotDePasse(motDePasse);
        Adresse a = c.getAdresse();
        if (numeroRue != null)
            a.setNumeroRue(numeroRue);
        if (ville != null)
            a.setVille(ville);
        c.setAdresse(a);
        c = repository.save(c);
        return this.getClientByNumero(c.getNumeroClient());
    }

    @Override
    public void deleteClient(Client c) {
        repository.delete(c);
    }

    @Override
    public void deleteClientByNumero(Long numeroClient) {
        repository.deleteById(numeroClient);
    }

    @Override
    public Client creerCompteClient(Client c, double montant) {
        Compte cm = new Compte();
        cm.setMontant(montant);
        c.addCompte(cm);
        c = repository.save(c);
        return this.getClientByNumero(c.getNumeroClient());
    }

    @Override
    public Client fermerCompteClient(Client c, Compte compte) {
        c.fermeCompte(compte);
        if (c.getComptes().isEmpty()) {
            this.deleteClientByNumero(c.getNumeroClient());
            return null;
        }
        c = repository.save(c);
        return this.getClientByNumero(c.getNumeroClient());
    }

    @Override
    public Client effectuerCreditCompte(Client c, Compte cm, double montant) {
        if (c.getComptes().contains(cm)) {
            compteService.effectuerCreditCompte(cm, montant);
            c = this.getClientByNumero(c.getNumeroClient());
        }
        return c;
    }

    @Override
    public Client effectuerDebitCompte(Client c, Compte cm, double montant) {
        if (c.getComptes().contains(cm)) {
            compteService.effectuerDebitCompte(cm, montant);
            c = this.getClientByNumero(c.getNumeroClient());
        }
        return c;
    }

    @Override
    public Client effectuerVirementCompte(Client c, Compte cm, Long numeroCompte2, double montant) {
        if (c.getComptes().contains(cm)) {
            compteService.effectuerVirementCompte(cm, numeroCompte2, montant);
            c = this.getClientByNumero(c.getNumeroClient());
        }
        return c;
    }

    @Override
    public Client suppressionHistoriqueVirement(Client c, Compte cm) {
        if (c.getComptes().contains(cm)) {
            compteService.suppressionHistoriqueVirement(cm);
            c = this.getClientByNumero(c.getNumeroClient());
        }
        return c;
    }

}
