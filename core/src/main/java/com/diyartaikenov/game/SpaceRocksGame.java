package com.diyartaikenov.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.diyartaikenov.game.base.BaseGame;

public class SpaceRocksGame extends BaseGame {
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;

	@Override
	public void create() {
		super.create();
		BitmapFont font64 = new BitmapFont(Gdx.files.internal("fonts/Arial-64.fnt"));
		setDefaultFont(font64);
		Color overColor = new Color(0x99CCFFFF);
		textButtonStyle.overFontColor = overColor;
		textButtonStyle.focusedFontColor = overColor;
		textButtonStyle.downFontColor = new Color(0x66B2FFFF);
		setScreen(new MainMenu(this));
	}
}