/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.events.selection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.travistorres.poseur.TheAnimatedPoseur;

/**
 * The <code>RedoActionHandler</code> class is responsible for communicating
 * with the state manager to redo any user operations that had been undone
 * by a previous call to an instance of a <code>UndoActionHandler</code>.
 *
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class RedoActionHandler implements ActionListener {

    /**
     * Calls the state manager to determine if there is an operation that can
     * be redone.
     *
     * @param   e
     *          The action that requested to communicate with the state
     *          manager.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();

        System.err.println( "Redo a previously undone aciton..." );
    }

}
