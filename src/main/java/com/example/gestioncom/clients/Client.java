package com.example.gestioncom.clients;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Client {
    
    @Id
    @GeneratedValue
    private long id;

    private String email;
    private String password;
    private String nom;
    private String prenom;

    public Client() {}

    public Client(String email, String password, String nom, String prenom) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
    }

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
