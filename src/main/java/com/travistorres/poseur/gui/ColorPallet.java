/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.gui;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * A ColorPallet is an object which will be used for containing a collection of
 * colors.  The user will interact with this control by determining which color
 * they desire to use and selecting the appropriate button from the pallet.
 *
 * @author Travis Anthony Torres
 * @version 1.0 Initial Release -- November 2012
 * Copyright 2012 Torres Visual Enterprise.
 */
public class ColorPallet extends JPanel {
    private static final int DEFAULT_NUM_ROWS = 2;
    private static final int DEFAULT_NUM_BUTTONS = 20;
    private static final Color DEFAULT_BUTTON_COLOR = Color.WHITE;

    private JButton[] colorButtons;

    /**
     * Will construct a new <code>ColorPallet</code> object composing of some
     * default number of rows and columns and a default color.
     */
    public ColorPallet() {
        //  Create an object of default values
        this( DEFAULT_NUM_ROWS, DEFAULT_NUM_BUTTONS, DEFAULT_BUTTON_COLOR );
    }

    /**
     * This will construct a new <code>ColorPallet</code> object with a set
     * number of rows and columns that will be composed of Button objects.  All
     * buttons will be initially be set to the color stored in
     * <code>unsetColor</code>.
     *
     * @param rows
     *          The number of rows of color buttons.
     * @param   numButtons
     *          The number of buttons to be stored within the color pallet.
     * @param   unsetColor
     *          The color to be used for any buttons that have not been defined.
     */
    public ColorPallet( int rows, int numButtons, Color unsetColor ) {
        super();

        //  Allocate memory to the array of JButtons
        colorButtons = new JButton[numButtons];

        //  Structure the way buttons will be organized within the pallet
        structureColorPallet( rows, numButtons );

        //  Add each button to the pallet
        for( JButton btn : colorButtons ) {
            btn = new JButton();
            btn.setBackground( unsetColor );
            this.add( btn );
        }
    }

    /**
     * Will setup the <code>ColorPallet</code> in a way that uses all of the
     * buttons stored within the <code>btns</code> parameter.
     *
     * @param   rows
     *          The number of rows to be setup within the pallet.
     * @param   btns
     *          An array of buttons that will exist within the ColorPallet.
     */
    public ColorPallet( int rows, JButton[] btns ) {
        super();

        //  Initialize the colorButtons variable
        colorButtons = btns;

        //  Structure the way buttons will be organized within the pallet
        int numButtons = colorButtons.length;
        structureColorPallet( rows, numButtons );

        //  Add each button to the color pallet
        for( JButton btn : colorButtons ) {
            this.add( btn );
        }
    }

    /**
     * Will structure the way that the buttons are stored with the JPanel.  A
     * limit is placed on the number of rows that are created, but the number
     * of columns is calculated based on the number of buttons that are being
     * stored.
     *
     * @param   rows
     *          The number of rows to be used to contain all of the buttons.
     * @param   numButtons
     *          The number of buttons that will be stored at the time that
     *          the method is used.
     */
    private void structureColorPallet( int rows, int numButtons ) {
        GridLayout palletLayout = new GridLayout( rows, numButtons / rows );
        this.setLayout( palletLayout );
        Border etchedBorder = BorderFactory.createEtchedBorder();
        this.setBorder( etchedBorder );
    }
}
