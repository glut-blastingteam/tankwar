package core.entity;

import com.badlogic.gdx.math.Vector2;

class Bullet {
    public float speedMax = 750;

    public float radius = 5;
    public Vector2 position = new Vector2();
    public Vector2 velocity = new Vector2();

    public void shootToward(float targetX, float targetY) {
        velocity.set(targetX - position.x, targetY - position.y).nor().scl(Math.min(position.dst(targetX, targetY), speedMax));
    }

    public void update(float deltaTime) {
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        //velocity.scl(1 - (0.98f * deltaTime));
    }

    public Bullet(Vector2 position) {
        this.position = position;
    }
}
