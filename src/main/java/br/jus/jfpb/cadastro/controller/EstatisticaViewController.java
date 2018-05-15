package br.jus.jfpb.cadastro.controller;

import br.jus.jfpb.cadastro.model.Conciliacao;
import br.jus.jfpb.cadastro.model.Conciliador;
import br.jus.jfpb.cadastro.model.Preposto;
import br.jus.jfpb.cadastro.model.Vara;
import br.jus.jfpb.cadastro.service.ConciliacaoService;
import br.jus.jfpb.cadastro.service.ConciliadorService;
import br.jus.jfpb.cadastro.service.PrepostoService;
import br.jus.jfpb.cadastro.service.VaraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("ver_estatistica")
public class EstatisticaViewController {
    @Autowired
    private ConciliacaoService conciliacaoService;
    @Autowired
    private ConciliadorService conciliadorService;
    @Autowired
    private VaraService varaService;
    @Autowired
    private PrepostoService prepostoService;

    @RequestMapping("conciliador")
    public String conciliador(ModelMap map){

        List<Conciliador> conciliadores = conciliadorService.listar();



        map.addAttribute("conciliadores", conciliadores);
        map.addAttribute("filtro",new Conciliador());
        map.addAttribute("vara", selectVara());
        map.addAttribute("preposto", selectPreposto());
        return "estatistica/conciliador";
    }

    @RequestMapping("est")
    public String mensal(ModelMap map){
        int ano = 0;
        int ano2 = 0;

        List<Conciliacao> conciliacaos = conciliacaoService.listar();
        List<Conciliador> conciliadores = conciliadorService.listar();
        List<Vara> varas = varaService.listar();
        List<Preposto> prepostos = prepostoService.listar();

        for(Conciliacao conciliacao:conciliacaos){
            ano = conciliacao.getData().getYear();
            if(ano>ano2){
                ano2 = ano;
                map.addAttribute("ano",ano);
                System.out.println(ano);
            }

        }

        map.addAttribute("conciliadores", conciliadores);
        map.addAttribute("varas", varas);
        map.addAttribute("preposto", prepostos);
        return "estatistica/mensal";
    }

    @RequestMapping("anual")
    public String anual(){
        return "estatistica/anual";
    }

    @RequestMapping("total")
    public String total(){
        return "estatistica/total";
    }

    public Map<Long, Date> selectConciliacao(){
        List<Conciliacao> conciliacaos = conciliacaoService.listar();
        Map<Long,Date> mapa = new HashMap();
        // mapa.put(0L,"Selecione");
        for(Conciliacao conciliacao:conciliacaos){
            mapa.put(conciliacao.getId(), conciliacao.getData());
        }
        return mapa;
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
