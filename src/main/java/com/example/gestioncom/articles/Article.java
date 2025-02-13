package com.example.gestioncom.articles;

import com.example.gestioncom.commandes.Commande;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="commande_id", nullable = false)
    private Commande commande;

    private String nom;

    public Article() {}

    public Article(Commande commande, String nom) {
        this.commande = commande;
        this.nom = nom;
    }

    public String getNom() { return this.nom; }
    public void setNom(String nom) { this.nom = nom; }
    public long getId() { return this.id; }
}
