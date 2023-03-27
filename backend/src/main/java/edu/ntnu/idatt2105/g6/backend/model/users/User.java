package edu.ntnu.idatt2105.g6.backend.model.users;

import edu.ntnu.idatt2105.g6.backend.model.chat.Conversation;
import edu.ntnu.idatt2105.g6.backend.model.chat.Message;
import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 User class represents a user of the e-commerce application with their personal information,
 authentication details, and activities on the platform.
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    /**
     * The unique identifier for the user
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    /**
     * The username of the user, must be unique and not null
     */
    @Column(name = "username", length = 64, nullable = false)
    @NonNull
    private String username;

    /**
     * The password of the user, not null
     */
    @Column(name = "password", nullable = false)
    @NonNull
    private String password;

    /**
     * The full name of the user, not null
     */
    @Column(name = "full_name", nullable = false)
    @NonNull
    private String fullName;

    /**
     * The email address of the user, not null
     */
    @Column(name = "email", nullable = false)
    @NonNull
    private String email;

    /**
     * The role of the user, must be a non-null value from Role enum
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    @NonNull
    private Role role;

    /**
     * The birth date of the user, can be null
     */
    @Column(name = "birth_date")
    private Date birthDate;

    /**
     * The phone number of the user, can be null
     */
    @Column(name = "phone")
    private String phone;

    /**
     * The profile picture of the user, can be null
     */
    @Column(name = "profile_picture")
    @Lob
    private byte[] picture;


    /**
     * The list of items the user has listed for sale, one-to-many relationship
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private List<Item> listedItems = new ArrayList<>();

    /**
     * The list of items the user has bookmarked, one-to-many relationship
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private List<Bookmark> bookmarks = new ArrayList<>();

    /**
     * The list of conversations the user has, one-to-many relationship
     */
    @OneToMany(mappedBy = "user1", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private List<Conversation> conversations1 = new ArrayList<>();

    /**
     * The list of conversations the user has, one-to-many relationship
     */
    @OneToMany(mappedBy = "user2", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private List<Conversation> conversations2 = new ArrayList<>();

    /**
     * The list of messages the user has as a sender, one-to-many relationship
     */
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private List<Message> messages = new ArrayList<>();

    //TODO: improve Two-To-Many relationship


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @NotNull
    @Override
    public String getPassword() {
        return password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}