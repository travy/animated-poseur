/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.events.animations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.travistorres.poseur.TheAnimatedPoseur;
import com.travistorres.poseur.states.StateManager;

/**
 * The class <code>RemoveFrameHandler</code> will remove the currently selected
 * animation frame from the animation sequence and memory.  The user should be
 * informed via a dialog box that deleting a frame will make it impossible to
 * retrieve.
 *
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class RemoveFrameHandler implements ActionListener {

    /**
     * Will request that the currently selected animation frame be removed.
     *
     * @param   e
     *          The event that requested the frame be removed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        StateManager sm = singleton.getStateManager();
        sm.removeFrame();
    }

}
