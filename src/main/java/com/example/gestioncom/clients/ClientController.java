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



@Controller
@RequestMapping("/store")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CommandeService commandeService;
    
    @GetMapping("home")
    public ModelAndView homeScreen(HttpSession session) {
        try {
            if((boolean) session.getAttribute("isLogged")) {
                ModelAndView homeScreen = new ModelAndView("home.html");
                homeScreen.addObject("clients", session.getAttribute("client"));
                List<Commande> commandesClient = commandeService.findByClientId((Client) session.getAttribute("client"));
                homeScreen.addObject("commandes", commandesClient);
                return homeScreen;
            } else {
                return new ModelAndView("login.html");
            }
        } catch(Exception e) {
            return new ModelAndView("login.html");
        }
    }

    @PostMapping("login")
    public RedirectView loginPost(@RequestParam String email, @RequestParam String password, HttpSession session) {
        try {
            session.setAttribute("client", clientService.login(email, password));
            session.setAttribute("isLogged", true);
            return new RedirectView("/store/home");
        } catch(Exception e) {
            return new RedirectView("/store/home");
        }
    }

    @PostMapping("signup")
    public RedirectView signUpPost(@RequestParam String email, @RequestParam String password,
            @RequestParam String nom, @RequestParam String prenom, HttpSession session) {
        try {
            session.setAttribute("client", clientService.signup(email, password, nom, prenom));
            session.setAttribute("isLogged", true);
            return new RedirectView("/store/home");
        } catch(Exception e) {
            return new RedirectView("/store/home");
        }
    }

    @PostMapping("logout")
    public RedirectView postMethodName(HttpSession session) {
        session.removeAttribute("client");
        session.removeAttribute("isLogged");
        return new RedirectView("/store/home");
    }
    
    
    @PostMapping("commande/creation")
    public RedirectView creation(@RequestParam String nom, HttpSession session) {
        commandeService.createCommande((Client) session.getAttribute("client"), nom);
        return new RedirectView("/store/home");
    }
}
