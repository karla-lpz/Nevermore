package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import java.awt.event.InputEvent;



public class Play extends Pantalla {
    private final Pantalla_Inicio pantallaInicio;
    private float power;
    private float direccion;
    private float variable;
    private float time;
    private float VELOCIDAD = 500;
    private Sprite sprite;
    private Texture plumaBlock = new Texture(Gdx.files.internal("pluma.png"));
    private Sprite plumaSprite = new com.badlogic.gdx.graphics.g2d.Sprite(plumaBlock);
    private Pluma pluma;

    private Texture fnd = new Texture("nivel1.png");
    private Texture BotRegreso = new Texture("back.png");
    private Texture BtnPause = new Texture("pausaBtn.png");

    private Texto texto;
    private Texto textoPuntuación;

    private Estado estado = Estado.JUGANDO;

    public Play(Pantalla_Inicio pantallaInicio) {

        this.pantallaInicio = pantallaInicio;
    }

    @Override
    public void show() {
        sprite = new Sprite(new Texture("back.png"));
        sprite.setPosition(ALTO*.2f,ANCHO*.2f );
        sprite = new Sprite(new Texture("pausaBtn.png"));
        Texture BotRegreso = new Texture("back.png");
        Texture BtnPause = new Texture("pausaBtn.png");
        Gdx.input.setInputProcessor(new ProcesadorDeEntrada());
        crearObjetos();
        Gdx.input.setInputProcessor(new ProcesadorDeEntrada());
    }
    private void crearObjetos(){
        pluma = new Pluma(plumaBlock, ANCHO/4, 20);
        texto = new Texto();
        textoPuntuación = new Texto();
    }


    @Override
    public void render(float delta) {
        borrarPantalla(0, 0, 1);
        time += Gdx.graphics.getDeltaTime();
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(fnd, 0, 0);
        pluma.dibujar(batch);
        pluma.mover(time);
        //El render es el que va a dibujar a la pluma mientras se mueve entonces deberia cambiar de trayectoria
        batch.draw(BotRegreso, ANCHO - BotRegreso.getWidth() * 1.0f, ALTO - BotRegreso.getHeight() * 1.2f);
        batch.draw(BtnPause, 0, ALTO / 1.12f);
        texto.mostrarMensaje(batch, Float.toString(100f - time), ANCHO/2-ANCHO/6, 3.3f*ALTO/4); //falta calcular bien el tiempo
        textoPuntuación.mostrarMensaje(batch, "Points: ", ANCHO/2-ANCHO/6, 3.5f*ALTO/4);
        batch.end();
    }
    private void actualizarObjetos() {
        if(estado== Estado.JUGANDO){
        }
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
            float xR = ANCHO-BotRegreso.getWidth()*1.0f;
            float yR = ALTO-BotRegreso.getWidth()*1.2f;
            float anchoBtn = BotRegreso.getWidth();
            float altoBtn = BotRegreso.getWidth();
            if(v.x >= xR && v.x <= xR + anchoBtn && v.y >= yR && v.y <= yR + altoBtn){
                pantallaInicio.setScreen(new PantallaMenu(pantallaInicio) );
            }
            float xP = ANCHO-BtnPause.getWidth();
            float yP = ALTO-BtnPause.getWidth();
            float anchoP = BtnPause.getWidth();
            float altoP = BtnPause.getWidth();
            if(v.x >= xP && v.x <= xP + anchoP && v.y >= yP && v.y <= yP + altoP){
                pantallaInicio.setScreen(new PantallaMenu(pantallaInicio) );
            }

            return true;

        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            Vector3 v = new Vector3(screenX, screenY, 0);
            camara.unproject(v);
            variable = ((360 - v.x)/50) * ANCHO;
            float alfa = (float) Math.atan(v.x);
            float vy = 5 * (float)Math.sin (alfa);
            float vx = variable * (float)Math.cos(alfa);
            pluma.setVx(vx);
            pluma.setVy(vy);
            return false;
        }
        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            Vector3 v = new Vector3(screenX, screenY, 0);
            camara.unproject(v);
            power = 100 - v.y;
            if(v.x >= 390){
                v.x = 390;
            }else if (v.x <= 340){
                v.x = 340;
            }
            variable = (((v.x - 360)/50) * ANCHO) + 10;
            direccion = v.x;
            pluma.sprite.setY(v.y-200);
            if(v.y >= 200){
                pluma.sprite.setY(200);
            }
                pluma.rotar(pluma, v.x);
                if(v.x > 390)
                {
                    pluma.rotar(pluma, 390);
                } else if(v.x < 340){
                    pluma.rotar(pluma, 340);
                }
            return true;
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
