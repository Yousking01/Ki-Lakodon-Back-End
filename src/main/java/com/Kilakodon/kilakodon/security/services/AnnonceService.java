
package com.Kilakodon.kilakodon.security.services;

import com.Kilakodon.kilakodon.models.Annonce;

import java.util.List;

public interface AnnonceService {

    Annonce creer (Annonce annonce);

    Annonce getTitreAnnonce(String titreannonce);

    List<Annonce> lire();

    Annonce modifier(Long idannonce, Annonce annonce);

    String supprimer(long idannonce);
}

