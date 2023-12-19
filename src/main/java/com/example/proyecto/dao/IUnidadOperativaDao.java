package com.example.proyecto.dao;

import java.util.List;

import com.example.proyecto.entity.UnidadOperativa;



public interface IUnidadOperativaDao {

    List<UnidadOperativa> findAll();

    public void save(UnidadOperativa unidadOperativa);

    UnidadOperativa findOne(Long id);

    public  void delete(Long id);
	
}
