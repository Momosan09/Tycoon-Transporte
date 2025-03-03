package mapa;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import entradas.Entradas;
import enums.DireccionCalle;
import enums.Esquina;
import eventos.EventoPonerCalle;
import eventos.Listeners;
import jugador.Habilidades;
import jugador.HabilidadesJugador;
import mapa.puntoDePartida.FabricaCarbon;
import mapa.puntoMuerto.FabricaEnergia;
import mapa.transporte.Calle;
import mapa.transporte.TileTransporte;
import pantallas.juegoGlobales.GlobalesJuego;
import utiles.DireccionClickTileTransporte;
import utiles.Render;
import vehiculos.Auto;
import mapa.transporte.Ruta;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;

public class Mapa implements InputProcessor, EventoPonerCalle{

    private Tile[][] tiles;
    private HashMap<Vector2, Calle> calles;
    private HashMap<Vector2, PuntoDePartida> puntosDePartida;
    private HashMap<Vector2, PuntoMuerto> puntosMuerto;
   // private Habilidades habilidades;

    //Pathfinding
    private Tile startNode, endNode, currentNode;
    private ArrayList<Tile> openList = new ArrayList<>();
    private ArrayList<Tile> chechedList = new ArrayList<>();
    private boolean goalReached = false, reiniciar = false;
    private int maximoDeBusqueda = 300; //"intentos" para encontrar el camino
   
    private ArrayList<TileTransporte> camino = new ArrayList<>();
    private HashMap<String, Ruta> rutas = new HashMap<>();
    public ArrayList<Auto> autos = new ArrayList<>();
    
    private Vector2 dimensiones;

    public Mapa() {
        this.calles = new HashMap<>();
        this.puntosDePartida = new HashMap<>();
        this.puntosMuerto = new HashMap<>();
        Listeners.agregarListener(this);
        //this.habilidades = new Habilidades(this); // Instancia de habilidades para interactuar con el mapa
    }

    // Método para inicializar el mapa, agregando tiles vacíos y otros elementos
    public void inicializar(int ancho, int alto) {
    	crearTiles(ancho, alto);//Las tiles tienen que ser las ultimas en agregarse para que funcione bien el inputevent de los demas
    	crearPuntosDePartida();
    	crearPuntosMuerto();
    }

    // Método para agregar una calle al mapa
//    public void agregarCalle(Vector2 inicio, Vector2 fin) {
//        Calle nuevaCalle = new Calle(inicio, fin);
//        calles.put(inicio, nuevaCalle); // Almacenar la calle por su punto de inicio
//
//        // Actualizar el tile correspondiente en el mapa
//        tiles[(int) inicio.x][(int) inicio.y] = nuevaCalle;
//    }

    

	/**
	 * Crea las tiles vacias
	 */
	private void crearTiles(int ancho, int alto) {
		tiles = new Tile[ancho][alto];
		int k = 0;
		for(int i = 0; i<ancho;i++) {
			for(int j = 0;j<alto;j++) {
				tiles[i][j] = new TileVacia(i,j);
			}
			
		}
		dimensiones = new Vector2(tiles.length*64, tiles[0].length*64);
	}
    
    // Método para remover una calle en una posición específica
    public void removerCalle(Vector2 posicion) {
        if (calles.containsKey(posicion)) {
            calles.remove(posicion);
            tiles[(int) posicion.x][(int) posicion.y] = new TileVacia((int) posicion.x, (int) posicion.y); // Restaurar el tile a vacío
        }
    }

    // Método para rotar una calle en una posición específica
    public void rotarCalle(Vector2 posicion) {
        if (calles.containsKey(posicion)) {
            Calle calle = calles.get(posicion);
            //calle.rotar(); // Llamar al método rotar en la clase Calle
        }
    }

    // Método para seleccionar un elemento en el mapa
    public void seleccionarElemento(Vector2 posicion) {
        // Aquí puedes definir cómo interactuar con el elemento seleccionado (punto de partida, calle, etc.)
        if (calles.containsKey(posicion)) {
            // Acción al seleccionar una calle
        } else if (puntosDePartida.containsKey(posicion)) {
            // Acción al seleccionar un punto de partida
        } else if (puntosMuerto.containsKey(posicion)) {
            // Acción al seleccionar un punto muerto
        }
    }

