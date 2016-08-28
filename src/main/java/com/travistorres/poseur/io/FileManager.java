/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.io;

import java.io.File;
import javax.swing.JOptionPane;
import com.travistorres.poseur.TheAnimatedPoseur;
import static com.travistorres.poseur.TheAnimatedPoseurSettings.*;
import com.travistorres.poseur.gui.UserInterface;
import com.travistorres.poseur.sprites.SpriteType;
import com.travistorres.poseur.states.ApplicationState;
import com.travistorres.poseur.states.StateManager;

/**
 * The <code>FileManager</code> is responsible for storing all the data for the
 * Sprite Type object that is being constructed.  From this class, new Sprite
 * Types can be created or older ones can be loaded and edited.  Information
 * such as saving of the files are all done from this class.
 *
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012       Initial Release
 */
public class FileManager {

    private File            file;
    private String          spriteTypeName;
    private String          filename;
    private boolean         saved;
    private SpriteTypeIO    spriteIO;

    /**
     * Will create an instance of the class <code>SpriteTypeIO</code> which
     * will be used later on for saving information about the Sprite Type to
     * HDD.  Information regarding the name of the file and sprite type will
     * remain undefined for the moment.
     */
    public FileManager() {
       file             = null;
       filename         = null;
       spriteTypeName   = null;
       saved            = true;
       spriteIO         = new SpriteTypeIO();
    }

    /**
     * Will make a request to the system in an attempt to make a new Sprite
     * Type file.  If there is currently a project being worked on, than the
     * application will ask the user to save, the request will continue only if
     * the user chooses "yes" or "no" but will fail if "cancel" is chosen from
     * the dialog box.  After words the user will be asked to enter a name for
     * the Sprite Type followed by a name for the very first Animation State.
     */
    public void requestNewSpriteType() {
        boolean continueNewSprite = true;

        //  If there is an unsaved project open, ask to save
        if( !saved ) {
            continueNewSprite = promptToSave();
        }

        //  Have the user fill out a dialog box to construct a Sprite Type
        if( continueNewSprite ) {
            //  Get the state manager
            TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
            StateManager sm = singleton.getStateManager();

            //  Reset the application state
            sm.resetState();

            //  Attempt to save file and create animation state
            if( promptForNew() ) {
                sm.setState( ApplicationState.SELECT_SHAPE_STATE );
            }

        }
    }

    /**
     * Will generate a dialog box which will ask the user if they desire to
     * save the current Sprite Type project.  If the user chooses not to save
     * than all data may potentially be lost forever.
     *
     * @return  <code>true</code> if the user chose to save the file.
     * @return  <code>false</code> if the user does not wish to save the file.
     */
    private boolean promptToSave() {
        boolean willSave = false;

        //  Obtain the user interface from the singleton object
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        UserInterface gui = singleton.getGUI();

        //  Ask if ther user would like to save their work
        int userChoice = JOptionPane.showOptionDialog( gui,
                MESSAGE_TO_SAVE_SPRITE_TYPE, MESSAGE_TO_SAVE_PROMPT,
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, null, null );

        //  Save the file if the user chose to save.
        if( userChoice == JOptionPane.YES_OPTION ) {
            spriteIO.save( file );
            saved = true;
            willSave = true;
        }

        return willSave;
    }

    /**
     * Will generate a dialog box which will ask the user if they would like
     * to continue creating a new project.  The user will be presented with a
     * dialog box that ask the user to name the Sprite Type object and than a
     * second dialog box which will ask for the name of the first Animation
     * State.
     *
     * @return  <code>true</code> if the user was successful in creating a new
     *          project.
     * @return  <code>false</code> if the user has failed to construct a new
     *          project, either by canceling the operation or not providing
     *          a name in one of the dialog boxes.
     */
    private boolean promptForNew() {
        boolean willCreateNew = false;

        //  Obtain the user interface from the singleton
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        UserInterface gui = singleton.getGUI();

        //  Ask the user to name the Sprite Type
        String spriteName = JOptionPane.showInputDialog(
                gui, MESSAGE_TO_NAME_SPRITE_TYPE, MESSAGE_TO_NAME_PROMPT,
                JOptionPane.QUESTION_MESSAGE );

        /*
         * If the Sprite Type name is valid then create an animation state and
         * save information to disk.
         */
        if( spriteName != null && spriteName.length() > 0 &&
                promptForAnimation() ) {
            //  Update file manager data
            spriteTypeName = spriteName;
            filename = spriteName + APPLICATION_SAVE_FILE_EXTENSION;
            file = new File( filename );

            //  save the data
            spriteIO.save( file );
            saved = true;

            //  update the gui's title bar
            String appName = gui.getAppName();
            gui.setTitle( appName + APP_NAME_SEPERATOR + file );
            willCreateNew = true;
        }

        return willCreateNew;
    }

    /**
     * Generates a dialog box that will ask the user to provide a name for a
     * new animation state.  If the user decides to name the animation state
     * than the state manager will update the current <code>SpriteType</code>
     * in memory otherwise no data will be recorded by the application.
     *
     * @return  <code>true</code> if the animation was successfully created.
     * @return  <code>false</code> if the user did not provide a valid name
     *          for the animation.
     */
    private boolean promptForAnimation() {
        //  Get the state manager and request a new animation
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        UserInterface gui = singleton.getGUI();
        StateManager sm = singleton.getStateManager();

        return sm.addAnimationState();
    }

    /**
     * Determines if the current project has been saved.
     *
     * @return  <code>true</code> if the program is currently saved.
     * @return  <code>false</code> if the program is currently not saved.
     */
    public boolean isFileSaved() {
        return saved;
    }

}
