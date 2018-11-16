package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import java.util.LinkedList;
import java.util.Queue;
import java.awt.Menu;
import java.util.logging.SocketHandler;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.viewport.Viewport;
//TODO: Vidas:
//TODO: Enemigos:
//TODO: Settings imagen:
//TODO: Scores que no funcionen:
//TODO: ganar y perder:
//TODO: Creditos letrero:
//TODO: Corregir direccion de la pluma con
public class Play extends Pantalla {
    private static float score;
    private final Pantalla_Inicio pantallaInicio;
    private float power;
    private float direccion;
    private float variable;
    private float time;
    private float iniPlumaY;
    private float iniPlumaX;
    private Sprite sprite;

//Enemigo___________________________________________________________________________________________

    private Texture enemigoBlock = new Texture(Gdx.files.internal("crow.png"));
    private Sprite enemigoSprite = new Sprite(enemigoBlock);
    private Enemigo enemigo;
    private Queue<Enemigo> crows;
    private int numCrows = 6;
    //Pausa
   // private ProcesadorDeEntrada.EscenaPausa escenaPausa;

//__________________________________________________________________________________________________

//Flecha____________________________________________________________________________________________

    //TODO: Move Pluma to method show
    private Texture plumaBlock = new Texture(Gdx.files.internal("pluma.png"));
    private Sprite plumaSprite = new Sprite(plumaBlock);
    private Pluma pluma;
    private float VELOCIDAD = 10;
    private Queue<Pluma> plumas;
    private int shoots = 9;

//__________________________________________________________________________________________________

    private Texture Arco = new Texture("arco.png");
    private Texture Mancha = new Texture("manchacuervo.png");
    private Texture fnd = new Texture("nivel1.png");
    private Texture BotRegreso = new Texture("back.png");
    private Texture BtnPause = new Texture("pausaBtn.png");
    Pixmap pixmap = new Pixmap((int)(ANCHO), (int)(ALTO*.22), Pixmap.Format.RGBA8888);
    private Texto texto;
    private float puntos;
    private Texto punctuationText;
    private Texto winText;
    private Texto loseText;
    private Estado estado;
    private boolean touchDownBool;
    private int cuerdaX;
    private int cuerdaY;
    public Play(Pantalla_Inicio pantallaInicio) {
        estado = Estado.JUGANDO;
        this.pantallaInicio = pantallaInicio;
        this.crows = new LinkedList<Enemigo>();
        for (int i = 0; i < this.numCrows; i++) {
            this.crows.add(new Enemigo( new Texture("cuervo-sprite.png"), (float) Math.random() * 500, (float) Math.random() * 10 + 880));
        }
        this.plumas =  new LinkedList<Pluma>();
        for (int i = 0; i < shoots; i++) {
            this.plumas.add(new Pluma(plumaBlock, ANCHO/3.6f, 20));
            //ancho/2 - pluma.block.with /2
        }
    }

    @Override
    public void show() {
        sprite = new Sprite(new Texture("back.png"));
        sprite = new Sprite(new Texture("arco.png"));
        sprite.setPosition(ALTO*.2f,ANCHO*.2f );
        sprite = new Sprite(new Texture("pausaBtn.png"));
        pixmap.setColor(1f,1f,1f,1f);
        //pixmap.setBlending(Pixmap.Blending.None);
        //pixmap.fillRectangle(0,0,pixmap.getWidth(),pixmap.getHeight());
        Gdx.input.setInputProcessor(new ProcesadorDeEntrada());
        crearObjetos();
        eliminarObjetos();
        Gdx.input.setInputProcessor(new ProcesadorDeEntrada());
    }


    // TODO: 6/11/18 Crear una clase eliminiar objetos

    private void crearObjetos(){
        touchDownBool = false;
        texto = new Texto();
        punctuationText = new Texto();
        winText = new Texto();
        loseText = new Texto();


    }
    private void eliminarObjetos(){
        pluma = this.plumas.remove();
        enemigo = this.crows.remove();

    }

