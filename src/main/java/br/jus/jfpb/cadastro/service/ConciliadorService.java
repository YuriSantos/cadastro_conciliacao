package br.jus.jfpb.cadastro.service;

import br.jus.jfpb.cadastro.dao.ConciliadorDAO;
import br.jus.jfpb.cadastro.model.Conciliador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ConciliadorService {
    @Autowired
    private ConciliadorDAO conciliadorDAO;

    public void inserir(Conciliador conciliador) throws Exception{
        conciliadorDAO.inserir(conciliador);
    }

    public void atualizar(Conciliador conciliador) throws Exception{
        conciliadorDAO.atualizar(conciliador);
    }

    public void excluir(Conciliador conciliador) throws Exception{
        conciliadorDAO.excluir(conciliador);
    }

    public Conciliador buscarPorId(Long id){
        Conciliador conciliador = null;
        conciliador = conciliadorDAO.buscarPorID(id);
        return conciliador;
    }

    public List<Conciliador> listar(){
        return conciliadorDAO.listar();
    }

//    public List<Conciliador> buscarPorNome(String filtro){
//        return conciliadorDAO.buscarPorNome(filtro);
//    }
}

