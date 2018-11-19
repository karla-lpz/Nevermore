package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public class PantallaCredits extends Pantalla {
    private final Pantalla_Inicio pantallaInicio;

    private Sprite sprite;

    private Texture fondo = new Texture("Background/FondoFinal.png");
    private Texture btnRegreso = new Texture( "Buttons/back.png");
    private Texture title = new Texture("Titles/creditstitulo.png");

    private Texto participantsTitle;
    private Texto participantsName;
    private Texto paticipantsC;
    private Texto music;

    public PantallaCredits(Pantalla_Inicio pantallaInicio) {

        this.pantallaInicio = pantallaInicio;
    }

    @Override
    public void show() {
        sprite = new Sprite(new Texture("Buttons/back.png"));
        sprite.setPosition(ALTO *.2f,ANCHO *.2f);
        sprite = new Sprite(new Texture("Titles/scorestitulo.png"));
        sprite.setPosition(ALTO / 2, ANCHO / 2);
        createText();
        Gdx.input.setInputProcessor(new PantallaCredits.ProcesadorDeEntrada());

    }

    private void createText(){
        participantsTitle = new Texto();
        participantsName = new Texto();
        paticipantsC = new Texto();
        music = new Texto();
    }

    @Override
    public void render(float delta) {
        borrarPantalla(0, 0, 0);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(fondo, 0, 0);
        batch.draw(btnRegreso, ANCHO - btnRegreso.getWidth() * 1.0f, ALTO - btnRegreso.getHeight() * 1.2f);
        batch.draw(title, ALTO / 150.5f, ANCHO / 0.70f);
        participantsTitle.mostrarMensaje(batch, "Participants", 40.5f * ALTO / 160.5f , ANCHO / 0.75f );
        participantsName.mostrarMensaje(batch, "Diana Diaz \nLeilani Trejo \nKarla Lopez \nHumberto Perez",
                30.5f * ALTO / 160.5f, ANCHO / 0.83f );
        paticipantsC.mostrarMensaje(batch, "LAD \nLAD \nISC \nISC", 62.5f * ALTO / 160.5f, ANCHO / 0.83f);
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

    private class ProcesadorDeEntrada implements InputProcessor {
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
                pantallaInicio.setScreen(new PantallaMenu(pantallaInicio));
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
