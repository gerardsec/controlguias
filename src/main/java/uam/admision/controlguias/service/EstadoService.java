package uam.admision.controlguias.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uam.admision.controlguias.domain.EstadoEntity;
import uam.admision.controlguias.repository.EstadoRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EstadoService {

    private static final Logger log = LoggerFactory.getLogger(EstadoService.class);
    @Autowired
    EstadoRepository repo;

    public List<EstadoEntity> listaTodo() {
        return repo.findAll(new Sort(Sort.Direction.ASC, "idEstado"));
    }

    public Map<Integer, String> listaEstados() {
        Map<Integer, String> mlistaestados = new HashMap<Integer, String>();
        List<EstadoEntity> listaEstados = repo.findAll();


        for (EstadoEntity estadoEntity : listaEstados) {

            Integer indice = estadoEntity.getIdEstado();
            String valor = estadoEntity.getDescribeLargo();

            mlistaestados.put(indice, valor);

        }
        return mlistaestados;
    }
}
