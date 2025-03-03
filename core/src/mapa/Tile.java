package mapa;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import utiles.Recursos;
import utiles.Render;

public abstract class Tile extends Rectangle {
    
	protected String nombre = "Vacio";
	public Vector2 posicionEnLaMatriz;
	
	//pathfinding
	//todas las tiles son solidas menos las de transporte
	protected boolean solida = true, checkeada, open;
	protected int gCost, hCost, fCost;
	protected boolean inicio = false;
	protected boolean goal = false;
	protected Tile parent;
	
    public Tile(float x, float y, String nombre) {
        super(x*64, y*64, 64, 64);
        posicionEnLaMatriz = new Vector2(x, y);
        this.nombre = nombre;
    }
    
    public void dibujar() {
        Render.sr.rect(x, y, width, height);
    }    
    
    public void onClick() {
    	System.out.println("Click en (" + posicionEnLaMatriz.x + "," + posicionEnLaMatriz.y+")");
    }
    
    public void onClick(float cursorX, float cursorY) {
    	System.out.println("Click en (" + posicionEnLaMatriz.x + "," + posicionEnLaMatriz.y+")");
    }
    
    //Pathfinding
    public void setAsOpen() {
    	open = true;
    }
    //Pathfinding
    public void setAsCheck() {
    	if(!inicio && !goal) {
    		Render.sr.setColor(Color.RED);
    	}
    	checkeada = true;
    }
    
    //Pathfinding
    public void reiniciar() {
    	if(!inicio && !goal) {
    		Render.sr.setColor(Color.WHITE);
    	}
    	checkeada = false;
    	open = false;
    }
    
    //Pathfinding
    public void volverALaNormalidad() {

    }
    
    
    
    //Pathfinding
    public void setAsPath() {

    }
    
    
    
    /**
     * Devuelve la posicion en la matriz
     * Ejemplo: si la tile esta en x=64, y=64 El valor que devuelve es x=1,y=1
     * (posicion/64)
     * @return
     */
    protected Vector2 getPosicionEnLaMatriz() {
    	return posicionEnLaMatriz;
    }
    
    public Vector2 getPosicion() {
    	return new Vector2(x,y);
    }
	
	//Setea esta tile como el nodo de fin para el pathfinding
	public void setGoal(Sprite sprite) {
		this.goal = true;
		sprite = new Sprite(new Texture(Recursos.RUTA_FIN));
		sprite.setX(x);
		sprite.setY(y);
	}
    
	public boolean getInicio() {
		return inicio;
	}
	
	public boolean getFinal() {
		return goal;
	}
	
	public String getNombre() {
		return nombre;
	}
}
