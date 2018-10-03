package core.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import core.entity.TankEntity;
import core.util.MapGenerator;
import core.util.MusicPlayer;


public class GameScreen implements Screen {
    private final TankWarGame game;

    private OrthographicCamera camera;
    private SpriteBatch batch;

    private ShapeRenderer renderer;
    private Stage stage;
    private TankEntity playerTank;
    //private Bullet bullet;

    public GameScreen(final TankWarGame game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1200, 900);

        batch = new SpriteBatch();
        MusicPlayer.playGameStartMusic();

        //renderer = new ShapeRenderer();
        stage = new Stage();
        playerTank = new TankEntity(stage, camera.viewportHeight, camera.viewportWidth);
        stage.addActor(playerTank);
        MapGenerator.generateMap1(stage, camera.viewportHeight, camera.viewportWidth);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        stage.act();
        stage.draw();

    }

    /*private void renderBullet(){
        //发射子弹逻辑
        Bullet bullet = new Bullet(new Vector2(player.x,player.y));
        bullet.update(Gdx.graphics.getDeltaTime());

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(255f, 255f, 255f, 0.0f);
        renderer.circle(bullet.position.x, bullet.position.y, bullet.radius);

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){

            switch (direction){
                case DIRECTION_UP:
                    bullet.shootToward(player.x,camera.viewportHeight);
                    break;
                case DIRECTION_DOWN:
                    bullet.shootToward(player.x,0);
                    break;
                case DIRECTION_LEFT:
                    bullet.shootToward(0,player.y);
                    break;
                case DIRECTION_RIGHT:
                    bullet.shootToward(camera.viewportWidth,player.y);
                    break;
            }
        }
        renderer.end();
    }*/


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
        renderer.dispose();
    }

}
