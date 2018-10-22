package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;




public class Pluma extends Objeto {
    boolean flag = false;
    boolean volando;
    private int cant_plumas;
    private float vx, vy;

    public void setVy(float vy){
        this.vy = vy;
    }


    public void setVx(float vx){
        this.vx = vx;
    }

    public void resetPosition(){
        if (sprite == null) {

        }
    }



    public Pluma(Texture textura, float x, float y) {
        super(textura, x, y);
    }



    public void dibujar(SpriteBatch batch) {
        sprite.draw(batch);
    }



    public void rotar(Pluma pluma, float rotacionX) {
        pluma.sprite.setRotation(rotacionX);
        flag = true;
    }


    public void mover(float dt, boolean volando, float power)
    {

        if(volando == true)
        {
            float limite_vertical;
            float dx = vx * dt;
            float dy = vy * dt;

            sprite.setPosition(sprite.getX() + dx, sprite.getY() + dy);

            if (volando == true) {
                vuelo(true);
                cant_plumas--;
            }
        }

    }

    public int getPlumas(){
        return cant_plumas;
    }
    public void setPlumas(int nivel){
        cant_plumas = nivel;
    }

    public void vuelo(boolean volando){
        if(sprite.getScaleX() > 0) {
            sprite.setScale(sprite.getScaleX() - (.02f));
        }else{
            sprite.setScale(0f, 0f);
        }

    }

}







