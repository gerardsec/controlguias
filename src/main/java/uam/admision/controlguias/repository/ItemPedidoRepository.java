package uam.admision.controlguias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uam.admision.controlguias.domain.ItempedidoEntity;
import uam.admision.controlguias.domain.ItempedidoEntityPK;

import java.util.List;

public interface ItemPedidoRepository extends JpaRepository<ItempedidoEntity, ItempedidoEntityPK> {
   // @Query("select j from ItempedidoEntity j where j.tipoGuia like %?1%")
   // List<ItempedidoEntity> findByCustomQuery(String word);


//    @Query("SELECT coalesce(max(ch.id), 1) FROM ItempedidoEntity ch")
//    Integer maxItemPedido();

}
