package modelo;

public class PedidoApoio {
    private String protocolo;
    private TipoApoio tipo;
    private String descricao;
    private StatusPedido status;
    private String resposta;

    public PedidoApoio(String protocolo, TipoApoio tipo, String descricao) {
        setProtocolo(protocolo);
        setTipo(tipo);
        setDescricao(descricao);
        this.status = StatusPedido.RECEBIDO;
        this.resposta = "";
    }

    public String getProtocolo() { return protocolo; }
    public void setProtocolo(String protocolo) {
        if (protocolo == null || protocolo.isBlank()) throw new IllegalArgumentException("Protocolo é obrigatório.");
        this.protocolo = protocolo.trim().toUpperCase();
    }
    public TipoApoio getTipo() { return tipo; }
    public void setTipo(TipoApoio tipo) {
        if (tipo == null) throw new IllegalArgumentException("Tipo de apoio é obrigatório.");
        this.tipo = tipo;
    }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) {
        if (descricao == null || descricao.isBlank() || descricao.length() > 500) {
            throw new IllegalArgumentException("A descrição deve ter de 1 a 500 caracteres.");
        }
        this.descricao = descricao.trim();
    }
    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) {
        if (status == null) throw new IllegalArgumentException("Status é obrigatório.");
        this.status = status;
    }
    public String getResposta() { return resposta; }
    public void setResposta(String resposta) {
        if (resposta == null || resposta.length() > 500) throw new IllegalArgumentException("A resposta deve ter até 500 caracteres.");
        this.resposta = resposta.trim();
    }

    // pede uma resposta quando o pedido foi respondido ou concluido
    public void atualizar(StatusPedido novoStatus, String novaResposta) {
        if (novoStatus == null || novoStatus == StatusPedido.RECEBIDO) {
            throw new IllegalArgumentException("Escolha um novo status válido.");
        }
        if ((novoStatus == StatusPedido.RESPONDIDO || novoStatus == StatusPedido.CONCLUIDO)
                && (novaResposta == null || novaResposta.isBlank())) {
            throw new IllegalArgumentException("Pedidos respondidos ou concluídos precisam de resposta.");
        }
        setResposta(novaResposta == null ? "" : novaResposta);
        setStatus(novoStatus);
    }

    public String resumo() {
        return protocolo + " | " + tipo.getDescricao() + " | " + status.getDescricao()
                + " | " + descricao + " | Resposta: " + (resposta.isBlank() ? "Aguardando resposta." : resposta);
    }
}
