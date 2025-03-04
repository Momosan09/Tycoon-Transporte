package mapa.transporte;

import java.util.ArrayList;
import java.util.Collections;

import vehiculos.Auto;
import vehiculos.Vehiculo;

public class Ruta {

	public String nombre;
	public ArrayList<Vehiculo> autos = new ArrayList<>();
	public ArrayList<TileTransporte> camino = new ArrayList<>();
	
	public Ruta(String clave, ArrayList<TileTransporte> camino) {
		this.camino = camino;
        Collections.reverse(this.camino); // Invierte para que empiece desde la primera tile
		this.nombre = clave;
		autos.add(new Auto(0,0,this.camino));
		
	}
	
	
	public void dibujarAutos() {
		if(!autos.isEmpty()) {
			for(int i = 0; i<autos.size();i++) {
				autos.get(i).dibujar();
				autos.get(i).circular();
			}
		}
	}
	
	public int getCantidadDeVehiculos() {
		return autos.size();
	}
	
	public void agregarVehiculo() {
		 ArrayList<TileTransporte> copiaDelCamino = new ArrayList<>(this.camino);
		autos.add(new Auto(0,0,copiaDelCamino));
	}
}
