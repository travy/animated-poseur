/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.animations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.TheAnimatedPoseur;
import poseur.states.StateManager;

/**
 * The class <code>AddFrameHandler</code> will construct a new frame that is
 * composed of all the shapes within the frame that had preceeded it.
 * 
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class AddFrameHandler implements ActionListener {

    /**
     * Will create a new frame composed of all shapes within the preceeding
     * frame.
     * 
     * @param   e
     *          The event that requested a new frame be constructed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        StateManager sm = singleton.getStateManager();
        
        //  Have the state manager construct a new frame
        sm.addFrame();
    }
    
}
