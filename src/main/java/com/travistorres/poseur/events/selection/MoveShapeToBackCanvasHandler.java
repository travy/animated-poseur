/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.selection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.TheAnimatedPoseur;

/**
 * The class <code>MoveShapeToBackCanvasHandler</code> will move a selected
 * shape to the back of the rendering canvas so that all other shapes that
 * intersect with it appear to overlap it.
 * 
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class MoveShapeToBackCanvasHandler implements ActionListener {

    /**
     * Will place the selected shape to the start of the rendering scheduler.
     * 
     * @param   e 
     *          The action that requested the shape be moved to the back of the
     *          rendering canvas.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        
        System.err.println( "Moves selected shape to back of canvas..." );
    }
    
}
