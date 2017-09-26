package fiap.sqlite.model;

/**
 * Created by logonpf on 26/09/2017.
 */

public class Produto {

    private int codigo;
    private String nome;
    private double valor;
    private String descricao;

    public Produto(int codigo, String nome, double valor, String descricao) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
    }

    public Produto() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
