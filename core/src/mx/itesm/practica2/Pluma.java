package mx.itesm.practica2;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Pluma extends Objeto {

//TODO (entra con el metodo touchUp)EL metodo disparo guarda el ultimo valor de Y que tuvo el sprite, ese valor lo va a usar como potencia y el angulo que lleva
//TODO (entra con el metodo touchDown) EL metodo apuntar es el que le da movimiento a la flecha y se termina de ejecutar cuando el evento de touchUp entra
//TODO Podemos cualcular a donde se dirige la flecha con Teorema de pitagoras.

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
