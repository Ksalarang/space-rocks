package com.diyartaikenov.game.base.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * A base screen that encapsulates some common properties to be used
 * in other game screens.
 */
public abstract class BaseScreen implements Screen, InputProcessor {
    protected Stage mainStage;
    protected Stage uiStage;
    protected boolean isPaused = false;
    protected InputMultiplexer inputMultiplexer;

    protected Color backgroundColor = Color.BLACK;

    public BaseScreen(Game game) {
        mainStage = new Stage();
        uiStage = new Stage();
        inputMultiplexer = new InputMultiplexer(this, mainStage, uiStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
        create(game);
    }

    protected abstract void create(Game game);
    protected abstract void update(float delta);

    @Override
    public void render(float delta) {
        uiStage.act();
        if (!isPaused) {
            mainStage.act();
            update(delta);
        }
        ScreenUtils.clear(backgroundColor);
        mainStage.draw();
        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        mainStage.getViewport().update(width, height, true);
        uiStage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void show() {}

    @Override
    public void dispose() {
        mainStage.dispose();
        uiStage.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}