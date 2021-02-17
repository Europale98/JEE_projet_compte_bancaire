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
    
    boolean verificationAppartenance(Long numeroClient, Long numeroCompte);

    Client createClient(String nom, String prenom, String motDePasse, String numeroRue, String ville, double montant)
            throws DeficitImpossibleException;

    Client updateClient(Long numeroClient, String nom, String prenom, String motDePasse, String numeroRue, String ville) throws ClientInexistantException;

    void deleteClientByNumero(Long numeroClient);

    Client creerCompteClient(Long numeroClient, double montant) throws DeficitImpossibleException, ClientInexistantException;

    Client fermerCompteClient(Long numeroClient, Long numeroCompte) throws CompteInexistantException, AuMoinsUnCompteException, ClientInexistantException;

    Client effectuerCreditCompte(Long numeroClient, Long numeroCompte, double montant)
            throws CompteInexistantException, MontantImpossibleException;

    Client effectuerDebitCompte(Long numeroClient, Long numeroCompte, double montant)
            throws DeficitImpossibleException, CompteInexistantException, MontantImpossibleException;

    Client effectuerVirementCompte(Long numeroClient, Long numeroCompte, Long numeroCompte2, double montant)
            throws DeficitImpossibleException, CompteInexistantException, MontantImpossibleException, MemeCompteException;

    Client suppressionHistoriqueVirement(Long numeroClient, Long numeroCompte) throws CompteInexistantException;
}
