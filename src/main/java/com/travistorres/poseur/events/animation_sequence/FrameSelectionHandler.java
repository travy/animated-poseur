/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.animation_sequence;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import poseur.TheAnimatedPoseur;
import poseur.gui.AnimationFrame;
import poseur.sprites.AnimationStateFrame;
import poseur.states.StateManager;

/**
 * The class <code>FrameSelectionHandler</code> will be triggered whenever the
 * user selects a frame from the animation sequence pane.  All of the 
 * information regarding the selected frame will be loaded and the rendering
 * canvas will render the newly selected frame onto the screen.
 * 
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012       Initial Release
 */
public class FrameSelectionHandler implements MouseListener {

    /**
     * Will request that the currently selected animation state frame be updated
     * to the frame the user selected from the animation sequence.
     * 
     * @param   e 
     *          The event that requested the currently selected animation state
     *          be changed.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        //  Select the currently selected frame
        AnimationFrame selectedFrame = (AnimationFrame)e.getComponent();
        AnimationStateFrame selectedAnimationFrame = selectedFrame.getAnimationFrame();
        
        //  Select the currently selected frame
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        StateManager sm = singleton.getStateManager();
        sm.changeSelectedFrame( selectedAnimationFrame );
    }

    /* UNUSED METHODS */
    
    @Override
    public void mousePressed(MouseEvent e) { /* INTENTIONALLY LEFT BLANK */ }

    @Override
    public void mouseReleased(MouseEvent e) { /* INTENTIONALLY LEFT BLANK */ }

    @Override
    public void mouseEntered(MouseEvent e) { /* INTENTIONALLY LEFT BLANK */ }

    @Override
    public void mouseExited(MouseEvent e) { /* INTENTIONALLY LEFT BLANK */ }
    
}
