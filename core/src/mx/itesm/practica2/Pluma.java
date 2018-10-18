package mx.itesm.practica2;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.xml.internal.bind.v2.TODO;

public class Pluma extends Objeto {
    boolean flag = false;
    boolean volando;

    private float vx, vy;

    public void setVy(float vy){
        this.vy = vy;
    }
    public void setVx(float vx){
        this.vx = vx;
    }


    public Pluma(Texture textura, float x, float y) {
        super(textura, x, y);
    }

    public void dibujar(SpriteBatch batch) {
        sprite.draw(batch);
    }

    //TODO revisar en donde se pone pica para ajustar en donde se pone el sprite
    /*
    * El calculo es, donde se pique, en x o y, menos el valor del sprite para alto u ancho y ponerlo en ese lugar
    * hagamos un metodo setPosPicado para cambiar la posicion inicial de mi sprite?? No es tan necesario
    * NEED HELP!!!
    * */
    public void rotar(Pluma pluma, float rotacionX) {
        pluma.sprite.setRotation(rotacionX);
        flag = true;
    }
    //Mober es una propiedad de la flecha
    public void mover(float dt)
    {
        float dx = vx * dt;
        float dy = vy * dt;
        sprite.setPosition(sprite.getX()+ dx, sprite.getY()+dy);
    }


    public void vuelo(float rotacion){
        if(volando == true){
            sprite.setRotation(rotacion);
       }
    }

}
