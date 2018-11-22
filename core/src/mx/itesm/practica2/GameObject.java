package mx.itesm.practica2;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class GameObject {
    protected boolean isActive;
    protected Sprite sprite;

    public GameObject(Texture textura, float x, float y){
        this.isActive = true;
        sprite = new Sprite(textura);
        sprite.setPosition(x, y);
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public float getPositionX() {
        return this.sprite.getX();
    }

    public float getPositionY() {
        return this.sprite.getY();
    }

    public float getHeight() {
        return this.sprite.getHeight();
    }

    public float getWidth() {
        return sprite.getWidth();
    }

    public float getScaleX(){
        return sprite.getScaleX();
    }

    public float getScaleY(){
        return sprite.getScaleX();
    }

    public java.lang.Object getRectangle() {
        return sprite.getBoundingRectangle();
    }
}
