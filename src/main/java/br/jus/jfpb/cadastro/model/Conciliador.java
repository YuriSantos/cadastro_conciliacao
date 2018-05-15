package br.jus.jfpb.cadastro.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@SequenceGenerator(sequenceName="conciliador_seq", name="conciliador_id", allocationSize=1)
public class Conciliador extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "conciliador_id")
    private Long id;
    @Temporal(TemporalType.DATE)
    Calendar data_cadastro = Calendar.getInstance();

    @Column
    private String nome;

    private String email;
    private String telefone;
    private String endereco;
    private Date data_nasc;
    private String rg;
    private String cpf;
    private String salvo_por;

    public String getSalvo_por() {
        return salvo_por;
    }

    public void setSalvo_por(String salvo_por) {
        this.salvo_por = salvo_por;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
