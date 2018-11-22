package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Crow extends Enemy {

    private static final String TEXTURE = "Enemies/spritesfinal.png";
    private static final int TILE_HEIGHT = 166;
    private static final int TILE_WIDTH = 342;


    public Crow(float x, float y) {
        super(new Texture(TEXTURE), TILE_WIDTH, TILE_HEIGHT, x, y);
    }
}