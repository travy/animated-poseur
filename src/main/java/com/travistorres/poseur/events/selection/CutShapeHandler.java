/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.selection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.TheAnimatedPoseur;

/**
 * The class <code>CutShapeHandler</code> will copy the dimensions of the
 * currently selected shape into the state managers clipboard and procedes to
 * remove the shape from the rendering canvas.
 * 
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class CutShapeHandler implements ActionListener {

    /**
     * Copies the currently selected shape into the clipboard and than removes
     * the shape from the rendering canvas.
     * 
     * @param   e 
     *          The action that requested a shape be cut.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        
        System.err.println( "Will cut the selected shape..." );
    }
    
}
