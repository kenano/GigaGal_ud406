package com.kenan.libgdxtutorial.gigagal.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    /**
     * Constructor.
     * Initialize the position for GG.
     */
    public GigaGal() {
        // Initialize GigaGal's position
        mPosition = new Vector2(20, Constants.FLOAT_GIGAGAL_FEET_TO_EYES_DIST);
    }

    /**
     *
     * update Gigagals properties.
     *
     * @param delta time difference since game has started.
     */
    public void update(float delta) {

    }

    /**
     *
     * @param batch
     */
    public void render(SpriteBatch batch) {

        //Draw the standing right AtlasRegion at GGs current position
        batch.draw(
                Assets.assetsInstance.gigaGalAssets.atlasRegionStandingRight.getTexture(),
                mPosition.x - Constants.VECTOR_GIGAGAL_EYE_POSITION.x,
                mPosition.y - Constants.VECTOR_GIGAGAL_EYE_POSITION.y,
                0,
                0,
                Assets.assetsInstance.gigaGalAssets.atlasRegionStandingRight.getRegionWidth(),
                Assets.assetsInstance.gigaGalAssets.atlasRegionStandingRight.getRegionHeight(),
                1,
                1,
                0,
                Assets.assetsInstance.gigaGalAssets.atlasRegionStandingRight.getRegionX(),
                Assets.assetsInstance.gigaGalAssets.atlasRegionStandingRight.getRegionY(),
                Assets.assetsInstance.gigaGalAssets.atlasRegionStandingRight.getRegionWidth(),
                Assets.assetsInstance.gigaGalAssets.atlasRegionStandingRight.getRegionHeight(),
                false,
                false);

    }

}
