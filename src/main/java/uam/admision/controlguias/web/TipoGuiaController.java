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
    
    @Autowired
    private NotificationService notifyService;

    @Autowired
    private TipoguiaService repoGuia;

    @ModelAttribute("todosTiposGuia")
    public List<TipoguiaEntity> generatodosTiposGuia() {
        List<TipoguiaEntity> todosTiposGuia = repoGuia.listaTodo();
        return todosTiposGuia;
    }

    @GetMapping(value = "/index")
    public ModelAndView index(ModelAndView model) {
        model.setViewName("index");
        return model;
    }

    @GetMapping(value = "/guia/addTipoGuia")
    public String addTipoGuia(final @ModelAttribute("tipoGuiaE") TipoguiaEntity tipoGuiaE,
                              ModelMap model) {
        return "addTipoGuia";
    }

    @PostMapping(value = "/guia/addTipoGuia", params = {"saveGuia"})
    public String addTipoGuia(
            final @Valid @ModelAttribute("tipoGuiaE") TipoguiaEntity tipoGuiaE,
            final BindingResult result,
            final ModelMap model) {
        if (result.hasErrors()) {
            //notifyService.addErrorMessage("Errores en la forma de alta de libros");
            logger.warn("Errores en la forma de alta de libros");

            return "addTipoGuia";
        }
        System.out.print(tipoGuiaE.getNombreGuia());
        System.out.print("datos recibidos .....terminando");

        try {
            repoGuia.insertData(tipoGuiaE);
        } catch (ParseException e) {
            logger.warn("Error en parse datos");
            e.printStackTrace();
        }
        model.clear();
        return "redirect:/guia/addTipoGuia";
    }
}
