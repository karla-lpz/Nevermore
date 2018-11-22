package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
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
    private Texture fondo = new Texture("Background/FondoFinal.png");
    private Texture title = new Texture("Titles/nevermoreTitulo.png");

    private LoadingAnimation loadingAnimation;
    //Contenedor de elementos graficos de la pantalla
    private Stage escenaMenu;

    private LoadingAnimation loading;


    public PantallaMenu(Pantalla_Inicio pantallaInicio) {

        this.pantallaInicio = pantallaInicio;
    }
    Music music = Gdx.audio.newMusic(Gdx.files.internal("music/CANCION_MENU_PRINCIPAL.mp3"));

    public PantallaMenu(Pantalla_Inicio pantallaInicio, LoadingAnimation loading) {
        this.pantallaInicio = pantallaInicio;
        this.loading = loading;
    }

    @Override
    public void show() {
        //Es el primer metodo que se ejecuta
        crearEscena();
        cargarMusica();
        //_____________________Título_____________________________________________________________________
        sprite = new Sprite(new Texture("Titles/nevermoreTitulo.png"));
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

        Texture textBtnPlay = new Texture("Buttons/playBtn.png");
        TextureRegionDrawable trd = new TextureRegionDrawable(new TextureRegion(textBtnPlay));

        //Imagen Boton oprimido

        Texture textBtnPlayOprimido = new Texture("Buttons/playBtnPush.png");
        TextureRegionDrawable trdOp = new TextureRegionDrawable(new TextureRegion(textBtnPlayOprimido));

        ImageButton btnPlay = new ImageButton(trd, trdOp);
        //Estamos creando un button con 2 imagenes
        btnPlay.setPosition(ANCHO/2-btnPlay.getWidth()/2, .5f*ALTO);
        //Programar accion del boton
        btnPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
//                int time = 0;
//                if (time <= 6){
//                    Gdx.gl.glClearColor(0, 0 , 0, 0);
//                    Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT);
//                    loadingAnimation.isActive();
//                    time += 1;
//                }
                Gdx.gl.glClearColor(0, 0 , 0, 0);
                Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT);
//
                pantallaInicio.setScreen(new Play(pantallaInicio));
                music.stop();
            }
        });

        //_______________________Scores____________Boton___________________________
//            Texture textBtnScores = new Texture("Buttons/scoresBtn.png");
//            TextureRegionDrawable ScoresTrd2 = new TextureRegionDrawable(new TextureRegion(textBtnScores));
//
//            Texture textBtnScoresOp = new Texture("Buttons/scoresBtnPush.png");
//            TextureRegionDrawable ScoresTrd2Op = new TextureRegionDrawable(new TextureRegion(textBtnScoresOp));
//            ImageButton btnScores = new ImageButton(ScoresTrd2, ScoresTrd2Op);
//            btnScores.setPosition(ANCHO/2-btnScores.getWidth()/2, .4f*ALTO);
//
//            btnScores.addListener(new ClickListener(){
//               @Override
//               public void clicked(InputEvent event, float x, float y) {
//                    super.clicked(event, x, y);
//                   try {
//                       pantallaInicio.setScreen(new PantallaScores(pantallaInicio));
//                   } catch (FileNotFoundException e) {
//                       e.printStackTrace();
//                   }
//               }
//            });


        //__________________________________________________________________________



        //______________________Help_______________________________________________

        Texture textBtnHelp = new Texture("Buttons/helpBtn.png");
        TextureRegionDrawable HelpTrd2 = new TextureRegionDrawable(new TextureRegion(textBtnHelp));
        Texture textBtnHelpOp = new Texture("Buttons/helpBtnPush.png");
        TextureRegionDrawable HelpTrd2Op = new TextureRegionDrawable(new TextureRegion(textBtnHelpOp));
        ImageButton btnHelp = new ImageButton(HelpTrd2, HelpTrd2Op);
        btnHelp.setPosition(ANCHO/2-btnHelp.getWidth()/2, .3f*ALTO);
        btnHelp.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
               pantallaInicio.setScreen(new PantallaHelp(pantallaInicio));
            }
        });


        //__________________________________________________________________________



        //______________________Story_______________________________________________
        
        Texture textBtnStory = new Texture("Buttons/libroBtn.png");
        TextureRegionDrawable StoryTrd2 = new TextureRegionDrawable(new TextureRegion(textBtnStory));

        Texture textBtnStoryOp = new Texture("Buttons/libroBtn.png");
        TextureRegionDrawable StoryTrd2Op = new TextureRegionDrawable(new TextureRegion(textBtnStoryOp));
        ImageButton btnStory = new ImageButton(StoryTrd2, StoryTrd2Op);
        btnStory.setPosition(0, 0);
        btnStory.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                pantallaInicio.setScreen(new PantallaStory(pantallaInicio));
            }
        });

        //__________________________________________________________________________


        //______________________Settings_______________________________________________

        Texture textBtnSettings = new Texture("Buttons/engraneBtn.png");
        TextureRegionDrawable SettingsTrd2 = new TextureRegionDrawable(new TextureRegion(textBtnSettings));

        Texture textBtnSettingsOp = new Texture("Buttons/engraneBtn.png");
        TextureRegionDrawable SettingsTrd2Op = new TextureRegionDrawable(new TextureRegion(textBtnSettingsOp));
        ImageButton btnSettings = new ImageButton(SettingsTrd2, SettingsTrd2Op);
        btnSettings.setPosition(ANCHO-btnSettings.getWidth(), .05f*ALTO);
        btnSettings.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                pantallaInicio.setScreen(new PantallaSettings(pantallaInicio));
            }
        });

     //_______________________________________________________________________________


        escenaMenu.addActor(btnPlay);
//        escenaMenu.addActor(btnScores);
        escenaMenu.addActor(btnHelp);
        escenaMenu.addActor(btnStory);
        escenaMenu.addActor(btnSettings);


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
        music.dispose();
    }

}
