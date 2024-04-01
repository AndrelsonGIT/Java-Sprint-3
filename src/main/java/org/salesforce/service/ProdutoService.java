package org.salesforce.service;


import org.salesforce.dao.Dao;
import org.salesforce.model.Produto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ProdutoService {

    public ProdutoService() {
    }

    public void inserirProduto(Produto produto){
        String query = String.format("INSERT INTO tb_produto (nome, descricao, preco, categoria) VALUES ('%s', '%s', %.2f, '%s')",
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getCategoria());

        boolean sucesso = Dao.insertUpdateDeleteCommand(query);

        if(sucesso){
            System.out.println("Produto inserido com sucesso");
        }
        else{
            System.out.printf("Falha ao cadastrar Produto");
        }
    }

    public void atualizarProduto(int idProduto, String nome, String descricao, double preco, String categoria){
        String query = String.format("UPDATE tb_produto SET nome='%s', descricao='%s', preco=%.2f, categoria='%s' WHERE id_produto=%d",
                nome,
                descricao,
                preco,
                categoria,
                idProduto);

        boolean sucesso = Dao.insertUpdateDeleteCommand(query);

        if(sucesso){
            System.out.println("Produto atualizado com sucesso");
        }
        else{
            System.out.printf("Falha ao atualizar Produto");
        }
    }

    public void apagarProduto(int idProduto){
        String query = String.format("DELETE FROM tb_produto WHERE id_produto = %d", idProduto);

        boolean sucesso = Dao.insertUpdateDeleteCommand(query);

        if(sucesso){
            System.out.println("Produto removido com sucesso");
        }
        else{
            System.out.printf("Falha ao remover Produto");
        }
    }

    public void listarProdutos() {
        String query = "SELECT * FROM tb_produto";
        ResultSet resultSet = Dao.selectCommand(query);
        try {
            while (resultSet.next()) {
                System.out.println("----");
                System.out.println("ID Produto: " + resultSet.getInt("id_produto"));
                System.out.println("Nome: " + resultSet.getString("nome"));
                System.out.println("Descrição: " + resultSet.getString("descricao"));
                System.out.println("Preço: " + resultSet.getDouble("preco"));
                System.out.println("Categoria: " + resultSet.getString("categoria"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Produto> acharProduto(int idProduto){
        String query = String.format("SELECT * FROM tb_produto WHERE id_produto=%d", idProduto);
        ResultSet resultSet = Dao.selectCommand(query);

        try {
            if(resultSet.next()) {
                int id = resultSet.getInt("id_produto");
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                double preco = resultSet.getDouble("preco");
                String categoria = resultSet.getString("categoria");

                Produto produto = new Produto(id, nome, descricao, preco, categoria);

                return Optional.of(produto);
            }else{
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}