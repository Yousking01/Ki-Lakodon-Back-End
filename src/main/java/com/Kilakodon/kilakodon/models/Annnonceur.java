package com.Kilakodon.kilakodon.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(	name = "annonceur")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Annnonceur extends Utilisateur  {

    private String adrresseannonceur;

    private String numeroannonceur;

    private int budgetannonceur;


    /*@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "id_espacepub")
    private EspacePub espacePub;*/

    @JsonIgnore
    @OneToMany
    private List<Annonce> annonce = new ArrayList<>();


    @ManyToOne
    private Kilakodon kilakodon;

    /*public Annnonceur( String username,  String email,  String password, String adrresseannonceur, String numeroannonceur, int budgetannonceur) {
        super(username, email, password);
        this.adrresseannonceur = adrresseannonceur;
        this.numeroannonceur = numeroannonceur;
        this.budgetannonceur = budgetannonceur;
    }*/
}
