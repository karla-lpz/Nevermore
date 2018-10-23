package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import java.awt.event.InputEvent;
import java.util.ArrayList;


public class Play extends Pantalla {
    private final Pantalla_Inicio pantallaInicio;
    private float power;
    private float direccion;
    private float variable;
    private float time;
    private Boolean volando = false;

    private Sprite sprite;

//Enemigo___________________________________________________________________________________________

    private Texture enemigoBlock = new Texture(Gdx.files.internal("crow.png"));
    private Sprite enemigoSprite = new com.badlogic.gdx.graphics.g2d.Sprite(enemigoBlock);
    private Enemigo enemigo;
    private ArrayList<Enemigo> crows;

//__________________________________________________________________________________________________



//Flecha____________________________________________________________________________________________


    private Texture plumaBlock = new Texture(Gdx.files.internal("pluma.png"));
    private Sprite plumaSprite = new com.badlogic.gdx.graphics.g2d.Sprite(plumaBlock);
    private Pluma pluma;
    private float VELOCIDAD = 10;

//__________________________________________________________________________________________________

    private Texture Arco = new Texture("arco.png");
    private Texture fnd = new Texture("nivel1.png");
    private Texture BotRegreso = new Texture("back.png");
    private Texture BtnPause = new Texture("pausaBtn.png");
    private Texto texto;
    private float puntos;
    private Texto textoPuntuación;
    private Estado estado = Estado.JUGANDO;

    public Play(Pantalla_Inicio pantallaInicio) {

        this.pantallaInicio = pantallaInicio;

    }

    @Override
    public void show() {
        sprite = new Sprite(new Texture("back.png"));
        sprite = new Sprite(new Texture("arco.png"));
        sprite.setPosition(ALTO*.2f,ANCHO*.2f );
        sprite = new Sprite(new Texture("pausaBtn.png"));
        Texture BotRegreso = new Texture("back.png");
        Texture BtnPause = new Texture("pausaBtn.png");
        Gdx.input.setInputProcessor(new ProcesadorDeEntrada());
        crearObjetos();
        Texture Arco = new Texture("arco.png");
        Gdx.input.setInputProcessor(new ProcesadorDeEntrada());
    }
    private void crearObjetos(){

        pluma = new Pluma(plumaBlock, ANCHO/4, 20);
        enemigo = new Enemigo( new Texture("cuervo-sprite.png"), ANCHO / 2, ALTO / 2);
        //enemigo = new Enemigo(enemigoBlock, (float) Math.random() * 500 + -250, (float) Math.random() * 10 + 880);
        texto = new Texto();
        textoPuntuación = new Texto();
    }


    @Override
    public void render(float delta) {
        borrarPantalla(0, 0, 1);
        time += Gdx.graphics.getDeltaTime();
        batch.setProjectionMatrix(camara.combined);
        //Gdx.app.log("Shit", Float.toString(enemigo.getPosition()));
        batch.begin();

        if(pluma.getPositionX() > enemigo.getPositionX() && pluma.getPositionX() < (enemigo.getPositionX() + enemigo.getAncho()) && pluma.getPositionY() > enemigo.getPositionY() && pluma.getPositionY() < (enemigo.getAlto() + enemigo.getPositionY()) ){
            puntos ++;

        }
        batch.draw(fnd, 0, 0);
        enemigo.dibujar(batch);
        pluma.dibujar(batch);
        batch.draw(Arco, 0, ALTO / 5.9f);
        pluma.mover(time, volando, power);
        batch.draw(BotRegreso, ANCHO - BotRegreso.getWidth() * 1.0f, ALTO - BotRegreso.getHeight() * 1.2f);
        batch.draw(BtnPause, 0, ALTO / 1.12f);
        texto.mostrarMensaje(batch, Float.toString(puntos), ANCHO/2-ANCHO/6, 3.3f*ALTO/4); //falta calcular bien el tiempo
        textoPuntuación.mostrarMensaje(batch, "Puntuacion", ANCHO/2-ANCHO/6, 3.5f*ALTO/4);

        enemigo.dibujar(batch);
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
            //batch.draw(BotRegreso, ANCHO - BotRegreso.getWidth() * 1.0f, ALTO - BotRegreso.getHeight() * 1.2f);
            float xR = ANCHO-BotRegreso.getWidth()*1.0f;
            float yR = ALTO-BotRegreso.getWidth()*1.2f;
            float anchoBtn = BotRegreso.getWidth();
            float altoBtn = BotRegreso.getWidth();
            if(v.x >= xR && v.x <= xR + anchoBtn && v.y >= yR && v.y <= yR + altoBtn){
                pantallaInicio.setScreen(new PantallaMenu(pantallaInicio) );
                Gdx.app.log("Pause is on", "ON");

            }
           // batch.draw(BtnPause, 0, ALTO / 1.12f);
            float xP = 0;
            float yP= ALTO-BotRegreso.getWidth()*1.2f;
            float anchoP = BtnPause.getWidth();
            float altoP = BtnPause.getWidth();
            if(v.x >= xP && v.x <= xP + anchoP && v.y >= yP && v.y <= yP + altoP){
                Gdx.graphics.setContinuousRendering(false);
                pantallaInicio.setScreen(new MenuStage(pantallaInicio) );

            }
            variable = (((v.x - 360)/50) * ANCHO) + 10;
            direccion = v.x;
            return true;

        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            Vector3 v = new Vector3(screenX, screenY, 0);
            camara.unproject(v);
            variable = ((360 - v.x)/50) * ANCHO;
            float alfa = (float) Math.atan(v.x);
            float vy = VELOCIDAD * (float)Math.sin (alfa);
            float vx = variable * (float)Math.cos(alfa);
            pluma.setVx(vx);
            pluma.setVy(vy);
            volando = true;
            if(pluma.getPositionX() == enemigo.getPositionX() && pluma.getPositionY() == enemigo.getPositionY()){
                Gdx.app.log("thats a hit", "Fuck this is hard");
                Gdx.app.log("Arrow Position", Float.toString(pluma.getPositionX()));
                Gdx.app.log("thats a hit", Float.toString(pluma.getPositionY()));
                Gdx.app.log("Arrow Position", Float.toString(enemigo.getPositionX()));
                Gdx.app.log("thats a hit", Float.toString(enemigo.getPositionY()));
            }
            return false;
        }
        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            Vector3 v = new Vector3(screenX, screenY, 0);
            camara.unproject(v);
            power = (v.y * 0.1f) * 2;
            Gdx.app.log("POWER", Float.toString(power));

//Seteando el sprite en la posicion inicial

            pluma.sprite.setY(v.y-200);
            if(v.y >= 200){
                pluma.sprite.setY(200);
            }

//Esta es la parte que hace que la puma gire y ademas tiene los limites de la pluma

                pluma.rotar(pluma, v.x);
                if(v.x > 390)
                {
                    pluma.rotar(pluma, 390);
                } else if(v.x < 340){
                    pluma.rotar(pluma, 340);
                }


            return true;
        }


//______Termina Touch_Drag

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
