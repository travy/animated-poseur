/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.events.animations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.travistorres.poseur.TheAnimatedPoseur;

/**
 * The class <code>MoveUpSequenceHandler</code> will request that the user
 * interface move the currently selected animation frame up the animation
 * sequence so that it will be rendered at an earlier time than it already is.
 *
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class MoveUpSequenceHandler implements ActionListener {

    /**
     * Will move the animation frame up the animation sequence.
     *
     * @param   e
     *          The event which requested the frame move up the sequence.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();

        System.err.println( "Moving frame up animation sequence..." );
    }

}
