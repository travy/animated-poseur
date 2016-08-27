/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import static poseur.TheAnimatedPoseurSettings.*;
import poseur.events.animation_sequence.FrameSelectionHandler;
import poseur.sprites.AnimationStateFrame;

/**
 * This class is responsible for handling all graphical representations for the
 * sequence of animations within the Animated Poseur application.  Users will
 * be able to add new frames to each sequence pane and manipulate the order so
 * the animations move as smoothly as possible.
 * 
 * @author Travis Anthony Torres
 * @version 21 November 2012
 */
public class AnimationSequence extends JPanel {
    private Dimension frameDimensions;
    
    /**
     * Renders all of the possible animation frames onto the screen.
     */
    public AnimationSequence() {
        super();
        
        //  Sets the dimensions for all frames in the sequence
        int frameWidth = SEQUENCE_FRAMES_WIDTH;
        int frameHeight = SEQUENCE_FRAMES_HEIGHT;
        frameDimensions = new Dimension( frameWidth, frameHeight );
        
        this.setBackground( Color.GRAY );
        this.setLayout( new FlowLayout( FlowLayout.LEFT ) );
    }
    
    /**
     * Will add a new frame to the animation sequence and will get a scaled
     * representation of the shapes that are contained within the frame.
     * @param frameToAdd 
     */
    public void addFrame( AnimationStateFrame frameToAdd ) {
        //  Construct a new frame and give it a handler
        AnimationFrame newFrame = new AnimationFrame( frameToAdd, frameDimensions );
        newFrame.addMouseListener( new FrameSelectionHandler() );
        
        //  Add the frame to the animation sequence
        this.add( newFrame );
        
        //  Tricks sequence into repainting since repaint() doesnt want to work
        this.setVisible(false);
        this.setVisible(true);
    }
}
