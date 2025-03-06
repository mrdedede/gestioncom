package com.example.gestioncom.commandes;

import com.example.gestioncom.clients.Client;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Cette classe réprésente une entité Commande, qui contient
 * des attributs liées à elle dans la base de données.
 * 
 * Elle est utilisé pour gérer des articles et donner l'utilité aux clients.
 */

@Entity
public class Commande {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name="id_client", nullable = false)
    private Client client;

    private String nom;

    // Suppression des constructeurs defaults pour assurer l'impossibilité d'instaces distincts
    public Commande() {}

    public Commande(Client client, String nom) {
        /**
         * Création d'un objet Commande étant donné son mail, mot de passe, nom et prénom.
         * Il réprésente une instance d'un commande sur notre base de données
         * 
         * @param client {Client} - L'objet Client de ce que lui a crée
         * @param nom {String} - Nom de la commande fourni par l'utilisateur
         */
        this.client = client;
        this.nom = nom;
    }

    // Méthodes getters et setters utilisées
    public String getNom() { return this.nom; }
    public void setNom(String nom) { this.nom = nom; }
    public long getId() { return this.id; }
}
