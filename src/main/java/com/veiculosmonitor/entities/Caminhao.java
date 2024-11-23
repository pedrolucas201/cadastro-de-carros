package com.veiculosmonitor.entities;

import javax.persistence.Entity;

@Entity
public class Caminhao extends Veiculo {
    private int capacidadeCarga;

    // Getters e Setters

    public int getCapacidadeCarga() {
        return capacidadeCarga;
    }

    public void setCapacidadeCarga(int capacidadeCarga) {
        this.capacidadeCarga = capacidadeCarga;
    }
}
