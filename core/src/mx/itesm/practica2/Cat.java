package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Cat extends Enemy {

//    private Animation<TextureRegion> animatedSprite;
//    private float timerAnimacion;

    public int x,y;
    private Animation animation;
    private float time;
    private TextureRegion [] regionMovimiento;
    private Texture image;
    private TextureRegion actualFrame;

    public Cat(Texture textura, float x, float y) {
        super( textura, x, y );

        //Load image
        image = new Texture(Gdx.files.internal("Enemies/gatosprites.png"));
        TextureRegion [][] tmp = TextureRegion.split(image, image.getWidth() / 10, image.getHeight());

        regionMovimiento = new TextureRegion[10];
        for(int i = 0; i < 10; i ++)regionMovimiento[i] = tmp[0][i];
        animation = new Animation(1, regionMovimiento);
        time = 0f;
//        this.isActive = true;
//        TextureRegion texturaCompleta = new TextureRegion(textura);
//        TextureRegion[][] textureCharacter = texturaCompleta.split(284,624);
//        //10 frames
//        animatedSprite = new Animation(0.5f, textureCharacter[0][9], textureCharacter[0][8], textureCharacter[0][7], textureCharacter[0][6],
//                textureCharacter[0][5], textureCharacter[0][5],textureCharacter[0][3], textureCharacter[0][2],textureCharacter[0][1], textureCharacter[0][0]);
//        animatedSprite.setPlayMode(Animation.PlayMode.LOOP);
//        timerAnimacion = 0;
//        sprite = new Sprite(textureCharacter[0][0]);    // QUIETO
//        sprite.setPosition(x,y);
//        sprite.setScale(.01f);
    }

    public void render(final SpriteBatch batch){
        time += Gdx.graphics.getDeltaTime(); //time passed since last render
        actualFrame = (TextureRegion) animation.getKeyFrame(time, true);
        batch.draw(actualFrame, x, y);
    }
}
