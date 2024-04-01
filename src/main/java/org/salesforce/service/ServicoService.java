package org.salesforce.service;

import org.salesforce.dao.Dao;
import org.salesforce.model.Servico;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServicoService {

    public ServicoService() {
    }

    public void inserirServico(Servico servico){
        String query = String.format("INSERT INTO tb_servicos (nome, descricao, preco, duracao, id_produto) VALUES ('%s', '%s', %.2f, %d, %d)",
                servico.getNome(),
                servico.getDescricao(),
                servico.getPreco(),
                servico.getDuracao(),
                servico.getIdProduto());

        boolean sucesso = Dao.insertUpdateDeleteCommand(query);

        if(sucesso){
            System.out.println("Serviço inserido com sucesso");
        }
        else{
            System.out.printf("Falha ao cadastrar Serviço");
        }
    }

    public void atualizarServico(int idServico, String nome, String descricao, double preco, Integer duracao, int idProduto){
        String query = String.format("UPDATE tb_servicos SET nome='%s', descricao='%s', preco=%.2f, duracao=%d, id_produto=%d WHERE id_servico=%d",
                nome,
                descricao,
                preco,
                duracao,
                idProduto,
                idServico);

        boolean sucesso = Dao.insertUpdateDeleteCommand(query);

        if(sucesso){
            System.out.println("Serviço atualizado com sucesso");
        }
        else{
            System.out.printf("Falha ao atualizar Serviço");
        }
    }

    public void apagarServico(int idServico){
        String query = String.format("DELETE FROM tb_servicos WHERE id_servico = %d", idServico);

        boolean sucesso = Dao.insertUpdateDeleteCommand(query);

        if(sucesso){
            System.out.println("Serviço removido com sucesso");
        }
        else{
            System.out.printf("Falha ao remover Serviço");
        }
    }

    public void listarServicos() {
        String query = "SELECT * FROM tb_servicos";
        ResultSet resultSet = Dao.selectCommand(query);
        try {
            List<Servico> servicos = new ArrayList<>();
            while (resultSet.next()) {
                int idServico = resultSet.getInt("id_servico");
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                double preco = resultSet.getDouble("preco");
                Integer duracao = resultSet.getInt("duracao");
                int idProduto = resultSet.getInt("id_produto");
                servicos.add(new Servico(idServico, nome, descricao, preco, duracao, idProduto));
            }
            servicos.forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Servico> acharServico(int idServico){
        String query = String.format("SELECT * FROM tb_servicos WHERE id_servico=%d", idServico);
        ResultSet resultSet = Dao.selectCommand(query);

        try {
            if(resultSet.next()) {
                int id = resultSet.getInt("id_servico");
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                double preco = resultSet.getDouble("preco");
                Integer duracao = resultSet.getInt("duracao");
                int idProduto = resultSet.getInt("id_produto");

                Servico servico = new Servico(id, nome, descricao, preco, duracao, idProduto);

                return Optional.of(servico);
            }else{
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}