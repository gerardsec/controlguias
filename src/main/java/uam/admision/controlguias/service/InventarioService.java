package uam.admision.controlguias.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import uam.admision.controlguias.domain.InventarioEntity;
import uam.admision.controlguias.repository.InventarioRepository;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class InventarioService {

    private static final Logger log = LoggerFactory.getLogger(InventarioService.class);

    @Autowired
    InventarioRepository repo;

    public void insertData(InventarioEntity algo) throws ParseException {
        log.warn("> Inventario: Inserting data...");

        try {
            repo.save(algo);
        }
        catch (DataAccessException E) {
            log.warn("Inventario: Error al escribir nuevo registro");
            E.printStackTrace();
        }
        log.warn(">Inventario: Done.");
    }

    public List<InventarioEntity> listaTodo(){
        return repo.findAll();
    }
    public List<InventarioEntity> queryagusto(String word){
        return repo.findByCustomQuery(word);
    }
    public Integer buscaClaveMayor() {return repo.maxInventario();}
    public List<InventarioEntity> disponiblesVenta(){return repo.inventarioDisponibles();}
    public InventarioEntity buscaPorId(Integer id){return repo.encuentraById(id);}

}