    // Método para mover un elemento (calle, edificio, etc.)
    public void moverElemento(Vector2 nuevaPosicion) {
        // Este método movería elementos, como calles, a una nueva posición en el mapa
    }

    // Método para colocar una estructura en el mapa (puede ser un edificio, semáforo, etc.)
    public void agregarEstructura(String tipo, Vector2 posicion) {
        // Crear y agregar estructuras en el mapa según el tipo
        // Ejemplo: un semáforo o un edificio
    }

    private void crearPuntosDePartida() {
        FabricaCarbon fabricaCarbon = new FabricaCarbon(1, 1);
        puntosDePartida.put(new Vector2(1, 1), fabricaCarbon);
        tiles[1][1] = fabricaCarbon;

    }

    private void crearPuntosMuerto() {
        FabricaEnergia fabricaEnergia = new FabricaEnergia(5, 1, 64);
        puntosMuerto.put(new Vector2(5, 1), fabricaEnergia);
        tiles[5][1] = fabricaEnergia;
        
    }


    // Getter para la lista de calles
    public HashMap<Vector2, Calle> getCalles() {
        return calles;
    }

	private void dibujarPuntosDePartida() {
		for (PuntoDePartida puntoDePartida : puntosDePartida.values()) {
			puntoDePartida.dibujar();
		}
	}
	
	private void dibujarPuntosMuerto() {
		for (PuntoMuerto puntoMuerto : puntosMuerto.values()) {
			puntoMuerto.dibujar();
		}
	}
	
	private void dibujarCalles() {
		for (Calle calle : calles.values()) {
			calle.dibujar();

		}
	}
	
	private void dibujarAutos() {
		if(!rutas.isEmpty()) {
			for(Ruta ruta : rutas.values()) {
				ruta.dibujarAutos();
			}
		}
	}
    
    // Método de renderizado si es necesario, aunque se realiza en otro lugar (como en Habilidades)
    public void dibujar() {
    	dibujarCalles();
    	dibujarPuntosDePartida();
    	dibujarPuntosMuerto();
    	dibujarAutos();
    }
    
	public void dibujarTiles() {
		for (int i = 0; i<tiles.length;i++) {
			for(int j= 0; j<tiles[i].length;j++) {
				if(tiles[i][j] instanceof TileTransporte) {
				}else if(tiles[i][j] instanceof PuntoDePartida){
																	
				}else if(tiles[i][j] instanceof PuntoMuerto) {
					
				}else if(tiles[i][j] instanceof ObjetoDelMapa){

				}else {
					tiles[i][j].dibujar();
					
				}
			}
		}
	}
	
	
	@Override
	public void ponerCalle(TileVacia tile, DireccionClickTileTransporte direccionYClick) {
		if(GlobalesJuego.habilidadEnUso == HabilidadesJugador.PONER_TILES_TRANSPORTE) {
	    int clickX = (int) tile.posicionEnLaMatriz.x;
	    int clickY = (int) tile.posicionEnLaMatriz.y;


	    System.out.println("Click en: (" + clickX + ", " + clickY + ")");

	    if (tiles[clickX][clickY] instanceof TileVacia) {
//	        System.out.println("Antes de colocar: " + tiles[clickX][clickY].getClass().getSimpleName());

	        calles.put(new Vector2(clickX, clickY), new Calle(clickX, clickY, direccionYClick.direccion));

	        tiles[clickX][clickY] = new Calle(clickX, clickY, direccionYClick.direccion);

//	        System.out.println("Despues de colocar: " + tiles[clickX][clickY].getClass().getSimpleName());
	        acomodarCalles(clickX,clickY, direccionYClick.esquina);
	    } else {
//	        System.out.println("No se puede colocar calle en: (" + clickX + ", " + clickY + "), ya hay: " + tiles[clickX][clickY].getClass().getSimpleName());
	    }
		}
	}
	

