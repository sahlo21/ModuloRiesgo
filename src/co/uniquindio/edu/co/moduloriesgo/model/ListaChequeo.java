package co.uniquindio.edu.co.moduloriesgo.model;

import java.io.Serializable;

public class ListaChequeo implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	String codigoRiesgo, si, no, nivelRiesgo, comentario;

	public ListaChequeo(String codigoRiesgo, String si, String no, String nivelRiesgo, String comentario) {
		super();
		this.codigoRiesgo = codigoRiesgo;
		this.si = si;
		this.no = no;
		this.nivelRiesgo = nivelRiesgo;
		this.comentario = comentario;
	}

	public String getCodigoRiesgo() {
		return codigoRiesgo;
	}

	public void setCodigoRiesgo(String codigoRiesgo) {
		this.codigoRiesgo = codigoRiesgo;
	}

	public String getSi() {
		return si;
	}

	public void setSi(String si) {
		this.si = si;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getNivelRiesgo() {
		return nivelRiesgo;
	}

	public void setNivelRiesgo(String nivelRiesgo) {
		this.nivelRiesgo = nivelRiesgo;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}



}
