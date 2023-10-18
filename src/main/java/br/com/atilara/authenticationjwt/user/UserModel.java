package br.com.atilara.authenticationjwt.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods, based on the fields
@Builder // Lombok annotation to create a builder class
@NoArgsConstructor // Lombok annotation to create a no args constructor
@AllArgsConstructor // Lombok annotation to create an all args constructor
@Entity // JPA annotation to make this object ready for storage in a JPA-based data store
@Table(name = "_user") // JPA annotation to specify the table name
public class UserModel implements UserDetails {

    @Id // JPA annotation to specify the primary key
    @GeneratedValue(strategy = GenerationType.UUID) // JPA annotation to specify the primary key generation strategy
    private UUID id;

    private String firstName;

    private String lastName;

    @Column(unique = true) // JPA annotation to specify that this field must be unique
    private String email;

    private String password;

    @Enumerated(EnumType.STRING) // JPA annotation to specify the type of the enum
    private RoleEnum role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getGrantedAuthorities();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
