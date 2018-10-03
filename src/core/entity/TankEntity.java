package core.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;


enum Direction {
    DIRECTION_UP, DIRECTION_DOWN, DIRECTION_LEFT, DIRECTION_RIGHT
}

public class TankEntity extends Actor {
    private float mapWidth, mapHeight;
    private Stage stage;
    private float x = 1200 / 2 - 64 - 64;
    private float y = 20;
    private Direction direction;
    private Texture[] playerDirection;
    private float tankWidth, tankHeight;

    public TankEntity(Stage stage, float mapHeight, float mapWidth) {
        playerDirection = new Texture[]{
                new Texture("player/p1tankU.gif"),
                new Texture("player/p1tankD.gif"),
                new Texture("player/p1tankL.gif"),
                new Texture("player/p1tankR.gif")
        };
        tankHeight = playerDirection[0].getHeight();
        tankWidth = playerDirection[0].getWidth();
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.stage = stage;
        direction = Direction.DIRECTION_UP;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        for (Actor a : stage.getActors()) {
            if (a instanceof BaseEntity) {
                BaseEntity b = (BaseEntity) a;
                switch (direction) {
                    case DIRECTION_UP:
                        if (b.isCollision(x, y + tankHeight) || b.isCollision(x + tankWidth, y + tankHeight)) {
                            y -= 1;
                            System.out.println("u");
                            amendPositionWithinMap();
                            return;
                        }
                        break;
                    case DIRECTION_DOWN: {
                        if (b.isCollision(x, y) || b.isCollision(x + tankWidth, y)) {
                            y += 1;
                            System.out.println("d");
                            amendPositionWithinMap();
                            return;
                        }
                        break;
                    }
                    case DIRECTION_RIGHT:
                        if (b.isCollision(x + tankWidth, y) || b.isCollision(x + tankWidth, y + tankHeight)) {
                            x -= 1;
                            System.out.println("r");
                            amendPositionWithinMap();
                            return;
                        }
                        break;
                    case DIRECTION_LEFT:
                        if (b.isCollision(x, y) || b.isCollision(x, y + tankHeight)) {
                            x += 1;
                            System.out.println("l");
                            amendPositionWithinMap();
                            return;
                        }
                        break;
                }
            }
        }


        //四方移动逻辑
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            direction = Direction.DIRECTION_UP;
            batch.draw(playerDirection[0], x, y);
            y += 200 * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            direction = Direction.DIRECTION_DOWN;
            batch.draw(playerDirection[1], x, y);
            y -= 200 * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            direction = Direction.DIRECTION_LEFT;
            batch.draw(playerDirection[2], x, y);
            x -= 200 * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            direction = Direction.DIRECTION_RIGHT;
            batch.draw(playerDirection[3], x, y);
            x += 200 * Gdx.graphics.getDeltaTime();
        } else {
            batch.draw(playerDirection[direction.ordinal()], x, y);
        }


        amendPositionWithinMap();
    }

    private void amendPositionWithinMap() {
        if (x < 0)
            x = 0;
        if (x > mapWidth - tankWidth)
            x = mapWidth - tankWidth;
        if (y < 0)
            y = 0;
        if (y > mapHeight - tankHeight)
            y = mapHeight - tankHeight;
    }

    public void dispose() {
        for (Texture t : playerDirection) {
            t.dispose();
        }
    }
}
