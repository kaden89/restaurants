package ru.karachurin.restaurants.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

/**
 * Created by Денис on 09.11.2016.
 */

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "username", name = "users_unique_username_idx")
,@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User extends BaseEntity {

    @NotEmpty
    @Column(name = "username", nullable = false)
    protected String username;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotEmpty
    private String email;

    @Column(name = "password", nullable = false)
    @NotEmpty
    @Length(min = 5)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "registered", columnDefinition = "timestamp default now()")
    private Date registered = new Date();

    public User() {
    }

    public User(Integer id, String name, String email, String password) {
        this(id, name, email, password, true);
    }

    public User(Integer id, String username, String email, String password, boolean enabled) {
        super(id);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.username = username;
    }

    public User(User u) {
        this(u.getId(), u.getUsername(), u.getEmail(), u.getPassword(), u.isEnabled());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name=" + username +
                ", enabled=" + enabled +
                '}';
    }
}
