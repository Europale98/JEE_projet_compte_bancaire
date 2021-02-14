package service;

import java.util.List;

import entity.Client;
import entity.Compte;
import exception.AuMoinsUnCompteException;
import exception.ClientInexistantException;
import exception.CompteInexistantException;
import exception.DeficitImpossibleException;

public interface ClientService {
    Client getClientByNumero(Long numeroClient) throws ClientInexistantException;

    List<Client> getAllClient();

    boolean verificationMotDePasse(Long numeroClient, String motDePasse);

    Client createClient(String nom, String prenom, String motDePasse, String numeroRue, String ville, double montant) throws DeficitImpossibleException;

    Client updateClient(Client c, String nom, String prenom, String motDePasse, String numeroRue, String ville);

    void deleteClient(Client c);

    void deleteClientByNumero(Long numeroClient);

    Client creerCompteClient(Client c, double montant) throws DeficitImpossibleException;

    Client fermerCompteClient(Client c, Compte compte) throws CompteInexistantException, AuMoinsUnCompteException;

    Client effectuerCreditCompte(Client c, Compte cm, double montant) throws CompteInexistantException;

    Client effectuerDebitCompte(Client c, Compte cm, double montant) throws DeficitImpossibleException, CompteInexistantException;

    Client effectuerVirementCompte(Client c, Compte cm, Long numeroCompte2, double montant) throws DeficitImpossibleException, CompteInexistantException;

    Client suppressionHistoriqueVirement(Client c, Compte cm) throws CompteInexistantException;
}
