package com.example.gestioncom.articles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    
    @Autowired
    private ArticleRepository repo;

    public List<Article> findByCommandeId(Long commandeId) {
        return repo.findByCommandeId(commandeId);
    }

    public void save(Article article){
        repo.save(article);
    }
}
