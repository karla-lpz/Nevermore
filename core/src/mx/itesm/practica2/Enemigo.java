package mx.itesm.practica2;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;




public class Enemigo extends Objeto {
    boolean flag = false;
    boolean volando;
    private int cant_enemigos;
    private float vx, vy;
    private float random;

    public void setVy(float vy){
        this.vy = vy;
    }
    public void setVx(float vx){
        this.vx = vx;
    }

    public Enemigo(Texture textura, float x, float y) {
        super(textura, x, y);
    }



    public void dibujar(SpriteBatch batch) {
        sprite.draw(batch);

    }




    public void mover()
    {
        //vuelo(true);
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



