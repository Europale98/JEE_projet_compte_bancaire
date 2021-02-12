package service;

import java.util.List;

import org.springframework.stereotype.Service;

import entity.Client;
import entity.Compte;

public interface ClientService {
    Client getClientByNumero(Long numeroClient);
    
    List<Client> getAllClient();
    
    Client createClient(String nom, String prenom, String numeroRue, String ville, double montant);
    
    Client updateClient(Client c, String nom, String prenom, String numeroRue, String ville);
    
    void deleteClient(Client c);

    void deleteClientByNumero(Long numeroClient);
    
    Client creerCompteClient(Client c, double montant);
    
    Client fermerCompteClient(Client c, Compte compte);
    
    Client effectuerCreditCompte(Client c, Compte cm, double montant);
    
    Client effectuerDebitCompte(Client c, Compte cm, double montant);
    
    Client effectuerVirementCompte(Client c, Compte cm, Long numeroCompte2, double montant);
    
    Client suppressionHistoriqueVirement(Client c, Compte cm);
}
