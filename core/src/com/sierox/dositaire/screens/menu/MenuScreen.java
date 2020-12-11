package com.sierox.dositaire.screens.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.sierox.dositaire.Dositaire;
import com.sierox.dositaire.util.managers.BackgroundManager;

/**
 * Created by Sierox on 01/02/2017.
 */
public class MenuScreen implements Screen {

    protected Dositaire game;
    private MenuScreenUI ui;

    public MenuScreen(Dositaire game) {
        this.game = game;
        initializeUi();
        initializeCamera();
        Gdx.input.setCatchBackKey(true);

        //ADS
        game.showAd();
    }

    private void initializeCamera() {
        game.resetCamera();
    }

    private void initializeUi() {
        ui = new MenuScreenUI(this);
        Gdx.input.setInputProcessor(ui);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        drawMenu();
        ui.act(delta);
        ui.draw();
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            // SURE?
        }
    }

    private void drawMenu() {
        game.batch.begin();
        game.batch.draw(BackgroundManager.getChosenMenu(), 0, 0);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        ui.dispose();
    }
}
