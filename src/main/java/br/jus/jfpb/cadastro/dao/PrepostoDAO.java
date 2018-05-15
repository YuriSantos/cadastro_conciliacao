package br.jus.jfpb.cadastro.dao;

import br.jus.jfpb.cadastro.model.Preposto;
import org.springframework.stereotype.Repository;

@Repository
public class PrepostoDAO extends AbstractDAO<Preposto>{
    public PrepostoDAO(){super(Preposto.class);}
}
