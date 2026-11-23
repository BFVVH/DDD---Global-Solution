package modelo;

public class EquipeApoio {
    private String nome;
    private Instituicao instituicao;

    public EquipeApoio(String nome, Instituicao instituicao) {
        setNome(nome);
        setInstituicao(instituicao);
    }

    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome da equipe é obrigatório.");
        this.nome = nome.trim();
    }
    public Instituicao getInstituicao() { return instituicao; }
    public void setInstituicao(Instituicao instituicao) {
        if (instituicao == null) throw new IllegalArgumentException("Instituição é obrigatória.");
        this.instituicao = instituicao;
    }

    // encontra o pedido antes de mudar o andamento
    public void atualizarPedido(CentralPedidos central, String protocolo, StatusPedido status, String resposta) {
        if (central == null) throw new IllegalArgumentException("Central de pedidos é obrigatória.");
        PedidoApoio pedido = central.consultarPedido(protocolo);
        if (pedido == null) throw new IllegalArgumentException("Pedido não encontrado.");
        pedido.atualizar(status, resposta);
    }
}
