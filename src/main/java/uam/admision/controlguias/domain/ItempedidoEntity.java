package uam.admision.controlguias.domain;


import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
@Table(name = "itempedido", schema = "public", catalog = "controlguias")
@IdClass(ItempedidoEntityPK.class)
public class ItempedidoEntity {
    private Integer numPedido;
    private Integer item;
    private Integer cantidad;
    private Float costoUnitario;
    private Integer tipoGuia;
    private Integer id;
    private Integer idInventario;
    private PedidoEntity pedido;
    @Transient private String nombreGuiaTem;
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
    @Min(1)
    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Basic
    @Column(name = "costo_unitario", nullable = false, precision = 0)
    public Float getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(Float costoUnitario) {
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

    @Transient
    @Access(AccessType.PROPERTY)
    public String getNombreGuiaTem() {
        return nombreGuiaTem;
    }
    @Transient
    public void setNombreGuiaTem(String nombreGuiaTem) {
        this.nombreGuiaTem = nombreGuiaTem;
    }

    @ManyToOne
    @JoinColumn(name = "num_pedido", updatable = false, insertable = false)
    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItempedidoEntity that = (ItempedidoEntity) o;
        return Objects.equals(getNumPedido(), that.getNumPedido()) &&
                Objects.equals(getItem(), that.getItem()) &&
                Objects.equals(getCantidad(), that.getCantidad()) &&
                Objects.equals(getCostoUnitario(), that.getCostoUnitario()) &&
                Objects.equals(getTipoGuia(), that.getTipoGuia()) &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getIdInventario(), that.getIdInventario()) &&
                Objects.equals(getPedido(), that.getPedido()) &&
                Objects.equals(getNombreGuiaTem(), that.getNombreGuiaTem());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getNumPedido(), getItem(), getCantidad(), getCostoUnitario(), getTipoGuia(), getId(), getIdInventario(), getPedido(), getNombreGuiaTem());
    }

    /*@ManyToOne
    @JoinColumn(name = "id_inventario", referencedColumnName = "id", nullable = false)
    public InventarioEntity getInventarioByIdInventario() {
        return inventarioByIdInventario;
    }

    public void setInventarioByIdInventario(InventarioEntity inventarioByIdInventario) {
        this.inventarioByIdInventario = inventarioByIdInventario;
    }*/
}
