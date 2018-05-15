package br.jus.jfpb.cadastro.dao;

import br.jus.jfpb.cadastro.model.Conciliacao;
import org.springframework.stereotype.Repository;

@Repository
public class ConciliacaoDAO extends AbstractDAO<Conciliacao>{
    public ConciliacaoDAO(){super(Conciliacao.class);}
}
