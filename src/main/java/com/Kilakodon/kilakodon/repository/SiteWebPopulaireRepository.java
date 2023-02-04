
package com.Kilakodon.kilakodon.repository;


import com.Kilakodon.kilakodon.models.Annonce;
import com.Kilakodon.kilakodon.models.SiteWebPopulaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SiteWebPopulaireRepository extends JpaRepository<SiteWebPopulaire, Long> {

    SiteWebPopulaire findByNomsitepopulaire(String nomsitepopulaire);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `annonce_site_web_populaires`(`annonce_id`, `site_web_populaires_idsitewebpopulaire`) VALUES (:annonce_id,':site_web_populaires_idsitewebpopulaire')",nativeQuery = true)
    void inserAsite(@Param("annonce_id") Long annonce_id, @Param("site_web_populaires_idsitewebpopulaire") Long site_web_populaires_idsitewebpopulaire);



}

