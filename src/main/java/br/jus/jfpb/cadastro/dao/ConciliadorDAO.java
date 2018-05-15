package br.jus.jfpb.cadastro.dao;

import br.jus.jfpb.cadastro.model.Conciliador;
import org.springframework.stereotype.Repository;

@Repository
public class ConciliadorDAO extends AbstractDAO<Conciliador>{
    public ConciliadorDAO(){
        super(Conciliador.class);
    }
}
