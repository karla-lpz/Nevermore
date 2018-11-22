package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Align;

//TODO: Ajustar tamaño de la fuente (esta muy pequeño)

public class GameOverScreen extends Pantalla {
    private static final int BANNER_WIDTH = 350;
    private static final int BANNER_HEIGHT = 100;

    private Texture tryAgain = new Texture("Buttons/tryagain.png");
    private Texture homeBtn = new Texture("Buttons/home2.png");

    private final Pantalla_Inicio pantallaInicio;

    private Texture fnd = new Texture("Levels/nivel2.png");

    private Sprite sprite;

    Pantalla_Inicio game;

    int score, highscore;

    Texture gameOverBanner;
    BitmapFont scoreFont;

    public GameOverScreen(Pantalla_Inicio game, int score, Pantalla_Inicio pantallaInicio){
        this.pantallaInicio = pantallaInicio;
        this.game = game;
        this.score = score;

        //Get highscore from saved file
        Preferences prefs = Gdx.app.getPreferences("Nevermore");
        this.highscore = prefs.getInteger("highscore", 0);

        //Check if score beats highscore
        if (score > highscore){
            prefs.putInteger("highscore", score);
            prefs.flush();
        }

        //Load textures and fonts
        gameOverBanner = new Texture("Titles/game_over.png");
        scoreFont = new BitmapFont(Gdx.files.internal("fonts/Nevermore.fnt"));
    }

    @Override
    public void show() {
        sprite = new Sprite(new Texture("Levels/nivel2.png"));
        sprite = new Sprite(new Texture("Buttons/tryagain.png"));
        sprite.setPosition(ALTO *.2f,ANCHO *.2f);
        sprite = new Sprite(new Texture("Buttons/home2.png"));
        sprite.setPosition(ALTO *.2f,ANCHO *.2f);
        sprite.setPosition(0,0);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT);
        //game.batch.setProjectionMatrix(camara.combined);
        game.batch.begin();

        //Buttons
        game.batch.draw(homeBtn, ANCHO / 1.5f, ALTO / 2);
        game.batch.draw(tryAgain, ANCHO / 2, ALTO / 1.5f);

        //game.batch.draw(fnd, 0, 0);
        game.batch.draw(gameOverBanner, Gdx.graphics.getWidth() / 3.5f - BANNER_WIDTH / 2,
                Gdx.graphics.getHeight() - BANNER_HEIGHT - 200, 900, 150);

        GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "Score: \n" + score, Color.WHITE, 0, Align.left, false);
        GlyphLayout highscoreLayout = new GlyphLayout(scoreFont, "Highscore: \n" + highscore, Color.WHITE, 0, Align.left, false);
        scoreFont.draw(game.batch, scoreLayout, Gdx.graphics.getWidth() / 2 - scoreLayout.width / 2, Gdx.graphics.getHeight() - BANNER_HEIGHT - 110 * 2);
        scoreFont.draw(game.batch, highscoreLayout, Gdx.graphics.getWidth() / 2 - highscoreLayout.width / 2, Gdx.graphics.getHeight() - BANNER_HEIGHT - scoreLayout.height - 85 * 3);

        game.batch.end();
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
