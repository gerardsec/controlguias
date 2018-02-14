package uam.admision.controlguias.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import uam.admision.controlguias.domain.InventarioEntity;
import uam.admision.controlguias.domain.ItempedidoEntity;
import uam.admision.controlguias.domain.PedidoEntity;
import uam.admision.controlguias.domain.TipoguiaEntity;

import uam.admision.controlguias.service.EstadoService;
import uam.admision.controlguias.service.InventarioService;
import uam.admision.controlguias.service.PedidoService;
import uam.admision.controlguias.service.TipoguiaService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigInteger;
import java.text.ParseException;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
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


    @GetMapping(value = "/pedido/addPedido")
    public ModelAndView addPedido(ModelMap model) {
        logger.warn("Entrando a getmapping");

        PedidoEntity pedidoInicial = new PedidoEntity();
        pedidoInicial.setEstado(1);
        ItempedidoEntity itemAgregar = new ItempedidoEntity();

        Map<Integer, String> listaTiposGuia = repoGuia.guiaTipoNombre();
        Map<Integer, String> listaEstados = repoEstado.listaEstados();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listaTiposGuia", listaTiposGuia);
        modelAndView.addObject("listaEstados", listaEstados);

        if (model.isEmpty()) {
            logger.warn("modelo vacío ....");

            modelAndView.addObject("pedidoE", pedidoInicial);

        }

        List<TipoguiaEntity> todosTiposGuia = repoGuia.listaTodo();
        List<InventarioEntity> listaInventarioDisponible = repoInventario.listaTodo();
        List<PedidoEntity> todosPedido = repoPedido.listaTodo();

        modelAndView.addObject("todosTiposGuia", todosTiposGuia);
        modelAndView.addObject("todosPedido", todosPedido);
        modelAndView.addObject("itemagregar", itemAgregar);
        modelAndView.addObject("listaInventarioDisponible", listaInventarioDisponible);
        modelAndView.setViewName("addPedido");
        return modelAndView;
    }


    @PostMapping(value = "/pedido/addPedido")
    public ModelAndView addPedido(@Valid PedidoEntity pedidoE, BindingResult result,
                                  ItempedidoEntity itemagregar,
                                  RedirectAttributes redirectAttributes,
                                  @RequestParam Map<String, String> paramMap) {
        logger.warn("Entrando a postmapping");



        ModelAndView formaAlta = new ModelAndView();
        Integer maxIdPedido = repoPedido.buscaClaveMayor() + 1;
        logger.warn("Inventario: clave_mayor " + maxIdPedido.toString());

        if (paramMap.containsKey("addRow")){

            logger.warn("----> Solicitando agregar renglón");
            //redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.inventarioE", result);
            redirectAttributes.addFlashAttribute("inventarioE", pedidoE);
            pedidoE.getItempedidos().add(new ItempedidoEntity());

            return new ModelAndView("redirect:/pedido/addPedido");
        }

        /*if (paramMap.containsKey("removeRow")){
            final PedidoEntity seedStarter, final BindingResult bindingResult,
            final HttpServletRequest req) {
                final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
                seedStarter.getItempedidos().remove(rowId.intValue());
                return "/pedido/addPedido";
        }*/

        if (result.hasErrors()) {
            //notifyService.addErrorMessage("Errores en la forma de alta de libros");
            logger.warn("Pedido: Errores en la forma de alta ");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.pedidoE", result);
            redirectAttributes.addFlashAttribute("pedidoE", pedidoE);
            redirectAttributes.addFlashAttribute("itemagregar", itemagregar);

            return new ModelAndView("redirect:/pedido/addPedido");

        } else {
            System.out.print("datos recibidos .....iniciando");

            System.out.print("datos recibidos .....terminando");

            try {
                maxIdPedido = repoPedido.buscaClaveMayor() + 1;
                pedidoE.setNumPedido(maxIdPedido);
                LocalDate hoyFecha = LocalDate.now();
                pedidoE.setFechaSolicita(hoyFecha);
                pedidoE.setFechaEnvio(null);
                pedidoE.setFechaRecibido(null);
                pedidoE.setFechaSurtido(null);

                //items
                ItempedidoEntity itempedidoEntity = new ItempedidoEntity();
                itempedidoEntity.setNumPedido(pedidoE.getNumPedido());
                itempedidoEntity.setItem(1);
                itempedidoEntity.setCantidad(1000);
                itempedidoEntity.setCostoUnitario(BigInteger.valueOf(10));
                itempedidoEntity.setTipoGuia(1);
                itempedidoEntity.setIdInventario(26);
                itempedidoEntity.setId(1);
                pedidoE.getItempedidos().add(itempedidoEntity);

                repoPedido.insertData(pedidoE);
                //  logger.warn(pedidoE.getFechaEntrada().toString());
            } catch (ParseException e) {
                logger.warn("Pedido: Error en parse datos");
                e.printStackTrace();
            }

        }
        /*Map<Integer, String> listaTiposGuia = repoGuia.guiaTipoNombre();
        formaAlta.addObject("listaTiposGuia", listaTiposGuia);

        List<PedidoEntity> todosPedido = repoPedido.listaTodo();
        formaAlta.addObject("todosPedido", todosPedido);
        PedidoEntity pedidoInicial = new PedidoEntity();
        formaAlta.setViewName("addPedido");
        formaAlta.addObject("pedidoE", pedidoInicial);
        return formaAlta;*/
        return new ModelAndView("redirect:/pedido/addPedido");
    }


}