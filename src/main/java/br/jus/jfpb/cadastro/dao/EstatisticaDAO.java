package br.jus.jfpb.cadastro.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class EstatisticaDAO {
    @PersistenceContext
    protected EntityManager manager;

    public List listar(){
        Query query = manager.createQuery("select count(*) from conciliacao group by resultado");
        System.out.println(query.getResultList());
        return query.getResultList();
    }

    public List listarmes(String ano, String mes){
        Query query = manager.createQuery("select count(*) From conciliacao where (extract('Year' From data) = "+ano+" and extract ('Month' From data)="+mes+")group by resultado");
        return query.getResultList();
    }
}
