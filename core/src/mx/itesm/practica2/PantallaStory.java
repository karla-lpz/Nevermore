package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class PantallaStory extends  Pantalla{

    private final Pantalla_Inicio pantallaInicio;

    private ArrayList<Texture> story = new ArrayList<Texture>();

    private Texture story1 = new Texture("HISTORIA1.png");
    private Texture story2 = new Texture("HISTORIA2.png");
    private Texture story3 = new Texture("HISTORIA3.png");
    private Texture story4 = new Texture("HISTORIA4.png");
    private Texture story5 = new Texture("HISTORIA5.png");
    private Texture btnRegreso = new Texture("back.png");
    private Texture btnNext = new Texture( "continueBtn.png");
    private Texture btnPrev = new Texture( "back.png");

    private int page = 0;
    private float xPointer = ANCHO;

    private Sprite sprite;

    public PantallaStory(Pantalla_Inicio pantallaInicio){

        this.pantallaInicio = pantallaInicio;
        story.add(story1);
        story.add(story2);
        story.add(story3);
        story.add(story4);
        story.add(story5);
    }
    @Override
    public void show() {
        sprite = new Sprite(new Texture("continueBtn.png"));
        sprite.setPosition(ALTO *.2f,ANCHO *.2f);
        sprite = new Sprite(new Texture("back.png"));
        sprite.setPosition(ALTO *.2f,ANCHO *.2f);
        sprite = new Sprite(new Texture("back.png"));
        sprite.setPosition(ALTO *.2f,ANCHO *.2f);
        Gdx.input.setInputProcessor(new PantallaStory.ProcesadorDeEntrada());
    }

    @Override
    public void render(float delta) {
        borrarPantalla(0, 0, 0);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(story.get(page), 0, 0);
        batch.draw(btnRegreso, ANCHO - btnRegreso.getWidth() * 1.0f, ALTO - btnRegreso.getHeight() * 1.2f);
        batch.draw( btnNext, ANCHO - btnNext.getWidth() ,  0.60f * ALTO / 4 - btnNext.getHeight());
        batch.draw( btnPrev, 0 ,  0.60f * ALTO / 4 - btnNext.getHeight());
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
            float x = ANCHO- btnNext.getWidth()*1.0f;
            float y = ALTO- btnNext.getWidth()*1.2f;
            float anchoBtn = btnNext.getWidth();
            float altoBtn = btnNext.getWidth();
            if(v.x >= x && v.x <= x + anchoBtn && v.y >= y && v.y <= y + altoBtn){
                pantallaInicio.setScreen(new PantallaMenu(pantallaInicio) );
            }
            float xPrev = 0;
            float yPrev = 0.60f * ALTO / 4 - btnNext.getHeight();
            float anchoPrev = btnPrev.getWidth();
            float altoPrev = btnPrev.getWidth();
            if(v.x >= xPrev && v.x <= xPrev + anchoPrev && v.y >= yPrev && v.y <= yPrev + altoPrev){
                if (page != 0){
                    page --;
                }
            }
            float xNext = ANCHO- btnNext.getWidth();
            float yNext = 0.60f * ALTO / 4 - btnNext.getHeight();
            float anchoNext = btnNext.getWidth();
            float altoNext = btnNext.getWidth();
            if(v.x >= xNext && v.x <= xNext + anchoNext && v.y >= yNext && v.y <= yNext + altoNext){
                if(page != story.size() - 1){
                    page ++;
                }
            }
            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) { return true; }

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