    @Override
    public void render(float delta) {
        if (estado == Estado.PAUSADO) {
           // escenaPausa.draw();
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
                || pluma.getPositionX() < -200
                || pluma.getPositionX() > ANCHO) {
            pluma.deactivate();
        }
        Rectangle rectPluma=  (Rectangle)pluma.getRectangle();
        Rectangle rectEnem =  (Rectangle)enemigo.getRectangle();
        if(pluma.isActive && rectPluma.overlaps(rectEnem)){
            rectPluma.height = rectPluma.getHeight() - 20f;
            rectPluma.width = rectPluma.getWidth() - 20f;
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
            if(touchDownBool == false){
                pixmap.drawLine(50,0,(int)(ANCHO/2), (int)(ALTO/10));
                pixmap.drawLine((int)(ANCHO-50f),0,(int)(ANCHO/2), (int)(ALTO/10));
            }else {
                pixmap.setColor(1,1,1,0f);
                pixmap.fill();
                pixmap.setColor(1,1,1,1f);
                pixmap.drawLine(50,0,(int)(ANCHO/2), (cuerdaY/3));

                pixmap.drawLine((int)(ANCHO-50f),0,(int)(ANCHO/2),(cuerdaY/3));
            }
            Texture texturaRectangulo = new Texture( pixmap );
            batch.draw(texturaRectangulo, 0,0);
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
            touchDownBool = true;
            pixmap.setColor(1,1,1,1);
            float xR = ANCHO-BotRegreso.getWidth()*1.0f;
            float yR = ALTO-BotRegreso.getWidth()*1.2f;
            float anchoBtn = BotRegreso.getWidth();
            float altoBtn = BotRegreso.getWidth();
            if(v.x >= xR && v.x <= xR + anchoBtn && v.y >= yR && v.y <= yR + altoBtn){
                pantallaInicio.setScreen(new PantallaMenu(pantallaInicio) );
            }
            float xP = 0;
            float yP= ALTO-BotRegreso.getWidth()*1.2f;
            float anchoP = BtnPause.getWidth();
            float altoP = BtnPause.getWidth();
            if(v.x >= xP && v.x <= xP + anchoP && v.y >= yP && v.y <= yP + altoP && (estado == Estado.JUGANDO || estado == Estado.PAUSADO)){
                if (estado == Estado.JUGANDO) {
                    estado = Estado.PAUSADO;
                }
                else if (estado == Estado.PAUSADO) {
                    estado = Estado.JUGANDO;
                }
            }
            variable = (((v.x - 360)/50) * ANCHO) + 10;
            direccion = v.x;
            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            Vector3 v = new Vector3(screenX, screenY, 0);
            touchDownBool = false;
            if (v.y < 1000 || !(estado == Estado.JUGANDO)) return true;
            camara.unproject(v);
            variable = ((360 - v.x)/50) * ANCHO;
            float alfa = (float) Math.atan2(ALTO-v.y, v.x);
            float vy = pluma.getVelocidad() * (float)Math.sin (alfa);
            float vx = variable * (float)Math.cos(alfa);
            pluma.setVx(vx);
            //pluma.setVx(Math.max(vx, -547));
            Gdx.app.log("Impulso en X", Float.toString(vx));
            pluma.setVy(vy);
            Gdx.app.log("Impulso en Y", Float.toString(vx));
            pluma.volar(true);
            return false;
        }
        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {

            Vector3 v = new Vector3(screenX, screenY, 0);
            if (v.y >= 1000 && estado == Estado.JUGANDO) {
                cuerdaX = (int)v.x;
                cuerdaY = (int)v.y-1000;
                camara.unproject(v);
                power = (v.y * 0.1f) * 2;
                //pluma.sprite.setY(v.y-200);
                float dy = v.y - iniPlumaY;
                iniPlumaY = v.y;
                pluma.sprite.setY(v.y - (pluma.getAlto() /2) );
                pluma.rotar(pluma, v.x);
                if(v.x > 390)
                {
                    pluma.rotar(pluma, 390);
                }else if(v.x < 340){
                    pluma.rotar(pluma, 340);
                }

                return true;
            }
            return false;
        }

            /*private class EscenaPausa extends Stage{
                public EscenaPausa(Viewport vista, SpriteBatch batch) {
                    super(vista, batch);
                    Pixmap pixmap = new Pixmap((int) (ANCHO * 0.7f), (int) (ALTO * 0.8f), Pixmap.Format.RGBA8888);
                    pixmap.setColor(1f, 1f, 1f, 1f);
                    pixmap.fillRectangle(0, 0, pixmap.getWidth(), pixmap.getHeight());
                    Texture texturaRectangulo = new Texture(pixmap);
                    pixmap.dispose();
                    Image imgRectangulo = new Image(texturaRectangulo);
                    imgRectangulo.setPosition(0.15f*ANCHO, 0.1f*ALTO);
                    this.addActor(imgRectangulo);
                    Texture texturaBtnSalir = new Texture("comun/btnSalir.png");
                    TextureRegionDrawable trdSalir = new TextureRegionDrawable(
                            new TextureRegion(texturaBtnSalir));
                    ImageButton btnSalir = new ImageButton(trdSalir);
                    btnSalir.setPosition(ANCHO/2-btnSalir.getWidth()/2, ALTO/2);
                    btnSalir.addListener(new ClickListener(){
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            //Regresa al menú
                            pantallaInicio.setScreen(new PantallaMenu(pantallaInicio));
                        }
                    });
                    this.addActor(btnSalir);
                }

            }*/

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