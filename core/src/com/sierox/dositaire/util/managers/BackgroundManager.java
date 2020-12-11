package com.sierox.dositaire.util.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sierox.dositaire.Dositaire;
import com.sierox.dositaire.util.Assets;

import java.util.ArrayList;

public class BackgroundManager {

    private static int chosenBackground = 0;
    //private static int chosenCardBack = 1;

    private static ArrayList<Texture> boards;
    private static ArrayList<Texture> indicators;
    private static ArrayList<Texture> menus;

    static {
        boards = new ArrayList<Texture>();
        indicators = new ArrayList<Texture>();
        menus = new ArrayList<Texture>();

        boards.add(Assets.BOARD_1);
        boards.add(Assets.BOARD_2);
        indicators.add(Assets.INDICATOR_TEXTURE_1);
        indicators.add(Assets.INDICATOR_TEXTURE_2);
        menus.add(Assets.MENU_1);
        menus.add(Assets.MENU_2);
    }

    public static void setChosenBackground(int choice) {
        chosenBackground = choice;
        Dositaire.preferences.putInteger("background", chosenBackground);
        Dositaire.preferences.flush();
    }

    public static void cycle() {
        setChosenBackground((chosenBackground + 1) % boards.size());
    }

    public static Texture getChosenBoard() {
        return boards.get(chosenBackground);
    }

    public static Texture getChosenMenu() {
        return menus.get(chosenBackground);
    }

    public static Sprite getChosenIndicator(boolean flipped) {
        if (flipped) {
            Sprite s = new Sprite(indicators.get(chosenBackground));
            s.setFlip(true, true);
            return s;
        } else
            return new Sprite(indicators.get(chosenBackground));
    }

}
