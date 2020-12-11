package com.sierox.dositaire.util.managers;

import com.badlogic.gdx.audio.Music;
import com.sierox.dositaire.Dositaire;
import com.sierox.dositaire.util.Assets;

import java.util.ArrayList;

public class MusicManager {

    private static int chosenMusic;

    private static ArrayList<Music> music;
    private static boolean playing;

    static {
        music = new ArrayList<Music>();

        music.add(Assets.JAZZ_PIANO);
        music.add(Assets.FUNKY_SUSPENSE);
        music.add(Assets.GOING_HIGHER);
    }

    public static void setChosenMusic(int choice) {
        music.get(chosenMusic).stop();
        chosenMusic = choice;

        if (isPlaying())
            music.get(chosenMusic).play();

        Dositaire.preferences.putInteger("music", choice);
        Dositaire.preferences.flush();
    }

    public static void setPlaying(boolean playing) {
        MusicManager.playing = playing;
        setChosenMusic(chosenMusic);

        Dositaire.preferences.putBoolean("playing", playing);
        Dositaire.preferences.flush();
    }

    public static void togglePlaying() {
        if (playing)
            setPlaying(false);
        else
            setPlaying(true);
    }

    public static boolean isPlaying() {
        return playing;
    }

    public static void cycle() {
        setChosenMusic((chosenMusic + 1) % (music.size()));
    }

    public static Music getChosenMusic() {
        return music.get(chosenMusic);
    }

}
