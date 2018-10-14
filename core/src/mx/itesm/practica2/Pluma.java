package mx.itesm.practica2;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Pluma extends Objeto {
    private float DX = 4;
    private float DY = 4;
    public Pluma(Texture textura, float x, float y){
        super(textura, x, y);
    }
    public  void dibujar(SpriteBatch batch){
        sprite.draw(batch);
    }
    //
    public void rotar (Pluma pluma){

    }

}
