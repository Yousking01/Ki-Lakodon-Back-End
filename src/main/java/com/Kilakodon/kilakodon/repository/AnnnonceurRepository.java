package com.Kilakodon.kilakodon.repository;

import com.Kilakodon.kilakodon.models.Annnonceur;
import com.Kilakodon.kilakodon.models.Annonce;
import com.Kilakodon.kilakodon.models.SiteWebPopulaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AnnnonceurRepository extends JpaRepository<Annnonceur, Long> {
    Optional<Annnonceur> findByUsername(String username);

    Optional<Annnonceur> findById(Long id);
    //List<Annonce> findBySiteWebPopulaires(SiteWebPopulaire siteWebPopulaire);
    List<Annnonceur> findByAnnonce(Annonce annonce);
    //List<Annnonceur> annonceurs = annnonceurRepository.findByAnnonce(annonce);
}
