package com.sierox.dositaire.game.cards.playground;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.sierox.dositaire.util.Constants;
import com.sierox.dositaire.game.cards.collections.CardStack;

import java.util.ArrayList;

public class Field {

    private ArrayList<Slot> slots;
    private Owner owner;

    public Field(Owner owner) {
        slots = new ArrayList<Slot>();
        this.owner = owner;
    }

    public void add(CardStack stack) {
        if (slots.size() < 4) {
            slots.add(new Slot(stack, owner));
        }
    }

    public boolean hasEmptySlot() {
        for (Slot s : slots) {
            if (s.isEmpty())
                return true;
        }
        return false;
    }

    public void remove(CardStack stack) {
        slots.remove(stack);
    }

    public void render(SpriteBatch sb, int x, int y, boolean flip) {
        if (flip) {
            for (int i = 0; i < slots.size(); i++) {
                slots.get(i).render(sb, x + (i * Constants.CARD_X_MARGIN), y, true);
            }
            for (int i = 0; i < (4 - slots.size()); i++) {
                slots.get(i).render(sb, x + ((4 - i) * Constants.CARD_X_MARGIN), y, true);
            }
        } else {
            for (int i = 0; i < slots.size(); i++) {
                slots.get(i).render(sb, x + (i * Constants.CARD_X_MARGIN), y, false);
            }
        }
    }

    public ArrayList<Slot> getSlots() {
        return slots;
    }

    public ArrayList<Rectangle> getClickRectangles(int x, int y, boolean flipped) {
        ArrayList<Rectangle> clickRectangles = new ArrayList<Rectangle>();
        if (flipped) {
            for (int i = 0; i < slots.size(); i++) {
                clickRectangles.add(new Rectangle(x + (i * Constants.CARD_X_MARGIN), y, Constants.CARD_WIDTH,
                        Constants.CARD_HEIGHT + ((slots.get(i).size() - 1) * Constants.STACK_MARGIN)));
            }
        } else {
            for (int i = 0; i < slots.size(); i++) {
                clickRectangles.add(new Rectangle(x + (i * Constants.CARD_X_MARGIN), y - ((slots.get(i).size() - 1) * Constants.STACK_MARGIN),
                        Constants.CARD_WIDTH, Constants.CARD_HEIGHT + ((slots.get(i).size() - 1) * Constants.STACK_MARGIN)));
            }
        }

        return clickRectangles;
    }
}
