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

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/commande")
public class CommandeController {

    @Autowired
    CommandeService commandeService;

    @Autowired
    ArticleService articleService;

    @GetMapping("/{commandeId}")
    public ModelAndView getMethodName(@PathVariable Long commandeId) {
        Commande curCommande = commandeService.findById(commandeId);
        ModelAndView curView = new ModelAndView("commandeinfo.html");
        List<Article> articles = articleService.findByCommandeId(commandeId);
        curView.addObject("articles", articles);
        curView.addObject("commande", curCommande);
        return curView;
    }

    @PostMapping("/{commandeId}/ajouter")
    public RedirectView addArticle(@RequestParam String nomArticle, @PathVariable Long commandeId) {
        Commande curCommande = commandeService.findById(commandeId);
        Article curArticle = new Article(curCommande, nomArticle);
        articleService.save(curArticle);
        return new RedirectView("/commande/"+commandeId);
    }
}
