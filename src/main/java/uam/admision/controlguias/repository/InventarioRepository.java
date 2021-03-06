package uam.admision.controlguias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uam.admision.controlguias.domain.InventarioEntity;
import uam.admision.controlguias.domain.TipoguiaEntity;

import java.util.List;

public interface InventarioRepository extends JpaRepository<InventarioEntity, Integer> {

    @Query("select j from InventarioEntity j where j.id = ?1")
    InventarioEntity encuentraById(Integer id);

    @Query("select j from InventarioEntity j where j.pedidoCompra like %?1%")
    List<InventarioEntity> findByCustomQuery(String word);

    @Query("SELECT coalesce(max(ch.id), 0) FROM InventarioEntity ch")
    Integer maxInventario();

    @Query("select j from InventarioEntity j where j.cantidadDisponible > 0")
    List<InventarioEntity> inventarioDisponibles();

    @Query("select j from InventarioEntity j where j.id = 1")
    InventarioEntity encuentra1();

}
