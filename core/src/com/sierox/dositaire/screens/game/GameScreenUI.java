package com.sierox.dositaire.screens.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.sierox.dositaire.util.Assets;
import com.sierox.dositaire.util.Constants;
import com.sierox.dositaire.game.algorithm.GameTurn;

/**
 * Created by Sierox on 01/02/2017.
 */
public class GameScreenUI extends Stage {

    private GameScreen screen;
    TextButton pass1;
    TextButton pass2;
    Label score1;
    Label score2;
    Container score2c;

    public GameScreenUI(GameScreen screen) {
        this.screen = screen;
        setViewport(screen.game.viewport);
        initializeUiComponents();
    }

    private void initializeUiComponents() {

        score1 = new Label("0", Assets.MENU_SKIN);
        score1.setAlignment(Align.center);
        score2 = new Label("0", Assets.MENU_SKIN);
        score2.setAlignment(Align.center);
        score2c = new Container(score2);
        score2c.setTransform(true);
        score2c.setRotation(180f);

        pass1 = new TextButton("Pass", Assets.GAME_SKIN);
        pass1.setSize(100, 76);
        pass1.setPosition(Constants.WIDTH - 122, 25);
        pass1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (screen.algorithm.getTurn() == GameTurn.PLAYER1)
                    passButtonPressed();
            }
        });

        pass2 = new TextButton("Pass", Assets.GAME_SKIN);
        pass2.setSize(100, 76);
        pass2.setPosition(122, Constants.HEIGHT - 25);
        pass2.setTransform(true);
        pass2.setRotation(180f);
        pass2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (screen.algorithm.getTurn() == GameTurn.PLAYER2) {
                    passButtonPressed();
                }
            }
        });

        addActor(pass1);
        addActor(pass2);
        addActor(score1);
        addActor(score2c);
    }

    private void passButtonPressed() {
        screen.algorithm.nextTurn(true);
        if (screen.algorithm.middle.fill(screen.algorithm.deck)) {
            screen.algorithm.consecutivePass = 0;
        }

        if (screen.algorithm.consecutivePass == 2) {
            screen.algorithm.middle.reopen(screen.algorithm.deck);
        } else if (screen.algorithm.consecutivePass == 3) {
            screen.algorithm.consecutivePass = 1;
        }
        updateUILabels();
        screen.algorithm.selector.clearSelections();
    }

    public void updateUILabels() {
        score1.setText(screen.algorithm.player1.getPoints());
        score1.setPosition(52, 108 - score1.getHeight());
        score2.setText(screen.algorithm.player2.getPoints());
        score2c.setPosition(Constants.WIDTH - 70, Constants.HEIGHT - 64);
    }
}

