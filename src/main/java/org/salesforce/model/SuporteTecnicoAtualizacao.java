package org.salesforce.model;

public class SuporteTecnicoAtualizacao {

    private StatusSuporteTecnico statusSuporteTecnico;

    private PrioridadeSuporteTecnico prioridadeSuporteTecnico;

    public SuporteTecnicoAtualizacao() {
    }

    public SuporteTecnicoAtualizacao(StatusSuporteTecnico statusSuporteTecnico, PrioridadeSuporteTecnico prioridadeSuporteTecnico) {
        this.statusSuporteTecnico = statusSuporteTecnico;
        this.prioridadeSuporteTecnico = prioridadeSuporteTecnico;
    }

    public StatusSuporteTecnico getStatusSuporteTecnico() {
        return statusSuporteTecnico;
    }

    public void setStatusSuporteTecnico(StatusSuporteTecnico statusSuporteTecnico) {
        this.statusSuporteTecnico = statusSuporteTecnico;
    }

    public PrioridadeSuporteTecnico getPrioridadeSuporteTecnico() {
        return prioridadeSuporteTecnico;
    }

    public void setPrioridadeSuporteTecnico(PrioridadeSuporteTecnico prioridadeSuporteTecnico) {
        this.prioridadeSuporteTecnico = prioridadeSuporteTecnico;
    }
}
