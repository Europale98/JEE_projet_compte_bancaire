package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CompteRepository;
import entity.Compte;


@Service("compteService")
public class CompteService {
    @Autowired
    private CompteRepository repository;
    
    public void deleteCompte(Compte c) {
        repository.delete(c);
    }
}
