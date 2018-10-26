package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import java.util.LinkedList;
import java.util.Queue;
//TODO: Vidas:
//TODO: Enemigos:
//TODO: Settings imagen:
//TODO: SCores que no funcionen:
//TODO: ganar y perder:
//TODO: Creditos letrero:

public class Play extends Pantalla {
    private static float score;
    private final Pantalla_Inicio pantallaInicio;
    private float power;
    private float direccion;
    private float variable;
    private float time;

    private Sprite sprite;

//Enemigo___________________________________________________________________________________________

    private Texture enemigoBlock = new Texture(Gdx.files.internal("crow.png"));
    private Sprite enemigoSprite = new Sprite(enemigoBlock);
    private Enemigo enemigo;
    private Queue<Enemigo> crows;
    private int numCrows = 6;

//__________________________________________________________________________________________________



//Flecha____________________________________________________________________________________________


    private Texture plumaBlock = new Texture(Gdx.files.internal("pluma.png"));
    private Sprite plumaSprite = new Sprite(plumaBlock);
    private Pluma pluma;
    private float VELOCIDAD = 10;
    private Queue<Pluma> plumas;
    private int shoots = 9;

//__________________________________________________________________________________________________

    private Texture Arco = new Texture("arco.png");
    private Texture fnd = new Texture("nivel1.png");
    private Texture BotRegreso = new Texture("back.png");
    private Texture BtnPause = new Texture("pausaBtn.png");
    private Texto texto;
    private float puntos;
    private Texto punctuationText;
    private Texto winText;
    private Texto loseText;
    private Estado estado;

    public Play(Pantalla_Inicio pantallaInicio) {
        estado = Estado.JUGANDO;
        this.pantallaInicio = pantallaInicio;
        this.crows = new LinkedList<Enemigo>();
        for (int i = 0; i < this.numCrows; i++) {
            this.crows.add(new Enemigo( new Texture("cuervo-sprite.png"), (float) Math.random() * 500, (float) Math.random() * 10 + 880));
        }
        this.plumas =  new LinkedList<Pluma>();
        for (int i = 0; i < shoots; i++) {
            this.plumas.add(new Pluma(plumaBlock, ANCHO/3, 20));
        }
    }

    @Override
    public void show() {
        sprite = new Sprite(new Texture("back.png"));
        sprite = new Sprite(new Texture("arco.png"));
        sprite.setPosition(ALTO*.2f,ANCHO*.2f );
        sprite = new Sprite(new Texture("pausaBtn.png"));
        Gdx.input.setInputProcessor(new ProcesadorDeEntrada());
        crearObjetos();
        Gdx.input.setInputProcessor(new ProcesadorDeEntrada());
    }
    private void crearObjetos(){
        pluma = this.plumas.remove();
        enemigo = this.crows.remove();
        texto = new Texto();
        punctuationText = new Texto();
        winText = new Texto();
        loseText = new Texto();
    }

    @Override
    public void render(float delta) {
        if (estado == Estado.PAUSADO) {
            return;
        }
        if (!this.pluma.isActive && !this.plumas.isEmpty()) {
            this.pluma = this.plumas.remove();
        }


        if (!this.enemigo.isActive && !this.crows.isEmpty()) {
            enemigo = this.crows.remove();
        }

        if (!this.enemigo.isActive && estado != Estado.GANO) {
            this.estado = Estado.GANO;
            //TODO: AGREGAR PUNTAJES
        }

        if (!pluma.isActive && estado != Estado.GANO && estado != Estado.PERDIO) {
            this.estado = Estado.PERDIO;
        }

        if (pluma.getPositionY() > ALTO
                || pluma.getPositionX() < 0
                || pluma.getPositionX() > ANCHO) {
            pluma.deactivate();
        }

        float x11 = pluma.getPositionX();
        float x21 = enemigo.getPositionX() + 60;
        float y11 = pluma.getPositionY();
        float y21 = enemigo.getPositionY();
        float x12 = x11 + pluma.getAncho();
        float x22 = x21 + enemigo.getAncho() - 120;
        float y22 = y21 + enemigo.getAlto() - 120;
        if(pluma.isActive
                &&(((x11 > x21 && x11 < x22) || (x12 > x21 && x12 < x22))
                && (y11 > y21 && y11 < y22))){
            pluma.deactivate();
            enemigo.deactivate();
            puntos ++;
            score = puntos;
        }

        borrarPantalla(0, 0, 1);
        time += Gdx.graphics.getDeltaTime();
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(fnd, 0, 0);
        if (estado == Estado.JUGANDO) {
            batch.draw(Arco, 0, ALTO / 5.9f);
            pluma.mover(delta, power);
            enemigo.mover(delta);
            batch.draw(BtnPause, 0, ALTO / 1.12f);
            enemigo.dibujar(batch);
            pluma.dibujar(batch);
        }
        batch.draw(BotRegreso, ANCHO - BotRegreso.getWidth() * 1.0f, ALTO - BotRegreso.getHeight() * 1.2f);
        texto.mostrarMensaje(batch, Float.toString(puntos), ANCHO/2-ANCHO/6, 3.3f*ALTO/4); //falta calcular bien el tiempo
        punctuationText.mostrarMensaje(batch, "Puntuacion", ANCHO/2-ANCHO/6, 3.5f*ALTO/4);

        if (estado == Estado.PERDIO) {
            loseText.mostrarMensaje(batch, "PERDISTE", ANCHO/2, ALTO/2);
        }

        if (estado == Estado.GANO) {
            winText.mostrarMensaje(batch, "GANASTE", ANCHO/2, ALTO/2);
        }
        batch.end();
    }
    private void actualizarObjetos() {
        if(estado== Estado.JUGANDO){
        }
    }

    public static float getScore() {
        return score;
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

            }
            // batch.draw(BtnPause, 0, ALTO / 1.12f);
            float xP = 0;
            float yP= ALTO-BotRegreso.getWidth()*1.2f;
            float anchoP = BtnPause.getWidth();
            float altoP = BtnPause.getWidth();
            if(v.x >= xP && v.x <= xP + anchoP && v.y >= yP && v.y <= yP + altoP && (estado == Estado.JUGANDO || estado == Estado.PAUSADO)){
                if (estado == Estado.JUGANDO) {
                    estado = Estado.PAUSADO;
                } else if (estado == Estado.PAUSADO) {
                    estado = Estado.JUGANDO;
                }

            }
            variable = (((v.x - 360)/50) * ANCHO) + 10;
            direccion = v.x;

            return true;

        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            if (screenY < 1000 || !(estado == Estado.JUGANDO)) return true;
            Vector3 v = new Vector3(screenX, screenY, 0);
            camara.unproject(v);
            variable = ((360 - v.x)/50) * ANCHO;
            float alfa = (float) Math.atan2(ALTO-v.y, v.x);
            float vy = pluma.getVelocidad() * (float)Math.sin (alfa);
            float vx = variable * (float)Math.cos(alfa);
            pluma.setVx(vx);
            pluma.setVy(vy);
            pluma.volar(true);
            return false;
        }
        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            if (screenY >= 1000 && estado == Estado.JUGANDO) {
                Vector3 v = new Vector3(screenX, screenY, 0);
                camara.unproject(v);
                power = (v.y * 0.1f) * 2;
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
            return false;
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