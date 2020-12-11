package com.sierox.dositaire.screens.options;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sierox.dositaire.util.Assets;
import com.sierox.dositaire.util.Constants;
import com.sierox.dositaire.util.managers.BackgroundManager;
import com.sierox.dositaire.util.managers.MusicManager;

public class OptionsScreenUI extends Stage {

    private OptionsScreen screen;

    private Button playingToggleButton;
    private Label playingLabel;

    private TextButton musicChangeButton;
    private TextButton backgroundChangeButton;

    public OptionsScreenUI(OptionsScreen screen) {
        this.screen = screen;
        setViewport(screen.game.viewport);
        initializeUiComponents();
    }

    private void initializeUiComponents() {

        playingLabel = new Label("Music On", Assets.MENU_SKIN);
        playingLabel.setPosition(Constants.WIDTH / 2 - 120,
                Constants.HEIGHT / 2 + 90 - Constants.MENU_ITEM_MARGIN_Y * 0);

        playingToggleButton = new Button(Assets.MENU_SKIN);
        playingToggleButton.setSize(80, 80);
        playingToggleButton.setPosition(Constants.WIDTH / 2 - 230,
                Constants.HEIGHT / 2 + 95 - Constants.MENU_ITEM_MARGIN_Y * 0);
        playingToggleButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MusicManager.togglePlaying();
                updateMusicUi();
            }
        });

        musicChangeButton = new TextButton("Next Music", Assets.MENU_SKIN);
        musicChangeButton.setSize(500, 140);
        musicChangeButton.setPosition(Constants.WIDTH / 2 - musicChangeButton.getWidth() / 2,
                Constants.HEIGHT / 2 + 65 - Constants.MENU_ITEM_MARGIN_Y * 1);
        musicChangeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MusicManager.cycle();
            }
        });

        backgroundChangeButton = new TextButton("Next Skin", Assets.MENU_SKIN);
        backgroundChangeButton.setSize(500, 140);
        backgroundChangeButton.setPosition(Constants.WIDTH / 2 - backgroundChangeButton.getWidth() / 2,
                Constants.HEIGHT / 2 + 65 - Constants.MENU_ITEM_MARGIN_Y * 2);
        backgroundChangeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                BackgroundManager.cycle();
            }
        });

        addActor(playingToggleButton);
        addActor(playingLabel);
        addActor(musicChangeButton);
        addActor(backgroundChangeButton);
        updateMusicUi();
    }

    private void updateMusicUi() {
        if (MusicManager.isPlaying()) {
            musicChangeButton.setTouchable(Touchable.enabled);
            playingLabel.setText("Music On");
        } else {
            musicChangeButton.setTouchable(Touchable.disabled);
            playingLabel.setText("Music Off");
        }
    }
}
