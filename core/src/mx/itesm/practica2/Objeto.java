package mx.itesm.practica2;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Objeto {
    protected Sprite sprite;
    public Objeto(Texture textura, float x, float y){
        sprite = new Sprite(textura);
        sprite.setY(y);
        sprite.setX(x);

    }


    public float getPositionX() {
        float positionX = sprite.getX();
        return positionX;
    }

    public float getPositionY() {
        float positionY = sprite.getY();
        return positionY;
    }
}
