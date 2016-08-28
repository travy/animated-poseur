/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.states;

/**
 * The class <code>ApplicatioState</code> will contain a list of all of the
 * possible states that The Animated Poseur application can be in.  Depending
 * on the currently active state, the application will only be able to perform
 * certain actions.  For instance, if the application is currently within the
 * <code>STARTUP_STATE</code> than the user will only be allowed to create or
 * open a file and also exit the application entirely.  And so it is made clear
 * that each state is to only be used at a time when it is critical that
 * certain operations be active while others are disabled.
 * 
 * What follows is a description of each state:
 * 
 * The <code>STARTUP_STATE</code> is exactly as described before.  This is the
 * state that the application will first be in when loaded into memory.  Since
 * the user has not created a Sprite Type or rendered any shapes yet, it is
 * apparent that the user will only be able to interact with the file management
 * portions of the application.
 * 
 * The <code>SELECT_SHAPE_STATE</code> is activated when the user has selected
 * the "select shape button" from the toolbar on the top of the screen.  When
 * this state is entered, the application will allow the user to click on a
 * shape so that the <code>SHAPE_SELECTED_STATE</code> will be activated
 * allowing the user to modify characteristics regarding the shape.
 * 
 * As described above, the <code>SHAPE_SELECTED_STATE</code> will allow the
 * users to modify characteristics of the currently selected shape.  The user
 * will also be able to cut/copy/paste items into/from the clipboard.  All
 * actions performed while in this mode should be recorded by the undo buffer.
 * 
 * The <code>CREATE_SHAPE_STATE</code> will allow the user to construct new
 * shapes onto the rendering canvas and within the structure of their Sprite
 * Type project.  This mode is activated whenever the user has pressed one of
 * the shape buttons on the top of the screen.
 * 
 * The <code>SHAPE_DRAG_STATE</code> will be entered whenever the user has
 * requested that the selected shape be moved.  This is done by selecting a
 * shape and dragging it across the canvas.
 * 
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012       Initial Release
 */
public enum ApplicationState {
    STARTUP_STATE,
    SELECT_SHAPE_STATE,
    SHAPE_SELECTED_STATE,
    CREATE_SHAPE_STATE,
    SHAPE_DRAG_STATE,
    CONSTRUCT_SHAPE_STATE
}
