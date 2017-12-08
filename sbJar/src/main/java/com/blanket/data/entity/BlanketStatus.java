package com.blanket.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "blanket_status", schema = "smartblanket", catalog = "")
public class BlanketStatus {
    private int id;
    private Timestamp datetime;
    private Integer tempTopleft;
    private Integer tempTopright;
    private Integer tempBotleft;
    private Integer tempBotright;
    private Integer vibrTopleft;
    private Integer vibrTopright;
    private Integer vibrBotleft;
    private Integer vibrBotright;
    private String userPhase;
    private Blanket blanketByBlanketId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "datetime", nullable = false)
    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    @Basic
    @Column(name = "temp_topleft", nullable = true)
    public Integer getTempTopleft() {
        return tempTopleft;
    }

    public void setTempTopleft(Integer tempTopleft) {
        this.tempTopleft = tempTopleft;
    }

    @Basic
    @Column(name = "temp_topright", nullable = true)
    public Integer getTempTopright() {
        return tempTopright;
    }

    public void setTempTopright(Integer tempTopright) {
        this.tempTopright = tempTopright;
    }

    @Basic
    @Column(name = "temp_botleft", nullable = true)
    public Integer getTempBotleft() {
        return tempBotleft;
    }

    public void setTempBotleft(Integer tempBotleft) {
        this.tempBotleft = tempBotleft;
    }

    @Basic
    @Column(name = "temp_botright", nullable = true)
    public Integer getTempBotright() {
        return tempBotright;
    }

    public void setTempBotright(Integer tempBotright) {
        this.tempBotright = tempBotright;
    }

    @Basic
    @Column(name = "vibr_topleft", nullable = true)
    public Integer getVibrTopleft() {
        return vibrTopleft;
    }

    public void setVibrTopleft(Integer vibrTopleft) {
        this.vibrTopleft = vibrTopleft;
    }

    @Basic
    @Column(name = "vibr_topright", nullable = true)
    public Integer getVibrTopright() {
        return vibrTopright;
    }

    public void setVibrTopright(Integer vibrTopright) {
        this.vibrTopright = vibrTopright;
    }

    @Basic
    @Column(name = "vibr_botleft", nullable = true)
    public Integer getVibrBotleft() {
        return vibrBotleft;
    }

    public void setVibrBotleft(Integer vibrBotleft) {
        this.vibrBotleft = vibrBotleft;
    }

    @Basic
    @Column(name = "vibr_botright", nullable = true)
    public Integer getVibrBotright() {
        return vibrBotright;
    }

    public void setVibrBotright(Integer vibrBotright) {
        this.vibrBotright = vibrBotright;
    }

    @Basic
    @Column(name = "user_phase", nullable = true, length = 15)
    public String getUserPhase() {
        return userPhase;
    }

    public void setUserPhase(String userPhase) {
        this.userPhase = userPhase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlanketStatus that = (BlanketStatus) o;

        if (id != that.id) return false;
        if (datetime != null ? !datetime.equals(that.datetime) : that.datetime != null) return false;
        if (tempTopleft != null ? !tempTopleft.equals(that.tempTopleft) : that.tempTopleft != null) return false;
        if (tempTopright != null ? !tempTopright.equals(that.tempTopright) : that.tempTopright != null) return false;
        if (tempBotleft != null ? !tempBotleft.equals(that.tempBotleft) : that.tempBotleft != null) return false;
        if (tempBotright != null ? !tempBotright.equals(that.tempBotright) : that.tempBotright != null) return false;
        if (vibrTopleft != null ? !vibrTopleft.equals(that.vibrTopleft) : that.vibrTopleft != null) return false;
        if (vibrTopright != null ? !vibrTopright.equals(that.vibrTopright) : that.vibrTopright != null) return false;
        if (vibrBotleft != null ? !vibrBotleft.equals(that.vibrBotleft) : that.vibrBotleft != null) return false;
        if (vibrBotright != null ? !vibrBotright.equals(that.vibrBotright) : that.vibrBotright != null) return false;
        if (userPhase != null ? !userPhase.equals(that.userPhase) : that.userPhase != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (datetime != null ? datetime.hashCode() : 0);
        result = 31 * result + (tempTopleft != null ? tempTopleft.hashCode() : 0);
        result = 31 * result + (tempTopright != null ? tempTopright.hashCode() : 0);
        result = 31 * result + (tempBotleft != null ? tempBotleft.hashCode() : 0);
        result = 31 * result + (tempBotright != null ? tempBotright.hashCode() : 0);
        result = 31 * result + (vibrTopleft != null ? vibrTopleft.hashCode() : 0);
        result = 31 * result + (vibrTopright != null ? vibrTopright.hashCode() : 0);
        result = 31 * result + (vibrBotleft != null ? vibrBotleft.hashCode() : 0);
        result = 31 * result + (vibrBotright != null ? vibrBotright.hashCode() : 0);
        result = 31 * result + (userPhase != null ? userPhase.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "blanket_id", referencedColumnName = "id", nullable = false)
    public Blanket getBlanketByBlanketId() {
        return blanketByBlanketId;
    }

    public void setBlanketByBlanketId(Blanket blanketByBlanketId) {
        this.blanketByBlanketId = blanketByBlanketId;
    }
}
