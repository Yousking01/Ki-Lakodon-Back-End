
package com.Kilakodon.kilakodon.repository;

import com.Kilakodon.kilakodon.models.Annonce;
import com.Kilakodon.kilakodon.models.SiteWebPopulaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
    Annonce findByTitreannonce(String titreannonce);

    List<Annonce> findBySiteWebPopulaires(SiteWebPopulaire siteWebPopulaire);
}

