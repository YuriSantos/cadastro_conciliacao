package br.jus.jfpb.cadastro.controller;

import br.jus.jfpb.cadastro.model.Conciliacao;
import br.jus.jfpb.cadastro.model.Estatistica;
import br.jus.jfpb.cadastro.service.ConciliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class JSONController {
    @RequestMapping("teste")
    public String execute(){
        return "chart";
    }
}

/*public class JSONController {
    @RequestMapping(value = "/teste", method = RequestMethod.GET)
    @ResponseBody
    public Estatistica Execute(){
        System.out.println("Executando a pagina inicial");
        Estatistica estatistica = new Estatistica();
        estatistica.setName("nome");
//        estatistica.setTeste(new String[]{"teste1","teste2"});

        return estatistica;
    }
}*/

