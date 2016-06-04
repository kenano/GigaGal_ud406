package com.kenan.libgdxtutorial.gigagal;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kenan.libgdxtutorial.gigagal.entities.GigaGal;

/**
 * Created by KenanO on 6/1/16.
 */
public class Level {

    //  GigaGal is the main character in the game.
    private GigaGal mGigaGal;

    public Level() {

        //Initialize/create  GigaGal
        mGigaGal = new GigaGal();

    }

    public void update(float delta) {

        //Update GigaGal
        mGigaGal.update(delta);

    }

    public void render(SpriteBatch batch) {

        // Render GigaGal
        mGigaGal.render(batch);

    }

}
