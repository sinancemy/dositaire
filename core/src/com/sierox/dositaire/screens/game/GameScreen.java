package com.sierox.dositaire.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.sierox.dositaire.Dositaire;
import com.sierox.dositaire.game.algorithm.GamePlayerMode;
import com.sierox.dositaire.util.Assets;
import com.sierox.dositaire.util.Constants;
import com.sierox.dositaire.game.algorithm.Algorithm;
import com.sierox.dositaire.game.algorithm.GameMode;
import com.sierox.dositaire.game.algorithm.GameTurn;
import com.sierox.dositaire.util.managers.BackgroundManager;
import com.sierox.dositaire.screens.menu.MenuScreen;

/**
 * Created by Sierox on 01/02/2017.
 */
public class GameScreen implements Screen {

    protected Dositaire game;
    private GameScreenUI ui;
    protected Algorithm algorithm;
    protected GestureDetector cardInputProcessor;
    public boolean gameRunning;

    public GameMode gameMode;
    public int until;

    private boolean exitDialog;

    public GameScreen(Dositaire game, GameMode gameMode, GamePlayerMode gamePlayerMode, int until) {
        this.game = game;
        algorithm = new Algorithm(game, gameMode, gamePlayerMode, until);
        gameRunning = true;
        initializeUi();
        initializeInputProcessor();
        initializeCamera();
        Gdx.input.setCatchBackKey(true);

        this.gameMode = gameMode;
        this.until = until;
        exitDialog = false;
    }

    private void initializeCamera() {
        game.resetCamera();
    }

    private void initializeUi() {
        ui = new GameScreenUI(this);
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
        game.batch.begin();
        drawBoardAndIndicator();
        game.batch.end();

        ui.updateUILabels();
        ui.act(delta);
        ui.draw();

        if (gameRunning) {
            game.batch.begin();
            renderCards();
            checkVictoryConditions();
            checkReturnToMenu();
            game.batch.end();
        }
    }

    private void renderCards(){
        algorithm.player1.render(game.batch);
        algorithm.player2.render(game.batch);
        algorithm.middle.render(game.batch);
        algorithm.collector.render(game.batch);
    }

    private void checkReturnToMenu() {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK) && !exitDialog) {
            exitDialog = true;
            gameRunning = false;
            Dialog dialog = new Dialog("", Assets.GAME_SKIN, "dialog") {
                public void result(Object obj) {
                    if (obj.equals(true))
                        backToMenu();
                    else {
                        gameRunning = true;
                        exitDialog = false;
                    }
                }
            };
            dialog.getBackground().setMinHeight(150);
            dialog.getBackground().setMinWidth(600);
            dialog.text("Go back to the main menu?");
            dialog.button("Yes", true);
            dialog.button("No", false);
            dialog.setMovable(false);
            dialog.show(ui);
        }
    }

    private void checkVictoryConditions() {
        if (algorithm.player1.getPoints() + algorithm.player2.getPoints() == 6 && gameMode == GameMode.CLASSIC) {
            String victoryMessage;
            if (algorithm.player1.getPoints() > algorithm.player2.getPoints())
                victoryMessage = "Player 1 wins " + algorithm.player1.getPoints() + " to " + algorithm.player2.getPoints() + "!";
            else if (algorithm.player1.getPoints() < algorithm.player2.getPoints())
                victoryMessage = "Player 2 wins " + algorithm.player2.getPoints() + " to " + algorithm.player1.getPoints() + "!";
            else
                victoryMessage = "It's a draw, 3 to 3!";

            endGame(victoryMessage);

        } else if (gameMode == GameMode.UNTIL) {
            if (algorithm.player1.getPoints() == until) {
                endGame("Player 1 wins " + algorithm.player1.getPoints() + " to " + algorithm.player2.getPoints() + "!");
            } else if (algorithm.player2.getPoints() == until) {
                endGame("Player 2 wins " + algorithm.player2.getPoints() + " to " + algorithm.player1.getPoints() + "!");
            }
        }
    }

    private void endGame(String victoryMessage) {
        gameRunning = false;
        Dialog dialog = new Dialog("", Assets.GAME_SKIN, "dialog") {
            public void result(Object obj) {
                backToMenu();
            }
        };
        dialog.getBackground().setMinHeight(140);
        dialog.getBackground().setMinWidth(500);
        dialog.text(victoryMessage);
        dialog.button("OK", true);
        dialog.show(ui);
    }

    private void backToMenu() {
        game.setScreen(new MenuScreen(game));
        dispose();
    }

    private void drawBoardAndIndicator() {
        game.batch.draw(BackgroundManager.getChosenBoard(), 0, 0);
        if (algorithm.getTurn() == GameTurn.PLAYER1)
            game.batch.draw(BackgroundManager.getChosenIndicator(true), 29,
                    Constants.HEIGHT / 2 - BackgroundManager.getChosenIndicator(true).getHeight());
        else
            game.batch.draw(BackgroundManager.getChosenIndicator(false), 29,
                    Constants.HEIGHT / 2);
    }

    private void initializeInputProcessor() {
        cardInputProcessor = new GestureDetector(new CardGestureListener());
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(cardInputProcessor);
        inputMultiplexer.addProcessor(ui);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private class CardGestureListener implements GestureDetector.GestureListener {

        @Override
        public boolean touchDown(float x, float y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean tap(float x, float y, int count, int button) {
            Vector2 tapPosition = game.viewport.unproject(new Vector2(x, y));
            algorithm.checkSelections((int) tapPosition.x, (int) tapPosition.y);
            algorithm.checkMove();
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
        ui.dispose();
    }
}
