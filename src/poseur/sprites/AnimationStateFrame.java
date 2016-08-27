/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.sprites;

import java.util.Iterator;
import java.util.LinkedList;
import poseur.shapes.PoseurShape;

/**
 * The <code>AnimationStateFrame</code> will represent one frame or pose, within
 * an animation sequence.  Each frame will contain its own set of shapes which
 * may be manipulated to accomplish the task of the frame.
 * 
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012       Initial Release
 */
public class AnimationStateFrame {
    private LinkedList<PoseurShape> shapes;
    private PoseurShape selectedShape;
    
    public AnimationStateFrame() {
        shapes = new LinkedList<>();
        selectedShape = null;
    }
    
    public void addShapeToFrame( PoseurShape shape ) {
        shapes.add( shape );
    }
    
    /**
     * Selects the shape that is currently selected by the user.
     * 
     * @return The currently selected shape.
     */
    public PoseurShape getSelectedShape() {
        return selectedShape;
    }
    
    /**
     * Sets the type of shape that is currently being selected by the user.
     * 
     * @param   selectedShape 
     *          The shape that is selected by the user.
     */
    public void setSelectedShape( PoseurShape selectedShape ) {
        this.selectedShape = selectedShape;
    }
    
    /**
     * Will retrieve an iterator of all the various shapes that go into making
     * the frame.
     * 
     * @return  An iterator of all shapes in the frame.
     */
    public Iterator iterator() {
        return shapes.iterator();
    }
    
    /**
     * Construct a duplicate frame.
     * 
     * @return A duplicate of this frame.
     */
    @Override
    protected AnimationStateFrame clone() {
        AnimationStateFrame copyFrame = new AnimationStateFrame();
        
        //  Copy instances of each shape within the frame over to the next frame
        Iterator<PoseurShape> iter = iterator();
        while( iter.hasNext() ) {
            PoseurShape shape = iter.next();
            copyFrame.addShapeToFrame( shape.clone() );
        }
        
        return copyFrame;
    }
}
