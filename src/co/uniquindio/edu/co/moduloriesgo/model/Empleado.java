package co.uniquindio.edu.co.moduloriesgo.model;

public class Empleado {
	String estado, nombre, cedula, cargo, arl, eps, arlpath, epspath;

	public String getEstado() {
		return estado;
	}

	public String getArlpath() {
		return arlpath;
	}

	public void setArlpath(String arlpath) {
		this.arlpath = arlpath;
	}

	public String getEpspath() {
		return epspath;
	}

	public void setEpspath(String epspath) {
		this.epspath = epspath;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getArl() {
		return arl;
	}

	public void setArl(String arl) {
		this.arl = arl;
	}

	public String getEps() {
		return eps;
	}

	public void setEps(String eps) {
		this.eps = eps;
	}

	public Empleado(String estado, String nombre, String cedula, String cargo, String arl, String eps) {
		super();
		this.estado = estado;
		this.nombre = nombre;
		this.cedula = cedula;
		this.cargo = cargo;
		this.arl = arl;
		this.eps = eps;
	}

}
