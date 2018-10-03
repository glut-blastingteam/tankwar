package core.game;

import com.badlogic.gdx.Game;

public class TankWarGame extends Game {


    @Override
    public void create() {

        this.setScreen(new MenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
    }
}
