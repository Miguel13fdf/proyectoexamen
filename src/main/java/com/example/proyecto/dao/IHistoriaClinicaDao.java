package com.example.proyecto.dao;

import java.util.List;

import com.example.proyecto.entity.HistoriasClinicas;



public interface IHistoriaClinicaDao {

    List<HistoriasClinicas> findAll();

    public void save(HistoriasClinicas historiaClinica);

    HistoriasClinicas findOne(Long id);

    public  void delete(Long id);
	
}
