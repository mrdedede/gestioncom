// Source code is decompiled from a .class file using FernFlower decompiler.
package com.example.gestioncom.commande;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CommandeRepository extends CrudRepository<Commande, Long> {
   List<Commande> findByClientId(long clientId);
}
