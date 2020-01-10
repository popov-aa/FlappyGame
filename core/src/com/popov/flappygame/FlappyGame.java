package com.popov.flappygame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.popov.flappygame.states.GameStateManager;
import com.popov.flappygame.states.MenuState;

import lombok.Getter;

public class FlappyGame extends ApplicationAdapter {

	@Getter
	private int screenWidth;
	@Getter
	private int screenHeight;
	@Getter
	private float factorX;
	@Getter
	private float factorY;

	@Getter
	public final String title = "Flappy Game";
	@Getter
	private GameStateManager gameStateManager = new GameStateManager();

	private SpriteBatch batch;

	@Override
	public void create () {
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		batch = new SpriteBatch();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gameStateManager.push(new MenuState(this));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}

	@Override
	public void resize (int width, int height) {
		screenWidth = Gdx.graphics.getWidth();
		factorX = screenWidth / 1080f;
		screenHeight = Gdx.graphics.getHeight();
		factorY = screenHeight / 1920f;
	}

}
