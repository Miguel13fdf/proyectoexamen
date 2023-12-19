package com.example.proyecto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;

import org.springframework.stereotype.Repository;

import com.example.proyecto.entity.HistoriasClinicas;





@Repository

public class HistoriaClinicaDaoImp implements IHistoriaClinicaDao {

    @PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<HistoriasClinicas> findAll() {
		return entityManager.createQuery("SELECT hi FROM historias_clinicas hi", HistoriasClinicas.class).getResultList();
	}

    @Transactional
	@Override
	public void save(HistoriasClinicas historiaClinica) {
        entityManager.persist(historiaClinica);
	}

	@Override
	public HistoriasClinicas findOne(Long id) {
        return entityManager.find(HistoriasClinicas.class, id);
	}

    @Transactional
	@Override
	public void delete(Long id) {
    	HistoriasClinicas histoClinica = findOne(id);
         if (histoClinica != null) {
             entityManager.remove(histoClinica);
         }		
	}

}
