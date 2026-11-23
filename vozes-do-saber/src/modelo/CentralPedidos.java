package modelo;

import java.util.ArrayList;
import java.util.List;

public class CentralPedidos {
    private List<PedidoApoio> pedidos;
    private int proximoNumero;

    public CentralPedidos() {
        this.pedidos = new ArrayList<>();
        this.proximoNumero = 1;
    }

    public List<PedidoApoio> getPedidos() { return new ArrayList<>(pedidos); }
    public void setPedidos(List<PedidoApoio> pedidos) {
        if (pedidos == null || pedidos.contains(null)) throw new IllegalArgumentException("Lista de pedidos inválida.");
        this.pedidos = new ArrayList<>(pedidos);
    }
    public int getProximoNumero() { return proximoNumero; }
    public void setProximoNumero(int proximoNumero) {
        if (proximoNumero < 1) throw new IllegalArgumentException("Número deve ser positivo.");
        this.proximoNumero = proximoNumero;
    }

    // cria o pedido com um protocolo novo
    public PedidoApoio registrarPedido(TipoApoio tipo, String descricao) {
        PedidoApoio pedido = new PedidoApoio(String.format("VDS-%04d", proximoNumero), tipo, descricao);
        pedidos.add(pedido);
        proximoNumero++;
        return pedido;
    }

    // procura o pedido pelo protocolo
    public PedidoApoio consultarPedido(String protocolo) {
        if (protocolo == null) return null;
        for (PedidoApoio pedido : pedidos) {
            if (pedido.getProtocolo().equalsIgnoreCase(protocolo.trim())) return pedido;
        }
        return null;
    }
}
