/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.sprites;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Iterator;
import com.travistorres.poseur.TheAnimatedPoseur;
import com.travistorres.poseur.gui.UserInterface;
import com.travistorres.poseur.shapes.PoseurShape;

/**
 * The <code>SpriteType</code> is responsible for keeping track of all the
 * information about the Sprite being constructed by the user.  Each animation
 * state is kept within a hash table to allow instant access to all of the
 * various animations that are constructed.  The class also provides routines
 * that will acquire the current frame and currently selected shape to avoid
 * the need of having duplicate information stored within the
 * <code>StateManager</code> which may easily be corrupted somewhere within
 * the program as the software grows in the future.
 *
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012       Initial Release
 */
public class SpriteType {
    private int spriteWidth;
    private int spriteHeight;
    private HashMap<String, AnimationState> animationStates;
    private AnimationState selectedAnimationState;

    /**
     * Will construct a new Sprite Type for the user to modify.  This will
     * provide the user with the first animation state as well as the first
     * frame within the animation state.
     *
     * @param width
     * @param height
     */
    public SpriteType( int width, int height ) {
        spriteWidth = width;
        spriteHeight = height;

        animationStates = new HashMap<>();
        selectedAnimationState = null;
    }

    /**
     * This will restore the <code>SpriteType</code> back to its original
     * settings when it was constructed.  This should be used whenever the
     * user request to create a new project to save time on deallocating and
     * reallocating memory for the system.
     */
    public void reset() {
        //  allocate memory for the sprites to be stored within
        animationStates = new HashMap<>();
        selectedAnimationState = null;
    }

    public void removeAnimationState( String animationName ) {
        AnimationState curr = animationStates.put( animationName, null );
        //TODO IN MORNING

    }

    /*
     * Retrieves the width of the current sprite type.
     *
     * @return The width of the sprite tyep in pixels.
     */
    public int getWidth() {
        return spriteWidth;
    }

    /**
     * Retrieves the height of the sprite type.
     *
     * @return The height of the sprite type in pixels.
     */
    public int getHeight() {
        return spriteHeight;
    }

    public int getCurrentAnimationFrameCount() {
        return getCurrentAnimationState().getFrameCount();
    }

    public int getIndexOfCurrentFrame() {
        return getCurrentAnimationState().getIndexOfCurrentFrame();
    }

    public int getNumberOfAnimations() {
        return animationStates.size();
    }

    /**
     * Will request a reference to the <code>AnimationState</code> that is
     * currently being worked on.
     *
     * @return  The <code>AnimationState</code> that the user is currently
     *          editing.
     */
    public AnimationState getCurrentAnimationState() {
        return selectedAnimationState;
    }

    /**
     * This will request a reference to the <code>AnimationStateFrame</code>
     * that is currently being edited by the user.
     *
     * @return  The <code>AnimationStateFrame</code> being edited by the user.
     * @return  <code>null</code> if a project has not been constructed
     *          properly, no animation state exist.
     */
    public AnimationStateFrame getCurrentFrame() {
        return (selectedAnimationState != null ) ?
                                    selectedAnimationState.getCurrentFrame() :
                                    null;
    }

    /**
     * Will acquire the currently selected <code>PoseurShape</code> within the
     * current animation frame.
     *
     * @return  The selected <code>PoseurShape</code> object.
     */
    public PoseurShape getSelectedShape() {
        return selectedAnimationState.getSelectedShape();
    }

    /**
     * Locates a shape, within the currently selected frame, that contains the
     * point <code>( x, y )</code> which will be referred to as <code>p</code>.
     * If there is not shape that contains <code>p</code> than a result of
     * <code>null</code> will be achieved.
     *
     * @param   x
     *          The x-coordinate for <code>p</code>.
     * @param   y
     *          The y-coordinate for <code>p</code>.
     *
     * @return  The shape that contains point <code>p</code> within it geometry.
     * @return  <code>null</code> if not shape within the current frame
     *          contains <code>p</code> within its geometry.
     */
    public PoseurShape getShapeWithPoint( int x, int y ) {
        //  Construct an object representation of the point
        PoseurShape shapeWithPoint = null;
        Point2D.Double point = new Point2D.Double( x, y );

        //  Aquire all of the shapes within the selected frame
        AnimationStateFrame frame = getCurrentFrame();
        Iterator<PoseurShape> shapes = frame.iterator();
        //  Determine if there is a shape with point p
        while( shapes.hasNext() && shapeWithPoint == null ) {
            PoseurShape shape = shapes.next();
            if( shape.containsPoint( point ) ) {
                shapeWithPoint = shape;
            }
        }

        return shapeWithPoint;
    }

    /**
     * Will create a new animation for the sprite type assuming the animation
     * name is not already chosen.
     *
     * @param   name
     *          The name of the animation state being constructed.
     *
     * @return  <code>true</code> if the animation state was added.
     * @return  <code>false</code> if the animation state cannot be added.
     */
    public boolean addAnimation( String name ) {
        boolean wasCreated = false;

        /*  NOTE-   Consider writing a hash table class that will handle all
         *          collisions with a quadratic hash algorithm instead of
         *          just returning the old value like the HashMap class does.
         */

        /*  POTENTIAL ISSUES-   Potentially two different animation names
         *          may evaluate to the same hash key and request the same
         *          memory address.  If this occurs than the new animation
         *          will not be created based on the implementation of the
         *          HashMap data structure.
         */

        //  Get the address where the animation state should be stored
        if( animationStates.get( name ) == null ) {
            AnimationState newAnimation = new AnimationState( name );
            animationStates.put( name, newAnimation );
            wasCreated = true;      //  state was created
        }

        return wasCreated;
    }

    /**
     * Sets the type of shape that is currently being selected by the user
     * within the currently selected frame.
     *
     * @param   selectedShape
     *          The shape that is selected by the user.
     */
    public void setSelectedShape( PoseurShape selectedShape ) {
        selectedAnimationState.setSelectedShape( selectedShape );
    }

    /**
     * Updates the value of the selected animation state and has the user
     * interface update the animation sequence.
     *
     * @param   animationName
     *          The name of the animation state that the user has selected.
     */
    public void setSelectedAnimationState( String animationName ) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        UserInterface gui = singleton.getGUI();

        //  Sets the currently selected animation state and has the gui updated
        selectedAnimationState = animationStates.get( animationName );
        if( selectedAnimationState != null ) {
            gui.updateAnimationSequence( selectedAnimationState );
        }
    }

    /**
     * Will resize the dimensions of the Sprite-Type.
     *
     * @param   width
     *          The value for the new width of the <code>SpriteType</code>.
     * @param   height
     *          The value for the new height of the <code>SpriteType</code>.
     */
    public void setSize( int width, int height ) {
        spriteWidth = width;
        spriteHeight = height;
    }
}
