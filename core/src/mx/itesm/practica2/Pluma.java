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
    public void mover(Pluma pluma){
        float xp = sprite.getX();
        float yp = sprite.getY();

        if(xp>=PantallaJuego.ANCHO-sprite.getWidth() ){
            DX = -DX;
        }

        float xr = pluma.sprite.getX();
        float yr = pluma.sprite.getY();

        if (xp>xr && xp<=xr+pluma.sprite.getWidth() && yp>=yr && yr<=(yr+pluma.sprite.getHeight()-sprite.getHeight())){
            DX = -DX;
        }

        if(yp>=PantallaJuego.ALTO-sprite.getHeight() || yp<=0){
            DY = -DY;
        }
        sprite.setPosition(xp+DX, yp+DY);
    }

}
