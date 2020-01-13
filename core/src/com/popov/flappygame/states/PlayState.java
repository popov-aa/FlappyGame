package com.popov.flappygame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.popov.flappygame.FlappyGame;
import com.popov.flappygame.sprites.Bird;
import com.popov.flappygame.sprites.Tube;

import java.awt.Rectangle;

public class PlayState extends State {

    private final int birdOffset = 10;
    private final int tubeSpacing = 512;
    private final int tubeCount;
    private static final Texture backgroundTexture = new Texture("background.png");

    private static Texture groundTexture = new Texture("ground.png");
    private final Vector2 ground1Pos;
    private final Vector2 ground2Pos;
    private final int groundOffset = -300;

    private Bird bird = new Bird(30, 500);

    private Array<Tube> tubes = new Array<>();

    public PlayState(FlappyGame flappyGame) {
        super(flappyGame);
        resize(flappyGame.getScreenWidth(), flappyGame.getScreenHeight());

        ground1Pos = new Vector2(camera.position.x / flappyGame.getFactorX(), groundOffset);
        ground2Pos = new Vector2(ground1Pos.x + groundTexture.getWidth(), groundOffset);
        tubeCount = (int) Math.ceil(flappyGame.getOriginalScreenWidth() / (double)(Tube.getTopTubeTexture().getWidth() + tubeSpacing));
        for (int i = 0; i < tubeCount; ++i) {
            Tube tube = new Tube(flappyGame, flappyGame.getOriginalScreenWidth() + (tubeSpacing + Tube.getTopTubeTexture().getWidth()) * i);
            tubes.add(tube);
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void update(float delta) {
        handleInput();
        updateGround();
        bird.update(delta);
        camera.position.x = (bird.getPosition().x + flappyGame.getOriginalScreenWidth() / 2 - birdOffset) * flappyGame.getFactorX();
        for (Tube tube: tubes) {
            if (
                    (tube.getPosTopTube().x + tube.getTopTubeTexture().getWidth())*flappyGame.getFactorX() <
                    camera.position.x - (camera.viewportWidth / 2)

            ) {
                tube.reposition((int) (tube.getPosTopTube().x + (tube.getTopTubeTexture().getWidth() + tubeSpacing)*tubeCount));
            }
            if (tube.collides(bird.getRectangle())) {
                flappyGame.getGameStateManager().set(new PlayState(flappyGame));
            }
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(
                backgroundTexture,
                camera.position.x - camera.viewportWidth/2,
                0,
                flappyGame.getScreenWidth(), flappyGame.getScreenHeight());
        spriteBatch.draw(
                bird.getBirdTexture(),
                bird.getPosition().x * flappyGame.getFactorX(),
                bird.getPosition().y * flappyGame.getFactorY(),
                bird.getBirdTexture().getWidth() * flappyGame.getFactorX(),
                bird.getBirdTexture().getHeight() * flappyGame.getFactorY());
        for (Tube tube: tubes) {
            spriteBatch.draw(
                    tube.getTopTubeTexture(),
                    tube.getPosTopTube().x * flappyGame.getFactorX(),
                    tube.getPosTopTube().y * flappyGame.getFactorY(),
                    tube.getTopTubeTexture().getWidth() * flappyGame.getFactorX(),
                    tube.getTopTubeTexture().getHeight() * flappyGame.getFactorY());
            spriteBatch.draw(
                    tube.getBottomTubeTexture(),
                    tube.getPosBottomTube().x * flappyGame.getFactorX(),
                    tube.getPosBottomTube().y * flappyGame.getFactorY(),
                    tube.getBottomTubeTexture().getWidth() * flappyGame.getFactorX(),
                    tube.getBottomTubeTexture().getHeight() * flappyGame.getFactorY());
        }
        spriteBatch.draw(
                groundTexture,
                ground1Pos.x * flappyGame.getFactorX(),
                ground1Pos.y * flappyGame.getFactorY(),
                groundTexture.getWidth() * flappyGame.getFactorX(),
                groundTexture.getHeight() * flappyGame.getFactorY()
        );
        spriteBatch.draw(
                groundTexture,
                ground2Pos.x * flappyGame.getFactorX(),
                ground2Pos.y * flappyGame.getFactorY(),
                groundTexture.getWidth() * flappyGame.getFactorX(),
                groundTexture.getHeight() * flappyGame.getFactorY()
        );
        spriteBatch.end();
    }

    @Override
    public void resize(int x, int y) {
        camera.setToOrtho(false, x, y);
        camera.position.x = bird.getPosition().x * flappyGame.getFactorX();
    }

    @Override
    public void dispose() {

    }

    private void updateGround() {
        if ((camera.position.x - (camera.viewportWidth / 2)) / flappyGame.getFactorX() > ground1Pos.x + groundTexture.getWidth()) {
            ground1Pos.add(groundTexture.getWidth() * 2, 0);
        }
        if ((camera.position.x - (camera.viewportWidth / 2)) / flappyGame.getFactorX() > ground2Pos.x + groundTexture.getWidth()) {
            ground2Pos.add(groundTexture.getWidth() * 2, 0);
        }
    }
}
