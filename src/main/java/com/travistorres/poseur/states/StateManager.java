/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.states;

import java.awt.geom.Rectangle2D;
import java.util.Stack;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import com.travistorres.poseur.TheAnimatedPoseur;
import static com.travistorres.poseur.TheAnimatedPoseurSettings.*;
import com.travistorres.poseur.gui.AnimationSequenceTools;
import com.travistorres.poseur.gui.PoseCanvas;
import com.travistorres.poseur.gui.UserInterface;
import com.travistorres.poseur.shapes.EllipseShape;
import com.travistorres.poseur.shapes.LineShape;
import com.travistorres.poseur.shapes.PoseurShape;
import com.travistorres.poseur.shapes.PoseurShapeType;
import com.travistorres.poseur.shapes.RectangleShape;
import com.travistorres.poseur.sprites.AnimationState;
import com.travistorres.poseur.sprites.AnimationStateFrame;
import com.travistorres.poseur.sprites.SpriteType;

/**
 * The <code>StateManager</code> is responsible for monitoring all of the
 * various states that the user can be in while using the application.  It is
 * also responsible for handling some simple routines that will intercommunicate
 * frameworks with one another.  Such an intercommunication would be the ability
 * to draw a shape to the rendering canvas which requires work between both the
 * sprite and GUI packages.
 *
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012       Initial Release
 */
public class StateManager {
    private ApplicationState state;
    private SpriteType spriteType;

    private PoseurShape clipboard;
    private Stack<Object> undoBuffer;   //  Needs an object specifier T

    private PoseCanvasState renderingState;

    private PoseurShape shapeInProgress;
    private PoseurShapeType shapeInProgressType;

    private int initialMouseXPosition;
    private int initialMouseYPosition;

    /**
     * Will construct the initial properties for the <code>StateManager</code>
     * such as turning on the <code>STARTUP_STATE</code> and allocating space
     * for the clipboard and undoBuffer.
     */
    public StateManager() {
        state = ApplicationState.STARTUP_STATE;

        spriteType = new SpriteType( DEFAULT_SPRITE_WIDTH, DEFAULT_SPRITE_HEIGHT );

        clipboard = null;
        undoBuffer = new Stack<>();

        renderingState = new PoseCanvasState();

        shapeInProgress = null;
        shapeInProgressType = null;
    }

    /**
     * Will add an animation state to the current SpriteType project.
     *
     * @return <code>true</code> if the animation state was constructed.
     * @return <code>false</code> if the animation state could not be constructed.
     */
    public boolean addAnimationState() {
        boolean isAnimationCreated = false;

        //  Get the user interface
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        UserInterface gui = singleton.getGUI();

        //  ask user to enter animation state
        String animationName = JOptionPane.showInputDialog(
                gui, MESSAGE_TO_NAME_ANIMATION_STATE,
                MESSAGE_TO_NAME_ANIMATION_PROMPT,
                JOptionPane.QUESTION_MESSAGE );
        //  have the state manager create the animation state
        if( animationName != null && animationName.length() > 0 ) {
            //  all animation names should be capitalized and not contain spaces
            animationName = animationName.toUpperCase();
            animationName = animationName.replace( ' ', '_' );

            //  check if the animation was added
            isAnimationCreated = spriteType.addAnimation( animationName );

            if( isAnimationCreated ) {
                //  Adds the name to the animation states combobox
                gui.updateAnimationStateCombobox( animationName );
                updateAnimationSequenceTools();
            }
        }

        return isAnimationCreated;
    }

    /**
     * Removes the currently selected animation state.
     */
    public void removeAnimationState() {
        //  Get the name of the current animation
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        UserInterface gui = singleton.getGUI();
        AnimationSequenceTools sequenceTools = gui.getAnimationTools();
        JComboBox animations = sequenceTools.getAnimationStatesCombobox();
        String currAnimation = (String)animations.getSelectedItem();

        //  Remove the animation
        spriteType.removeAnimationState( currAnimation );
    }

    /**
     * Changes the selected frame.
     *
     * @param   frameToSelect
     *          Frame that the user has selected.
     */
    public void changeSelectedFrame( AnimationStateFrame frameToSelect ) {
        //  Select the user chosen frame
        AnimationState currentAnimation = spriteType.getCurrentAnimationState();
        currentAnimation.setSelectedFrame( frameToSelect );

        //  Activates all necessary buttons on animation sequence
        updateAnimationSequenceTools();

        //  Update the canvas
        repaintCanvases();
    }

