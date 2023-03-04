
package com.Kilakodon.kilakodon.repository;

import com.Kilakodon.kilakodon.models.Annnonceur;
import com.Kilakodon.kilakodon.models.Annonce;
import com.Kilakodon.kilakodon.models.SiteWebPopulaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
    Annonce findByTitreannonce(String titreannonce);
    //Annonce findByIdannonce(Long idannonce);

    List<Annonce> findBySiteWebPopulaires(SiteWebPopulaire siteWebPopulaire);
    List<Annonce> findByAnnonceur(Annnonceur annnonceur);

    //Annonce annonce = annonceRepository.getReferenceById(idannonce);

   // void deleteAll(Optional<Annonce> annonces);
}

