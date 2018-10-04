package core.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MapEntity extends Actor {
    private int solidness;
    private Texture texture;
    private float x, y;

    public MapEntity(String resourceName, float x, float y, int solidness) {
        this.texture = new Texture(resourceName);
        this.x = x;
        this.y = y;
        this.solidness = solidness;
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

    boolean isCollided(float x, float y) {
        return (x >= this.x && x <= (this.x + getTextureWidth())) && (y >= this.y && y <= (this.y + getTextureHeight()));
    }


    boolean hitAndCheck() {
        return --solidness == 0;
    }

    public static class MapEntityType {
        public static final int BRICK = 1;
        public static final int STEEL = 4;
        public static final int BOOS = 1;
    }

}
