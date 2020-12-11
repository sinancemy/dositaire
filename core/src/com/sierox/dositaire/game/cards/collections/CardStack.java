package com.sierox.dositaire.game.cards.collections;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierox.dositaire.util.Constants;
import com.sierox.dositaire.game.cards.Card;
import com.sierox.dositaire.game.cards.CardValue;

import java.util.Stack;

public class CardStack {

    private Stack<Card> stack;

    public CardStack() {
        stack = new Stack<Card>();
    }

    public CardStack(Stack<Card> stack) {
        this.stack = new Stack<Card>();
        for (Card c : stack) {
            this.stack.add(c);
        }
    }

    public void add(Card card) {
        stack.add(card);
    }

    public void add(CardStack cardStack) {
        for (Card c : cardStack.stack) {
            stack.add(c);
        }
    }

    public Card bottom() {
        return stack.firstElement();
    }

    public Card top() {
        return stack.lastElement();
    }

    public boolean isComplete() {
        return (top().getValue() == CardValue.ACE && bottom().getValue() == CardValue.KING);
    }

    public Stack<Card> getCards() {
        return stack;
    }

    public void render(SpriteBatch sb, int x, int y, boolean flip, boolean selected) {
        if (selected) {
            sb.setColor(1f, 1f, 0.5f, 1f);
        }
        if (isEmpty()) {
            Card.renderEmptyCard(sb, x, y);
        } else {
            if (flip) {
                for (int i = 0; i < stack.size(); i++) {
                    if(!stack.get(i).isAnimated())
                        stack.get(i).setPosition(x, y + (i * Constants.STACK_MARGIN));
                    // else ANIMATE fLIPPED;

                    stack.get(i).render(sb);
                }
            } else {
                for (int i = 0; i < stack.size(); i++) {
                    if(!stack.get(i).isAnimated())
                        stack.get(i).setPosition(x, y - (i * Constants.STACK_MARGIN));
                    // else ANIMATE NON FLIEPED;

                    stack.get(i).render(sb);
                }
            }
        }
        sb.setColor(1f, 1f, 1f, 1f);
    }

    public void clear() {
        stack.clear();
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public boolean canCombineFromTop(CardStack combine) {
        return (top().getSuit().color() != combine.bottom().getSuit().color()) &&
                (top().getValue().value() - 1 == combine.bottom().getValue().value());
    }

    public boolean canCombineFromBottom(CardStack combine) {
        return (bottom().getSuit().color() != combine.top().getSuit().color()) &&
                (bottom().getValue().value() + 1 == combine.top().getValue().value());
    }

    public boolean canCombine(CardStack combine) {
        return (canCombineFromTop(combine) || canCombineFromBottom(combine));
    }

    public boolean combine(CardStack combine) {
        if (canCombineFromTop(combine)) {
            add(combine);
            combine.clear();
            return true;
        } else if (canCombineFromBottom(combine)) {
            Stack<Card> newStack = new Stack<Card>();
            newStack.addAll(combine.stack);
            newStack.addAll(stack);
            stack = newStack;
            combine.clear();
            return true;
        } else
            return false;
    }
}
