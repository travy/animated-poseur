/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.shapes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.TheAnimatedPoseur;
import poseur.shapes.PoseurShapeType;
import poseur.states.StateManager;

/**
 * The <code>CreateLineHandler</code> class will place the state manager into
 * a shape drawing state and than tell the rendering manager to render a
 * line segment onto the rendering canvas.
 * 
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public class CreateLineHandler implements ActionListener {

    /**
     * Renders a line segment onto the rendering canvas.
     * 
     * @param   e
     *          The event that requested a line segment be rendered onto the
     *          canvas.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //  Inform the shape manager to enter into the drawing state for rendering
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        StateManager sm = singleton.getStateManager();
        sm.selectShapeToDraw( PoseurShapeType.LINE );
    }
    
}
