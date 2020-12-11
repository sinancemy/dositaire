package com.sierox.dositaire.screens.setup.gamesetup;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.sierox.dositaire.game.algorithm.GamePlayerMode;
import com.sierox.dositaire.util.Assets;
import com.sierox.dositaire.util.Constants;
import com.sierox.dositaire.game.algorithm.GameMode;
import com.sierox.dositaire.screens.game.GameScreen;

public class GameSetupScreenUI extends Stage {

    private GameSetupScreen screen;
    private TextButton playButton;

    private Label untilText;
    private TextField untilTextField;
    private SelectBox<GameMode> gameModeSelectBox;

    public GameSetupScreenUI(GameSetupScreen screen) {
        this.screen = screen;
        setViewport(screen.game.viewport);
        initializeUiComponents();
    }

    private void initializeUiComponents() {
        gameModeSelectBox = new SelectBox<GameMode>(Assets.MENU_SKIN);
        Array<GameMode> gameModes = new Array<GameMode>();
        gameModes.add(GameMode.CLASSIC);
        gameModes.add(GameMode.UNTIL);
        gameModeSelectBox.setItems(gameModes);
        gameModeSelectBox.setSize(500, 140);
        gameModeSelectBox.setPosition(Constants.WIDTH / 2 - gameModeSelectBox.getWidth() / 2,
                Constants.HEIGHT / 2 + 40);
        gameModeSelectBox.setAlignment(Align.center);

        untilText = new Label("Until:", Assets.MENU_SKIN);
        untilText.setPosition(Constants.WIDTH / 2 - gameModeSelectBox.getWidth() / 2 + 70,
                Constants.HEIGHT / 2 + 65 - Constants.MENU_ITEM_MARGIN_Y * 1);

        untilTextField = new TextField("10", Assets.MENU_SKIN);
        untilTextField.setSize(160, 140);
        untilTextField.setAlignment(Align.center);
        untilTextField.setMaxLength(3);
        untilTextField.setPosition(Constants.WIDTH / 2 - gameModeSelectBox.getWidth() / 2 + 260,
                Constants.HEIGHT / 2 + 40 - Constants.MENU_ITEM_MARGIN_Y * 1);

        playButton = new TextButton("Play", Assets.MENU_SKIN);
        playButton.setSize(500, 140);
        playButton.setPosition(Constants.WIDTH / 2 - playButton.getWidth() / 2,
                Constants.HEIGHT / 2 + 40 - Constants.MENU_ITEM_MARGIN_Y * 2);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.game.setScreen(new GameScreen(screen.game, getGameMode(), GamePlayerMode.SINGLEPLAYER, getUntilSettings()));
                screen.dispose();
            }
        });

        addActor(playButton);
        addActor(gameModeSelectBox);
        addActor(untilTextField);
        addActor(untilText);
    }

    protected GameMode getGameMode() {
        return gameModeSelectBox.getSelected();
    }

    protected int getUntilSettings() {
        return Integer.parseInt(untilTextField.getText());
    }

    protected void hideUntilSettings() {
        untilTextField.setVisible(false);
        untilText.setVisible(false);
    }

    protected void showUntilSettings() {
        untilTextField.setVisible(true);
        untilText.setVisible(true);
    }

    protected void checkUntilSettings() {
        try {
            if (Integer.parseInt(untilTextField.getText()) > 100) {
                untilTextField.setText("100");
            } else if (Integer.parseInt(untilTextField.getText()) < 1)
                untilTextField.setText("1");
        } catch (NumberFormatException e) {

        }
    }
}
