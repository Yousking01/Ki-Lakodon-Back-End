
package com.Kilakodon.kilakodon.controllers;


import com.Kilakodon.kilakodon.Image.ConfigImage;
import com.Kilakodon.kilakodon.models.Annonce;
import com.Kilakodon.kilakodon.models.Annonceur;
import com.Kilakodon.kilakodon.models.Notification;
import com.Kilakodon.kilakodon.repository.AnnonceurRepository;
import com.Kilakodon.kilakodon.repository.NotificationRepository;
import com.Kilakodon.kilakodon.security.services.AnnonceService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/annonce")
@AllArgsConstructor
public class AnnonceController {


    @Autowired
    private final AnnonceService annonceService;
    @Autowired
    private final AnnonceurRepository annonceurRepository;
    @Autowired
    private final NotificationRepository notificationRepository;

    @PostMapping("/creer/{idnotification}")
    public Annonce create(@Param("titreannonce") String titreannonce,
                          @Param("descriptionannonce") String descriptionannonce,
                          @Param("ciblediffusionannonce") String ciblediffusionannonce,
                          @Param("budgetannonce") Double budgetannonce,
                          @Param("dateDebut") String dateDebut,
                          @Param("dateFin") String dateFin,
                          @Param("image") MultipartFile image,
                          @Param("idannonceur") Long idannonceur,
                          @PathVariable(value = "idnotification") Long idNotif
    ) throws IOException {
        System.err.println(idNotif);
        Annonceur annonceur= annonceurRepository.findById(idannonceur).get();
        System.err.println("kjhbvg"+annonceur);
        Notification notification= notificationRepository.findByIdnotif(idNotif);
        System.err.println("notificdation"+notification);
        Annonce annonce = new Annonce();
        annonce.setTitreannonce(titreannonce);
        annonce.setDescriptionannonce(descriptionannonce);
        annonce.setCiblediffusionannonce(ciblediffusionannonce);
        annonce.setBudgetannonce(budgetannonce);
        annonce.setDateDebut(dateDebut);
        annonce.setDateFin(dateFin);
        //annonce.setAnnonceur(annonceur);
        annonce.setNotification(notification);

        String img = StringUtils.cleanPath(image.getOriginalFilename());
        annonce.setImage(img);
        String uploaDir = "C:\\Users\\Youssouf DJIRE\\Desktop\\Ki-Lakodon\\src\\assets\\image";
        ConfigImage.saveimg(uploaDir, img, image);



        return annonceService.creer(annonce);
    }

    @GetMapping("/lire")
    public List<Annonce> read(){
        return annonceService.lire();
    }

    @PutMapping("/modifier/{idannonce}")
    public Annonce update(@PathVariable Long idannonce, @RequestBody Annonce annonce){
        return annonceService.modifier(idannonce,annonce);
    }

    @DeleteMapping("/suprimer/{idannonce}")
    public String suprimer(@PathVariable Long idannonce){
        return annonceService.supprimer(idannonce);
    }
}

