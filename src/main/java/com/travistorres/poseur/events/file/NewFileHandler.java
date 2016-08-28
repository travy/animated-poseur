/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.events.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.travistorres.poseur.TheAnimatedPoseur;
import com.travistorres.poseur.io.FileManager;

/**
 * The <code>NewFileHandler</code> will make a request to create a new
 * Animated Poseur save file.  If the save file is safe to create, does not
 * conflict with any other files, than a prompt will be called asking for the
 * size of the SpriteType object which will then be used to construct the first
 * <code>PoseCanvas</code> and <code>AnimationState</code> object.
 *
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class NewFileHandler implements ActionListener {

    /**
     * Calls the file manager framework to attempt to create a new file.
     *
     * @param   e
     *          The event that requested the SpriteType file.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //  Acquire the file manager from the singleton object
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        FileManager fm = singleton.getFileManager();

        //  Attempt to make a new Sprite Type
        fm.requestNewSpriteType();
    }

}
