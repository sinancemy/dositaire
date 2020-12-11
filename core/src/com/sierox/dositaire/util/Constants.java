package com.sierox.dositaire.util;

public class Constants {

    public static final String TAG = "DOSITAIRE_LOG";
    public static final int WIDTH = 1080;
    public static final int HEIGHT = 1920;

    public static final int CARD_WIDTH = 156;
    public static final int CARD_HEIGHT = 238;

    public static final int STACK_MARGIN = 40;
    public static final int CARD_X_MARGIN = 200;
    public static final int REAL_CARD_X_MARGIN = CARD_X_MARGIN - CARD_WIDTH;

    public static final int FIELD_Y_MARGIN = 340;

    public static final int MIDDLE_X = WIDTH / 2 - CARD_WIDTH / 2 - CARD_X_MARGIN;
    public static final int MIDDLE_Y = HEIGHT / 2 - CARD_HEIGHT / 2;

    public final static int COLLECTOR_X = WIDTH - CARD_WIDTH - 30;
    public final static int COLLECTOR_Y = HEIGHT / 2 - CARD_HEIGHT / 2;

    public static final int PLAYER_1_X = WIDTH / 2 - 3 * REAL_CARD_X_MARGIN / 2 - 2 * CARD_WIDTH;
    public static final int PLAYER_1_Y = HEIGHT / 2 - CARD_HEIGHT / 2 - FIELD_Y_MARGIN;
    public static final int PLAYER_2_X = WIDTH / 2 - 3 * REAL_CARD_X_MARGIN / 2 - 2 * CARD_WIDTH;
    public static final int PLAYER_2_Y = HEIGHT / 2 - CARD_HEIGHT / 2 + FIELD_Y_MARGIN;

    public static final int MENU_ITEM_MARGIN_Y = 190;
}
