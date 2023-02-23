
package com.Kilakodon.kilakodon.controllers;


import com.Kilakodon.kilakodon.Image.ConfigImage;
import com.Kilakodon.kilakodon.models.Annnonceur;
import com.Kilakodon.kilakodon.models.Annonce;
import com.Kilakodon.kilakodon.models.Notification;
import com.Kilakodon.kilakodon.models.SiteWebPopulaire;
import com.Kilakodon.kilakodon.repository.AnnnonceurRepository;
import com.Kilakodon.kilakodon.repository.AnnonceRepository;
import com.Kilakodon.kilakodon.repository.NotificationRepository;
import com.Kilakodon.kilakodon.repository.SiteWebPopulaireRepository;
import com.Kilakodon.kilakodon.security.services.AnnonceService;
import com.Kilakodon.kilakodon.security.services.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/auth/annonce")
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
//@CrossOrigin(value = "http://localhost:4200/%22,maxAge = 3600,allowCredentials = "true")
public class AnnonceController {


    @Autowired
    private final AnnonceService annonceService;
    @Autowired
    private final AnnnonceurRepository annonceurRepository;
    @Autowired
    private final NotificationRepository notificationRepository;
    @Autowired
    private SiteWebPopulaireRepository siteWebPopulaireRepository;
    @Autowired
    private AnnonceRepository annonceRepository;

    //////:Email-sender de config
    @Autowired
    private EmailService emailService;
    @PostMapping("/creer/{idnotification}/{idannonceur}/{siteWebPopulaires}")
    public Object create(@Param("titreannonce") String titreannonce,
                          @Param("descriptionannonce") String descriptionannonce,
            /* @Param("ciblediffusionannonce") String ciblediffusionannonce,*/
                          @Param("budgetannonce") int budgetannonce,
                          @Param("dateDebut") String dateDebut,
                          @Param("dateFin") String dateFin,
                          @Param("image") MultipartFile image,
                          @PathVariable("siteWebPopulaires") Long siteWebPopulaires,
                          @PathVariable(value = "idannonceur") Long idannonceur,
                          @PathVariable(value = "idnotification") Long idNotif
    ) throws IOException, ParseException, MessagingException {
        System.err.println(idNotif);
        Annnonceur annonceur= annonceurRepository.findById(idannonceur).get();
        System.err.println("kjhbvg"+annonceur);
        Notification notification= notificationRepository.findByIdnotif(idNotif);
        System.err.println("notificdation"+notification);
        Annonce annonce = new Annonce();
        annonce.setTitreannonce(titreannonce);
        annonce.setDescriptionannonce(descriptionannonce);
        /*annonce.setCiblediffusionannonce(ciblediffusionannonce);*/
        //annonce.setBudgetannonce(budgetannonce);

        ///////////plafond de budget//////
        // Valider le budget de l'annonce
        System.out.println("le budjettttttttttttttt");
        System.out.println(annonce.getBudgetannonce());

        ////Comparaison des dates ////////
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = outputFormat.parse(dateDebut);
        Date date1 = outputFormat.parse(dateFin);

        annonce.setDateDebut(date);
        // Vérifier si la date de fin est antérieure ou égale à la date de début
        if (date1.compareTo(date) <= 0) {
            // Ajouter des jours à la date de fin jusqu'à ce qu'elle soit postérieure à la date de début
            Calendar cal = Calendar.getInstance();
            cal.setTime(date1);

            while (cal.getTime().compareTo(date) <= 0) {
                cal.add(Calendar.DATE, 1);
            }

            date1 = cal.getTime();
        }
        annonce.setDateFin(date1);

        // Sérialiser l'objet Annonce en JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String annonceJson = objectMapper.writeValueAsString(annonce);

        // Afficher l'objet Annonce en JSON dans la console
        System.out.println("Annonce : " + annonceJson);


        // Afficher les dates de début et de fin
        System.out.println("Date de début : " + date);
        System.out.println("Date de fin : " + date1);

        //CAlculer la difference entre les deux dates
        Instant instantDebut = date.toInstant();
        Instant instantFin = date1.toInstant();
        Duration difference = Duration.between(instantDebut, instantFin);

        // Afficher la différence entre les deux dates
        System.out.println("La différence entre les dates de début et de fin est de " + difference.toDays() + " jours.");


        //else {

        // ("Le budget de l'annonce doit être compris entre 10 000 et 100 000 francs");
        //}

        //////////////////      commparer de date    //////////

        /*if (annonce.isEndDateBeforeStartDate()) {
            ResponseEntity.badRequest().body("La date de fin doit être postérieure à la date de début.");
        }

        // Enregistrer l'événement dans la base de données.
        ResponseEntity.ok("L'événement a été enregistré avec succès.");
    }*/
        //Date t=new Date();
        //Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateDebut);
        //Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dateFin);
        //System.err.println(date);

        //String dateString= "15/02/2023";
        /*SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateDebut);
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dateFin);*/


        //Ajouter 1 jours à la date de début pour obtenir la fin
        /*Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.after( 1);
        Date date1 = cal.getTime();*/
        // Vérifier que la date de fin est postérieure à la date de début
        /*if (date1.before(date)){
            // Si la date de fin est avant la date de début, ajouter un jour supplémentaire
            cal.after(1);
            date1 = cal.getTime();
        }*/

        //Date date1 = dateFormat.parse(dateString);


        //if(t.before(dateFin)){




        //}
        //  else {
        //    System.out.println("date de fin infere à today");
        //}

        annonce.setAnnonceur(annonceur);
        annonce.setNotification(notification);
        SiteWebPopulaire siteWebPopulaire = siteWebPopulaireRepository.getReferenceById(siteWebPopulaires);
        List<SiteWebPopulaire> siteWebPopulaires1 = new ArrayList<>();

        siteWebPopulaires1.add(siteWebPopulaire);

        annonce.setSiteWebPopulaires(siteWebPopulaires1);

        String img = StringUtils.cleanPath(image.getOriginalFilename());
        annonce.setImage(img);
        String uploaDir = "C:\\Users\\Youssouf DJIRE\\Desktop\\Ki-Lakodon\\src\\assets\\image";
        ConfigImage.saveimg(uploaDir, img, image);
        if (budgetannonce >= 10000 ) {
            annonce.setBudgetannonce(budgetannonce);

            String to = annonce.getAnnonceur().getEmail();
            String subject = "Aw Bissimla Ki-Lakodon Sanfai, Bonjour sur Ki-Lakodon, Hello you are welcome on Ki-Lakodon " + annonce.getAnnonceur().getUsername() + ",\n\n" ;
            String text = "Bonjour " + annonce.getAnnonceur().getUsername() + ",\n\nAdresse: " + annonce.getAnnonceur().getAdrresseannonceur() + ",\n\nNuméro: " + annonce.getAnnonceur().getNumeroannonceur() + ",\n\nVotre annonce est en cours de traitement, Veiller patienter... Votre annonce sera publiée dans 12 ou 24 heures sur " + annonce.getSiteWebPopulaires().get(0).getNomsitepopulaire() + ",\n\n";

            emailService.sendEmail(to, subject, text);
            return annonceService.creer(annonce);
        }



        return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le budget de l'annonce doit être compris entre 10 000 et 100 000 francs");

    }

