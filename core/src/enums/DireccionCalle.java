package enums;

import com.badlogic.gdx.math.Vector2;

import utiles.Recursos;

public enum DireccionCalle {

	CALLE_HORIZONTAL(Recursos.bundle.get("enums_direccionCalle_horizontal_nombre"), new Vector2(2,1)),
	CALLE_VERTICAL(Recursos.bundle.get("enums_direccionCalle_vertical_nombre"), new Vector2(2,0)),	
	CODO_1(Recursos.bundle.get("enums_direccionCalle_codo_1_nombre"), new Vector2(0,0)),
	CODO_2(Recursos.bundle.get("enums_direccionCalle_codo_2_nombre"), new Vector2(1,0)),
	CODO_3(Recursos.bundle.get("enums_direccionCalle_codo_3_nombre"), new Vector2(1,1)),
	CODO_4(Recursos.bundle.get("enums_direccionCalle_codo_4_nombre"), new Vector2(0,1)),
	CALLE_EN_T_HORIZONTAL_ARRIBA(Recursos.bundle.get("enums_direccionCalle_en_T_horizontal_arriba_nombre"), new Vector2(2,2)),
	CALLE_EN_T_HORIZONTAL_ABAJO(Recursos.bundle.get("enums_direccionCalle_en_T_horizontal_abajo_nombre"), new Vector2(2,3)),
	CALLE_EN_T_VERTICAL_DERECHA(Recursos.bundle.get("enums_direccionCalle_en_T_vertical_derecha_nombre"), new Vector2(1,2)),
	CALLE_EN_T_VERTICAL_IZQUIERDA(Recursos.bundle.get("enums_direccionCalle_en_T_vertical_izquierda_nombre"), new Vector2(1,3)),
	INTERSECCION(Recursos.bundle.get("enums_direccionCalle_interseccion_nombre"), new Vector2(0,2));
	
	String nombre;
	Vector2 posicion;
	
	DireccionCalle(String nombre,Vector2 posicion){
		this.posicion = posicion;
		this.nombre = nombre;
	}
	
	/**
	 * Devuelve la posicion de la imagen en la matriz
	 * @return
	 */
	public Vector2 getPosicion() {
		return posicion;
	}
	
	public String getNombre() {
		return nombre;
	}
}
