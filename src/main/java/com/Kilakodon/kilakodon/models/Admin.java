
package com.Kilakodon.kilakodon.models;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Admin")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@SuperBuilder
public class Admin extends Utilisateur {


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "admin1_roles",
            joinColumns = @JoinColumn(name = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public Admin(String username, String email, String password) {
        super(username,email,password);

    }


}

