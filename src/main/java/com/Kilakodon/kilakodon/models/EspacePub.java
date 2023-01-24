
package com.Kilakodon.kilakodon.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "EspacePub")
@AllArgsConstructor
@NoArgsConstructor
public class EspacePub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idespacepub")
    private Long idespacepub;

    @Column(name = "nomespacepub")
    private String nomespacepub;

    @Column(name = "description")
    private String descriptionespacepub;

    @Column(name = "prix")
    private int Prixespacepub;


@Column(name = "espacepubetat")
    private EspacePubEtat Etat;



    @OneToOne
    private Etat etat;

    public Long getIdespacepub() {
        return idespacepub;
    }

    public void setIdespacepub(Long idespacepub) {
        this.idespacepub = idespacepub;
    }


/*@OneToMany
        private Annonceur  annonceur;*/


/*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "annonceur_id")
    private Annonceur annonceur;

    @OneToMany(mappedBy = "espacePub",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    //@JoinColumn(name = "annonceur_id")
    private List<Annonceur> annonceur;*/

    public String getNomespacepub() {
        return nomespacepub;
    }

    public void setNomespacepub(String nomespacepub) {
        this.nomespacepub = nomespacepub;
    }

    public String getDescription() {
        return descriptionespacepub;
    }

    public void setDescription(String description) {
        this.descriptionespacepub = description;
    }

    public int getPrix() {
        return Prixespacepub;
    }

    public void setPrix(int prix) {
        Prixespacepub = prix;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }
}

