/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.io;

import java.awt.Container;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import static com.travistorres.poseur.TheAnimatedPoseurSettings.*;

/**
 * This class is designed to batch load files (images at present) into the
 * application, in a way that the application does not appear to have frozen
 * when being downloaded over the Internet.
 *
 * @author Travis Anthony Torres
 * @version 21 November 2012.
 */
public class BatchLoader {

    /**
     * GUI setup method can be quite lengthy and repetitive so
     * it helps to create helper methods that can do a bunch of
     * things at once. This method creates a button with a bunch
     * of pre-made values. Note that we are using Java reflection
     * here, to make an object based on what class type it has.
     *
     * Written by Richard McKenna
     *
     * @param imageFile The image to use for the button.
     *
     * @param parent The container inside which to put the button.
     *
     * @param tracker This makes sure our button fully loads.
     *
     * @param id A unique id for the button so the tracker knows it's there.
     *
     * @param buttonType The type of button, we'll use reflection for making it.
     *
     * @param bg Some buttons will go into groups where only one may be selected
     * at a time.
     *
     * @param tooltip The mouse-over text for the button.
     *
     * @return A fully constructed and initialized button with all the data
     * provided to it as arguments.
     */

    public static AbstractButton initButton(  String imageFile,
                                        Container parent,
                                        MediaTracker tracker,
                                        int id,
                                        Class buttonType,
                                        ButtonGroup bg,
                                        String tooltip)
    {
        try
        {
            // LOAD THE IMAGE AND MAKE AN ICON
            Image img = batchLoadImage(imageFile, tracker, id);
            ImageIcon ii = new ImageIcon(img);

            // HERE'S REFLECTION MAKING OUR OBJECT USING IT'S CLASS
            // NOTE THAT DOING IT LIKE THIS CALLS THE buttonType
            // CLASS' DEFAULT CONSTRUCTOR, SO WE MUST MAKE SURE IT HAS ONE
            AbstractButton createdButton;
            createdButton = (AbstractButton)buttonType.newInstance();

            // NOW SETUP OUR BUTTON FOR USE
            createdButton.setIcon(ii);
            createdButton.setToolTipText(tooltip);
            parent.add(createdButton);

            // INSETS ARE SPACING INSIDE THE BUTTON,
            // TOP LEFT RIGHT BOTTOM
            Insets buttonMargin = new Insets(
                    BUTTON_INSET, BUTTON_INSET, BUTTON_INSET, BUTTON_INSET);
            createdButton.setMargin(buttonMargin);

            // ADD IT TO ITS BUTTON GROUP IF IT'S IN ONE
            if (bg != null)
            {
                bg.add(createdButton);
            }

            // AND RETURN THE SETUP BUTTON
            return createdButton;
        }
        catch (InstantiationException | IllegalAccessException ex)
        {
            // WE SHOULD NEVER GET THIS ERROR, BUT WE HAVE TO PUT
            // A TRY CATCH BECAUSE WE'RE USING REFLECTION TO DYNAMICALLY
            // CONSTRUCT OUR BUTTONS BY CLASS NAME
            System.err.println( ex.getMessage() );
        }
        // THIS WOULD MEAN A FAILURE OF SOME SORT OCCURED
        return null;
    }

    /**
     * This method helps us load a bunch of images and ensure they are
     * fully loaded when we want to use them.
     *
     * Written by Richard McKenna
     *
     * @param imageFile The path and name of the image file to load.
     *
     * @param tracker This will help ensure all the images are loaded.
     *
     * @param id A unique identifier for each image in the tracker. It
     * will only wait for ids it knows about.
     *
     * @return A constructed image that has been registered with the tracker.
     * Note that the image's data has not necessarily been fully loaded when
     * this method ends.
     */
    public static Image batchLoadImage(String imageFile, MediaTracker tracker, int id)
    {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage(imageFile);
        tracker.addImage(img, id);
        return img;
    }
}
