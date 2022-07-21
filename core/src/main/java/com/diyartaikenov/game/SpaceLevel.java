package com.diyartaikenov.game;

import static com.diyartaikenov.game.SpaceRocksGame.HEIGHT;
import static com.diyartaikenov.game.SpaceRocksGame.WIDTH;
import static com.diyartaikenov.game.utils.UrlConstants.BACKGROUND;
import static com.diyartaikenov.game.utils.UrlConstants.LASER;
import static com.diyartaikenov.game.utils.UrlConstants.PNG;
import static com.diyartaikenov.game.utils.UrlConstants.SPACESHIP;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.diyartaikenov.game.base.actors.BaseActor;
import com.diyartaikenov.game.base.actors.PhysicsActor;
import com.diyartaikenov.game.base.screens.BaseScreen;

public class SpaceLevel extends BaseScreen {
    private SpaceRocksGame spaceGame;

    private BaseActor background;
    private SpaceShip ship;

    public SpaceLevel(Game game) {
        super(game);
    }

    @Override
    protected void create(Game game) {
        spaceGame = (SpaceRocksGame) game;
        mainStage.setViewport(new ExtendViewport(WIDTH, HEIGHT));
        uiStage.setViewport(new ExtendViewport(WIDTH, HEIGHT));

        Texture starfield = spaceGame.assetManager.getTexture(BACKGROUND + PNG);
        background = new BaseActor(starfield, mainStage);

        Texture shipTexture = spaceGame.assetManager.getTexture(SPACESHIP + PNG);
        Texture laserTexture = spaceGame.assetManager.getTexture(LASER + PNG);
        ship = new SpaceShip(shipTexture, laserTexture, mainStage);
        ship.setPosition(200, HEIGHT / 2f);
        SpaceShip.setWorldBounds(WIDTH, HEIGHT);

        inputMultiplexer.addProcessor(ship);
    }

    @Override
    protected void update(float delta) {}
}
