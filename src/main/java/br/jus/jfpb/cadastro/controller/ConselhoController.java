package br.jus.jfpb.cadastro.controller;

import br.jus.jfpb.cadastro.model.Conselho;
import br.jus.jfpb.cadastro.service.ConselhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("conselho")
public class ConselhoController {
    @Autowired
    private ConselhoService conselhoService;

    @RequestMapping(method = RequestMethod.GET, value = {"novo"})
    public String novo(ModelMap map){
        System.out.println("Executando o Conselho");
        Conselho conselho = new Conselho();
        map.addAttribute("conselho", conselho);
        return "conselho/novo";
    }

    @RequestMapping(method = RequestMethod.POST, value = {"salvar"})

    public String save(@ModelAttribute("conselho")Conselho conselho){
        try {
            if(conselho.temIdValido()){
                System.out.println("ok");
                conselhoService.atualizar(conselho);
            }
            else
            {
                conselhoService.inserir(conselho);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/conselho/novo";
    }

    @RequestMapping(method = RequestMethod.GET, value = {"listar"})
    public String list(ModelMap map) {

        List<Conselho> conselhos = conselhoService.listar();
        map.addAttribute("conselho", conselhos);
        map.addAttribute("filtro", new Conselho());
        return "/conselho/listar";
    }

    @RequestMapping(method=RequestMethod.GET, value="/{id}/deletar")
    public String remove(@PathVariable("id") Long id){
        Conselho conselho = conselhoService.buscarPorId(id);
        if(conselho != null){
            try{
                conselhoService.excluir(conselho);
            }catch (Exception e){
                System.out.println("erro ao deletar");

            }
        }
        return "redirect:/conselho/listar";
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/{id}/atualizar"})
    public String update(@PathVariable("id") Long id, ModelMap map) throws Exception{
       Conselho conselho = conselhoService.buscarPorId(id);
        map.addAttribute("conselho",conselho);
        return "/conselho/novo";
    }
}
