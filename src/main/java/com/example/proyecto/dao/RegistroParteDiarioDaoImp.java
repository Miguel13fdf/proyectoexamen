package com.example.proyecto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.proyecto.entity.RegistroParteDiario;



@Repository
public class RegistroParteDiarioDaoImp implements IRegistroParteClienteDao {

    @PersistenceContext
    private EntityManager entityManager;
	
	
	@Override
	public List<RegistroParteDiario> findAll() {
        return entityManager.createQuery("SELECT re FROM registro_parte_diario re", RegistroParteDiario.class).getResultList();

	}

    @Transactional
	@Override
	public void save(RegistroParteDiario registroParteDiario) {
        entityManager.persist(registroParteDiario);
	}

	@Override
	public RegistroParteDiario findOne(Long id) {
        return entityManager.find(RegistroParteDiario.class, id);
	}

	@Override
	public void delete(Long id) {
		RegistroParteDiario reg = findOne(id);
        if (reg != null) {
            entityManager.remove(reg);
        }
	}

}
