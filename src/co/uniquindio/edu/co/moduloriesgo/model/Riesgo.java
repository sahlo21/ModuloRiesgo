package co.uniquindio.edu.co.moduloriesgo.model;

import java.io.Serializable;

public class Riesgo implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	String nombre;
	String estado;
	Categoria categoria;
	Modulo modulo;
	String descripcion;
	int identificador;
	String fechaCreacion;
	boolean estadoRevision;
	PlanRiesgo planRiesgo;


	public boolean isEstadoRevision() {
		return estadoRevision;
	}
	public void setEstadoRevision(boolean estadoRevision) {
		this.estadoRevision = estadoRevision;
	}
	public PlanRiesgo getPlanRiesgo() {
		return planRiesgo;
	}
	public void setPlanRiesgo(PlanRiesgo planRiesgo) {
		this.planRiesgo = planRiesgo;
	}
	public Riesgo(String nombre, Categoria categoria, Modulo modulo, String descripcion, int identificador, String fechaCreacion) {
		super();
		this.nombre = nombre;
		this.estado = "En Revision";
		this.categoria = categoria;
		this.modulo = modulo;
		this.descripcion = descripcion;
		this.identificador = identificador;
		this.fechaCreacion = fechaCreacion;
	}
	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Riesgo [nombre=" + nombre + ", estado=" + estado + ", categoria=" + categoria + ", modulo=" + modulo
				+ ", descripcion=" + descripcion + ", identificador=" + identificador + ", fechaCreacion="
				+ fechaCreacion + "]";
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Modulo getModulo() {
		return modulo;
	}
	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



}
