package com.example.gestioncom.commande;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Commande {

    @Id
    @GeneratedValue
    private long id;

    private String nom;
    private long idClient;

    public Commande() {}

    public Commande(String nom, long idClient) {
        this.nom = nom;
        this.idClient = idClient;
    }

    public void setNom(String nom) { this.nom = nom; }
    public void setIdClient(long idClient) { this.idClient = idClient; }
    public String getNom() { return this.nom; }
    public long getIdClient() { return this.idClient; }
}
