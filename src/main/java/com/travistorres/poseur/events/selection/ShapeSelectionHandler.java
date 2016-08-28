/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.events.selection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.travistorres.poseur.TheAnimatedPoseur;
import com.travistorres.poseur.states.ApplicationState;
import com.travistorres.poseur.states.StateManager;

/**
 * The class <code>ShapeSelectionHandler</code> is responsible for selecting
 * shapes that have already been rendered onto the rendering canvas.  Once a
 * shape is selected the state manager will allow the user to modify properties
 * of the shape such as its color and position.
 *
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class ShapeSelectionHandler implements ActionListener {

    /**
     * Enables the user to select shapes on the rendering canvas.
     *
     * @param   e
     *          The action that requested the ability to select shapes.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        StateManager sm = singleton.getStateManager();

        sm.setState( ApplicationState.SELECT_SHAPE_STATE );
    }

}
