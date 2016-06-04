package com.kenan.libgdxtutorial.gigagal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.kenan.libgdxtutorial.gigagal.util.Assets;
import com.kenan.libgdxtutorial.gigagal.util.Constants;

/**
 * Created by KenanO on 6/1/16.
 */
public class GameplayScreen  extends ScreenAdapter {

    /** Used for logging. */
    public static final String TAG = GameplayScreen.class.getName();

    // SpriteBatch provides functionality to draw textures.
    private SpriteBatch mSpriteBatch;

    // ExtendViewport - keeps the world aspect ratio by extending the world in one direction
    // the rest is letterboxed.
    private ExtendViewport mExtendedViewport;

    // The environment GigaGal is in.
    private Level mLevel;


    @Override
    public void show() {
        // Initialize the Assets instance
        Assets.assetsInstance.init();

        // Initialize/create the Level
        mLevel = new Level();

        // Initalize the SpriteBatch.
        mSpriteBatch = new SpriteBatch();

        // Initialize the viewport
        mExtendedViewport = new  ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
    }

    @Override
    public void resize(int width, int height) {
        // Update the viewport
        mExtendedViewport.update(width, height, true);
    }

    @Override
    public void render(float delta) {

        // Call update() on the Level
        mLevel.update(delta);

        //  Apply the viewport
        mExtendedViewport.apply();

        // set the clear color
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g,
                Constants.BACKGROUND_COLOR.b, 1);

        //Clear the screen to the BACKGROUND_COLOR
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Set the SpriteBatch's projection matrix
        mSpriteBatch.setProjectionMatrix(mExtendedViewport.getCamera().combined);

        //Begin the SpriteBatch
        mSpriteBatch.begin();

        //  Render the Level
        mLevel.render(mSpriteBatch);

        // End the SpriteBatch
        mSpriteBatch.end();
    }

    @Override
    public void dispose() {
        // Dispose of the Assets instance
        Assets.assetsInstance.dispose();

        // Dispose of the SpriteBatch
        mSpriteBatch.dispose();
    }

}