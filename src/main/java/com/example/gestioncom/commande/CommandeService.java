package com.example.gestioncom.commande;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestioncom.clients.Client;

@Service
public class CommandeService {
    
    @Autowired
    private CommandeRepository repo;

    public List<Commande> findByClientId(Client client) {
        return repo.findByClientId(client.getId());
    }

    public Commande createCommande(Client client, String nom) {
        Commande curCommande = new Commande(client, nom);
        repo.save(curCommande);
        return curCommande;
    }

}
