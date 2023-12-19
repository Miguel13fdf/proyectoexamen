package com.example.proyecto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.proyecto.entity.UnidadOperativa;



@Repository
public class UnidadOperativaDaoImp implements IUnidadOperativaDao {

    @PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<UnidadOperativa> findAll() {
        return entityManager.createQuery("SELECT uni FROM unidad_operativa uni", UnidadOperativa.class).getResultList();
	}

	@Transactional
	@Override
	public void save(UnidadOperativa unidadOperativa) {
		entityManager.persist(unidadOperativa);
		
	}

	@Override
	public UnidadOperativa findOne(Long id) {
        return entityManager.find(UnidadOperativa.class, id);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		UnidadOperativa uniOp = findOne(id);
         if (uniOp != null) {
             entityManager.remove(uniOp);
         }		
	}

}
