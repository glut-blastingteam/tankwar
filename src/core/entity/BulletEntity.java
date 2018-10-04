package core.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import core.util.Direction;

class BulletEntity extends Actor {
    Texture texture;
    Vector2 position;
    Vector2 target;
    private Direction direction;

    BulletEntity(Direction direction, Vector2 position, Vector2 target) {
        this.position = position;
        this.target = target;
        this.direction = direction;
        texture = new Texture("tankmissile.gif");
    }

    void update(float deltaTime) {
        switch (direction) {
            case DIRECTION_UP:
                position.set(target.x, position.y + 80 * deltaTime);
                break;
            case DIRECTION_DOWN:
                position.set(target.x, position.y - 80 * deltaTime);
                break;
            case DIRECTION_LEFT:
                position.set(position.x - 80 * deltaTime, target.y);
                break;
            case DIRECTION_RIGHT:
                position.set(position.x + 80 * deltaTime, target.y);
                break;
        }
    }
}
