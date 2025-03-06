package com.example.gestioncom.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Ce classe réprésente tous les transactions possibles sur la base de données
 * (service) qui sont liées au cycle de vie du client sur l'application.
 */

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository repo;

    /**
     * Cette méthode fait le cycle d'un login par un client, où la
     * recherche dans la base de données par email et mot de passe
     * est le principal facteur.
     * @param email - email du client
     * @param password - mot de passe client
     * @return
     * @throws Exception - Crée si le login n'est pas possible
     */
    public Client login(String email, String password) throws Exception {
        List<Client> loginPossibilities = repo.findByEmailAndPassword(email, password);
        System.out.println(loginPossibilities);
        if(loginPossibilities.size() == 0) {
            throw new Exception("Login failed");
        } else {
            return loginPossibilities.get(0);
        }
    }

    /**
     * Cette méthode fait le cycle du signup par un client, où les nouvelles informations
     * sont mises à la base de données si le client n'a pas encore une compte
     * @param email - email du client
     * @param password - mot de passe client
     * @param nom - nom de famille du client
     * @param prenom - prenom du client
     * @return - L'objet du client crée
     * @throws Exception - Crée si le client a déjà une compte
     */
    public Client signup(String email, String password, String nom, String prenom) throws Exception  {
        List<Client> loginPossibilities = findByEmailAndPassword(email, password);
        if(loginPossibilities.size() > 0) {
            throw new Exception("Can't sing up, e-mail already has an account");
        } else {
            Client curClient = new Client(email, password, nom, prenom);
            repo.save(curClient);
            return curClient;
        }
    }

    /**
     * Cette méthode recherche dans la base de données un email et un mot de passe
     * pour trouver un client spécifique - cette fonction est utile pour faire login
     * et pour être sur qu'un client ne peut avoir que une compte.
     * 
     * @param email - email du client
     * @param password - mot de passe client
     * @return - Une liste de clients (on espère qu'avec un seul résultat)
     */
    public List<Client> findByEmailAndPassword(String email, String password) {
        return repo.findByEmailAndPassword(email, password);
    }
}
