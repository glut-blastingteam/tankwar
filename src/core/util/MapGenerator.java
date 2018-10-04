package core.util;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import core.entity.MapEntity;
import core.entity.MapEntityType;
import core.game.TankWarGame;

public class MapGenerator {
    public static void generateMap1(TankWarGame g, Stage stage, float width, float height) {
        for (int i = 200; i < height - 200; i += 100) {
            for (int k = 0; k < width; k += 100) {
                stage.addActor(new MapEntity("invariant/walls.gif", k, i, MapEntityType.BRICK));
            }
        }
        MapEntity boss = new MapEntity("symbol.gif", width / 2.0f, 0.0f, MapEntityType.BOOS);
        boss.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (event.getStage() == null) {
                    System.out.println("worse");
                }
                return false;
            }
        });
        stage.addActor(boss);
        stage.addActor(new MapEntity(
                "invariant/steels.gif",
                width / 2.0f - boss.getTextureWidth(), 0.0f,
                MapEntityType.STEEL
        ));
        stage.addActor(new MapEntity(
                "invariant/steels.gif",
                width / 2.0f + boss.getTextureWidth(), 0.0f,
                MapEntityType.STEEL
        ));
        stage.addActor(new MapEntity(
                "invariant/steels.gif",
                width / 2.0f - boss.getTextureWidth(), boss.getTextureHeight(),
                MapEntityType.STEEL
        ));
        stage.addActor(new MapEntity(
                "invariant/steels.gif",
                width / 2.0f, boss.getTextureHeight(),
                MapEntityType.STEEL
        ));
        stage.addActor(new MapEntity(
                "invariant/steels.gif",
                width / 2.0f + boss.getTextureWidth(), boss.getTextureHeight(),
                MapEntityType.STEEL
        ));
    }
}
