package com.popov.flappygame.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.popov.flappygame.FlappyGame;
import com.popov.flappygame.sprites.Bird;

public class PlayState extends State {

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

    }

    @Override
    public void update(float delta) {
        bird.update(delta);
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
