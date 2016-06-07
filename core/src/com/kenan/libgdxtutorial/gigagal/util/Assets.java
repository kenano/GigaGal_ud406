package com.kenan.libgdxtutorial.gigagal.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;


/**
 * Created by KenanO on 6/1/16.
 * This utility class holds onto all the assets used. It's a singleton, so the constructor is
 * private, and a single instance is created when the class is loaded. That way all
 * the entities that make up the game can access their sprites in the same place, and no work
 * loading up textures is repeated.
 *
 * Each entity gets its own inner class to hold its assets. Below you'll complete the GigaGalAssets
 * class by finding up the standing-right AtlasRegion within the TextureAtlas loaded in init() .
 */
public class Assets implements Disposable, AssetErrorListener {

    public static final String TAG = Assets.class.getName();

    /** an instance of this class. Since its a singleton it can be static. */
    public static final Assets assetsInstance = new Assets();

    /**
     * assets for the gigagal sprite. since this class is a singleton we can give public access
     * to this member since there is always be one instance of it.
     */
    public GigaGalAssets gigaGalAssets;

    // AssetManager - Loads and stores assets like textures, bitmapfonts, tile maps, etc
    private AssetManager mAssetManager;

    /*
     * Constructor
     * Since this class should be instantiated as a singleton make its constructor private so no
     * other class can instantiate a second instance.
     */
    private Assets() {}

    /**Create an instance of mAssetManager, sets a ErrorListener to it, loads texture atlas. */
    public void init() {

        mAssetManager = new AssetManager();
        mAssetManager.setErrorListener(this);
        mAssetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        mAssetManager.finishLoading();

        //get the textureatlas from the AssetManager
        TextureAtlas atlas = mAssetManager.get(Constants.TEXTURE_ATLAS);

        //create an instance of the  GigaGalAssests obj.
        gigaGalAssets = new GigaGalAssets(atlas);
    }

    /** This inner class stores the assets for the Gigagal entity.*/
    public class GigaGalAssets {

        /** AtlasRegion the standing-right sprite */
        public final TextureAtlas.AtlasRegion atlasRegionStandingRight;

        /** AtlasRegion for the standing-left sprite */
        public final TextureAtlas.AtlasRegion atlasRegionStandingLeft;

        // Add AtlasRegions for jumping-left and jumping-right
        /** AtlasRegion for the jumping-right sprite */
        public final TextureAtlas.AtlasRegion atlasRegionJumpingRight;

        /** AtlasRegion for the jumping-left sprite */
        public final TextureAtlas.AtlasRegion atlasRegionJumpingLeft;

        /**
         *
         * @param atlas The texture altas which has the image assets for the project.
         */
        public GigaGalAssets(TextureAtlas atlas) {

            //initialize the standing right AtlasRegion
            atlasRegionStandingRight = atlas.findRegion(Constants.SPRITE_STANDING_RIGHT_NAME);

            // Find the standing-left AtlasRegion
            atlasRegionStandingLeft = atlas.findRegion(Constants.SPRITE_STANDING_LEFT_NAME);

            // initialize the jumping right/left AtlasRegion
            atlasRegionJumpingRight = atlas.findRegion(Constants.SPRITE_JUMPING_RIGHT_NAME);
            atlasRegionJumpingLeft = atlas.findRegion(Constants.SPRITE_JUMPING_LEFT_NAME);
        }
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset: " + asset.fileName, throwable);
    }

    @Override
    public void dispose() {
        mAssetManager.dispose();
    }
}
