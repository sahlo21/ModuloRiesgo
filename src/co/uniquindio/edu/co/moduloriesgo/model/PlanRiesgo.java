package co.uniquindio.edu.co.moduloriesgo.model;

import java.util.Date;

public class PlanRiesgo {
	String solucion;
	int idRiesgo;
	Date fecha;
	public PlanRiesgo(String solucion, int idRiesgo, Date fecha) {
		super();
		this.solucion = solucion;
		this.idRiesgo = idRiesgo;
		this.fecha = fecha;
	}
	public String getSolucion() {
		return solucion;
	}
	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}
	public int getIdRiesgo() {
		return idRiesgo;
	}
	public void setIdRiesgo(int idRiesgo) {
		this.idRiesgo = idRiesgo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
