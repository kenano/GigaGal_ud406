package com.kenan.libgdxtutorial.gigagal.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by KenanO on 6/1/16.
 */
public class Constants {

    /**the background color used. */
    public static final Color BACKGROUND_COLOR = Color.SKY;

    /** the world size. the number of pixels of our Pixel art that will fit on the screen.
     * use this size to initialize both dimensions of an ExtendViewport
     * the game will be run in landscape mode, so this will specify the height of the world. */
    public static final float WORLD_SIZE = 128.0f;

    /** name of label from .atlas for sprite: player standing facing to the right. */
    public static final String SPRITE_STANDING_RIGHT_NAME = "standing-right";

    /** the name of label from .atlas for sprite: ... left */
    public static final String SPRITE_STANDING_LEFT_NAME = "standing-left";

    /** path to file which defines the texture atlas. */
    public static final String TEXTURE_ATLAS = "images/gigagal.pack.atlas";

    /** A Vector2 Constant for GigaGal's eye position within her sprites.*/
    public static final Vector2 VECTOR_GIGAGAL_EYE_POSITION = new Vector2(16,24);

    /** A float constant for the height of GigaGal's eye above her feet. */
    public static final float FLOAT_GIGAGAL_FEET_TO_EYES_DIST = 16.0f;

    /** a float for GigaGal's movement speed */
    public static final float FLOAT_GIGAGAL_SPEED = 72.0f;
}
