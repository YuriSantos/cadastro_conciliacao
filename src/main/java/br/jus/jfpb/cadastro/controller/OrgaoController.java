package br.jus.jfpb.cadastro.controller;

import br.jus.jfpb.cadastro.model.Orgao;
import br.jus.jfpb.cadastro.service.OrgaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.List;

@Controller
@RequestMapping("orgao")
public class OrgaoController {
    @Autowired
    private OrgaoService orgaoService;
    @RequestMapping(method = RequestMethod.GET, value = {"novo"})
    public String novo(ModelMap map){
        System.out.println("Executando cadastro");
        Orgao orgao = new Orgao();
        map.addAttribute("orgao", orgao);
        return "orgao/novo";
    }

    @RequestMapping(method = RequestMethod.POST, value = {"salvar"})
    public String save(@ModelAttribute("orgao")Orgao orgao, RedirectAttributes rm){
        try {
            if(orgao.temIdValido()){
                System.out.println("ok");
                orgaoService.atualizar(orgao);
                rm.addFlashAttribute("mensagem","Orgão atualizado com sucesso!");
            }
            else
            {
                orgaoService.inserir(orgao);
                rm.addFlashAttribute("mensagem","Orgão salvo com sucesso!");
            }
        }catch (Exception e){
            e.printStackTrace();
            rm.addFlashAttribute("erro","Erro ao salvar orgão!");
        }
        return "redirect:/orgao/novo";
    }

    @RequestMapping(method = RequestMethod.GET, value = {"listar"})
    public String list(ModelMap map) {

        List<Orgao> orgaos = orgaoService.listar();
        map.addAttribute("orgaos", orgaos);
        map.addAttribute("filtro", new Orgao());
        return "/orgao/listar";
    }

    @RequestMapping(method=RequestMethod.GET, value="/{id}/deletar")
    public String remove(@PathVariable("id") Long id, RedirectAttributes rm){
        Orgao orgao = orgaoService.buscarPorId(id);
        if(orgao != null){
            try{
                orgaoService.excluir(orgao);
                rm.addFlashAttribute("mensagem","Orgão Deletado com sucesso!!");
            }catch (Exception e){
                System.out.println("erro ao deletar");
                rm.addFlashAttribute("erro","Erro ao deletar orgão!");
            }
        }
        return "redirect:/orgao/listar";
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/{id}/atualizar"})
    public String update(@PathVariable("id") Long id, ModelMap map) throws Exception{
        Orgao orgao = orgaoService.buscarPorId(id);
        map.addAttribute("orgao",orgao);
        return "/orgao/novo";
    }
}
