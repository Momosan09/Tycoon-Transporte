package utiles;

import com.badlogic.gdx.utils.TimeUtils;

public abstract class Tiempo {
    private static long tiempoInicio = -1; // Inicialmente no ha empezado la espera

    public static boolean esperarTiempo(int miliS) {
        if (tiempoInicio == -1) { 
            tiempoInicio = TimeUtils.millis(); // Guarda el tiempo inicial solo una vez
        }

        if (TimeUtils.millis() >= tiempoInicio + miliS) { 
            tiempoInicio = -1; // Resetea para futuras esperas
            return true;
        }

        return false;
    }

    public static void reset() {
        tiempoInicio = -1; // Se puede usar para forzar el reinicio
    }
}
