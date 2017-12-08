package com.blanket.data.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

import static com.blanket.data.entity.User.GET_USER_BY_USERNAME;


@NamedQueries({
        @NamedQuery(name = GET_USER_BY_USERNAME,
                query = "select u from User u " +
                        "where u.username = :username")
})


@Entity
@Table(name = "user")
public class User {
    public static final String GET_USER_BY_USERNAME = "user.getUserByUsername";
    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String email;
    private String password;
    private String phoneNbr;
    private Date birth;
    private Collection<Blanket> blanketsById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 40)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "phone_nbr", nullable = true, length = 20)
    public String getPhoneNbr() {
        return phoneNbr;
    }

    public void setPhoneNbr(String phoneNbr) {
        this.phoneNbr = phoneNbr;
    }

    @Basic
    @Column(name = "birth", nullable = true)
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (phoneNbr != null ? !phoneNbr.equals(user.phoneNbr) : user.phoneNbr != null) return false;
        if (birth != null ? !birth.equals(user.birth) : user.birth != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (phoneNbr != null ? phoneNbr.hashCode() : 0);
        result = 31 * result + (birth != null ? birth.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Blanket> getBlanketsById() {
        return blanketsById;
    }

    public void setBlanketsById(Collection<Blanket> blanketsById) {
        this.blanketsById = blanketsById;
    }
}
