package com.veiculosmonitor.services;

import com.veiculosmonitor.entities.Veiculo;
import com.veiculosmonitor.interfaces.IServicoVendas;
import com.veiculosmonitor.interfaces.IVeiculosRepositorio;

public class VendasService implements IServicoVendas {
    private IVeiculosRepositorio repositorio;

    public VendasService(IVeiculosRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public boolean venderVeiculo(Long idVeiculo) {
        Veiculo veiculo = repositorio.buscarPorId(idVeiculo);
        if (veiculo != null) {
            // Exemplo de lógica de venda
            System.out.println("Veículo vendido: " + veiculo.getModelo());
            return true;
        }
        return false;
    }
}
