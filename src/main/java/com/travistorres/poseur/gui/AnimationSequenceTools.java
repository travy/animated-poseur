/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.MediaTracker;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import com.travistorres.poseur.TheAnimatedPoseur;
import static com.travistorres.poseur.TheAnimatedPoseurSettings.*;
import com.travistorres.poseur.events.animations.AddAnimationStateHandler;
import com.travistorres.poseur.events.animations.AddFrameHandler;
import com.travistorres.poseur.events.animations.AnimationStatesComboboxHandler;
import com.travistorres.poseur.events.animations.DuplicateFrameHandler;
import com.travistorres.poseur.events.animations.MoveDownSequenceHandler;
import com.travistorres.poseur.events.animations.MoveUpSequenceHandler;
import com.travistorres.poseur.events.animations.RemoveAnimationStateHandler;
import com.travistorres.poseur.events.animations.RemoveFrameHandler;
import com.travistorres.poseur.events.animations.RenderAnimationStateHandler;
import com.travistorres.poseur.io.BatchLoader;
import com.travistorres.poseur.sprites.SpriteType;
import com.travistorres.poseur.states.StateManager;

/**
 * This class is responsible for managing the graphical representation of
 * animations within the Animated Poseur.  Tools such as buttons to add/remove
 * animations and their frames are provided so the user can customize as they
 * desire.
 *
 * @author travis
 */
public class AnimationSequenceTools extends JPanel {
    private JPanel      toolbarPanel;

    //  Provides tools for modifying animation states
    private JToolBar    animationStatesToolbar;
    private JComboBox   animationStates;
    private JButton     addAnimationStateButton;
    private JButton     removeAnimationStateButton;
    private JButton     renderAnimationStateButton;

    //  Provides tools for modifying individual frames
    private JToolBar    animationFramesToolbar;
    private JButton     duplicateFrameButton;
    private JButton     addFrameButton;
    private JButton     removeFrameButton;
    private JButton     moveFrameLeftButton;
    private JButton     moveFrameRightButton;

    //  Displays the sequence of the currently selected animation
    private AnimationSequence   animationSequence;

    /**
     * Constructs a new <code>AnimatedSequenceEditor</code> object which will
     * allow users to modify the order of animations for a SpriteType under
     * construction.
     *
     */
    public AnimationSequenceTools() {
        super( new BorderLayout() );

        //  Setup the controllers within the Animation seequence
        constructControllers();
        layoutControllers();
        initializeControlHandlers();
    }

    /**
     * Allocates memory for all controls used within the editor tool.  All image
     * icons that will be used for buttons will be loaded by a batch system
     * in the case the application is being used across the web.
     */
    private void constructControllers() {
        //  Keeps track of all images being loaded into the editor
        MediaTracker tracker = new MediaTracker(this);
        int trackerID = 0;

        //  The type of button that is used within batch loading macros.
        //  Makes typing easier!!!
        Class btnType = JButton.class;

        toolbarPanel    = new JPanel( new FlowLayout( FlowLayout.LEFT ) );

        //  Allocate memory for animation states toolbar
        animationStatesToolbar      = new JToolBar();
        animationStates             = new JComboBox();
        animationStates.addItem( ANIMATION_STATES_COMBOBOX_MESSAGE );
        animationStatesToolbar.add( animationStates );
        addAnimationStateButton     = (JButton)BatchLoader.initButton( ADD_ANIMATION_STATE_IMAGE, animationStatesToolbar, tracker, trackerID++, btnType, null, ADD_ANIMATION_STATE_TOOTIP );
        removeAnimationStateButton  = (JButton)BatchLoader.initButton( REMOVE_ANIMATION_STATE_IMAGE, animationStatesToolbar, tracker, trackerID++, btnType, null, REMOVE_ANIMATION_STATE_TOOLTIP );
        renderAnimationStateButton  = (JButton)BatchLoader.initButton( RENDER_ANIMATION_STATE_IMAGE, animationStatesToolbar, tracker, trackerID++, btnType, null, RENDER_ANIMATION_STATE_TOOLTIP );

        //  Allocate memory for animation frames toolbar
        animationFramesToolbar  = new JToolBar();
        duplicateFrameButton    = (JButton)BatchLoader.initButton( DUPLICATE_FRAME_IMAGE, animationFramesToolbar, tracker, trackerID++, btnType, null, DUPLICATE_FRAME_TOOLTIP );
        addFrameButton          = (JButton)BatchLoader.initButton( ADD_FRAME_IMAGE, animationFramesToolbar, tracker, trackerID++, btnType, null, ADD_FRAME_TOOLTIP );
        removeFrameButton       = (JButton)BatchLoader.initButton( REMOVE_FRAME_IMAGE, animationFramesToolbar, tracker, trackerID++, btnType, null, REMOVE_FRAME_TOOLTIP );
        moveFrameLeftButton     = (JButton)BatchLoader.initButton( MOVE_LEFT_IMAGE, animationFramesToolbar, tracker, trackerID++, btnType, null, MOVE_FRAME_LEFT_TOOLTIP );
        moveFrameRightButton    = (JButton)BatchLoader.initButton( MOVE_RIGHT_IMAGE, animationFramesToolbar, tracker, trackerID++, btnType, null, MOVE_FRAME_RIGHT_TOOLTIP );

        //  Sets up the animation sequnce
        animationSequence   = new AnimationSequence();
    }

