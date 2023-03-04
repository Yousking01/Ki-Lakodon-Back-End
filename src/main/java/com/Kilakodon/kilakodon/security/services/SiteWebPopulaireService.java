
package com.Kilakodon.kilakodon.security.services;

import com.Kilakodon.kilakodon.models.SiteWebPopulaire;

import java.util.List;

public interface SiteWebPopulaireService {

    SiteWebPopulaire creer(SiteWebPopulaire siteWebPopulaire);

    SiteWebPopulaire getNomsitepopulaire(String nomsitepopulaire);

    List<SiteWebPopulaire> lire();

    SiteWebPopulaire modifier(Long id, SiteWebPopulaire siteWebPopulaire);

    String suprimer(Long id);


}

