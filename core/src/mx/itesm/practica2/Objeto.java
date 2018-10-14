package mx.itesm.practica2;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Objeto {
    protected Sprite sprite;
    public Objeto(Texture textura, float x, float y){
        sprite = new Sprite(textura);
        sprite.setPosition(x, y);
    }
}
