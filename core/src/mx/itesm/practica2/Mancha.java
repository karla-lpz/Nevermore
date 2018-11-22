package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Mancha extends GameObject {
    boolean isActive;
    boolean volando = true;

    private int cant_enemigos;
    private float vx, vy;
    private float random;

    private Animation<TextureRegion> animatedSprite;
    private float timerAnimacion;
    private boolean volar;

    public void setVy(float vy){
        this.vy = vy;
    }
    public void setVx(float vx){
        this.vx = vx;
    }

    public Mancha (Texture textura, float x, float y) {
        super(textura, x, y);
        TextureRegion stainAnimation = new TextureRegion(textura);
        TextureRegion[][] characterTexture = stainAnimation.split(342,166);
        //5 frames
        animatedSprite = new Animation(0.2f, characterTexture[0][1], characterTexture[0][2], characterTexture[0][3], characterTexture[0][4],
                characterTexture[0][5], characterTexture[0][6]);
        animatedSprite.setPlayMode(Animation.PlayMode.NORMAL);
        timerAnimacion = 0;
       //sprite = new Sprite(characterTexture[0][0]);    // QUIETO
        sprite.setPosition(x,y);
        sprite.setScale(.01f);

    }

//    public die(Texture textura, float x, float y) {
//        super(textura, x, y);
//    }



    public void Mancha(Texture texture, float x, float y){
        TextureRegion mancha_anim = new TextureRegion(texture);
        animatedSprite = new Animation(600000f, mancha_anim);
        animatedSprite.setPlayMode(Animation.PlayMode.NORMAL);
        timerAnimacion=0;
        sprite = new Sprite(mancha_anim);
    }
    public float getScaleX(){
        return sprite.getScaleX();
    }

    public float getScaleY(){
        return sprite.getScaleX();
    }

    //TODO: Mueva en X
    //TODO: renew
    // TODO SCALE
    public void dibujar(SpriteBatch batch) {
        timerAnimacion += Gdx.graphics.getDeltaTime();
        // Frame que se dibujará
        TextureRegion region = animatedSprite.getKeyFrame(timerAnimacion);
        //batch.draw(region, sprite.getX(), sprite.getY());
        //Escalar
        sprite.setRegion(region);
        sprite.draw(batch);


    }

    public void scaleAnimetion(SpriteBatch batch){
        //batch.draw(animation, sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight(), sprite.scale(sprite.getScaleX()+ .015), sprite.scale(sprite.getScaleY() + 1.5)) ;
    }

    public void deactivate() {

        this.isActive = false;
    }

    public boolean isActive() {

        return this.isActive;
    }

    public void mover(float dt)
    {

        if(volando == true)
        {
            vuelo(true);

        }

    }



    public void vuelo(boolean volando){
        sprite.setScale(sprite.getScaleX() + (.005f));
        if (sprite.getScaleX() >= 5) {
            isActive = false;
        }

    }


    public float getAncho() {
        float ancho = sprite.getWidth();
        return ancho;
    }

    public void setScale() {
        sprite.setScale(.2f);
    }

    public float getAlto() {
        float alto = sprite.getHeight();
        return alto;
    }

    public void setVolar(boolean volar) {
        this.volar = volar;
    }
}