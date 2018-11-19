package mx.itesm.practica2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Pantalla_Inicio extends Game {

    public static final int WIDTH = 720;
    public static final int HEIGHT = 1280;

    public SpriteBatch batch;

	@Override
	public void create () {
		setScreen(new PantallaMenu(this));
		batch = new SpriteBatch();
		//Que sea la primer pantalla que se muestra
		//Se manda el this para que tome toda la direcccion completa y mande llamar las demas pantallas
	}

	public void render(){
		super.render();
	}

}
