package com.sierox.dositaire.game.cards.collections;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierox.dositaire.util.Assets;
import com.sierox.dositaire.game.cards.Card;
import com.sierox.dositaire.game.cards.CardSuit;
import com.sierox.dositaire.game.cards.CardValue;

import java.util.Collections;
import java.util.Stack;

/**
 * Created by USER on 01/03/2018.
 */

public class Deck {
    Stack<Card> cards;

    public Deck() {
        cards = new Stack<Card>();
    }

    public Deck(boolean shuffle) {
        cards = new Stack<Card>();
        CardSuit[] suits = {CardSuit.CLUBS, CardSuit.DIAMONDS, CardSuit.HEARTS, CardSuit.SPADES};
        CardValue[] values = {CardValue.ACE, CardValue.TWO, CardValue.THREE, CardValue.FOUR,
                CardValue.FIVE, CardValue.SIX, CardValue.SEVEN, CardValue.EIGHT, CardValue.NINE,
                CardValue.TEN, CardValue.JACK, CardValue.QUEEN, CardValue.KING};
        for (CardSuit s : suits) {
            for (CardValue v : values) {
                add(new Card(s, v));
            }
        }
        if (shuffle) shuffle();
    }

    public void add(Card card) {
        cards.push(card);
    }

    public void add(CardStack stack) {
        cards.addAll(stack.getCards());
    }

    public Card draw() {
        return cards.pop();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void render(SpriteBatch sb, float x, float y) {
        sb.begin();
        sb.draw(Assets.CARDS.findRegion("back"), x, y);
        sb.end();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
