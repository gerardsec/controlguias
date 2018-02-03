package uam.admision.controlguias.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pedido", schema = "public", catalog = "controlguias")
public class PedidoEntity {
    private Integer numPedido;
    private String solicitante;
    private String areaSolicita;
    private String unidadSolicita;
    private LocalDate fechaSolicita;
    private LocalDate fechaSurtido;
    private LocalDate fechaEnvio;
    private LocalDate fechaRecibido;
    private Integer estado;

    @OneToMany(
            mappedBy = "pedido",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ItemPedidoEntity> itemsPedido = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_pedido", nullable = false)
    public Integer getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(Integer numPedido) {
        this.numPedido = numPedido;
    }

    @Basic
    @Column(name = "solicitante", nullable = false, length = 40)
    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    @Basic
    @Column(name = "area_solicita", nullable = false, length = 40)
    public String getAreaSolicita() {
        return areaSolicita;
    }

    public void setAreaSolicita(String areaSolicita) {
        this.areaSolicita = areaSolicita;
    }

    @Basic
    @Column(name = "unidad_solicita", nullable = false, length = 12)
    public String getUnidadSolicita() {
        return unidadSolicita;
    }

    public void setUnidadSolicita(String unidadSolicita) {
        this.unidadSolicita = unidadSolicita;
    }

    @Basic
    @Column(name = "fecha_solicita", nullable = false)
    public LocalDate getFechaSolicita() {
        return fechaSolicita;
    }

    public void setFechaSolicita(LocalDate fechaSolicita) {
        this.fechaSolicita = fechaSolicita;
    }

    @Basic
    @Column(name = "fecha_surtido", nullable = true)
    public LocalDate getFechaSurtido() {
        return fechaSurtido;
    }

    public void setFechaSurtido(LocalDate fechaSurtido) {
        this.fechaSurtido = fechaSurtido;
    }

    @Basic
    @Column(name = "fecha_envio", nullable = true)
    public LocalDate getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDate fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    @Basic
    @Column(name = "fecha_recibido", nullable = true)
    public LocalDate getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(LocalDate fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    @Basic
    @Column(name = "estado", nullable = false)
    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoEntity that = (PedidoEntity) o;
        return Objects.equals(numPedido, that.numPedido) &&
                Objects.equals(solicitante, that.solicitante) &&
                Objects.equals(areaSolicita, that.areaSolicita) &&
                Objects.equals(unidadSolicita, that.unidadSolicita) &&
                Objects.equals(fechaSolicita, that.fechaSolicita) &&
                Objects.equals(fechaSurtido, that.fechaSurtido) &&
                Objects.equals(fechaEnvio, that.fechaEnvio) &&
                Objects.equals(fechaRecibido, that.fechaRecibido) &&
                Objects.equals(estado, that.estado);
    }

    @Override
    public int hashCode() {

        return Objects.hash(numPedido, solicitante, areaSolicita, unidadSolicita, fechaSolicita, fechaSurtido, fechaEnvio, fechaRecibido, estado);
    }
}
