package com.sierox.dositaire.game.cards.playground;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierox.dositaire.game.cards.collections.CardStack;

public class Slot {

    private CardStack stack;
    private boolean selected;
    private Owner owner;

    public Slot(CardStack stack, Owner owner) {
        this.stack = stack;
        selected = false;
        this.owner = owner;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void clear() {
        stack.clear();
    }

    public void render(SpriteBatch sb, int x, int y, boolean flip) {
        stack.render(sb, x, y, flip, selected);
    }

    public boolean isSelected() {
        return selected;
    }

    public void select() {
        selected = !selected;
    }

    public int size() {
        return stack.size();
    }

    public CardStack getCardStack() {
        return stack;
    }

    public Owner getOwner() {
        return owner;
    }

    public boolean combine(Slot combine) {
        return stack.combine(combine.stack);
    }

    public void setCards(CardStack stack) {
        this.stack = stack;
    }
}
