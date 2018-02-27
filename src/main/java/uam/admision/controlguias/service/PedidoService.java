package uam.admision.controlguias.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uam.admision.controlguias.domain.PedidoEntity;
import uam.admision.controlguias.repository.PedidoRepository;

import java.text.ParseException;
import java.util.List;

@Service
public class PedidoService {

    private static final Logger log = LoggerFactory.getLogger(PedidoService.class);

    @Autowired
    PedidoRepository repo;

    public void insertData(PedidoEntity algo) throws ParseException {
        log.warn("> Pedido: Inserting data...");

        try {
            repo.save(algo);
        } catch (DataAccessException E) {
            log.warn("Pedido: Error al escribir nuevo registro");
            E.printStackTrace();
        }
        log.warn(">Pedido: Done.");
    }

    public List<PedidoEntity> listaTodo() {
        return repo.findAll();
    }

    public List<PedidoEntity> queryagusto(String word) {
        return repo.findByCustomQuery(word);
    }

    public Integer buscaClaveMayor() {
        return repo.maxPedido();
    }

    public List<PedidoEntity> pedidosPendientes() {
        return repo.pedidosPendientes();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = PedidoTransactionException.class)
    public void realizaPedido(PedidoEntity pedidoEntity) throws PedidoTransactionException {

        for (int i = 0; i < pedidoEntity.getItempedidos().size(); i++) {
            pedidoEntity.getItempedidos().get(i).setNumPedido(pedidoEntity.getNumPedido());
            pedidoEntity.getItempedidos().get(i).setItem(i + 1);}

            try {
                log.warn("insertando pedido transaction");
                this.insertData(pedidoEntity);
            } catch (ParseException e) {
                log.warn("error transaction pedido");
                e.printStackTrace();
            }


    }

}
