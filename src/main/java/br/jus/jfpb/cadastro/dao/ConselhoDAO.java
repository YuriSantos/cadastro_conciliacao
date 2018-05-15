package br.jus.jfpb.cadastro.dao;

import br.jus.jfpb.cadastro.model.Conselho;
import org.springframework.stereotype.Repository;

@Repository
public class ConselhoDAO extends AbstractDAO<Conselho> {
    public ConselhoDAO(){super(Conselho.class);}
}
