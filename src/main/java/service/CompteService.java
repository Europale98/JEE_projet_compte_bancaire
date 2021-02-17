package service;

import entity.Compte;
import exception.CompteInexistantException;
import exception.DeficitImpossibleException;
import exception.MontantImpossibleException;

public interface CompteService {
    Compte getCompteByNumero(Long numeroCompte) throws CompteInexistantException;

    void effectuerCreditCompte(Long numeroCompte, double montant) throws MontantImpossibleException, CompteInexistantException;

    void effectuerDebitCompte(Long numeroCompte, double montant) throws DeficitImpossibleException, MontantImpossibleException, CompteInexistantException;

    void effectuerVirementCompte(Long numeroCompte, Long numeroCompte2, double montant) throws DeficitImpossibleException, CompteInexistantException, MontantImpossibleException;

    void suppressionHistoriqueVirement(Long numeroCompte) throws CompteInexistantException;
}
