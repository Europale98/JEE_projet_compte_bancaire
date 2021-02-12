package dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import entity.Compte;

@Repository
public interface CompteRepository extends CrudRepository<Compte, Long> {
}
