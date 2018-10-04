package desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import core.game.TankWarGame;
import core.util.Config;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "90 tank war - powered by OpenGL";
        config.height = Config.MAP_HEIGHT;
        config.width = Config.MAP_WIDTH;
        new LwjglApplication(new TankWarGame(), config);
    }
}
