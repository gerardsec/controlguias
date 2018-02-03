package uam.admision.controlguias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uam.admision.controlguias.domain.TipoguiaEntity;

import java.util.List;
import java.util.Map;

public interface TipoguiaRepository extends JpaRepository<TipoguiaEntity, Integer> {
    @Query("select j from TipoguiaEntity j where j.nombreGuia like %?1%")
    List<TipoguiaEntity> findByCustomQuery(String word);

}
