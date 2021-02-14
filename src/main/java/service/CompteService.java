package service;

import entity.Compte;
import exception.CompteInexistantException;
import exception.DeficitImpossibleException;

public interface CompteService {
    Compte getCompteByNumero(Long numeroCompte) throws CompteInexistantException;

    void effectuerCreditCompte(Compte c, double montant);

    void effectuerDebitCompte(Compte c, double montant) throws DeficitImpossibleException;

    void effectuerVirementCompte(Compte c, Long numeroCompte2, double montant) throws DeficitImpossibleException, CompteInexistantException;

    void suppressionHistoriqueVirement(Compte c);
}
