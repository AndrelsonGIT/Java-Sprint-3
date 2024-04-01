package org.salesforce.service;

import org.salesforce.dao.Dao;
import org.salesforce.model.PedidoProduto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoProdutoService {

    public PedidoProdutoService() {
    }

    public void inserirPedidoProduto(PedidoProduto pedidoProduto){
        String query = String.format("INSERT INTO tb_pedido_produto (id_pedido, id_produto) VALUES (%d, %d)",
                pedidoProduto.getIdPedido(),
                pedidoProduto.getIdProduto());

        boolean sucesso = Dao.insertUpdateDeleteCommand(query);

        if(sucesso){
            System.out.println("Associação Pedido-Produto inserida com sucesso");
        }
        else{
            System.out.printf("Falha ao inserir Associação Pedido-Produto");
        }
    }

    public void apagarPedidoProduto(int idPedido, int idProduto){
        String query = String.format("DELETE FROM tb_pedido_produto WHERE id_pedido = %d AND id_produto = %d", idPedido, idProduto);

        boolean sucesso = Dao.insertUpdateDeleteCommand(query);

        if(sucesso){
            System.out.println("Associação Pedido-Produto removida com sucesso");
        }
        else{
            System.out.printf("Falha ao remover Associação Pedido-Produto");
        }
    }

    public void listarPedidoProdutos() {
        String query = "SELECT * FROM tb_pedido_produto";
        ResultSet resultSet = Dao.selectCommand(query);
        try {
            List<PedidoProduto> pedidoProdutos = new ArrayList<>();
            while (resultSet.next()) {
                int idPedido = resultSet.getInt("id_pedido");
                int idProduto = resultSet.getInt("id_produto");
                pedidoProdutos.add(new PedidoProduto(idPedido, idProduto));
            }
            pedidoProdutos.forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
