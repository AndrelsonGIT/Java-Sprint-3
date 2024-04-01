package org.salesforce.model;

import org.salesforce.model.pedido.StatusPedido;

public class PedidoAtualizacao {

    private int pedidoId;

    private StatusPedido statusPedido;

    public PedidoAtualizacao(int pedidoId, StatusPedido statusPedido) {
        this.pedidoId = pedidoId;
        this.statusPedido = statusPedido;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }
}
