package service;

import entity.Compte;

public interface CompteService {
    Compte getCompteByNumero(Long numeroCompte);
    
    void effectuerCreditCompte(Compte c, double montant);
    
    void effectuerDebitCompte(Compte c, double montant);
    
    void effectuerVirementCompte(Compte c, Long numeroCompte2, double montant);
    
    void suppressionHistoriqueVirement(Compte c);
}
