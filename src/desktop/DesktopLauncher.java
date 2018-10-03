package desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import core.game.TankWarGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "90 tank war - powered by OpenGL";
        config.height = 900;
        config.width = 1200;
        new LwjglApplication(new TankWarGame(), config);
    }
}
