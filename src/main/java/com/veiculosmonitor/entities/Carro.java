package com.veiculosmonitor.entities;

import javax.persistence.Entity;

@Entity
public class Carro extends Veiculo {
    private int numeroPortas;

    // Getters e Setters

    public int getNumeroPortas() {
        return numeroPortas;
    }

    public void setNumeroPortas(int numeroPortas) {
        this.numeroPortas = numeroPortas;
    }
}
