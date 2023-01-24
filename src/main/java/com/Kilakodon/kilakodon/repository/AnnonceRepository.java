
package com.Kilakodon.kilakodon.repository;

import com.Kilakodon.kilakodon.models.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
    Annonce findByTitreannonce(String titreannonce);
}

