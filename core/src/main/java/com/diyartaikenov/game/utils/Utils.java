package com.diyartaikenov.game.utils;

import com.badlogic.gdx.math.Vector2;

public class Utils {
    /**
     * Return coordinates that are centered within a rectangle with provided width and height.
     * @param width the width of the containing rectangle.
     * @param height the height of the containing rectangle.
     * @param elementWidth the width of the element that is being centered.
     * @param elementHeight the height of the element that is being centered.
     */
    public static Vector2 center(
            float width,
            float height,
            float elementWidth,
            float elementHeight
    ) {
        return new Vector2(
                width / 2 - elementWidth / 2,
                height / 2 - elementHeight / 2
        );
    }
}
