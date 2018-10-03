package core.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicPlayer {
    private static Music gameStartMusic;

    public static void playGameStartMusic() {
        gameStartMusic = Gdx.audio.newMusic(Gdx.files.internal("music/start.wav"));
        gameStartMusic.play();
        gameStartMusic.setLooping(false);
    }

    public static void dispose() {
        gameStartMusic.dispose();
    }
}
