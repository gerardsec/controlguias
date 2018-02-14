package uam.admision.controlguias.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uam.admision.controlguias.domain.TipoguiaEntity;
import uam.admision.controlguias.repository.TipoguiaRepository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TipoguiaService {

    private static final Logger log = LoggerFactory.getLogger(TipoguiaService.class);

    @Autowired
    TipoguiaRepository repo;

    public void insertData(TipoguiaEntity algo) throws ParseException {
        log.info("> Inserting data...");
        //TipoguiaEntity algo = new TipoguiaEntity();
        try {
            repo.save(algo);
        } catch (DataAccessException E) {
            log.info("Error al escribir nuevo registro");
        }


        log.info("> Done.");

    }

    public List<TipoguiaEntity> listaTodo() {
        return repo.findAll(new Sort(Sort.Direction.ASC, "tipoGuia"));
    }

    public List<TipoguiaEntity> queryagusto(String word) {
        return repo.findByCustomQuery(word);
    }

    public Map<Integer, String> guiaTipoNombre() {
        Map<Integer, String> listaGuiaNombre = new HashMap<Integer, String>();
        List<TipoguiaEntity> listaGuias = repo.findAll();

        Integer i = 0;
        for (TipoguiaEntity tipoguiaEntity : listaGuias) {

            Integer indice = tipoguiaEntity.getTipoGuia();
            String valor = tipoguiaEntity.getNombreGuia();

            //System.out.println("tipo guia:" + indice);
            //System.out.println("nombre guia:" + valor);

            listaGuiaNombre.put(indice, valor);
            i++;
        }
        return listaGuiaNombre;
    }

}
