package com.diyartaikenov.game.base.actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Provides animating capabilities.
 */
public class AnimatedActor extends PhysicsActor {
    protected Animation<TextureRegion> animation = null;
    protected float elapsedTime;

    public AnimatedActor(Animation<TextureRegion> animation) {
        super(animation.getKeyFrame(0.0f).getTexture());
        setAnimation(animation);
    }

    public void setAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        elapsedTime += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (animation != null) {
            textureRegion.setRegion(animation.getKeyFrame(elapsedTime));
        }
        super.draw(batch, parentAlpha);
    }
}
