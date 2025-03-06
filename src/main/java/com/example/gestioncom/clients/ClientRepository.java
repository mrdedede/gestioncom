package com.example.gestioncom.clients;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * Cette interface rajoute des possibilités de recherche sur la base de données
 * par email et mot de passe, que de base ne serait pas possible.
 */
public interface ClientRepository extends CrudRepository<Client, Long>{

    List<Client> findByEmailAndPassword(String email, String password);

}
