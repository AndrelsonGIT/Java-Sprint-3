package org.salesforce.service;


import org.salesforce.dao.Dao;
import org.salesforce.model.SuporteTecnico;
import org.salesforce.model.SuporteTecnicoAtualizacao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SuporteTecnicoService {

    public SuporteTecnicoService() {
    }

    public void inserirSuporteTecnico(SuporteTecnico suporteTecnico){
        String query = String.format("INSERT INTO tb_suporte_tecnico (titulo, status, descricao, prioridade, id_pedido) VALUES ('%s', '%s', '%s', '%s', %d)",
                suporteTecnico.getTitulo(),
                suporteTecnico.getStatus(),
                suporteTecnico.getDescricao(),
                suporteTecnico.getPrioridade(),
                suporteTecnico.getIdPedido());

        boolean sucesso = Dao.insertUpdateDeleteCommand(query);

        if(sucesso){
            System.out.println("Suporte Técnico inserido com sucesso");
        }
        else{
            System.out.printf("Falha ao cadastrar Suporte Técnico");
        }
    }

    public void atualizarSuporteTecnico(SuporteTecnicoAtualizacao suporteTecnico){
        String query = String.format("UPDATE tb_suporte_tecnico SET status='%s', prioridade='%s' WHERE id_protocolo=%d",
                suporteTecnico.getStatusSuporteTecnico().toString(),
                suporteTecnico.getPrioridadeSuporteTecnico().toString());

        boolean sucesso = Dao.insertUpdateDeleteCommand(query);

        if(sucesso){
            System.out.println("Suporte Técnico atualizado com sucesso");
        }
        else{
            System.out.printf("Falha ao atualizar Suporte Técnico");
        }
    }

    public void apagarSuporteTecnico(int idProtocolo){
        String query = String.format("DELETE FROM tb_suporte_tecnico WHERE id_protocolo = %d", idProtocolo);

        boolean sucesso = Dao.insertUpdateDeleteCommand(query);

        if(sucesso){
            System.out.println("Suporte Técnico removido com sucesso");
        }
        else{
            System.out.printf("Falha ao remover Suporte Técnico");
        }
    }

    public void listarSuporteTecnico() {
        String query = "SELECT * FROM tb_suporte_tecnico";
        ResultSet resultSet = Dao.selectCommand(query);
        try {
            while (resultSet.next()) {
                System.out.println("----");
                System.out.println("ID Protocolo: " + resultSet.getInt("id_protocolo"));
                System.out.println("Título: " + resultSet.getString("titulo"));
                System.out.println("Status: " + resultSet.getString("status"));
                System.out.println("Descrição: " + resultSet.getString("descricao"));
                System.out.println("Prioridade: " + resultSet.getString("prioridade"));
                System.out.println("ID Pedido: " + resultSet.getInt("id_pedido"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<SuporteTecnico> acharSuporteTecnico(int idProtocolo){
        String query = String.format("SELECT * FROM tb_suporte_tecnico WHERE id_protocolo=%d", idProtocolo);
        ResultSet resultSet = Dao.selectCommand(query);

        try {
            if(resultSet.next()) {
                int idProtocoloTabela = resultSet.getInt("id_protocolo");
                String titulo = resultSet.getString("titulo");
                String status = resultSet.getString("status");
                String descricao = resultSet.getString("descricao");
                String prioridade = resultSet.getString("prioridade");
                int idPedido = resultSet.getInt("id_pedido");

                SuporteTecnico suporteTecnico = new SuporteTecnico(idProtocoloTabela, titulo, status, descricao, prioridade, idPedido);

                return Optional.of(suporteTecnico);
            }else{
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
