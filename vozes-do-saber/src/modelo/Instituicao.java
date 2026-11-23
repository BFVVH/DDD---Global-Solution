package modelo;

import java.util.ArrayList;
import java.util.List;

public class Instituicao {
    private String nome;
    private String tipo;
    private String comunidade;
    private List<MaterialEstudo> materiais;

    public Instituicao(String nome, String tipo, String comunidade) {
        setNome(nome);
        setTipo(tipo);
        setComunidade(comunidade);
        this.materiais = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = textoObrigatorio(nome, "Nome"); }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = textoObrigatorio(tipo, "Tipo"); }
    public String getComunidade() { return comunidade; }
    public void setComunidade(String comunidade) { this.comunidade = textoObrigatorio(comunidade, "Comunidade"); }
    public List<MaterialEstudo> getMateriais() { return new ArrayList<>(materiais); }
    public void setMateriais(List<MaterialEstudo> materiais) {
        if (materiais == null || materiais.stream().anyMatch(m -> m == null || m.getInstituicao() != this)) {
            throw new IllegalArgumentException("A lista deve conter somente materiais desta instituição.");
        }
        this.materiais = new ArrayList<>(materiais);
    }

    public void publicarMaterial(MaterialEstudo material) {
        if (material == null || material.getInstituicao() != this) {
            throw new IllegalArgumentException("O material deve pertencer a esta instituição.");
        }
        if (!materiais.contains(material)) {
            materiais.add(material);
        }
    }

    private static String textoObrigatorio(String valor, String campo) {
        if (valor == null || valor.isBlank()) throw new IllegalArgumentException(campo + " é obrigatório.");
        return valor.trim();
    }
}
