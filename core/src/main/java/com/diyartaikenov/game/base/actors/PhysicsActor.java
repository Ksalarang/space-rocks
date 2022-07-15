package com.diyartaikenov.game.base.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class PhysicsActor extends BaseActor {
    // todo: what does this constant represent?
    protected final float ROTATION_CONSTANT = 57.295776F;

    private final Vector2 velocity = new Vector2();
    private final Vector2 acceleration = new Vector2();
    private float maxSpeed = 1000F;
    private boolean isAutoRotating;
    protected boolean isDecelerating;
    protected float deceleration = 50;

    public PhysicsActor() {}

    public PhysicsActor(int width, int height, Color color) {
        super(width, height, color);
    }

    public PhysicsActor(Texture texture) {
        super(texture);
    }

    protected void applyRotationToVelocity() {
        setVelocityWih(getRotation(), getSpeed());
    }

    protected void setVelocityWithCurrentRotation(float speed) {
        setVelocityWih(getRotation(), speed);
    }

    /**
     * Set velocity with given angle in degrees and speed.
     * @param angleDegrees describes the direction in 2D space, in which this actor will move.
     */
    protected void setVelocityWih(float angleDegrees, float speed) {
        velocity.x = speed * MathUtils.cosDeg(angleDegrees);
        velocity.y = speed * MathUtils.sinDeg(angleDegrees);
    }

    public float getSpeed() {
        return velocity.len();
    }

    public void setSpeed(float speed) {
        // if the previous value of the velocity length was zero,
        // then the setLength() method won't work.
        // To make it work, change the velocity's value first.
        if (velocity.x == 0 && velocity.y == 0) {
            setVelocityWih(getRotation(), 1);
        }
        velocity.setLength(speed);
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float ms) {
        maxSpeed = ms;
    }

    /**
     * todo
     * @return
     */
    public float getMotionAngle() {
        return MathUtils.atan2(velocity.y, velocity.x) * ROTATION_CONSTANT;
    }

    public void setAutoRotation(boolean autoRotation) {
        isAutoRotating = autoRotation;
    }

    protected void setAcceleration(float a) {
        if (acceleration.x == 0 && acceleration.y == 0) {
            setAccelerationWith(getRotation(), 1);
        }
        acceleration.setLength(a);
    }

    protected void setAccelerationWith(float angleDegrees, float acceleration) {
        this.acceleration.x = acceleration * MathUtils.cosDeg(angleDegrees);
        this.acceleration.y = acceleration * MathUtils.sinDeg(angleDegrees);
    }

    public void accelerateInCurrentDirection(float acceleration) {
        setAccelerationWith(getRotation(), acceleration);
    }

    public float getAcceleration() {
        return acceleration.len();
    }

    public boolean isAccelerating() {
        return acceleration.x != 0 || acceleration.y != 0;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (isAutoRotating && getSpeed() > 0.1f) {
            setRotation(getMotionAngle());
        }
        if (isDecelerating) {
            float decelerateRate = deceleration * delta;
            float speed = getSpeed();
            if (speed < decelerateRate) {
                setSpeed(0);
            } else {
                setSpeed(speed - decelerateRate);
            }
        }
        setAccelerationWith(getRotation(), getAcceleration());
        velocity.add(acceleration.x * delta, acceleration.y * delta);
        applyRotationToVelocity();
        if (getSpeed() > maxSpeed) {
            setSpeed(maxSpeed);
        }
        moveBy(velocity.x * delta, velocity.y * delta);
    }
}
