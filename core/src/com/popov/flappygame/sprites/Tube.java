package com.popov.flappygame.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
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

    private final Rectangle boundsTop = new Rectangle(0, 0, topTubeTexture.getWidth(), topTubeTexture.getHeight());
    private final Rectangle boundsBottom = new Rectangle(0, 0, bottomTubeTexture.getWidth(), bottomTubeTexture.getHeight());

    private FlappyGame flappyGame;
    private Random random = new Random();
    private int min = 300;
    private int gap = 600;

    public Tube(FlappyGame flappyGame, int x) {
        this.flappyGame = flappyGame;
        reposition(x);
    }

    public void reposition(int x) {
        int variant = flappyGame.getOriginalScreenHeight() - min*2 - gap;
        posTopTube = new Vector2(x, min + gap + random.nextInt(variant));
        boundsTop.setPosition(posTopTube.x, posTopTube.y);

        posBottomTube = new Vector2(x, posTopTube.y - gap - bottomTubeTexture.getHeight());
        boundsBottom.setPosition(posBottomTube.x, posBottomTube.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsTop) || player.overlaps(boundsBottom);
    }
}
