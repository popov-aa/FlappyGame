package com.popov.flappygame.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.popov.flappygame.FlappyGame;

import java.util.Random;

import lombok.Getter;

public class Tube {
    @Getter
    static private Texture topTubeTexture = new Texture("tube_top.png");
    @Getter
    static private Texture bottomTubeTexture = new Texture("tube_bottom.png");
    @Getter
    private Vector2 posTopTube = new Vector2();
    @Getter
    private Vector2 posBottomTube = new Vector2();

    private FlappyGame flappyGame;
    private Random random = new Random();
    private int min = 300;
    private int gap = 300;

    public Tube(FlappyGame flappyGame, int x) {
        this.flappyGame = flappyGame;
        reposition(x);
    }

    public void reposition(int x) {
        int variant = flappyGame.getOriginalScreenHeight() - min*2 - gap;
        posTopTube = new Vector2(x, min + gap + random.nextInt(variant));
        posBottomTube = new Vector2(x, posTopTube.y - gap - bottomTubeTexture.getHeight());
    }
}
