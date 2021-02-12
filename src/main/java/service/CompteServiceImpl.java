package service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CompteRepository;
import entity.Compte;

@Service("compteService")
public class CompteServiceImpl implements CompteService {
    private static CompteServiceImpl serviceInstance = null;
    @Autowired
    private CompteRepository repository;
    
    private CompteServiceImpl() {
    }
    
    public static CompteServiceImpl getInstance() {
        if(serviceInstance == null) {
            serviceInstance = new CompteServiceImpl();
        }
        return serviceInstance;
    }

    @Override
    public Compte getCompteByNumero(Long numeroCompte) {
        Optional<Compte> c = repository.findById(numeroCompte);
        if(c.isPresent())
            return c.get();
        else
            return null;
    }

    @Override
    public void effectuerCreditCompte(Compte c, double montant) {
        c.effectuerCredit(montant);
        System.out.println("COmptes -->" + c);
        repository.save(c);
    }

    @Override
    public void effectuerDebitCompte(Compte c, double montant) {
        c.effectuerDebit(montant);
        repository.save(c);
    }

    @Override
    public void effectuerVirementCompte(Compte c, Long numeroCompte2,
            double montant) {
        Compte c2 = this.getCompteByNumero(numeroCompte2);
        c.effectuerVirement(montant, c2);
        repository.save(c);
    }

    @Override
    public void suppressionHistoriqueVirement(Compte c) {
        c.supprimerHistorique();
        repository.save(c);
    }

    
    
    
}
