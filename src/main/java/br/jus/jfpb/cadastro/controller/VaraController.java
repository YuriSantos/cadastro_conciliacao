package br.jus.jfpb.cadastro.controller;

import br.jus.jfpb.cadastro.model.Vara;
import br.jus.jfpb.cadastro.service.VaraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/vara")
public class VaraController {
    @Autowired
    private VaraService varaService;

    @RequestMapping(method = RequestMethod.GET, value = {"novo"})
    public String novo(ModelMap map){
        System.out.println("Executando cadastro");
        Vara vara = new Vara();
        map.addAttribute("vara", vara);
        return "vara/novo";
    }

    @RequestMapping(method = RequestMethod.POST, value = {"salvar"})
    public String save(@ModelAttribute("vara")Vara vara, RedirectAttributes rm){
        try {
            if(vara.temIdValido()){
                System.out.println("ok");
                varaService.atualizar(vara);
                rm.addFlashAttribute("mensagem","Vara atualizada com sucesso!!");
            }
            else
            {
                varaService.inserir(vara);
                rm.addFlashAttribute("mensagem","Vara salva com sucesso!!");
            }
        }catch (Exception e){
            e.printStackTrace();
            rm.addFlashAttribute("erro","Erro ao salvar vara!");
        }
        return "redirect:/vara/novo";
    }

    @RequestMapping(method = RequestMethod.GET, value = {"listar"})
    public String list(ModelMap map) {

        List<Vara> varas = varaService.listar();
        map.addAttribute("varas", varas);
        map.addAttribute("filtro", new Vara());
        return "/vara/listar";
    }

    @RequestMapping(method=RequestMethod.GET, value="/{id}/deletar")
    public String remove(@PathVariable("id") Long id, RedirectAttributes rm){
        Vara vara = varaService.buscarPorId(id);
        if(vara != null){
            try{
                varaService.excluir(vara);
                rm.addFlashAttribute("mensagem","Vara deletada com sucesso!");
            }catch (Exception e){
                System.out.println("erro ao deletar");
                rm.addFlashAttribute("erro","Erro ao deletar a vara!");
            }
        }
        return "redirect:/vara/listar";
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/{id}/atualizar"})
    public String update(@PathVariable("id") Long id, ModelMap map) throws Exception{
       Vara vara = varaService.buscarPorId(id);
        map.addAttribute("vara",vara);
        return "/vara/novo";
    }
}
