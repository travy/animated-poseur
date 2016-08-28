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
 * The class <code>CreateRectangleHandler</code> will call the update the state
 * manager to a rendering state and have the rendering manager construct a
 * rectangle shape on the rendering canvas.
 *
 * @author      Travis Anthony Torres
 * @version     2.0     November 2012   Initial Release
 */
public class CreateRectangleHandler implements ActionListener {

    /**
     * Will render a rectangle onto the canvas.
     *
     * @param   e
     *          The event that requested a rectangle be rendered.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //  Inform the shape manager to enter into the drawing state for rendering
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        StateManager sm = singleton.getStateManager();
        sm.selectShapeToDraw( PoseurShapeType.RECTANGLE );
    }

}
