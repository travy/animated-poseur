/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.events.zooming;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import com.travistorres.poseur.TheAnimatedPoseur;
import com.travistorres.poseur.gui.ChangeSpriteDimensionsDialog;
import com.travistorres.poseur.gui.UserInterface;
import com.travistorres.poseur.states.StateManager;

/**
 * The class <code>ResizeCanvasHandler</code> will call a dialogue window which
 * ask the user to enter the new dimensions for the SpriteType.  Once the user
 * has pressed ok on the dialogue window, the information will be sent to the
 * sprite type manager where the SpriteType will be resized.
 *
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class ResizeCanvasHandler implements ActionListener {

    /**
     * Will resize th dimensions for the SpriteType being constructed.
     *
     * @param   e
     *          The event which requested the SpriteType be resized.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        UserInterface gui = singleton.getGUI();
        StateManager sm = singleton.getStateManager();

        ChangeSpriteDimensionsDialog csdd = new ChangeSpriteDimensionsDialog();

        /*
        //  Request the sprite be resized
        Dimension spriteSize = new Dimension();
        if( requestSpriteResize( spriteSize, gui ) ) {
            //  Resize the sprite
            int width = spriteSize.width;
            int height = spriteSize.height;
            sm.resizeSprite(width, height);
        }*/
    }

    /**
     * Generates a dialog box which ask the user for the new dimensions of the
     * Sprite being constructed.  If the user provides a valid dimension for
     * the sprite than the sprite will be resize otherwise a message will be
     * displayed informing them of the issue.
     *
     * @param   size
     *          The new dimensions for the Sprite.
     * @param   display
     *          The graphical interface that all dialog box's will be displayed
     *          onto.
     *
     * @return  <code>true</code> if the dimensions provided by the user are
     *          valid dimensions for a sprite.
     * @return  <code>false</code> if the dimensions the user provided are not
     *          valid dimensions of a Sprite or the form was not filled
     *          properly.
     */
    private boolean requestSpriteResize( Dimension size, JFrame display ) {
        boolean resizeSprite = false;



        return resizeSprite;
    }
}
