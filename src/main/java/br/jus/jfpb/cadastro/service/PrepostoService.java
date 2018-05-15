package br.jus.jfpb.cadastro.service;

import br.jus.jfpb.cadastro.dao.PrepostoDAO;
import br.jus.jfpb.cadastro.model.Preposto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PrepostoService {
    @Autowired
    private PrepostoDAO prepostoDAO;

    public void inserir(Preposto preposto){
        prepostoDAO.inserir(preposto);
    }

    public void atualizar(Preposto preposto){
        prepostoDAO.atualizar(preposto);
    }

    public void excluir(Preposto preposto){
        prepostoDAO.excluir(preposto);
    }

    public Preposto buscarPorId(Long id){
        Preposto preposto = null;
        preposto = prepostoDAO.buscarPorID(id);
        return preposto;
    }

    public List<Preposto> listar(){
        return prepostoDAO.listar();
    }

    /*public List<Conciliacao> buscarPorNome(String filtro){
        return conciliacaoDAO.buscarPorNome(filtro);
    }*/

}

