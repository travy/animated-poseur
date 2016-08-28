/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur;

import java.awt.Color;
import java.awt.Font;

/**
 * This class will contain several global constants to be used elsewhere within
 * the application.  The importance of this class is that it will prevent the
 * use of literals within the application which will make future system
 * maintenence much simpler.
 *
 * @author Travis Anthony Torres
 * @version 1.0 Initial Release -- November 2012
 * Copyright 2012 Torres Visual Enterprise.
 */
public class TheAnimatedPoseurSettings {
    /** WINDOW PROPERTIES SETTINGS
     * This information should be loaded in by an XML file.  It is here for
     * simplicity while project is started up but should be removed later.
     */
    public static final String  WINDOW_TITLE        = "The Animated Poseur";
    public static final int     WINDOW_WIDTH        = -1;
    public static final int     WINDOW_HEIGHT       = -1;
    public static final boolean WINDOW_RESIZEABLE   = false;

    /** DATA PATH */
    public static final String APPLICATION_DATA_PATH = "./data/";
    public static final String BUTTONS_IMAGE_PATH = APPLICATION_DATA_PATH + "buttons/";

    /** BUTTONS IMAGE FILES */
    //  File Toolbar Images
    public static final String NEW_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "new_button.png";
    public static final String OPEN_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "open_button.png";
    public static final String SAVE_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "save_button.png";
    public static final String SAVEAS_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "saveas_button.png";
    public static final String EXPORT_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "export_button.png";
    public static final String CLOSE_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "close_button.png";

    //  Shape Selection toolbar Images
    public static final String UNDO_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "undo_button.png";
    public static final String REDO_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "redo_button.png";
    public static final String SELECTION_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "selection_button.png";
    public static final String CUT_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "cut_button.png";
    public static final String COPY_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "copy_button.png";
    public static final String PASTE_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "paste_button.png";
    public static final String MOVE_SHAPE_BACK_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "move_shape_back_button.png";
    public static final String MOVE_SHAPE_FRONT_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "move_shape_front_button.png";

    //  Shape Creator Toolbar Images
    public static final String LINE_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "line_button.png";
    public static final String RECT_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "rect_button.png";
    public static final String ELLIPSE_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "ellipse_button.png";
    public static final String FREEHAND_SHAPE_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "freehand_button.png";

    //  Zoom Toolbar Images
    public static final String ZOOM_IN_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "zoom_in_button.png";
    public static final String ZOOM_OUT_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "zoom_out_button.png";
    public static final String RESIZE_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "resize_button.png";

    //  Color Toolbar Images
    public static final String OUTLINE_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "outline_color_button.png";
    public static final String BACKGROUND_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "background_color_button.png";
    public static final String INVERT_COLORS_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "invert_color_button.png";
    public static final String COLOR_CHOOSER_BUTTON_IMAGE = BUTTONS_IMAGE_PATH + "color_chooser_button.png";

    //  Animation states Toolbar Images
    public static final String ADD_ANIMATION_STATE_IMAGE = BUTTONS_IMAGE_PATH + "add_animation_button.png";
    public static final String REMOVE_ANIMATION_STATE_IMAGE = BUTTONS_IMAGE_PATH + "remove_animation_button.png";
    public static final String RENDER_ANIMATION_STATE_IMAGE = BUTTONS_IMAGE_PATH + "render_animation_button.png";

    //  Animation Frames Toolbar Images
    public static final String DUPLICATE_FRAME_IMAGE = BUTTONS_IMAGE_PATH + "duplicate_frame_button.png";
    public static final String ADD_FRAME_IMAGE = BUTTONS_IMAGE_PATH + "add_frame_button.png";
    public static final String REMOVE_FRAME_IMAGE = BUTTONS_IMAGE_PATH + "remove_frame_button.png";
    public static final String MOVE_LEFT_IMAGE = BUTTONS_IMAGE_PATH + "move_left_button.png";
    public static final String MOVE_RIGHT_IMAGE = BUTTONS_IMAGE_PATH + "move_right_button.png";

    public static final int NUMBER_OF_STROKES = 9;
    public static final String STROKE_IMAGE_PREFIX = BUTTONS_IMAGE_PATH + "Stroke";
    public static final String STROKE_IMAGE_POSTFIX = ".png";

    /** ALPHA COLOR ICON PROPERITES  */
    //  Acquire character 0x03B1 (alpha symbol) from the UNICODE character set
    public static final String  ALPHA_LABEL_TEXT = "  " + (char)(0x03B1) + ": ";
    public static final Font    ALPHA_LABEL_FONT = new Font( "Serif", Font.BOLD, 24 );
    public static final Color   ALPHA_BACKGROUND_COLOR = new Color( 255, 255, 160 );

    /** BUTTON BORDER INSETS */
    public static final int BUTTON_INSET = 2;

    /**  TOOLTIPS FOR BUTTONS  */

    //  File Toolbar Tooltips
    public static final String NEW_BUTTON_TOOLTIP = "Create New Sprite Type";
    public static final String OPEN_BUTTON_TOOLTIP = "Open Sprite Type Project";
    public static final String SAVE_BUTTON_TOOLTIP = "Save Sprite Type";
    public static final String SAVEAS_BUTTON_TOOLTIP = "Save Sprite Type As...";
    public static final String EXPORT_BUTTON_TOOLTIP = "Export Sprite Type";
    public static final String CLOSE_BUTTON_TOOLTIP = "Close Sprite Type Project";

