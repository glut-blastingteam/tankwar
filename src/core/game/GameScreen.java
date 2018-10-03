package core.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import core.entity.TankEntity;
import core.util.MapGenerator;
import core.util.MusicPlayer;


public class GameScreen implements Screen {
    private final TankWarGame game;

    private OrthographicCamera camera;
    private SpriteBatch batch;

    private Stage mapComponentStage, bulletStage, tankStage;
    private TankEntity playerTank;

    public GameScreen(final TankWarGame game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.MAP_WIDTH, Config.MAP_HEIGHT);

        batch = new SpriteBatch();
        MusicPlayer.playGameStartMusic();

        mapComponentStage = new Stage();
        bulletStage = new Stage();
        tankStage = new Stage();
        playerTank = new TankEntity(mapComponentStage, bulletStage);
        tankStage.addActor(playerTank);
        MapGenerator.generateMap1(mapComponentStage, Config.MAP_WIDTH, Config.MAP_HEIGHT);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        mapComponentStage.act();
        mapComponentStage.draw();
        bulletStage.act();
        bulletStage.draw();
        tankStage.act();
        tankStage.draw();
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        MusicPlayer.dispose();
        playerTank.dispose();
    }

}
