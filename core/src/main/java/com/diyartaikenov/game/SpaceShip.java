package com.diyartaikenov.game;

import static com.badlogic.gdx.utils.Align.center;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.diyartaikenov.game.base.actors.PhysicsActor;

public class SpaceShip extends PhysicsActor implements InputProcessor {
    private static final float ACCELERATION = 200;
    private static final float HALF_ACCELERATION = ACCELERATION / 2;
    private static final float ROTATE_AMOUNT = 5;

    private final Texture laserTexture;
    private boolean isTurningRight, isTurningLeft;
    private boolean isReverseThrustOn;

    public SpaceShip(float x, float y, Stage stage) {
        super(new Texture(Gdx.files.internal("spaceship.png")), stage);
        setPosition(x, y);
        setOrigin(center);
        setMaxSpeed(5000);
        laserTexture = new Texture(Gdx.files.internal("laser.png"));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (isTurningLeft) {
            rotateBy(ROTATE_AMOUNT);
        }
        if (isTurningRight) {
            rotateBy(-ROTATE_AMOUNT);
        }
        if (isReverseThrustOn) {
            setAccelerationWith(getRotation() - 180, HALF_ACCELERATION);
        } else {
            setAccelerationWith(getRotation(), getAcceleration());
        }
        wrapAroundWorld();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.A) {
            isTurningLeft = true;
        }
        if (keycode == Keys.D) {
            isTurningRight = true;
        }
        if (keycode == Keys.W) {
            accelerateInCurrentDirection(ACCELERATION);
        }
        if (keycode == Keys.S) {
            isReverseThrustOn = true;
        }
        if (keycode == Keys.SPACE) {
            // todo: replace with shoot laser
            setAcceleration(0);
            setSpeed(0);
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.A) {
            isTurningLeft = false;
        }
        if (keycode == Keys.D) {
            isTurningRight = false;
        }
        if (keycode == Keys.W) {
            setAcceleration(0);
        }
        if (keycode == Keys.S) {
            isReverseThrustOn = false;
            setAcceleration(0);
        }

        return true;
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
