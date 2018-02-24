package uam.admision.controlguias.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import uam.admision.controlguias.domain.InventarioEntity;
import uam.admision.controlguias.domain.ItempedidoEntity;
import uam.admision.controlguias.domain.PedidoEntity;
import uam.admision.controlguias.domain.TipoguiaEntity;
import uam.admision.controlguias.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class PedidoController {

    private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);

    /*@Autowired
    private InventarioRepository repo;*/

    @Autowired
    private PedidoService repoPedido;

    @Autowired
    private TipoguiaService repoGuia;

    @Autowired
    private InventarioService repoInventario;

    @Autowired
    private EstadoService repoEstado;

    @Autowired
    private ItemPedidoService repoItemPedido;

    @ModelAttribute("listaTiposGuia")
    public Map<Integer, String> generaListaTiposGuia() {
        Map<Integer, String> listaTiposGuia = repoGuia.guiaTipoNombre();
        return listaTiposGuia;
    }

    @ModelAttribute("listaEstados")
    public Map<Integer, String> generaListaEstados() {
        Map<Integer, String> listaEstados = repoEstado.listaEstados();
        return listaEstados;
    }

    @ModelAttribute("todosTiposGuia")
    public List<TipoguiaEntity> generatodosTiposGuia() {
        List<TipoguiaEntity> todosTiposGuia = repoGuia.listaTodo();
        return todosTiposGuia;
    }

    @ModelAttribute("listaInventarioDisponible")
    public List<InventarioEntity> generalistaInventarioDisponible() {
        List<InventarioEntity> listaInventarioDisponible = repoInventario.listaTodo();
        return listaInventarioDisponible;
    }

    @ModelAttribute("todosPedido")
    public List<PedidoEntity> todosPedido() {
        List<PedidoEntity> todosPedido = repoPedido.listaTodo();
        return todosPedido;
    }


    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".html");
        //resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @RequestMapping(value = "/pedido/addPedido")
    public String muestraPedidos(final @ModelAttribute("pedidoE") PedidoEntity pedidoE,
                                 ModelMap model) {
        ItempedidoEntity itemagregar = new ItempedidoEntity();
        model.addAttribute("itemagregar", itemagregar);
        pedidoE.setEstado(1);
        pedidoE.setFechaSolicita(LocalDate.now());
        return "addPedido";
    }

    @RequestMapping(value = "/pedido/addPedido", params = {"savePedido"})
    public String guardaPedidoE(
            final @Valid @ModelAttribute("pedidoE") PedidoEntity pedidoE, final BindingResult bindingResult,
            final ModelMap model,
            final @ModelAttribute("itemagregar") ItempedidoEntity itemagregar) {
        logger.warn("Solicitando guardar pedido");
        if (bindingResult.hasErrors()) {
            return "addPedido";
        }

        //Definiendo la fecha de pedido
        pedidoE.setFechaSolicita(LocalDate.now());
        //Determinando la llave: num_pedido
        Integer numPedidoActual = repoPedido.buscaClaveMayor() + 1;
        pedidoE.setNumPedido(numPedidoActual);

        logger.warn("itemes loop");
///ERROR en esta parte....
        System.out.println(pedidoE.getItempedidos().toString());
        //definiendo llaves en itemspedido
        for (int i = 0; i < pedidoE.getItempedidos().size(); i++) {
            pedidoE.getItempedidos().get(i).setNumPedido(pedidoE.getNumPedido());
            pedidoE.getItempedidos().get(i).setItem(i);
            System.out.println(pedidoE.getItempedidos().get(i).toString());
        }

        try {
            this.repoPedido.insertData(pedidoE);
        } catch (ParseException e) {
            logger.warn("Error pedido parse");
        }
        model.clear();
        return "redirect:/pedido/addPedido";
    }

    @RequestMapping(value = "/pedido/addPedido", params = {"addItem"})
    public String addItem(final @ModelAttribute("pedidoE") PedidoEntity pedidoE,
                          final BindingResult bindingResult,
                          final @ModelAttribute("itemagregar") ItempedidoEntity itemagregar) {

        logger.warn(" ----- Agregando item pedido" + itemagregar.getIdInventario().toString());

        /* Se actualiza el itemagregar con los datos del inventario y se agrega al pedido*/

        InventarioEntity inventario = repoInventario.buscaPorId(itemagregar.getIdInventario());

        if (inventario == null) {
            logger.warn("Inventario nulo");
        } else {
            logger.warn("inventario no es null");
        }
        ItempedidoEntity itemNuevo = new ItempedidoEntity();
        logger.warn("después nuevo item");
        if (inventario.getCantidadDisponible() >= itemagregar.getCantidad()) {
            itemNuevo.setCantidad(itemagregar.getCantidad());
        } else {
            itemNuevo.setCantidad(inventario.getCantidadDisponible());
        }
        logger.debug("después if");
        itemNuevo.setIdInventario(itemagregar.getIdInventario());
        itemNuevo.setCostoUnitario(inventario.getCostoUnitario());
        itemNuevo.setTipoGuia(inventario.getTipoGuia());
        logger.warn("tipo guia" + inventario.getTipoGuia().toString());

        Map<Integer, String> lTiposGuia = repoGuia.guiaTipoNombre();
        itemNuevo.setNombreGuiaTem(lTiposGuia.get(inventario.getTipoGuia()));


        //Integer itempedidoMayor = repoItemPedido.buscaClaveMayor();
        logger.debug("antes consultar itempedido");
        //List<ItempedidoEntity> itemstodos = repoItemPedido.listaTodo();
        itemNuevo.setId(10);
        itemNuevo.setIdInventario(itemagregar.getIdInventario());

        pedidoE.getItempedidos().add(itemNuevo);

        return "addPedido";
    }

    @RequestMapping(value = "/pedido/addPedido", params = {"quitaItem"})
    public String removeRow(
            final @ModelAttribute("pedidoE") PedidoEntity pedidoE,
            final BindingResult bindingResult,
            final HttpServletRequest req,
            final @ModelAttribute("itemagregar") ItempedidoEntity itemagregar) {
        final Integer rowId = Integer.valueOf(req.getParameter("quitaItem"));
        pedidoE.getItempedidos().remove(rowId.intValue());
        //seedStarter.getRows().remove(rowId.intValue());
        return "addPedido";
    }
}