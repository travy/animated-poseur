/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.events.selection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.travistorres.poseur.TheAnimatedPoseur;

/**
 * The <code>UndoActionHandler</code> class is responsible for undoing an
 * action that the user has recently made on the rendering canvas.  If the user
 * had just drawn a Line Segment, then the Line Segment will be removed from
 * the canvas and if their was an action made before hand, it will be the
 * previous action performed.
 *
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class UndoActionHandler implements ActionListener {

    /**
     * Calls the state manager to undo the previous action made on the
     * rendering canvas.
     *
     * @param   e
     *          The action that requested communication with the state manager.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();

        System.err.println( "The previous action will be undone..." );
    }

}
