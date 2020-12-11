package com.sierox.dositaire.game.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierox.dositaire.util.Assets;
import com.sierox.dositaire.game.cards.collections.CardStack;

/**
 * Created by USER on 01/03/2018.
 */

public class Card {
    private CardSuit suit;
    private CardValue value;

    private int x = 0;
    private int y = 0;
    private boolean animated = false;

    public Card(CardSuit suit, CardValue value) {
        this.suit = suit;
        this.value = value;
    }

    public void render(SpriteBatch sb) {
        Assets.CARDS.findRegion(String.valueOf(suit.symbol()) + String.valueOf(value.symbol())).getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sb.draw(Assets.CARDS.findRegion(String.valueOf(suit.symbol()) + String.valueOf(value.symbol())), x, y);
    }

    public static void renderEmptyCard(SpriteBatch sb, float x, float y) {
        sb.draw(Assets.CARDS.findRegion("back"), x, y);
    }

    public CardStack toStack() {
        CardStack stack = new CardStack();
        stack.add(this);
        return stack;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public CardValue getValue() {
        return value;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isAnimated() {
        return animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }
}
