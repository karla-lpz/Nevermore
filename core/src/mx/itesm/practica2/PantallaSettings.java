package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public class PantallaSettings extends Pantalla {
    private final Pantalla_Inicio pantallaInicio;
    private Sprite sprite;
    private Sprite settings;
    private Sprite volume;

    private Texture fondo = new Texture("settings-protipe.png");
    private Texture btnRegreso = new Texture( "back.png");

    public PantallaSettings(Pantalla_Inicio pantallaInicio){

        this.pantallaInicio = pantallaInicio;
    }
    @Override
    public void show() {
        sprite = new Sprite(new Texture("back.png"));
        sprite.setPosition(ALTO *.2f,ANCHO *.2f);
        //settings = new Sprite(new Texture("setting.png"));
        //settings.setPosition(ALTO / 2, ANCHO / 4);
        //volume = new Sprite(new Texture("volume.png"));
        //volume.setPosition(ALTO / 2 ,ANCHO / 2);
        Texture btnRegreso = new Texture("back.png");
        Gdx.input.setInputProcessor(new ProcesadorDeEntrada());
    }

    @Override
    public void render(float delta) {
        borrarPantalla(0, 0, 1);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(fondo, 0, 0);
        batch.draw(btnRegreso, ANCHO - btnRegreso.getWidth() * .70f, ALTO - btnRegreso.getHeight() * 1.5f);
        //batch.draw(settings, ANCHO / 2 - settings.getHeight() * 1.5f, ALTO *.90f - settings.getHeight());
        //batch.draw(volume, ANCHO / 2 - volume.getHeight() * .75f , ALTO / 2 - volume.getWidth() * .20f);
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

    class ProcesadorDeEntrada implements InputProcessor {

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
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            Vector3 v = new Vector3(screenX, screenY, 0);
            camara.unproject(v);
            float x = ANCHO-btnRegreso.getWidth()*.75f;
            float y = ALTO-btnRegreso.getWidth()*1.5f;
            float anchoBtn = btnRegreso.getWidth();
            float altoBtn = btnRegreso.getWidth();
            if(v.x >= x && v.x <= x + anchoBtn && v.y >= y && v.y <= y + altoBtn){
                pantallaInicio.setScreen(new PantallaMenu(pantallaInicio) );
            }
            return true;
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
        public boolean scrolled(int amount) {
            return false;
        }
    }
}
