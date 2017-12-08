package com.blanket.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Blanket {
    private int id;
    private String serialCode;
    @JsonIgnore
    private User userByUserId;
    @JsonIgnore
    private Collection<BlanketStatus> blanketStatusesById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "serial_code", nullable = false, length = 45)
    public String getSerialCode() {
        return serialCode;
    }

    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Blanket blanket = (Blanket) o;

        if (id != blanket.id) return false;
        if (serialCode != null ? !serialCode.equals(blanket.serialCode) : blanket.serialCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (serialCode != null ? serialCode.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @OneToMany(mappedBy = "blanketByBlanketId")
    public Collection<BlanketStatus> getBlanketStatusesById() {
        return blanketStatusesById;
    }

    public void setBlanketStatusesById(Collection<BlanketStatus> blanketStatusesById) {
        this.blanketStatusesById = blanketStatusesById;
    }
}
