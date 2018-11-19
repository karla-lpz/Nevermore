package mx.itesm.practica2;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Cat extends Enemy {

    private Animation<TextureRegion> animatedSprite;
    private float timerAnimacion;

    public Cat(Texture textura, float x, float y) {
        super( textura, x, y );

        this.isActive = true;
        TextureRegion texturaCompleta = new TextureRegion(textura);
        TextureRegion[][] textureCharacter = texturaCompleta.split(284,624);
        //10 frames
        animatedSprite = new Animation(0.5f, textureCharacter[0][1], textureCharacter[0][2], textureCharacter[0][3], textureCharacter[0][4],
                textureCharacter[0][5], textureCharacter[0][6],textureCharacter[0][7], textureCharacter[0][8],textureCharacter[0][9]);
        animatedSprite.setPlayMode(Animation.PlayMode.LOOP);
        timerAnimacion = 0;
        sprite = new Sprite(textureCharacter[0][0]);    // QUIETO
        sprite.setPosition(x,y);
        sprite.setScale(.01f);
    }
}
