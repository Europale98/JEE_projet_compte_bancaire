package service;

import java.util.List;

import entity.Client;
import entity.Compte;
import exception.AuMoinsUnCompteException;
import exception.ClientInexistantException;
import exception.CompteInexistantException;
import exception.DeficitImpossibleException;
import exception.MemeCompteException;
import exception.MontantImpossibleException;

public interface ClientService {
    Client getClientByNumero(Long numeroClient) throws ClientInexistantException;

    List<Client> getAllClient();

    boolean verificationMotDePasse(Long numeroClient, String motDePasse);

    Client createClient(String nom, String prenom, String motDePasse, String numeroRue, String ville, double montant)
            throws DeficitImpossibleException;

    Client updateClient(Client c, String nom, String prenom, String motDePasse, String numeroRue, String ville);

    void deleteClient(Client c);

    void deleteClientByNumero(Long numeroClient);

    Client creerCompteClient(Client c, double montant) throws DeficitImpossibleException;

    Client fermerCompteClient(Client c, Long numeroCompte) throws CompteInexistantException, AuMoinsUnCompteException;

    Client effectuerCreditCompte(Client c, Long numeroCompte, double montant)
            throws CompteInexistantException, MontantImpossibleException;

    Client effectuerDebitCompte(Client c, Long numeroCompte, double montant)
            throws DeficitImpossibleException, CompteInexistantException, MontantImpossibleException;

    Client effectuerVirementCompte(Client c, Long numeroCompte, Long numeroCompte2, double montant)
            throws DeficitImpossibleException, CompteInexistantException, MontantImpossibleException, MemeCompteException;

    Client suppressionHistoriqueVirement(Client c, Long numeroCompte) throws CompteInexistantException;
}
