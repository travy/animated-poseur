/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.animations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.TheAnimatedPoseur;

/**
 * The <code>RenderAnimationStateHandler</code> will open up a rendering
 * window where the user will be able to view their Sprite Type be rendered
 * at real-time.  Within this mode the user will be able to set the rate that
 * animations flip their frames.
 * 
 * @author      Travis Anthony Torres
 */
public class RenderAnimationStateHandler implements ActionListener {

    /**
     * Will request information be sent to the rendering tool so that the user
     * can watch their Sprite Type be rendered in an animated way.
     * 
     * @param   e 
     *          The event that requested that the Sprite Type render.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        
        System.err.println( "Rendering window will open..." );
    }
    
}
