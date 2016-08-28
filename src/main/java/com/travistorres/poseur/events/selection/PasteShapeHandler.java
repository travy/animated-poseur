/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.selection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.TheAnimatedPoseur;

/**
 * The class <code>PasteShapeHandler</code> will paste the contents of the
 * clipboard to the top-left corner of the screen.
 * 
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class PasteShapeHandler implements ActionListener {

    /**
     * Paste the shape contained within the clipboard onto the top-left corner
     * of the rendering canvas.
     * 
     * @param   e 
     *          The action that requested a shape be paste to the canvas.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        
        System.err.println( "Contents of clipboard will paste to canvas..." );
    }
    
}
