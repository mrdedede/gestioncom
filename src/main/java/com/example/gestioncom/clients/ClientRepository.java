package com.example.gestioncom.clients;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long>{

    List<Client> findByEmailAndPassword(String email, String password);

}
