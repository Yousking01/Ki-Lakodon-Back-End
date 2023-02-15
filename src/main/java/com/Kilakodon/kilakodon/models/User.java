package com.Kilakodon.kilakodon.models;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/*import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;*/
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class User extends Utilisateur{
   @NotBlank
    @Size(max = 120)
    private String confirmpassword;

   /* @ManyToOne
    private Kilakodon kilakodon;

    @OneToMany
    private List<Notification> notifications = new ArrayList<>();

*/
    public User( String username, String email, String password, String confirmpassword) {

        super(username,email,password);
        this.confirmpassword = confirmpassword;

    }

    /*public Kilakodon getKilakodon() {
        return kilakodon;
    }

    public void setKilakodon(Kilakodon kilakodon) {
        this.kilakodon = kilakodon;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }
*/
    /*
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(  name = "admin_roles",
            joinColumns = @JoinColumn(name = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "role1_id")
    )

    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User(String username, String email, String password, String confirmpassword, Set<Role> roles) {
        super(username, email, password);
        this.confirmpassword = confirmpassword;
        this.roles = roles;
    }

     */


}
