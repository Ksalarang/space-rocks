package com.diyartaikenov.game.ship;

import static com.badlogic.gdx.math.MathUtils.cosDeg;
import static com.badlogic.gdx.math.MathUtils.sinDeg;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.diyartaikenov.game.base.actors.PhysicsActor;

public class SpaceShip extends PhysicsActor implements InputProcessor {
    private static final float ACCELERATION = 200;
    private static final float HALF_ACCELERATION = ACCELERATION / 2;
    private static final float ROTATE_AMOUNT = 5;
    private static final float LASER_SPEED = 400;

    private final Texture laserTexture;
    private final PhysicsActor exhaust;
    private float exhaustOffsetX;
    private float exhaustOffsetY;
    private boolean isTurningRight, isTurningLeft;
    private boolean isReverseThrustOn;

    public SpaceShip(Texture ship, Texture laser, Texture exhaust, Stage stage) {
        super(ship, stage);
        setOrigin(Align.center);
        setMaxSpeed(5000);
        laserTexture = laser;

        this.exhaust = new PhysicsActor(exhaust, stage);
        this.exhaust.setVisible(false);
        // just guessed these numbers, don't really know what's going on.
        exhaustOffsetX = exhaust.getWidth() * 1.48f;
        exhaustOffsetY = getHeight() / 2.6f;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        makeTurn();
        updateAcceleratingDirection();
        wrapAroundWorld();
        if (exhaust.isVisible()) {
            updateExhaustPosition();
        }
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

    private void updateExhaustPosition() {
        exhaust.setRotation(getRotation() - 180);
        float angle = exhaust.getRotation();
        float x = getX() + exhaustOffsetX;
        float y = getY() + exhaustOffsetY;
        float cx = getX() + getOriginX();
        float cy = getY() + getOriginY();
        Vector2 pos = getRotatedPoint(x, y, cx, cy, angle);
        exhaust.setPosition(pos.x, pos.y);
    }

    private void shoot() {
        float angle = getRotation();

        float x = getX() + getWidth();
        float y = getY();
        float y2 = getY() + getHeight() - laserTexture.getHeight();

        float cx = getX() + getOriginX();
        float cy = getY() + getOriginY();

        Vector2 leftLaserPos = getRotatedPoint(x, y2, cx, cy, angle);
        Vector2 rightLaserPos = getRotatedPoint(x, y, cx, cy, angle);
        Laser leftLaser = new Laser(leftLaserPos.x, leftLaserPos.y, laserTexture, getStage());
        Laser rightLaser = new Laser(rightLaserPos.x, rightLaserPos.y, laserTexture, getStage());
        // fixme: add up ship's velocity with lasers'
        float speed = LASER_SPEED;
        leftLaser.setRotationAndSpeed(speed, angle);
        rightLaser.setRotationAndSpeed(speed, angle);
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
            exhaust.setVisible(true);
        }
        if (keycode == Keys.S) {
            isReverseThrustOn = true;
        }
        if (keycode == Keys.SPACE) {
            shoot();
        }
        if (keycode == Keys.ALT_LEFT) {
            // fixme: temporary for testing
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
            exhaust.setVisible(false);
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
