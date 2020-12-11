package com.sierox.dositaire.screens.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sierox.dositaire.util.Assets;
import com.sierox.dositaire.util.Constants;
import com.sierox.dositaire.screens.credits.CreditsScreen;
import com.sierox.dositaire.screens.options.OptionsScreen;
import com.sierox.dositaire.screens.setup.gamesetup.GameSetupScreen;
import com.sierox.dositaire.screens.tutorial.TutorialScreen;

/**
 * Created by Sierox on 01/02/2017.
 */
public class MenuScreenUI extends Stage {

    private MenuScreen screen;
    TextButton newGameButton;
    TextButton optionsButton;
    TextButton tutorialButton;
    TextButton creditsButton;
    TextButton donateButton;

    public MenuScreenUI(MenuScreen screen) {
        this.screen = screen;
        setViewport(screen.game.viewport);
        initializeUiComponents();
    }

    private void initializeUiComponents() {
        newGameButton = new TextButton("New Game", Assets.MENU_SKIN);
        newGameButton.setSize(500, 140);
        newGameButton.setPosition(Constants.WIDTH / 2 - newGameButton.getWidth() / 2, Constants.HEIGHT / 2 + 40);
        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.game.setScreen(new GameSetupScreen(screen.game));
                screen.dispose();
            }
        });

        optionsButton = new TextButton("Options", Assets.MENU_SKIN);
        optionsButton.setSize(500, 140);
        optionsButton.setPosition(Constants.WIDTH / 2 - newGameButton.getWidth() / 2, Constants.HEIGHT / 2 - Constants.MENU_ITEM_MARGIN_Y * 1 + 40);
        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.game.setScreen(new OptionsScreen(screen.game));
                dispose();
            }
        });

        tutorialButton = new TextButton("How to Play", Assets.MENU_SKIN);
        tutorialButton.setSize(500, 140);
        tutorialButton.setPosition(Constants.WIDTH / 2 - optionsButton.getWidth() / 2, Constants.HEIGHT / 2 - Constants.MENU_ITEM_MARGIN_Y * 2 + 40);
        tutorialButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.game.setScreen(new TutorialScreen(screen.game));
                dispose();
            }
        });

        creditsButton = new TextButton("Credits", Assets.MENU_SKIN);
        creditsButton.setTransform(true);
        creditsButton.setScale(0.4f);
        creditsButton.setSize(500, 140);
        creditsButton.setPosition(20, 20);
        creditsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.game.setScreen(new CreditsScreen(screen.game));
                dispose();
            }
        });

        donateButton = new TextButton("Donate", Assets.MENU_SKIN);
        donateButton.setTransform(true);
        donateButton.setScale(0.4f);
        donateButton.setSize(500, 140);
        donateButton.setPosition(240, 20);

        addActor(optionsButton);
        addActor(newGameButton);
        addActor(tutorialButton);
        addActor(creditsButton);
        addActor(donateButton);
    }
}

