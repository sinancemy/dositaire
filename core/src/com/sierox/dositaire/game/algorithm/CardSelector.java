package com.sierox.dositaire.game.algorithm;

import com.sierox.dositaire.game.cards.playground.Collector;
import com.sierox.dositaire.game.cards.playground.Middle;
import com.sierox.dositaire.game.cards.playground.Slot;
import com.sierox.dositaire.game.player.Player;

import java.util.LinkedList;

public class CardSelector {

    private Player player1;
    private Player player2;
    private Middle middle;
    private Collector collector;

    private LinkedList<Slot> selections;

    public CardSelector(Player player1, Player player2, Middle middle, Collector collector) {
        selections = new LinkedList<Slot>();
        this.player1 = player1;
        this.player2 = player2;
        this.middle = middle;
        this.collector = collector;
    }

    public void checkSelections(int x, int y) {
        for (int i = 0; i < player1.getFieldClickRectangles().size(); i++) {
            if (player1.getFieldClickRectangles().get(i).contains(x, y)) {
                player1.getField().getSlots().get(i).select();
                if (player1.getField().getSlots().get(i).isSelected())
                    selections.add(player1.getField().getSlots().get(i));
                else
                    selections.remove(player1.getField().getSlots().get(i));
                return;
            }
        }
        for (int i = 0; i < player2.getFieldClickRectangles().size(); i++) {
            if (player2.getFieldClickRectangles().get(i).contains(x, y)) {
                player2.getField().getSlots().get(i).select();
                if (player2.getField().getSlots().get(i).isSelected())
                    selections.add(player2.getField().getSlots().get(i));
                else
                    selections.remove(player2.getField().getSlots().get(i));
                return;
            }
        }
        for (int i = 0; i < middle.getClickRectangles().size(); i++) {
            if (middle.getClickRectangles().get(i).contains(x, y)) {
                middle.getSlots().get(i).select();
                if (middle.getSlots().get(i).isSelected())
                    selections.add(middle.getSlots().get(i));
                else
                    selections.remove(middle.getSlots().get(i));
                return;
            }
        }
        if (collector.getClickRectangle().contains(x, y)) {
            selections.add(collector.getSlot());
            return;
        }
    }

    public void clearSelections() {
        for (Slot s : selections) {
            s.select();
        }
        selections.clear();
    }

    public LinkedList<Slot> getSelections() {
        return selections;
    }
}
