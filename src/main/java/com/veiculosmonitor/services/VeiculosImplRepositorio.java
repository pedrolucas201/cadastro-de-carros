package com.veiculosmonitor.services;

import com.veiculosmonitor.entities.Veiculo;
import com.veiculosmonitor.interfaces.IVeiculosRepositorio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class VeiculosImplRepositorio implements IVeiculosRepositorio {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("VeiculosPU");

    @Override
    public void salvar(Veiculo veiculo) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(veiculo);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao salvar o veículo: " + e.getMessage());
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Veiculo buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Veiculo.class, id); // Busca o veículo pelo ID
        } catch (Exception e) {
            System.err.println("Erro ao buscar veículo por ID: " + e.getMessage());
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Veiculo> buscarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT v FROM Veiculo v", Veiculo.class).getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao buscar todos os veículos: " + e.getMessage());
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void atualizar(Veiculo veiculo) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(veiculo);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao atualizar o veículo: " + e.getMessage());
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void excluir(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Veiculo veiculo = em.find(Veiculo.class, id);
            if (veiculo != null) {
                em.getTransaction().begin();
                em.remove(veiculo);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao excluir o veículo: " + e.getMessage());
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Veiculo buscarPorPlaca(String placa) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT v FROM Veiculo v WHERE v.placa = :placa", Veiculo.class)
                    .setParameter("placa", placa)
                    .getSingleResult();
        } catch (Exception e) {
            System.err.println("Erro ao buscar veículo por placa: " + e.getMessage());
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Veiculo> buscarPorTipo(String tipo) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT v FROM Veiculo v WHERE TYPE(v) = :tipo", Veiculo.class)
                    .setParameter("tipo", tipo)
                    .getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao buscar veículos por tipo: " + e.getMessage());
            throw e;
        } finally {
            em.close();
        }
    }

    public void fechar() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
