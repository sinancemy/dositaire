package com.sierox.dositaire.game.cards.playground;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.sierox.dositaire.util.Constants;
import com.sierox.dositaire.game.cards.collections.Deck;

import java.util.ArrayList;

public class Middle {

    private ArrayList<Slot> slots;
    private Owner owner;

    public Middle() {
        slots = new ArrayList<Slot>();
        owner = Owner.MIDDLE;
    }

    public boolean fill(Deck deck) {
        boolean openedCard = false;
        if (deck.isEmpty())
            return true;
        for (Slot s : slots) {
            if (s.isEmpty() && !deck.isEmpty()) {
                s.setCards(deck.draw().toStack());
                openedCard = true;
            }
        }
        return openedCard;
    }

    public void reopen(Deck deck) {
        shuffleToDeck(deck);
        deck.shuffle();
        fill(deck);
    }

    private void shuffleToDeck(Deck deck) {
        for (Slot s : slots) {
            if (!s.isEmpty()) {
                deck.add(s.getCardStack().top());
                s.clear();
            }
        }
    }

    public void render(SpriteBatch sb) {
        for (int i = 0; i < slots.size(); i++) {
            slots.get(i).render(sb, Constants.MIDDLE_X + (i * Constants.CARD_X_MARGIN), Constants.MIDDLE_Y, false);
        }
    }

    public ArrayList<Rectangle> getClickRectangles() {
        ArrayList<Rectangle> clickRectangles = new ArrayList<Rectangle>();
        for (int i = 0; i < slots.size(); i++) {
            clickRectangles.add(new Rectangle(Constants.MIDDLE_X + (i * Constants.CARD_X_MARGIN), Constants.MIDDLE_Y,
                    Constants.CARD_WIDTH, Constants.CARD_HEIGHT));
        }
        return clickRectangles;
    }

    public ArrayList<Slot> getSlots() {
        return slots;
    }

    public Owner getOwner() {
        return owner;
    }
}
