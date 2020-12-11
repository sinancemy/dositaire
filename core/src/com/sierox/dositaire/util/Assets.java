package com.sierox.dositaire.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;

public class Assets {

    public static BitmapFont FONT_36, FONT_76, DEBUG_FONT_32, DEBUG_FONT_16;
    public static TextureAtlas CARDS;
    public static Texture MENU_1;
    public static Texture MENU_2;
    public static Texture BOARD_1;
    public static Texture BOARD_2;
    public static Texture INDICATOR_TEXTURE_1;
    public static Texture INDICATOR_TEXTURE_2;
    public static Skin GAME_SKIN;
    public static Skin MENU_SKIN;

    public static ArrayList<Texture> TUTORIAL_SLIDES;

    public static Music JAZZ_PIANO;
    public static Music FUNKY_SUSPENSE;
    public static Music GOING_HIGHER;

    private static void initializeAssets() {
        initializeFonts();
        initializeTextures();
        initializeSkins();
        initializeTutorial();
        initializeMusic();
    }

    private static void initializeTextures() {
        CARDS = new TextureAtlas(Gdx.files.internal("gfx\\cards\\cards.atlas"));
        MENU_1 = new Texture(Gdx.files.internal("gfx\\menus\\menu1.png"));
        MENU_2 = new Texture(Gdx.files.internal("gfx\\menus\\menu2.png"));
        BOARD_1 = new Texture(Gdx.files.internal("gfx\\boards\\board1.png"));
        BOARD_2 = new Texture(Gdx.files.internal("gfx\\boards\\board2.png"));
        INDICATOR_TEXTURE_1 = new Texture(Gdx.files.internal("gfx\\indicators\\indicator1.png"));
        INDICATOR_TEXTURE_2 = new Texture(Gdx.files.internal("gfx\\indicators\\indicator2.png"));
    }

    private static void initializeFonts() {
        FONT_36 = new BitmapFont(Gdx.files.internal("font\\font36.fnt"), Gdx.files.internal("font\\font36.png"), false);
        FONT_76 = new BitmapFont(Gdx.files.internal("font\\font76.fnt"), Gdx.files.internal("font\\font76.png"), false);
        DEBUG_FONT_32 = new BitmapFont(Gdx.files.internal("font\\debug\\debug32.fnt"), Gdx.files.internal("font\\debug\\debug32.png"), false);
        DEBUG_FONT_16 = new BitmapFont(Gdx.files.internal("font\\debug\\debug16.fnt"), Gdx.files.internal("font\\debug\\debug16.png"), false);
        FONT_36.setColor(1f, 1f, 1f, 1f);
        FONT_76.setColor(1f, 1f, 1f, 1f);
        DEBUG_FONT_32.setColor(1f, 1f, 1f, 1f);
        DEBUG_FONT_16.setColor(1f, 1f, 1f, 1f);
    }

    private static void initializeSkins() {
        GAME_SKIN = new Skin(Gdx.files.internal("ui\\game\\uiskin.json"));
        MENU_SKIN = new Skin(Gdx.files.internal("ui\\menu\\uiskin.json"));
    }

