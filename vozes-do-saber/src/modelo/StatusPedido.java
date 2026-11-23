package modelo;

public enum StatusPedido {
    RECEBIDO("Recebido"),
    EM_ANALISE("Em análise"),
    RESPONDIDO("Respondido"),
    CONCLUIDO("Concluído");

    private final String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
