package uam.admision.controlguias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uam.admision.controlguias.domain.EstadoEntity;

public interface EstadoRepository extends JpaRepository<EstadoEntity, Integer>{

    //public interface InventarioRepository extends JpaRepository<InventarioEntity, Integer>
}
