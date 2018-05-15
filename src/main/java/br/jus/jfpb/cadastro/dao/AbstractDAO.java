package br.jus.jfpb.cadastro.dao;

import br.jus.jfpb.cadastro.model.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class AbstractDAO<T extends AbstractEntity> {

    @PersistenceContext
    protected EntityManager manager;

    private Class<T> classe;

    public AbstractDAO(Class<T> classe){
        this.classe = classe;
    }

    public void inserir(T entidade) {manager.persist(entidade);}

    public void atualizar(T entidade) {manager.merge(entidade);}

    public void excluir(T entidade){
        entidade = manager.find(classe, entidade.getId());
        manager.remove(entidade);
    }

    public T buscarPorID(Long id){
        return manager.find(classe, id);
    }

    public List<T> listar(){
        Query query = manager.createQuery("select e from "+classe.getSimpleName()+" e");
        return query.getResultList();
    }

    public List<T> listamesano(String ano, String mes){
        Query query = manager.createQuery("select e From "+classe.getSimpleName()+" e where (extract('Year' From data) = "+ano+" and extract ('Month' From data)="+mes+")");
        return query.getResultList();
    }

    public List<T> listaano(String ano){
        Query query = manager.createQuery("select e From "+classe.getSimpleName()+" e where (extract('Year' From data) = "+ano+")");
        return query.getResultList();
    }

    public List<T> concEstme(String ano, String mes, String conc_id){
        Query query = manager.createQuery("select e From "+classe.getSimpleName()+" e where (extract('Year' From data) = "+ano+" and extract ('Month' From data)="+mes+") AND conciliador_id = "+ conc_id+"");
        return query.getResultList();
    }

    public List<T> concEstto(String conc_id){
        Query query = manager.createQuery("select e From "+classe.getSimpleName()+" e where conciliador_id = "+ conc_id+"");
        return query.getResultList();
    }

    public List<T> concEstan(String ano, String conc_id){
        Query query = manager.createQuery("select e From "+classe.getSimpleName()+" e where (extract('Year' From data) = "+ano+") AND conciliador_id = "+ conc_id+"");
        return query.getResultList();
    }

    public List<T> varaEstto(String vara_id){
        Query query = manager.createQuery("select e From "+classe.getSimpleName()+" e where vara_id = "+ vara_id+"");
        return query.getResultList();
    }

    public List<T> varaEstme(String ano, String mes, String vara_id){
        Query query = manager.createQuery("select e From "+classe.getSimpleName()+" e where (extract('Year' From data) = "+ano+" and extract ('Month' From data)="+mes+") AND vara_id = "+ vara_id+")");
        return query.getResultList();
    }

    public List<T> varaEstano(String ano, String vara_id){
        Query query = manager.createQuery("select e From "+classe.getSimpleName()+" e where (extract('Year' From data) = "+ano+") AND vara_id = "+ vara_id+")");
        return query.getResultList();
    }

    public List<T> prepEstto(String prep_id){
        Query query = manager.createQuery("select e From "+classe.getSimpleName()+" e where preposto_id = "+ prep_id+"");
        return query.getResultList();
    }

    public List<T> prepEstme(String ano, String mes, String preposto_id){
        Query query = manager.createQuery("select e From "+classe.getSimpleName()+" e where (extract('Year' From data) = "+ano+" and extract ('Month' From data)="+mes+") AND preposto_id = "+ preposto_id+")");
        return query.getResultList();
    }

    public List<T> prepEstano(String ano, String preposto_id){
        Query query = manager.createQuery("select e From "+classe.getSimpleName()+" e where (extract('Year' From data) = "+ano+") AND preposto_id = "+ preposto_id+"");
        return query.getResultList();
    }

}
