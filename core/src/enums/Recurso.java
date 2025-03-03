package enums;

import utiles.Recursos;

public enum Recurso {

	CARBON(Recursos.bundle.get("enums_recursos_carbon")),
	HIERRO(Recursos.bundle.get("enums_recursos_hierro")),
	MADERA(Recursos.bundle.get("enums_recursos_madera")),
	COMIDA(Recursos.bundle.get("enums_recursos_comida")),
	PRECIOSO(Recursos.bundle.get("enums_recursos_precioso"));
	
	String nombre;
	
	Recurso(String nombre){
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
}
