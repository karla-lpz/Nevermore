package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public class Play extends Pantalla {
    private final Pantalla_Inicio pantallaInicio;
    private Sprite sprite;

    private Texture fnd = new Texture("nivel1.png");
    private Texture BotRegreso = new Texture("back.png");

    public Play(Pantalla_Inicio pantallaInicio) {

        this.pantallaInicio = pantallaInicio;
    }

    @Override
    public void show() {
        sprite = new Sprite(new Texture("back.png"));
        sprite.setPosition(ALTO*.2f,ANCHO*.2f );
        Texture BotRegreso = new Texture("back.png");
        Gdx.input.setInputProcessor(new ProcesadorDeEntrada());
    }

    @Override
    public void render(float delta) {
        borrarPantalla(0, 0, 1);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(fnd, 0, 0);
        //Los elementos se crean en orden
        batch.draw(BotRegreso, ANCHO - BotRegreso.getWidth() * 1.0f, ALTO - BotRegreso.getHeight() * 1.2f);
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
            float x = ANCHO-BotRegreso.getWidth()*1.5f;
            float y = ALTO-BotRegreso.getWidth()*1.5f;
            float anchoBtn = BotRegreso.getWidth();
            float altoBtn = BotRegreso.getWidth();
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
