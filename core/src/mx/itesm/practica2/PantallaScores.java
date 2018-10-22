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

    private Texture fondo = new Texture("FondoFinal.png");
    private Texture btnRegreso = new Texture( "back.png");
    private Texture title = new Texture("scorestitulo.png");

    private Texto score;

    private ArrayList<String> scores;

    public PantallaScores(Pantalla_Inicio pantallaInicio) throws FileNotFoundException {

        this.pantallaInicio = pantallaInicio;
        scores = new ArrayList<String>();
        try {
            Gdx.app.log( "HOLO", "HOLI" );
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
        sprite = new Sprite(new Texture("back.png"));
        sprite.setPosition(ALTO *.2f,ANCHO *.2f);
        sprite = new Sprite(new Texture("scorestitulo.png"));
        sprite.setPosition(ALTO / 2, ANCHO / 2);
        Texture btnRegreso = new Texture("back.png");
        score = new Texto();
        for (int i = 0; i <= scores.size() - 1; i ++){
         score.mostrarMensaje(batch, scores.get(i), ANCHO / 2, ALTO / 2);
        }
        Gdx.input.setInputProcessor(new ProcesadorDeEntrada());
    }

    @Override
    public void render(float delta) {
        borrarPantalla(0, 0, 0);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(fondo, 0, 0);
        batch.draw(btnRegreso, ANCHO - btnRegreso.getWidth() * 1.0f, ALTO - btnRegreso.getHeight() * 1.2f);
        batch.draw(title, ALTO / 150.5f, ANCHO / 0.70f);

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
