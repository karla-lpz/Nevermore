package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;

//TODO: Ajustar tamaño de la fuente (esta muy pequeño)

public class GameOverScreen extends Pantalla {
    private static final int BANNER_WIDTH = 350;
    private static final int BANNER_HEIGHT = 100;

    Pantalla_Inicio game;

    int score, highscore;

    Texture gameOverBanner;
    BitmapFont scoreFont;

    public GameOverScreen(Pantalla_Inicio game, int score){
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
        gameOverBanner = new Texture("game_over.png");
        scoreFont = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        game.batch.draw(gameOverBanner, Gdx.graphics.getWidth() / 3.5f - BANNER_WIDTH / 2,
                Gdx.graphics.getHeight() - BANNER_HEIGHT - 200, 900, 150);

        GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "Score: \n" + score, Color.BLUE, 0, Align.left, false);
        GlyphLayout highscoreLayout = new GlyphLayout(scoreFont, "Highscore: \n" + highscore, Color.BLUE, 0, Align.left, false);
        scoreFont.draw(game.batch, scoreLayout, Gdx.graphics.getWidth() / 2 - scoreLayout.width / 2, Gdx.graphics.getHeight() - BANNER_HEIGHT - 110 * 2);
        scoreFont.draw(game.batch, highscoreLayout, Gdx.graphics.getWidth() / 2 - highscoreLayout.width / 2, Gdx.graphics.getHeight() - BANNER_HEIGHT - scoreLayout.height - 85 * 3);

        GlyphLayout tryAgainLayout = new GlyphLayout(scoreFont, "Try Again");
        GlyphLayout mainMenuLayout = new GlyphLayout(scoreFont, "Main Menu");

        float tryAgainX = Gdx.graphics.getWidth() / 2 - tryAgainLayout.width / 2;
        float tryAgainY = Gdx.graphics.getHeight() / 2 - tryAgainLayout.height / 2;
        float mainMenuX = Gdx.graphics.getWidth() / 2 - mainMenuLayout.width / 2;
        float mainMenuY = Gdx.graphics.getHeight() / 2 - mainMenuLayout.height / 2 - tryAgainLayout.height - 15;

        float touchX = Gdx.input.getX(), touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

        //If try again and main menu is being pressed
        if(Gdx.input.isTouched()){
            //Try Again
            if(touchX > tryAgainX && touchX < tryAgainX + tryAgainLayout.width && touchY > tryAgainY - tryAgainLayout.height && touchY < tryAgainY){
                this.dispose();
                game.batch.end();
                game.setScreen(new PantallaMenu(game));
                return;
            }

            //Main menu
            if(touchX > mainMenuX && touchX < mainMenuX + mainMenuLayout.width && touchY > mainMenuY - mainMenuLayout.height && touchY < mainMenuY){
                this.dispose();
                game.batch.end();
                game.setScreen(new PantallaJuego(game));
                return;
            }
        }

        // Draw buttons
        scoreFont.draw(game.batch, tryAgainLayout, tryAgainX, tryAgainY);
        scoreFont.draw(game.batch, mainMenuLayout, mainMenuX, mainMenuY);

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
