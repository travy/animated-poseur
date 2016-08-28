/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.events.canvas;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import com.travistorres.poseur.TheAnimatedPoseur;
import com.travistorres.poseur.states.StateManager;

/**
 * The <code>MouseInteractionHandler</code> class is responsible for responding
 * to the users mouse interactions on the rendering canvas.  This will enable
 * the application to select and move shapes around as well as construct new
 * shapes based on the applications current mode of operation.
 *
 * @author      Travis Anthony Torres
 * @version     1.0     December 2012       Initial Release
 */
public class MouseInteractionHandler
        implements MouseListener, MouseMotionListener {

    /**
     * Will be triggered whenever the user drags the mouse around the rendering
     * canvas.  Since there are multiple uses for dragging the mouse within
     * the application:  sizing a shape or moving shape around canvas; the
     * task to be performed after dragging will be determined by the state
     * manager.
     *
     * @param   e
     *          The mouse dragged event.
     */
    @Override
    public void mouseDragged( MouseEvent e ) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        StateManager sm = singleton.getStateManager();
        sm.processMouseDragged( e.getX(), e.getY() );
    }

    /**
     * Will be triggered whenever the user presses the left mouse button while
     * the cursor is within the rendering canvas.  There are two notable
     * actions that can transpire when this event is triggered:  a shape is
     * selected or a position has been selected to draw a shape at.  The action
     * that is to occur will be determined by the state manager.
     *
     * @param   e
     *          The event which contains information about the mouse button
     *          that was pressed.
     *
     */
    @Override
    public void mousePressed( MouseEvent e ) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        StateManager sm = singleton.getStateManager();
        sm.processMousePressed( e.getX(), e.getY() );
    }

    /**
     * Will be triggered whenever the user releases the left mouse button after
     * it has been pressed within the rendering canvas.  The action to be
     * performed is determined by the state manager.
     *
     * @param   e
     *          The event which contains information about the mouse button
     *          that was released.
     */
    @Override
    public void mouseReleased( MouseEvent e ) {
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        StateManager sm = singleton.getStateManager();
        sm.processMouseReleased( e.getX(), e.getY() );
    }

    /*
     * INTERACTIONS THAT ARE NOT DEFINED WITHIN THE APPLICATION
     */

    @Override
    public void mouseMoved( MouseEvent e ) { /* INTENTIONALLY LEFT BLANK */ }

    @Override
    public void mouseClicked( MouseEvent e ) { /* INTENTIONALLY LEFT BLANK */ }

    @Override
    public void mouseEntered( MouseEvent e ) { /* INTENTIONALLY LEFT BLANK */ }

    @Override
    public void mouseExited( MouseEvent e ) { /* INTENTIONALLY LEFT BLANK */ }

}
