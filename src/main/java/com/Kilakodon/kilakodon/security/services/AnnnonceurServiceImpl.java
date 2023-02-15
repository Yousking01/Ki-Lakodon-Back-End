package com.Kilakodon.kilakodon.security.services;

import com.Kilakodon.kilakodon.models.Annnonceur;
import com.Kilakodon.kilakodon.models.EspacePub;
import com.Kilakodon.kilakodon.repository.AnnnonceurRepository;
import com.Kilakodon.kilakodon.repository.EspacePubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnnonceurServiceImpl {

    @Autowired
    private AnnnonceurRepository annonceurRepository;

    @Autowired
    private EspacePubRepository espacePubRepository;



    public Annnonceur creer(Annnonceur annonceur) {

        return annonceurRepository.save(annonceur);
    }

    public List<Annnonceur> lire() {
        return annonceurRepository.findAll();
    }


    public String suprimer(long idannonceur) {
        annonceurRepository.deleteById(idannonceur);
        return "Annonceur Suprimer avec Succèss";
    }


    public String Acheter(long idannonceur, Long idespacepub) {
        Annnonceur annonceur=annonceurRepository.findById(idannonceur).orElse(null);
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
