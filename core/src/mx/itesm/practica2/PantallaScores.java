package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PantallaScores extends Pantalla {
    private final Pantalla_Inicio pantallaInicio;
    private Sprite sprite;

    private Texture fondo = new Texture("Background/FondoFinal.png");
    private Texture btnRegreso = new Texture( "Buttons/back.png");
    private Texture title = new Texture("Titles/scorestitulo.png");
    private Texture firstPlace = new Texture("medalla-oro.png");
    private Texture secondPlace = new Texture("medalla-plata.png");
    private Texture thirdPlace = new Texture("medalla-bronce.png");

    private Texto score;
    private Texto fPlaceText;
    private Texto sPlaceText;
    private Texto tPlaceText;
    private Texto fourthPlace;
    private Texto fourthPlaceText;
    private Texto fifthPlace;
    private Texto fifthPlaceText;

    private ArrayList<String> scores;

    public PantallaScores(Pantalla_Inicio pantallaInicio) throws FileNotFoundException {

        this.pantallaInicio = pantallaInicio;
        scores = new ArrayList<String>();
        try {
            //Gdx.app.log( "HOLO", "HOLI" );
            File scoreFile = new File("assets/Scores.txt");
            BufferedReader br = new BufferedReader(new FileReader(scoreFile));
            String readLine;
            while ((readLine = br.readLine()) != null) {
                Gdx.app.log( "HOLO", readLine );
                scores.add(readLine);
            }
        } catch(IOException e) {

        }
    }

    @Override
    public void show() {
        sprite = new Sprite(new Texture("Buttons/back.png"));
        sprite.setPosition(ALTO *.2f,ANCHO *.2f);
        sprite = new Sprite(new Texture("Titles/scorestitulo.png"));
        sprite.setPosition(ALTO / 2, ANCHO / 2);
        Texture btnRegreso = new Texture("Buttons/back.png");
        sprite = new Sprite(new Texture("medalla-oro.png"));
        sprite.setPosition(ALTO / 2, ANCHO / 2);
        sprite = new Sprite(new Texture("medalla-plata.png"));
        sprite.setPosition(ALTO / 2, ANCHO / 2);
        sprite = new Sprite(new Texture("medalla-bronce.png"));
        sprite.setPosition(ALTO / 2, ANCHO / 2);
        createText();
        for (int i = 0; i <= scores.size() - 1; i ++){
            score.mostrarMensaje(batch, scores.get(i), ANCHO / 2, ALTO / 2);
        }
        sprite.setScale(1);
        Gdx.input.setInputProcessor(new ProcesadorDeEntrada());
    }

    private void createText(){
        score = new Texto();
        fPlaceText = new Texto();
        sPlaceText = new Texto();
        tPlaceText = new Texto();
        fourthPlaceText = new Texto();
        fourthPlace = new Texto();
        fifthPlace = new Texto();
        fifthPlaceText = new Texto();
    }

    @Override
    public void render(float delta) {
        borrarPantalla(0, 0, 0);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(fondo, 0, 0);
        batch.draw(btnRegreso, ANCHO - btnRegreso.getWidth() * 1.0f, ALTO - btnRegreso.getHeight() * 1.2f);
        batch.draw(title, ALTO / 150.5f, ANCHO / 0.70f);
        batch.draw(firstPlace, ALTO /160.5f,  ANCHO / 0.85f);
        fPlaceText.mostrarMensaje(batch, "Roberto 100", 40.5f * ALTO / 160.5f , ANCHO / 0.75f);
        batch.draw(secondPlace, ALTO /160.5f,  0.82f * ANCHO / 0.85f);
        sPlaceText.mostrarMensaje(batch, "Hugo 85", 35.5f * ALTO / 160.5f, ANCHO / 0.90f);
        batch.draw(thirdPlace, ALTO /160.5f,  0.64f * ANCHO / 0.85f);
        tPlaceText.mostrarMensaje(batch, "Sergio 77", 37.5f * ALTO / 160.5f, ANCHO / 1.1f);
        fourthPlace.mostrarMensaje(batch, "4", 9.5f  * ALTO / 160.5f, ANCHO / 1.5f);
        fourthPlaceText.mostrarMensaje(batch, "Angeles 61", 37.5f  * ALTO / 160.5f, ANCHO / 1.45f);
        fifthPlace.mostrarMensaje(batch, "5", 9.5f  * ALTO / 160.5f, ANCHO / 1.9f);
        fifthPlaceText.mostrarMensaje(batch, "Alejandra 55", 39.5f  * ALTO / 160.5f, ANCHO / 1.9f);

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
