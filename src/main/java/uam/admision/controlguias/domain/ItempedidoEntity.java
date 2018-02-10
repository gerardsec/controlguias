package uam.admision.controlguias.domain;


import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "itempedido", schema = "public", catalog = "controlguias")
@IdClass(ItempedidoEntityPK.class)
public class ItempedidoEntity {
    private Integer numPedido;
    private Integer item;
    private Integer cantidad;
    private BigInteger costoUnitario;
    private Integer tipoGuia;
    private Integer id;
    private Integer idInventario;
    //ORIGINAL private PedidoEntity pedidoByNumPedido;//
    private PedidoEntity pedido;
    /*private InventarioEntity inventarioByIdInventario;*/

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
    @Column(name = "cantidad", nullable = true)
    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Basic
    @Column(name = "costo_unitario", nullable = false, precision = 0)
    public BigInteger getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(BigInteger costoUnitario) {
        this.costoUnitario = costoUnitario;
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
    @Column(name = "id", nullable = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_inventario", nullable = false)
    public Integer getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItempedidoEntity that = (ItempedidoEntity) o;
        return Objects.equals(numPedido, that.numPedido) &&
                Objects.equals(item, that.item) &&
                Objects.equals(cantidad, that.cantidad) &&
                Objects.equals(costoUnitario, that.costoUnitario) &&
                Objects.equals(tipoGuia, that.tipoGuia) &&
                Objects.equals(id, that.id) &&
                Objects.equals(idInventario, that.idInventario);
    }

    @Override
    public int hashCode() {

        return Objects.hash(numPedido, item, cantidad, costoUnitario, tipoGuia, id, idInventario);
    }

    @ManyToOne
    @JoinColumn(name = "num_pedido", updatable = false, insertable = false)
    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }
    //ORIGINAL//
    /*@ManyToOne
    @JoinColumn(name = "num_pedido", referencedColumnName = "num_pedido", nullable = false)
    public PedidoEntity getPedidoByNumPedido() {
        return pedidoByNumPedido;
    }

    public void setPedidoByNumPedido(PedidoEntity pedidoByNumPedido) {
        this.pedidoByNumPedido = pedidoByNumPedido;
    }*/

    /*@ManyToOne
    @JoinColumn(name = "id_inventario", referencedColumnName = "id", nullable = false)
    public InventarioEntity getInventarioByIdInventario() {
        return inventarioByIdInventario;
    }

    public void setInventarioByIdInventario(InventarioEntity inventarioByIdInventario) {
        this.inventarioByIdInventario = inventarioByIdInventario;
    }*/
}
