package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public class PantallaSettings extends Pantalla {
    private final Pantalla_Inicio pantallaInicio;

    private Sprite sprite;

    private Texture fondo = new Texture("Background/FondoFinal.png");
    private Texture btnRegreso = new Texture( "Buttons/back.png");
    private Texture aboutUs = new Texture("Buttons/aboutus.png");
    private Texture creditsBtn = new Texture( "Buttons/creditsBtn.png");
    private Texture title = new Texture("Titles/settingstitulo.png");
    private Texture Vol = new Texture("volumenon.png");
    private Texture NoVol = new Texture("volumenoff.png");
    private Boolean hayMusica = true;

    Music music = Gdx.audio.newMusic(Gdx.files.internal("music/CANCION_MENU_PRINCIPAL.mp3"));

    public PantallaSettings(Pantalla_Inicio pantallaInicio) {

        this.pantallaInicio = pantallaInicio;
        Music music = Gdx.audio.newMusic(Gdx.files.internal("music/CANCION_MENU_PRINCIPAL.mp3"));
    }

    @Override
    public void show() {
        cargarMusica();
        sprite = new Sprite(new Texture("Buttons/back.png"));
        sprite.setPosition(ALTO *.2f,ANCHO *.2f);
        sprite = new Sprite(new Texture("Titles/scorestitulo.png"));
        sprite.setPosition(ALTO / 2, ANCHO / 2);
        sprite = new Sprite(new Texture("Buttons/creditsBtn.png"));
        sprite.setPosition(ALTO / 2,ANCHO / 2);
        sprite = new Sprite(new Texture("volumenon.png"));
        sprite.setPosition(ALTO / 2,ANCHO / 2);
        sprite = new Sprite(new Texture("volumenoff.png"));
        sprite.setPosition(ALTO / 2,ANCHO / 2);
        Gdx.input.setInputProcessor(new ProcesadorDeEntrada());

        Preferences preferencias = Gdx.app.getPreferences("settings");
        hayMusica = preferencias.getBoolean("musica", true);

    }

    @Override
    public void render(float delta) {
        borrarPantalla(0, 0, 0);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(fondo, 0, 0);
        batch.draw(btnRegreso, ANCHO - btnRegreso.getWidth() * 1.0f, ALTO - btnRegreso.getHeight() * 1.2f);
        if(hayMusica == true){
            batch.draw(Vol, (ANCHO/2) -50, (ALTO /2));


        }else{
            batch.draw(NoVol, (ANCHO/2)-50, (ALTO /2));

        }
        batch.draw(creditsBtn,  0.50f * ANCHO / 2 , 0.07f * ALTO / 2);
        batch.draw(title, ALTO / 150.5f, ANCHO / 0.70f);
        batch.end();

    }

    private void cargarMusica() {
        AssetManager manager = new AssetManager();
        manager.load("music/CANCION_MENU_PRINCIPAL.mp3", Music.class);
        manager.finishLoading();
        music = manager.get("music/CANCION_MENU_PRINCIPAL.mp3");
        music.setLooping(true);
        music.play();
        music.setLooping(true);
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

            float xVol = ANCHO/2;
            float yVol = ALTO/5;
            float anchoVol = creditsBtn.getWidth();
            float altoVol = creditsBtn.getHeight();
            if(v.x >= ANCHO/2 && v.x <= (ANCHO/2)+Vol.getWidth() && v.y >= ALTO/2 && v.y <= (ALTO/2) + Vol.getWidth()){
                Preferences preferencias = Gdx.app.getPreferences("settings");
                //preferencias.putBoolean("musica", false);
                //preferencias.flush();
                if(hayMusica==true){
                    hayMusica = false;
                    preferencias.putBoolean("musica", true);
                    preferencias.flush();


                }else {
                    preferencias.putBoolean("musica", true);
                    preferencias.flush();
                    hayMusica = true;
                }
            }
            float xCredits = 0.50f * ANCHO / 2;
            float yCredits = 0.07f * ALTO / 2;
            float anchoCredits = creditsBtn.getWidth();
            float altoCredits = creditsBtn.getWidth();
            if(v.x >= xCredits && v.x <= xCredits + anchoCredits && v.y >= yCredits && v.y <= yCredits + altoCredits){

                pantallaInicio.setScreen(new PantallaCredits(pantallaInicio));

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