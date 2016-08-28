/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.sprites;

import java.awt.Dimension;
import java.util.Iterator;
import java.util.LinkedList;
import poseur.shapes.PoseurShape;

/**
 * The class <code>SpritePose</code> will represent one frame within an 
 * animation sequence.  All of the shapes that have been constructed for the
 * frame will be kept track by this one instance.
 * 
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class SpritePose {
    private Dimension dimensions;
    private LinkedList<PoseurShape> shapes;
    private PoseurShape selectedShape;
    
    /**
     * Will setup the initial settings for the sprite frame such as the dimensions
     * for the frame and allocating memory for all shapes that will be added to
     * the frame.
     * 
     * @param   width
     *          The width of the frame (SpriteType).
     * @param   height
     *          The height of the frame (SpriteType).
     */
    public SpritePose( int width, int height ) {
        //  Sets the width and height of the pose and allocates memory for shapes
        dimensions = new Dimension( width, height );
        shapes = new LinkedList<>();
        selectedShape = null;
    }
    
    /**
     * Retrieves the size of the frame.
     * 
     * @return The size of the frame
     */
    public Dimension getDimensions() {
        return dimensions;
    }
    
    /**
     * Allows the user to set a new size for the frame.
     * 
     * @param   width
     *          The new width of the frame.
     * @param   height 
     *          The new height of the frame.
     */
    public void setDimensions( int width, int height ) {
        dimensions = new Dimension( width, height );
    }
    
    /**
     * Adds a new <code>PoseurShape</code> to the list of shapes that exist
     * within the frame.  All new shapes will be added to the end of the list
     * so that they will appear to render on the top of the rendering canvas.
     * 
     * @param   shape 
     *          The shape to be added to the frame/
     */
    public void addShape( PoseurShape shape ) {
        shapes.add( shape );
    }
    
    /**
     * Moves the selected shape from its current position within the list of
     * shapes to the end of the list so that it will appear to render on the
     * top of the rendering canvas.
     */
    public void moveSelectedShapeToFront() {
        shapes.remove( selectedShape );         //  remove from list
        shapes.offerLast( selectedShape );      //  move to last position in list
    }
    
    /**
     * Moves the selected shape from its current position within the list of
     * shapes to the start of the list so that all shapes afterwords will be
     * rendered on top of it.
     */
    public void moveSelectedShapeToBack() {
        shapes.remove( selectedShape );
        shapes.offerFirst( selectedShape );
    }
    
    /**
     * Accuire an iterator for all of the shapes that exist within the current
     * frame.
     * 
     * @return An iterator for all of the shapes within the frame.
     */
    public Iterator getShapesIterator() {
        return shapes.iterator();
    }
}
