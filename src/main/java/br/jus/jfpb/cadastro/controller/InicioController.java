package br.jus.jfpb.cadastro.controller;

import br.jus.jfpb.cadastro.model.Conciliacao;
import br.jus.jfpb.cadastro.model.Estatistica;
import br.jus.jfpb.cadastro.service.ConciliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class InicioController {
    @Autowired
    ConciliacaoService conciliacaoService;

    @RequestMapping("/inicio")
    public String Execute(ModelMap map){
        Estatistica estatistica = new Estatistica();
        List<Conciliacao> conciliacaos = conciliacaoService.listar();



        System.out.println("Executando a pagina inicial");
        return "inicial";
    }
}
