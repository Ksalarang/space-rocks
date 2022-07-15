package com.diyartaikenov.game.base;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class BaseGame extends Game {
    public SpriteBatch batch;
    public ShapeRenderer shapeRenderer;
    public BitmapFont defaultFont;

    public TextButtonStyle textButtonStyle;
    public LabelStyle labelStyle;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());

        defaultFont = new BitmapFont();

        textButtonStyle = new TextButtonStyle();
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.font = defaultFont;

        labelStyle = new LabelStyle();
        labelStyle.fontColor = Color.WHITE;
        labelStyle.font = defaultFont;
    }

    public void setDefaultFont(BitmapFont font) {
        defaultFont = font;
        textButtonStyle.font = font;
        labelStyle.font = font;
    }
}
