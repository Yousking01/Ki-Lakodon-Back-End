
package com.Kilakodon.kilakodon.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="annonce")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idannonce;

    @Column(length = 100)
    private String titreannonce;

    @Column(length = 255)
    private String descriptionannonce;

    /*@Column(length = 100)
    private String ciblediffusionannonce;*/

    @Column(length = 20)
    private Double budgetannonce;

    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    //@JsonFormat(pattern = "aaaa-MM-jj", shape = JsonFormat.Shape.STRING)
    @Column(length = 20)
    private Date dateDebut;

    //@JsonFormat(pattern = "aaaa-MM-jj", shape = JsonFormat.Shape.STRING)
    @Column(length = 20)
    private Date dateFin;

////////commparaison des dates/////////
    public boolean isEndDateBeforeStartDate() {
        return dateFin.before(dateDebut);
    }

    public boolean isEndDateAfterStartDate() {
        return dateFin.after(dateDebut);
    }
/////////fin de la comparaison des dates////////
    public Annnonceur getAnnonceur() {
        return annonceur;
    }

    public void setAnnonceur(Annnonceur annonceur) {
        this.annonceur = annonceur;
    }

    @ManyToOne
    private Annnonceur annonceur;

    @ManyToMany
    private List<SiteWebPopulaire> siteWebPopulaires = new ArrayList<>();

    public List<SiteWebPopulaire> getSiteWebPopulaires() {
        return siteWebPopulaires;
    }

    public void setSiteWebPopulaires(List<SiteWebPopulaire> siteWebPopulaires) {
        this.siteWebPopulaires = siteWebPopulaires;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    @OneToOne
    private Notification notification;

public Long getIdannocne() {
        return idannonce;
    }

    public void setIdannocne(Long idannocne) {
        this.idannonce = idannocne;
    }


    public String getTitreannonce() {
        return titreannonce;
    }

    public void setTitreannonce(String titreannonce) {
        this.titreannonce = titreannonce;
    }

    public String getDescriptionannonce() {
        return descriptionannonce;
    }

    public void setDescriptionannonce(String descriptionannonce) {
        this.descriptionannonce = descriptionannonce;
    }

   /* public String getCiblediffusionannonce() {
        return ciblediffusionannonce;
    }

    public void setCiblediffusionannonce(String ciblediffusionannonce) {
        this.ciblediffusionannonce = ciblediffusionannonce;
    }*/

    public Double getBudgetannonce() {
        return budgetannonce;
    }

    public void setBudgetannonce(Double budgetannonce) {
        this.budgetannonce = budgetannonce;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
}

