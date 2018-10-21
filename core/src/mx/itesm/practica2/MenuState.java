/*package mx.itesm.practica2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


public class MenuState extends Pantalla{

    private final Pantalla_Inicio pantallaInicio;
    private Texture background;
    private Texture playbutton;
    private Stage escenaMenu;
    public MenuState(Pantalla_Inicio pantallaInicio) {

        this.pantallaInicio = pantallaInicio;
        background = new Texture("FondoFinal.png");
        playbutton = new Texture("playBtn.png");

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(escenaMenu);
    }
    private void crearEscena() {

        escenaMenu = new Stage(vista);


        //###########################################
        //
        //                 Botones
        //
        //###########################################

        //_____________________Play_____________________________________________________________________

        Texture textBtnPlay = new Texture("playBtn.png");
        TextureRegionDrawable trd = new TextureRegionDrawable(new TextureRegion(textBtnPlay));

        //Imagen Boton oprimido

        Texture textBtnPlayOprimido = new Texture("playBtnPush.png");
        TextureRegionDrawable trdOp = new TextureRegionDrawable(new TextureRegion(textBtnPlayOprimido));

        ImageButton btnPlay = new ImageButton(trd, trdOp);
        //Estamos creando un button con 2 imagenes
        btnPlay.setPosition(ANCHO / 2 - btnPlay.getWidth() / 2, .6f * ALTO);
        //Programar accion del boton
        btnPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                pantallaInicio.setScreen(new Play(pantallaInicio));
            }
        });
    }
    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(playbutton, 0, ALTO/2);
        escenaMenu.draw();
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
}
*/



package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

class MenuStage extends Pantalla{
    private final Pantalla_Inicio pantallaInicio;
    private Sprite sprite;

    private Texture fondo = new Texture("FondoFinal.png");
    private Texture title = new Texture("nevermoreTitulo.png");
    //Contenedor de elementos graficos de la pantalla
    private Stage escenaMenu;


    public MenuStage(Pantalla_Inicio pantallaInicio) {

        this.pantallaInicio = pantallaInicio;
    }
    @Override
    public void show() {
        //Es el primer metodo que se ejecuta
        crearEscena();
        // cargarMusica();
        //_____________________Título_____________________________________________________________________
        sprite = new Sprite(new Texture("nevermoreTitulo.png"));
        sprite.setPosition(ALTO/2,ANCHO/2 );
        //__________________________________________________________________________
        Gdx.input.setInputProcessor(escenaMenu);
    }

    private void crearEscena() {

        escenaMenu = new Stage(vista);


        //###########################################
        //
        //                 Botones
        //
        //###########################################

        //_____________________Play_____________________________________________________________________

        Texture textBtnPlay = new Texture("playBtn.png");
        TextureRegionDrawable trd = new TextureRegionDrawable(new TextureRegion(textBtnPlay));

        //Imagen Boton oprimido

        Texture textBtnPlayOprimido = new Texture("playBtnPush.png");
        TextureRegionDrawable trdOp = new TextureRegionDrawable(new TextureRegion(textBtnPlayOprimido));

        ImageButton btnPlay = new ImageButton(trd, trdOp);
        //Estamos creando un button con 2 imagenes
        btnPlay.setPosition(ANCHO/2-btnPlay.getWidth()/2, .6f*ALTO);
        //Programar accion del boton
        btnPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.graphics.setContinuousRendering(true);
                pantallaInicio.setScreen(new Play(pantallaInicio));
            }
        });


        escenaMenu.addActor(btnPlay);

    }


    @Override
    public void render(float delta) {
        borrarPantalla(0, 0, 0);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        //Dibujar la textura del fondo
        batch.draw(sprite, ALTO/2f,ANCHO/2f);
        batch.draw(fondo, 0, 0);
        batch.draw(title, ALTO / 150.5f, ANCHO / 0.70f);
        batch.end();
        //Cada que cambiamos de pantalla usamos el matrix
        escenaMenu.draw();

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

}
