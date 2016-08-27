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
 * The class <code>ZoomInHandler</code> is responsible for requesting the
 * rendering canvas adjust all shapes to appear larger than they truly are.
 * 
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class ZoomInHandler implements ActionListener {

    /**
     * Request the rendering canvas scale all shapes it is rendering.
     * 
     * @param   e 
     *          The event which requested shapes be scaled.s
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        StateManager sm = singleton.getStateManager();
        
        sm.zoomInCanvas();
    }
    
}
