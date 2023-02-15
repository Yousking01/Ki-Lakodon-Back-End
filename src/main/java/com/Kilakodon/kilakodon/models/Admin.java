
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
    @JoinTable(  name = "admin_roles",
            joinColumns = @JoinColumn(name = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "role1_id"))
    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Admin(String username, String email, String password) {
        super(username,email,password);

    }


}

