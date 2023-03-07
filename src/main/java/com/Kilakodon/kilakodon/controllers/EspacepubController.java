
package com.Kilakodon.kilakodon.controllers;


import com.Kilakodon.kilakodon.models.EspacePub;
import com.Kilakodon.kilakodon.models.Etat;
import com.Kilakodon.kilakodon.repository.EspacePubRepository;
import com.Kilakodon.kilakodon.repository.EtatRepository;
import com.Kilakodon.kilakodon.security.services.EspacepubService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/espacepub")
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class EspacepubController {

    private final EspacepubService espacepubService;
    private final EtatRepository etatRepository;


    @Autowired
    private EspacePubRepository espacePubRepository;


    @PostMapping("/creer/{idetat}")
    public EspacePub create(@RequestBody EspacePub espacePub, @PathVariable Long idetat) {
        Etat etat = etatRepository.findById(idetat).get();
        espacePub.setEtat(etat);
        return espacepubService.creer(espacePub);
    }

    @GetMapping("/lire")
    public List<EspacePub> lire() {
        return espacepubService.lire();
    }

    @PutMapping("/modifier/{idespacepub}")
    public EspacePub modifier(@RequestBody EspacePub espacePub, @PathVariable Long idespacepub) {

        return espacepubService.modifier(idespacepub, espacePub);
    }

    @DeleteMapping("/suprimer/{idespacespub}")
    public String suprimer(@PathVariable Long idespacepub) {
        return espacepubService.suprimer(idespacepub);
    }


}
/* @GetMapping("/lire")
    public List<EspacePub> lire() {
        return espacePubRepository.findAll();
    }

    @GetMapping("/lire/{id}")
    public EspacePub lireEspaceId(@PathVariable Long idespacepub) {
        return espacePubRepository.findByIdespacepub(idespacepub);
    }

    @PutMapping("/modifier/{idespacepub}")
    public EspacePub modifier(@RequestBody EspacePub espacePub, @PathVariable Long idespacepub) {

        return espacePubRepository.modifier(idespacepub, espacePub );
    }

    @DeleteMapping("/suprimer/{idespacespub}")
    public String suprimer(@PathVariable Long idespacepub) {
        return espacePubRepository.deleteById(idespacepub);
    }*//*

}
*/
