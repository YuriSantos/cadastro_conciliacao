package br.jus.jfpb.cadastro.model;

public abstract class AbstractEntity {

    public abstract Long getId();
    public abstract void setId(Long id);

    @Override
    public boolean equals(Object obj){
        AbstractEntity entidade = (AbstractEntity) obj;
        if(this.temIdValido() && entidade.temIdValido() && this.getId().equals(entidade.getId())){
            return true;
        }
        return false;
    }

    public boolean temIdValido(){
        return getId() != null && getId() != 0;
    }
}
