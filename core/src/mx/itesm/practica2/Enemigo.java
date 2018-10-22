package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Enemigo extends Objeto {
    boolean flag = false;
    boolean volando;

    private int cant_enemigos;
    private float vx, vy;
    private float random;

    private Animation<TextureRegion> animatedSprite;
    private float timerAnimacion;

    public void setVy(float vy){
        this.vy = vy;
    }
    public void setVx(float vx){
        this.vx = vx;
    }

    public Enemigo(Texture textura, float x, float y) {
        super(textura, x, y);
        TextureRegion texturaCompleta = new TextureRegion(textura);
        TextureRegion[][] texturaPersonaje = texturaCompleta.split(166,342);
        animatedSprite = new Animation(0.1f, texturaPersonaje[0][3], texturaPersonaje[0][2], texturaPersonaje[0][1] );
        animatedSprite.setPlayMode(Animation.PlayMode.LOOP);
        timerAnimacion = 0;
        sprite = new Sprite(texturaPersonaje[0][0]);    // QUIETO
        sprite.setPosition(x,y);

    }



    public void dibujar(SpriteBatch batch) {
        timerAnimacion += Gdx.graphics.getDeltaTime();
        // Frame que se dibujará
        TextureRegion region = animatedSprite.getKeyFrame(timerAnimacion);
        batch.draw(region, sprite.getX(), sprite.getY());

    }

    public void mover()
    {
        vuelo(true);
    }

    public int getEnemigos(){
        return cant_enemigos;
    }
    public void setEnemigos(int nivel){
        cant_enemigos = nivel;
    }


    public void vuelo(boolean volando){

        sprite.setScale(sprite.getScaleX() + (.1f));



    }


    public float getAncho() {
        float ancho = sprite.getHeight();
        return ancho;
    }

    public void setScale() {
        sprite.setScale(.2f);
    }

    public float getAlto() {
        float alto = sprite.getHeight();
        return alto;
    }
}



