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
 * The class <code>RemoveAnimationStateHandler</code> will remove the currently
 * selected animation state from the SpriteType at work.  Once the user has
 * removed an animation state all of its accompanying frames will also be removed
 * and cannot be recovered after words, a dialog box should appear to alert the
 * users of this fact.
 * @author travis
 */
public class RemoveAnimationStateHandler implements ActionListener {

    /**
     * Will request that the currently selected animation state be removed from
     * memory.
     *
     * @param   e
     *          The event that requested the animation state be removed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //  Gets the state manager
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        StateManager sm = singleton.getStateManager();

        //  Removes the currently selected animation state
        sm.removeAnimationState();
    }

}
