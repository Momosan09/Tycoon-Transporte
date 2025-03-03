
package utiles;

import java.util.Random;

public abstract class Util {

	private static Random r = new Random();
	
	public static int generarAleatorio(int min, int max) {
		return r.nextInt(max+1) + min;
	}
	
}
