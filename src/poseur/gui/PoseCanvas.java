/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import javax.swing.JPanel;
import poseur.TheAnimatedPoseur;
import static poseur.TheAnimatedPoseurSettings.*;
import poseur.shapes.PoseurShape;
import poseur.sprites.AnimationStateFrame;
import poseur.states.PoseCanvasState;
import poseur.states.StateManager;

/**
 * A PoseCanvas is used for rendering shapes on.  The user will interact with
 * this canvas whenever they wish to edit the current frame of animation.  The
 * Dimensions of the frame are set to that of the SpriteTypes resolution.  The
 * user can zoom into the canvas by using the zoom tool-bar.
 * 
 * @author Travis Anthony Torres
 * @version 1.0 Initial Release -- November 2012
 */
public class PoseCanvas extends JPanel {
    private PoseCanvasState state;
    
    /**
     * Constructs a new <code>PoseCanvas</code> object.
     */
    public PoseCanvas( PoseCanvasState poseState ) {
        state = poseState;  //  Will contain information to display
    }
    
    /**
     * Will return the state that the <code>PoseCanvas</code> depends on to
     * render information.
     * 
     * @return  The state of the canvas.
     */
    public PoseCanvasState getState() {
        return state;
    }
    
    /**
     * Reads all shapes held within the current animation frame and draws each
     * one onto the screen.
     * 
     * @param g
     *          The graphics device that shapes will be drawn to.
     */
    @Override
    public void paintComponent( Graphics g ) {
        //  paints to the canvas only if it exist
        if( getWidth() > 0 ) {
            //  Uses 2-dimensional graphics
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor( POSE_CANVAS_COLOR );
            super.paintComponent( g2d );
            
            //  Get dimension of the Sprite-Type and draw the canvas
            Rectangle2D.Double spriteDimensions = state.getCanvasDimensions();
            g2d.fill( spriteDimensions );
            
            //  Render all of the shapes that are contained within the frame
            renderShapes( g2d, spriteDimensions );      //  Constructed shapes
            renderShapeInProgress( g2d, spriteDimensions ); //  Under construction
        }
    }
    
    /**
     * Will render all of the shapes that exist within the frame that is
     * currently selected by the user.  All of the shapes that are rendered
     * within this method have already been fully constructed by the user, to
     * render shapes that the user is currently constructing look at the
     * <code>renderShapeInProgress</code> method.
     * 
     * @param   g2d
     *          The graphics device that all shapes are being rendered onto.
     * @param   region
     *          Information regarding the dimensions of the Sprite Type.
     */
    private void renderShapes( Graphics2D g2d, Rectangle2D.Double region ) {
        //  Used to determine if a shape is selected
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        StateManager sm = singleton.getStateManager();
        
        //  Used for scaling shapes to the correct zoom level (in/out)
        float zoomLevel = state.getZoomLevel();
        
        //  Retrieve an iterator for all shapes within the current frame
        AnimationStateFrame frame = state.getCurrentFrame();
        if( frame == null ) return;     //  There is no project setup
        Iterator<PoseurShape> shapeIterator = frame.iterator();
        while( shapeIterator.hasNext() ) {
            //  Determine if the shape is selected by the user.
            PoseurShape shape = shapeIterator.next();
            boolean isShapeSelected = sm.isSelectedShape( shape );
            
            //  Renders the shape onto the screen, renders red if selected
            shape.render( g2d, (int)region.getX(), (int)region.getY(), 
                    zoomLevel, isShapeSelected);
        }
    }
    
    /**
     * Used for constructing shapes that the user has not completed sizing, has
     * not released the mouse button.  This method is necessary since the shape
     * is not currently entered into the frames list of shapes and thus will
     * not be read into memory by the <code>renderShapes</code> routine.
     * 
     * @param   g2d
     *          The graphics device that the shape will be rendered onto.
     * @param   region 
     *          The dimensions of the Sprite Type so that the shape will not
     *          appear off of the rendering canvas.
     */
    private void renderShapeInProgress( Graphics2D g2d, Rectangle2D.Double region ) {
        //  Retrieves a reference to the shape that is under construction
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        StateManager sm = singleton.getStateManager();
        PoseurShape shapeInProgress = sm.getShapeInProgress();
        
        //  Render a shape IF AND ONLY IF there is a shape in progress
        if( shapeInProgress != null ) {
            shapeInProgress.render( g2d,
                                    (int)region.getX(), (int)region.getY(),
                                    state.getZoomLevel(), true );
        }
    }
    
}
