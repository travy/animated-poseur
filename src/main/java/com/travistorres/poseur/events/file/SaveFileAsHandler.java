/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.TheAnimatedPoseur;

/**
 * The <code>SaveFileAsHandler</code> will construct a new SpriteType file
 * so that the user can close the application and continue the project at a 
 * later time.  Unlike the <code>SaveButtonHandler</code> class, whenever a
 * request is made to <code>SaveFileAsHandler</code> the user will need to
 * enter a new filename to save the file as.
 * 
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class SaveFileAsHandler implements ActionListener {

    /**
     * Calls a prompt for the user to enter a new name for the currently
     * editable SpriteType object to be saved as.
     * 
     * @param   e
     *          The event that requested the SpriteType be saved in a new file.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        
        System.err.println( "Will save the SpriteType as a new file..." );
    }
    
}
