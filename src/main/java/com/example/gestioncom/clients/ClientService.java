package com.example.gestioncom.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository repo;

    public Client login(String email, String password) throws Exception {
        List<Client> loginPossibilities = repo.findByEmailAndPassword(email, password);
        System.out.println(loginPossibilities);
        if(loginPossibilities.size() == 0) {
            throw new Exception("Login failed");
        } else {
            return loginPossibilities.get(0);
        }
    }

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

    public List<Client> findByEmailAndPassword(String email, String password) {
        return repo.findByEmailAndPassword(email, password);
    }
}
