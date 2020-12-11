package com.sierox.dositaire.game.algorithm;

import com.sierox.dositaire.Dositaire;
import com.sierox.dositaire.game.cards.CardSuit;
import com.sierox.dositaire.game.cards.collections.CardStack;
import com.sierox.dositaire.game.cards.collections.Deck;
import com.sierox.dositaire.game.cards.playground.Collector;
import com.sierox.dositaire.game.cards.playground.Middle;
import com.sierox.dositaire.game.cards.playground.Owner;
import com.sierox.dositaire.game.cards.playground.Slot;
import com.sierox.dositaire.game.player.Player;

import java.util.LinkedList;

/**
 * Created by USER on 01/03/2018.
 */


public class Algorithm {

    public Dositaire game;
    public Player player1;
    public Player player2;
    public Deck deck;
    public Middle middle;
    public Collector collector;

    public CardSelector selector;
    private GameTurn turn;
    public int consecutivePass;

    GameMode gameMode;
    GamePlayerMode gamePlayerMode;
    int until;

    public Algorithm(Dositaire game, GameMode gameMode, GamePlayerMode gamePlayerMode, int until) {
        this.game = game;
        setupGame();
        consecutivePass = 0;
        selector = new CardSelector(player1, player2, middle, collector);
        this.gameMode = gameMode;
        this.gamePlayerMode = gamePlayerMode;
        this.until = until;
    }

    public void setupGame() {
        turn = GameTurn.PLAYER1;
        player1 = new Player(true, Owner.PLAYER1);
        player2 = new Player(false, Owner.PLAYER2);
        deck = new Deck(true);
        middle = new Middle();
        collector = new Collector();

        for (int i = 0; i < 4; i++) {
            player1.addStackToField(deck.draw().toStack());
            player2.addStackToField(deck.draw().toStack());
        }
        for (int i = 0; i < 3; i++) {
            middle.getSlots().add(new Slot(deck.draw().toStack(), middle.getOwner()));
        }
    }

    public void nextTurn(boolean passed) {
        if (passed)
            consecutivePass++;
        else
            consecutivePass = 0;

        if (turn == GameTurn.PLAYER1)
            turn = GameTurn.PLAYER2;
        else
            turn = GameTurn.PLAYER1;

    }

    public GameTurn getTurn() {
        return turn;
    }

    public void checkSelections(int x, int y) {
        selector.checkSelections(x, y);
    }

    public void checkMove() {
        LinkedList<Slot> selections = selector.getSelections();
        if (selections.size() == 1) {
            if (selections.get(0).getOwner() == Owner.COLLECTOR || selections.get(0).isEmpty()) {
                selector.clearSelections();
            }
        }
        if (selections.size() == 2) {
            if ((selections.get(0).getOwner() == Owner.MIDDLE && selections.get(1).getOwner() == turn.player()) ||
                    (selections.get(0).getOwner() == turn.enemy() && selections.get(1).getOwner() == turn.player() && !selections.get(1).isEmpty()) ||
                    (selections.get(0).getOwner() == turn.player() && selections.get(1).getOwner() == turn.player()) &&
                            ((!selections.get(0).isEmpty()) && !selections.get(1).isEmpty())) {
                //ORTADAN KENDİNE KART ALMA (orta -> kendi)
                //RAKİPTEN KENDİ KARTI İLE ÇALMA (rakip -> kendi)
                //KENDİ KARTLARINI BİRLEŞTİRME (kendi1 -> kendi2)
                if (!selections.get(0).isEmpty() && !selections.get(1).isEmpty()) {
                    if (selections.get(1).getCardStack().combine(selections.get(0).getCardStack())) {
                        selector.clearSelections();
                        nextTurn(false);
                        return;
                    }
                } else if (!selections.get(0).isEmpty() && selections.get(1).isEmpty()) {
                    //KENDİ BOŞ YERİNE ORTADAN KART ALMA
                    selections.get(1).setCards(new CardStack(selections.get(0).getCardStack().getCards()));
                    selections.get(0).clear();
                    selector.clearSelections();
                    nextTurn(false);
                    return;
                }
            } else if (selections.get(0).getOwner() == Owner.MIDDLE
                    && selections.get(1).getOwner() == turn.enemy()) {
                return;
            } else if (selections.get(0).getCardStack().isComplete()
                    && selections.get(0).getOwner() == turn.player()
                    && selections.get(1).getOwner() == Owner.COLLECTOR) {
                //SCORE
                int score;
                if (selections.get(0).getCardStack().top().getSuit() == CardSuit.CLUBS ||
                        selections.get(0).getCardStack().top().getSuit() == CardSuit.DIAMONDS)
                    score = 1;
                else
                    score = 2;

                if (turn.player() == Owner.PLAYER1)
                    player1.addPoints(score);
                else
                    player2.addPoints(score);

                if (gameMode == GameMode.UNTIL) {
                    deck.add(selections.get(0).getCardStack());
                    deck.shuffle();
                }

                selections.get(0).clear();
                selector.clearSelections();
                nextTurn(false);
                return;
            } else {
                selector.clearSelections();
                return;
            }
        } else if (selections.size() == 3) {
            if ((selections.get(0).getOwner() == Owner.MIDDLE) && (selections.get(1).getOwner() == turn.enemy()) &&
                    (selections.get(2).getOwner() == turn.player()) && (selections.get(2).isEmpty())) {
                //ORTADAN KARŞININ KARTINI ÇALMA (orta -> karşı -> kendi-boş)
                if (selections.get(1).combine(selections.get(0))) {
                    selections.get(2).setCards(new CardStack(selections.get(1).getCardStack().getCards()));
                    selections.get(1).clear();
                    selector.clearSelections();
                    nextTurn(false);
                    return;
                }
                selector.clearSelections();
            }
            selector.clearSelections();
        } else if (selections.size() == 1)
            return;
        else
            selector.clearSelections();
    }
}
