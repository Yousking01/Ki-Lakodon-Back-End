/*
package com.Kilakodon.kilakodon.controllers;

import com.Kilakodon.kilakodon.models.Kilakodon;
import com.Kilakodon.kilakodon.models.SiteWebPopulaire;
import com.Kilakodon.kilakodon.security.services.KilakodonService;
import com.Kilakodon.kilakodon.security.services.SiteWebPopulaireService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kilakodon")
@AllArgsConstructor
public class KilakodonController {

    private final KilakodonService kilakodonService;



    @PostMapping("/creer")
    public Kilakodon creer(@RequestBody Kilakodon kilakodon){
        return kilakodonService.save(kilakodon);
    }

    @GetMapping("/lire")
    public List<Kilakodon> lire() {
        return kilakodonService.lire();
    }

    @PutMapping("/modifier/{idKilakodon}")
    public Kilakodon modifier(@PathVariable Long idKilakodon, @RequestBody Kilakodon  kilakodon) {
        return kilakodonService.modifier(idKilakodon, kilakodon);
    }
    @DeleteMapping("suprimer/{idsitepopulaire}")
    public String suprimer(@PathVariable Long idKilakodon) {
        return kilakodonService.suprimer(idKilakodon);
    }
}
*/
