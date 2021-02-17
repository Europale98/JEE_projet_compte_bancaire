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
import exception.MemeCompteException;
import exception.MontantImpossibleException;
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
    public boolean verificationAppartenance(Long numeroClient, Long numeroCompte) {
        Client c;
        try {
            c = this.getClientByNumero(numeroClient);
        } catch (ClientInexistantException e1) {
            return false;
        }
        try {
            c.getCompte(numeroCompte);
        } catch (CompteInexistantException e) {
            return false;
        }
        return true;
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
    public Client updateClient(Long numeroClient, String nom, String prenom, String motDePasse, String numeroRue,
            String ville) throws ClientInexistantException {
        Client c = this.getClientByNumero(numeroClient);
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
    public void deleteClientByNumero(Long numeroClient) {
        Client c;
        try {
            c = this.getClientByNumero(numeroClient);
            c.changeVirementPourSupression();
            c = repository.save(c);
        } catch (ClientInexistantException e) {
        }
        repository.deleteById(numeroClient);
    }

    @Override
    public Client creerCompteClient(Long numeroClient, double montant)
            throws DeficitImpossibleException, ClientInexistantException {
        Client c = this.getClientByNumero(numeroClient);
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
    public Client fermerCompteClient(Long numeroClient, Long numeroCompte)
            throws CompteInexistantException, AuMoinsUnCompteException, ClientInexistantException {
        Client c = this.getClientByNumero(numeroClient);
        Compte compte = c.getCompte(numeroCompte);
        compte.changeVirementPourSupression();
        c = repository.save(c);
        compte = c.getCompte(numeroCompte);
        c.fermeCompte(compte);
        c = repository.save(c);
        try {
            c = this.getClientByNumero(c.getNumeroClient());
        } catch (ClientInexistantException e) {
        }
        return c;
    }

    @Override
    public Client effectuerCreditCompte(Long numeroClient, Long numeroCompte, double montant)
            throws CompteInexistantException, MontantImpossibleException {
        if(!this.verificationAppartenance(numeroClient, numeroCompte)) {
            throw new CompteInexistantException();
        }
        compteService.effectuerCreditCompte(numeroCompte, montant);
        Client c = null;
        try {
            c = this.getClientByNumero(numeroClient);
        } catch (ClientInexistantException e) {
        }
        return c;
    }

    @Override
    public Client effectuerDebitCompte(Long numeroClient, Long numeroCompte, double montant)
            throws DeficitImpossibleException, CompteInexistantException, MontantImpossibleException {
        if(!this.verificationAppartenance(numeroClient, numeroCompte)) {
            throw new CompteInexistantException();
        }
        compteService.effectuerDebitCompte(numeroCompte, montant);
        Client c = null;
        try {
            c = this.getClientByNumero(numeroClient);
        } catch (ClientInexistantException e) {
        }
        return c;
    }

    @Override
    public Client effectuerVirementCompte(Long numeroClient, Long numeroCompte, Long numeroCompte2, double montant)
            throws DeficitImpossibleException, CompteInexistantException, MontantImpossibleException,
            MemeCompteException {
        if (numeroCompte.equals(numeroCompte2)) {
            throw new MemeCompteException();
        }
        if(!this.verificationAppartenance(numeroClient, numeroCompte)) {
            throw new CompteInexistantException();
        }
        compteService.effectuerVirementCompte(numeroCompte, numeroCompte2, montant);
        Client c = null;
        try {
            c = this.getClientByNumero(numeroClient);
        } catch (ClientInexistantException e) {
        }
        return c;
    }

    @Override
    public Client suppressionHistoriqueVirement(Long numeroClient, Long numeroCompte) throws CompteInexistantException {
        if(!this.verificationAppartenance(numeroClient, numeroCompte)) {
            throw new CompteInexistantException();
        }
        compteService.suppressionHistoriqueVirement(numeroCompte);
        Client c = null;
        try {
            c = this.getClientByNumero(numeroClient);
        } catch (ClientInexistantException e) {
        }
        return c;
    }

}
