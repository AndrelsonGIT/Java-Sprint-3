package org.salesforce.model;

public class SuporteTecnico {
    private int idProtocolo;
    private String titulo;
    private String status;
    private String descricao;
    private String prioridade;
    private int idPedido;

    public SuporteTecnico() {
    }

    public SuporteTecnico(int idProtocolo, String titulo, String status, String descricao, String prioridade, int idPedido) {
        this.idProtocolo = idProtocolo;
        this.titulo = titulo;
        this.status = status;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.idPedido = idPedido;
    }

    public int getIdProtocolo() {
        return idProtocolo;
    }

    public void setIdProtocolo(int idProtocolo) {
        this.idProtocolo = idProtocolo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }


}
