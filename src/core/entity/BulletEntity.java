package core.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

class BulletEntity extends Actor {
    Texture texture;
    Vector2 position;
    Vector2 target;
    private TankEntity.Direction direction;

    BulletEntity(TankEntity.Direction direction, Vector2 position, Vector2 target) {
        this.position = position;
        this.target = target;
        this.direction = direction;
        texture = new Texture("tankmissile.gif");
    }

    void update(float deltaTime) {
        System.out.println(position.x + "--" + position.y);
        switch (direction) {
            case DIRECTION_UP:
                position.set(target.x, position.y + 10000 * deltaTime);
                break;
            case DIRECTION_DOWN:
                position.set(target.x, -100 * deltaTime);
                break;
            case DIRECTION_LEFT:
                position.set(-100 * deltaTime, target.y);
                break;
            case DIRECTION_RIGHT:
                position.set(100 * deltaTime, target.y);
                break;
        }
    }
}
