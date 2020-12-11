package com.sierox.dositaire.game.algorithm;

import com.sierox.dositaire.game.cards.playground.Owner;

public enum GameTurn {
    PLAYER1(Owner.PLAYER1, Owner.PLAYER2), PLAYER2(Owner.PLAYER2, Owner.PLAYER1);

    private final Owner player;
    private final Owner enemy;

    GameTurn(Owner player, Owner enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public Owner player() {
        return player;
    }

    public Owner enemy() {
        return enemy;
    }
}
