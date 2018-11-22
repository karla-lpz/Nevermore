package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Loading extends GameObject{

    private static final Animation.PlayMode MODE = Animation.PlayMode.NORMAL;
    private static final float FRAME_DURATION = 0.2f;
    private static final float SCALE = 0.01f;

    protected Animation<TextureRegion> animation;
    protected float animationTimer;

    public Loading(Texture tileSheet, int tileWidth, int tileHeight, float x, float y) {
        super(tileSheet, x, y);
        this.animationTimer = 0;
        TextureRegion[][] tiles = TextureRegion.split(tileSheet, tileWidth,tileHeight);

        Array<TextureRegion> sequence = new Array<TextureRegion>();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                sequence.add(tiles[i][j]);
            }
        }

        this.animation = new Animation<TextureRegion>(FRAME_DURATION, sequence, MODE);
        sprite = new Sprite(tiles[0][0]);
        sprite.setPosition(x, y);
        sprite.setScale(SCALE);
    }

    public void animate(SpriteBatch batch) {
        animationTimer += Gdx.graphics.getDeltaTime();
        TextureRegion region = animation.getKeyFrame(animationTimer);
        sprite.setRegion(region);
        sprite.draw(batch);
    }
}
