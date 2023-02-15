
package com.Kilakodon.kilakodon.repository;

import com.Kilakodon.kilakodon.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    @Transactional
    @Query(value = "INSERT INTO `admin_roles`(`admin_id`, `role1_id`) VALUES (:admin_id,:role1_id)", nativeQuery = true)
    void inserInToAdminRole(@Param("admin_id") Long admin_id, @Param("role1_id") Long role1_id);
}

