package co.uniquindio.edu.co.moduloriesgo.model;

import java.io.Serializable;
import java.time.LocalDate;

public class PlanRiesgo implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	String solucion;
	LocalDate fechaInicio;
	LocalDate fechaFinal;
	public String getSolucion() {
		return solucion;
	}
	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(LocalDate fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public PlanRiesgo(String solucion, LocalDate fechaInicio, LocalDate fechaFinal) {
		super();
		this.solucion = solucion;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
	}



}
