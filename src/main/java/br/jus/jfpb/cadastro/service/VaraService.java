package br.jus.jfpb.cadastro.service;

import br.jus.jfpb.cadastro.dao.VaraDAO;
import br.jus.jfpb.cadastro.model.Vara;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class VaraService {
    @Autowired
    private VaraDAO varaDAO;

    public void inserir(Vara vara){
        varaDAO.inserir(vara);
    }

    public void atualizar(Vara vara){
        varaDAO.atualizar(vara);
    }

    public void excluir(Vara vara){
        varaDAO.excluir(vara);
    }

    public Vara buscarPorId(Long id){
        Vara vara = null;
        vara = varaDAO.buscarPorID(id);
        return vara;
    }

    public List<Vara> listar(){
        return varaDAO.listar();
    }

    /*public List<Conciliacao> buscarPorNome(String filtro){
        return conciliacaoDAO.buscarPorNome(filtro);
    }*/

}
