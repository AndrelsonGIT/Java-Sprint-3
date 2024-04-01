package org.salesforce.service;

import org.salesforce.connection.OracleDBConnection;
import org.salesforce.dao.Dao;
import org.salesforce.model.Usuario;
import org.salesforce.model.UsuarioAtualizacao;

import javax.swing.text.html.Option;
import java.sql.*;
import java.util.Optional;
import java.util.OptionalInt;

public class UsuarioService {

    public UsuarioService() {
    }

    public void inserirUsuario(Usuario usuario){


        String query = String.format("INSERT INTO tb_usuario (nome, sobrenome, email, senha) VALUES ('%s', '%s', '%s', '%s')",
                usuario.getNome(),
                usuario.getSobrenome(),
                usuario.getEmail(),
                usuario.getSenha());

        boolean sucesso = Dao.insertUpdateDeleteCommand(query);

        if(sucesso){
            System.out.println("Usuário inserido com sucesso");
        }
        else{
            System.out.printf("Falha ao cadastrar usuário");
        }

    }

    public void atualizarUsuario(UsuarioAtualizacao usuario){

        Optional<Usuario> optionalUsuario = acharUsuario(usuario.getId());

        if(optionalUsuario.isEmpty()){
            System.out.println("Usuario não foi encontrado");
            return;
        }else{
            String query = String.format("update tb_usuario set email='%s', senha='%s' where id_usuario = %s ",usuario.getEmail(),
                    usuario.getPassword(),
                    usuario.getId());

            boolean sucesso = Dao.insertUpdateDeleteCommand(query);

            if(sucesso){
                System.out.println("Usuário atualizado com sucesso");
            }
            else{
                System.out.printf("Falha ao atualizar usuário");
            }
        }


    }

    public void apagarUsuario(int usuarioId){
       Optional<Usuario> optionalUsuario  = acharUsuario(usuarioId);

       if(optionalUsuario.isEmpty()){
           System.out.println("Usuario não foi encontrado");
       }
       else{
           String query = String.format("delete from tb_usuario where id_usuario = %s", usuarioId);

           boolean sucesso = Dao.insertUpdateDeleteCommand(query);

           if(sucesso){
               System.out.println("Usuário removido com sucesso");
           }
           else{
               System.out.printf("Falha ao remover usuário");
           }
       }

    }

    public void listarUsuarios() {
        String query = "select * from tb_usuario";
        ResultSet resultSet = Dao.selectCommand(query);
        try {
            while (resultSet.next()) {

                System.out.println("----");
                System.out.println("Usuario id: " + resultSet.getString("id_usuario"));
                System.out.println("Usuario nome: " + resultSet.getString("nome"));
                System.out.println("Usuario email: " + resultSet.getString("email"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        public Optional<Usuario> acharUsuario(int usuarioId){
        String query = String.format("select * from tb_usuario where id_usuario=%d", usuarioId);
        ResultSet resultSet=  Dao.selectCommand(query);

        try {
            if(resultSet.next()) {
                int idUsuario = resultSet.getInt("id_usuario");
                String nomeUsuario = resultSet.getString("nome");
                String sobrenomeUsuario = resultSet.getString("sobrenome");
                String emailUsuario = resultSet.getString("email");
                String senhaUsuario = resultSet.getString("senha");

                Usuario usuario = new Usuario(idUsuario, nomeUsuario, sobrenomeUsuario, emailUsuario, senhaUsuario);

                return Optional.of(usuario);
            }else{
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Usuario> acharUsuario(String emailUsuario){
        String query = String.format("select * from tb_usuario where email ='%s'", emailUsuario);
        ResultSet resultSet =  Dao.selectCommand(query);
        Optional<Usuario> optionalUsuario = null;
        try {
            if(resultSet.next()){
                int id = resultSet.getInt("id_usuario");
                String nome = resultSet.getString("nome");
                String sobrenome = resultSet.getString("sobrenome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");

                optionalUsuario =  Optional.of(new Usuario(id, nome, sobrenome, email,senha));
            }
        } catch (SQLException e) {
            optionalUsuario = Optional.empty();
        }
        return optionalUsuario;
    }

}

