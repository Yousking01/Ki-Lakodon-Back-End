package com.Kilakodon.kilakodon.controllers;

import com.Kilakodon.kilakodon.models.Annnonceur;
import com.Kilakodon.kilakodon.models.EspacePub;
import com.Kilakodon.kilakodon.models.Kilakodon;
import com.Kilakodon.kilakodon.repository.EspacePubRepository;
import com.Kilakodon.kilakodon.repository.KilakodonRepository;
import com.Kilakodon.kilakodon.repository.UserRepository;
import com.Kilakodon.kilakodon.security.services.AnnnonceurServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/annonceur")
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AnnnonceurController {

    private final AnnnonceurServiceImpl annonceurService;

    private final EspacePubRepository espacePubRepository;

    private final KilakodonRepository kilakodonRepository;
    private final UserRepository userRepository;

    @PostMapping("/creer/{idespacepub}/{idKilakodon}")
    public Annnonceur Ajouter(
            @RequestBody Annnonceur annonceur, @PathVariable("idespacepub") Long idespacepub, @PathVariable("idKilakodon") Long idKilakodon
    )
    {
        EspacePub espacePub = espacePubRepository.findById(idespacepub).get();
        annonceur.setEspacePub(espacePub);
        Kilakodon kilakodon = kilakodonRepository.findById(idKilakodon).get();
        annonceur.setKilakodon(kilakodon);
        return annonceurService.creer(annonceur);
    }



    @GetMapping("/lire")
    public List<Annnonceur> lire() {
        return annonceurService.lire();
    }

    @DeleteMapping("/suprimer/{idannonceur}")
    public String suprimer(@PathVariable Long idannonceur) {
        return annonceurService.suprimer(idannonceur);
    }

}
