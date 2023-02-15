
package com.Kilakodon.kilakodon.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SuperBuilder

public abstract class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(max = 50)
    private String password;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "admin1_roles",
           joinColumns = @JoinColumn(name = "admin_id"),
    inverseJoinColumns = @JoinColumn(name = "role1_id"))
    private Set<Role> roles = new HashSet<>();

    public Utilisateur(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

