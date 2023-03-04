
package com.Kilakodon.kilakodon.security.services;

import com.Kilakodon.kilakodon.models.Annonce;
import com.Kilakodon.kilakodon.repository.AnnonceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AnnonceServiceImpl implements AnnonceService{

    @Autowired
    private AnnonceRepository AnnonceRepository;

    @Override
    public Annonce creer(Annonce annonce) {
        return AnnonceRepository.save(annonce);
    }

    @Override
    public Annonce getTitreAnnonce(String titreannonce) {
        return AnnonceRepository.findByTitreannonce(titreannonce);
    }

    @Override
    public List<Annonce> lire() {
        return AnnonceRepository.findAll();
    }

    @Override
    public Annonce modifier(Long idannonce, Annonce annonce) {
        return AnnonceRepository.findById(idannonce)
                .map(p->{
                    p.setTitreannonce(annonce.getTitreannonce());
                    p.setDescriptionannonce(annonce.getDescriptionannonce());
                    /*p.setCiblediffusionannonce(annonce.getCiblediffusionannonce());*/
                    p.setPrixannonce(annonce.getPrixannonce());
                    p.setDateFin(annonce.getDateFin());
                    p.setImage(annonce.getImage());
                    p.setAnnonceur(p.getAnnonceur());
                    p.setSiteWebPopulaires(p.getSiteWebPopulaires());
                    p.setNotification(p.getNotification());
                    return AnnonceRepository.save(p);
                }).orElseThrow(()-> new RuntimeException("Annonce non trouvé !"));
    }

    @Override
    public String supprimer(long idannonce) {
        AnnonceRepository.deleteById(idannonce);
        return "Annonce Supprimer avec succès";
    }
}

