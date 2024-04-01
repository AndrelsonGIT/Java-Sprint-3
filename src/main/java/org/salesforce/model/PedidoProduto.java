package org.salesforce.model;

public class PedidoProduto {

    private int idPedido;
    private int idProduto;

    public PedidoProduto() {
    }

    public PedidoProduto(int idPedido, int idProduto) {
        this.idPedido = idPedido;
        this.idProduto = idProduto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }
}