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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import uam.admision.controlguias.domain.TipoguiaEntity;
import uam.admision.controlguias.service.NotificationService;
import uam.admision.controlguias.service.TipoguiaService;


import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Controller
public class TipoGuiaController {

    private static final Logger logger = LoggerFactory.getLogger(TipoGuiaController.class);

    /*@Autowired
    private TipoguiaRepository repo;*/


    @Autowired
    private NotificationService notifyService;

    @Autowired
    private TipoguiaService repoGuia;

    /*@ModelAttribute("todosTiposGuia")
    public List<TipoguiaEntity>  generaCatalogoGuias(){
        return this.repoGuia.listaTodo();
    }*/

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

    @GetMapping(value = "/index")
    public ModelAndView index(ModelAndView model) {
        model.setViewName("index");
        return model;
    }

    @GetMapping(value = "/guia/addTipoGuia")
    public ModelAndView addTipoGuia(ModelMap model) {
        TipoguiaEntity tipoGuiaInicial = new TipoguiaEntity();

        ModelAndView modelAndView = new ModelAndView();
        if (model.isEmpty()) {
            logger.warn("modelo vacío ....");

            modelAndView.addObject("tipoGuiaE", tipoGuiaInicial);

        }

        List<TipoguiaEntity> todosTiposGuia = repoGuia.listaTodo();
        modelAndView.addObject("todosTiposGuia", todosTiposGuia);
        modelAndView.setViewName("addTipoGuia");
        return modelAndView;
    }

    @PostMapping(value = "/guia/addTipoGuia")
    public ModelAndView addTipoGuia(@Valid TipoguiaEntity tipoGuiaE, BindingResult result,
                                    RedirectAttributes redirectAttributes) {
        ModelAndView formaAlta = new ModelAndView();


        if (result.hasErrors()) {
            //notifyService.addErrorMessage("Errores en la forma de alta de libros");
            logger.warn("Errores en la forma de alta de libros");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.tipoGuiaE", result);
            redirectAttributes.addFlashAttribute("tipoGuiaE", tipoGuiaE);

            return new ModelAndView("redirect:/guia/addTipoGuia");

        } else {
            System.out.print("datos recibidos .....iniciando");
            System.out.print(tipoGuiaE.getIsbn());
            System.out.print(tipoGuiaE.getNombreGuia());
            System.out.print("datos recibidos .....terminando");

            try {
                repoGuia.insertData(tipoGuiaE);
            } catch (ParseException e) {
                logger.warn("Error en parse datos");
                e.printStackTrace();
            }
        }

        List<TipoguiaEntity> todosTiposGuia = repoGuia.listaTodo();
        formaAlta.addObject("todosTiposGuia", todosTiposGuia);
        TipoguiaEntity tipoGuiaInicial = new TipoguiaEntity();
        formaAlta.setViewName("addTipoGuia");
        formaAlta.addObject("tipoGuiaE", tipoGuiaInicial);
        return formaAlta;
    }

}
