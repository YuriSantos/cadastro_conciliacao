package br.jus.jfpb.cadastro.service;

import br.jus.jfpb.cadastro.dao.ConselhoDAO;
import br.jus.jfpb.cadastro.model.Conselho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ConselhoService {
    @Autowired
    private ConselhoDAO conselhoDAO;

    public void inserir(Conselho conselho) throws Exception{
        conselhoDAO.inserir(conselho);
    }

    public void atualizar(Conselho conselho) throws Exception{
        conselhoDAO.atualizar(conselho);
    }

    public void excluir(Conselho conselho) throws Exception{
        conselhoDAO.excluir(conselho);
    }

    public Conselho buscarPorId(Long id){
        Conselho conselho = null;
        conselho = conselhoDAO.buscarPorID(id);
        return conselho;
    }

    public List<Conselho> listar(){
        return conselhoDAO.listar();
    }

    /*public List<Conciliacao> buscarPorNome(String filtro){
        return conciliacaoDAO.buscarPorNome(filtro);
    }*/
}
