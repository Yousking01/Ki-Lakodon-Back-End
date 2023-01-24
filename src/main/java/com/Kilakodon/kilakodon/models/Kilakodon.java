package com.Kilakodon.kilakodon.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kilakodon")
@AllArgsConstructor
@NoArgsConstructor
public class Kilakodon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Kilakodon")
    private Long idKilakodon;

    /*@OneToMany
    private List<Annonceur> annonceurs = new ArrayList<>();*/

    @OneToMany
    private List<User> users = new ArrayList<>();

    @OneToMany
    private List<SiteWebPopulaire> siteWebPopulaires = new ArrayList<>();

    @Column(name = "libelle")
    private String libelleKilakodon;

    public Long getIdKilakodon() {
        return idKilakodon;
    }

    public void setIdKilakodon(Long idKilakodon) {
        this.idKilakodon = idKilakodon;
    }

    public String getLibelleKilakodon() {
        return libelleKilakodon;
    }

    public void setLibelleKilakodon(String libelleKilakodon) {
        this.libelleKilakodon = libelleKilakodon;
    }
}