    /**
     * Will resize the dimensions of the Sprite-Type.  When performing this
     * operation a signal will be sent to the rendering canvas to make the
     * canvas larger or smaller based on the Sprites new size.
     *
     * @param   width
     *          The value for the new width of the <code>SpriteType</code>.
     * @param   height
     *          The value for the new height of the <code>SpriteType</code>.
     */
    public void resizeSprite( int width, int height ) {
        //  Update the size of the sprite
        spriteType.setSize( width, height );

        //  Update the dimensions of the rendering canvas
        renderingState.updateRenderingCanvasDimensions();

        //  Redraw the rendeing canvas
        repaintCanvases();
    }

    /**
     * Will return the state that the pose canvas should be in.
     *
     * @return  State of the rendering canvas.
     */
    public PoseCanvasState getCanvasState() {
        return renderingState;
    }

    /**
     * Resets the state of the application back to its initial values.  All
     * buffers including the clipboard and undoBuffer will be brought back to
     * an undefined status.
     */
    public void resetState() {
        spriteType.reset();
        renderingState.updateRenderingCanvasDimensions();   //  update pose dimensions
        setState( ApplicationState.SHAPE_SELECTED_STATE );
    }

    /**
     * Acquires the current state that the application is running in so that
     * the application can be updated accordingly.
     *
     * @return The current <code>ApplicationState</code> that is set.
     */
    public ApplicationState getState() {
        return state;
    }

    /**
     * Acquires a reference to the shape that the user is currently in the
     * process of constructing.
     *
     * @return The <code>PoseurShape</code> under construction.
     * @return <code>null</code> if there is not shape in progress.
     */
    public PoseurShape getShapeInProgress() {
        return shapeInProgress;
    }

    /**
     * Acquires the name of the shape that is currently being constructed by the
     * user.
     *
     * @return The name of the <code>PoseurShapType</code> that is currently
     *         being constructed.
     * @return <code>null</code> if there is no <code>PoseurShapeType</code>
     *          being constructed.
     */
    public PoseurShapeType getShapeInProgressType() {
        return shapeInProgressType;
    }

    /**
     * Obtains a reference to the <code>SpriteType</code> that is the
     * application is being used to construct.  This object can be used to
     * obtain various pieces of information regarding the Sprite Type.
     *
     * @return  The <code>SpriteType</code> being constructed.
     */
    public SpriteType getSpriteType() {
        return spriteType;
    }

    /**
     * Obtains a reference to the <code>AnimationState</code> that is currently
     * under construction by the user.  This object can be used for creating
     * new frames for the animation as well deleting the animation entirely.
     *
     * @return  The <code>AnimationState</code> that the user is currently
     *          working on.
     */
    public AnimationState getCurrentAnimationState() {
        return spriteType.getCurrentAnimationState();
    }

    /**
     * Obtains the <code>AnimationStateFrame</code> that is currently under
     * construction by the user.  This can be used for adding new shapes to the
     * frame or deleting itself and all shapes inside of it completely.
     *
     * @return  The <code>AnimationStateFrame</code> currently under
     *          construction by the user.
     */
    public AnimationStateFrame getCurrentFrame() {
        return spriteType.getCurrentFrame();
    }

    /**
     * Obtains the <code>PoseurShape</code> that the user has just selected.
     * With this object, the user can manipulate the color, thickness and even
     * location of the shape on the rendering canvas.
     *
     * @return  The <code>PoseurShape</code> currently being selected.
     */
    public PoseurShape getSelectedShape() {
        return spriteType.getSelectedShape();
    }

    /**
     * Determines if the <param>shape</code> is the same as the selected shape.
     *
     * @param   shape
     *          Some shape that is believed to be selected.
     * @return  <code>true</code> if the <param>shape</code> is the currently
     *          selected shape.
     * @return  <code>false</code> if <param>shape</code> is not selected.
     */
    public boolean isSelectedShape( PoseurShape shape ) {
        return getSelectedShape() == shape;
    }

