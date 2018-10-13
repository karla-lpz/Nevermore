package mx.itesm.practica2.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import mx.itesm.practica2.Pantalla_Inicio;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width =720;
		config.height=1280;
		new LwjglApplication(new Pantalla_Inicio(), config);
	}
}
