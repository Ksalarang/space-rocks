package com.diyartaikenov.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.diyartaikenov.game.base.actors.PhysicsActor;

public class Laser extends PhysicsActor {
    public Laser(float x, float y, Texture texture, Stage stage) {
        super(texture, stage);
        setPosition(x, y);

        addAction(Actions.delay(3));
        addAction(Actions.after(Actions.fadeOut(0.5f)));
        addAction(Actions.after(Actions.removeActor()));

        setSpeed(400);
        setMaxSpeed(400);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        applyRotationToVelocity();
        wrapAroundWorld();
    }
}