    /**
     * Lays-out the sequence editor in an organized manner.
     */
    private void layoutControllers() {
        JScrollPane animationSequencePane = new JScrollPane( animationSequence );
        animationSequencePane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_NEVER );

        //  Places containers onto the editor
        this.add( toolbarPanel, BorderLayout.NORTH );
        this.add( animationSequencePane, BorderLayout.CENTER );

        //  Stores toolbars onto the toolbar panel
        toolbarPanel.add( animationStatesToolbar );
        toolbarPanel.add( animationFramesToolbar );
    }

    /**
     * Appends each controller within the animation sequence with its
     * corresponding handlers so that the user will be able to interact with
     * the application.
     */
    private void initializeControlHandlers() {
        //  Setup the animation state toolbar
        animationStates.addItemListener( new AnimationStatesComboboxHandler() );
        addAnimationStateButton.addActionListener( new AddAnimationStateHandler() );
        removeAnimationStateButton.addActionListener( new RemoveAnimationStateHandler() );
        renderAnimationStateButton.addActionListener( new RenderAnimationStateHandler() );

        //  Setup the animation frames toolbar
        duplicateFrameButton.addActionListener( new DuplicateFrameHandler() );
        addFrameButton.addActionListener( new AddFrameHandler() );
        removeFrameButton.addActionListener( new RemoveFrameHandler() );
        moveFrameLeftButton.addActionListener( new MoveUpSequenceHandler() );
        moveFrameRightButton.addActionListener( new MoveDownSequenceHandler() );
    }

    /**
     * Disables all of the controls when the application is in the startup
     * state.
     */
    public void activateStartupControls( boolean enabled ) {
        //  Neither of the toolbars should be active at startup.
        setEnabledToolbar( animationFramesToolbar, enabled );
        setEnabledToolbar( animationStatesToolbar, enabled );
    }

    /**
     * Will disable all of the controls found within the <code>JToolBar</code>.
     *
     * @param   tb
     *          The <code>JToolBar</code> that is to be disabled at startup.
     */
    private void setEnabledToolbar( JToolBar tb, boolean enabled ) {
        Component[] controls = tb.getComponents();
        for( Component ctrl : controls ) {
            ctrl.setEnabled( enabled );
        }
    }

    /**
     * Will return the <code>JComboBox</code> which contains a list of all
     * animation states available in the working Sprite Type.
     *
     * @return  Animation states combo-box.
     */
    public JComboBox getAnimationStatesCombobox() {
        return animationStates;
    }

    /**
     * Will obtain a reference to the <code>AnimationSequence</code> so that
     * it can be updated.
     *
     * @return  A reference to the <code>AnimationSequence</code>.
     */
    public AnimationSequence getAnimationSequence() {
        return animationSequence;
    }

    /**
     * Determines if the remove animation state button can be removed.
     *
     * @param   canRemove
     *          Determines if the button is active.
     */
    public void enableRemoveAnimationButton( boolean canRemove ) {
        removeAnimationStateButton.setEnabled( canRemove );
    }

    /**
     * Determines if user can remove animation frames from sequence.
     *
     * @param   canRemove
     *          Determines if frames can be removed
     */
    public void enableRemoveAnimationFrameButton( boolean canRemove ) {
        removeFrameButton.setEnabled( canRemove );
    }

    /**
     * Determines if the frame can be moved up the animation sequence.
     *
     * @param   canMove
     *          Determine if the frame can be moved.
     */
    public void enableMoveFrameLeftButton( boolean canMove ) {
        moveFrameLeftButton.setEnabled( canMove );
    }

    /**
     * Determines if the frame can be moved down the animation sequence.
     *
     * @param   canMove
     *          Determines if the frame can be moved.
     */
    public void enableMoveFrameRightButton( boolean canMove ) {
        moveFrameRightButton.setEnabled( canMove );
    }
}
