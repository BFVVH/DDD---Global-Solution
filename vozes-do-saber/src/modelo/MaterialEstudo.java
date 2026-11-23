package modelo;

public class MaterialEstudo {
    private String titulo;
    private String etapaEnsino;
    private String descricao;
    private Instituicao instituicao;

    public MaterialEstudo(String titulo, String etapaEnsino, String descricao, Instituicao instituicao) {
        setTitulo(titulo);
        setEtapaEnsino(etapaEnsino);
        setDescricao(descricao);
        setInstituicao(instituicao);
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = textoObrigatorio(titulo, "Título"); }
    public String getEtapaEnsino() { return etapaEnsino; }
    public void setEtapaEnsino(String etapaEnsino) { this.etapaEnsino = textoObrigatorio(etapaEnsino, "Etapa de ensino"); }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = textoObrigatorio(descricao, "Descrição"); }
    public Instituicao getInstituicao() { return instituicao; }
    public void setInstituicao(Instituicao instituicao) {
        if (instituicao == null) throw new IllegalArgumentException("Instituição é obrigatória.");
        this.instituicao = instituicao;
    }

    public String resumo() {
        return titulo + " | " + etapaEnsino + " | " + instituicao.getNome() + " | " + descricao;
    }

    private static String textoObrigatorio(String valor, String campo) {
        if (valor == null || valor.isBlank()) throw new IllegalArgumentException(campo + " é obrigatório.");
        return valor.trim();
    }
}
