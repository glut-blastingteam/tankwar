package core.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import core.util.Config;
import core.util.Direction;

public class TankEntity extends Actor {
    private Stage mapComponentStage, bulletStage;
    private float x = Config.INITIAL_PLAYER_X;
    private float y = Config.INITIAL_PLAYER_Y;
    private Direction direction;
    private Texture[] playerDirection;
    private float tankWidth, tankHeight;

    public TankEntity(Stage mapComponentStage, Stage bulletStage) {
        playerDirection = new Texture[]{
                new Texture("player/p1tankU.gif"),
                new Texture("player/p1tankD.gif"),
                new Texture("player/p1tankL.gif"),
                new Texture("player/p1tankR.gif")
        };
        tankHeight = playerDirection[0].getHeight();
        tankWidth = playerDirection[0].getWidth();
        this.mapComponentStage = mapComponentStage;
        this.bulletStage = bulletStage;
        direction = Direction.DIRECTION_UP;
        // 注意必须使用InputProcessor，Gdx.input.isKeyPressed会因为快速渲染而导致输入多次
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.SPACE) {
                    addBulletIntoStage();
                }
                return true;
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        for (Actor b : bulletStage.getActors()) {
            BulletEntity bullet = (BulletEntity) b;
            // 如果子弹射中地图物体,则移除子弹和地图物体
            MapEntity m = hitObject(bullet.position.x, bullet.position.y);
            if (m != null) {
                bullet.remove();
                if (m.hitAndCheck()) {
                    m.remove();
                }
                break;
            }
            // 如果子弹位于地图范围内则更新子弹位置否则移除子弹
            if (Math.abs(((BulletEntity) b).position.y - ((BulletEntity) b).target.y) > 0 ||
                    Math.abs(((BulletEntity) b).position.x - ((BulletEntity) b).target.x) > 0) {
                ((BulletEntity) b).update(delta);
            } else {
                b.remove();
            }
        }


        //四方移动逻辑
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            direction = Direction.DIRECTION_UP;
            if (isCollided(x, y + 200 * Gdx.graphics.getDeltaTime())) {
                return;
            }
            y += 200 * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (isCollided(x, y - 200 * Gdx.graphics.getDeltaTime())) {
                return;
            }
            direction = Direction.DIRECTION_DOWN;
            y -= 200 * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            direction = Direction.DIRECTION_LEFT;
            if (isCollided(x - 200 * Gdx.graphics.getDeltaTime(), y)) {
                return;
            }
            x -= 200 * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            direction = Direction.DIRECTION_RIGHT;
            if (isCollided(x + 200 * Gdx.graphics.getDeltaTime(), y)) {
                return;
            }
            x += 200 * Gdx.graphics.getDeltaTime();
        }

        ensureWithinMap();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        for (Actor b : bulletStage.getActors()) {
            batch.draw(((BulletEntity) b).texture,
                    ((BulletEntity) b).position.x, ((BulletEntity) b).position.y);

        }
        batch.draw(playerDirection[direction.ordinal()], x, y);
    }

    /**
     * 碰撞检测
     *
     * @return 是否碰撞
     */
    private boolean isCollided(float x, float y) {
        for (Actor a : mapComponentStage.getActors()) {
            MapEntity b = (MapEntity) a;
            switch (direction) {
                case DIRECTION_UP:
                    if (b.isCollided(x, y + tankHeight) || b.isCollided(x + tankWidth, y + tankHeight)) {
                        ensureWithinMap();
                        return true;
                    }
                    break;
                case DIRECTION_DOWN: {
                    if (b.isCollided(x, y) || b.isCollided(x + tankWidth, y)) {
                        ensureWithinMap();
                        return true;
                    }
                    break;
                }
                case DIRECTION_RIGHT:
                    if (b.isCollided(x + tankWidth, y) || b.isCollided(x + tankWidth, y + tankHeight)) {
                        ensureWithinMap();
                        return true;
                    }
                    break;
                case DIRECTION_LEFT:
                    if (b.isCollided(x, y) || b.isCollided(x, y + tankHeight)) {
                        ensureWithinMap();
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    /**
     * 子弹是否射中地图物体
     *
     * @param x 子弹x
     * @param y 子弹y
     * @return 射中返回地图物体, 否则返回null
     */
    private MapEntity hitObject(float x, float y) {
        for (Actor mapComponentActor : mapComponentStage.getActors()) {
            MapEntity mapComponent = (MapEntity) mapComponentActor;
            if (mapComponent.isCollided(x, y)) {
                return mapComponent;
            }
        }
        return null;
    }

    /**
     * 修正无效移动确保坦克在地图范围内
     */
    private void ensureWithinMap() {
        if (x < 0) {
            x = 0;
        }
        if (x > Config.MAP_WIDTH - tankWidth) {
            x = Config.MAP_WIDTH - tankWidth;
        }
        if (y < 0) {
            y = 0;
        }
        if (y > Config.MAP_HEIGHT - tankHeight) {
            y = Config.MAP_HEIGHT - tankHeight;
        }
    }

    /**
     * 释放资源
     */
    public void dispose() {
        for (Texture t : playerDirection) {
            t.dispose();
        }
    }

    /**
     * 创建子弹
     */
    private void addBulletIntoStage() {
        switch (direction) {
            case DIRECTION_UP:
                bulletStage.addActor(new BulletEntity(
                        Direction.DIRECTION_UP,
                        new Vector2(x + tankWidth / 2 - 8, y + tankHeight),
                        new Vector2(x + tankWidth / 2 - 8, Config.MAP_HEIGHT)));
                break;
            case DIRECTION_DOWN:
                bulletStage.addActor(new BulletEntity(
                        Direction.DIRECTION_DOWN,
                        new Vector2(x + tankWidth / 2 - 8, y),
                        new Vector2(x + tankWidth / 2 - 8, 0)));
                break;
            case DIRECTION_LEFT:
                bulletStage.addActor(new BulletEntity(
                        Direction.DIRECTION_LEFT,
                        new Vector2(x, y + tankHeight / 2 - 8),
                        new Vector2(0, y + tankHeight / 2 - 8)));
                break;
            case DIRECTION_RIGHT:
                bulletStage.addActor(new BulletEntity(
                        Direction.DIRECTION_RIGHT,
                        new Vector2(x + tankWidth, y + tankHeight / 2 - 8),
                        new Vector2(Config.MAP_WIDTH, y + tankHeight / 2 - 8)));
                break;
        }
    }
}