    /**
     * Will change the current state of the application and submit all changes
     * to the user interface.
     *
     * @param   state
     *          The new state for the application.
     */
    public void setState( ApplicationState state ) {
        this.state = state;

        //  Updates the gui to match the conditions of the new state
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        UserInterface gui = singleton.getGUI();
        gui.updateMode();
    }

    /**
     * Will copy the instance of the selected shape to the clipbaord and than
     * remove the shape from the screen.
     */
    public void cutSelectedShape() {
        //  Store the shape inside the clipbaord
        PoseurShape selectedShape = getSelectedShape();
        clipboard = selectedShape;

        //  Remove the selected shape from the current frame
        AnimationStateFrame currentFrame = getCurrentFrame();
        //currentFrame.remove( selectedShape );
    }

    /**
     * Will copy a new instance of the selected shape to the clipboard.
     */
    public void copySelectedShape() {
        PoseurShape selectedShape = getSelectedShape();
        //clipboard = selectedShape.clone();
    }

    /**
     * Will paste the contents of the clipboard onto the rendering screen in the
     * top-left corner.
     */
    public void pasteShapeToFrame() {
        //  Make a copy of the clipboard shape
        //PoseurShape shapeToPaste = clipboard.clone();
        //shapeToPaste.move( 0, 0 );

        //  Add the shape to the current frame being rendered
        //AnimationStateFrame currentFrame = getCurrentFrame();
        //currentFrame.addShape( shapeToPaste );
    }

    /**
     * This method will communicate with the <code>PoseCanvasState</code> so
     * that the dimensions of the <code>SpriteType</code> will scale to make
     * the canvas appear to be zoomed in.
     */
    public void zoomInCanvas() {
        renderingState.zoomInCanvas();
    }

    /**
     * This method will communicate with the <code>PoseCanvasState</code> so
     * that the dimensions of the <code>SpriteType</code> will scale down to
     * make the canvas to appear to be zoomed out.
     */
    public void zoomOutCanvas() {
        renderingState.zoomOutCanvas();
    }

    /**
     * Will set the program into the drawing state and inform the user interface
     * of the type of shape that will be drawn to the rendering canvas.
     *
     * @param   shapeType
     *          The type of shape that is to be drawn by the user interface.
     */
    public void selectShapeToDraw( PoseurShapeType shapeType ) {
        //  Sets the currently selected shape as null
        spriteType.setSelectedShape( null );

        //  Informs the gui that the state of the program has changed
        setState( ApplicationState.CREATE_SHAPE_STATE );

        //  Informs the gui of the type of shape being constructed
        shapeInProgress = null;
        shapeInProgressType = shapeType;
    }

    /**
     * This event will either move the currently selected shape to the new
     * position of the mouse cursor, assuming the cursor stays on canvas, or
     * resize a shape that the user is constructing.
     *
     * @param   mouseXPos
     *          Current x-position of the mouse.
     * @param   mouseYPos
     *          Current y-position of the mouse.
     */
    public void processMouseDragged( int mouseXPos, int mouseYPos ) {
        PoseurShape selectedShape;      //  only used if dragging a shape

        //  Record the change in the mouse's position
        Rectangle2D canvasArea = renderingState.getCanvasDimensions();

        //  compute an unscaled distance between points
        float zoomLevel = renderingState.getZoomLevel();
        int dx = (int)(( mouseXPos - initialMouseXPosition ) / zoomLevel);
        int dy = (int)(( mouseYPos - initialMouseYPosition ) / zoomLevel);

        //  Set the new position of the mouse as the new initial position
        initialMouseXPosition = mouseXPos;
        initialMouseYPosition = mouseYPos;

        //  If the user has selected a shape, than they are attempting to drag
        if( state == ApplicationState.SHAPE_SELECTED_STATE ) {
            setState( ApplicationState.SHAPE_DRAG_STATE );
        }

        //  If the user is attempting to drag a shape, then update the screen
        if( state == ApplicationState.SHAPE_DRAG_STATE &&
                (selectedShape = spriteType.getSelectedShape()) != null ) {

            //  If the shape is still in bounds with the canvas, update screen
            if( canvasArea.contains( mouseXPos, mouseYPos ) ) {
                //  Construct an unscaled version of the canvas
                Rectangle2D.Double unscaledGeo = new Rectangle2D.Double(
                        canvasArea.getX(), canvasArea.getY(),
                        canvasArea.getWidth() / zoomLevel,
                        canvasArea.getHeight() / zoomLevel );

                //  Move the shape along the canvas
                selectedShape.moveShape( dx, dy, unscaledGeo );
                repaintCanvases();
            }
            //  If the shape is no longer on canvas, stop dragging
            else {
                setState( ApplicationState.SHAPE_SELECTED_STATE );
            }

        }

        //  If the user is attempting to construct a shape
        else if( state == ApplicationState.CONSTRUCT_SHAPE_STATE ) {

            //  Determine unscaled coordinates with respect to the canvas
            int xpos = (int)((mouseXPos - canvasArea.getX()) / zoomLevel );
            int ypos = (int)((mouseYPos - canvasArea.getY()) / zoomLevel );

            //  Update the shape in progress if mouse is on rendering canvas
            if( mouseIsWithinCanvas( xpos, ypos, canvasArea ) ) {
                //  Draw shape
                shapeInProgress.updateShapeInProgress( xpos, ypos );

                //  Update screen
                repaintCanvases();
            }
            //  If the mouse is not on the canvas, shape must start over
            else {
                setState( ApplicationState.CREATE_SHAPE_STATE );
                shapeInProgress = null;
            }
        }
    }

