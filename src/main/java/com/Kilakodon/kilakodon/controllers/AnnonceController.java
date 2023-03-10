
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
import com.Kilakodon.kilakodon.security.services.AnnnonceurService;
import com.Kilakodon.kilakodon.security.services.AnnonceService;
import com.Kilakodon.kilakodon.security.services.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("/api/auth/annonce")
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
//@CrossOrigin(value = "http://localhost:4200/%22,maxAge = 3600,allowCredentials = "true")
public class AnnonceController {


    @Autowired
    private final AnnonceService annonceService;
    /*@Autowired
    private final AnnnonceurService annnonceurService;*/
    @Autowired
    private final AnnnonceurRepository annonceurRepository;
    @Autowired
    private final NotificationRepository notificationRepository;

    @Autowired
    private SiteWebPopulaireRepository siteWebPopulaireRepository;
    @Autowired
    private AnnonceRepository annonceRepository;

    @Autowired
    private JavaMailSender mailSender;
    //////:Email-sender de config
    @Autowired
    private EmailService emailService;
    @PostMapping("/creer/{idnotification}/{idannonceur}/{siteWebPopulaires}")
    public Object create(@Param("titreannonce") String titreannonce,
                          @Param("descriptionannonce") String descriptionannonce,
            /* @Param("ciblediffusionannonce") String ciblediffusionannonce,*/
                          @Param("budgetannonce") int prixannonce,
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
        System.out.println(annonce.getPrixannonce());

        ////Comparaison des dates ////////
        //SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        /*SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);*/
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = inputFormat.parse(dateDebut);
        Date date1 = inputFormat.parse(dateFin);

        annonce.setDateDebut(date);
        System.out.println("la date=====" + date);
        // V??rifier si la date de fin est ant??rieure ou ??gale ?? la date de d??but
        if (date1.compareTo(date) <= 0) {
            // Ajouter des jours ?? la date de fin jusqu'?? ce qu'elle soit post??rieure ?? la date de d??but
            Calendar cal = Calendar.getInstance();
            cal.setTime(date1);

            while (cal.getTime().compareTo(date) <= 0) {
                cal.add(Calendar.DATE, 1);
            }

            date1 = cal.getTime();
        }
        annonce.setDateFin(date1);

        // S??rialiser l'objet Annonce en JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String annonceJson = objectMapper.writeValueAsString(annonce);

        // Afficher l'objet Annonce en JSON dans la console
        System.out.println("Annonce : " + annonceJson);


        // Afficher les dates de d??but et de fin
        System.out.println("Date de d??but : " + date);
        System.out.println("Date de fin : " + date1);

        //CAlculer la difference entre les deux dates
        Instant instantDebut = date.toInstant();
        Instant instantFin = date1.toInstant();
        Duration difference = Duration.between(instantDebut, instantFin);

        // Afficher la diff??rence entre les deux dates
        System.out.println("La diff??rence entre les dates de d??but et de fin est de " + difference.toDays() + " jours.");

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

            annonce.setPrixannonce(prixannonce);
            ////////ENVOIE DE MAIL POUR INFORMATION DE LA RECEPTION DE L'ANNONCE////

            String to = annonce.getAnnonceur().getEmail();
            String subject = "Aw Bissimla ,Hello you are welcome,Bonjour , Ki-Lakodon "
                    + annonce.getAnnonceur().getUsername() + ",\n";
            String text = "Bonjour " + annonce.getAnnonceur().getUsername() + ",\n"
                                + "Adresse: " + annonce.getAnnonceur().getAdrresseannonceur() + ",\n"
                                + "Num??ro: " + annonce.getAnnonceur().getNumeroannonceur() + ",\n"
                                + "Votre annonce est en cours de traitement, Veiller patienter... Votre annonce sera publi??e dans 12 ou 24 heures sur " + annonce.getSiteWebPopulaires().get(0).getNomsitepopulaire() + ",\n";

            emailService.sendEmail(to, subject, text);
////////////////RECUPERATION DES DONNEES DE L'ANNONCE PAR MAIL VERS LE SITE DE PUB///////////
            try {
                StringBuilder siteNames = new StringBuilder();
                List<SiteWebPopulaire> siteList = annonce.getSiteWebPopulaires();
                for (SiteWebPopulaire site : siteList) {
                    siteNames.append(site.getNomsitepopulaire()).append(", ");
                }
                siteNames.setLength(siteNames.length() - 2);
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                int index = 0;
                //index
                // V??rifier que la liste des sites web populaires contient des ??l??ments
                if (annonce.getSiteWebPopulaires() != null && annonce.getSiteWebPopulaires().size() > 0) {

                    // R??cup??rer l'e-mail du premier site web populaire de la liste
                    String emailsiteweb = annonce.getSiteWebPopulaires().get(index).getEmail();

                    // D??finir l'e-mail de l'annonceur comme destinataire
                    helper.setTo(emailsiteweb);
                }
                //helper.setTo("djireyoussouf1999@gmail.com");
                helper.setSubject("Nouvelle annonce : " + titreannonce);

                // Cr??er la premi??re partie du message avec le texte de l'e-mail
                MimeBodyPart textPart = new MimeBodyPart();
                textPart.setText("Une nouvelle annonce a ??t?? cr????e avec les informations suivantes : \n"
                        + "Prix : " + annonce.getPrixannonce() + "\n"
                        + "Annonceur : " + annonce.getAnnonceur().getUsername() + "\n"
                        + "Adresse de l'annonceur : " + annonce.getAnnonceur().getAdrresseannonceur() + "\n"
                        + "T??l??phone de l'annonceur : " + annonce.getAnnonceur().getNumeroannonceur() + "\n"
                        + "Date de d??but : " + annonce.getDateDebut() + "\n"
                        + "Date de fin : " + annonce.getDateFin() + "\n"
                        + "Description : " + annonce.getDescriptionannonce() + "\n"
                        + "Sites de publicit?? : " + siteNames.toString() + "\n");

                // Ajouter l'image en tant que pi??ce jointe
                MimeBodyPart imagePart = new MimeBodyPart();
                ByteArrayDataSource dataSource = new ByteArrayDataSource(image.getBytes(), image.getContentType());
                imagePart.setDataHandler(new DataHandler(dataSource));
                imagePart.setFileName("image.jpg");

                // Ajouter les parties au message
                MimeMultipart multipart = new MimeMultipart();
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(imagePart);
                message.setContent(multipart);

                mailSender.send(message);
                annonceService.creer(annonce);
                return new ResponseEntity<>("Email envoy?? avec succ??s !", HttpStatus.OK);
            } catch (MessagingException | IOException e) {
                return new ResponseEntity<>("Erreur lors de l'envoi de l'email.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
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
    @GetMapping("/lireannonceparidannonceur/{id}")
    public  List<Annonce> lire1(@PathVariable("id") Long id){
        Annnonceur annnonceur = annonceurRepository.getReferenceById(id);
        return annonceRepository.findByAnnonceur(annnonceur);
    }

    @PutMapping("/modifier/{idannonce}")
    public Object update
            /*(@PathVariable Long idannonce, @RequestBody Annonce annonce){
        return annonceService.modifier(idannonce,annonce);*/
    (@PathVariable Long idannonce,
     @Param("titreannonce") String titreannonce,
     @Param("descriptionannonce") String descriptionannonce,
            /* @Param("ciblediffusionannonce") String ciblediffusionannonce,*/
     @Param("budgetannonce") int prixannonce,
     @Param("dateDebut") String dateDebut,
     @Param("dateFin") String dateFin,
     @Param("image") MultipartFile image
//     @PathVariable("siteWebPopulaires") Long siteWebPopulaires,
//     @PathVariable(value = "idannonceur") Long idannonceur,
//     @PathVariable(value = "idnotification") Long idNotif
    ) throws IOException, ParseException {
        //System.err.println(idNotif);
        //Annonceur annonceur= annonceurRepository.findById(idannonceur).get();
        //System.err.println("kjhbvg"+annonceur);
        //Notification notification= notificationRepository.findByIdnotif(idNotif);
        //System.err.println("notificdation"+notification);
        Annonce annonce = new Annonce();
        annonce.setTitreannonce(titreannonce);
        annonce.setDescriptionannonce(descriptionannonce);

        ////Comparaison des dates ////////
        //SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        /*SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);*/
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Date date = inputFormat.parse(dateDebut);
        /*Date date = outputFormat.parse(dateDebut);
        Date date1 = outputFormat.parse(dateFin);*/
        Date date = inputFormat.parse(dateDebut);
        Date date1 = inputFormat.parse(dateFin);

        annonce.setDateDebut(date);
        System.out.println("la date=====" + date);
        // V??rifier si la date de fin est ant??rieure ou ??gale ?? la date de d??but
        if (date1.compareTo(date) <= 0) {
            // Ajouter des jours ?? la date de fin jusqu'?? ce qu'elle soit post??rieure ?? la date de d??but
            Calendar cal = Calendar.getInstance();
            cal.setTime(date1);

            while (cal.getTime().compareTo(date) <= 0) {
                cal.add(Calendar.DATE, 1);
            }

            date1 = cal.getTime();
        }
        annonce.setDateFin(date1);

        // S??rialiser l'objet Annonce en JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String annonceJson = objectMapper.writeValueAsString(annonce);

        // Afficher l'objet Annonce en JSON dans la console
        System.out.println("Annonce : " + annonceJson);


        // Afficher les dates de d??but et de fin
        System.out.println("Date de d??but : " + date);
        System.out.println("Date de fin : " + date1);

        //CAlculer la difference entre les deux dates
        Instant instantDebut = date.toInstant();
        Instant instantFin = date1.toInstant();
        Duration difference = Duration.between(instantDebut, instantFin);

        // Afficher la diff??rence entre les deux dates
        System.out.println("La diff??rence entre les dates de d??but et de fin est de " + difference.toDays() + " jours.");


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
        /////Plafond du budget////////////
        //if (budgetannonce >= 10000 ) {
            annonce.setPrixannonce(prixannonce);

        ////////////////RECUPERATION DES DONNEES DE L'ANNONCE PAR MAIL VERS LE SITE DE PUB///////////
        try {
            StringBuilder siteNames = new StringBuilder();
            List<SiteWebPopulaire> siteList = annonce.getSiteWebPopulaires();
            for (SiteWebPopulaire site : siteList) {
                siteNames.append(site.getUsername()).append(", ");
            }
            siteNames.setLength(siteNames.length() - 2);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo("djireyoussouf1999@gmail.com");
            helper.setSubject("Nouvelle annonce : " + titreannonce);

            // Cr??er la premi??re partie du message avec le texte de l'e-mail
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Une nouvelle annonce a ??t?? cr????e avec les informations suivantes : \n"
                    + "Prix : " + annonce.getPrixannonce() + "\n"
                    + "Annonceur : " + annonce.getAnnonceur().getUsername() + "\n"
                    + "Annonceur : " + annonce.getAnnonceur().getAdrresseannonceur() + "\n"
                    + "Annonceur : " + annonce.getAnnonceur().getNumeroannonceur() + "\n"
                    + "Date de d??but : " + annonce.getDateDebut() + "\n"
                    + "Date de fin : " + annonce.getDateFin() + "\n"
                    + "Description : " + annonce.getDescriptionannonce() + "\n"
                    + "Sites de publicit?? : " + siteNames.toString() + "\n");

            // Ajouter l'image en tant que pi??ce jointe
            MimeBodyPart imagePart = new MimeBodyPart();
            ByteArrayDataSource dataSource = new ByteArrayDataSource(image.getBytes(), image.getContentType());
            imagePart.setDataHandler(new DataHandler(dataSource));
            imagePart.setFileName("image.jpg");

            // Ajouter les parties au message
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(imagePart);
            message.setContent(multipart);

            mailSender.send(message);

            annonceService.modifier(idannonce,annonce);
            return new ResponseEntity<>("Email envoy?? avec succ??s !", HttpStatus.OK);
        } catch (MessagingException | IOException e) {
            return new ResponseEntity<>("Erreur lors de l'envoi de l'email.", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }



      /*  return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le budget de l'annonce doit ??tre compris entre 10 000 et 100 000 francs");




    }*/
      @GetMapping("/lire/{idannonce}")
      public Annonce lirebyId(@PathVariable("idannonce") Long idannonce) {
          System.err.println(annonceRepository.getReferenceById(idannonce).getAnnonceur());

          return annonceRepository.findById(idannonce).get();
          //return siteWebPopulaireRepository.getReferenceById(id);
      }
    @DeleteMapping("/suprimer/{idannonce}")
    public String suprimer(@PathVariable Long idannonce){
        return annonceService.supprimer(idannonce);
    }
}

