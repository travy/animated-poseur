/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur;

import static poseur.TheAnimatedPoseurSettings.*;
import poseur.gui.UserInterface;
import poseur.io.FileManager;
import poseur.states.StateManager;

/**
 * TheAniatedPoseur is a singleton class which will act as a communication
 * device between all frameworks within the system.  Whenever an external
 * framework needs to communicate with the application directly 
 * (Exp. Get the Graphics Window for Drawing) this class can be used to get the
 * only reference to the application which will than extracting the desired
 * information.
 * 
 * @author Travis
 * @version 1.0 Initial Release -- November 2012
 * Copyright 2012 Torres Visual Enterprises.
 */
public class TheAnimatedPoseur {
    //  a singleton object that will contain the only object in memory
    private static TheAnimatedPoseur singleton = null;
    
    private UserInterface gui;
    private StateManager stateManager;
    private FileManager fileManager;
    
    /**
     * Will allocate memory for all of the instance variables within the object.
     * Because this is the only object that will ever be constructed, this
     * constructor is marked as private.  To create an instance of this class
     * the method <code>getAnimatedPoseur</code>, if an instance already exist
     * than this method will simply return the instance that exist within 
     * memory.
     */
    private TheAnimatedPoseur() {
        /**  Intentionally left blank */
    }
    
    /**
     * Obtains a reference to the only <code>TheAnimatedPoseur</code> instance
     * for use by other frameworks.  By using this reference external frameworks
     * can aquire information about the state of the program to perform some
     * necessary task.
     * 
     * @return The only instance in memory
     */
    public static TheAnimatedPoseur getAnimatedPoseur() {
        //  construct a new instance if one does not already exist
        if( singleton == null ) {
            singleton = new TheAnimatedPoseur();
        }
        
        return singleton;
    }
    
    /**
     * Will initialize all frameworks that will communicate through the
     * <code>TheAnimatedPoseur</code> class.  Once all frameworks have been
     * allocated, they can call this class to collect information in order to
     * accomplish their desired task.
     */
    public void init() {
        stateManager = new StateManager();
        fileManager = new FileManager();
        gui = new UserInterface();
        
        gui.setAppName( WINDOW_TITLE );
    }
    
    /**
     * Obtains the user interface for the application.  This can help other
     * frameworks understand what changes have been made to various shapes.
     * 
     * @return  A reference to the applications user interface.
     */
    public UserInterface getGUI() {
        return gui;
    }
    
    /**
     * Obtains the state manager for the application.  This will provide
     * helpful information on the execution of the program.
     * 
     * @return  A reference to the applications <code>StateManager</code>.
     */
    public StateManager getStateManager() {
        return stateManager;
    }
    
    /**
     * Obtains the file manager that contains information about the current
     * sprite type object under construction.
     * 
     * @return  A reference to the applications <code>FileManager</code>.
     */
    public FileManager getFileManager() {
        return fileManager;
    }
    
    /**
     * Entry point for the application.  From this point the gui will be
     * constructed and than control will be passed to the ActionHandlers loop.
     * 
     * @param   args
     *          The application does not support command line arguments at this
     *          time.
     */
    public static void main( String[] args ) {
        //  Obtains the singleton object
        TheAnimatedPoseur app = TheAnimatedPoseur.getAnimatedPoseur();
        app.init();
        
        //  Sets up the user interface so that the application can start
        UserInterface window = app.getGUI();
    }
}
