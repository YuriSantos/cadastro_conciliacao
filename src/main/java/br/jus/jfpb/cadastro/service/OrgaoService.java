package br.jus.jfpb.cadastro.service;

import br.jus.jfpb.cadastro.dao.OrgaoDAO;
import br.jus.jfpb.cadastro.model.Orgao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrgaoService {
    @Autowired
    private OrgaoDAO orgaoDAO;

    public void inserir(Orgao orgao) throws Exception{
        orgaoDAO.inserir(orgao);
    }

    public void atualizar(Orgao orgao) throws Exception{
        orgaoDAO.atualizar(orgao);
    }

    public void excluir(Orgao orgao) throws Exception{
        orgaoDAO.excluir(orgao);
    }

    public Orgao buscarPorId(Long id){
        Orgao orgao = null;
        orgao = orgaoDAO.buscarPorID(id);
        return orgao;
    }

    public List<Orgao> listar(){
        return orgaoDAO.listar();
    }

    /*public List<Conciliacao> buscarPorNome(String filtro){
        return conciliacaoDAO.buscarPorNome(filtro);
    }*/
}
