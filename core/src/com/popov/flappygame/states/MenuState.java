package com.popov.flappygame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.popov.flappygame.FlappyGame;

public class MenuState extends State {

    private Texture backgroundTexture = new Texture("background.png");
    private Texture playButtonTexture = new Texture("play_button.png");

    public MenuState(FlappyGame flappyGame) {
        super(flappyGame);
        resize(flappyGame.getScreenWidth(), flappyGame.getScreenHeight());
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            flappyGame.getGameStateManager().set(new PlayState(flappyGame));
        }
    }

    @Override
    public void update(float delta) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        camera.setToOrtho(
                false,
                flappyGame.getScreenWidth(),
                flappyGame.getScreenHeight());
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(
                backgroundTexture,
                0, 0,
                flappyGame.getScreenWidth(), flappyGame.getScreenHeight());
        spriteBatch.draw(
                playButtonTexture,
                (flappyGame.getScreenWidth() - playButtonTexture.getWidth()*flappyGame.getFactorX())/2,
                (flappyGame.getScreenHeight() - playButtonTexture.getHeight()*flappyGame.getFactorY())/2,
                playButtonTexture.getWidth() * flappyGame.getFactorX(),
                playButtonTexture.getHeight() * flappyGame.getFactorY());
        spriteBatch.end();
    }

    @Override
    public void resize(int x, int y) {
        camera.setToOrtho(false, x, y);
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        playButtonTexture.dispose();
    }
}
