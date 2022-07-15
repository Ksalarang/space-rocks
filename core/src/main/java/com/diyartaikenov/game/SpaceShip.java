package com.diyartaikenov.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.diyartaikenov.game.base.actors.BaseActor;
import com.diyartaikenov.game.base.actors.PhysicsActor;

public class SpaceShip extends PhysicsActor {
    private final Texture laserTexture;

    public SpaceShip(float x, float y, Stage stage) {
        super(new Texture(Gdx.files.internal("spaceship.png")), stage);
        setPosition(x, y);
        laserTexture = new Texture(Gdx.files.internal("laser.png"));
    }
}
