package com.diyartaikenov.game;

import static com.diyartaikenov.game.SpaceRocksGame.HEIGHT;
import static com.diyartaikenov.game.SpaceRocksGame.WIDTH;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FillViewport;
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
		uiStage.setViewport(new FillViewport(WIDTH, HEIGHT));

		Texture texture = new Texture(Gdx.files.internal("space_background.png"));
		background = new BaseActor(texture);
		playButton = new TextButton("Play", spaceGame.textButtonStyle);
		quitButton = new TextButton("Quit", spaceGame.textButtonStyle);

		uiTable = new Table();
		uiTable.add(playButton).height(100);
		uiTable.row();
		uiTable.add(quitButton);
		Vector2 tablePosition = Utils.center(
				Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(),
				uiTable.getWidth(),
				uiTable.getHeight()
		);
		uiTable.setPosition(tablePosition.x, tablePosition.y);

		uiStage.addActor(background);
		uiStage.addActor(uiTable);
	}

	@Override
	protected void update(float delta) {}
}