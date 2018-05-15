package br.jus.jfpb.cadastro.service;

import br.jus.jfpb.cadastro.dao.ConciliacaoDAO;
import br.jus.jfpb.cadastro.model.Conciliacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ConciliacaoService {
    protected EntityManager manager;

    @Autowired
    private ConciliacaoDAO conciliacaoDAO;

    public void inserir(Conciliacao conciliacao) throws Exception{
        conciliacaoDAO.inserir(conciliacao);
    }

    public void atualizar(Conciliacao conciliacao) throws Exception{
        conciliacaoDAO.atualizar(conciliacao);
    }

    public void excluir(Conciliacao conciliacao) throws Exception{
        conciliacaoDAO.excluir(conciliacao);
    }

    public Conciliacao buscarPorId(Long id){
        Conciliacao conciliacao = null;
        conciliacao = conciliacaoDAO.buscarPorID(id);
        return conciliacao;
    }

    public List<Conciliacao> listar(){
        return conciliacaoDAO.listar();
    }

    /*public List<Conciliacao> buscarPorNome(String filtro){
        return conciliacaoDAO.buscarPorNome(filtro);
    }*/
    public List<Conciliacao> listarmes(String ano, String mes){
        return conciliacaoDAO.listamesano(ano,mes);
    }

    public List<Conciliacao> listaano(String ano){
        return conciliacaoDAO.listaano(ano);
    }

    public List<Conciliacao> concEstme(String ano, String mes, String conc_id){
        return conciliacaoDAO.concEstme(ano,mes,conc_id);
    }

    public List<Conciliacao> concEstto(String conc_id){
        return conciliacaoDAO.concEstto(conc_id);
    }

    public List<Conciliacao> concEstan(String ano, String conc_id){
        return conciliacaoDAO.concEstan(ano,conc_id);
    }

    public List<Conciliacao> prepEstto(String conc_id){
        return conciliacaoDAO.prepEstto(conc_id);
    }
    public List<Conciliacao> prepEstano(String ano, String prep_id){
        return conciliacaoDAO.prepEstano(ano,prep_id);
    }

    public List<Conciliacao> prepEstme(String ano, String mes, String prep_id){
        return conciliacaoDAO.concEstme(ano,mes,prep_id);
    }

    public List<Conciliacao> varaEstto(String vara_id){
        return conciliacaoDAO.varaEstto(vara_id);
    }

    public List<Conciliacao> varaEstano(String ano, String vara_id){
        return conciliacaoDAO.prepEstano(ano,vara_id);
    }

    public List<Conciliacao> varaEstme(String ano, String mes, String vara_id){
        return conciliacaoDAO.concEstme(ano,mes,vara_id);
    }
}
