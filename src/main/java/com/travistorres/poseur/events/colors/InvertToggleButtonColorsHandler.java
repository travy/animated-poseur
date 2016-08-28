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
 * The class <code>InvertToggleButtonColorsHandler</code> will be called when
 * ever the user desires to invert the colors specified by the background and
 * outline color buttons.
 *
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class InvertToggleButtonColorsHandler implements ActionListener {

    /**
     * Will exchange the color values between the outline and background color
     * buttons on the applications gui.
     *
     * @param   e
     *          The event that requested the color buttons be inverted.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //  Get a connection with the applicaiton user interface
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        UserInterface gui = singleton.getGUI();

        //  Request the toggle buttons color values be swaped
        gui.invertToggleButtonValues();
    }

}
