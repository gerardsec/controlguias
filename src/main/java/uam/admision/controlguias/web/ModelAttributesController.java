package uam.admision.controlguias.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import uam.admision.controlguias.domain.TipoguiaEntity;
import uam.admision.controlguias.service.TipoguiaService;

import java.util.List;

@RestController
public class ModelAttributesController {

    /*@Autowired
    private TipoguiaService tipoGuiaService;

    @ModelAttribute("todosTiposGuia")
    public List<TipoguiaEntity>  generaCatalogoGuias(){
        return this.tipoGuiaService.listaTodo();
    }*/
}
