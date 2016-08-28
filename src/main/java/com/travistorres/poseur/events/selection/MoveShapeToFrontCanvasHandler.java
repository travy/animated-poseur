/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.events.selection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.travistorres.poseur.TheAnimatedPoseur;

/**
 * The class <code>MoveShapeToFrontCanvasHandler</code> is responsible for
 * moving the selected shape to the front of the rendering canvas so that it
 * is not covered by other shapes.
 *
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class MoveShapeToFrontCanvasHandler implements ActionListener {

    /**
     * Communicates with the frame that is currently being edited, and moves
     * the selected shape to the end of the rendering scheduler.
     *
     * @param   e
     *          The action that requested the selected shape be moved to the
     *          front of the screen.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();

        System.err.println( "Move selected shape to front of canvas..." );
    }

}
