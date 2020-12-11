package com.sierox.dositaire.game.cards.playground;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.sierox.dositaire.util.Assets;
import com.sierox.dositaire.util.Constants;
import com.sierox.dositaire.game.cards.collections.CardStack;

public class Collector {

    private Owner owner;
    private Slot slot;

    public Collector() {
        owner = Owner.COLLECTOR;
        slot = new Slot(new CardStack(), owner);
    }

    public void render(SpriteBatch sb) {
        sb.draw(Assets.CARDS.findRegion("drop"), Constants.COLLECTOR_X, Constants.COLLECTOR_Y);
    }

    public Rectangle getClickRectangle() {
        return new Rectangle(Constants.COLLECTOR_X, Constants.COLLECTOR_Y, Constants.CARD_WIDTH, Constants.HEIGHT);
    }

    public Owner getOwner() {
        return owner;
    }

    public Slot getSlot() {
        return slot;
    }
}