    //  Selection Toolbar Tooltips
    public static final String UNDO_BUTTON_TOOLTIP = "Undo";
    public static final String REDO_BUTTON_TOOLTIP = "Redo";
    public static final String SELECTION_BUTTON_TOOLTIP = "Select Shape";
    public static final String CUT_BUTTON_TOOLTIP = "Cut Shape Out";
    public static final String COPY_BUTTON_TOOLTIP = "Copy Shape";
    public static final String PASTE_BUTTON_TOOLTIP = "Paste Shape";
    public static final String MOVE_SHAPE_BACK_BUTTON_TOOLTIP = "Move Shape to Back";
    public static final String MOVE_SHAPE_FRONT_BUTTON_TOOLTIP = "Move Shpae to Front";

    //  Shape Cration Toolbar Tooltips
    public static final String LINE_BUTTON_TOOLTIP = "Draw a Line Segment";
    public static final String RECT_BUTTON_TOOLTIP = "Draw a Rectanglular Shape";
    public static final String ELLIPSE_BUTTON_TOOLTIP = "Draw an Ellipse Shape";
    public static final String FREEHAND_SHAPE_BUTTON_TOOLTIP = "Draw a Shape Free Handed";

    //  Zoom Toolbar Tooltips
    public static final String ZOOM_IN_BUTTON_TOOLTIP = "Zoom In";
    public static final String ZOOM_OUT_BUTTON_TOOLTIP = "Zoom Out";
    public static final String RESIZE_SPRITE_BUTTON_TOOLTIP = "Resize Sprite Type";

    //  Color Toolbar Tooltips
    public static final String OUTLINE_BUTTON_TOOLTIP = "Set Outline Color";
    public static final String BACKGROUND_BUTTON_TOOLTIP = "Set Background Color";
    public static final String INVERT_COLORS_BUTTON_TOOLTIP = "Swaps Outline and Background Colors";
    public static final String COLOR_CHOOSER_BUTTON_TOOLTIP = "Select a Color";

    /** INFORMATION PRETAINING TO THE ALPHA COLOR SLIDER */

    //  Slider range properties
    public static final int MINIMUM_SLIDER_RANGE = 0;
    public static final int MAXIMUM_SLIDER_RANGE = 255;

    //  Slider spacing properties
    public static final int MAJOR_TICK_SPACING = 255;
    public static final int MINOR_TICK_SPACING = 8;

    //  Sliders track properties
    public static final boolean SET_ALPHA_SLIDER_LABELS = true;
    public static final boolean SET_ALPHA_SLIDER_TICKS = true;
    public static final boolean SET_ALPHA_SLIDER_TRACK = true;
    public static final boolean SNAP_ALPHA_SLIDER_TICKS = true;

    //  Sliders tooltip
    public static final String ALPHA_SLIDER_TOOLTIP = "Change Transparancy Value";

    //  Animation State Tooltips
    public static final String ANIMATION_STATES_TOOLTIP = "Choose an Animation State";
    public static final String ADD_ANIMATION_STATE_TOOTIP = "Create an Animation State";
    public static final String REMOVE_ANIMATION_STATE_TOOLTIP = "Deletes an Animation State";
    public static final String RENDER_ANIMATION_STATE_TOOLTIP = "Renders the selected Animation State";

    //  Animation Frames toolbar
    public static final String DUPLICATE_FRAME_TOOLTIP = "Duplicate Selected Frame";
    public static final String ADD_FRAME_TOOLTIP = "Adds a New Frame";
    public static final String REMOVE_FRAME_TOOLTIP = "Deletes Frame from Sequence";
    public static final String MOVE_FRAME_LEFT_TOOLTIP = "Moves Frame Up Sequence";
    public static final String MOVE_FRAME_RIGHT_TOOLTIP = "Moves Frame Down Sequence";

    public static final String ANIMATION_STATES_COMBOBOX_MESSAGE = "--Select an Animation State--";

    public static final String SELECT_CUSTOM_COLOR_TEXT = "Choose a color to be used...";

    public static final String MESSAGE_TO_SAVE_SPRITE_TYPE = "Would you like to save the your current project?";
    public static final String MESSAGE_TO_SAVE_PROMPT = "Save project?";
    public static final String MESSAGE_TO_NAME_SPRITE_TYPE = "What is the name for the new Sprite Type?";
    public static final String MESSAGE_TO_NAME_PROMPT = "Sprite Type Name";

    public static final String MESSAGE_TO_NAME_ANIMATION_STATE = "What would you like to name your first Animation State?";
    public static final String MESSAGE_TO_NAME_ANIMATION_PROMPT = "Animation State Name";

    public static final String APPLICATION_SAVE_FILE_EXTENSION = ".stf"; //  Sprite Type File
    public static final String APP_NAME_SEPERATOR = "  --  ";

    public static final int DEFAULT_SPRITE_WIDTH = 150;
    public static final int DEFAULT_SPRITE_HEIGHT = 150;

    public static final int SEQUENCE_FRAMES_WIDTH = 100;
    public static final int SEQUENCE_FRAMES_HEIGHT = 100;

    public static final Color POSE_CANVAS_COLOR = new Color( 250, 250, 250 );

    public static final String SPRITE_DIMENSIONS_TITLE_MESSAGE = "Change Dimensions for Sprite Type";
    public static final String SPRITE_DIMENSIONS_DESCRIPTIVE_MESSAGE = "Enter new dimensions for Sprite:";
    public static final String SPRITE_DIMENSIONS_WIDTH_FIELD = "Width:";
    public static final String SPRITE_DIMENSIONS_HEIGHT_FIELD = "Height:";

    public static final int MINIMUM_SPRITE_DIMENSION = 1;
    public static final int MAXIMUM_SPRITE_DIMENSION = 2000;

    public static final Color SELECTED_SHAPE_COLOR = new Color( 255, 0, 0 );
}
