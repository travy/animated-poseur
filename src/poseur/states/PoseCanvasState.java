/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.states;

import java.awt.geom.Rectangle2D;
import poseur.TheAnimatedPoseur;
import poseur.gui.PoseCanvas;
import poseur.sprites.AnimationStateFrame;
import poseur.sprites.SpriteType;

/**
 * The <code>PoseCanvasState</code> class will handle information that the
 * <code>PoseCanvas</code> uses to display.  Information such as the level that
 * the canvas is zoomed into and the current animation frame being edited will
 * be contained within this state manager class.
 * 
 * @author      Travis Anthony Torres
 * @version     1.0     December 2012       Initial Release
 */
public class PoseCanvasState {
    private float zoomLevel;
    private float zoomFactor;
    private float minimumZoomLevel;
    
    private PoseCanvas renderingCanvas;
    
    private SpriteType spriteType;
    
    private Rectangle2D.Double canvasDimensions;
    
    /**
     * Will prepare the state for when an animation state is constructed and
     * animation is to be expected.
     */
    public PoseCanvasState() {
        zoomLevel = 1.0f;
        zoomFactor = 0.1f;
        minimumZoomLevel = 0.1f;
        
        renderingCanvas = null;
        
        spriteType = null;
        
        canvasDimensions = new Rectangle2D.Double();
    }
    
    /**
     * Will allow the frame to be updated to the currently selected frame.
     */
    public void init( PoseCanvas canvas ) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        StateManager sm = singleton.getStateManager();
        SpriteType spriteData = sm.getSpriteType();
        
        spriteType = spriteData;
        renderingCanvas = canvas;
    }
    
    /**
     * Will retrieve the address of the rendering canvas.
     * 
     * @return  The rendering canvas.
     */
    public PoseCanvas getCanvas() {
        return renderingCanvas;
    }
    
    /**
     * Will retrieve a reference to the currently selected animation frame.
     * 
     * @return  The currently selected animation frame.
     */
    public AnimationStateFrame getCurrentFrame() {
        return spriteType.getCurrentFrame();
    }
    
    /**
     * Returns the scale that is used to scale shapes by.
     * 
     * @return The scale of the canvas.
     */
    public float getZoomLevel() {
        return zoomLevel;
    }
    
    /**
     * Will retrieve information about the canvas's dimensions.
     * 
     * @return  The dimensions of the canvas.
     */
    public Rectangle2D.Double getCanvasDimensions() {
        return canvasDimensions;
    }
    
    /**
     * Updates the size and location of the canvas whenever the user decides to
     * zoom in or out.
     */
    public void updateRenderingCanvasDimensions() {
        int canvasWidth = renderingCanvas.getWidth();
        int canvasHeight = renderingCanvas.getHeight();
        
        //  Determines the width and height of the canvas after zooming
        canvasDimensions.width = (int)(spriteType.getWidth() * zoomLevel);
        canvasDimensions.height = (int)(spriteType.getHeight() * zoomLevel);
        
        //  Calculates the location of the canvas after the size is computed
        canvasDimensions.x = ( canvasWidth - canvasDimensions.width ) / 2;
        canvasDimensions.y = ( canvasHeight - canvasDimensions.height ) / 2;
    }
    
    /**
     * Will manipulate the value of the zoom level so that the rendering canvas
     * will appear to have been zoomed in.
     */
    public void zoomInCanvas() {
        zoomLevel += zoomFactor;
        
        updateRenderingCanvas();
    }
    
    /**
     * Will manipulate the value of the zoom level so that the rendering canvas
     * will appear to have been zoomed out.  If the canvas is at the normal
     * size of the Sprite Type, than nothing will happen.
     */
    public void zoomOutCanvas() {
        zoomLevel -= zoomFactor;
        if( zoomLevel < minimumZoomLevel ) {
            zoomLevel = minimumZoomLevel;
        }
        
        updateRenderingCanvas();
    }
    
    /**
     * Will update the dimensions of the rendering canvas and than force the
     * rendering canvas to repaint itself onto the screen.
     */
    private void updateRenderingCanvas() {
        updateRenderingCanvasDimensions();
        renderingCanvas.repaint();
    }
}
