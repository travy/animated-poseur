/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.colors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.TheAnimatedPoseur;
import poseur.gui.UserInterface;

/**
 * The <code>BackgroundColorHanler</code> will be called whenever the user
 * selects the background button to toggle it into the on position.  Once in
 * the on position, the user can change the color for shapes backgrounds.  The
 * class will also un-toggle the outline color button.
 * @author travis
 */
public class BackgroundColorHandler implements ActionListener {

    /**
     * Communicates with th user interface so that it can request the
     * background color button be switched to the on position.
     * 
     * @param   e
     *          The event that requested the background button be toggled to
     *          the on position.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //  Request communication with the user interface
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        UserInterface gui = singleton.getGUI();
        
        //  Toggles the background button to on
        gui.activateBackgroundToggleButton();;
    }
    
}
