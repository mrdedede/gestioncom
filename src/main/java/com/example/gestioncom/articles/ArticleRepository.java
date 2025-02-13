package com.example.gestioncom.articles;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long>{

    List<Article> findByCommandeId(long commandeId);

    
}
