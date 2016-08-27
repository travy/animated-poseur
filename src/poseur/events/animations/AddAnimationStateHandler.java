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
 * The class <code>AddAnimationStateHandler</code> will be called whenever the
 * user desires to create a new Animation State for their current project.
 * 
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class AddAnimationStateHandler implements ActionListener {

    /**
     * Will call the Sprite Manager and request a new animation state be created
     * and than will communicate with the user interface to make sure that it
     * will update the information within the animation sequence toolbars.
     * 
     * @param   e 
     *          The event that requested a new animation state be created.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //  Get the state manager
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        StateManager sm = singleton.getStateManager();
        
        //  Construct a new animation state
        sm.addAnimationState();
    }
    
}
