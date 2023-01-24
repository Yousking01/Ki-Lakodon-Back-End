/*
package com.Kilakodon.kilakodon.controllers;

import com.Kilakodon.kilakodon.models.Kilakodon;
import com.Kilakodon.kilakodon.models.Notification;
import com.Kilakodon.kilakodon.models.User;
import com.Kilakodon.kilakodon.repository.UserRepository;
import com.Kilakodon.kilakodon.security.services.NotificationService;
import com.Kilakodon.kilakodon.security.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final UserRepository userRepository;

    @PostMapping("/creer/{id}")
    public Notification creer(@RequestBody Notification notification, @PathVariable Long id){
        User user= userRepository.findById(id).get();
        notification.setUser(user);
        return notificationService.save(notification);
    }

    @GetMapping("/lire")
    public List<Notification> lire() {
        return notificationService.lire();
    }

   */
/* @PutMapping("/modifier/{idKilakodon}")
    public Notification modifier(@PathVariable Long idNotif, @RequestBody Notification  notification) {
        return notificationService.modifier(idNotif, notification);
    }*//*

    @DeleteMapping("suprimer/{idNotif}")
    public String suprimer(@PathVariable Long idNotif) {
        return notificationService.supprimer(idNotif);
    }
}
*/
