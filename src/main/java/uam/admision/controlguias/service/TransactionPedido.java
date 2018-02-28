package uam.admision.controlguias.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uam.admision.controlguias.domain.InventarioEntity;
import uam.admision.controlguias.domain.PedidoEntity;
import uam.admision.controlguias.repository.InventarioRepository;
import uam.admision.controlguias.repository.PedidoRepository;

import java.text.ParseException;
import java.util.List;

public class TransactionPedido {

    @Autowired
    PedidoRepository repoPedido;
    @Autowired
    InventarioRepository repoInventario;
    @Autowired
    PedidoService pedidoService;

    private static final Logger log = LoggerFactory.getLogger(TransactionPedido.class);

    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = PedidoTransactionException.class)
    public void realizaPedido(PedidoEntity pedidoEntity) throws PedidoTransactionException {

        for (int i = 0; i < pedidoEntity.getItempedidos().size(); i++) {
            pedidoEntity.getItempedidos().get(i).setNumPedido(pedidoEntity.getNumPedido());
            pedidoEntity.getItempedidos().get(i).setItem(i + 1);
        }
        try {
            for (int i = 0; i < pedidoEntity.getItempedidos().size(); i++) {
                log.warn("buscando inventario con inv="+pedidoEntity.getItempedidos().get(i).getIdInventario());
                //InventarioEntity inventario = repoInventario.findOne(pedidoEntity.getItempedidos().get(i).getIdInventario());
                List<InventarioEntity> inventario = repoInventario.findAll();
                ///log.warn("inventario encontrado con tipo guía:"+inventario.getTipoGuia()+" disponible:"+inventario.getCantidadDisponible());
                ///Integer disponibleFinal = inventario.getCantidadDisponible() - pedidoEntity.getItempedidos().get(i).getCantidad();
                ///log.warn("antes guardar inventario. disponible final:"+disponibleFinal);
                ///inventario.setCantidadDisponible(disponibleFinal);
                //repoInventario.save(inventario);
            }
            log.warn("insertando pedido transaction");
            pedidoService.insertData(pedidoEntity);
        } catch (ParseException e) {
            log.warn("error parse pedido");
        }


    }
}
