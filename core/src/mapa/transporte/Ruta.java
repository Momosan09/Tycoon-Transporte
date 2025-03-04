package mapa.transporte;

import java.util.ArrayList;
import java.util.Collections;

import mapa.PuntoDePartida;
import mapa.PuntoMuerto;
import mapa.Tile;
import vehiculos.Auto;
import vehiculos.Vehiculo;

public class Ruta {

	public String nombre;
	public ArrayList<Vehiculo> autos = new ArrayList<>();
	public ArrayList<TileTransporte> camino = new ArrayList<>();
	public PuntoMuerto pm;
	public PuntoDePartida pp;
	
	public Ruta(String clave, ArrayList<TileTransporte> camino, Tile startNode, Tile endNode) {
		this.camino = camino;
        Collections.reverse(this.camino); // Invierte para que empiece desde la primera tile
		this.nombre = clave;
		autos.add(new Auto(0,0,this.camino));
		this.pp = (PuntoDePartida) startNode;
		this.pm = (PuntoMuerto) endNode;
		
	}
	
	
	public void dibujarAutos() {
		if(!autos.isEmpty()) {
			for(int i = 0; i<autos.size();i++) {
				autos.get(i).dibujar();
				autos.get(i).circular(pp,pm);
			}
		}
	}
	
	public int getCantidadDeVehiculos() {
		return autos.size();
	}
	
	public void agregarVehiculo() {
		autos.add(new Auto(0,0,camino));
	}
}
