package com.example.gestioncom.clients;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientInterface extends CrudRepository<Client, Long>{

    List<Client> findByEmailAndPassword(String email, String password);
}
