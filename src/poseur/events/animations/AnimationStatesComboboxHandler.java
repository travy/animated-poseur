/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.animations;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import poseur.TheAnimatedPoseur;
import static poseur.TheAnimatedPoseurSettings.*;
import poseur.sprites.SpriteType;
import poseur.states.StateManager;

/**
 * The <code>AnimationStatesComoboboxHandler</code> will be triggered whenever
 * the user chooses an animation state from the drop down list.  After choosing
 * an animation, the user selected <code>AnimationState</code> will be updated
 * and the user interface will display all of the animations frames within the
 * animation sequence.
 * 
 * @author      Travis Anthony Torres
 * @version     1.0     December 2012       Initial Release
 */
public class AnimationStatesComboboxHandler implements ItemListener {

    /**
     * Will request the value of the selected <code>AnimationState</code> be
     * updated and that the user interface populate the animation sequence with
     * all of the corresponding <code>AnimationStateFrame</code> objects.
     * 
     * @param   e 
     *          The event that requested the application update the animation
     *          sequence.
     */
    @Override
    public void itemStateChanged( ItemEvent e ) {
        String selectedAnimation = (String)e.getItem();
        
        //  Should ignore the selection of the combobox message
        if( !selectedAnimation.equals( ANIMATION_STATES_COMBOBOX_MESSAGE ) &&
                e.getStateChange() == ItemEvent.SELECTED ) {
            //  Obtain a rference to the Sprite Type object
            TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
            StateManager sm = singleton.getStateManager();
            SpriteType sprite = sm.getSpriteType();
            
            //  Update the selected animation
            sprite.setSelectedAnimationState( selectedAnimation );
        }
    }
    
}
