
package com.Kilakodon.kilakodon.security.services;

import com.Kilakodon.kilakodon.models.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {

    Notification save(Notification notification);

    Optional<Notification> getByIdNotif(Long idNotif);

    List<Notification> lire();

    String supprimer(Long idNotif);
}

