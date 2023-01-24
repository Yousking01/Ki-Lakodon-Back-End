
package com.Kilakodon.kilakodon.security.services;


import com.Kilakodon.kilakodon.models.*;
import com.Kilakodon.kilakodon.repository.AnnonceurRepository;
import com.Kilakodon.kilakodon.repository.EspacePubRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class AnnonceurServiceImpl
        implements AnnonceurService {

    @Autowired
    private AnnonceurRepository annonceurRepository;

    @Autowired
    private EspacePubRepository espacePubRepository;


    @Override
    public Annonceur creer(Annonceur annonceur) {

        return annonceurRepository.save(annonceur);
    }

    /*@Override
    public Optional<Annonceur> getNomannonceur(String nomannonceur) {
        return annonceurRepository.findByUsername(nomannonceur);
    }*/

    @Override
    public List<Annonceur> lire() {
        return annonceurRepository.findAll();
    }


/*@Override
    public Annonceur modifier(Long idannonceur, Annonceur annonceur) {
        return annonceurRepository.findById(idannonceur)
                .map(p->{
                    p.setUsername (annonceur.getUsername());
                    p.setAdrresseannonceur(annonceur.getAdrresseannonceur());
                    p.setEmail(annonceur.getEmail());
                    p.setNumeroannonceur(annonceur.getNumeroannonceur());
                    p.setBudgetannonceur(annonceur.getBudgetannonceur());
                    return annonceurRepository.save(annonceur);
                }).orElseThrow(()-> new RuntimeException("Annonceur non trouver"));
    }*/


    @Override
    public String suprimer(long idannonceur) {
        annonceurRepository.deleteById(idannonceur);
        return "Annonceur Suprimer avec Succèss";
    }

    @Override
    public String Acheter(long idannonceur, Long idespacepub) {
        Annonceur annonceur=annonceurRepository.findById(idannonceur).orElse(null);
        EspacePub espacePub=espacePubRepository.findById(idespacepub).orElse(null);
        if (annonceur != null && espacePub != null){
            int budgetannonceur = annonceur.getBudgetannonceur();
            int prix = espacePub.getPrix();
            if (budgetannonceur>= prix){
                annonceur.setBudgetannonceur(budgetannonceur-prix);
                //espacePub.(EspacePubEtat.ESPACE_PUB_VENDU);
                annonceurRepository.save(annonceur);
                espacePubRepository.save(espacePub);
            }else {
                throw new IllegalArgumentException("Le budget de l'annonceur est insuffisant pour acheter l'espace publicitaire");

            }
        }else {
            throw new IllegalArgumentException("Annonceur ou espace publicitaire introuvable");
        }

        return "Espace Achéter avec Succèss";
    }
}

