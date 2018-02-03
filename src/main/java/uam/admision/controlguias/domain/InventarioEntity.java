package uam.admision.controlguias.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;
import uam.admision.controlguias.utils.JsonDateSerializer;
import uam.admision.controlguias.utils.LocalDateTimeConverter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "inventario", schema = "public", catalog = "controlguias")
public class InventarioEntity {

    @Transient
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private Integer id;
    private String claveEntrada;
    private Integer tipoGuia;
    private Integer cantidadInicial;
    private Integer cantidadDisponible;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaEntrada;
    private Integer pedidoCompra;
    private Integer edicion;
    private String observaciones;

    @OneToMany (
            mappedBy = "inventario",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    private List<ItemPedidoEntity> itemsPedido = new ArrayList<>();

    public InventarioEntity(Integer id, String claveEntrada, Integer tipoGuia, Integer cantidadInicial,
                            Integer cantidadDisponible, String fechaEntradaST, Integer pedidoCompra,
                            Integer edicion, String observaciones) throws ParseException {
        this.id = id;
        this.claveEntrada = claveEntrada;
        this.tipoGuia = tipoGuia;
        this.cantidadInicial = cantidadInicial;
        this.cantidadDisponible = cantidadDisponible;

        this.fechaEntrada = LocalDate.parse(fechaEntradaST, formatter);
        this.pedidoCompra = pedidoCompra;
        this.edicion = edicion;
        this.observaciones = observaciones;

    }

    public InventarioEntity() {
    }

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @NotNull
    @Size(min = 2, max = 20)
    @Column(name = "clave_entrada", nullable = false, length = 20)
    public String getClaveEntrada() {
        return claveEntrada;
    }

    public void setClaveEntrada(String claveEntrada) {
        this.claveEntrada = claveEntrada;
    }

    @Basic
    @NotNull
    @Min(1)
    @Column(name = "tipo_guia", nullable = false)
    public Integer getTipoGuia() {
        return tipoGuia;
    }

    public void setTipoGuia(Integer tipoGuia) {
        this.tipoGuia = tipoGuia;
    }


    @Basic
    @NotNull
    @Min(1)
    @Column(name = "cantidad_inicial", nullable = false)
    public Integer getCantidadInicial() {
        return cantidadInicial;
    }

    public void setCantidadInicial(Integer cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    @Basic
    @NotNull
    @Column(name = "cantidad_disponible", nullable = false)
    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }


    @NotNull
    @Column(name = "fecha_entrada", nullable = false)
    @JsonSerialize(using = JsonDateSerializer.class)
    @Convert(converter = LocalDateTimeConverter.class)
    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    @Basic
    @Column(name = "pedido_compra", nullable = false)
    public Integer getPedidoCompra() {
        return pedidoCompra;
    }

    public void setPedidoCompra(Integer pedidoCompra) {
        this.pedidoCompra = pedidoCompra;
    }

    @Basic
    @Column(name = "edicion", nullable = false)
    public Integer getEdicion() {
        return edicion;
    }

    public void setEdicion(Integer edicion) {
        this.edicion = edicion;
    }

    @Basic
    @Column(name = "observaciones", nullable = true, length = 300)
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }


//    @JsonIgnore
//    public String getfechaEntradaAsShort() {
//        return formatter.format(fechaEntrada);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventarioEntity that = (InventarioEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(claveEntrada, that.claveEntrada) &&
                Objects.equals(tipoGuia, that.tipoGuia) &&
                Objects.equals(cantidadInicial, that.cantidadInicial) &&
                Objects.equals(cantidadDisponible, that.cantidadDisponible) &&
                Objects.equals(fechaEntrada, that.fechaEntrada) &&
                Objects.equals(pedidoCompra, that.pedidoCompra) &&
                Objects.equals(edicion, that.edicion) &&
                Objects.equals(observaciones, that.observaciones);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, claveEntrada, tipoGuia, cantidadInicial, cantidadDisponible, fechaEntrada, pedidoCompra, edicion, observaciones);
    }


}
