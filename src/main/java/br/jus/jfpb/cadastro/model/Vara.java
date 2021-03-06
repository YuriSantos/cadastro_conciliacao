package br.jus.jfpb.cadastro.model;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@SequenceGenerator(sequenceName="vara_seq", name="vara_id", allocationSize=1)
public class Vara extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "vara_id")
    private Long id;
    private String nome;
    private String salvo_por;

    Calendar data_cadastro = Calendar.getInstance();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
