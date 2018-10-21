package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;




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
        float limite_vertical;
        float dx = vx * dt;
        float dy = vy * dt;
        sprite.setPosition(sprite.getX()+ dx, sprite.getY()+dy);
        if(volando == true){
            vuelo(true);
        }

        if(sprite.getY() >= 1080 - (power * 10) ){
            vuelo(false);
            limite_vertical = 1000 -(power * 100);
            sprite.setY(limite_vertical);
        }

    }





    public void vuelo(boolean volando){
        sprite.setScale(sprite.getScaleX() - (.02f));
        if(volando == false)
            if(sprite.getScaleX() <= 0)
            {
                sprite.setScale(.0001f);

            }

    }








}
