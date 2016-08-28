/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.travistorres.poseur.TheAnimatedPoseur;
import static com.travistorres.poseur.TheAnimatedPoseurSettings.*;
import com.travistorres.poseur.sprites.SpriteType;
import com.travistorres.poseur.states.StateManager;

/**
 * Will present the user with a forum that is used for changing the dimensions
 * of the SpriteType in progress.  If the user fills in valid information than
 * the dimensions will be changed otherwise the user will be presented with an
 * error message alerting them that the dimensions could not be changed.
 *
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012       Initial Release
 */
public class ChangeSpriteDimensionsDialog extends JDialog {
    private JLabel enterDimensionsLabel;
    private JLabel enterWidthLabel;
    private JLabel enterHeightLabel;

    private JTextField widthField;
    private JTextField heightField;

    private JPanel buttonPanel;
    private JButton okButton;
    private JButton cancelButton;

    /**
     * Will setup all of the components of the dialog and lay them out in an
     * order that will allow the user to fill out the new dimensions for the
     * Sprite.
     */
    public ChangeSpriteDimensionsDialog() {
        super();

        //  Set properties for dialog box
        setModal( true );   //  Prevents user from selecting application window
        setTitle( SPRITE_DIMENSIONS_TITLE_MESSAGE );

        //  Store all controls in the correct positions with correct values
        initComponents();
        layoutComponents();
        initLocation(); //  Center window on screen
        initHandlers();

        //  Make the dialog visible once everything is functioning
        setVisible( true );
    }

    /**
     * Centers the dialog box onto the middle of the screen so the user will
     * see it as soon as it appear without the need of looking around for it.
     */
    private void initLocation() {
        //  Get the dimensions for the users display
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //  Get the coordinates for the center of screen
        int xpos = ( screenSize.width - getWidth() ) / 2;
        int ypos = ( screenSize.height - getHeight() ) / 2;
        setLocation( xpos, ypos );
    }

    /**
     * Will allocate memory to all controls and set the width and height text
     * fields with the current width and height of the Sprite project.
     */
    private void initComponents() {
        //  Obtain the sprite dimensions to fill initial value of text fields
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        StateManager sm = singleton.getStateManager();
        SpriteType sprite = sm.getSpriteType();

        //  Initialize labels with corresponding text
        enterDimensionsLabel = new JLabel( SPRITE_DIMENSIONS_DESCRIPTIVE_MESSAGE );
        enterWidthLabel = new JLabel( SPRITE_DIMENSIONS_WIDTH_FIELD );
        enterHeightLabel = new JLabel( SPRITE_DIMENSIONS_HEIGHT_FIELD );

        //  Initialize text fields with current dimensions for sprite type
        Integer currentWidth = sprite.getWidth();
        Integer currentHeight = sprite.getHeight();
        widthField = new JTextField( 15 );
        widthField.setText( currentWidth.toString() );
        heightField = new JTextField( 15 );
        heightField.setText( currentHeight.toString() );

        //  Initialize buttons
        buttonPanel = new JPanel();
        okButton = new JButton( "OK" );
        cancelButton = new JButton( "Cancel" );
    }

    /**
     * Lays out all of the controls, in an organized fashion, on top of the
     * dialog window so that the user will know how to use the forum.
     */
    private void layoutComponents() {
        //  Stores all of the buttons and labels in their correct position
        setLayout( new GridBagLayout() );
        addComponent( enterDimensionsLabel, 0, 0, 2, 1 );
        addComponent( enterWidthLabel,      0, 1, 1, 1 );
        addComponent( widthField,           1, 1, 1, 1 );
        addComponent( enterHeightLabel,     0, 2, 1, 1 );
        addComponent( heightField,          1, 2, 1, 1 );
        addComponent( buttonPanel,          0, 3, 1, 1 );

        //  Stores the buttons on the button panel
        buttonPanel.add( okButton );
        buttonPanel.add( cancelButton );

        //  Condenses the dialog so all controls fit comfortably within it
        pack();
    }

    /**
     * This helper method lets us easily add components to our dialog useing
     * <code>GridBagLayout</code>.
     *
     * Written by Richard McKenna
     *
     * @param   c
     *          The control to add to the dialog, like a button.
     * @param   col
     *          The column in the grid where it will start.
     * @param   row
     *          The row in the grid where it will start.
     * @param   colSpan
     *          The number of columns in the grid this control will span.
     * @param   rowSpan
     *          The number of rows in the grid this control will span.
     */
    private void addComponent( Component c, int col, int row, int colSpan, int rowSpan ) {
        //  THIS PROVIDES INTERNAL SPACING
        Insets insets = new Insets( 3, 3, 3, 3 );

        // SETUP ALL THE SETTINGS FOR ADDING THE COMPONENT
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.gridwidth = colSpan;
        gbc.gridheight = rowSpan;
        gbc.insets = insets;
        gbc.anchor = GridBagConstraints.LINE_START;

        //  AND HERE IT GOES
        add( c, gbc );
    }

    /**
     * Will define what will occur if the user presses the OK or CANCEL button
     * within the forum.  Depending on how the user fills the forum out, the
     * handlers may send the user back to the application with no changes
     * occurring, or an error message will come up or the dimensions of the
     * Sprite will be changed.
     */
    private void initHandlers() {
        OkHandler okPressed = new OkHandler();
        widthField.addActionListener( okPressed );
        heightField.addActionListener( okPressed );
        okButton.addActionListener( okPressed );
        cancelButton.addActionListener( new CancelHandler() );
    }

    /**
     * Will determine if the user has filled the forum correctly and if so will
     * ask the state manager to resize the sprite; otherwise, an error message
     * will show informing the user of what went wrong.
     */
    private void requestSpriteResize() {
        /*
         * TODO-  It is 100% necessary to setup a try-block to in case the user
         * used text instead of integer values within the text fields.
         */
        try {
            //  Parse the width and height out of the textfields
            int width = Integer.parseInt( widthField.getText() );
            int height = Integer.parseInt( heightField.getText() );

            //  Check if the dimensions are within the valid range of a sprite
            if(     width > MINIMUM_SPRITE_DIMENSION &&
                    width <= MAXIMUM_SPRITE_DIMENSION &&
                    height > MINIMUM_SPRITE_DIMENSION &&
                    height <= MAXIMUM_SPRITE_DIMENSION ) {
                //  Get the statemanager and resize the sprite
                TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
                StateManager sm = singleton.getStateManager();

                //  Have sprite manager resize the sprite
                sm.resizeSprite( width, height );
                setVisible( false );    //  close the window
            } else {
                /*  TODO-  Display an error message alerting the user about the
                *  minimum and maximum values of Sprites.
                */
            }
        } catch( NumberFormatException | NullPointerException ex ) {
            //  Display error message
        }
    }

    /**
     * This private class will respond whenever the user either presses the ok
     * button or presses the ENTER-key while editing one of the text fields.
     */
    private class OkHandler implements ActionListener {

        /**
         * Will request that the values within the text fields be used to
         * resize the Sprite.
         *
         * @param   e
         *          The event that requested the Sprite be resized.
         */
        @Override
        public void actionPerformed( ActionEvent e ) {
            requestSpriteResize();
        }

    }

    /**
     * This private class will be triggered whenever the user presses the
     * cancel button.
     */
    private class CancelHandler implements ActionListener {

        /**
         * Will close the dialog window so that the user will not resize the
         * Sprite's dimensions.
         *
         * @param   e
         *          The event that requested the window be closed.
         */
        @Override
        public void actionPerformed( ActionEvent e ) {
            setVisible( false );
        }

    }

}
