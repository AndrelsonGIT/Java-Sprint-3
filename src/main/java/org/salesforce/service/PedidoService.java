package org.salesforce.service;

import org.salesforce.dao.Dao;
import org.salesforce.model.PedidoAtualizacao;
import org.salesforce.model.Usuario;
import org.salesforce.model.pedido.Pedido;
import org.salesforce.model.pedido.StatusPedido;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class PedidoService {

    private DataRegional dataRegional;

    public PedidoService(DataRegional dataRegional) {
        this.dataRegional = dataRegional;
    }

    public void inserirPedido(Pedido pedido){
        String query  = String.format("INSERT INTO tb_pedido(id_usuario,data_pedido, status) VALUES (%s, TO_DATE('%s', 'DD/MM/YYYY HH24:MI:SS'), '%s')",
                pedido.getIdUsuario(),
                dataRegional.converterParaFormatoBrasileiro(pedido.getDataPedido()),
                pedido.getStatusPedido().toString());

         boolean success = Dao.insertUpdateDeleteCommand(query);

         if(success){
             System.out.println("Pedido inserido com sucesso");
         }
         else{
             System.out.println("Falha ao inserir pedido");
         }

    }

    public void atualizarUsuario(PedidoAtualizacao pedido){
        Optional<Pedido> optionalUsuario = acharPedido(pedido.getPedidoId());

        if(optionalUsuario.isEmpty()){
            System.out.println("Pedido não foi encontrado na base de dados");
            return;
        }else {

            String query = String.format("update tb_pedido set status='%s' where id_pedido = '%s' ", pedido.getStatusPedido().toString(),
                    pedido.getPedidoId());

            Dao.insertUpdateDeleteCommand(query);
        }

    }


    public void listarPedidos() {
        String query = "select * from tb_pedido";
        ResultSet resultSet = Dao.selectCommand(query);
        try {
            while (resultSet.next()) {
                System.out.println("----");
                System.out.println("Pedido id: " + resultSet.getString("id_pedido"));
                System.out.println("Data pedido : " + resultSet.getString("data_pedido"));
                System.out.println("Status Pedido : " + resultSet.getString("status"));
                System.out.println("Id do usuário do pedido"+ resultSet.getString("id_usuario"));

            }
        } catch (SQLException e) {
            System.out.println("Falha ao listar a lista de pedidos");
            System.out.println(e);
        }
    }

    public void apagarPedido(int pedidoId){
        Optional<Pedido> optionalPedido  = acharPedido(pedidoId);

        if(optionalPedido.isEmpty()){
            System.out.println("Pedido não foi encontrado");
        }
        else{
            String query = String.format("delete from tb_pedido where id_usuario = '%s'", pedidoId);

            boolean sucesso = Dao.insertUpdateDeleteCommand(query);

            if(sucesso){
                System.out.println("Pedido removido com sucesso");
            }
            else{
                System.out.printf("Falha ao remover pedido");
            }
        }

    }

    public Optional<Pedido> acharPedido(int idPedido){
        String query = String.format("select * from tb_pedido where id_pedido=%d", idPedido);
        ResultSet resultSet=  Dao.selectCommand(query);

        try {
            if(resultSet.next()) {
                int idPedidoTabela = resultSet.getInt("id_pedido");
                int idPedidoUsuario = resultSet.getInt("id_usuario");
                LocalDateTime dataPedido = resultSet.getTimestamp("data_pedido").toLocalDateTime();
                String statusPedido = resultSet.getString("sobrenome");

                Pedido pedido = new Pedido(idPedidoTabela, idPedidoUsuario, dataPedido, StatusPedido.valueOf(statusPedido) );

                return Optional.of(pedido);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar usuário");
            System.out.println(e);
        }
        return Optional.empty();
    }
}
