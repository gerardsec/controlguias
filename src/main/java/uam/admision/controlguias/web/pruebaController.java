package uam.admision.controlguias.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uam.admision.controlguias.domain.PedidoEntity;
import uam.admision.controlguias.service.EstadoService;
import uam.admision.controlguias.service.InventarioService;
import uam.admision.controlguias.service.PedidoService;
import uam.admision.controlguias.service.TipoguiaService;


import java.text.ParseException;
import java.util.Map;

@Controller
public class pruebaController {

    private static final Logger logger = LoggerFactory.getLogger(pruebaController.class);

    @Autowired
    private PedidoService repoPedido;

    @Autowired
    private TipoguiaService repoGuia;

    @Autowired
    private InventarioService repoInventario;

    @Autowired
    private EstadoService repoEstado;

    @ModelAttribute("listaTiposGuia")
    public Map<Integer, String> listaTiposGuias() {
        Map<Integer, String> listaTiposGuia = repoGuia.guiaTipoNombre();
        return listaTiposGuia;
    }

    @ModelAttribute("listaEstados")
    public Map<Integer, String> listaEstados() {
        Map<Integer, String> listaEstados = repoEstado.listaEstados();
        return listaEstados;
    }

    @GetMapping({"/prueba/addPedidosBis"})
    public String listaPedidosBis(final PedidoEntity pedidoE, final ModelMap model) {
        logger.warn("iniciando addPEDIDOS");
        pedidoE.setNumPedido(1);
        model.addAttribute("pedidoE", pedidoE);
        return "addPedidosBis";
    }

    @PostMapping(value = "/prueba/addPedidosBis", params = {"save"})
    public String savePedido(
            final PedidoEntity pedidoE, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            logger.warn("Errores en la forma");
            return "addPedidosBis";
        }
        logger.warn("Insertando pedido");
        try {
            repoPedido.insertData(pedidoE);
        } catch (ParseException e) {
            logger.warn("Error al insertar pedido");
        }

        model.clear();
        return "redirect:/prueba/addPedidosBis";
    }
}
