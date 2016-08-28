/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.events.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.travistorres.poseur.TheAnimatedPoseur;

/**
 * The <code>SaveFileHandler</code> will save the currently editable
 * SpriteType in a file that has been created for it.  This class will not
 * construct a new file to save write data to.
 *
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class SaveFileHandler implements ActionListener {

    /**
     * Calls the file manager framework so that any newly created features of
     * the SpriteType object can be written to the save file.
     *
     * @param   e
     *          The event that requested that the SpriteTypes data be written
     *          to a file.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();

        System.err.println( "SpriteType will be saved..." );
    }

}
