package br.jus.jfpb.cadastro.dao;

import br.jus.jfpb.cadastro.model.Vara;
import org.springframework.stereotype.Repository;

@Repository
public class VaraDAO extends AbstractDAO<Vara>{
    public VaraDAO(){super(Vara.class);}
}
