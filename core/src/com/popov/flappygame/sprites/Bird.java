package com.popov.flappygame.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import lombok.Getter;

public class Bird {
    private final static int GRAVITY = -40;
    private final static int MOVEMENT = 500;
    @Getter
    private final Vector3 position = new Vector3();
    private final Vector3 velocity = new Vector3();
    @Getter
    private Rectangle rectangle;
    private Animation animation;

    public Bird(int x, int y) {
        this.position.set(x, y, 0);
        Texture birdTexture = new Texture("bird.png");
        this.animation = new Animation(new TextureRegion(birdTexture), 3, 0.5f);
        rectangle = new Rectangle(0, 0, birdTexture.getWidth() / animation.getFrameCount(), birdTexture.getHeight());
    }

    public void update(float diff) {
        animation.update(diff);
        if (position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(diff);
        position.add(MOVEMENT*diff, velocity.y, 0);
        velocity.scl(1 / diff);

        if (position.y < 0) {
            position.y = 0;
        }
        rectangle.setPosition(position.x, position.y);
    }

    public void jump() {
        velocity.add(0, 1000, 0);
    }

    public TextureRegion getTextureRegion() {
        return animation.getFrame();
    }
}
