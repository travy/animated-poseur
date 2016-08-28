/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.events.shapes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.travistorres.poseur.TheAnimatedPoseur;
import com.travistorres.poseur.shapes.PoseurShapeType;
import com.travistorres.poseur.states.StateManager;

/**
 * The class <code>CreateEllipseHandler</code> is used for updating the state
 * manager when the user is ready to render an elliptical shape onto the
 * rendering canvas.
 *
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class CreateEllipseHandler implements ActionListener {

    /**
     * Will render an elliptical shape onto the canvas.
     *
     * @param   e
     *          The event that requested an ellipse be rendered onto the
     *          canvas.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //  Inform the shape manager to enter into the drawing state for rendering
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        StateManager sm = singleton.getStateManager();
        sm.selectShapeToDraw( PoseurShapeType.ELLIPSE );
    }

}
