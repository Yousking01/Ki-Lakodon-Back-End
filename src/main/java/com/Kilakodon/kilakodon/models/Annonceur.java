
package com.Kilakodon.kilakodon.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "annonceur")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Annonceur extends Utilisateur{

    @Column(name = "adresseannonceur")
    private String adrresseannonceur;



    @Column(name = "numeroannonceur")
    private String numeroannonceur;

    @Column(name = "budgetannonceur")
    private int budgetannonceur;


/*@OneToMany
    private EspacePubEtat espacePubEtat;*/

/*@OneToMany(mappedBy = "annonceur",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "annonceur_id")
    private List<EspacePub> espacePubs;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "id_espacepub")
    private EspacePub espacePub;*/

    @OneToMany
    private List<Annonce> annonce = new ArrayList<>();

    @ManyToOne
    private Kilakodon kilakodon;

    public Annonceur(String username, String email, String password, String adrresseannonceur) {
        super(username, email, password);
        this.adrresseannonceur = adrresseannonceur;
    }
}


