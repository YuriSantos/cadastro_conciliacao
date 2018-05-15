package br.jus.jfpb.cadastro.dao;

import br.jus.jfpb.cadastro.model.Orgao;
import org.springframework.stereotype.Repository;

@Repository
public class OrgaoDAO extends AbstractDAO<Orgao>{
    public OrgaoDAO(){super(Orgao.class);}
}
