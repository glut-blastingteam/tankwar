package core.util;

import com.badlogic.gdx.scenes.scene2d.Stage;
import core.entity.BaseEntity;

public class MapGenerator {
    public static void generateMap1(Stage stage, float height, float width) {
        for (int i = 200; i < height - 200; i += 100) {
            for (int k = 0; k < width; k += 100) {
                stage.addActor(new BaseEntity("invariant/walls.gif", k, i));
            }
        }
        BaseEntity boss = new BaseEntity("symbol.gif", width / 2.0f, 0.0f);
        stage.addActor(boss);
        stage.addActor(new BaseEntity(
                "invariant/steels.gif",
                width / 2.0f - boss.getTextureWidth(), 0.0f
        ));
        stage.addActor(new BaseEntity(
                "invariant/steels.gif",
                width / 2.0f + boss.getTextureWidth(), 0.0f
        ));
        stage.addActor(new BaseEntity(
                "invariant/steels.gif",
                width / 2.0f - boss.getTextureWidth(), boss.getTextureHeight()
        ));
        stage.addActor(new BaseEntity(
                "invariant/steels.gif",
                width / 2.0f, boss.getTextureHeight()
        ));
        stage.addActor(new BaseEntity(
                "invariant/steels.gif",
                width / 2.0f + boss.getTextureWidth(), boss.getTextureHeight()
        ));
    }
}
