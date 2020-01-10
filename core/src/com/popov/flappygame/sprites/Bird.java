package com.popov.flappygame.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import lombok.Getter;

public class Bird {
    private final static int GRAVITY = -15;
    @Getter
    private final Vector3 position = new Vector3();
    private final Vector3 velocity = new Vector3();
    @Getter
    private final Texture birdTexture = new Texture("bird.png");

    public Bird(int x, int y) {
        this.position.set(x, y, 0);
    }

    public void update(float diff) {
        if (position.y > 0) {
            velocity.add(0, GRAVITY, 0);
            velocity.scl(diff);
            position.add(0, velocity.y, 0);
            velocity.scl(1 / diff);

            if (position.y < 0) {
                position.y = 0;
            }
        }
    }

    public void jump() {
        velocity.add(0, 250, 0);
    }
}
