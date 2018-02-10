package uam.admision.controlguias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uam.admision.controlguias.domain.PedidoEntity;


import java.util.List;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {
    @Query("select j from PedidoEntity j where j.solicitante like %?1%")
    List<PedidoEntity> findByCustomQuery(String word);

    @Query("SELECT coalesce(max(ch.numPedido), 0) FROM PedidoEntity ch")
    Integer maxPedido();
    @Query("select j from PedidoEntity j where j.estado > 0")
    List<PedidoEntity> pedidosPendientes();


}
