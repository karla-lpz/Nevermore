package mx.itesm.practica2;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy extends AnimatedGameObject {
    private float vx, vy;
    public void setVy(float vy){
        this.vy = vy;
    }
    public void setVx(float vx){
        this.vx = vx;
    }

    public Enemy(Texture tileSheet, int width, int height, float x, float y) {
        super(tileSheet, width, height, x, y);
    }

    public void die(float escalaX, float escalaY){
        Texture stain = new Texture("manchacuervo.png");
        TextureRegion mancha_anim = new TextureRegion(stain);
        animation = new Animation(.1f, mancha_anim);
        animation.setPlayMode(Animation.PlayMode.NORMAL);
        animationTimer = 10;
        sprite = new Sprite(stain);
        sprite.setScale(escalaX/3, escalaY/3);
        this.deactivate();
    }

    // NOTE: Los dos se mueven de la misma manera, es una ilusion causada por el incremento de la imagen.
    public void mover()
    {
        sprite.setScale(sprite.getScaleX() + (.005f));
        if (sprite.getScaleX() >= 5) {
            this.deactivate();
        }
    }
}