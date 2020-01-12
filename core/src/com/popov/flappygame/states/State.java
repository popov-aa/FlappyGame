package com.popov.flappygame.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.popov.flappygame.FlappyGame;

public abstract class State implements Disposable {

    protected final OrthographicCamera camera = new OrthographicCamera();
    protected final Vector3 mouse = new Vector3();
    protected final FlappyGame flappyGame;

    public State(FlappyGame flappyGame) {
        this.flappyGame = flappyGame;
    }

    protected abstract void handleInput();
    public abstract void update(float delta);
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void resize(int x, int y);
}
