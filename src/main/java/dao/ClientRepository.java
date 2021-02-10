package dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import entity.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long>{
}
