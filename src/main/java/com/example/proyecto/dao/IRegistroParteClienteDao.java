package com.example.proyecto.dao;
import java.util.List;

import com.example.proyecto.entity.RegistroParteDiario;



public interface IRegistroParteClienteDao {

    List<RegistroParteDiario> findAll();

    public void save(RegistroParteDiario registroParteDiario);

    RegistroParteDiario findOne(Long id);

    public  void delete(Long id);
	
}
