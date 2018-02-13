package uam.admision.controlguias.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tipoguia", schema = "public", catalog = "controlguias")
public class TipoguiaEntity {
    private Integer tipoGuia;
    private String nombreGuia;
    private String imagenPortada;
    private String isbn;
    private String caracteristicas;
    private List inventarios = new ArrayList<>();

    @Id
    @Column(name = "tipo_guia", nullable = false)
    public Integer getTipoGuia() {
        return tipoGuia;
    }

    public void setTipoGuia(Integer tipoGuia) {
        this.tipoGuia = tipoGuia;
    }

    @Basic
    @Column(name = "nombre_guia", nullable = false, length = 60)
    public String getNombreGuia() {
        return nombreGuia;
    }

    public void setNombreGuia(String nombreGuia) {
        this.nombreGuia = nombreGuia;
    }

    @Basic
    @Column(name = "imagen_portada", nullable = true, length = 40)
    public String getImagenPortada() {
        return imagenPortada;
    }

    public void setImagenPortada(String imagenPortada) {
        this.imagenPortada = imagenPortada;
    }

    @Basic
    @Column(name = "isbn", nullable = false, length = 20)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "caracteristicas", nullable = false, length = -1)
    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoguiaEntity that = (TipoguiaEntity) o;
        return Objects.equals(tipoGuia, that.tipoGuia) &&
                Objects.equals(nombreGuia, that.nombreGuia) &&
                Objects.equals(imagenPortada, that.imagenPortada) &&
                Objects.equals(isbn, that.isbn) &&
                Objects.equals(caracteristicas, that.caracteristicas);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tipoGuia, nombreGuia, imagenPortada, isbn, caracteristicas);
    }

    @OneToMany(mappedBy = "tipoguia", cascade = CascadeType.ALL)
    public List<InventarioEntity> getInventarios() {
        return inventarios;
    }

    public void setInventarios(List<InventarioEntity> inventarios) {
        this.inventarios = inventarios;
    }


}
