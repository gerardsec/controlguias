package uam.admision.controlguias.domain;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;
import uam.admision.controlguias.utils.JsonDateSerializer;
import uam.admision.controlguias.utils.LocalDateTimeConverter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pedido", schema = "public", catalog = "controlguias")
public class PedidoEntity {
    private Integer numPedido;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoEntity that = (PedidoEntity) o;
        return Objects.equals(getNumPedido(), that.getNumPedido()) &&
                Objects.equals(getSolicitante(), that.getSolicitante()) &&
                Objects.equals(getAreaSolicita(), that.getAreaSolicita()) &&
                Objects.equals(getUnidadSolicita(), that.getUnidadSolicita()) &&
                Objects.equals(getFechaSolicita(), that.getFechaSolicita()) &&
                Objects.equals(getFechaSurtido(), that.getFechaSurtido()) &&
                Objects.equals(getFechaEnvio(), that.getFechaEnvio()) &&
                Objects.equals(getFechaRecibido(), that.getFechaRecibido()) &&
                Objects.equals(getEstado(), that.getEstado()) &&
                Objects.equals(getItempedidos(), that.getItempedidos());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getNumPedido(), getSolicitante(), getAreaSolicita(), getUnidadSolicita(), getFechaSolicita(), getFechaSurtido(), getFechaEnvio(), getFechaRecibido(), getEstado(), getItempedidos());
    }

    private String solicitante;
    private String areaSolicita;
    private String unidadSolicita;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaSolicita;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaSurtido;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaEnvio;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaRecibido;
    private Integer estado;
    //ORIGINAL private Collection<ItempedidoEntity> itempedidosByNumPedido; //
    //ORIGINAL private Collection<ItempedidoEntity> itempedidos; //
    private List itempedidos = new ArrayList<>();

    @Id
    @Column(name = "num_pedido", nullable = false)
    public Integer getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(Integer numPedido) {
        this.numPedido = numPedido;
    }

    @Basic
    @Column(name = "solicitante", nullable = false, length = 40)
    @Size(min = 1, max=40)
    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    @Basic
    @Column(name = "area_solicita", nullable = false, length = 40)
    @Size(min = 1, max=40)
    public String getAreaSolicita() {
        return areaSolicita;
    }

    public void setAreaSolicita(String areaSolicita) {
        this.areaSolicita = areaSolicita;
    }

    @Basic
    @Column(name = "unidad_solicita", nullable = false, length = 12)
    @Size(min = 1, max=40)
    public String getUnidadSolicita() {
        return unidadSolicita;
    }

    public void setUnidadSolicita(String unidadSolicita) {
        this.unidadSolicita = unidadSolicita;
    }

    @Basic
    @Column(name = "fecha_solicita", nullable = false)
    @JsonSerialize(using = JsonDateSerializer.class)
    @Convert(converter = LocalDateTimeConverter.class)
    public LocalDate getFechaSolicita() {
        return fechaSolicita;
    }

    public void setFechaSolicita(LocalDate fechaSolicita) {
        this.fechaSolicita = fechaSolicita;
    }

    @Basic
    @Column(name = "fecha_surtido", nullable = true)
    @JsonSerialize(using = JsonDateSerializer.class)
    @Convert(converter = LocalDateTimeConverter.class)
    public LocalDate getFechaSurtido() {
        return fechaSurtido;
    }

    public void setFechaSurtido(LocalDate fechaSurtido) {
        this.fechaSurtido = fechaSurtido;
    }

    @Basic
    @Column(name = "fecha_envio", nullable = true)
    @JsonSerialize(using = JsonDateSerializer.class)
    @Convert(converter = LocalDateTimeConverter.class)
    public LocalDate getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDate fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    @Basic
    @Column(name = "fecha_recibido", nullable = true)
    @JsonSerialize(using = JsonDateSerializer.class)
    @Convert(converter = LocalDateTimeConverter.class)
    public LocalDate getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(LocalDate fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    @Basic
    @Column(name = "estado", nullable = false)
    @Min(1)
    @Max(100)
    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }


    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    public List<ItempedidoEntity> getItempedidos() {
        return itempedidos;
    }

    public void setItempedidos(List<ItempedidoEntity> itempedidos) {
        this.itempedidos = itempedidos;
    }
    //ORIGINAL//
    /*@OneToMany(mappedBy = "pedidoByNumPedido")
    public Collection<ItempedidoEntity> getItempedidosByNumPedido() {
        return itempedidosByNumPedido;
    }

    public void setItempedidosByNumPedido(Collection<ItempedidoEntity> itempedidosByNumPedido) {
        this.itempedidosByNumPedido = itempedidosByNumPedido;
    }*/
}
