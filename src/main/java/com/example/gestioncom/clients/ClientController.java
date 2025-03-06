package com.example.gestioncom.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.gestioncom.commandes.Commande;
import com.example.gestioncom.commandes.CommandeService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Ce classe répresente le controlleur web de tous les routes où le client
 * c'est la pièce principale à définir - soit-ils les logins, signups et les changements
 * d'information.
 * 
 * Ce classe possède des références autoliées à deux services - celui pour le client et celui
 * pour les commandes.
 * 
 * @author André Filho
 * @see ClientService
 * @see CommandeService
 */

@Controller
@RequestMapping("/store") //Mapping nécessaire pour le projet comme prevu dans la consigne
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CommandeService commandeService;
    
    @GetMapping("home") //Accessible par le chemin store/home
    public ModelAndView homeScreen(HttpSession session) {
        /**
         * Fonction référent au écran de login, où si le login est bien fait,
         * l'utilisateur est rédirectioné vers sa page d'accueil, mais si il n'est pas,
         * il reste toujours sur la page de login
         * 
         * @param session {HttpSession} - Informations d'utilisateur
         */
        try {
            if((boolean) session.getAttribute("isLogged")) {
                // Si l'utilisateur est déjà loggé, il accède sa page d'accueil
                ModelAndView homeScreen = new ModelAndView("home.html");
                homeScreen.addObject("clients", session.getAttribute("client"));
                List<Commande> commandesClient = commandeService.findByClientId((Client) session.getAttribute("client"));
                homeScreen.addObject("commandes", commandesClient);
                return homeScreen;
            } else {
                return new ModelAndView("login.html");
            }
        } catch(Exception e) {
            // En cas d'exception, l'utilisateur doit refaire login pour leur sécurité
            return new ModelAndView("login.html");
        }
    }

    @PostMapping("login") //Accessible par le chemin store/login
    public RedirectView loginPost(@RequestParam String email, @RequestParam String password, HttpSession session) {
        /**
         * Fonction réprésentant le login d'un client.
         * Les informations du client sont rétrouvées au service client et la session HTTP est mise à jour
         * si le client a bien fait le login
         * 
         * @param email {String} - email client
         * @param password {String} - mot de passe client
         * @param session {HttpSession} - session http du client
         */
        try {
            session.setAttribute("client", clientService.login(email, password));
            session.setAttribute("isLogged", true);
            return new RedirectView("/store/home");
        } catch(Exception e) {
            return new RedirectView("/store/home");
        }
    }

    @PostMapping("signup") //Accessible par le chemin store/signup
    public RedirectView signUpPost(@RequestParam String email, @RequestParam String password,
            @RequestParam String nom, @RequestParam String prenom, HttpSession session) {
        /**
         * Fonction réprésentant la création d'une compte par un client.
         * Les informations du client sont mises au service client et la session HTTP est mise à jour
         * si le client n'a pas encore une compte
         * 
         * @param email {String} - email client
         * @param password {String} - mot de passe client
         * @param nom {String} - nom de famille du client
         * @param prenom {String} - prenom du client
         * @param session {HttpSession} - session http du client
         */
        try {
            session.setAttribute("client", clientService.signup(email, password, nom, prenom));
            session.setAttribute("isLogged", true);
            return new RedirectView("/store/home");
        } catch(Exception e) {
            return new RedirectView("/store/home");
        }
    }

    @PostMapping("logout") //Accessible par le chemin store/logout
    public RedirectView postMethodName(HttpSession session) {
        /**
         * Ce methode met à jour la session http, en vidant les informations mises
         * à la session http et en deletant ses attributs pour que la session soit vide
         * pour une prochaine login
         * 
         * @param session {HttpSession} - Session http à mettre à jour
         */
        session.removeAttribute("client");
        session.removeAttribute("isLogged");
        return new RedirectView("/store/home");
    }
    
    
    @PostMapping("commande/creation") //Accessible par le chemin store/commande/creation
    public RedirectView creation(@RequestParam String nom, HttpSession session) {
        /**
         * Ce méthode gère la création des commandes par un client connecté
         * 
         * @param nom {String} - Nom da commande fourni par le client
         * @param session {HttpSession} - Session HTTP du client logué au moment
         */
        commandeService.createCommande((Client) session.getAttribute("client"), nom);
        return new RedirectView("/store/home");
    }
}
