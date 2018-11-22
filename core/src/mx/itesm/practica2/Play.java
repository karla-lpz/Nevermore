package mx.itesm.practica2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
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
    private String LetreroGanaste="Ganaste!";
    private Sprite sprite;
    private int stage = 0;
    private Music Musica;
    private Array<Corazones> arrCorazon;
    //Enemigo_______________________________
    private Enemy enemigo;
    private Queue<Enemy> enemies;
    private int numEnemies = 10;


    Sound EffectL = Gdx.audio.newSound(Gdx.files.internal("music/FOLEY_LANZAMIENTO.mp3"));
    Sound EffectE = Gdx.audio.newSound(Gdx.files.internal("music/FOLEY_EXPLOSION.mp3"));

    private EscenaPausa escenaPausa;
    private EscenaGano escenaGano;
    private EscenaPerdio escenaPerdio;

//__________________________________

//Flecha________________________________

    //TODO: Move Pluma to method show
    private Texture corazon1 = new Texture("hearts/corazon.png");
    private Texture corazon2 = new Texture("hearts/CORA2.png");
    private Texture corazon3 = new Texture("hearts/CORA3.png");
    private Texture corazon4 = new Texture("hearts/CORA4.png");
    private Texture corazon5 = new Texture("hearts/CORA5.png");
    private Pluma pluma;
    private float VELOCIDAD = 10;
    private Queue<Pluma> plumas;
    private int shoots = 9;


    //__________________________________
    private Texture Arco = new Texture("arco.png");
    private Texture fnd = new Texture("Levels/nivel1.png");
    private Texture BotRegreso = new Texture("Buttons/back.png");
    private Texture BtnPause = new Texture("Buttons/pausaBtn.png");
    Corazones cora = new Corazones(20, 20);
    Pixmap pixmap = new Pixmap((int)(ANCHO), (int)(ALTO*.22), Pixmap.Format.RGBA8888);

    private float puntos;

    private Texto texto;
    private Texto punctuationText;
    private Texto winText;
    private Texto loseText;

    private Estado estado;

    private boolean touchDownBool;

    private int cuerdaX;
    private int cuerdaY;



    public Play(Pantalla_Inicio pantallaInicio) {
        estado = Estado.JUGANDO;

        Music Musica = Gdx.audio.newMusic(Gdx.files.internal("music/CANCION_NIVEL1.mp3"));
        this.pantallaInicio = pantallaInicio;
        this.enemies = new LinkedList<Enemy>();
        for (int i = 0; i < this.numEnemies; i++) {
            Enemy newEnemy;
            int tmp = (int) ( Math.random() * 2 + 1);
            switch (tmp){
                case 1:
                    newEnemy = new Crow((float) Math.random() * 500, (float) Math.random() * 10 + 880);
                    this.enemies.add(newEnemy);
                    break;
                case 2:
                    newEnemy = new Cat((float) Math.random() * 500, (float) Math.random() * 10 + 880);
                    this.enemies.add(newEnemy);
                    break;
            }
        }
        this.plumas =  new LinkedList<Pluma>();
        for (int i = 0; i < shoots; i++) {
            this.plumas.add(new Pluma(ANCHO/3.6f, 20));
        }
    }

    @Override
    public void show() {
        sprite = new Sprite(new Texture("Buttons/back.png"));
        sprite = new Sprite(new Texture("arco.png"));
        sprite.setPosition(ALTO*.2f,ANCHO*.2f );
        sprite = new Sprite(new Texture("Buttons/pausaBtn.png"));
        pixmap.setColor(1f,1f,1f,1f);
        Gdx.input.setInputProcessor(new ProcesadorDeEntrada());
        crearObjetos();
        cargarMusica();
        eliminarObjetos();
        Gdx.input.setInputProcessor(new ProcesadorDeEntrada());
        arrCorazon = new Array<Corazones>(12*5);
        escenaPerdio = new EscenaPerdio(vista,batch);
        escenaGano = new EscenaGano(vista, batch);
    }


    private void crearObjetos(){
        touchDownBool = false;
        texto = new Texto();
        punctuationText = new Texto();
        winText = new Texto();
        loseText = new Texto();


    }
    private void eliminarObjetos(){
        pluma = this.plumas.remove();
        enemigo = this.enemies.remove();

    }

    @Override
    public void render(float delta) {

        if (!this.pluma.isActive && !this.plumas.isEmpty()) {
            this.pluma = this.plumas.remove();
        }


        if (!this.enemigo.isActive() && !this.enemies.isEmpty()) {
            enemigo = this.enemies.remove();
        }

        if (!this.enemigo.isActive() && estado != Estado.GANO) {
            this.estado = Estado.GANO;
            stage ++;
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
//        rectEnem.setCenter(enemigo.getPositionX()+(enemigo.getWidth()/2), enemigo.getPositionY()+(enemigo.getHeight()/2));
//        rectEnem.setSize(enemigo.getWidth()/2, enemigo.getHeight()/2);
        //rectPluma.height = rectPluma.getHeight();
        //rectPluma.width = rectPluma.getWidth();
        if(pluma.isActive && rectPluma.overlaps(rectEnem)){
            EffectE.play(1f);
            pluma.deactivate();
            enemigo.die(enemigo.getScaleX(), enemigo.getScaleY());
            Gdx.input.vibrate(500);
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
            enemigo.mover();
            batch.draw(BtnPause, 0, ALTO / 1.12f);
            enemigo.animate(batch);
            pluma.dibujar(batch);
            cora.render(batch, cora.getEstado());
            cora.render(batch, cora.getEstado());
            //Texture texturaRectangulo = new Texture( pixmap );
            //batch.draw(texturaRectangulo, 0,0);
            if(enemigo.getScaleX() > 4f){
                cora.BajarVida(cora.getEstado());
                Gdx.input.vibrate( 1000);
                enemigo.deactivate();
            }

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

        }
        batch.draw(BotRegreso, ANCHO - BotRegreso.getWidth() * 1.0f, ALTO - BotRegreso.getHeight() * 1.2f);
        texto.mostrarMensaje(batch, Float.toString(puntos), ANCHO/2-ANCHO/6, 3.3f*ALTO/4); //falta calcular bien el tiempo
        punctuationText.mostrarMensaje(batch, "Puntuacion", ANCHO/2-ANCHO/6, 3.5f*ALTO/4);

        batch.end();

        if (estado == Estado.PAUSADO) {
            escenaPausa.draw();
        }

        if (estado == Estado.PERDIO) {
            //escenaPerdio.draw();
            escenaPerdio.draw();
            Gdx.input.setInputProcessor(escenaPerdio);

        }

        if (estado == Estado.GANO) {
            escenaGano.draw();
            Gdx.input.setInputProcessor(escenaGano);
            Musica.stop();
        }

    }

    private void actualizarObjetos() {
        if(estado== Estado.JUGANDO){
            Musica.play();
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
    private void cargarMusica() {
        if(stage == 1 ){
            AssetManager manager = new AssetManager();
            manager.load("music/CANCION_NIVEL1.mp3", Music.class);
            manager.finishLoading();
            Musica = manager.get("music/CANCION_NIVEL1.mp3");
            Musica.setLooping(true);
            Musica.play();
        } else if(stage == 2){
            AssetManager manager = new AssetManager();
            manager.load("music/CANCION_NIVEL3.mp3", Music.class);
            manager.finishLoading();
            Musica = manager.get("music/CANCION_NIVEL3.mp3");
            Musica.setLooping(true);
            Musica.play();
        } else{
            AssetManager manager = new AssetManager();
            manager.load("music/CANCION_NIVEL1.mp3", Music.class);
            manager.finishLoading();
            Musica = manager.get("music/CANCION_NIVEL1.mp3");
            Musica.setLooping(true);
            Musica.play();
        }

    }
    @Override
    public void dispose() {

    }

    class ProcesadorDeEntrada implements InputProcessor {

        @Override
        public boolean keyDown(int keycode) {
            if(keycode == Input.Keys.BACK){
                Gdx.input.setCatchBackKey(true);
                pantallaInicio.setScreen(new PantallaMenu(pantallaInicio));
                return true;
            }
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
            //Pone el estado en Pausa
            if(v.x >= xP && v.x <= xP + anchoP && v.y >= yP && v.y <= yP + altoP && (estado == Estado.JUGANDO || estado == Estado.PAUSADO)){
                if (estado == Estado.JUGANDO) {
                    estado = Estado.PAUSADO;
                    //si pausa no existe lo crea
                    if (escenaPausa == null) {
                        escenaPausa = new EscenaPausa(vista, batch);
                    }
                    // PASA EL CONTROL A LA ESCENA
                    Gdx.input.setInputProcessor(escenaPausa); //

                }
                else if (estado == Estado.PAUSADO) {
                    estado = Estado.JUGANDO;
                }
            }

           /* if(estado==Estado.GANO){
               if(escenaGano == null){
                   escenaGano = new EscenaGano(vista,batch);
                   Gdx.input.setInputProcessor(escenaGano);
               }
            }*/
            /*if(estado==Estado.PERDIO){
                if(escenaPerdio == null){
                    escenaPerdio = new EscenaPerdio(vista,batch);
                    Gdx.input.setInputProcessor(escenaPerdio);
                }
            }*/




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
            Float newSuperVx;
            //newSuperVx = v.x;
            if(v.x < 90f){
                newSuperVx = 90f;
            }else {
                newSuperVx = v.x;
            }
            Gdx.app.log("Valor de Vx : ", Float.toString(v.x));
            variable = ((360 - newSuperVx)/50) * ANCHO;
            //if(v.y <= -50){
            //    VyTouchUp = -50;
            //}
            float alfa = (float) Math.atan2(ALTO-v.y, newSuperVx);
            float vy = pluma.getVelocidad() * (float)Math.sin(alfa);
            float vx = variable * (float)Math.cos(alfa);
            pluma.setVx(vx);
            //pluma.setVx(Math.max(vx, -547));
            pluma.setVy(vy);
            pluma.volar(true);
            EffectL.play(1f);
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            Vector3 v = new Vector3(screenX, screenY, 0);
            if (v.y >= 1000 && estado == Estado.JUGANDO) {
                //Se modifican aos
                cuerdaX = (int)v.x;
                cuerdaY = (int)v.y-1000;
                camara.unproject(v);
                power = (v.y * 0.1f) * 2;
                iniPlumaY = v.y;
                pluma.sprite.setY(v.y - (pluma.getHeight() /2) );
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
        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }
    private class EscenaPausa extends Stage{
        public EscenaPausa(Viewport vista, SpriteBatch batch) {
            super(vista, batch);
            Texto score = new Texto();
            Musica.pause();
            Texture fondoPausa = new Texture(Gdx.files.internal("Background/fondopausa1.png"));
            //Pixmap pixmap = new Pixmap((int) (ANCHO * 0.7f), (int) (ALTO * 0.8f), Pixmap.Format.RGBA8888);
            //pixmap.dispose();
            Image imgRectangulo = new Image(fondoPausa);
            imgRectangulo.setPosition(0.15f*ANCHO, 0.1f*ALTO);
            this.addActor(imgRectangulo);
            Texture texturaBtnSalir = new Texture("Buttons/home.png");
            TextureRegionDrawable trdSalir = new TextureRegionDrawable(new TextureRegion(texturaBtnSalir));
            ImageButton btnSalir = new ImageButton(trdSalir);
            btnSalir.setPosition((ANCHO/2)+150-btnSalir.getWidth()/2, ALTO/6);
            btnSalir.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    //Regresa al menú
                    Musica.stop();
                    pantallaInicio.setScreen(new PantallaMenu(pantallaInicio));

                }
            });
            this.addActor(btnSalir);
            Texture texturaBtnContinuar = new Texture("Buttons/playbtn1.png");
            TextureRegionDrawable trdContinuar = new TextureRegionDrawable(
                    new TextureRegion(texturaBtnContinuar));
            ImageButton btnContinuar = new ImageButton(trdContinuar);
            btnContinuar.setPosition((ANCHO/2)-150-btnContinuar.getWidth()/2, ALTO/6);
            btnContinuar.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    // Regresa al juego
                    Musica.play();
                    estado = Estado.JUGANDO;
                    Gdx.input.setInputProcessor(new ProcesadorDeEntrada()); // No debería crear uno nuevo
                }
            });
            this.addActor(btnContinuar);

            TextButton.TextButtonStyle estilo = new TextButton.TextButtonStyle();
            estilo.fontColor = Color.BLACK;
            estilo.font= new BitmapFont(Gdx.files.internal("fonts/Nevermore.fnt"));
            TextButton btn = new TextButton(String.valueOf(puntos), estilo);
            btn.setPosition((ANCHO/2) -30, (ALTO/2)-10);
            this.addActor(btn);

        }

    }

    //_______-PERDIO-________________________
    private class EscenaPerdio extends Stage{
        public EscenaPerdio(Viewport vista, SpriteBatch batch) {
            super(vista, batch);
            Texto score = new Texto();
            Texture fondoPausa = new Texture(Gdx.files.internal("Levels/nivel2.png"));
            //
            //
            //
            Image imgRectangulo = new Image(fondoPausa);
            imgRectangulo.setPosition(0, 0);
            this.addActor(imgRectangulo);
            Texture texturaBtnSalir = new Texture("Buttons/home.png");
            TextureRegionDrawable trdSalir = new TextureRegionDrawable(new TextureRegion(texturaBtnSalir));
            ImageButton btnSalir = new ImageButton(trdSalir);
            btnSalir.setPosition((ANCHO/2)-btnSalir.getWidth()/2, ALTO/6);
            btnSalir.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Musica.stop();
                    pantallaInicio.setScreen(new PantallaMenu(pantallaInicio));

                }
            });
            this.addActor(btnSalir);
            Texture texturaBtnContinuar = new Texture("Buttons/tryagain.png");
            TextureRegionDrawable trdContinuar = new TextureRegionDrawable(
                    new TextureRegion(texturaBtnContinuar));
            ImageButton btnContinuar = new ImageButton(trdContinuar);
            btnContinuar.setPosition((ANCHO/2)-btnContinuar.getWidth()/2, ALTO/2);
            btnContinuar.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    pantallaInicio.setScreen(new Play(pantallaInicio));
                    //Gdx.input.setInputProcessor(new ProcesadorDeEntrada()); // No debería crear uno nuevo
                }
            });
            this.addActor(btnContinuar);

            TextButton.TextButtonStyle estilo = new TextButton.TextButtonStyle();
            estilo.fontColor = Color.WHITE;
            estilo.font= new BitmapFont(Gdx.files.internal("fonts/Nevermore.fnt"));
            TextButton btn = new TextButton(String.valueOf(puntos), estilo);
            btn.setPosition((ANCHO/2) -30, (ALTO/2)-10);
            this.addActor(btn);

        }

    }


