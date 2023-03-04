package com.Kilakodon.kilakodon.security.services;

import com.Kilakodon.kilakodon.models.Annnonceur;


public interface AnnnonceurService {
    Annnonceur modifier(Long id, Annnonceur annnonceur);
    String supprimer(long id);
}
