package service;

import entity.Compte;
import exception.CompteInexistantException;
import exception.DeficitImpossibleException;
import exception.MontantImpossibleException;

public interface CompteService {
    Compte getCompteByNumero(Long numeroCompte) throws CompteInexistantException;

    void effectuerCreditCompte(Compte c, double montant) throws MontantImpossibleException;

    void effectuerDebitCompte(Compte c, double montant) throws DeficitImpossibleException, MontantImpossibleException;

    void effectuerVirementCompte(Compte c, Long numeroCompte2, double montant) throws DeficitImpossibleException, CompteInexistantException, MontantImpossibleException;

    void suppressionHistoriqueVirement(Compte c);
}
