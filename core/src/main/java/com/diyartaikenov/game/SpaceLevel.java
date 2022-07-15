package com.diyartaikenov.game;

import com.badlogic.gdx.Game;
import com.diyartaikenov.game.base.screens.BaseScreen;

public class SpaceLevel extends BaseScreen {
    private SpaceRocksGame spaceGame;

    public SpaceLevel(Game game) {
        super(game);
    }

    @Override
    protected void create(Game game) {
        spaceGame = (SpaceRocksGame) game;
    }

    @Override
    protected void update(float delta) {

    }
}
