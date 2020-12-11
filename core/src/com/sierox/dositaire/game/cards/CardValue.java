package com.sierox.dositaire.game.cards;

/**
 * Created by USER on 01/03/2018.
 */

public enum CardValue {
    TWO(2, '2'), THREE(3, '3'), FOUR(4, '4'), FIVE(5, '5'), SIX(6, '6'), SEVEN(7, '7'), EIGHT(8, '8'), NINE(9, '9'), TEN(10, '0'),
    JACK(11, 'j'), QUEEN(12, 'q'), KING(13, 'k'), ACE(1, 'a'), JOKER(15, 'x');

    private final int value;
    private final char symbol;

    CardValue(int value, char symbol) {
        this.value = value;
        this.symbol = symbol;
    }

    public int value() {
        return value;
    }

    public char symbol() {
        return symbol;
    }
}
