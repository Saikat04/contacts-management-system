package com.cms.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "contact_number", unique = true)
    private String contactNumber;  
    @Column(name = "about")  
    private String about;
    @Column(name = "profile_link")
    private String profileLink;
    @Column(name = "status")
    private boolean enabled = false;
    @Column(name = "email_verified")    
    private boolean emailVarified = false;
    @Column(name = "phone_verified")
    private boolean phoneVarified = false;
    // sign in method
    @Column(name = "provider")
    private String provider = Provider.SELF.toString();
    @Column(name = "provider_user_id")
    private String providerUserId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    
}
