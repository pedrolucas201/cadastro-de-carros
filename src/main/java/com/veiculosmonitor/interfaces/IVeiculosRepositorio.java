package com.veiculosmonitor.interfaces;

import com.veiculosmonitor.entities.Veiculo;

import java.util.List;

public interface IVeiculosRepositorio {
    void salvar(Veiculo veiculo);            // Adiciona um novo veículo
    void atualizar(Veiculo veiculo);        // Atualiza os dados de um veículo
    void excluir(Long id);                  // Remove um veículo pelo ID
    Veiculo buscarPorId(Long id);           // Busca um veículo pelo ID
    Veiculo buscarPorPlaca(String placa);   // Busca um veículo pela placa
    List<Veiculo> buscarTodos();            // Retorna todos os veículos
    List<Veiculo> buscarPorTipo(String tipo); // Retorna veículos filtrados por tipo
}