	private void acomodarCalles(int clickX, int clickY, Esquina esquina) {
		if(calles.size() >= 1) {
			Calle calleIzquierda = calles.get(new Vector2(clickX-1,clickY));
			Calle calleDerecha = calles.get(new Vector2(clickX+1,clickY));
			Calle calleArriba = calles.get(new Vector2(clickX,clickY+1));
			Calle calleAbajo = calles.get(new Vector2(clickX,clickY-1));
			
			Calle calleObj = calles.get(new Vector2(clickX,clickY));
			DireccionCalle direccionActual = calleObj.getDireccion();
			
			boolean izquierda=false, derecha=false, arriba=false, abajo=false;
			
			if(calleIzquierda != null) {
			if(calleObj.posicionEnLaMatriz.x-1 == calleIzquierda.posicionEnLaMatriz.x){
				System.out.println("izquierda en linea");
				izquierda=true;
			}
			}
			
			if(calleDerecha != null) {
			if(calleObj.posicionEnLaMatriz.x+1 == calleDerecha.posicionEnLaMatriz.x){
				System.out.println("derecha en linea");
				derecha=true;
			}
			}
			
			
			if(calleArriba != null) {
			if(calleObj.posicionEnLaMatriz.y+1 == calleArriba.posicionEnLaMatriz.y){
				System.out.println("arriba en linea");
				arriba=true;
			}
			}
			
			if(calleAbajo != null) {
			if(calleObj.posicionEnLaMatriz.y-1 == calleAbajo.posicionEnLaMatriz.y){
				abajo=true;
			}
			}
			

			
			if((direccionActual == DireccionCalle.CALLE_VERTICAL && esquina == Esquina.ABAJO) && (derecha && izquierda)) {
				direccionActual = DireccionCalle.CALLE_EN_T_HORIZONTAL_ABAJO;
				calleObj.setDireccion(direccionActual);

			}
			
			
			if((direccionActual == DireccionCalle.CALLE_VERTICAL && esquina == Esquina.ARRIBA) && (derecha && izquierda)) {
				direccionActual = DireccionCalle.CALLE_EN_T_HORIZONTAL_ARRIBA;
				calleObj.setDireccion(direccionActual);

			}
			
			if((direccionActual == DireccionCalle.CALLE_VERTICAL || direccionActual == DireccionCalle.CALLE_HORIZONTAL) && (derecha && arriba) && (esquina == Esquina.DERECHA || esquina == Esquina.ARRIBA)) {
				direccionActual = DireccionCalle.CODO_4;
				calleObj.setDireccion(direccionActual);
			}
			
			if((direccionActual == DireccionCalle.CALLE_VERTICAL || direccionActual == DireccionCalle.CALLE_HORIZONTAL) && (izquierda && arriba) && (esquina == Esquina.IZQUIERDA || esquina == Esquina.ARRIBA)) {
				direccionActual = DireccionCalle.CODO_3;
				calleObj.setDireccion(direccionActual);
			}
			
			if((direccionActual == DireccionCalle.CALLE_VERTICAL || direccionActual == DireccionCalle.CALLE_HORIZONTAL) && (izquierda && abajo) && (esquina == Esquina.IZQUIERDA || esquina == Esquina.ABAJO)) {
				direccionActual = DireccionCalle.CODO_2;
				calleObj.setDireccion(direccionActual);
			}
			//Ok
			if((direccionActual == DireccionCalle.CALLE_VERTICAL) && izquierda && (esquina == Esquina.IZQUIERDA || esquina == Esquina.ABAJO)) {
				direccionActual = DireccionCalle.CODO_2;
				calleObj.setDireccion(direccionActual);
			}
			//Ok
			if((direccionActual == DireccionCalle.CALLE_HORIZONTAL) && arriba && (esquina == Esquina.DERECHA || esquina == Esquina.ARRIBA)) {
				direccionActual = DireccionCalle.CODO_4;
				calleObj.setDireccion(direccionActual);
			}
			//Ok
			if((direccionActual == DireccionCalle.CALLE_VERTICAL) && izquierda && (esquina == Esquina.IZQUIERDA || esquina == Esquina.ARRIBA)) {
				direccionActual = DireccionCalle.CODO_3;
				calleObj.setDireccion(direccionActual);
			}
			//Ok
			if((direccionActual == DireccionCalle.CALLE_HORIZONTAL) && arriba && (esquina == Esquina.IZQUIERDA || esquina == Esquina.ARRIBA)) {
				direccionActual = DireccionCalle.CODO_3;
				calleObj.setDireccion(direccionActual);
			}
			//Ok
			if((direccionActual == DireccionCalle.CALLE_VERTICAL) && derecha && ( esquina == Esquina.ARRIBA)) {
				direccionActual = DireccionCalle.CODO_4;
				calleObj.setDireccion(direccionActual);
			}
			
			//Ok
			if((direccionActual == DireccionCalle.CALLE_VERTICAL) && derecha && ( esquina == Esquina.ABAJO)) {
				direccionActual = DireccionCalle.CODO_1;
				calleObj.setDireccion(direccionActual);
			}
			
			//Ok
			if((direccionActual == DireccionCalle.CALLE_HORIZONTAL) && abajo && ( esquina == Esquina.IZQUIERDA) && calleAbajo.getDireccion() == DireccionCalle.CODO_2) {
				direccionActual = DireccionCalle.CODO_3;
				calleObj.setDireccion(direccionActual);
			}
			
			
			if((direccionActual == DireccionCalle.CALLE_HORIZONTAL) && (abajo && calleAbajo.getDireccion() == DireccionCalle.CALLE_VERTICAL) && esquina == Esquina.DERECHA) {
				direccionActual = DireccionCalle.CODO_1;
				calleObj.setDireccion(direccionActual);
			}
			
			if((direccionActual == DireccionCalle.CALLE_HORIZONTAL) && (abajo && calleAbajo.getDireccion() == DireccionCalle.CALLE_VERTICAL) && esquina == Esquina.IZQUIERDA) {
				direccionActual = DireccionCalle.CODO_2;
				calleObj.setDireccion(direccionActual);
			}
			
			
		}
	}
	
	

	
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    	if(button == 0) {
        Vector3 worldCoords = Render.camaraJuego.unproject(new Vector3(screenX, screenY, 0));
        
        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < tiles[i].length; j++) {
            	Tile tile = tiles[i][j];
        		if(tile.contains(worldCoords.x, worldCoords.y)) {
        			if(tile instanceof TileVacia) {
        				if(GlobalesJuego.habilidadEnUso == HabilidadesJugador.PONER_TILES_TRANSPORTE) {
        					tile.onClick(worldCoords.x, worldCoords.y);        					
        				}else {

        				}
        			}
        			
        			if(tile instanceof PuntoDePartida) {
        				if(GlobalesJuego.habilidadEnUso == HabilidadesJugador.CREAR_RUTA) {
        					if(startNode == null) {
        						tile.onClick();        						
        						startNode = tile;
        						currentNode = startNode;
        					}
        				}else if(GlobalesJuego.habilidadEnUso == HabilidadesJugador.INFO){
        					tile.onClick();
        				}
        			}
        			
        			if(tile instanceof PuntoMuerto) {
        				if(GlobalesJuego.habilidadEnUso == HabilidadesJugador.CREAR_RUTA) {
        					if(startNode !=null) {        						
        						tile.onClick();
        						endNode = tile;
        						setCostos();
        					}
        				}else if(GlobalesJuego.habilidadEnUso == HabilidadesJugador.INFO){
        					tile.onClick();
        					
        				}
        			}

        			return true;
        		}
        	}
        }
    	}
    	return false;
    }

    //Pathfinding
    private void setCostos() {	
    	for(int i = 0; i<tiles.length;i++) {
    		for(int j = 0; j<tiles[i].length;j++) {
        		getCostos(tiles[i][j]);
    		}
    	}
    	
    }
    
    //Pathfinding
    private void getCostos(Tile t) {
    	
    	//Calcula el costo G (Distancia que hay hasta el nodo inicial)
    	int xDistance = Math.abs((int)(t.posicionEnLaMatriz.x - startNode.posicionEnLaMatriz.x));
    	int yDistance = Math.abs((int)(t.posicionEnLaMatriz.y - startNode.posicionEnLaMatriz.y));
    	
    	t.gCost = xDistance + yDistance;
    	
    	//Calcula el costo H (Distancia que hay hasta el el nodo final)
    	xDistance = Math.abs((int)(t.posicionEnLaMatriz.x - endNode.posicionEnLaMatriz.x));
    	yDistance = Math.abs((int)(t.posicionEnLaMatriz.y - endNode.posicionEnLaMatriz.y));
    	
    	t.hCost = xDistance + yDistance;
    	
    	//Calcula el costo F (El costo total)
    	t.fCost = t.gCost + t.hCost;
    	
    	if(t != startNode && t != endNode) {
    		//System.out.println("("+t.posicionEnLaMatriz.x + ","+ t.posicionEnLaMatriz.y+ ") = F " + t.fCost);
    	}
    }
    
  //Pathfinding
    public void search() {
    	int buscandoIntento= 0;
    	camino.clear();
    	while(!goalReached && buscandoIntento < maximoDeBusqueda) {
    		int col = (int)currentNode.posicionEnLaMatriz.x;
    		int row = (int)currentNode.posicionEnLaMatriz.y;
    		
    		currentNode.setAsCheck();
    		chechedList.add(currentNode);
    		openList.remove(currentNode);
    		
    		//Tile arriba de la actual
    		if(row -1 >= 0) {
        		openNode(tiles[col][row-1]);
    		}
    		//Tile abajo de la actual
    		if(row+1 < tiles.length) {
    			openNode(tiles[col][row+1]);
    			
    		}
    		//Tile a la izquierda de la actual
    		if(col-1 >= 0) {
    			openNode(tiles[col-1][row]);    			
    		}
    		//Tile a la derecha de la actual
    		if(col+1 < tiles[0].length) {
    			openNode(tiles[col+1][row]);    			
    		}
    		
    		
    		//Buscar el mejor nodo
    		int bestNodeIndex = 0;
    		int bestNodeFCost = 999;
    		
    		for(int i = 0;i<openList.size();i++) {
    			//ver si el este nodo tiene un mejor costo F
    			if(openList.get(i).fCost < bestNodeFCost) {
    				bestNodeIndex = i;
    				bestNodeFCost = openList.get(i).fCost;
    			}else if(openList.get(i).fCost == bestNodeFCost) {
    				if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
    					bestNodeIndex = i;
    				}
    			}
    		}
    		
    		//Cuando termina el loop tenemos el mejor nodo, que es nuestro proximo nodo
    		if(!openList.isEmpty()) {
    			currentNode = openList.get(bestNodeIndex);    			
    		}else {
    			System.err.println("No hay ruta posible");
    		}
    		
    		if(currentNode == endNode) {
    			System.out.println("A* LLEGUE");
    			goalReached = true;
    			trackThePath();
    			
    			
    			String claveDefecto = "ruta"+rutas.size();
    			Ruta ruta = new Ruta(claveDefecto, camino);
    			
    			rutas.put(ruta.nombre, ruta);
    			Listeners.rutaAgregada(ruta.nombre);
    		}
    		buscandoIntento++;
    	}

    }
    
    //Pathfinding
    private void openNode(Tile t) {
    	if(!t.open && !t.checkeada && !t.solida) {
    		t.setAsOpen();
    		t.parent = currentNode; 
    		openList.add(t);
    	}
    }

    //Pathfinding
    private void trackThePath() {
    	Tile current = endNode;
    	while(current != startNode) {
    		current = current.parent;
    		if(current != startNode) {
    			current.setAsPath();
    			camino.add((TileTransporte)current);
    		}
    	
    	}
    }
    
    /**
     * Deja todo listo para volver a hacer A* desde otros nodos
     */
    public void reiniciar() {
    	startNode.volverALaNormalidad();
    	endNode.volverALaNormalidad();
		startNode = null;
		currentNode = null;
		endNode = null;
		chechedList.clear();
		openList.clear();
		
		for(int i = 0; i<tiles.length;i++) {
			for(int j=0;j<tiles[i].length;j++) {
				tiles[i][j].reiniciar();
			}
		}
		goalReached = false;
		 System.out.println("Pathfinding reiniciado.");
    }
    
    public boolean getGoalReached() {
    	return goalReached;
    }
    
    public ArrayList<TileTransporte> getCamino(){
    	return camino;
    }
    
    public Tile getEndNode() {
    	return endNode;
    }
    
	public Vector2 getDimensiones() {
		return dimensiones;
	}
	
	public HashMap<String, Ruta> getRutas(){
		return rutas;
	}
	
	public Ruta getRuta(String clave){
		return rutas.get(clave);
	}
    
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}



	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}
}
