package com.example.proyecto.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;



@Entity
@Table (name = "historias_clinicas")
public class HistoriasClinicas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8740174365202789876L;

	@Id
	private String cedulaNroHistoriaClinica;
	
	private String nombrePaciente;
	private String apellidoPaciente;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date fechaNacimientoPaciente;

	public String getCedulaNroHistoriaClinica() {
		return cedulaNroHistoriaClinica;
	}

	public void setCedulaNroHistoriaClinica(String cedulaNroHistoriaClinica) {
		this.cedulaNroHistoriaClinica = cedulaNroHistoriaClinica;
	}

	public String getNombrePaciente() {
		return nombrePaciente;
	}

	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}

	public String getApellidoPaciente() {
		return apellidoPaciente;
	}

	public void setApellidoPaciente(String apellidoPaciente) {
		this.apellidoPaciente = apellidoPaciente;
	}

	public Date getFechaNacimientoPaciente() {
		return fechaNacimientoPaciente;
	}

	public void setFechaNacimientoPaciente(Date fechaNacimientoPaciente) {
		this.fechaNacimientoPaciente = fechaNacimientoPaciente;
	}
	
}
