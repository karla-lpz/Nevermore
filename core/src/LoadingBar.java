import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoadingBar extends ApplicationAdapter{

    SpriteBatch batch;
    Texture img;

    AssetManager assetManager;


    private void loadAssets(){
        assetManager.load("", Texture.class);
    }

    @Override
    public void create(){
        batch = new SpriteBatch();
        img =  assetManager.get("", Texture.class);

        assetManager = new AssetManager();
        this.loadAssets();

    }

    @Override
    public void render(){
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0,0);
        batch.end();
    }

    @Override
    public void dispose(){
        batch.dispose();
        img.dispose();
    }

}
