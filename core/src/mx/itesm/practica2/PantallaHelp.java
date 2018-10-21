package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public class PantallaHelp extends Pantalla {
    private final Pantalla_Inicio pantallaInicio;
    private Sprite sprite;

    private Texture fondo = new Texture("FondoFinal.png");
    private Texture btnRegreso = new Texture( "back.png");
    private Texture title = new Texture("helptitulo.png");

    private Texto helpText;
    private Texto textEnemies;
    private Texto textItems;


    public PantallaHelp(Pantalla_Inicio pantallaInicio){

        this.pantallaInicio = pantallaInicio;
    }
    @Override
    public void show() {
        sprite = new Sprite(new Texture("back.png"));
        sprite.setPosition(ALTO *.2f,ANCHO *.2f);
        sprite = new Sprite(new Texture("helptitulo.png"));
        sprite.setPosition(ALTO / 2, ANCHO / 2);
        Texture btnRegreso = new Texture("back.png");
        createText();
        Gdx.input.setInputProcessor(new ProcesadorDeEntrada());
    }

    public void createText(){
        helpText = new Texto();
        textEnemies = new Texto();
        textItems = new Texto();
    }

    @Override
    public void render(float delta) {
        borrarPantalla(0, 0, 0);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(fondo, 0, 0);
        batch.draw(btnRegreso, ANCHO - btnRegreso.getWidth() * 1.0f, ALTO - btnRegreso.getHeight() * 1.2f);
        batch.draw(title, ALTO / 150.5f, ANCHO / 0.70f);
        helpText.mostrarMensaje(batch, "Swipe down to shoot \n the feather and kill \n your enemies",
                0.80f * ANCHO/2, 2.2f * ALTO/3);
        textEnemies.mostrarMensaje(batch, "Beat all of \n them to save your \n life and return to \n your world",
                0.80f * ANCHO / 2, 1.6f * ALTO / 3);
        textItems.mostrarMensaje(batch, "It would be items \n to help you in your \n adventure",0.80f * ANCHO / 2,
                0.75f * ALTO / 3);
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
