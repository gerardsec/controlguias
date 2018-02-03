package uam.admision.controlguias.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "itempedido", schema = "public", catalog = "controlguias")
@IdClass(ItemPedidoEntityPK.class)
public class ItemPedidoEntity {
    private Integer numPedido;
    private Integer item;
    private Integer cantidad;
    private BigDecimal costoUnitario;
    private Integer tipoGuia;
    private Integer id;
    private Integer id_inventario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inventario")
    private InventarioEntity inventario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numPedido")
    private PedidoEntity pedido;

    public Integer getId_inventario() {
        return id_inventario;
    }

    public void setId_inventario(Integer id_inventario) {
        this.id_inventario = id_inventario;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        id = id;
    }


    @Id
    @Column(name = "num_pedido", nullable = false)
    public Integer getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(Integer numPedido) {
        this.numPedido = numPedido;
    }

    @Id
    @Column(name = "item", nullable = false)
    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
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
    @Column(name = "cantidad", nullable = true)
    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Basic
    @Column(name = "costo_unitario", nullable = false, precision = 2)
    public BigDecimal getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(BigDecimal costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedidoEntity that = (ItemPedidoEntity) o;
        return Objects.equals(numPedido, that.numPedido) &&
                Objects.equals(item, that.item) &&
                Objects.equals(tipoGuia, that.tipoGuia) &&
                Objects.equals(cantidad, that.cantidad) &&
                Objects.equals(costoUnitario, that.costoUnitario);
    }

    @Override
    public int hashCode() {

        return Objects.hash(numPedido, item, tipoGuia, cantidad, costoUnitario);
    }


}