    private static void initializeTutorial() {
        Texture TUTORIAL_SLIDE_1 = new Texture(Gdx.files.internal("gfx\\tutorial\\tutorial1.png"));
        Texture TUTORIAL_SLIDE_2 = new Texture(Gdx.files.internal("gfx\\tutorial\\tutorial2.png"));
        Texture TUTORIAL_SLIDE_3 = new Texture(Gdx.files.internal("gfx\\tutorial\\tutorial3.png"));
        Texture TUTORIAL_SLIDE_4 = new Texture(Gdx.files.internal("gfx\\tutorial\\tutorial4.png"));
        Texture TUTORIAL_SLIDE_5 = new Texture(Gdx.files.internal("gfx\\tutorial\\tutorial5.png"));
        Texture TUTORIAL_SLIDE_6 = new Texture(Gdx.files.internal("gfx\\tutorial\\tutorial6.png"));
        Texture TUTORIAL_SLIDE_7 = new Texture(Gdx.files.internal("gfx\\tutorial\\tutorial7.png"));
        Texture TUTORIAL_SLIDE_8 = new Texture(Gdx.files.internal("gfx\\tutorial\\tutorial8.png"));
        Texture TUTORIAL_SLIDE_9 = new Texture(Gdx.files.internal("gfx\\tutorial\\tutorial9.png"));
        Texture TUTORIAL_SLIDE_10 = new Texture(Gdx.files.internal("gfx\\tutorial\\tutorial10.png"));
        Texture TUTORIAL_SLIDE_11 = new Texture(Gdx.files.internal("gfx\\tutorial\\tutorial11.png"));
        Texture TUTORIAL_SLIDE_12 = new Texture(Gdx.files.internal("gfx\\tutorial\\tutorial12.png"));
        Texture TUTORIAL_SLIDE_13 = new Texture(Gdx.files.internal("gfx\\tutorial\\tutorial13.png"));
        Texture TUTORIAL_SLIDE_14 = new Texture(Gdx.files.internal("gfx\\tutorial\\tutorial14.png"));
        Texture TUTORIAL_SLIDE_15 = new Texture(Gdx.files.internal("gfx\\tutorial\\tutorial15.png"));
        Texture TUTORIAL_SLIDE_16 = new Texture(Gdx.files.internal("gfx\\tutorial\\tutorial16.png"));

        TUTORIAL_SLIDES = new ArrayList<Texture>();

        TUTORIAL_SLIDES.add(TUTORIAL_SLIDE_1);
        TUTORIAL_SLIDES.add(TUTORIAL_SLIDE_2);
        TUTORIAL_SLIDES.add(TUTORIAL_SLIDE_3);
        TUTORIAL_SLIDES.add(TUTORIAL_SLIDE_4);
        TUTORIAL_SLIDES.add(TUTORIAL_SLIDE_5);
        TUTORIAL_SLIDES.add(TUTORIAL_SLIDE_6);
        TUTORIAL_SLIDES.add(TUTORIAL_SLIDE_7);
        TUTORIAL_SLIDES.add(TUTORIAL_SLIDE_8);
        TUTORIAL_SLIDES.add(TUTORIAL_SLIDE_9);
        TUTORIAL_SLIDES.add(TUTORIAL_SLIDE_10);
        TUTORIAL_SLIDES.add(TUTORIAL_SLIDE_11);
        TUTORIAL_SLIDES.add(TUTORIAL_SLIDE_12);
        TUTORIAL_SLIDES.add(TUTORIAL_SLIDE_13);
        TUTORIAL_SLIDES.add(TUTORIAL_SLIDE_14);
        TUTORIAL_SLIDES.add(TUTORIAL_SLIDE_15);
        TUTORIAL_SLIDES.add(TUTORIAL_SLIDE_16);
    }

    private static void initializeMusic() {
        JAZZ_PIANO = Gdx.audio.newMusic(Gdx.files.internal("sound\\music\\bensound-thejazzpiano_dositaire.mp3"));
        JAZZ_PIANO.setLooping(true);
        Assets.JAZZ_PIANO.setVolume(4f);

        FUNKY_SUSPENSE = Gdx.audio.newMusic(Gdx.files.internal("sound\\music\\bensound-funkysuspense_dositaire.mp3"));
        FUNKY_SUSPENSE.setLooping(true);
        Assets.FUNKY_SUSPENSE.setVolume(4f);

        GOING_HIGHER = Gdx.audio.newMusic(Gdx.files.internal("sound\\music\\bensound-goinghigher_dositaire.mp3"));
        GOING_HIGHER.setLooping(true);
        Assets.GOING_HIGHER.setVolume(4f);
    }

    static {
        initializeAssets();
    }
}
