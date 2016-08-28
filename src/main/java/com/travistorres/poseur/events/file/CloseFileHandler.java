/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.events.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.travistorres.poseur.TheAnimatedPoseur;

/**
 * The <code>CloseFileHandler</code> class is used for closing down a
 * Animated Poseur Project safely.  Upon exit, an option pane should show up
 * which will ask the user to save the project, if the project has not already
 * been saved.  Once the project is safe to close down, the java virtual
 * machine is shutdown.
 *
 * @author      Travis Anthony Torres
 * @version     1.0 November 2012 - Initial Release
 */
public class CloseFileHandler implements ActionListener {

    /**
     * Calls the state manager framework to determine if the project has been
     * saved before closing.
     *
     * @param   e
     *          Event that requested the project close.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();

        System.err.println("Project will be shutdown...");
    }

}
