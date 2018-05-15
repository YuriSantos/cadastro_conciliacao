package br.jus.jfpb.cadastro.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@SequenceGenerator(sequenceName="conciliacao_seq", name="conciliacao_id", allocationSize=1)
public class Conciliacao extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "conciliacao_id")
    private Long id;
    private String numeroprocesso;
    private Date data;
    @ManyToOne
    private Vara vara;
    @ManyToOne
    private Conciliador conciliador, co_conciliador, observador_1,observador_2;
    private String Observador_NC;
    private String Observacao;
    @ManyToOne
    private Orgao orgao;
    @ManyToOne
    private Conselho conselho;
    @ManyToOne
    private Preposto preposto;
    private int resultado;
    @Transient
    private String acordo;
    private float valor;

    @OneToMany(mappedBy = "conciliador")
    private List<Conciliacao> conciliacoes;

    @Transient
    private String valor_string;

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

    public String getNumeroprocesso() {
        return numeroprocesso;
    }

    public void setNumeroprocesso(String numeroprocesso) {
        this.numeroprocesso = numeroprocesso;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Vara getVara() {
        return vara;
    }

    public void setVara(Vara vara) {
        this.vara = vara;
    }

    public Conciliador getConciliador() {
        return conciliador;
    }

    public void setConciliador(Conciliador conciliador) {
        this.conciliador = conciliador;
    }

    public Conciliador getCo_conciliador() {
        return co_conciliador;
    }

    public void setCo_conciliador(Conciliador co_conciliador) {
        this.co_conciliador = co_conciliador;
    }

    public Conciliador getObservador_1() {
        return observador_1;
    }

    public void setObservador_1(Conciliador observador_1) {
        this.observador_1 = observador_1;
    }

    public Conciliador getObservador_2() {
        return observador_2;
    }

    public void setObservador_2(Conciliador observador_2) {
        this.observador_2 = observador_2;
    }

    public Orgao getOrgao() {
        return orgao;
    }

    public void setOrgao(Orgao orgao) {
        this.orgao = orgao;
    }

    public Conselho getConselho() {
        return conselho;
    }

    public void setConselho(Conselho conselho) {
        this.conselho = conselho;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getObservador_NC() {
        return Observador_NC;
    }

    public void setObservador_NC(String observador_NC) {
        Observador_NC = observador_NC;
    }

    public String getObservacao() {
        return Observacao;
    }

    public void setObservacao(String observacao) {
        Observacao = observacao;
    }

    public Preposto getPreposto() {
        return preposto;
    }

    public void setPreposto(Preposto preposto) {
        this.preposto = preposto;
    }

    public String getSalvo_por() {
        return salvo_por;
    }

    public void setSalvo_por(String salvo_por) {
        this.salvo_por = salvo_por;
    }

    public String getValor_string() {
        return valor_string;
    }

    public void setValor_string(String valor_string) {
        this.valor_string = valor_string;
    }

    public String getAcordo(){
        List<String> list = new ArrayList<String>() {{
            add("Não informado");
            add("Sem Acordo");
            add("Com Acordo");
            add("Ausente");
            add("Redesignação");
            add("Retirada de Pauta/Cancelada");
        }};

        return list.get(this.resultado);
    }
}
