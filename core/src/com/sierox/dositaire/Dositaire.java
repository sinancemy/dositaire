package com.sierox.dositaire;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sierox.dositaire.ads.AdHandler;
import com.sierox.dositaire.network.Client;
import com.sierox.dositaire.network.Server;
import com.sierox.dositaire.util.Constants;
import com.sierox.dositaire.util.managers.BackgroundManager;
import com.sierox.dositaire.util.managers.MusicManager;
import com.sierox.dositaire.screens.menu.MenuScreen;

public class Dositaire extends Game {

    public SpriteBatch batch;
    public OrthographicCamera cam;
    public Viewport viewport;
    public static Preferences preferences;

    private AdHandler adHandler;

    public Dositaire() {
    }

    public Dositaire(AdHandler adHandler) {
        this.adHandler = adHandler;
    }

    @Override
    public void create() {
        Server s = new Server();
        managePreferences();
        batch = new SpriteBatch();
        cam = new OrthographicCamera();
        viewport = new ExtendViewport(Constants.WIDTH, Constants.HEIGHT, cam);
        viewport.apply();
        resetCamera();
        setScreen(new MenuScreen(this));
    }

    public void resetCamera() {
        cam.position.set(Constants.WIDTH / 2, Constants.HEIGHT / 2, 0);
        cam.zoom = 1;
        cam.update();
    }

    public void managePreferences() {
        preferences = Gdx.app.getPreferences("preferences");
        if (!preferences.contains("used")) {
            preferences.putBoolean("used", true);
            preferences.putInteger("music", 0);
            preferences.putBoolean("playing", true);
            preferences.putInteger("background", 0);
        }
        MusicManager.setChosenMusic(preferences.getInteger("music"));
        MusicManager.setPlaying(preferences.getBoolean("playing"));
        BackgroundManager.setChosenBackground(preferences.getInteger("background"));
    }

    @Override
    public void render() {
        batch.setProjectionMatrix(cam.combined);
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void resume() {
        super.resume();
        MusicManager.setPlaying(true);
    }

    public void showAd() {
        if (Gdx.app.getType() == Application.ApplicationType.Android)
            adHandler.showOrLoadAdColony();
    }
}
