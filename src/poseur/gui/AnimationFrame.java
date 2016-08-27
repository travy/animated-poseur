/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import poseur.sprites.AnimationStateFrame;

/**
 *
 * @author travis
 */
public class AnimationFrame extends JPanel {
    
    private AnimationStateFrame animationFrame;
    
    public AnimationFrame( AnimationStateFrame animationFrame, Dimension frameDimensions ) {
        super();
        
        this.animationFrame = animationFrame;
        
        //  Sets dimensions for the frame
        setPreferredSize( frameDimensions );
        setMinimumSize( frameDimensions );
        setMaximumSize( frameDimensions );
        setSize( frameDimensions );
        setBackground( Color.WHITE );
    }
    
    public AnimationStateFrame getAnimationFrame() {
        return animationFrame;
    }
    
}
