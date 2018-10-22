package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import java.awt.event.InputEvent;



public class Play extends Pantalla {
    private final Pantalla_Inicio pantallaInicio;
    private float power;
    private float direccion;
    private float variable;
    private float time;
    private Boolean volando = false;
    private float VELOCIDAD = 5;
    private Sprite sprite;
    private Texture plumaBlock = new Texture(Gdx.files.internal("pluma.png"));
    private Sprite plumaSprite = new com.badlogic.gdx.graphics.g2d.Sprite(plumaBlock);
    private Pluma pluma;
    private static final int FRAME_COLS = 2, FRAME_ROWS=7;
    private Texture Arco = new Texture("arco.png");
    private Texture fnd = new Texture("nivel1.png");
    private Texture BotRegreso = new Texture("back.png");
    private Texture BtnPause = new Texture("pausaBtn.png");
    private Texture plumas = new Texture("pluma.png");
    private Texto texto;
    private Texto textoPuntuación;
    private Estado estado = Estado.JUGANDO;
    private int plumasNivel  = 5;

    Animation<TextureRegion> crowAnimation;
    Texture crowSheet;
    SpriteBatch spriteBatch;
    float stateTime;

    public Play(Pantalla_Inicio pantallaInicio) {

        this.pantallaInicio = pantallaInicio;
    }


    public void create() {

        // Load the sprite sheet as a Texture
        crowSheet = new Texture(Gdx.files.internal("sprites1.png"));

        // Use the split utility method to create a 2D array of TextureRegions. This is
        // possible because this sprite sheet contains frames of equal size and they are
        // all aligned.
        TextureRegion[][] tmp = TextureRegion.split(crowSheet, crowSheet.getWidth() / FRAME_COLS, crowSheet.getHeight() / FRAME_ROWS);

        // Place the regions into a 1D array in the correct order, starting from the top
        // left, going across first. The Animation constructor requires a 1D array.
        TextureRegion[] crowFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                crowFrames[index++] = tmp[i][j];
            }
        }
        // Initialize the Animation with the frame interval and array of frames
        crowAnimation = new Animation<TextureRegion>(0.025f, crowFrames);

        // Instantiate a SpriteBatch for drawing and reset the elapsed animation
        // time to 0
        spriteBatch = new SpriteBatch();
        stateTime = 0f;
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
        create();
        crearObjetos();
        Texture Arco = new Texture("arco.png");
        Gdx.input.setInputProcessor(new ProcesadorDeEntrada());
    }
    private void crearObjetos(){

        pluma = new Pluma(plumaBlock, ANCHO/4, 20);
        pluma.setPlumas(plumasNivel);
        texto = new Texto();
        textoPuntuación = new Texto();
    }


    @Override
    public void render(float delta) {
        borrarPantalla(0, 0, 0);
        TextureRegion currentFrame = crowAnimation.getKeyFrame(stateTime, true);
        stateTime += Gdx.graphics.getDeltaTime();
        batch.setProjectionMatrix(camara.combined);
        spriteBatch.draw(currentFrame, ANCHO/2, ALTO/2); // Draw current frame at (50, 50)
        batch.begin();
        batch.draw(fnd, 0, 0);
        pluma.dibujar(batch);
        batch.draw(Arco, 0, ALTO / 5.9f);
        pluma.mover(time, volando, power);
        //El render es el que va a dibujar a la pluma mientras se mueve entonces deberia cambiar de trayectoria
        batch.draw(BotRegreso, ANCHO - BotRegreso.getWidth() * 1.0f, ALTO - BotRegreso.getHeight() * 1.2f);
        batch.draw(BtnPause, 0, ALTO / 1.12f);
        texto.mostrarMensaje(batch, Integer.toString(3), ANCHO/2-ANCHO/6, 3.3f*ALTO/4); //falta calcular bien el tiempo
        textoPuntuación.mostrarMensaje(batch, "Puntuacion", ANCHO/2-ANCHO/6, 3.5f*ALTO/4);


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
        spriteBatch.dispose();
        crowSheet.dispose();
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
            plumasNivel --;
            pluma.setPlumas(plumasNivel);
            volando = true;
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
//______________________________________


//Esta es la parte que hace que la puma gire y ademas tiene los limites de la pluma

                pluma.rotar(pluma, v.x);
                if(v.x > 390)
                {
                    pluma.rotar(pluma, 390);
                } else if(v.x < 340){
                    pluma.rotar(pluma, 340);
                }

 //_____________________________________


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
