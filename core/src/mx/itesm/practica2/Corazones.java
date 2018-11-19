package mx.itesm.practica2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static mx.itesm.practica2.Corazones.EstadoCorazon.CUATROCUARTOS;


class Corazones
{
    private Texture texturaCorazon1;
    private Texture texturaCorazon2;
    private Texture texturaCorazon3;
    private Texture texturaCorazonLLENO;
    private Texture texturaCorazon4;

    private EstadoCorazon estado;

    // timer explotando
    float timerExplota = 0;

    private float x, y;   // Posición
    private float ScX, ScY; //Sclae

    public  Corazones(int x, int y) {
        texturaCorazonLLENO = new Texture("hearts/CORA_LLENO.png");
        texturaCorazon1 = new Texture("hearts/CORA2.png");
        texturaCorazon2 = new Texture("hearts/CORA3.png");
        texturaCorazon3 = new Texture("hearts/CORA4.png");
        texturaCorazon4 = new Texture("hearts/CORA5.png");
        estado = CUATROCUARTOS;
        this.x = x;
        this.y = y;

    }

    public void render(SpriteBatch batch, EstadoCorazon estado) {
        switch (estado) {
            case CUATROCUARTOS:
                batch.draw(texturaCorazonLLENO, x, y);

                break;
            case TRESCUARTOS:
                batch.draw(texturaCorazon1, x, y);
                break;
            case DOSCUARTOS:
                batch.draw(texturaCorazon2, x, y);

                break;
            case MUERTO:
                batch.draw(texturaCorazon4, x, y);
                break;
            case UNCUARTO:
                batch.draw(texturaCorazon3, x, y);
                break;

        }
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Texture getTextura() {
        return texturaCorazonLLENO;
    }

    public EstadoCorazon getEstado() {
        return estado;
    }

    public void setEstado(EstadoCorazon estado) {
        this.estado = estado;
    }

    public void BajarVida(EstadoCorazon actualEstado) {
        switch (actualEstado){
            case CUATROCUARTOS:
                estado = EstadoCorazon.TRESCUARTOS;
                break;
            case TRESCUARTOS:
                estado = EstadoCorazon.DOSCUARTOS;
                break;

            case DOSCUARTOS:
                estado = EstadoCorazon.UNCUARTO;
                break;

            case UNCUARTO:
                 estado = EstadoCorazon.MUERTO;
                break;

        }
    }




    public enum EstadoCorazon {
        CUATROCUARTOS,
        TRESCUARTOS,
        DOSCUARTOS,
        UNCUARTO,
        MUERTO
    }
}