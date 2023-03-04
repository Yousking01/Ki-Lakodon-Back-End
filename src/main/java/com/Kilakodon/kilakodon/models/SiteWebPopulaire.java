package com.Kilakodon.kilakodon.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sitewebpopulaire")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class SiteWebPopulaire extends Utilisateur {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idsitewebpopulaire")
    private Long idsitepopulaire;*/

   /* public Long getIdsitepopulaire() {
        return idsitepopulaire;
    }

    public void setIdsitepopulaire(Long idsitepopulaire) {
        this.idsitepopulaire = idsitepopulaire;
    }*/

    public List<Annonce> getAnnonces() {
        return annonces;
    }

    public void setAnnonces(List<Annonce> annonces) {
        this.annonces = annonces;
    }

    @Column(name = "nomsitepopulaire")
    private String nomsitepopulaire;

    @Column(name = "URL")
    private String URL;

    private String image;

    @ManyToOne
    private Kilakodon kilakodon;

    @ManyToMany
    private List<Annonce> annonces = new ArrayList<>();

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getNomsitepopulaire() {
        return nomsitepopulaire;
    }

    public void setNomsitepopulaire(String nomsitepopulaire) {
        this.nomsitepopulaire = nomsitepopulaire;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

}
