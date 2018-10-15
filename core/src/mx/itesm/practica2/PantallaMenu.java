package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

class PantallaMenu extends Pantalla{
    private final Pantalla_Inicio pantallaInicio;
    private Sprite sprite;

    private Texture fondo = new Texture("FondoFinal.png");
    //Contenedor de elementos graficos de la pantalla
    private Stage escenaMenu;


    public PantallaMenu(Pantalla_Inicio pantallaInicio) {

        this.pantallaInicio = pantallaInicio;
    }
    Music music = Gdx.audio.newMusic(Gdx.files.internal("soundGame.mp3"));

    @Override
    public void show() {
        //Es el primer metodo que se ejecuta
        crearEscena();
        //music.play();
        //_____________________Título_____________________________________________________________________
        sprite = new Sprite(new Texture("nevermoreTitulo.png"));
        sprite.setPosition(ALTO/2f,ANCHO/2f );
        //__________________________________________________________________________
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
                    pantallaInicio.setScreen(new Play(pantallaInicio));
            }
        });

        //_______________________Scores____________Boton___________________________
            Texture textBtnScores = new Texture("scoresBtn.png");
            TextureRegionDrawable ScoresTrd2 = new TextureRegionDrawable(new TextureRegion(textBtnScores));

            Texture textBtnScoresOp = new Texture("scoresBtnPush.png");
            TextureRegionDrawable ScoresTrd2Op = new TextureRegionDrawable(new TextureRegion(textBtnScoresOp));
            ImageButton btnScores = new ImageButton(ScoresTrd2, ScoresTrd2Op);
            btnScores.setPosition(ANCHO/2-btnScores.getWidth()/2, .4f*ALTO);

            btnScores.addListener(new ClickListener(){
               @Override
               public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    pantallaInicio.setScreen(new PantallaScores(pantallaInicio)
                    );
                }
            });


        //__________________________________________________________________________



        //______________________Help_______________________________________________

        Texture textBtnHelp = new Texture("helpBtn.png");
        TextureRegionDrawable HelpTrd2 = new TextureRegionDrawable(new TextureRegion(textBtnHelp));

        Texture textBtnHelpOp = new Texture("helpBtnPush.png");
        TextureRegionDrawable HelpTrd2Op = new TextureRegionDrawable(new TextureRegion(textBtnHelpOp));
        ImageButton btnHelp = new ImageButton(HelpTrd2, HelpTrd2Op);
        btnHelp.setPosition(ANCHO/2-btnHelp.getWidth()/2, .2f*ALTO);
        btnHelp.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
               pantallaInicio.setScreen(new PantallaHelp(pantallaInicio));
            }
        });


        //__________________________________________________________________________



        //______________________Story_______________________________________________
        
        Texture textBtnStory = new Texture("libroBtn.png");
        TextureRegionDrawable StoryTrd2 = new TextureRegionDrawable(new TextureRegion(textBtnStory));

        Texture textBtnStoryOp = new Texture("libroBtn.png");
        TextureRegionDrawable StoryTrd2Op = new TextureRegionDrawable(new TextureRegion(textBtnStoryOp));
        ImageButton btnStory = new ImageButton(StoryTrd2, StoryTrd2Op);
        btnStory.setPosition(0, 0);
        btnStory.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                pantallaInicio.setScreen(new PantallaHelp(pantallaInicio));
            }
        });

        //__________________________________________________________________________


        //______________________Settings_______________________________________________

        Texture textBtnSettings = new Texture("engraneBtn.png");
        TextureRegionDrawable SettingsTrd2 = new TextureRegionDrawable(new TextureRegion(textBtnSettings));

        Texture textBtnSettingsOp = new Texture("engraneBtn.png");
        TextureRegionDrawable SettingsTrd2Op = new TextureRegionDrawable(new TextureRegion(textBtnSettingsOp));
        ImageButton btnSettings = new ImageButton(SettingsTrd2, SettingsTrd2Op);
        btnSettings.setPosition(ANCHO-btnSettings.getWidth(), .05f*ALTO);
        btnSettings.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                pantallaInicio.setScreen(new PantallaHelp(pantallaInicio));
            }
        });

     //_______________________________________________________________________________


        escenaMenu.addActor(btnPlay);
        escenaMenu.addActor(btnScores);
        escenaMenu.addActor(btnHelp);
        escenaMenu.addActor(btnStory);
        escenaMenu.addActor(btnSettings);


    }



    @Override
    public void render(float delta) {
        borrarPantalla(0, 0, 0);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        //Dibujar la textura del fondo
        batch.draw(sprite, ALTO/2f,ANCHO/2f);
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
