package uam.admision.controlguias.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ItempedidoEntityPK implements Serializable {
    private Integer numPedido;
    private Integer item;

    @Column(name = "num_pedido", nullable = false)
    @Id
    public Integer getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(Integer numPedido) {
        this.numPedido = numPedido;
    }

    @Column(name = "item", nullable = false)
    @Id
    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItempedidoEntityPK that = (ItempedidoEntityPK) o;
        return Objects.equals(numPedido, that.numPedido) &&
                Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {

        return Objects.hash(numPedido, item);
    }
}
