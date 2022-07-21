package com.diyartaikenov.game;

import static com.badlogic.gdx.math.MathUtils.cosDeg;
import static com.badlogic.gdx.math.MathUtils.sinDeg;
import static com.badlogic.gdx.utils.Align.center;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.diyartaikenov.game.base.actors.PhysicsActor;

public class SpaceShip extends PhysicsActor implements InputProcessor {
    private static final float ACCELERATION = 200;
    private static final float HALF_ACCELERATION = ACCELERATION / 2;
    private static final float ROTATE_AMOUNT = 5;

    private Texture laserTexture;
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
        makeTurn();
        updateAcceleratingDirection();
        wrapAroundWorld();
    }

    private void makeTurn() {
        if (isTurningLeft) {
            rotateBy(ROTATE_AMOUNT);
        }
        if (isTurningRight) {
            rotateBy(-ROTATE_AMOUNT);
        }
    }

    private void updateAcceleratingDirection() {
        if (isReverseThrustOn) {
            float oppositeDirection = getRotation() - 180;
            setAccelerationWith(oppositeDirection, HALF_ACCELERATION);
        } else {
            setAccelerationWith(getRotation(), getAcceleration());
        }
    }

    private void shoot() {
        // fixme: lasers are slightly off to the right when the ship is facing north or south
        float angle = getRotation();
        float cx = getX() + getOriginX();
        float cy = getY() + getOriginY();
        float x = getX() + getWidth();

        float y = getY();
        float y2 = getY() + getHeight() - laserTexture.getHeight();

        Vector2 leftLaserPos = getRotatedPoint(x, y2, cx, cy, angle);
        Vector2 rightLaserPos = getRotatedPoint(x, y, cx, cy, angle);
        Laser leftLaser = new Laser(leftLaserPos.x, leftLaserPos.y, laserTexture, getStage());
        Laser rightLaser = new Laser(rightLaserPos.x, rightLaserPos.y, laserTexture, getStage());
        leftLaser.setRotation(angle);
        rightLaser.setRotation(angle);
    }

    private Vector2 getRotatedPoint(float x, float y, float cx, float cy, float angle) {
        float ox = x - cx;
        float oy = y - cy;
        float rx = ox * cosDeg(angle) - oy * sinDeg(angle) + cx;
        float ry = ox * sinDeg(angle) + oy * cosDeg(angle) + cy;
        return new Vector2(rx, ry);
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
            shoot();
        }
        if (keycode == Keys.ALT_LEFT) {
            // todo: temporary
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
