package com.popov.flappygame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.popov.flappygame.FlappyGame;
import com.popov.flappygame.sprites.Bird;

public class PlayState extends State {

    private Texture backgroundTexture = new Texture("background.png");
    private Bird bird = new Bird(30, 500);

    public PlayState(FlappyGame flappyGame) {
        super(flappyGame);
        orthographicCamera.setToOrtho(
                false,
                flappyGame.getScreenWidth(),
                flappyGame.getScreenHeight());
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void update(float delta) {
        bird.update(delta);
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        orthographicCamera.setToOrtho(
                false,
                flappyGame.getScreenWidth(),
                flappyGame.getScreenHeight());
        spriteBatch.setProjectionMatrix(orthographicCamera.combined);
        spriteBatch.begin();
        spriteBatch.draw(
                backgroundTexture,
                0, 0,
                flappyGame.getScreenWidth(), flappyGame.getScreenHeight());
        spriteBatch.draw(
                bird.getBirdTexture(),
                bird.getPosition().x,
                bird.getPosition().y,
                bird.getBirdTexture().getWidth() * flappyGame.getFactorX(),
                bird.getBirdTexture().getHeight() * flappyGame.getFactorY());
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
