package com.sierox.dositaire.game.player.ai;

import com.sierox.dositaire.game.cards.playground.Owner;
import com.sierox.dositaire.game.player.Player;

public class AIPlayer extends Player {
    AINeuralNetwork ai;

    public AIPlayer(boolean isPrimaryPlayer, Owner owner) {
        super(isPrimaryPlayer, owner);
        ai = new AINeuralNetwork();
    }
}
