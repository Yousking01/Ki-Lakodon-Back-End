
package com.Kilakodon.kilakodon.repository;

import com.Kilakodon.kilakodon.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Notification findByIdnotif(Long idNotif);
}

