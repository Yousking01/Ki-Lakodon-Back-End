
package com.Kilakodon.kilakodon.security.services;

import com.Kilakodon.kilakodon.models.SiteWebPopulaire;
import com.Kilakodon.kilakodon.repository.SiteWebPopulaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteWebPopulaireServiceImpl implements SiteWebPopulaireService {

    @Autowired
    private SiteWebPopulaireRepository siteWebPopulaireRepository;

    @Override
    public SiteWebPopulaire creer(SiteWebPopulaire siteWebPopulaire) {
        return siteWebPopulaireRepository.save(siteWebPopulaire);
    }

    @Override
    public SiteWebPopulaire getNomsitepopulaire(String nomsitepopulaire) {
        return siteWebPopulaireRepository.findByNomsitepopulaire(nomsitepopulaire);
    }

    @Override
    public List<SiteWebPopulaire> lire() {
        return siteWebPopulaireRepository.findAll();
    }

    @Override
    public SiteWebPopulaire modifier(Long id, SiteWebPopulaire siteWebPopulaire) {

        return siteWebPopulaireRepository.findById(id)
                .map(p->{
                    p.setNomsitepopulaire(siteWebPopulaire.getNomsitepopulaire());
                    p.setURL(siteWebPopulaire.getURL());
                    return siteWebPopulaireRepository.save(siteWebPopulaire);
                }).orElseThrow(()-> new RuntimeException("Site web non trouver"));
    }

    @Override
    public String suprimer(Long id) {
        siteWebPopulaireRepository.deleteById(id);
        return "Site Web Suprimer avec succès !!!";
    }
}

