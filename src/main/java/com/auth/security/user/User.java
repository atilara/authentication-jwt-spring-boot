package com.auth.security.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods, based on the fields
@Builder // Lombok annotation to create a builder class
@NoArgsConstructor // Lombok annotation to create a no args constructor
@AllArgsConstructor // Lombok annotation to create an all args constructor
@Entity // JPA annotation to make this object ready for storage in a JPA-based data store
@Table(name = "_user") // JPA annotation to specify the table name
public class User {

    @Id // JPA annotation to specify the primary key
    @GeneratedValue(strategy = GenerationType.UUID) // JPA annotation to specify the primary key generation strategy
    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

}
