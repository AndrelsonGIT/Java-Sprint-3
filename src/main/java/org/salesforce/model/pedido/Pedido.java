package org.salesforce.model.pedido;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Pedido {
    private int id;

    private int idUsuario;

    private LocalDateTime dataPedido;

    private StatusPedido statusPedido;

    public Pedido() {
    }

    public Pedido(int id, int idUsuario, LocalDateTime dataPedido, StatusPedido statusPedido) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.dataPedido = dataPedido;
        this.statusPedido = statusPedido;
    }

    public Pedido(int idUsuario, LocalDateTime dataPedido, StatusPedido statusPedido) {
        this.idUsuario = idUsuario;
        this.dataPedido = dataPedido;
        this.statusPedido = statusPedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }
}
