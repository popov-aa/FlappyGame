package com.popov.flappygame.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import lombok.Getter;

public class Animation {
    private Array<TextureRegion> frames = new Array<>();
    private float maxFrameTime;
    private float currentFrameTime;
    @Getter
    private int frameCount;
    private int frame;

    public Animation(TextureRegion textureRegion, int frameCount, float cycleTime) {
        int frameWidth = textureRegion.getRegionWidth() / frameCount;
        for (int i = 0; i < frameCount; ++i) {
            frames.add(new TextureRegion(textureRegion, i * frameWidth, 0, frameWidth, textureRegion.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float diff) {
        currentFrameTime += diff;
        if (currentFrameTime > maxFrameTime) {
            ++frame;
            currentFrameTime = 0;
        }
        if (frame >= frameCount) {
            frame = 0;
        }
    }


    public TextureRegion getFrame() {
        return frames.get(frame);
    }
}