    /**
     * Will determine whether the user desires to select a shape on the
     * rendering canvas or is attempting to construct a new shape.  Depending
     * on the state of the application, the desired action will be performed
     * and the user interface will be informed of the changes that have been
     * made.
     *
     * @param   mouseXPos
     * @param   mouseYPos
     */
    public void processMousePressed( int mouseXPos, int mouseYPos ) {
        //  Determine the position of the mouse in the scaled canvas coordinates
        Rectangle2D canvasArea = renderingState.getCanvasDimensions();
        float zoomLevel = renderingState.getZoomLevel();
        //  Get unscaled positions with respect to rendering canvas
        int xpos = (int)((mouseXPos - canvasArea.getX()) / zoomLevel);
        int ypos = (int)((mouseYPos - canvasArea.getY()) / zoomLevel);

        //  Set the initial value for the mouse location
        initialMouseXPosition = mouseXPos;
        initialMouseYPosition = mouseYPos;

        if( mouseIsWithinCanvas( xpos, ypos, canvasArea ) ) {

            //  Determine the desired operation
            if( state == ApplicationState.CREATE_SHAPE_STATE ) {

                //  Construct the desired shape type object
                if( shapeInProgressType == PoseurShapeType.LINE ) {
                    shapeInProgress = LineShape.factoryBuildLine( xpos, ypos );
                } else if( shapeInProgressType == PoseurShapeType.RECTANGLE ) {
                    shapeInProgress = RectangleShape.factoryBuildRectangle( xpos, ypos );
                } else if( shapeInProgressType == PoseurShapeType.ELLIPSE ) {
                    shapeInProgress = EllipseShape.factoryBuildEllipse( xpos, ypos );
                } else if( shapeInProgressType == PoseurShapeType.FREEHAND ) {
                    //  TODO-
                }

                //  Place application in a state that knows to construct a shape
                setState( ApplicationState.CONSTRUCT_SHAPE_STATE );

            } else if( state == ApplicationState.SELECT_SHAPE_STATE ) {

                //  Determine if there is a shape containing the mouse position
                PoseurShape selectedShape = spriteType.getShapeWithPoint( xpos, ypos );
                spriteType.setSelectedShape( selectedShape );

                //  If a shape was selected, enter the shape selected state
                if( selectedShape != null) {
                    setState( ApplicationState.SHAPE_SELECTED_STATE );
                }

            }

        }
    }

