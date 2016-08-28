/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.events.selection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.travistorres.poseur.TheAnimatedPoseur;

/**
 * The class <code>CopyShapeHandler</code> will copy the dimensions of the
 * currently selected shape and store the duplicate into the clipboard.  Unlike
 * the <code>CutShapeHandler</code>, <code>CopyShapeHandler</code> will not
 * remove the shape from the rendering canvas.
 *
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class CopyShapeHandler implements ActionListener {

    /**
     * Will copy a selected shape into the clipboard.
     *
     * @param   e
     *          The action that requested a shape be copied.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();

        System.err.println( "Shape will be copied into clipboard..." );
    }

}
