package com.sierox.dositaire.game.cards;

/**
 * Created by USER on 01/03/2018.
 */

public enum CardSuit {
    CLUBS(1, 'c', CardColor.BLACK), DIAMONDS(2, 'd', CardColor.RED),
    HEARTS(3, 'h', CardColor.RED), SPADES(4, 's', CardColor.BLACK), JOKER(5, 'j', CardColor.BLACK);

    private final int value;
    private final char symbol;
    private final CardColor color;

    CardSuit(int value, char symbol, CardColor color) {
        this.value = value;
        this.symbol = symbol;
        this.color = color;
    }

    public int value() {
        return value;
    }

    public char symbol() {
        return symbol;
    }

    public CardColor color() {
        return color;
    }
}
