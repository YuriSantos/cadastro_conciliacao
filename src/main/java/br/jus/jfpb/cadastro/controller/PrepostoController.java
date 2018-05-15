package br.jus.jfpb.cadastro.controller;

import br.jus.jfpb.cadastro.excecao.*;
import br.jus.jfpb.cadastro.model.Orgao;
import br.jus.jfpb.cadastro.model.Preposto;
import br.jus.jfpb.cadastro.service.OrgaoService;
import br.jus.jfpb.cadastro.service.PrepostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/preposto")
public class PrepostoController {
    @Autowired
    private PrepostoService prepostoService;
    @Autowired
    private OrgaoService orgaoService;

    @RequestMapping(method = RequestMethod.GET, value = {"novo"})
    public String novo(ModelMap map){
        System.out.println("Executando cadastro");
        Preposto preposto = new Preposto();
        map.addAttribute("preposto", preposto);
        map.addAttribute("orgao",selectOrgao());
        return "preposto/novo";
    }

    @RequestMapping(method = RequestMethod.POST, value = {"salvar"})
    public String save(@ModelAttribute("preposto")Preposto preposto, RedirectAttributes rm){
        try {
            if(preposto.getNome().contains("\"")) throw new CaractereEspeciaEx("Caractere inválido inserido:\"");
            if(preposto.getNome().contains("@")) throw new CaractereEspeciaEx("Caractere inválido inserido: @");
            if(preposto.getNome().contains("#")) throw new CaractereEspeciaEx("Caractere inválido inserido: #");
            if(preposto.getNome().contains("$")) throw new CaractereEspeciaEx("Caractere inválido inserido: $");
            if(preposto.getNome().contains("%")) throw new CaractereEspeciaEx("Caractere inválido inserido: %");
            if(preposto.getNome().contains("&")) throw new CaractereEspeciaEx("Caractere inválido inserido: &");
            if(preposto.getNome().equals(null) || preposto.getNome().equals("")) throw new NumeroProcessoVazio("O campo nome não pode ser vazio.");
            if(preposto.getNome().length() <= 3) throw new TamanhoCampoDigitadoEx("Nome muito pequeno");
            if(preposto.getNome().length() >= 255) throw new TamanhoCampoDigitadoEx("Nome muito grande.");
            if(preposto.temIdValido()){
                System.out.println("ok");
                prepostoService.atualizar(preposto);
                rm.addFlashAttribute("mensagem","Preposto atualizado com sucesso!!");
            }
            else
            {
                prepostoService.inserir(preposto);
                rm.addFlashAttribute("mensagem","Preposto salvo com sucesso!!");
            }
        }catch (NumeroProcessoVazio e){
            rm.addFlashAttribute("erro",e.getMessage());
        }catch (TamanhoCampoDigitadoEx e){
            rm.addFlashAttribute("erro",e.getMessage());
        }catch (CaractereEspeciaEx e) {
            rm.addFlashAttribute("erro", e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            rm.addFlashAttribute("erro","Erro ao salvar preposto!");
        }
        return "redirect:/preposto/novo";
    }

    @RequestMapping(method = RequestMethod.GET, value = {"listar"})
    public String list(ModelMap map) {

        List<Preposto> prepostos = prepostoService.listar();
        map.addAttribute("prepostos", prepostos);
        map.addAttribute("filtro", new Preposto());
        return "/preposto/listar";
    }

    @RequestMapping(method=RequestMethod.GET, value="/{id}/deletar")
    public String remove(@PathVariable("id") Long id, RedirectAttributes rm){
        Preposto preposto = prepostoService.buscarPorId(id);
        if(preposto != null){
            try{
                prepostoService.excluir(preposto);
                rm.addFlashAttribute("mensagem","Preposto deletado com sucesso!");
            }catch (Exception e){
                System.out.println("erro ao deletar");
                rm.addFlashAttribute("erro","Erro ao deletar o preposto!");
            }
        }
        return "redirect:/preposto/listar";
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/{id}/atualizar"})
    public String update(@PathVariable("id") Long id, ModelMap map) throws Exception{
        Preposto preposto = prepostoService.buscarPorId(id);
        map.addAttribute("preposto",preposto);
        map.addAttribute("orgao",selectOrgao());
        return "/preposto/novo";
    }

    public Map<Long, String> selectOrgao(){
        List<Orgao> orgaos = orgaoService.listar();
        Map<Long, String> mapa = new HashMap();
        // mapa.put(0L,"Selecione");
        for(Orgao orgao:orgaos){
            mapa.put(orgao.getId(), orgao.getNome());
        }
        return mapa;
    }
}
