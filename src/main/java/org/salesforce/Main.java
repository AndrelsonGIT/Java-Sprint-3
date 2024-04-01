package org.salesforce;

import org.salesforce.model.Usuario;
import org.salesforce.model.UsuarioAtualizacao;
import org.salesforce.model.pedido.Pedido;
import org.salesforce.model.pedido.StatusPedido;
import org.salesforce.service.DataRegional;
import org.salesforce.service.PedidoService;
import org.salesforce.service.UsuarioService;

import java.time.LocalDateTime;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("Bruce","Wayne","batmanwayne@gotham.city.com", "Batcaverna");
        UsuarioService usuarioService = new UsuarioService();
        usuarioService.inserirUsuario(usuario);
        usuarioService.listarUsuarios();

        Optional<Usuario> optionalUsuario = usuarioService.acharUsuario(usuario.getEmail());
        UsuarioAtualizacao usuarioAtualizacao = new UsuarioAtualizacao(optionalUsuario.get().getId(), "coringa@gotham.city.com", "joker123");
        usuarioService.atualizarUsuario(usuarioAtualizacao);



        usuarioService.listarUsuarios();

        usuarioService.apagarUsuario(optionalUsuario.get().getId());

        usuarioService.inserirUsuario(usuario);

        optionalUsuario = usuarioService.acharUsuario(usuario.getEmail());

        Pedido pedido = new Pedido(optionalUsuario.get().getId(), LocalDateTime.now(), StatusPedido.PENDENTE);
        DataRegional dataRegional = new DataRegional();
        PedidoService pedidoService = new PedidoService(dataRegional);
        pedidoService.inserirPedido(pedido);
    }
}