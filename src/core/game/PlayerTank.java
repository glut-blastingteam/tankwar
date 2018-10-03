package core.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PlayerTank extends Actor {
    enum Direction{
        UP,DOWN,LEFT,RIGHT
    };
    private Direction direction;
    private Texture[] playerDirection;
    private float x,y;

    public PlayerTank(){
        playerDirection = new Texture[]{
                new Texture("player/p1tankU.gif"),
                new Texture("player/p1tankD.gif"),
                new Texture("player/p1tankL.gif"),
                new Texture("player/p1tankR.gif")
        };
        this.setSize(64f,64f);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(playerDirection[direction.ordinal()],x,y);

    }

    public void setPropertyBeforeDrawing(float x, float y, Direction direction){
        this.x=x;this.y=y;this.direction=direction;
    }
}
