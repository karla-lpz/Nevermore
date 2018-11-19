package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public class PantallaSettings extends Pantalla {
    private final Pantalla_Inicio pantallaInicio;

    private Sprite sprite;

    private Texture fondo = new Texture("settingsPantalla.png");
    private Texture btnRegreso = new Texture( "Buttons/back.png");
    private Texture creditsBtn = new Texture( "Buttons/creditsBtn.png");
    private Texture title = new Texture("Titles/settingstitulo.png");

    private boolean music;

    public PantallaSettings(Pantalla_Inicio pantallaInicio) {

        this.pantallaInicio = pantallaInicio;

    }

    @Override
    public void show() {
        sprite = new Sprite(new Texture("Buttons/back.png"));
        sprite.setPosition(ALTO *.2f,ANCHO *.2f);
        sprite = new Sprite(new Texture("Titles/scorestitulo.png"));
        sprite.setPosition(ALTO / 2, ANCHO / 2);
        sprite = new Sprite(new Texture("Buttons/creditsBtn.png"));
        sprite.setPosition(ALTO / 2,ANCHO / 2);
        Gdx.input.setInputProcessor(new ProcesadorDeEntrada());
    }

    @Override
    public void render(float delta) {
        borrarPantalla(0, 0, 0);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(fondo, 0, 0);
        batch.draw(btnRegreso, ANCHO - btnRegreso.getWidth() * 1.0f, ALTO - btnRegreso.getHeight() * 1.2f);
        batch.draw(creditsBtn,  0.50f * ANCHO / 2 , 0.07f * ALTO / 2);
        batch.draw(title, ALTO / 150.5f, ANCHO / 0.70f);
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
            float x = ANCHO-btnRegreso.getWidth()*1.0f;
            float y = ALTO-btnRegreso.getWidth()*1.2f;
            float anchoBtn = btnRegreso.getWidth();
            float altoBtn = btnRegreso.getWidth();
            if(v.x >= x && v.x <= x + anchoBtn && v.y >= y && v.y <= y + altoBtn){
                pantallaInicio.setScreen(new PantallaMenu(pantallaInicio) );
            }
            float xMusic = 0.75f * ANCHO / 2 ;
            float yMusic = 0.25f * ALTO / 2;
            float anchoMusic = creditsBtn.getWidth();
            float altoMusic = creditsBtn.getWidth();
            if(v.x >= xMusic && v.x <= xMusic + anchoMusic && v.y >= yMusic && v.y <= yMusic + altoMusic){
                pantallaInicio.setScreen(new PantallaCredits(pantallaInicio));
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
