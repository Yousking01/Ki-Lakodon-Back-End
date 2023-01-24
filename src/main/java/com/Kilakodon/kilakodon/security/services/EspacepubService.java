
package com.Kilakodon.kilakodon.security.services;

import com.Kilakodon.kilakodon.models.EspacePub;
import com.Kilakodon.kilakodon.models.Etat;

import java.util.List;

public interface EspacepubService {

 EspacePub creer(EspacePub espacePub);

    List<EspacePub> lister();

    EspacePub findByIdespacepub(Long idespacepub);

    EspacePub modifier(Long idespacepub, EspacePub espacePub);
    String suprimer(Long idespacepub);


    //EspacePub creer(EspacePub espacePub);

    EspacePub getNomespacepub(String nomespacepub );

    List<EspacePub> lire();

    //EspacePub modifier(Long idespacepub, EspacePub espacePub);

    String suprimer(long idespacepub);


    Etat saveEtat (Etat etat);
}

