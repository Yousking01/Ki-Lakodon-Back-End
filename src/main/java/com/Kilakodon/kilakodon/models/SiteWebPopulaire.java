package com.Kilakodon.kilakodon.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sitewebpopulaire")
@AllArgsConstructor
@NoArgsConstructor
public class SiteWebPopulaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idsitewebpopulaire")
    private Long idsitepopulaire;

    @Column(name = "nomsitepopulaire")
    private String nomsitepopulaire;

    @Column(name = "URL")
    private String URL;

    @ManyToOne
    private Kilakodon kilakodon;

    @ManyToMany
    private List<Annonce> annonces = new ArrayList<>();


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
