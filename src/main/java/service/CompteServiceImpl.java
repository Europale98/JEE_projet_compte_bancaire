package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.CompteRepository;
import entity.Client;
import entity.Compte;
import entity.Virement;
import exception.CompteInexistantException;
import exception.DeficitImpossibleException;
import exception.MontantImpossibleException;

@Service("compteService")
@Transactional
public class CompteServiceImpl implements CompteService {
    private static CompteServiceImpl serviceInstance = null;
    @Autowired
    private CompteRepository repository;

    private CompteServiceImpl() {
    }

    public static CompteServiceImpl getInstance() {
        if (serviceInstance == null) {
            serviceInstance = new CompteServiceImpl();
        }
        return serviceInstance;
    }

    @Override
    public Compte getCompteByNumero(Long numeroCompte) throws CompteInexistantException {
        Optional<Compte> c = repository.findById(numeroCompte);
        if (c.isPresent())
            return c.get();
        else
            throw new CompteInexistantException();
    }

    @Override
    public void effectuerCreditCompte(Long numeroCompte, double montant) throws MontantImpossibleException, CompteInexistantException {
        Compte c = this.getCompteByNumero(numeroCompte);
        c.effectuerCredit(montant);
        repository.save(c);
    }

    @Override
    public void effectuerDebitCompte(Long numeroCompte, double montant) throws DeficitImpossibleException, MontantImpossibleException, CompteInexistantException {
        Compte c = this.getCompteByNumero(numeroCompte);
        c.effectuerDebit(montant);
        repository.save(c);
    }

    @Override
    public void effectuerVirementCompte(Long numeroCompte, Long numeroCompte2, double montant)
            throws DeficitImpossibleException, CompteInexistantException, MontantImpossibleException {
        Compte c = this.getCompteByNumero(numeroCompte);
        Compte c2 = this.getCompteByNumero(numeroCompte2);
        Virement v = c.effectuerVirement(montant, c2);
        c = repository.save(c);
        v = c.getVirementDebit(v);
        c2.updateVirementCredit(v);
        c2 = repository.save(c2);
    }

    @Override
    public void suppressionHistoriqueVirement(Long numeroCompte) throws CompteInexistantException {
        Compte c = this.getCompteByNumero(numeroCompte);
        c.changeVirementPourSupression();
        repository.save(c);
        c.supprimerHistorique();
        repository.save(c);
    }

}
