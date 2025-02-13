package com.example.gestioncom.commandes;

import com.example.gestioncom.clients.Client;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Commande {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name="id_client", nullable = false)
    private Client client;

    private String nom;

    public Commande() {}

    public Commande(Client client, String nom) {
        this.client = client;
        this.nom = nom;
    }

    public String getNom() { return this.nom; }
    public void setNom(String nom) { this.nom = nom; }
    public long getId() { return this.id; }
}
