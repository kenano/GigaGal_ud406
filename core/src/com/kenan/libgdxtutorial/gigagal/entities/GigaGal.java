package com.kenan.libgdxtutorial.gigagal.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
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

    // GigaGal's velocity
    private Vector2 mVelocity;

    // GGs JumpState
    private JumpingState mJumpingState;

    // timestamp when jump was initiated.
    private long mJumpStartTime;

    /**
     * Constructor.
     * Initialize the position for GG.
     */
    public GigaGal() {
        // Initialize GigaGal's position
        mPosition = new Vector2(20, Constants.FLOAT_GIGAGAL_FEET_TO_EYES_DIST);

        // Initialize facing, probably with Facing.RIGHT
        mFacingDirection = Facing.RIGHT;

        // Initialize velocity
        mVelocity = new Vector2();

        //Initialize JumpState.
        mJumpingState = JumpingState.FALLING;
    }

    /**
     *
     * update GigaGals properties. Update position of GigaGal based on result of
     * Gdx.input.isKeyPressed.
     *
     * @param delta time difference since game has started.
     */
    public void update(float delta) {

        // Accelerate GigaGal down.
        mVelocity.y -= Constants.ACCELERATION_GRAVITY * delta;

        // Apply GigaGal's velocity to her position
        mPosition.mulAdd(mVelocity, delta);

        // If GigaGal isn't JUMPING, make her now FALLING
        if(mJumpingState != JumpingState.JUMPING){
            mJumpingState = JumpingState.FALLING;
        }

        // Check if GigaGal has landed on the ground
        if(mPosition.y - Constants.FLOAT_GIGAGAL_FEET_TO_EYES_DIST <= 0){
            mJumpingState = JumpingState.GROUNDED;
            mVelocity.y = 0;
            mPosition.y = 16.0f;
        }

        // Handle jump key
        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {

            switch (mJumpingState){
                case GROUNDED:
                    startJump();
                    break;
                case JUMPING:
                    continueJump();
                    break;
                case FALLING:
                    break;
            }

        } else {
            //if jump key wasnt pressed, end the jump. (even if not jumping)
            endJump();
        }

        //Handle right and left input.
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            moveLeft(delta);
        }else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
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

    //enum containing JUMPING, FALLING, and GROUNDED. The possible jump states.
    private enum JumpingState {
        JUMPING,
        FALLING,
        GROUNDED
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

    private void startJump() {
        //invoked when GG starts jumping.

        // Set jumpState to JUMPING
        mJumpingState = JumpingState.JUMPING;

        // Set the jump start time
        mJumpStartTime = TimeUtils.nanoTime();

        // Call continueJump()
        continueJump();

    }

    private void continueJump() {
        //if GG is already jumping and user is still pressing the jump button, she should continue
        // to jump higher for some reasonable amount of time. (this achieves variable jumping).

        // First, check if we're JUMPING, if not, just return
        if(mJumpingState != JumpingState.JUMPING){
            return;
        }

        // Find out how long we've been jumping
        float time_jumping = MathUtils.nanoToSec * (TimeUtils.nanoTime() - mJumpStartTime);

        // If we have been jumping for less than the max jump duration, set GG's vertical to the
        // jump speed constant. Else, call endJump()
        if(time_jumping < Constants.MAX_JUMP_DURATION){
            mVelocity.y = Constants.JUMP_SPEED;
        }else {
            endJump();
        }
    }

    private void endJump() {

        //If we're JUMPING, now we're FALLING
        if(mJumpingState == JumpingState.JUMPING){
            mJumpingState = JumpingState.FALLING;
        }
    }
}