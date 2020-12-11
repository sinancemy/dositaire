package com.sierox.dositaire.screens.tutorial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.sierox.dositaire.Dositaire;
import com.sierox.dositaire.util.Assets;
import com.sierox.dositaire.screens.menu.MenuScreen;

/**
 * Created by Sierox on 01/02/2017.
 */
public class TutorialScreen implements Screen {

    protected Dositaire game;
    protected GestureDetector slideInputProcessor;

    protected int slideNo;

    public TutorialScreen(Dositaire game) {
        this.game = game;
        initializeInputProcessor();
        slideNo = 0;
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        drawSlides();

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.setScreen(new MenuScreen(game));
            dispose();
        }
    }

    private void drawSlides() {
        game.batch.begin();
        game.batch.draw(Assets.TUTORIAL_SLIDES.get(slideNo), 0, 0);
        game.batch.end();
    }

    private void initializeInputProcessor() {
        slideInputProcessor = new GestureDetector(new MapScreenGestureListener());
        Gdx.input.setInputProcessor(slideInputProcessor);
    }

    private class MapScreenGestureListener implements GestureDetector.GestureListener {

        @Override
        public boolean touchDown(float x, float y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean tap(float x, float y, int count, int button) {
            slideNo++;
            if (Assets.TUTORIAL_SLIDES.size() == slideNo) {
                game.setScreen(new MenuScreen(game));
                dispose();
            }
            return false;
        }

        @Override
        public boolean longPress(float x, float y) {
            return false;
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {
            return false;
        }

        @Override
        public boolean pan(float x, float y, float deltaX, float deltaY) {
            return false;
        }

        @Override
        public boolean panStop(float x, float y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean zoom(float initialDistance, float distance) {
            return false;
        }

        @Override
        public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
            return false;
        }

        @Override
        public void pinchStop() {

        }
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

    }
}
