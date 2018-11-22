package mx.itesm.practica2;

import com.badlogic.gdx.graphics.Texture;

public class Cat extends Enemy {

    private static final String TEXTURE = "Enemies/gatosprites.png";
    private static final Texture TILES_SHEET = new Texture(TEXTURE);
    private static final int TILE_HEIGHT = TILES_SHEET.getHeight();
    private static final int TILE_WIDTH = TILES_SHEET.getWidth() / 10;

    public Cat(float x, float y) {
        super(TILES_SHEET, TILE_WIDTH, TILE_HEIGHT, x, y);
    }
}
