package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

class PantallaMenu extends Pantalla{
    private final Pantalla_Inicio pantallaInicio;

    private Texture fondo = new Texture("nevermore.png");
    //Contenedor de elementos graficos de la pantalla
    private Stage escenaMenu;


    public PantallaMenu(Pantalla_Inicio pantallaInicio) {

        this.pantallaInicio = pantallaInicio;
    }

    @Override
    public void show() {
        //Es el primer metodo que se ejecuta
        crearEscena();
        Gdx.input.setInputProcessor(escenaMenu);
    }

    private void crearEscena() {
        //Estamos creando una escena
        //asi le ponemos la vista a la escena

        escenaMenu = new Stage(vista);


        //###########################################
        //
        //                 Botones
        //
        //###########################################

        //_____________________Play_____________________________________________________________________

        Texture textBtnPlay = new Texture("play.png");
        TextureRegionDrawable trd = new TextureRegionDrawable(new TextureRegion(textBtnPlay));

        //Imagen Boton oprimido

        Texture textBtnPlayOprimido = new Texture("play1.png");
        TextureRegionDrawable trdOp = new TextureRegionDrawable(new TextureRegion(textBtnPlayOprimido));

        ImageButton btnPlay = new ImageButton(trd, trdOp);
        //Estamos creando un button con 2 imagenes
        btnPlay.setPosition(ANCHO/2-btnPlay.getWidth()/2, .6f*ALTO);
        //Programar accion del boton
        btnPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                    pantallaInicio.setScreen(new Play(pantallaInicio));
            }
        });

        //_______________________Settings____________Boton___________________________
            Texture textBtnSettings = new Texture("setting.png");
            TextureRegionDrawable SettingsTrd2 = new TextureRegionDrawable(new TextureRegion(textBtnSettings));

            Texture textBtnSettingsOp = new Texture("setting2.png");
            TextureRegionDrawable SettingsTrd2Op = new TextureRegionDrawable(new TextureRegion(textBtnSettingsOp));
            ImageButton btnSettings = new ImageButton(SettingsTrd2, SettingsTrd2Op);
            btnSettings.setPosition(ANCHO/2-btnSettings.getWidth()/2, .4f*ALTO);

            btnSettings.addListener(new ClickListener(){
               @Override
               public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    pantallaInicio.setScreen(new PantallaSettings(pantallaInicio)
                    );
                }
            });


        //__________________________________________________________________________



        //______________________Score_______________________________________________

        Texture textBtnScore = new Texture("help1.png");
        TextureRegionDrawable ScoreTrd2 = new TextureRegionDrawable(new TextureRegion(textBtnScore));

        Texture textBtnScoreOp = new Texture("help2.png");
        TextureRegionDrawable ScoreTrd2Op = new TextureRegionDrawable(new TextureRegion(textBtnScoreOp));
        ImageButton btnScore = new ImageButton(ScoreTrd2, ScoreTrd2Op);
        btnScore.setPosition(ANCHO/2-btnScore.getWidth()/2, .2f*ALTO);
        btnScore.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
               pantallaInicio.setScreen(new PantallaHelp(pantallaInicio));
            }
        });
     //_______________________________________________________________________________


        escenaMenu.addActor(btnPlay);
        escenaMenu.addActor(btnSettings);
        escenaMenu.addActor(btnScore);


    }



    @Override
    public void render(float delta) {
        borrarPantalla(0, 0, 0);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        //Dibujar la textura del fondo
        batch.draw(fondo, 0, 0);
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
