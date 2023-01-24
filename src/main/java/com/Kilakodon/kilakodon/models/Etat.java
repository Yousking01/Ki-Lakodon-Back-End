package com.Kilakodon.kilakodon.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.lang.model.element.Name;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Etat")
@AllArgsConstructor
@NoArgsConstructor

public class Etat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "idEtat")
    private Long id;

    @Enumerated(EnumType.STRING)
    //@Column(name = "NomEtat")
    private EspacePubEtat name;

/*    @OneToOne
    private EspacePub espacePub;*/
    /*@JoinTable(  name = "espacepub_etat",
            joinColumns = @JoinColumn(name = "idespacepub"),
            inverseJoinColumns = @JoinColumn(name = "idetat"))
    private Set<Etat> etats = new HashSet<>();*/


    /*@OneToMany
    private Annonceur annonceur;*/



   /* @OneToOne
    private EspacePub espacePub;*/



    public Long getIdetat() {
        return id;
    }

    public void setIdetat(Long idetat) {
        this.id = idetat;
    }

    public EspacePubEtat getName() {
        return name;
    }

    public void setName(EspacePubEtat name) {
        this.name = name;
    }
}

