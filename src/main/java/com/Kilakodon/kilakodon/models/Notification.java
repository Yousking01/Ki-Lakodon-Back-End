package com.Kilakodon.kilakodon.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notification")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNotif")
    private Long idnotif;

    @Column(name = "Description_Notif")
    private String descriptionNotif;

    @Column(name = "Validation")
    private Boolean validation;

/*
    @OneToOne
    private Annonce annonce;
*/


    @ManyToOne
    private User user;


}
