package com.diyartaikenov.game;

import static com.diyartaikenov.game.SpaceRocksGame.HEIGHT;
import static com.diyartaikenov.game.SpaceRocksGame.WIDTH;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.diyartaikenov.game.base.actors.BaseActor;
import com.diyartaikenov.game.base.screens.BaseScreen;

public class SpaceLevel extends BaseScreen {
    private SpaceRocksGame spaceGame;

    private BaseActor background;

    public SpaceLevel(Game game) {
        super(game);
    }

    @Override
    protected void create(Game game) {
        spaceGame = (SpaceRocksGame) game;
        mainStage.setViewport(new ExtendViewport(WIDTH, HEIGHT));
        uiStage.setViewport(new ExtendViewport(WIDTH, HEIGHT));

        Texture starfield = new Texture(Gdx.files.internal("starfield_extended.png"));
        background = new BaseActor(starfield);

        mainStage.addActor(background);
    }

    @Override
    protected void update(float delta) {}

    @Override
    public void dispose() {
        super.dispose();
        background.dispose();
    }
}
