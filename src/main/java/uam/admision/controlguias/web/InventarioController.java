package uam.admision.controlguias.web;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;
import uam.admision.controlguias.domain.InventarioEntity;
import uam.admision.controlguias.domain.TipoguiaEntity;
import uam.admision.controlguias.service.NotificationService;
import uam.admision.controlguias.service.InventarioService;
import uam.admision.controlguias.service.TipoguiaService;

import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class InventarioController {

    private static final Logger logger = LoggerFactory.getLogger(InventarioController.class);

    /*@Autowired
    private InventarioRepository repo;*/

    @Autowired
    private InventarioService repoInventario;

    @Autowired
    private TipoguiaService repoGuia;


    @Autowired
    private NotificationService notifyService;

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

    private TemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addDialect(new Java8TimeDialect());
        engine.setTemplateResolver(templateResolver);
        return engine;
    }


    @GetMapping(value = "/inventario/addInventario")
    //public ModelAndView addInventario(ModelAndView model) {
    public ModelAndView addInventario(ModelMap model) {
        InventarioEntity inventarioInicial = new InventarioEntity();

        Map<Integer, String> listaTiposGuia = repoGuia.guiaTipoNombre();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listaTiposGuia", listaTiposGuia);

        if (model.isEmpty()) {
            logger.warn("modelo vacío ....");

            modelAndView.addObject("inventarioE", inventarioInicial);

        }

        List<TipoguiaEntity> todosTiposGuia = repoGuia.listaTodo();


        List<InventarioEntity> todosInventario = repoInventario.listaTodo();
        modelAndView.addObject("todosTiposGuia", todosTiposGuia);
        modelAndView.addObject("todosInventario", todosInventario);
        modelAndView.setViewName("addInventario");
        return modelAndView;
    }

    @PostMapping(value = "/inventario/addInventario")
    public ModelAndView addInventario(@Valid InventarioEntity inventarioE, BindingResult result,
                                      RedirectAttributes redirectAttributes) {
        ModelAndView formaAlta = new ModelAndView();
        Integer maxIdInventario = repoInventario.buscaClaveMayor() + 1;
        logger.warn("Inventario: clave_mayor " + maxIdInventario.toString());


        if (result.hasErrors()) {
            //notifyService.addErrorMessage("Errores en la forma de alta de libros");
            logger.warn("Inventario: Errores en la forma de alta ");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.inventarioE", result);
            redirectAttributes.addFlashAttribute("inventarioE", inventarioE);

            return new ModelAndView("redirect:/inventario/addInventario");

        } else {
            System.out.print("datos recibidos .....iniciando");
            //System.out.print(InventarioE.getIsbn());
            //System.out.print(InventarioE.getNombreGuia());
            System.out.print("datos recibidos .....terminando");

            try {
                maxIdInventario = repoInventario.buscaClaveMayor() + 1;
                inventarioE.setId(maxIdInventario);
                repoInventario.insertData(inventarioE);
                logger.warn(inventarioE.getFechaEntrada().toString());
            } catch (ParseException e) {
                logger.warn("Inventario: Error en parse datos");
                e.printStackTrace();
            }

        }
        Map<Integer, String> listaTiposGuia = repoGuia.guiaTipoNombre();
        formaAlta.addObject("listaTiposGuia", listaTiposGuia);

        List<InventarioEntity> todosInventario = repoInventario.listaTodo();
        formaAlta.addObject("todosInventario", todosInventario);
        InventarioEntity inventarioInicial = new InventarioEntity();
        formaAlta.setViewName("addInventario");
        formaAlta.addObject("inventarioE", inventarioInicial);
        return formaAlta;
    }

}
