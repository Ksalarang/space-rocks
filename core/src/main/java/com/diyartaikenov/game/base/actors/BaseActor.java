package com.diyartaikenov.game.base.actors;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BaseActor extends Actor {
    public static Rectangle worldBounds = new Rectangle();

    protected TextureRegion textureRegion = new TextureRegion();
    protected Polygon boundaryPolygon = null;

    public BaseActor() {
        this(0, 0, Color.WHITE);
    }

    /**
     * Create an actor with a rectangle as its texture.
     * @param width the rectangle width.
     * @param height the rectangle height.
     * @param color the rectangle color.
     */
    public BaseActor(int width, int height, Color color) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        setColor(color);
        pixmap.fillRectangle((int) getX(), (int) getY(), width, height);
        setTexture(new Texture(pixmap));
    }

    public static void setWorldBounds(float width, float height) {
        worldBounds.set(0, 0, width, height);
    }

    /**
     * Keep this actor inside the {@link #worldBounds}.
     */
    public void boundToWorld() {
        if (getX() < 0) {
            setX(0);
        }
        if (getX() + getWidth() > worldBounds.width) {
            setX(worldBounds.width - getWidth());
        }
        if (getY() < 0) {
            setY(0);
        }
        if (getY() + getHeight() > worldBounds.height) {
            setY(worldBounds.height - getHeight());
        }
    }

    public BaseActor(Texture texture) {
        setTexture(texture);
    }

    public void setTexture(Texture texture) {
        textureRegion.setRegion(texture);
        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        setRectanglePolygon();
    }

    public void setRectanglePolygon() {
        float w = getWidth();
        float h = getHeight();
        float[] vertices = {0, 0, w, 0, w, h, 0, h};
        boundaryPolygon = new Polygon(vertices);
    }

    /**
     * Set a polygon that approximates the shape of an ellipse
     * contained within the current rectangle boundary.
     * @param sides the number of sides of the resulting shape. The larger the value,
     *              the smoother the shape will be. However, collision detection for general
     *              polygons is computationally intensive, large values can drastically
     *              slow down the program.
     */
    public void setEllipsePolygon(int sides) {
        float w = getWidth();
        float h = getHeight();
        float twoPi = (float) (2 * Math.PI);
        float[] vertices = new float[2 * sides];

        for (int i = 0; i < sides; i++) {
            float angle = i * twoPi / sides;
            vertices[2 * i] = w/2 * MathUtils.cos(angle) + w/2;
            vertices[2 * i + 1] = h/2 * MathUtils.sin(angle) + h/2;
        }
        boundaryPolygon = new Polygon(vertices);
    }

    public Polygon getBoundaryPolygon() {
        boundaryPolygon.setPosition(getX(), getY());
        boundaryPolygon.setOrigin(getOriginX(), getOriginY());
        boundaryPolygon.setScale(getScaleX(), getScaleY());
        boundaryPolygon.setRotation(getRotation());
        return boundaryPolygon;
    }

    public boolean overlaps(BaseActor other) {
        Polygon thisPoly = getBoundaryPolygon();
        Polygon otherPoly = other.getBoundaryPolygon();

        if (!thisPoly.getBoundingRectangle().overlaps(otherPoly.getBoundingRectangle())) {
            return false;
        }
        return Intersector.overlapConvexPolygons(thisPoly, otherPoly);
    }

    /**
     * Calculate the direction corresponding to the minimal distance this actor needs to be moved
     * so that there is no overlap, and move accordingly.
     * @param other the actor with whom overlap needs to be prevented.
     * @return a minimum translation vector indicating the minimum magnitude vector
     * required to push this actor out of collision with the other actor
     * or null in case of no overlapping.
     */
    public Vector2 preventOverlap(BaseActor other) {
        Polygon thisPoly = getBoundaryPolygon();
        Polygon otherPoly = other.getBoundaryPolygon();

        if (!thisPoly.getBoundingRectangle().overlaps(otherPoly.getBoundingRectangle())) {
            return null;
        }

        Intersector.MinimumTranslationVector vector = new Intersector.MinimumTranslationVector();
        boolean isPolygonsOverlapped = Intersector
                .overlapConvexPolygons(thisPoly, otherPoly, vector);

        if (!isPolygonsOverlapped) {
            return null;
        }
        moveBy(vector.normal.x * vector.depth, vector.normal.y * vector.depth);
        return vector.normal;
    }

    public void alignCamera() {
        Camera camera = getStage().getCamera();
        camera.position.set(getX() + getOriginX(),getY() + getOriginY(), 0);
        camera.position.x = MathUtils.clamp(
                camera.position.x,
                camera.viewportWidth / 2,
                worldBounds.width - camera.viewportWidth / 2
        );
        camera.position.y = MathUtils.clamp(
                camera.position.y,
                camera.viewportHeight / 2,
                worldBounds.height - camera.viewportHeight / 2
        );
        camera.update();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(getColor());
        if (isVisible()) {
            batch.draw(
                    textureRegion,
                    getX(), getY(),
                    getOriginX(), getOriginY(),
                    getWidth(), getHeight(),
                    getScaleX(), getScaleY(),
                    getRotation()
            );
        }
    }
}
