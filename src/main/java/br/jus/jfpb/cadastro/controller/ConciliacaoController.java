package br.jus.jfpb.cadastro.controller;

import br.jus.jfpb.cadastro.excecao.*;
import br.jus.jfpb.cadastro.model.*;
import br.jus.jfpb.cadastro.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("conciliacao")
public class ConciliacaoController {
    @Autowired
    private ConciliacaoService conciliacaoService;
    @Autowired
    private ConciliadorService conciliadorService;
    @Autowired
    private ConselhoService conselhoService;
    @Autowired
    private OrgaoService orgaoService;
    @Autowired
    private VaraService varaService;
    @Autowired
    private PrepostoService prepostoService;

    Date local = new Date(System.currentTimeMillis());

    @RequestMapping(method = RequestMethod.GET, value = {"novo"})
    public String novo(ModelMap map){
        System.out.println("Executando cadastro");
        Conciliacao conciliacao = new Conciliacao();
        map.addAttribute("conciliacao", conciliacao);
        map.addAttribute("conciliador", selectConciliadores());
        map.addAttribute("conselho", selectConselho());
        map.addAttribute("orgao", selectOrgao());
        map.addAttribute("vara", selectVara());
        map.addAttribute("preposto", selectPreposto());
        return "conciliacao/novo";
    }

    @RequestMapping(method = RequestMethod.GET, value = {"listar"})
    public String list(ModelMap map) {

        List<Conciliacao> conciliacaos = conciliacaoService.listar();
        map.addAttribute("conciliacaos", conciliacaos);
        map.addAttribute("filtro", new Conciliacao());
        return "/conciliacao/listar";
    }

   /* public Page<Conciliacao> findAll(Pageable pageable){
        Assert.notNull(pageable, "Pagina não pode ser nula!");
        return conciliacaoRepository.findAll(pageable);
    }*/

    @RequestMapping(method = RequestMethod.GET, value = {"teste"})
    public String teste(ModelMap map) {

        List<Conciliacao> conciliacaos = conciliacaoService.listar();
        map.addAttribute("conciliacaos", conciliacaos);
        map.addAttribute("filtro", new Conciliacao());
        return "/conciliacao/teste2";
    }

