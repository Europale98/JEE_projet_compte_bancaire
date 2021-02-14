package service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CompteRepository;
import entity.Compte;
import exception.CompteInexistantException;
import exception.DeficitImpossibleException;

@Service("compteService")
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
    public void effectuerCreditCompte(Compte c, double montant) {
        c.effectuerCredit(montant);
        repository.save(c);
    }

    @Override
    public void effectuerDebitCompte(Compte c, double montant) throws DeficitImpossibleException {
        c.effectuerDebit(montant);
        repository.save(c);
    }

    @Override
    public void effectuerVirementCompte(Compte c, Long numeroCompte2, double montant) throws DeficitImpossibleException, CompteInexistantException {
        Compte c2 = this.getCompteByNumero(numeroCompte2);
        c.effectuerVirement(montant, c2);
        repository.save(c);
        repository.save(c2);
    }

    @Override
    public void suppressionHistoriqueVirement(Compte c) {
        c.supprimerHistorique();
        repository.save(c);
    }

}
