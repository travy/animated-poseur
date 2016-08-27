/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.colors;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;
import poseur.TheAnimatedPoseur;
import static poseur.TheAnimatedPoseurSettings.*;
import poseur.gui.ColorToggleButton;
import poseur.gui.UserInterface;

/**
 * The <code>ColorChooserHandler</code> will open up a 
 * <code>JColorChooser</code> dialog window for the user to select thir desired
 * color from.  Once the user has selected a color, the currently selected
 * toggle button will be set to that color.
 * 
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class ColorChooserHandler implements ActionListener {

    /**
     * Will ask the user to select a color from the dialog box that pops up and
     * than proceeds to set the currently set toggle button.
     * 
     * @param   e
     *          The event that requested a color chooser dialog.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //  Request communication with the user interface
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        UserInterface gui = singleton.getGUI();
        
        //  Let the user select their own color
        Color selectedColor = JColorChooser.showDialog( 
                gui, SELECT_CUSTOM_COLOR_TEXT, Color.yellow);
        
        //  Sets the color of the currently toggled color button
        ColorToggleButton toggledButton = gui.getSelectedToggleButton();
        toggledButton.setBackground( selectedColor );
    }
    
}
