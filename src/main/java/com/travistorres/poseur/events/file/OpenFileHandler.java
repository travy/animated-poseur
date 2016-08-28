/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.events.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.travistorres.poseur.TheAnimatedPoseur;

/**
 * The <code>OpenFileHandler</code> will generate a file navigator so the
 * user can select the desired SpriteType file.  Based on the file chosen from
 * the file navigator, information from said file will be used to construct
 * all animation states and their accompanying frames so the user can continue
 * to edit the SpriteType.
 *
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class OpenFileHandler implements ActionListener {

    /**
     * Will call the file navigator so that they user can select their desired
     * SpriteType object.
     *
     * @param   e
     *          The event that requested a file be loaded into memory.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();

        System.err.println( "Will display an option pane for the user to " +
                "select their desired SpriteType file..." );
    }

}
