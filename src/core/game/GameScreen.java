package core.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import core.TankWarGame;

public class GameScreen implements Screen {
    enum Direction{
        UP,DOWN,LEFT,RIGHT
    };
    private Direction direction;
    private final TankWarGame game;

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture[] playerDirection;
    private Music gameStartMusic;

    private Rectangle player;
    private ShapeRenderer renderer;
    private Bullet bullet;

    public GameScreen(final TankWarGame game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1200, 900);

        batch = new SpriteBatch();

        playerDirection = new Texture[]{
                new Texture("player/p1tankU.gif"),
                new Texture("player/p1tankD.gif"),
                new Texture("player/p1tankL.gif"),
                new Texture("player/p1tankR.gif")
        };

        gameStartMusic = Gdx.audio.newMusic(Gdx.files.internal("music/start.wav"));
        gameStartMusic.play();
        gameStartMusic.setLooping(false);

        player = new Rectangle();
        player.x = 1200 / 2 - 64;
        player.y = 20;
        player.width = 64;
        player.height = 64;

        bullet = new Bullet();
        renderer = new ShapeRenderer();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        playerMove();
        playerShoot();
        restrictPlayerWithinMap();
    }

    @Override
    public void resize(int i, int i1) { }
    @Override
    public void pause() { }
    @Override
    public void resume() {}
    @Override
    public void hide() { }

    @Override
    public void dispose() {
        batch.dispose();
        for (Texture t : playerDirection) {
            t.dispose();
        }
        gameStartMusic.dispose();
    }

    private void playerShoot(){
        // 如果子弹未初始化
        if(bullet.position.x == 0 && bullet.position.y == 0){
            bullet.position.x = player.x;
            bullet.position.y = player.y;
        }
        bullet.update(Gdx.graphics.getDeltaTime());
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(255f, 255f, 255f, 0.0f);
        renderer.circle(bullet.position.x, bullet.position.y, bullet.radius);

        //发射子弹逻辑
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            switch (direction){
                case UP:
                    bullet.shootToward(player.x,camera.viewportHeight);
                    break;
                case DOWN:
                    bullet.shootToward(player.x,0);
                    break;
                case LEFT:
                    bullet.shootToward(0,player.y);
                    break;
                case RIGHT:
                    bullet.shootToward(camera.viewportWidth,player.y);
                    break;
            }
        }
        renderer.end();
    }

    private void playerMove() {
        //四方移动逻辑
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            direction = Direction.UP;
            batch.begin();
            batch.draw(playerDirection[0], player.x, player.y);
            batch.end();
            player.y += 200 * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            direction = Direction.DOWN;
            batch.begin();
            batch.draw(playerDirection[1], player.x, player.y);
            batch.end();
            player.y -= 200 * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            direction = Direction.LEFT;
            batch.begin();
            batch.draw(playerDirection[2], player.x, player.y);
            batch.end();
            player.x -= 200 * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            direction = Direction.RIGHT;
            batch.begin();
            batch.draw(playerDirection[3], player.x, player.y);
            batch.end();
            player.x += 200 * Gdx.graphics.getDeltaTime();
        }
        //如果不按任何键，初始化一个玩家坦克即可
        else {
            if(direction==null){
                direction = Direction.UP;
            }
            batch.begin();
            batch.draw(playerDirection[direction.ordinal()], player.x, player.y);
            batch.end();
        }
    }

    private void restrictPlayerWithinMap(){
        if(player.x < 0) player.x = 0;
        if(player.x > camera.viewportWidth- player.width) player.x = camera.viewportWidth - player.width;
        if(player.y<0) player.y=0;
        if(player.y>camera.viewportHeight- player.height) player.y = camera.viewportHeight- player.height;
    }
}
