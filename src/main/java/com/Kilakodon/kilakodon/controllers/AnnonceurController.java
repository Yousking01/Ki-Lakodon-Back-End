
package com.Kilakodon.kilakodon.controllers;

import com.Kilakodon.kilakodon.models.Annonceur;
import com.Kilakodon.kilakodon.models.EspacePub;
import com.Kilakodon.kilakodon.models.Kilakodon;
import com.Kilakodon.kilakodon.repository.EspacePubRepository;
import com.Kilakodon.kilakodon.repository.KilakodonRepository;
import com.Kilakodon.kilakodon.security.services.AnnonceurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/annonceur")
@RequestMapping("/api/auth/annonceur")
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AnnonceurController {

    private final AnnonceurService annonceurService;
    private final EspacePubRepository espacePubRepository;

    private final KilakodonRepository kilakodonRepository;

    @PostMapping("/creer/{idespacepub}/{idKilakodon}")
    public Annonceur create(@RequestBody Annonceur annonceur, @PathVariable Long idespacepub, @PathVariable Long idKilakodon) {
        EspacePub espacePub = espacePubRepository.findById(idespacepub).get();
        Kilakodon kilakodon = kilakodonRepository.findById(idKilakodon).get();
        annonceur.setKilakodon(kilakodon);
        annonceur.setEspacePub(espacePub);
        return annonceurService.creer(annonceur);
    }

    @GetMapping("/lire")
    public List<Annonceur> lire() {
        return annonceurService.lire();
    }
}


/*@PutMapping("/modifier/{idannonceur}")
    public Annonceur modifier(@PathVariable Long idannonceur, @RequestBody Annonceur annonceur){
        return annonceurService.modifier(idannonceur, annonceur);
    }*//*


    @DeleteMapping("/suprimer/{idannonceur}")
    public String suprimer(@PathVariable Long idannonceur){
        return annonceurService.suprimer(idannonceur);
    }

    @PostMapping("/{idannonceur}/espacePubs/{idespacepub}")
    public void Acheter(@PathVariable Long idannonceur,@PathVariable Long idespacePub) {
        annonceurService.Acheter(idannonceur,idespacePub);
    }
}*/

