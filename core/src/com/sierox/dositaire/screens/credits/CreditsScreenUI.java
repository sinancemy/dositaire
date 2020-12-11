package com.sierox.dositaire.screens.credits;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sierox.dositaire.util.Assets;
import com.sierox.dositaire.util.Constants;

/**
 * Created by Sierox on 01/02/2017.
 */
public class CreditsScreenUI extends Stage {

    private CreditsScreen screen;

    private Label musicLabel;
    private Label musicLabel2;

    private Label cardsLabel;
    private Label cardsLabel2;

    public CreditsScreenUI(CreditsScreen screen) {
        this.screen = screen;
        setViewport(screen.game.viewport);
        initializeUiComponents();
    }

    private void initializeUiComponents() {
        musicLabel = new Label("Music by:", Assets.MENU_SKIN);
        musicLabel2 = new Label("https://www.bensound.com", Assets.MENU_SKIN);
        musicLabel.setFontScale(0.6f);
        musicLabel2.setFontScale(0.6f);
        musicLabel2.setColor(0.3f, 0.6f, 1f, 1f);
        musicLabel2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.net.openURI("https://www.bensound.com");
            }
        });
        musicLabel.setPosition(Constants.WIDTH / 2 - 6 * musicLabel.getWidth() / 20,
                Constants.HEIGHT / 2 + 100);
        musicLabel2.setPosition(Constants.WIDTH / 2 - 6 * musicLabel2.getWidth() / 20,
                Constants.HEIGHT / 2 + 45);

        cardsLabel = new Label("Cards by:", Assets.MENU_SKIN);
        cardsLabel2 = new Label("http://acbl.mybigcommerce.com", Assets.MENU_SKIN);
        cardsLabel.setFontScale(0.6f);
        cardsLabel2.setFontScale(0.5f);
        cardsLabel2.setColor(0.3f, 0.6f, 1f, 1f);
        cardsLabel2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.net.openURI("http://acbl.mybigcommerce.com/52-playing-cards/");
            }
        });
        cardsLabel.setPosition(Constants.WIDTH / 2 - 6 * cardsLabel.getWidth() / 20,
                Constants.HEIGHT / 2 - 50);
        cardsLabel2.setPosition(Constants.WIDTH / 2 - 5 * cardsLabel2.getWidth() / 20,
                Constants.HEIGHT / 2 - 105);

        addActor(musicLabel);
        addActor(musicLabel2);
        addActor(cardsLabel);
        addActor(cardsLabel2);
    }
}

