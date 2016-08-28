/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.states;

import poseur.gui.AnimationFrame;
import poseur.sprites.AnimationStateFrame;

/**
 * The class <code>AnimationFrameState</code> is responsible for understanding
 * information about it corresponding <code>AnimationStateFrame</code> and
 * having it scale down to fit into the frame sequence.
 * 
 * @author      Travis Anthony Torres
 * @version     1.0     December 2012       Initial Release
 */
public class AnimationFrameState {
    private AnimationStateFrame animationStateFrame;
    private AnimationFrame frameToRender;
    
    public AnimationFrameState( AnimationFrame frame ) {
        frameToRender = frame;
    }
    
    public void renderFrame() {
        
    }
}
