package uam.admision.controlguias.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import uam.admision.controlguias.domain.InventarioEntity;
import uam.admision.controlguias.domain.ItempedidoEntity;
import uam.admision.controlguias.domain.PedidoEntity;
import uam.admision.controlguias.domain.TipoguiaEntity;
import uam.admision.controlguias.service.EstadoService;
import uam.admision.controlguias.service.InventarioService;
import uam.admision.controlguias.service.PedidoService;
import uam.admision.controlguias.service.TipoguiaService;

import javax.validation.Valid;
import java.math.BigInteger;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class PedidoBisController {

    private static final Logger logger = LoggerFactory.getLogger(PedidoBisController.class);

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

    @RequestMapping(value = "/prueba/addPedidoBis")
    public String muestraPedidos(final @ModelAttribute("pedidoE") PedidoEntity pedidoE,
                                 ModelMap model) {
        ItempedidoEntity itemagregar = new ItempedidoEntity();
        model.addAttribute("itemagregar", itemagregar);
        pedidoE.setEstado(1);
        pedidoE.setFechaSolicita(LocalDate.now());
        return "addPedidoBis";
    }

    @RequestMapping(value = "/prueba/addPedidoBis", params = {"save"})
    public String guardaPedidoE(
            final @Valid @ModelAttribute("pedidoE") PedidoEntity pedidoE, final BindingResult bindingResult,
            final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "addPedidoBis";
        }
        try {
            this.repoPedido.insertData(pedidoE);
        } catch (ParseException e) {
            logger.warn("Error pedido parse");
        }
        model.clear();
        return "redirect:/addPedidoBis";
    }

    @RequestMapping(value="/prueba/addPedidoBis", params = {"addItem"})
    public String addItem(final @ModelAttribute("pedidoE") PedidoEntity pedidoE,
                          final BindingResult bindingResult, ModelMap model) {

        logger.warn(" ----- Agregando item pedido");
        pedidoE.getItempedidos().add(new ItempedidoEntity());
        return "addPedidoBis";
    }
}