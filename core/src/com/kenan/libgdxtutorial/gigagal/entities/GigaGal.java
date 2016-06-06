package com.kenan.libgdxtutorial.gigagal.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.kenan.libgdxtutorial.gigagal.util.Assets;
import com.kenan.libgdxtutorial.gigagal.util.Constants;

/**
 * Created by KenanO on 6/1/16.
 */
public class GigaGal {

    /** for logging */
    public final static String TAG = GigaGal.class.getName();

    //GGs position in the screen.
    private Vector2 mPosition;

    // stores which direction GG is facing.
    private Facing mFacingDirection;

    /**
     * Constructor.
     * Initialize the position for GG.
     */
    public GigaGal() {
        // Initialize GigaGal's position
        mPosition = new Vector2(20, Constants.FLOAT_GIGAGAL_FEET_TO_EYES_DIST);

        // Initialize facing, probably with Facing.RIGHT
        mFacingDirection = Facing.RIGHT;
    }

    /**
     *
     * update Gigagals properties. Update position of GigaGal based on result of
     * Gdx.input.isKeyPressed.
     *
     * @param delta time difference since game has started.
     */
    public void update(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            moveLeft(delta);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            moveRight(delta);
        }
    }

    /**
     *
     * draw the spritebatch for GigaGal.
     *
     * @param batch time difference since game has started.
     */
    public void render(SpriteBatch batch) {

//        TextureRegion texture_region_standing_right =
//                Assets.assetsInstance.gigaGalAssets.atlasRegionStandingRight;

        // Set region to the correct sprite for the current facing direction
        TextureRegion current_texture_region = null;

        if(mFacingDirection == Facing.RIGHT){
            current_texture_region =
                    Assets.assetsInstance.gigaGalAssets.atlasRegionStandingRight;
        }else if(mFacingDirection == Facing.LEFT){
            current_texture_region =
                    Assets.assetsInstance.gigaGalAssets.atlasRegionStandingLeft;
        }else {
            //implement error handling.
        }

        //Draw the standing right AtlasRegion at GGs current position
        batch.draw(
                current_texture_region.getTexture(),
                mPosition.x - Constants.VECTOR_GIGAGAL_EYE_POSITION.x,
                mPosition.y - Constants.VECTOR_GIGAGAL_EYE_POSITION.y,
                0,
                0,
                current_texture_region.getRegionWidth(),
                current_texture_region.getRegionHeight(),
                1,
                1,
                0,
                current_texture_region.getRegionX(),
                current_texture_region.getRegionY(),
                current_texture_region.getRegionWidth(),
                current_texture_region.getRegionHeight(),
                false,
                false);

    }

    // defines the possible directions GG could be facing.
    private  enum Facing {
        LEFT,
        RIGHT
    }

    private void moveLeft(float delta) {
        //update GGs position based on GGs speed from Constants and the change rate delta parameter.

        // Update facing direction
        mFacingDirection = Facing.LEFT;

        mPosition.x -= Constants.FLOAT_GIGAGAL_SPEED * delta;

    }

    private void moveRight(float delta) {
        //update GGs position based on GGs speed from Constants and the change rate delta parameter.

        // Update facing direction
        mFacingDirection = Facing.RIGHT;

        mPosition.x += Constants.FLOAT_GIGAGAL_SPEED * delta;

    }

}
