package uam.admision.controlguias.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import uam.admision.controlguias.domain.TipoguiaEntity;
import uam.admision.controlguias.repository.TipoguiaRepository;

import java.text.ParseException;
import java.util.List;

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
        }
        catch (DataAccessException E) {
            log.info("Error al escribir nuevo registro");
        }


        log.info("> Done.");

    }

    public List<TipoguiaEntity> listaTodo(){
        return repo.findAll();
    }
    public List<TipoguiaEntity> queryagusto(String word){
        return repo.findByCustomQuery(word);
    }

}
