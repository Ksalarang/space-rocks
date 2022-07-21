package com.diyartaikenov.game;

import static com.diyartaikenov.game.SpaceRocksGame.HEIGHT;
import static com.diyartaikenov.game.SpaceRocksGame.WIDTH;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.diyartaikenov.game.base.actors.BaseActor;
import com.diyartaikenov.game.base.screens.BaseScreen;
import com.diyartaikenov.game.utils.Utils;

public class MainMenu extends BaseScreen {
	private SpaceRocksGame spaceGame;
	private Table uiTable;
	private BaseActor background;
	private TextButton playButton;
	private TextButton quitButton;

	public MainMenu(Game game) {
		super(game);
	}

	@Override
	protected void create(Game game) {
		spaceGame = (SpaceRocksGame) game;
		uiStage.setViewport(new ExtendViewport(WIDTH, HEIGHT));

		Texture starfield = new Texture(Gdx.files.internal("starfield_extended.png"));
		background = new BaseActor(starfield);

		playButton = new TextButton("Play", spaceGame.textButtonStyle);
		quitButton = new TextButton("Quit", spaceGame.textButtonStyle);
		Utils.addClickListener(playButton, this::onPlayButtonClick);
		Utils.addClickListener(quitButton, this::onQuitButtonClick);

		uiTable = new Table();
		addUiTableRows();
		Vector2 tablePosition = Utils.center(Gdx.graphics.getWidth(), HEIGHT, uiTable.getWidth(), uiTable.getHeight());
		uiTable.setPosition(tablePosition.x, tablePosition.y);

		uiStage.addActor(background);
		uiStage.addActor(uiTable);
	}

	private void addUiTableRows() {
		uiTable.add(playButton).height(200);
		uiTable.row();
		uiTable.add(quitButton);
	}

	@Override
	protected void update(float delta) {}

	private void onPlayButtonClick() {
		dispose();
		spaceGame.setScreen(new SpaceLevel(spaceGame));
	}

	private void onQuitButtonClick() {
		dispose();
		Gdx.app.exit();
	}
}