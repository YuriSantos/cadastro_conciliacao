package br.jus.jfpb.cadastro.model;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@SequenceGenerator(sequenceName="preposto_seq", name="preposto_id", allocationSize=1)
public class Preposto extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "preposto_id")
    private Long id;
    @ManyToOne
    private Orgao orgao;
    private String nome;
    private String salvo_por;

    Calendar data_cadastro = Calendar.getInstance();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Orgao getOrgao() {
        return orgao;
    }

    public void setOrgao(Orgao orgao) {
        this.orgao = orgao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSalvo_por() {
        return salvo_por;
    }

    public void setSalvo_por(String salvo_por) {
        this.salvo_por = salvo_por;
    }
}
