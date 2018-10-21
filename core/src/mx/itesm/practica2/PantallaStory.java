package mx.itesm.practica2;

import com.badlogic.gdx.graphics.Texture;

public class PantallaStory extends  Pantalla{

    private final Pantalla_Inicio pantallaInicio;

    private Texture story1 = new Texture("HISTORIA1.png");

    public PantallaStory(Pantalla_Inicio pantallaInicio){

        this.pantallaInicio = pantallaInicio;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        borrarPantalla(0, 0, 0);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(story1, 0, 0);
        batch.end();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
