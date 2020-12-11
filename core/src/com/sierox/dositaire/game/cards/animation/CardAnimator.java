package com.sierox.dositaire.game.cards.animation;

import com.badlogic.gdx.math.Vector2;
import com.sierox.dositaire.game.cards.playground.Collector;
import com.sierox.dositaire.game.cards.playground.Field;
import com.sierox.dositaire.game.cards.playground.Middle;
import com.sierox.dositaire.game.cards.playground.Slot;

public class CardAnimator {

    private Slot[][] board;
    protected static final float ANIMATION_SPEED = 1.0f;

    public CardAnimator(Field p1, Field p2, Middle middle, Collector collector){
        for (int i=0; i < 4; i++){
            board[0][i] = p1.getSlots().get(i);
            board[2][i] = p2.getSlots().get(i);
            if(i<3)
                board[1][i] = middle.getSlots().get(i);
        }
        board[1][3] = collector.getSlot();
    }

    public void animateCards(int fromX, int fromY, int toX, int toY){
        Slot from = board[fromX][fromY];
        Slot to = board[toX][toY];

        from.getCardStack().getCards().get(0).setAnimated(true);
        // TODO: ööö from.getCardStack().getCards().get(0).
    }

    public void animateCards(Slot from, Slot to){
        Vector2 fromCoords = getSlotCoordinates(from);
        Vector2 toCoords = getSlotCoordinates(to);
        animateCards((int) fromCoords.x, (int) fromCoords.y, (int) toCoords.x, (int) toCoords.y);
    }

    private Vector2 getSlotCoordinates(Slot slot){
        for (int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                if(board[i][j] == slot){
                    return new Vector2(i,j);
                }
            }
        }
        //TODO: SlotNotFoundException
        return null;
    }
}
