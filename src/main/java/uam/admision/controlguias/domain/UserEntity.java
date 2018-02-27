package uam.admision.controlguias.domain;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import uam.admision.controlguias.utils.JsonDateSerializer;
import uam.admision.controlguias.utils.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "User", schema = "public", catalog = "controlguias")
@DynamicUpdate
public class UserEntity {
    private Long id;
    private String username;
    private String passwordHash;
    private String fullName;
    private Integer status;
    private Integer blocked;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate timeBlocked;
    private String email;
    private Integer group;
    private String area;
    private String unidad;
    private String telefono;
    private String ubicacion;

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
    @JsonSerialize(using = JsonDateSerializer.class)
    @Convert(converter = LocalDateTimeConverter.class)
    public LocalDate getTimeBlocked() {
        return timeBlocked;
    }

    public void setTimeBlocked(LocalDate timeBlocked) {
        this.timeBlocked = timeBlocked;
    }

    @Basic
    @Column(name = "mail", nullable = false, length = 40)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "group", nullable = false)
    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    @Basic
    @Column(name = "area", nullable = false, length = 40)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column(name = "unidad", nullable = false, length = 40)
    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    @Basic
    @Column(name = "telefono", nullable = false, length = 20)
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Basic
    @Column(name = "ubicacion", nullable = false, length = 80)
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getUsername(), that.getUsername()) &&
                Objects.equals(getPasswordHash(), that.getPasswordHash()) &&
                Objects.equals(getFullName(), that.getFullName()) &&
                Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getBlocked(), that.getBlocked()) &&
                Objects.equals(getTimeBlocked(), that.getTimeBlocked()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getGroup(), that.getGroup()) &&
                Objects.equals(getArea(), that.getArea()) &&
                Objects.equals(getUnidad(), that.getUnidad()) &&
                Objects.equals(getTelefono(), that.getTelefono()) &&
                Objects.equals(getUbicacion(), that.getUbicacion());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getUsername(), getPasswordHash(), getFullName(), getStatus(), getBlocked(), getTimeBlocked(), getEmail(), getGroup(), getArea(), getUnidad(), getTelefono(), getUbicacion());
    }


}