    /**
     * This procedure will determine if a newly constructed shape should be
     * added to the frame indefinatly or if a selected shape has been moved.
     *
     * @param   mouseXPos
     *          Current x-position of the mouse.
     * @param   mouseYPos
     *          Current y-position of the mouse.
     */
    public void processMouseReleased( int mouseXPos, int mouseYPos ) {
        //  If the user is constructing a new shape
        if( state == ApplicationState.CONSTRUCT_SHAPE_STATE ) {
            //  Get the dimensions of the sprite type
            Rectangle2D canvasArea = renderingState.getCanvasDimensions();
            float zoomLevel = renderingState.getZoomLevel();
            int xpos = (int)((mouseXPos - canvasArea.getX()) / zoomLevel);
            int ypos = (int)((mouseYPos - canvasArea.getY()) / zoomLevel);

            //  Update the position of the mouse
            initialMouseXPosition = mouseXPos;
            initialMouseYPosition = mouseYPos;

            //  A shape can only be completed if it is on the canvas
            if( mouseIsWithinCanvas( xpos, ypos, canvasArea ) ) {

                if( shapeInProgress.completesValidShape( xpos, ypos ) ) {
                    //  Add the shape to the frames list
                    AnimationStateFrame frame = spriteType.getCurrentFrame();
                    frame.addShapeToFrame( shapeInProgress );
                }
            }

            //  reset the state in progress and revert state back to normal
            shapeInProgress = null;
            setState( ApplicationState.CREATE_SHAPE_STATE );
            repaintCanvases();
        }
        //  If the user is moving a selected shape
        else if( state == ApplicationState.SHAPE_DRAG_STATE ) {
            setState( ApplicationState.SHAPE_SELECTED_STATE );
        }
    }

    /**
     * Determines if the mouse is still within the rendering canvas.
     *
     * @param   xpos
     *          The x-position of the mouse.
     * @param   ypos
     *          The y-position for the mouse.
     * @param   dim
     *          The dimensions for the rendering canvas.
     *
     * @return  <code>true</code> if the mouse cursor is still within the
     *          bounds of the rendering canvas.
     * @return  <code>false</code> if the mouse cursor is no longer within the
     *          bounds of the rendering canvas.
     */
    private boolean mouseIsWithinCanvas( int xpos, int ypos, Rectangle2D dim ) {
        return  xpos > 0 && xpos <= dim.getWidth() &&
                ypos > 0 && ypos <= dim.getHeight();
    }

    /**
     * Adds a new animation frame to the current animation state, with all of
     * the shapes of the frame that preceded it.
     */
    public void addFrame() {
        //  Request the sprite type add an animation frame
        AnimationState currentAnimation = spriteType.getCurrentAnimationState();
        currentAnimation.addFrame();

        //  Update the animation sequence pane
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        UserInterface gui = singleton.getGUI();
        gui.updateAnimationSequence( currentAnimation );
        updateAnimationSequenceTools();

        repaintCanvases();
    }

    /**
     * Will remove the currently selected frame from the animation sequence.
     */
    public void removeFrame() {
        AnimationState currentAnimation = spriteType.getCurrentAnimationState();
        currentAnimation.removeSelectedFrame();

        //  Update the animation sequence pane
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        UserInterface gui = singleton.getGUI();
        gui.updateAnimationSequence( currentAnimation );
        updateAnimationSequenceTools();

        repaintCanvases();
    }

    private void updateAnimationSequenceTools() {
        //  Gets access to the animation sequence
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        UserInterface gui = singleton.getGUI();
        AnimationSequenceTools tools = gui.getAnimationTools();

        //  Determines which tools in the animation sequence can be enabled
        int frameCount = spriteType.getCurrentAnimationFrameCount();
        int frameIndex = spriteType.getIndexOfCurrentFrame();
        boolean canRemoveAnimation = spriteType.getNumberOfAnimations() > 1;
        tools.enableRemoveAnimationButton( canRemoveAnimation );
        boolean canRemoveFrame = frameCount > 1;
        tools.enableRemoveAnimationFrameButton( canRemoveFrame );
        boolean canMoveLeft = frameIndex > 0;
        tools.enableMoveFrameLeftButton( canMoveLeft );
        boolean canMoveRight = frameIndex < (frameCount - 1);
        tools.enableMoveFrameRightButton( canMoveRight );

    }

    /**
     * Will have the rendering canvas render any changes made to the Sprite
     * onto the screen and informs the <code>StateManager</code> that the
     * project is now in an unsaved state so that the save button will become
     * active to the user.
     */
    private void repaintCanvases() {
        //  mark sprite as changed
        //  store change in undo-buffer somehow

        PoseCanvas renderingCanvas = renderingState.getCanvas();
        renderingCanvas.repaint();
    }
}
