/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.TheAnimatedPoseur;

/**
 * The <code>ExportSpriteTypeHandler</code> class is used for calling the
 * routines for exporting the final SpriteType XML file and its accompanying
 * image files.
 * 
 * @author      Travis Anthony Torres
 * @version     1.0 November 2012   Initial Release
 */
public class ExportSpriteTypeHandler implements ActionListener {

    /**
     * Used for calling routines responsible for creating image files that will
     * be used within the SpriteType XML file.
     * 
     * @param   e
     *          The event that has called this object.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        
        System.err.println( "Exports the final sprite type product..." );
    }
    
}
