/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.animations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.TheAnimatedPoseur;

/**
 * The class <code>MoveDownSequenceHandler</code> will move the currently
 * selected animation frame, down the animation sequence so that it will be
 * rendered at a later time than it is already scheduled to.  This handler will
 * not perform any task if there is no frame to the right of the currently
 * selected frame.
 * 
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class MoveDownSequenceHandler implements ActionListener {

    /**
     * Will move the currently selected frame down the animation sequence order.
     * 
     * @param   e 
     *          The event that requested the frame be moved down the animation
     *          sequence.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        
        System.err.println( "Moving frame down animation sequence..." );
    }
    
}
