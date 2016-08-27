/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.zooming;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.TheAnimatedPoseur;
import poseur.states.StateManager;

/**
 * The class <code>ZoomOutHandler</code> will request that the rendering canvas
 * scale all of its shapes to a smaller size.
 * 
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class ZoomOutHandler implements ActionListener {

    /**
     * Request the rendering canvas scale all shapes to a smaller size.
     * 
     * @param   e
     *          The event that requested all shapes scale to a smaller size.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        StateManager sm = singleton.getStateManager();
        
        sm.zoomOutCanvas();
    }
    
}
