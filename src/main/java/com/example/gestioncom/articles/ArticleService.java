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

    public Article findById(Long id) {
        try {
            return repo.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    public void save(Article article){
        repo.save(article);
    }

    public void delete(Article article) {
        repo.delete(article);
    }
}
