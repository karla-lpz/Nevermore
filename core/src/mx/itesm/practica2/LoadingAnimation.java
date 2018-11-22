package mx.itesm.practica2;

import com.badlogic.gdx.graphics.Texture;

class LoadingAnimation extends Loading{

    private static final String TEXTURE = "loading.PNG";
    private static final int TILE_HEIGHT = 166;
    private static final int TILE_WIDTH = 342;


    public LoadingAnimation(float x, float y) {
        super(new Texture(TEXTURE), TILE_WIDTH, TILE_HEIGHT, x, y);
    }

}
