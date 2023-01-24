/*
package com.Kilakodon.kilakodon.controllers;


import com.Kilakodon.kilakodon.models.SiteWebPopulaire;
import com.Kilakodon.kilakodon.security.services.SiteWebPopulaireService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/siteweb")
@AllArgsConstructor
public class SiteWebPopulaireController {

    private final SiteWebPopulaireService siteWebPopulaireService;

    @PostMapping("/creer")
    public SiteWebPopulaire creer(@RequestBody SiteWebPopulaire siteWebPopulaire){
        return siteWebPopulaireService.creer(siteWebPopulaire);
    }

    @GetMapping("/lire")
    public List<SiteWebPopulaire> lire() {
        return siteWebPopulaireService.lire();
    }

    @PutMapping("/modifier/{idsitepopulaire}")
    public SiteWebPopulaire modifier(@PathVariable Long idsitepopulaire, @RequestBody SiteWebPopulaire siteWebPopulaire) {
        return siteWebPopulaireService.modifier(idsitepopulaire, siteWebPopulaire);
    }
    @DeleteMapping("suprimer/{idsitepopulaire}")
    public String suprimer(@PathVariable Long idsitepopulaire) {
        return siteWebPopulaireService.suprimer(idsitepopulaire);
    }


}
*/
