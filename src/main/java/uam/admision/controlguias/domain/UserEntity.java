package uam.admision.controlguias.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "User", schema = "public", catalog = "controlguias")
public class UserEntity {
    private Long id;
    private String username;
    private String passwordHash;
    private String fullName;
    private Integer status;
    private Integer blocked;
    private LocalDate timeBlocked;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "passwordHash", nullable = false, length = 20)
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Basic
    @Column(name = "fullName", nullable = false, length = 60)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "blocked", nullable = true)
    public Integer getBlocked() {
        return blocked;
    }

    public void setBlocked(Integer blocked) {
        this.blocked = blocked;
    }

    @Basic
    @Column(name = "timeBlocked", nullable = true)
    public LocalDate getTimeBlocked() {
        return timeBlocked;
    }

    public void setTimeBlocked(LocalDate timeBlocked) {
        this.timeBlocked = timeBlocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(passwordHash, that.passwordHash) &&
                Objects.equals(fullName, that.fullName) &&
                Objects.equals(status, that.status) &&
                Objects.equals(blocked, that.blocked) &&
                Objects.equals(timeBlocked, that.timeBlocked);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, passwordHash, fullName, status, blocked, timeBlocked);
    }
}