    @GetMapping("/lire")
    public List<Annonce> read(){
        return annonceService.lire();
    }

    @GetMapping("/lireannonceparidsite/{id}")
    public List<Annonce> read1( @PathVariable("id") Long idSite){
        SiteWebPopulaire siteWebPopulaire = siteWebPopulaireRepository.getReferenceById(idSite);
        return annonceRepository.findBySiteWebPopulaires(siteWebPopulaire);
    }

    @PutMapping("/modifier/{idannonce}")
    public Object update
            /*(@PathVariable Long idannonce, @RequestBody Annonce annonce){
        return annonceService.modifier(idannonce,annonce);*/
    (@PathVariable Long idannonce,
     @Param("titreannonce") String titreannonce,
     @Param("descriptionannonce") String descriptionannonce,
            /* @Param("ciblediffusionannonce") String ciblediffusionannonce,*/
     @Param("budgetannonce") int budgetannonce,
     @Param("dateDebut") Date dateDebut,
     @Param("dateFin") Date dateFin,
     @Param("image") MultipartFile image
//     @PathVariable("siteWebPopulaires") Long siteWebPopulaires,
//     @PathVariable(value = "idannonceur") Long idannonceur,
//     @PathVariable(value = "idnotification") Long idNotif
    ) throws IOException {
        //System.err.println(idNotif);
        //Annonceur annonceur= annonceurRepository.findById(idannonceur).get();
        //System.err.println("kjhbvg"+annonceur);
        //Notification notification= notificationRepository.findByIdnotif(idNotif);
        //System.err.println("notificdation"+notification);
        Annonce annonce = new Annonce();
        annonce.setTitreannonce(titreannonce);
        annonce.setDescriptionannonce(descriptionannonce);
        /*annonce.setCiblediffusionannonce(ciblediffusionannonce);*/
        // Valider le budget de l'annonce
        if (annonce.getBudgetannonce() < 10000 || annonce.getBudgetannonce() > 100000) {
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le budget de l'annonce doit être compris entre 10 000 et 100 000 francs");
            annonce.setBudgetannonce(budgetannonce);
        }else {
            return ("Le budget de l'annonce doit être compris entre 10 000 et 100 000 francs");
        }


        annonce.setDateDebut(dateDebut);
        annonce.setDateFin(dateFin);
        //annonce.setAnnonceur(annonceur);
        //annonce.setNotification(notification);
        //SiteWebPopulaire siteWebPopulaire = siteWebPopulaireRepository.getReferenceById(siteWebPopulaires);
        List<SiteWebPopulaire> siteWebPopulaires1 = new ArrayList<>();

        //siteWebPopulaires1.add(siteWebPopulaire);

        annonce.setSiteWebPopulaires(siteWebPopulaires1);

        String img = StringUtils.cleanPath(image.getOriginalFilename());
        annonce.setImage(img);
        String uploaDir = "C:\\Users\\Youssouf DJIRE\\Desktop\\Ki-Lakodon\\src\\assets\\image";
        ConfigImage.saveimg(uploaDir, img, image);



        return annonceService.modifier(idannonce,annonce);
    }

    @DeleteMapping("/suprimer/{idannonce}")
    public String suprimer(@PathVariable Long idannonce){
        return annonceService.supprimer(idannonce);
    }
}

