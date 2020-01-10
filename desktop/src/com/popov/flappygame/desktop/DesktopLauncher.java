package com.popov.flappygame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.popov.flappygame.FlappyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		FlappyGame flappyGame = new FlappyGame();
		config.width = flappyGame.getScreenWidth();
		config.height = flappyGame.getScreenHeight();
		config.title = flappyGame.getTitle();
		new LwjglApplication(flappyGame, config);
	}
}
