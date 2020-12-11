package com.sierox.dositaire.game.cards.animation;

import com.sierox.dositaire.game.cards.playground.Slot;

public class CardAnimation {

    private Slot from;
    private Slot to;

    public CardAnimation(Slot from, Slot to){
        this.from = from;
        this.to = to;
    }

    protected void step(){
       // from.getCardStack().CardAnimator.ANIMATION_SPEED;
    }
}