//__________-GANO-_______________


    private class EscenaGano extends Stage{
        public EscenaGano(Viewport vista, SpriteBatch batch) {
            super(vista, batch);
            Texto score = new Texto();
            Texture fondoPausa = new Texture(Gdx.files.internal("Levels/nivel2.png"));

            Image imgRectangulo = new Image(fondoPausa);
            imgRectangulo.setPosition(0, 0);
            this.addActor(imgRectangulo);
            Texture texturaBtnSalir = new Texture("Buttons/home.png");
            TextureRegionDrawable trdSalir = new TextureRegionDrawable(new TextureRegion(texturaBtnSalir));
            ImageButton btnSalir = new ImageButton(trdSalir);
            btnSalir.setPosition((ANCHO/2)-btnSalir.getWidth(), ALTO/6);
            btnSalir.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Musica.stop();
                    pantallaInicio.setScreen(new PantallaMenu(pantallaInicio));

                }
            });
            this.addActor(btnSalir);

            Texture texturaBtnMedalla = new Texture("medalla-oro.png");
            TextureRegionDrawable trdContinuar = new TextureRegionDrawable(
                    new TextureRegion(texturaBtnMedalla));
            ImageButton btnContinuar = new ImageButton(trdContinuar);
            btnContinuar.setPosition((ANCHO/2)-texturaBtnMedalla.getWidth()/2, ALTO-500);
            btnContinuar.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {

                }
            });
            this.addActor(btnContinuar);

            TextButton.TextButtonStyle estilo = new TextButton.TextButtonStyle();
            estilo.fontColor = Color.WHITE;
            estilo.font= new BitmapFont(Gdx.files.internal("fonts/Nevermore.fnt"));
            TextButton btn = new TextButton(String.valueOf(puntos), estilo);
            btn.setPosition((ANCHO/2) -30, (ALTO/2)-10);
            this.addActor(btn);



        }

    }


}