package com.Kilakodon.kilakodon.controllers;

import com.Kilakodon.kilakodon.models.*;
import com.Kilakodon.kilakodon.repository.*;
import com.Kilakodon.kilakodon.security.services.AnnnonceurServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/annonceur")
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AnnnonceurController {

    private final AnnnonceurServiceImpl annonceurService;

    //private final EspacePubRepository espacePubRepository;

    private final KilakodonRepository kilakodonRepository;
    private final UserRepository userRepository;
    private final AnnnonceurRepository annnonceurRepository;
    private final AnnonceRepository annonceRepository;
    //private final AnnnonceurRepository annnonceurRepository;

    @PostMapping("/creer/{idKilakodon}")
    public Annnonceur Ajouter(
            @RequestBody Annnonceur annonceur, Long idespacepub, @PathVariable("idKilakodon") Long idKilakodon
    )
    {
        //EspacePub espacePub = espacePubRepository.findById(idespacepub).get();
        //annonceur.setEspacePub(espacePub);
        Kilakodon kilakodon = kilakodonRepository.findById(idKilakodon).get();
        annonceur.setKilakodon(kilakodon);
        if(annonceur.getBudgetannonceur() <= 9999) {

            throw new IllegalArgumentException("Le budget doit être supérieur à 10000 fr.");
        }
        return annonceurService.creer(annonceur);
    }

    @PutMapping("/modifier/{id}")
    public Annnonceur modifier(@RequestBody Annnonceur annnonceur, @PathVariable Long id){
        return annonceurService.modifier(id, annnonceur);

         // ResponseEntity.status(HttpStatus.OK).body("votre annonce à été modifier avec succèss");

    }



    @GetMapping("/lire")
    public List<Annnonceur> lire() {
        return annonceurService.lire();
    }

    /*@GetMapping("/lire/{id}")
    public SiteWebPopulaire lirebyId(@PathVariable("id") Long id) {
        System.err.println(siteWebPopulaireRepository.getReferenceById(id).getAnnonces());
        System.out.println("je suis ici=========="+siteWebPopulaireRepository.findById(id).get().getURL());
        return siteWebPopulaireRepository.findById(id).get();
        //return siteWebPopulaireRepository.getReferenceById(id);
    }*/
    @GetMapping("/lireannonceurId/{id}")
    public Annnonceur lirebyId(@PathVariable("id") Long id){
        System.err.println((annnonceurRepository.getReferenceById(id).getAnnonce()));
        return annnonceurRepository.findById(id).get();
    }

   /* @DeleteMapping("/suprimer/{idannonce}/{id]")
    public String suprimer(@PathVariable Long id, @PathVariable Long idannonce) {
        return annonceurService.supprimerAnnonceurparAnnonc(idannonce);
    }*/
   /*@GetMapping("/lireannonceparidsite/{id}")
   public List<Annonce> read1(@PathVariable("id") Long idSite){
       SiteWebPopulaire siteWebPopulaire = siteWebPopulaireRepository.getReferenceById(idSite);
       return annonceRepository.findBySiteWebPopulaires(siteWebPopulaire);
   }*/

   @GetMapping("/lireAnnonceurbyIdAnnonce/{idannonce}")
   public List<Annnonceur> read2(@PathVariable("idannonce") Long idannonce){
       Annonce annonce = annonceRepository.getReferenceById(idannonce);
       return annnonceurRepository.findByAnnonce(annonce);
   }
    /*@GetMapping("/lireannonceparidannonceur/{id}")
    public  List<Annonce> lire1(@PathVariable("id") Long id){
        Annnonceur annnonceur = annonceurRepository.getReferenceById(id);
        return annonceRepository.findByAnnonceur(annnonceur);
    }*/
}
