
package com.Kilakodon.kilakodon.repository;


import com.Kilakodon.kilakodon.models.SiteWebPopulaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteWebPopulaireRepository extends JpaRepository<SiteWebPopulaire, Long> {

    SiteWebPopulaire findByNomsitepopulaire(String nomsitepopulaire);

}

