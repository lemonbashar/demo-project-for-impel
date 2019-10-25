package com.demoforimpel.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Token-Store store the client side public IP Address that's why from a specific ip address only one user
 * Can access only one login-access
 * that's why we use their public IP
 */
@Entity
@Table(name = "TOKEN_STORE")
public class TokenStore implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "TOKEN_STORE_PK")
    @SequenceGenerator(name = "TOKEN_STORE_PK", sequenceName = "TOKEN_STORE_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "JWT_TOKEN", length = 2048, unique = true)
    private String token;

    @ManyToOne
    @JoinColumn(name = "CORRESPONDENT_USER_ID")
    private User user;

    @Column(name = "VALIDATE_DATE")
    private Date validateDate;

    @Column(name = "ACTIVE")
    private Boolean active;

    public TokenStore() {
    }

    public TokenStore(String token, User user, Date validateDate, Boolean active) {
        this.token = token;
        this.user = user;
        this.validateDate = validateDate;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getValidateDate() {
        return validateDate;
    }

    public void setValidateDate(Date validateDate) {
        this.validateDate = validateDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
