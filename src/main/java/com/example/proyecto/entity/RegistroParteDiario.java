package com.example.proyecto.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;



@Entity
@Table (name = "registro_parte_diario")
public class RegistroParteDiario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3429685739657111954L;

	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	private Long idParteDiario;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date fechaRegistro;
	
	private int numeroVisita;
	private Double pesoPaciente;
	private Double tallaPaciente;
	
	@ManyToOne ( cascade = CascadeType.ALL)
	@JoinColumn(name = "cedula_NroHistoriaClinica", referencedColumnName = "cedulaNroHistoriaClinica")
	private HistoriasClinicas historiaClinica;
	
	@ManyToOne ( cascade = CascadeType.ALL)
	@JoinColumn(name = "codigo", referencedColumnName = "codigo")
	private UnidadOperativa unidadOperativa;
	
	public Long getIdParteDiario() {
		return idParteDiario;
	}
	public void setIdParteDiario(Long idParteDiario) {
		this.idParteDiario = idParteDiario;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public int getNumeroVisita() {
		return numeroVisita;
	}
	public void setNumeroVisita(int numeroVisita) {
		this.numeroVisita = numeroVisita;
	}
	public Double getPesoPaciente() {
		return pesoPaciente;
	}
	public void setPesoPaciente(Double pesoPaciente) {
		this.pesoPaciente = pesoPaciente;
	}
	public Double getTallaPaciente() {
		return tallaPaciente;
	}
	public void setTallaPaciente(Double tallaPaciente) {
		this.tallaPaciente = tallaPaciente;
	}
	public HistoriasClinicas getHistoriaClinica() {
		return historiaClinica;
	}
	public void setHistoriaClinica(HistoriasClinicas historiaClinica) {
		this.historiaClinica = historiaClinica;
	}
	public UnidadOperativa getUnidadOperativa() {
		return unidadOperativa;
	}
	public void setUnidadOperativa(UnidadOperativa unidadOperativa) {
		this.unidadOperativa = unidadOperativa;
	}
	
}
