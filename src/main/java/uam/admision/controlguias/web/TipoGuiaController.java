package uam.admision.controlguias.web;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import uam.admision.controlguias.domain.TipoguiaEntity;
import uam.admision.controlguias.service.TipoguiaService;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
public class TipoGuiaController {

    private static final Logger logger = LoggerFactory.getLogger(TipoGuiaController.class);

    /*@Autowired
    private TipoguiaRepository repo;*/

     @Autowired
    private TipoguiaService repo;

   @Bean
   public LayoutDialect layoutDialect(){
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
    public ModelAndView addTipoGuia(ModelAndView model) {
        TipoguiaEntity tipoGuiaInicial = new TipoguiaEntity();
        model.addObject("tipoGuiaE", tipoGuiaInicial);
        model.setViewName("addTipoGuia");

        List<TipoguiaEntity> todosTiposGuia = repo.listaTodo();
        model.addObject("todosTiposGuia", todosTiposGuia);
        return model;
    }

    @PostMapping(value = "/guia/addTipoGuia")
    public ModelAndView addTipoGuia(@Valid TipoguiaEntity tipoGuiaE, BindingResult result) {
        ModelAndView formaAlta = new ModelAndView();
        //TipoguiaEntity tipoGuiaE = new TipoguiaEntity();
        if (result.hasErrors()) {
            logger.warn("Errores en la forma de alta de libros");

        } else {
            System.out.print("datos recibidos .....iniciando");
            System.out.print(tipoGuiaE.getIsbn());
            System.out.print(tipoGuiaE.getNombreGuia());
            System.out.print("datos recibidos .....terminando");

            try {
                repo.insertData(tipoGuiaE);
            } catch (ParseException e) {
                logger.warn("Error en parse datos");
                e.printStackTrace();
            }
        }

        List<TipoguiaEntity> todosTiposGuia = repo.listaTodo();
        formaAlta.addObject("todosTiposGuia", todosTiposGuia);

        formaAlta.setViewName("addTipoGuia");
        formaAlta.addObject("tipoGuiaE", tipoGuiaE);
        return formaAlta;
    }

}
