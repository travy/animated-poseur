/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.animations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.TheAnimatedPoseur;

/**
 * The class <code>DuplicateFrameHandler</code> is responsible for creating
 * a new frame that is an exact duplicate of some other frame.
 * 
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class DuplicateFrameHandler implements ActionListener {

    /**
     * Will create a duplicate of the currently selected animation frame.
     * 
     * @param   e 
     *          The event that requested a frame be duplicated.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        
        System.err.println( "Frame will be duplicated..." );
    }
    
}
