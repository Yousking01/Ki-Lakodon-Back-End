
package com.Kilakodon.kilakodon.controllers;


import com.Kilakodon.kilakodon.Image.ConfigImage;
import com.Kilakodon.kilakodon.models.SiteWebPopulaire;
import com.Kilakodon.kilakodon.repository.SiteWebPopulaireRepository;
import com.Kilakodon.kilakodon.security.services.SiteWebPopulaireService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/auth/siteweb")
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class SiteWebPopulaireController {

    private final SiteWebPopulaireService siteWebPopulaireService;
    private final SiteWebPopulaireRepository siteWebPopulaireRepository;

    @PostMapping("/creer")
    public SiteWebPopulaire creer(
            //@RequestBody SiteWebPopulaire siteWebPopulaire
            @Param("nomsitepopulaire") String nomsitepopulaire,
            @Param("URL") String URL,
            /*@Param("ciblediffusionannonce") String ciblediffusionannonce,
            @Param("budgetannonce") Double budgetannonce,
            @Param("dateDebut") String dateDebut,
            @Param("dateFin") String dateFin,*/
            @Param("image") MultipartFile image
    ) throws IOException {


        SiteWebPopulaire siteWebPopulaire = new SiteWebPopulaire();
        siteWebPopulaire.setNomsitepopulaire(nomsitepopulaire);
        siteWebPopulaire.setURL(URL);

        String img = StringUtils.cleanPath(image.getOriginalFilename());
        siteWebPopulaire.setImage(img);
        String uploaDir = "C:\\Users\\Youssouf DJIRE\\Desktop\\Ki-Lakodon\\src\\assets\\image";
        ConfigImage.saveimg(uploaDir, img, image);

        return siteWebPopulaireService.creer(siteWebPopulaire);
    }

    @GetMapping("/lire")
    public List<SiteWebPopulaire> lire() {
        return siteWebPopulaireService.lire();
    }

    @GetMapping("/lire/{id}")
    public SiteWebPopulaire lirebyId(@PathVariable("id") Long id) {
        System.err.println(siteWebPopulaireRepository.getReferenceById(id).getAnnonces());
        return siteWebPopulaireRepository.getReferenceById(id);
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

