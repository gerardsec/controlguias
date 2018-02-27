package uam.admision.controlguias.domain;

/*@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
@JsonSerialize(using = JsonDateSerializer.class)
    @Convert(converter = LocalDateTimeConverter.class)
    LocalDate
*/

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import uam.admision.controlguias.utils.JsonDateSerializer;
import uam.admision.controlguias.utils.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "inventario", schema = "public", catalog = "controlguias")
@DynamicUpdate
public class InventarioEntity {
    private Integer id;
    private String claveEntrada;
    private Integer tipoGuia;
    //private Number AGREGAR costo unitario
    private Integer cantidadInicial;
    private Integer cantidadDisponible;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaEntrada;
    private Integer pedidoCompra;
    private Integer edicion;
    private String observaciones;
    private Float costoUnitario;
    private TipoguiaEntity tipoguia;
    /*private Collection<ItempedidoEntity> itempedidosById;*/



    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "clave_entrada", nullable = false, length = 20)
    public String getClaveEntrada() {
        return claveEntrada;
    }

    public void setClaveEntrada(String claveEntrada) {
        this.claveEntrada = claveEntrada;
    }

    @Basic
    @Column(name = "tipo_guia", nullable = false)
    public Integer getTipoGuia() {
        return tipoGuia;
    }

    public void setTipoGuia(Integer tipoGuia) {
        this.tipoGuia = tipoGuia;
    }

    @Basic
    @Column(name = "cantidad_inicial", nullable = false)
    public Integer getCantidadInicial() {
        return cantidadInicial;
    }

    public void setCantidadInicial(Integer cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    @Basic
    @Column(name = "cantidad_disponible", nullable = false)
    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    @Basic
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

    public Float getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(Float costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

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

    @ManyToOne
    @JoinColumn(name = "tipo_guia", referencedColumnName = "tipo_guia",updatable = false, insertable = false, nullable = false)
    public TipoguiaEntity getTipoguia() {
        return tipoguia;
    }

    public void setTipoguia(TipoguiaEntity tipoguia) {
        this.tipoguia = tipoguia;
    }


    /*@OneToMany(mappedBy = "inventarioByIdInventario")
    public Collection<ItempedidoEntity> getItempedidosById() {
        return itempedidosById;
    }

    public void setItempedidosById(Collection<ItempedidoEntity> itempedidosById) {
        this.itempedidosById = itempedidosById;
    }*/
}
