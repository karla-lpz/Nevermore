package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Pluma extends GameObject {

    private static final FileHandle TEXTURE_PATH = Gdx.files.internal("pluma.png");
    boolean volando;
    private float vx, vy;
    private float velocidad = 800;

    public void setVy(float vy){
        this.vy = vy;
    }

    public void setVx(float vx){

        this.vx = Math.max(vx, -547);
        //this.vx = vx;
    }

    public Pluma(float x, float y) {
        super(new Texture(TEXTURE_PATH), x, y);
    }

    public void dibujar(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void renew(){
        sprite.setPosition(Play.ANCHO/4, 20);
        volando = false;
        sprite.setScale(1);
        sprite.setRotation(360);
    }


    public void rotar(Pluma pluma, float rotacionX) {
        pluma.sprite.setRotation(rotacionX);
    }


    public void mover(float dt, float power)
    {

        if(volando == true)
        {
            float limite_vertical;
            float dx = vx * dt;
            float dy = vy * dt;
            sprite.setPosition(sprite.getX() + dx, sprite.getY() + dy);
            if (volando == true) {
                vuelo(true);
            }
            if (sprite.getY() >= Pantalla.ALTO*1.5){
                renew();
            }
        }

    }

//    public int getPlumas(){
//        return cant_plumas;
//    }
//    public void setPlumas(int nivel){
//        cant_plumas = nivel;
//    }
//    private int cant_plumas;
//public void resetPosition(){
//        volando = false;
//    }

    public void vuelo(boolean volando){
        if(sprite.getScaleX() > 0) {
            sprite.setScale(sprite.getScaleX() - (.015f));
        }else{
            sprite.setScale(0f, 0f);
        }
    }


    public void volar(boolean b) {
        volando = true;
    }

    public float getVelocidad() {
        return velocidad;
    }

}







