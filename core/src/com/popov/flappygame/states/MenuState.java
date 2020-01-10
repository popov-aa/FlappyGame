package com.popov.flappygame.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.popov.flappygame.FlappyGame;

public class MenuState extends State {

    private Texture backgroundTexture = new Texture("background.png");
    private Texture playButtonTexture = new Texture("play_button.png");

    public MenuState(FlappyGame flappyGame) {
        super(flappyGame);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(
                backgroundTexture,
                0, 0,
                flappyGame.getScreenWidth(), flappyGame.getScreenHeight());
        spriteBatch.draw(
                playButtonTexture,
                (flappyGame.getScreenWidth() - playButtonTexture.getWidth())/2,
                (flappyGame.getScreenHeight() - playButtonTexture.getHeight())/2,
                playButtonTexture.getWidth(),
                playButtonTexture.getHeight());
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        playButtonTexture.dispose();
    }
}
