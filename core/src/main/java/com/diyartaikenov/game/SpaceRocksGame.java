package com.diyartaikenov.game;

import static com.diyartaikenov.game.utils.UrlConstants.BACKGROUND;
import static com.diyartaikenov.game.utils.UrlConstants.FNT;
import static com.diyartaikenov.game.utils.UrlConstants.FONT_ARIAL_64;
import static com.diyartaikenov.game.utils.UrlConstants.LASER;
import static com.diyartaikenov.game.utils.UrlConstants.PNG;
import static com.diyartaikenov.game.utils.UrlConstants.SPACESHIP;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.diyartaikenov.game.base.BaseGame;
import com.diyartaikenov.game.managers.SimpleAssetManager;

public class SpaceRocksGame extends BaseGame {
	public static int WIDTH = 1920;
	public static int HEIGHT = 1080;

	public SimpleAssetManager assetManager;
	private final String defaultFontFileName = FONT_ARIAL_64 + FNT;
	private boolean isMainMenuActive;

	@Override
	public void create() {
		super.create();
		// set world width to the actual screen width
		if (Gdx.graphics.getWidth() > WIDTH) {
			WIDTH = Gdx.graphics.getWidth();
		}
		assetManager = new SimpleAssetManager();
		addAssetsToLoadingQueue();
		setButtonStyleFontColors();
	}

	private void addAssetsToLoadingQueue() {
		assetManager.addFontToLoad(defaultFontFileName);
		assetManager.addTextureToLoad(BACKGROUND + PNG);
		assetManager.addTextureToLoad(SPACESHIP + PNG);
		assetManager.addTextureToLoad(LASER + PNG);
	}

	private void setButtonStyleFontColors() {
		Color overFontColor = new Color(0x99CCFFFF);
		textButtonStyle.overFontColor = overFontColor;
		textButtonStyle.focusedFontColor = overFontColor;
		textButtonStyle.downFontColor = new Color(0x66B2FFFF);
	}

	@Override
	public void render() {
		super.render();
		// if loading is finished, set screen to MainMenu.
		if (!isMainMenuActive && assetManager.updateLoading()) {
			setDefaultFont(assetManager.getFont(defaultFontFileName));
			setScreen(new MainMenu(this));
			isMainMenuActive = true;
		}
	}
}