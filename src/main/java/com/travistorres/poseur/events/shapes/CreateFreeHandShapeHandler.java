/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.shapes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.TheAnimatedPoseur;

/**
 * The class <code>CreateFreeHandShapeHandler</code> will update the state
 * manager so that the user will be able to render shapes onto the rendering
 * canvas.
 * 
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class CreateFreeHandShapeHandler implements ActionListener {

    /**
     * Will allow the user to render a shape of their own design on the canvas.
     * 
     * @param   e
     *          The event that requested the user to have the ability to draw
     *          a freehand shape.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        
        System.err.println( "Render a freehand shape..." );
    }
    
}
