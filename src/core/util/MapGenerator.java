package core.util;

import com.badlogic.gdx.scenes.scene2d.Stage;
import core.entity.MapEntity;
import core.entity.MapEntity.MapEntityType;
import core.game.TankWarGame;

public class MapGenerator {
    public static void generateMap1(TankWarGame g, Stage mapStage, float width, float height) {
        for (int i = 200; i < height - 200; i += 100) {
            for (int k = 0; k < width; k += 100) {
                if ((k / 100) % 2 == 0) {
                    mapStage.addActor(new MapEntity(g, "invariant/walls.gif", k, i, MapEntityType.BRICK));
                } else {
                    mapStage.addActor(new MapEntity(g, "invariant/steels.gif", k, i, MapEntityType.STEEL));
                }
            }
        }
        MapEntity boss = new MapEntity(g, "symbol.gif", width / 2.0f, 0.0f, MapEntityType.BOOS);
        mapStage.addActor(boss);
        mapStage.addActor(new MapEntity(g,
                "invariant/steels.gif",
                width / 2.0f - boss.getTextureWidth(), 0.0f,
                MapEntityType.STEEL
        ));
        mapStage.addActor(new MapEntity(g,
                "invariant/steels.gif",
                width / 2.0f + boss.getTextureWidth(), 0.0f,
                MapEntityType.STEEL
        ));
        mapStage.addActor(new MapEntity(g,
                "invariant/steels.gif",
                width / 2.0f - boss.getTextureWidth(), boss.getTextureHeight(),
                MapEntityType.STEEL
        ));
        mapStage.addActor(new MapEntity(g,
                "invariant/steels.gif",
                width / 2.0f, boss.getTextureHeight(),
                MapEntityType.STEEL
        ));
        mapStage.addActor(new MapEntity(g,
                "invariant/steels.gif",
                width / 2.0f + boss.getTextureWidth(), boss.getTextureHeight(),
                MapEntityType.STEEL
        ));
    }
}
