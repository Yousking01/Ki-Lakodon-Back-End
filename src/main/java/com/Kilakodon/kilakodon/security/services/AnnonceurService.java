
package com.Kilakodon.kilakodon.security.services;


import com.Kilakodon.kilakodon.models.Annonceur;

import java.util.List;
import java.util.Optional;

public interface AnnonceurService {

    Annonceur creer(Annonceur annonceur);

    //Optional<Annonceur> getNomannonceur(String nomannonceur);

    List<Annonceur> lire();

    //Annonceur modifier(Long idannonceur, Annonceur annonceur);

    String suprimer(long idannonceur);

    String Acheter(long idannonceur, Long idespacepub);
}

