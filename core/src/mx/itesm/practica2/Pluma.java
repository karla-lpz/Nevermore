package mx.itesm.practica2;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Pluma extends Objeto {


    boolean flag = false;
    boolean volando;

    public Pluma(Texture textura, float x, float y){
        super(textura, x, y);
    }
    public  void dibujar(SpriteBatch batch){
        sprite.draw(batch);
    }
    //
    public void rotar (Pluma pluma, float rotacionX){
        pluma.sprite.setRotation(rotacionX);

        flag = true;
    }
    public void mover(float potencia, float direccion){
        if(flag) {
            sprite.setPosition(200, potencia);
        }
    }
    public void vuelo(){
        if(volando == true){
            sprite.setRotation(0);
        }
    }

}
