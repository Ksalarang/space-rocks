package com.diyartaikenov.game.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.diyartaikenov.game.utils.callback.Callback;

import java.util.function.Function;

public class Utils {
    /**
     * Return coordinates that are centered within a rectangle with provided width and height.
     * @param width the width of the containing rectangle.
     * @param height the height of the containing rectangle.
     * @param elementWidth the width of the element that is being centered.
     * @param elementHeight the height of the element that is being centered.
     */
    public static Vector2 center(float width, float height, float elementWidth, float elementHeight) {
        return new Vector2(
                width / 2 - elementWidth / 2,
                height / 2 - elementHeight / 2
        );
    }

    /**
     * Add a click listener to the given actor.
     * @param onClick the callback invoked when the actor is clicked.
     */
    public static void addClickListener(Actor actor, Callback onClick) {
        actor.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onClick.invoke();
            }
        });
    }
}
