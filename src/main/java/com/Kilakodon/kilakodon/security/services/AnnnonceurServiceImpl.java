package com.Kilakodon.kilakodon.security.services;

import com.Kilakodon.kilakodon.models.Annnonceur;
import com.Kilakodon.kilakodon.models.Annonce;
import com.Kilakodon.kilakodon.models.EspacePub;
import com.Kilakodon.kilakodon.repository.AnnnonceurRepository;
import com.Kilakodon.kilakodon.repository.AnnonceRepository;
import com.Kilakodon.kilakodon.repository.EspacePubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnnonceurServiceImpl {

    @Autowired
    private AnnnonceurRepository annonceurRepository;

    /*@Autowired
    private EspacePubRepository espacePubRepository;*/

    @Autowired
    private AnnonceRepository annonceRepository;



    public Annnonceur creer(Annnonceur annonceur) {

        return annonceurRepository.save(annonceur);
    }

    public List<Annnonceur> lire() {
        return annonceurRepository.findAll();
    }

   // @Override
    public Annnonceur modifier(Long id, Annnonceur annnonceur) {
        return annonceurRepository.findById(id)
                .map(p->{
                    p.setEmail(annnonceur.getEmail());
                    p.setUsername(annnonceur.getUsername());
                    p.setPassword(annnonceur.getPassword());
                    p.setAdrresseannonceur(annnonceur.getAdrresseannonceur());
                    p.setNumeroannonceur(annnonceur.getNumeroannonceur());
                    p.setBudgetannonceur(annnonceur.getBudgetannonceur());

                    /*p.setAnnonceur(p.getAnnonceur());
                    p.setSiteWebPopulaires(p.getSiteWebPopulaires());
                    p.setNotification(p.getNotification());*/
                    return annonceurRepository.save(p);
                }).orElseThrow(()-> new RuntimeException("Annonceur non trouvé !"));
    }

    //@Override
  /*  public String supprimer(long id) {
        annonceurRepository.deleteById(id);
        return "Annonceur Suprimer avec Succèss";
    }*/
// Méthode pour supprimer toutes les annonces liées à un annonceur spécifique
    /*public void supprimerAnnoncesParAnnonceur(Long id) {
        Optional<Annonce> annonces = annonceRepository.findById(id);
        if (!annonces.isEmpty()) {
            annonceRepository.deleteAll(annonces);
        }
    }*/

   /* public String Acheter(long idannonceur, Long idespacepub) {
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
    }*/
}
