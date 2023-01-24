
package com.Kilakodon.kilakodon.security.services;


import com.Kilakodon.kilakodon.models.Notification;
import com.Kilakodon.kilakodon.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Optional<Notification> getByIdNotif(Long idNotif) {
        return notificationRepository.findById(idNotif);
    }

    @Override
    public List<Notification> lire() {
        return notificationRepository.findAll();
    }

    @Override
    public String supprimer(Long idNotif) {
        notificationRepository.findById(idNotif);
        return "La notification a été suprimer avec Succès !";
    }
}

