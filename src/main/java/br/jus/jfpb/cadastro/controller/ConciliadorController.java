package br.jus.jfpb.cadastro.controller;

import br.jus.jfpb.cadastro.excecao.*;
import br.jus.jfpb.cadastro.model.Conciliador;
import br.jus.jfpb.cadastro.service.ConciliadorService;
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
@RequestMapping("conciliador")
public class ConciliadorController {
    @Autowired
    private ConciliadorService conciliadorService;

    @RequestMapping(method = RequestMethod.GET, value = {"novo"})
    public String novo(ModelMap map){
        System.out.println("Executando cadastro");
        Conciliador conciliador = new Conciliador();
        map.addAttribute("conciliador", conciliador);
        return "conciliador/novo";
    }

    @RequestMapping(method = RequestMethod.POST, value = {"salvar"})
    public String save(@ModelAttribute("conciliador")Conciliador conciliador, RedirectAttributes rm){
        System.out.println("id:"+conciliador.getId());
        System.out.println("nome:"+conciliador.getNome());
        try {
            if(conciliador.getNome().contains("\"")) throw new CaractereEspeciaEx("Caractere inválido inserido:\"");
            if(conciliador.getNome().contains("@")) throw new CaractereEspeciaEx("Caractere inválido inserido: @");
            if(conciliador.getNome().contains("#")) throw new CaractereEspeciaEx("Caractere inválido inserido: #");
            if(conciliador.getNome().contains("$")) throw new CaractereEspeciaEx("Caractere inválido inserido: $");
            if(conciliador.getNome().contains("%")) throw new CaractereEspeciaEx("Caractere inválido inserido: %");
            if(conciliador.getNome().contains("&")) throw new CaractereEspeciaEx("Caractere inválido inserido: &");
            if(conciliador.getNome().equals(null) || conciliador.getNome().equals("")) throw new NumeroProcessoVazio("O campo nome não pode ser vazio.");
            if(conciliador.getNome().length() <= 3) throw new TamanhoCampoDigitadoEx("Nome muito pequeno");
            if(conciliador.getNome().length() >= 255) throw new TamanhoCampoDigitadoEx("Nome muito grande.");
            if(conciliador.temIdValido()){
                System.out.println("ok");
                conciliadorService.atualizar(conciliador);
                rm.addFlashAttribute("mensagem","Conciliador atualizado com sucesso!");
            }
            else
            {
                conciliadorService.inserir(conciliador);
                System.out.println("id:"+conciliador.getId());
                rm.addFlashAttribute("mensagem","Conciliador salvo com sucesso!");
            }
        }catch (NumeroProcessoVazio e){
            rm.addFlashAttribute("erro",e.getMessage());
        }catch (TamanhoCampoDigitadoEx e){
            rm.addFlashAttribute("erro",e.getMessage());
        }catch (CaractereEspeciaEx e) {
            rm.addFlashAttribute("erro", e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            rm.addFlashAttribute("erro","Erro ao salvar conciliador!");
        }
        return "redirect:/conciliador/novo";
    }

    @RequestMapping(method = RequestMethod.GET, value = {"listar"})
    public String list(ModelMap map) {

        List<Conciliador> conciliadors = conciliadorService.listar();
        map.addAttribute("conciliadors", conciliadors);
        map.addAttribute("filtro", new Conciliador());
        return "/conciliador/listar";
    }

    @RequestMapping(method=RequestMethod.GET, value="/{id}/deletar")
    public String remove(@PathVariable("id") Long id, RedirectAttributes rm){
        Conciliador conciliador = conciliadorService.buscarPorId(id);
        if(conciliador != null){
            try{
                conciliadorService.excluir(conciliador);
                rm.addFlashAttribute("mensagem","Conciliador deletado com sucesso!");
            }catch (Exception e){
                System.out.println("erro ao deletar");
                rm.addFlashAttribute("erro","Erro ao deletar conciliador!");
            }
        }
        return "redirect:/conciliador/listar";
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/{id}/atualizar"})
    public String update(@PathVariable("id") Long id, ModelMap map) throws Exception{

        map.addAttribute("conciliador",conciliadorService.buscarPorId(id));
        return "/conciliador/novo";
    }


}


