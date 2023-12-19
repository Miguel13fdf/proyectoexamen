package com.example.proyecto.entity;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table (name = "unidad_operativa")
public class UnidadOperativa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6458661718318732840L;

	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	private Long codigo;
	
	private String nombreUnidadOperativa;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNombreUnidadOperativa() {
		return nombreUnidadOperativa;
	}

	public void setNombreUnidadOperativa(String nombreUnidadOperativa) {
		this.nombreUnidadOperativa = nombreUnidadOperativa;
	}
	
}
