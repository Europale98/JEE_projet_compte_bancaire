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
import exception.AuMoinsUnCompteException;
import exception.DeficitImpossibleException;
import exception.ClientInexistantException;
import exception.CompteInexistantException;

@Service("clientService")
@Transactional
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
    public Client getClientByNumero(Long numeroClient) throws ClientInexistantException {
        Optional<Client> c = repository.findById(numeroClient);
        if (c.isPresent())
            return c.get();
        else
            throw new ClientInexistantException();
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
            double montant) throws DeficitImpossibleException {
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
        try {
            c = this.getClientByNumero(c.getNumeroClient());
        } catch (ClientInexistantException e) {
        }
        return c;
    }

    @Override
    public Client updateClient(Client c, String nom, String prenom, String motDePasse, String numeroRue, String ville) {
        if (nom != null && !nom.isEmpty())
            c.setNom(nom);
        if (prenom != null && !prenom.isEmpty())
            c.setPrenom(prenom);
        if (motDePasse != null && !motDePasse.isEmpty())
            c.setMotDePasse(motDePasse);
        Adresse a = c.getAdresse();
        if (numeroRue != null && !numeroRue.isEmpty())
            a.setNumeroRue(numeroRue);
        if (ville != null && !ville.isEmpty())
            a.setVille(ville);
        c.setAdresse(a);
        c = repository.save(c);
        try {
            c = this.getClientByNumero(c.getNumeroClient());
        } catch (ClientInexistantException e) {
        }
        return c;
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
    public Client creerCompteClient(Client c, double montant) throws DeficitImpossibleException {
        Compte cm = new Compte();
        cm.setMontant(montant);
        c.addCompte(cm);
        c = repository.save(c);
        try {
            c = this.getClientByNumero(c.getNumeroClient());
        } catch (ClientInexistantException e) {
        }
        return c;
    }

    @Override
    public Client fermerCompteClient(Client c, Long numeroCompte)
            throws CompteInexistantException, AuMoinsUnCompteException {
        Compte compte = c.getCompte(numeroCompte);
        c.fermeCompte(compte);
        c = repository.save(c);
        try {
            c = this.getClientByNumero(c.getNumeroClient());
        } catch (ClientInexistantException e) {
        }
        return c;
    }

    @Override
    public Client effectuerCreditCompte(Client c, Long numeroCompte, double montant) throws CompteInexistantException {
        Compte compte = c.getCompte(numeroCompte);
        compteService.effectuerCreditCompte(compte, montant);
        try {
            c = this.getClientByNumero(c.getNumeroClient());
        } catch (ClientInexistantException e) {
        }
        return c;
    }

    @Override
    public Client effectuerDebitCompte(Client c, Long numeroCompte, double montant) throws DeficitImpossibleException, CompteInexistantException {
        Compte compte = c.getCompte(numeroCompte);

        compteService.effectuerDebitCompte(compte, montant);
        try {
            c = this.getClientByNumero(c.getNumeroClient());
        } catch (ClientInexistantException e) {
        }
        return c;
    }

    @Override
    public Client effectuerVirementCompte(Client c, Long numeroCompte, Long numeroCompte2, double montant)
            throws DeficitImpossibleException, CompteInexistantException {
        Compte compte = c.getCompte(numeroCompte);
        compteService.effectuerVirementCompte(compte, numeroCompte2, montant);
        try {
            c = this.getClientByNumero(c.getNumeroClient());
        } catch (ClientInexistantException e) {
        }
        return c;
    }

    @Override
    public Client suppressionHistoriqueVirement(Client c, Long numeroCompte) throws CompteInexistantException {
        Compte compte = c.getCompte(numeroCompte);
        compteService.suppressionHistoriqueVirement(compte);
        try {
            c = this.getClientByNumero(c.getNumeroClient());
        } catch (ClientInexistantException e) {
        }
        return c;
    }

}
