package co.uniquindio.edu.co.moduloriesgo.model;

public enum Modulo {
	
	planificacion("Modulo de planificacion"),
	colaboracion("Modulo de colaboracion"),
	facturacionYContabilidad("Modulo de facturacion y contabilidad"),
	seguimiento("Modulo de seguimiento"),
	gestionRecursos("Modulo de gestion de recursos"),
	gestionRiesgos("Modulo de gestion de riesgos"),
	seguridad("Modulo de seguridad");
	
	private String nombre="";
	
	Modulo (String nombre){
		this.nombre = nombre;
		
	}

	public String getNombre() {
		return nombre;
	}

	
	

}

		

