package br.jus.jfpb.cadastro.model;

import javax.persistence.*;

@Entity
public class Usuario extends AbstractEntity {
    @Id
    @SequenceGenerator(
            name = "sq_usuario",
            sequenceName = "sequence_usuario",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sq_usuario")
    private Long id;
    private String nome;
    private String senha;

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
