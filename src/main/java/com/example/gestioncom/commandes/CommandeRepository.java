// Source code is decompiled from a .class file using FernFlower decompiler.
package com.example.gestioncom.commandes;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * Cette interface extend le Repository CRUD (où les opérations des base de données
 * liées aux commandes) sont faites, en rajoutant la possibilité de chercher des commandes
 * liées à un ID de client, sachant que cela est la clé étrangère dans cette base de données.
 */

public interface CommandeRepository extends CrudRepository<Commande, Long> {
   List<Commande> findByClientId(long clientId);
}
