/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.sprites;

import java.util.Iterator;
import java.util.LinkedList;
import com.travistorres.poseur.TheAnimatedPoseur;
import com.travistorres.poseur.gui.UserInterface;
import com.travistorres.poseur.shapes.PoseurShape;

/**
 * The <code>AnimationState</code> class is responsible for keeping track of the
 * animation sequence.  The order of all of the
 * <code>AnimationStateFrames</code> is of the utmost importance because that
 * order is what determines the way the final Sprite Type object will render
 * once finished.  This class also provides information on what frame is
 * currently under construction by the user and provides several routines for
 * adding/removing/copying new frames.
 *
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012       Initial Release
 */
public class AnimationState {
    private String name;
    private LinkedList<AnimationStateFrame> animationSequence;
    private AnimationStateFrame selectedFrame;

    /**
     * Constructs a new animation for the current <code>SpriteType</code>
     * under construction and creates the first frame within the animation that
     * the user will be able to modify.
     *
     * @param   animationName
     *          The name of the animation being created.
     */
    public AnimationState( String animationName ) {
        //  Setup the settings for the new animation
        name = animationName;
        animationSequence = new LinkedList<>();

        //  Construct the first frame for the animation
        AnimationStateFrame firstFrame = new AnimationStateFrame();
        animationSequence.add( firstFrame );

        selectedFrame = firstFrame;
    }

    /**
     * Obtains the index of the currently selected frame.
     *
     * @return Integer number representing the index.
     */
    public int getIndexOfCurrentFrame() {
        return animationSequence.indexOf( selectedFrame );
    }

    /**
     * Determines the number of frames within this animation state.
     *
     * @return  Number of frames.
     */
    public int getFrameCount() {
        return animationSequence.size();
    }

    /**
     * Adds a new frame to the animation sequence.
     */
    public void addFrame() {
        //  Create new frame from the old frame
        AnimationStateFrame lastFrame = animationSequence.getLast();
        AnimationStateFrame newFrame = lastFrame.clone();
        animationSequence.add( newFrame );

        //  Set the new frame as the current frame
        selectedFrame = newFrame;
    }

    /**
     * Removes the currently selected frame.
     */
    public void removeSelectedFrame() {
        animationSequence.remove( selectedFrame );
        selectedFrame = animationSequence.getFirst();
    }

    /**
     * Returns the name for the <code>AnimationState</code>.
     *
     * @return Name of the <code>AnimationState</code>.
     */
    public String getName() {
        return name;
    }

    /**
     * Will get a reference to the <code>AnimationStateFram</code> that is
     * currently selected by the user.
     *
     * @return  The animation frame that the user is working on.
     */
    public AnimationStateFrame getCurrentFrame() {
        return selectedFrame;
    }

    /**
     * Sets the type of shape that is currently being selected by the user
     * within the currently selected frame.
     *
     * @param   selectedShape
     *          The shape that is selected by the user.
     */
    public void setSelectedShape( PoseurShape selectedShape ) {
        selectedFrame.setSelectedShape( selectedShape );
    }

    /**
     * Will set the frame that the user has selected.
     *
     * @param   frameToSelect
     *          The frame that the user has selected.
     */
    public void setSelectedFrame( AnimationStateFrame frameToSelect ) {
        selectedFrame = frameToSelect;
    }

    /**
     * Will get a reference to the <code>PoseurShape</code> that the user has
     * selected.
     *
     * @return  The selected shape within the rendering canvas.
     */
    public PoseurShape getSelectedShape() {
        return selectedFrame.getSelectedShape();
    }

    /**
     * Retrieves an <code>Iterator</code> of all the animation frames within
     * the state.  This will prevent any modification of the structure.
     *
     * @return  An <code>Iterator</code> of animation frames.
     */
    public Iterator getAnimationSequenceIterator() {
        return animationSequence.iterator();
    }
}
