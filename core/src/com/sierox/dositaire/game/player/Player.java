package com.sierox.dositaire.game.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.sierox.dositaire.util.Constants;
import com.sierox.dositaire.game.cards.collections.CardStack;
import com.sierox.dositaire.game.cards.playground.Field;
import com.sierox.dositaire.game.cards.playground.Owner;

import java.util.ArrayList;

public class Player {

    private Field field;
    private int points;
    private boolean isPrimaryPlayer;

    public Player(boolean isPrimaryPlayer, Owner owner) {
        field = new Field(owner);
        points = 0;
        this.isPrimaryPlayer = isPrimaryPlayer;
    }

    public void addStackToField(CardStack stack) {
        field.add(stack);
    }

    public Field getField() {
        return field;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int add) {
        points += add;
    }

    public void render(SpriteBatch sb) {
        if (isPrimaryPlayer)
            field.render(sb, Constants.PLAYER_1_X, Constants.PLAYER_1_Y, false);
        else
            field.render(sb, Constants.PLAYER_2_X, Constants.PLAYER_2_Y, true);
    }

    public ArrayList<Rectangle> getFieldClickRectangles() {
        if (isPrimaryPlayer)
            return field.getClickRectangles(Constants.PLAYER_1_X, Constants.PLAYER_1_Y, false);
        else
            return field.getClickRectangles(Constants.PLAYER_2_X, Constants.PLAYER_2_Y, true);
    }
}
