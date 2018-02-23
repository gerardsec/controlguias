package uam.admision.controlguias.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import uam.admision.controlguias.domain.ItempedidoEntity;
import uam.admision.controlguias.repository.ItemPedidoRepository;

import java.text.ParseException;
import java.util.List;

@Service
public class ItemPedidoService {

    private static final Logger log = LoggerFactory.getLogger(ItemPedidoService.class);

    @Autowired
    ItemPedidoRepository repo;

    public void insertData(ItempedidoEntity algo) throws ParseException {
        log.warn("> Item Pedido: Inserting data...");

        try {
            repo.save(algo);
        } catch (DataAccessException E) {
            log.warn("Item Pedido: Error al escribir nuevo registro");
            E.printStackTrace();
        }
        log.warn(">Ite Pedido: Done.");
    }

    public List<ItempedidoEntity> listaTodo() {return repo.findAll(); }

    //public Integer buscaClaveMayor() {
    //    return repo.maxItemPedido();
    //}

}
