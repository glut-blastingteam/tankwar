package core.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BaseEntity extends Actor {
    private Texture texture;
    private float x, y;

    public BaseEntity(String resourceName, float x, float y) {
        texture = new Texture(resourceName);
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, x, y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public int getTextureHeight() {
        return texture.getHeight();
    }

    public int getTextureWidth() {
        return texture.getWidth();
    }

    public boolean isCollision(float x, float y) {
        return (x >= this.x && x <= (this.x + getTextureWidth())) && (y >= this.y && y <= (this.y + getTextureHeight()));
    }
}