    @RequestMapping(method = RequestMethod.POST, value = {"salvar"})
    public String save(@ModelAttribute("conciliacao") Conciliacao conciliacao, RedirectAttributes rm, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            System.out.println("Entrou visse!!!!");
            return "conciliacao/novo";
        }
        try {
            System.out.print(conciliacao.getValor_string());
            //Validações de Formulário no lado do servidor
            if(conciliacao.getResultado()==0) throw new TipoAcordoInvalido("Tipo de acordo inválido");
            if(conciliacao.getNumeroprocesso().contains("\"")) throw new CaractereEspeciaEx("Caractere inválido inserido:\"");
            if(conciliacao.getNumeroprocesso().contains("@")) throw new CaractereEspeciaEx("Caractere inválido inserido: @");
            if(conciliacao.getNumeroprocesso().contains("#")) throw new CaractereEspeciaEx("Caractere inválido inserido: #");
            if(conciliacao.getNumeroprocesso().contains("$")) throw new CaractereEspeciaEx("Caractere inválido inserido: $");
            if(conciliacao.getNumeroprocesso().contains("%")) throw new CaractereEspeciaEx("Caractere inválido inserido: %");
            if(conciliacao.getNumeroprocesso().contains("&")) throw new CaractereEspeciaEx("Caractere inválido inserido: &");
            if(conciliacao.getNumeroprocesso().equals(null) || conciliacao.getNumeroprocesso().equals("")) throw new NumeroProcessoVazio("Número de Processo não pode ser vazio.");
            if(conciliacao.getNumeroprocesso().length() <= 3) throw new TamanhoCampoDigitadoEx("Número de processo muito pequeno");
            if(conciliacao.getNumeroprocesso().length() >= 30) throw new TamanhoCampoDigitadoEx("Número de processo não pode ser maior do que 30 Caracteres.");
            if(local.before(conciliacao.getData())) throw new DataFuturaEx("Data invalida, a data não pode ser maior que hoje.");
            if(conciliacao.getData().equals(null) || conciliacao.getData().equals("")) throw new DataVaziaEx("O campo data não pode ser vazio.");
            if(conciliacao.getConciliador().getId()==1) throw new SelecioneExcepition("O conciliador precisa ser selecionado");

            //Conversor de moeda
            if(conciliacao.getValor_string().equals(null) || conciliacao.getValor_string().equals("")){
                conciliacao.setValor_string("R$ 0,00");
                System.out.println("Entrou no 0 /n");
            }
                System.out.println("Olha aí o que ta voltando: "+conciliacao.getValor_string()+" !!!!!!!!!!!!");
                String c = conciliacao.getValor_string();
                c = c.replace(".","");
                c = c.replace("R$","");
                c = c.replace(",",".");
                conciliacao.setValor(Float.parseFloat(c));

            System.out.println("Olha aí o que ta voltando: "+conciliacao.getData()+" !!!!!!!!!!!!");

            if(conciliacao.temIdValido()){
                System.out.println("ok");

                conciliacaoService.atualizar(conciliacao);
                rm.addFlashAttribute("mensagem","Conciliação atualizada com sucesso");
            }
            else
            {
                conciliacaoService.inserir(conciliacao);
                rm.addFlashAttribute("mensagem","Conciliação salva com sucesso");
            }
        }catch (TipoAcordoInvalido e){
            rm.addFlashAttribute("erro", e.getMessage());
        }catch (NumeroProcessoVazio e){
            rm.addFlashAttribute("erro",e.getMessage());
        }catch (DataFuturaEx e){
            rm.addFlashAttribute("erro",e.getMessage());
        }catch (TamanhoCampoDigitadoEx e){
            rm.addFlashAttribute("erro",e.getMessage());
        }catch (CaractereEspeciaEx e) {
            rm.addFlashAttribute("erro", e.getMessage());
        }catch (ValorVazioEx e){
            rm.addFlashAttribute("erro",e.getMessage());
        }catch (DataVaziaEx e){
            rm.addFlashAttribute("erro", e.getMessage());
        }catch (SelecioneExcepition e){
            rm.addFlashAttribute("erro",e.getMessage());
        }catch (Exception e){
            rm.addFlashAttribute("erro","Erro ao salvar");
            e.getStackTrace();
        }
        return "redirect:/conciliacao/novo";
    }

    @RequestMapping(method=RequestMethod.GET, value="/{id}/deletar")
    public String remove(@PathVariable("id") Long id,RedirectAttributes rm){
        Conciliacao conciliacao = conciliacaoService.buscarPorId(id);
        if(conciliacao != null){
            try{
                conciliacaoService.excluir(conciliacao);
                rm.addFlashAttribute("mensagem","Conciliação deletada com sucesso");
            }catch (Exception e){
                System.out.println("erro ao deletar");
                rm.addFlashAttribute("erro","Erro ao deletar");
            }
        }
        return "redirect:/conciliacao/listar";
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/{id}/atualizar"})
    public String update(@PathVariable("id") Long id, ModelMap map) throws Exception{
        Conciliacao conciliacao = conciliacaoService.buscarPorId(id);
        map.addAttribute("conciliacao", conciliacao);
        map.addAttribute("conciliador", selectConciliadores());
        map.addAttribute("conselho", selectConselho());
        map.addAttribute("orgao", selectOrgao());
        map.addAttribute("vara", selectVara());
        return "/conciliacao/novo";
    }

    public Map<Long, String> selectConciliadores(){
        List<Conciliador> conciliadores = conciliadorService.listar();
        Map<Long, String> mapa = new HashMap();
       // mapa.put(0L,"Selecione");
        for(Conciliador conciliador:conciliadores){
            mapa.put(conciliador.getId(), conciliador.getNome());
        }
        return mapa;
    }

    public Map<Long, String> selectConselho(){
        List<Conselho> conselhos = conselhoService.listar();
        Map<Long, String> mapa = new HashMap();
       // mapa.put(0L,"Selecione");
        for(Conselho conselho:conselhos){
            mapa.put(conselho.getId(), conselho.getNome());
        }
        return mapa;
    }

    public Map<Long, String> selectOrgao(){
        List<Orgao> orgaos = orgaoService.listar();
        Map<Long, String> mapa = new HashMap();
        //mapa.put(0L,"Selecione");
        for(Orgao orgao:orgaos){
            mapa.put(orgao.getId(), orgao.getNome());
        }
        return mapa;
    }

    public Map<Long, String> selectVara(){
        List<Vara> varas = varaService.listar();
        Map<Long, String> mapa = new HashMap();
       // mapa.put(0L,"Selecione");
        for(Vara vara:varas){
            mapa.put(vara.getId(), vara.getNome());
        }
        return mapa;
    }

    public Map<Long, String> selectPreposto(){
        List<Preposto> prepostos = prepostoService.listar();
        Map<Long, String> mapa = new HashMap();
        // mapa.put(0L,"Selecione");
        for(Preposto preposto:prepostos){
            mapa.put(preposto.getId(), preposto.getNome());
        }
        return mapa;
    }
}
