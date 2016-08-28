/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.events.colors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.travistorres.poseur.TheAnimatedPoseur;
import com.travistorres.poseur.gui.UserInterface;

/**
 * The <code>OutlineColorHandler</code> will be called whenever the user goes
 * to toggle the outline color button into the on position.  After pressing the
 * button, the background color button will be toggled into the off position.
 * Once set, the user will be able to select a color from the color pallet
 * to the right of the button.
 *
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class OutlineColorHandler implements ActionListener {

    /**
     * Will call the user interface to request the outline color button be
     * switched to the on position.
     *
     * @param   e
     *          The event that requested the outline button be toggled on.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //  Obtain a reference to the user interface
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        UserInterface gui = singleton.getGUI();

        //  Turn on the outline toggle button
        gui.activateOutlineToggleButton();
    }

}
