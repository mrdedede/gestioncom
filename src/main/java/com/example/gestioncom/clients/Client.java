package com.example.gestioncom.clients;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Cette classe spécifie une entité Client qui va être utilisé par les procedures
 * de login et sécurité des routes de l'application
 * 
 * @author André Filho
 */

@Entity
public class Client {
    
    @Id
    @GeneratedValue
    private long id;

    private String email;
    private String password;
    private String nom;
    private String prenom;

    // Suppression des constructeurs defaults pour assurer l'impossibilité d'instaces distincts
    public Client() {}

    public Client(String email, String password, String nom, String prenom) {
        /**
         * Création d'un objet Client étant donné son mail, mot de passe, nom et prénom.
         * Il réprésente une instance d'un client sur notre base de données
         * 
         * @param email {String} - Le mail du client
         * @param password {String} - Le mot de passe du client
         * @param nom {String} - Le nom de famille du client
         * @param prenom {String} - Le prenom du client
         */
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
    }

    // Méthodes Getters et Setters pour les attributs de la classe
    public String getEmail() { return this.email; }
    public String getPassword() { return this.password; }
    public String getNom() { return this.nom; }
    public String getPrenom() { return this.prenom; }
    public long getId() { return this.id; }
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
    public void setNom(String nom) {this.nom = nom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}

}
