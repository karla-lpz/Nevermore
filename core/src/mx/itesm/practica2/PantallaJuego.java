package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

class PantallaJuego extends Pantalla {
    private final Pantalla_Inicio pantallaInicio;
    private Sprite sprite;


    //Fondo de la pantalla

    private Texture textFondo;
    private  Texture textBtnRegreso;


    public PantallaJuego(Pantalla_Inicio pantallaInicio) {

        this.pantallaInicio = pantallaInicio;
    }

    @Override
    public void show() {
        sprite = new Sprite(new Texture("Buttons/button_help.png"));
        sprite.setPosition(ANCHO/2, ALTO/3);
        //Fondo
        textFondo = new Texture("A_dream.jpg");
        textBtnRegreso = new Texture("Buttons/back.jpg");
        Gdx.input.setInputProcessor(new ProcesadorEntrada());


    }

    @Override
    public void render(float delta) {
        borrarPantalla(0, 0, 1);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(textFondo, 0, 0);
        batch.draw(textBtnRegreso, ANCHO-textBtnRegreso.getWidth()*1.5f, ALTO-textBtnRegreso.getHeight()*1.5f);
        sprite.draw(batch);
        batch.end();
        sprite.rotate(2);
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
    class ProcesadorEntrada implements InputProcessor {
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
            //Regresa al menú
            Vector3 v = new Vector3(screenX, screenY, 0);
            camara.unproject(v);
            float x = ANCHO - textBtnRegreso.getWidth() * 1.5f;
            float y = ALTO - textBtnRegreso.getHeight() * 1.5f;
            float anchoBtn = textBtnRegreso.getWidth();
            float altoBtn = textBtnRegreso.getWidth();
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
        //es una clase que si tiene que implementar para usar eventos

    }
}
