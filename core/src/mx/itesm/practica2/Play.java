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

        batch.end();
        //Gdx.app.log("Time", String.valueOf(time));
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
            //-vectores
            Gdx.app.log("Se solto el boton","el boton esta suelto" );
            Vector3 v = new Vector3(screenX, screenY, 0);
            camara.unproject(v);
            //Estos metodos no se como funcionan pero, no suenan logicos, como cambiar los argumentos de una funcion? y luego ni se usan de nuevo
            //
            //screenX = pluma.setVx(v.x);
            //screenY = vy;
           //TODO tengo que darle los valores en vx y vx como vectores pero estos valores son de PLUMA entonces aqui solo se hacen los calculos

            //esto es a donde se dirige la pluma
            /*La variable alfa=tangente^⁻1 de el valor de ALTO de la pantalla menos el valor de donde esta el dedo en forma de vectores
              luego la velocidad en x es el sin de alfa porque tenemos que sacar un valor del desplazamiento, este valor es menor que el cos de alfa que es igual a vy
              la velocidad en y
              los metodos setVy y setVx son para fijar el desplazamiento que va a tener la flecha
            */
            float alfa = (float) Math.atan2(ALTO-v.y, ANCHO-v.x);
            float vy = VELOCIDAD * (float)Math.sin (alfa);
            float vx = VELOCIDAD * (float)Math.cos(alfa);
            pluma.setVx(vx);
            pluma.setVy(vy);

            return false;
        }
        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            Vector3 v = new Vector3(screenX, screenY, 0);
            camara.unproject(v);
            power = 100 - v.y;
            direccion = v.x;
            pluma.sprite.setY(v.y-200);

            if(v.y >= 100){
                pluma.sprite.setY(100);
            }

                pluma.rotar(pluma, v.x);
                if(v.x > 470)
                {
                    pluma.rotar(pluma, 470);
                } else if(v.x < 270){
                    pluma.rotar(pluma, 270);
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
