package br.jus.jfpb.cadastro.controller;

import br.jus.jfpb.cadastro.dao.EstatisticaDAO;
import br.jus.jfpb.cadastro.model.*;
import br.jus.jfpb.cadastro.service.ConciliacaoService;
import br.jus.jfpb.cadastro.service.ConciliadorService;
import br.jus.jfpb.cadastro.service.PrepostoService;
import br.jus.jfpb.cadastro.service.VaraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("estatistica")
public class EstatisticaController {
    @Autowired
    ConciliacaoService conciliacaoService;

    List<Conciliacao> conciliacaos;

    @RequestMapping(value = "total", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Estatistica> execute(ModelMap map){
        Estatistica estatistica = new Estatistica();
        Estatistica estatistica2 = new Estatistica();
        Estatistica estatistica3 = new Estatistica();
        Estatistica estatistica4 = new Estatistica();
        int sem=0;
        int com=0;
        int aus=0;
        int res=0;
        List<Conciliacao> conciliacaos = conciliacaoService.listar();
        List<Estatistica> lista = new ArrayList<Estatistica>();
        estatistica.setName("Sem Acordo");
        estatistica2.setName("Com Acordo");
        estatistica3.setName("Ausente");
        estatistica4.setName("Redesignação");
        for(Conciliacao conciliacao : conciliacaos){
            if(conciliacao.getResultado()==1){
                sem++;
                estatistica.setY(sem);
            }

            if(conciliacao.getResultado()==2){
                com++;
                estatistica2.setY(com);
            }

            if(conciliacao.getResultado()==3){
                aus++;
                estatistica3.setY(aus);
            }

            if(conciliacao.getResultado()==4){
                res++;
                estatistica4.setY(res);
            }

        }
        lista.add(estatistica);
        lista.add(estatistica2);
        //lista.add(estatistica3);
        //lista.add(estatistica4);
        map.addAttribute("estatistica",lista);
        return lista;
    }
    @RequestMapping(value = "mensal/{ano}-{mes}/{conc}/{vara}/{preposto}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Estatistica> mensal(@PathVariable("ano") String ano, @PathVariable("mes") String mes,
                                                  @PathVariable("conc") String conc,@PathVariable("vara") String vara, @PathVariable("preposto") String preposto){
        Estatistica estatistica = new Estatistica();
        Estatistica estatistica2 = new Estatistica();
        Estatistica estatistica3 = new Estatistica();
        Estatistica estatistica4 = new Estatistica();
        Estatistica estatistica5 = new Estatistica();
        System.out.println(ano+mes+conc+vara);

        List<Estatistica> lista = new ArrayList<Estatistica>();;
        int sem=0;
        int com=0;
        int aus=0;
        int res=0;

        //Geral Anual
        if((mes.equals("0"))&&(vara.equals("0")) && (conc.equals("0")) && !(ano.equals("0")) && (preposto.equals("0"))){
            conciliacaos = conciliacaoService.listaano(ano);
            System.out.println("Entrou 1");
        }

        //Geral Mensal
        if((vara.equals("0")) && (conc.equals("0")) && !(mes.equals("0"))&&!(ano.equals("0")) && (preposto.equals("0"))){
            conciliacaos = conciliacaoService.listarmes(ano,mes);
            System.out.println("Entrou 2");
        }

        //Geral Total
        if((vara.equals("0")) && (conc.equals("0")) && (mes.equals("0"))&&(ano.equals("0")) && (preposto.equals("0"))){
            conciliacaos = conciliacaoService.listar();
            System.out.println("Entrou 2");
        }

        //Conciliador Mensal
        if((vara.equals("0")) && !(conc.equals("0")) && !(mes.equals("0"))&&!(ano.equals("0")) && (preposto.equals("0"))){
            conciliacaos = conciliacaoService.concEstme(ano,mes,conc);
            System.out.println("Entrou 3");

        }

        //Conciliador Total
        if((vara.equals("0")) && !(conc.equals("0")) && (mes.equals("0"))&&(ano.equals("0")) && (preposto.equals("0"))){
            conciliacaos = conciliacaoService.concEstto(conc);
            System.out.println("Entrou 3");

        }

        //Conciliador Anual
        if((vara.equals("0")) && !(conc.equals("0")) && (mes.equals("0"))&&!(ano.equals("0")) && (preposto.equals("0"))){
            conciliacaos = conciliacaoService.concEstan(ano,conc);
            System.out.println("Entrou 4");
        }

        //Vara Anual
        if(!(vara.equals("0")) && (conc.equals("0")) && (mes.equals("0"))&&!(ano.equals("0")) && (preposto.equals("0"))){
            conciliacaos = conciliacaoService.varaEstano(ano,vara);
            System.out.println("Entrou 4");
        }

        //Vara Mensal
        if(!(vara.equals("0")) && (conc.equals("0")) && !(mes.equals("0"))&&!(ano.equals("0")) && (preposto.equals("0"))){
            conciliacaos = conciliacaoService.varaEstme(ano,mes,vara);
            System.out.println("Entrou 4");
        }

        //Vara Total
        if(!(vara.equals("0")) && (conc.equals("0")) && (mes.equals("0"))&&(ano.equals("0")) && (preposto.equals("0"))){
            conciliacaos = conciliacaoService.varaEstto(vara);
            System.out.println("Entrou 4");
        }

        //Preposto Anual
        if((vara.equals("0")) && (conc.equals("0")) && (mes.equals("0"))&&!(ano.equals("0")) && !(preposto.equals("0"))){
            conciliacaos = conciliacaoService.prepEstano(ano,preposto);
            System.out.println("Entrou 4");
        }

        //Preposto Mensal
        if((vara.equals("0")) && (conc.equals("0")) && !(mes.equals("0"))&&!(ano.equals("0")) && !(preposto.equals("0"))){
            conciliacaos = conciliacaoService.varaEstme(ano,mes,preposto);
            System.out.println("Entrou 4");
        }
        //Preposto total
        if(!(vara.equals("0")) && (conc.equals("0")) && (mes.equals("0"))&&(ano.equals("0")) && !(preposto.equals("0"))){
            conciliacaos = conciliacaoService.prepEstto(preposto);
            System.out.println("Entrou 4");
        }

        estatistica.setName("Sem Acordo");
        estatistica2.setName("Com Acordo");
        estatistica3.setName("Ausente");
        estatistica4.setName("Redesignação");
        estatistica5.setName("Retirada de Pauta/Cancelada");
        for(Conciliacao conciliacao : conciliacaos){
            if((conciliacao.getResultado()==1)){
                sem++;
                estatistica.setY(sem);
            }

            if(conciliacao.getResultado()==2){
                com++;
                estatistica2.setY(com);
            }

            if(conciliacao.getResultado()==3){
                aus++;
                estatistica3.setY(aus);
            }

            if(conciliacao.getResultado()==4){
                res++;
                estatistica4.setY(res);
            }

            if(conciliacao.getResultado()==5){
                res++;
                estatistica5.setY(res);
            }

        }
        lista.add(estatistica);
        lista.add(estatistica2);
        lista.add(estatistica3);
        lista.add(estatistica4);
        lista.add(estatistica5);
        System.out.println("Ano: "+ano +" Mês: "+ mes);
        return lista;
    }

}

