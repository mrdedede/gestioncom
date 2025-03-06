package com.example.gestioncom.commandes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.gestioncom.articles.Article;
import com.example.gestioncom.articles.ArticleService;
import com.example.gestioncom.clients.Client;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;

/**
 * Cette classe répresente le controlleur web de tous les routes où les commandes
 * est la pièce principale à définir - soit-ils leur création, rajout, rémotion d'articles
 * et les changements d'information.
 * 
 * Ce classe possède des références autoliées à deux services - celui pour le articles et celui
 * pour les commandes.
 * 
 * @author André Filho
 * @see ClientService
 * @see ArticleService
 */


@Controller
@RequestMapping("/commande")
public class CommandeController {

    @Autowired
    CommandeService commandeService;

    @Autowired
    ArticleService articleService;

    /**
     * Cette méthode fait la fonction de checage initial pour être sur que le client
     * connecté au moment est vraiment le client qui possède la commande qui est recherché
     * La checage se passe sur la base de données des commandes, où le client est recherché et, si
     * c'est le mauvais client, le résultat est faux.
     * 
     * @param session - Session HTTP du client connecté
     * @param curCommandeId - La commande qui est recherché au moment
     * @return - Si le client est le bon ou pas
     */
    public boolean securityChecking(HttpSession session, Long curCommandeId) {
        if ((boolean) session.getAttribute("isLogged")) {
            List<Commande> clientComs = commandeService.findByClientId((Client) session.getAttribute("client"));
            for(int i = 0; i < clientComs.size(); i++) {
                if(clientComs.get(i).getId() == curCommandeId) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Méthode principale des accès aux commandes, où la première étape est référente à
     * la checage de sécurité sur securityChecking.
     * Après la validation, un objet de visualisation est crée, si le client est le correct,
     * il peut voir sa commande et les articles à ajouter, au contraire, il véra une page d'erreur 403.
     * @param commandeId - L'ID de la comande à acceder
     * @param session - La session HTTP du client connecté
     * @return - Soit la page de la commande, soit une page d'erreur
     */
    @GetMapping("/{commandeId}") // Accedé par le chemin /commande/{commandeId}
    public ModelAndView getMethodName(@PathVariable Long commandeId, HttpSession session) {
        boolean secCheck = securityChecking(session, commandeId);
        if(secCheck) {
            Commande curCommande = commandeService.findById(commandeId);
            ModelAndView curView = new ModelAndView("commandeinfo.html");
            List<Article> articles = articleService.findByCommandeId(commandeId);
            curView.addObject("articles", articles);
            curView.addObject("commande", curCommande);
            return curView;
        } else {
            return new ModelAndView("error403.html");
        }
    }

    @PostMapping("/{commandeId}/ajouter") // Accédé par /commande/{commandeId}/ajouter
    public RedirectView addArticle(@RequestParam String nomArticle, @PathVariable Long commandeId) {
        Commande curCommande = commandeService.findById(commandeId);
        Article curArticle = new Article(curCommande, nomArticle);
        articleService.save(curArticle);
        return new RedirectView("/commande/"+commandeId);
    }

    @PostMapping("/{commandeId}/effacer/{articleId}")
    public RedirectView removeArticle(@PathVariable Long commandeId, @PathVariable Long articleId) {
        Article curArticle = articleService.findById(articleId);
        articleService.delete(curArticle);
        return new RedirectView("/commande/"+commandeId);
    }

    @PostMapping("/{commandeId}/imprimer")
    public ModelAndView addArticle(@PathVariable Long commandeId) {
        List<Article> curArticles = articleService.findByCommandeId(commandeId);
        ModelAndView commandeView = new ModelAndView("impressioncommande.html");
        commandeView.addObject("articles", curArticles);
        return commandeView;
    }
}
