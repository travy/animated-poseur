/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import com.travistorres.poseur.TheAnimatedPoseur;
import static com.travistorres.poseur.TheAnimatedPoseurSettings.*;
import com.travistorres.poseur.events.canvas.MouseInteractionHandler;
import com.travistorres.poseur.events.colors.BackgroundColorHandler;
import com.travistorres.poseur.events.colors.ColorChooserHandler;
import com.travistorres.poseur.events.colors.InvertToggleButtonColorsHandler;
import com.travistorres.poseur.events.colors.OutlineColorHandler;
import com.travistorres.poseur.events.file.CloseFileHandler;
import com.travistorres.poseur.events.file.ExportSpriteTypeHandler;
import com.travistorres.poseur.events.file.NewFileHandler;
import com.travistorres.poseur.events.file.OpenFileHandler;
import com.travistorres.poseur.events.file.SaveFileAsHandler;
import com.travistorres.poseur.events.file.SaveFileHandler;
import com.travistorres.poseur.events.selection.CopyShapeHandler;
import com.travistorres.poseur.events.selection.CutShapeHandler;
import com.travistorres.poseur.events.selection.MoveShapeToBackCanvasHandler;
import com.travistorres.poseur.events.selection.MoveShapeToFrontCanvasHandler;
import com.travistorres.poseur.events.selection.PasteShapeHandler;
import com.travistorres.poseur.events.selection.RedoActionHandler;
import com.travistorres.poseur.events.selection.ShapeSelectionHandler;
import com.travistorres.poseur.events.selection.UndoActionHandler;
import com.travistorres.poseur.events.shapes.CreateEllipseHandler;
import com.travistorres.poseur.events.shapes.CreateFreeHandShapeHandler;
import com.travistorres.poseur.events.shapes.CreateLineHandler;
import com.travistorres.poseur.events.shapes.CreateRectangleHandler;
import com.travistorres.poseur.events.zooming.ResizeCanvasHandler;
import com.travistorres.poseur.events.zooming.ZoomInHandler;
import com.travistorres.poseur.events.zooming.ZoomOutHandler;
import com.travistorres.poseur.io.BatchLoader;
import com.travistorres.poseur.io.FileManager;
import com.travistorres.poseur.sprites.AnimationState;
import com.travistorres.poseur.sprites.AnimationStateFrame;
import com.travistorres.poseur.states.ApplicationState;
import com.travistorres.poseur.states.PoseCanvasState;
import com.travistorres.poseur.states.StateManager;

/**
 * This class is responsible for handling all of the graphical components for
 * The Animated Poseur application.  Using this class, information regarding
 * shapes customization can be extracting for use within the render framework.
 *
 * @author Travis Anthony Torres
 * @version 1.0 Initial Release -- November 2012.
 * Copyright 2012 Torres Visual Enterprise.
 */
public class UserInterface extends JFrame {
    //  If the user defined dimensions for the window are too large or small
    //  to fit within the window, than the window will be fixed to some scale
    //  of the displays resolution.
    private static final double  WINDOW_SCALE_PORPORTION = 0.90;
    private static final int     MINIMUM_WINDOW_WIDTH = 640;
    private static final int     MINIMUM_WINDOW_HEIGHT = 480;

    /*
     * GRAPHICAL CONTROLLERS
     */

    private String appName;

    //  Toolbar container on top of the window
    private JPanel  renderingToolbarContainer;
    private JPanel  northernRenderingToolbarContainer;
    private JPanel  southernRenderingToolbarContainer;

    //  Controllers used for the file toolbar
    private JToolBar    fileToolbar;
    private JButton     newFileButton;
    private JButton     openFileButton;
    private JButton     saveFileButton;
    private JButton     saveAsFileButton;
    private JButton     exportSpriteButton;
    private JButton     closeFileButton;

    //  Controllers used for the shape selection toolbar
    private JToolBar    selectionToolbar;
    private JButton     undoButton;
    private JButton     redoButton;
    private JButton     selectShapeButton;
    private JButton     cutShapeButton;
    private JButton     copyShapeButton;
    private JButton     pasteShapeButton;
    private JButton     moveShapeToBackButton;
    private JButton     moveShapeToFrontButton;

    //  Controllers used for the shape editor toolbar
    private JToolBar    shapeEditorToolbar;
    private JButton     createLineButton;
    private JButton     createRectButton;
    private JButton     createEllipseButton;
    private JButton     createFreeHandShapeButton;
    private JComboBox   lineThicknessCombobox;

    //  Controllers used for the zoom toolbar
    private JToolBar    zoomToolbar;
    private JButton     zoomInButton;
    private JButton     zoomOutButton;
    private JButton     resizeSpriteButton;

    //  Controllers used for the color toolbar
    private JToolBar            colorToolbar;
    private ColorToggleButton   outlineColorButton;
    private ColorToggleButton   backgroundColorButton;
    private JButton             invertColorsButton;
    private ColorPallet         colorPallet;
    private JButton             colorChooserButton;
    private JLabel              alphaColorIcon;
    private JSlider             alphaColorSlider;

    //  Controllers used for the rendering area
    private PoseCanvas  renderingCanvas;

    private AnimationSequenceTools animationPane;

    /**
     * Will allocate memory for the user interface and all of the controllers
     * that exist within it.  This will also check that the settings that the
     * user provided for the window size are not invalid, that is the window
     * is not too large to fit in the display and not too small to see.
     */
    public UserInterface() {
        super();

        //  obtain the size of the users display
        Toolkit display = Toolkit.getDefaultToolkit();
        Dimension displaySize = display.getScreenSize();
        Dimension windowSize = acquireValidWindowSize( displaySize );

        //  calculate the center of the display window
        int windowXPos = (int)( displaySize.getWidth() - windowSize.getWidth() ) / 2;
        int windowYPos = (int)( displaySize.getHeight() - windowSize.getHeight() ) / 2;

        //  Construct the user interface
        constructInterface();

        //  Temporary statement, remove before release
        backgroundColorButton.deselect();
        outlineColorButton.setBackground( Color.BLACK );
        backgroundColorButton.setBackground( Color.GREEN );

        updateMode();

        //  Sets the properties for the window
        setSize( windowSize );
        setTitle( WINDOW_TITLE );
        setResizable( WINDOW_RESIZEABLE );
        setLocation( windowXPos, windowYPos );  //  centers the screen
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setVisible( true );
    }

    /**
     * Constructs the user interfaces controllers and places them into their
     * correct locations within the window.  Also links all handlers to their
     * corresponding controller.
     */
    private void constructInterface() {
        //  Allocates memory for all controllers on the screen
        initializeControllers();

        //  Provides ActionListeners for all controllers
        initializeHandlers();

        //  Place the controllers in their correct position
        organizeControllers();
    }

    /**
     * Allocates memory for all controllers and defines the organization scheme
     * for all containers that will store controllers.
     */
    private void initializeControllers() {
        //  Allocate memory for jpanels that will store toolbars on top of
        //  window
        renderingToolbarContainer = new JPanel( new BorderLayout() );
        northernRenderingToolbarContainer = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
        southernRenderingToolbarContainer = new JPanel( new FlowLayout( FlowLayout.LEFT ) );

        //  Keeps track of any images that may be taking a while to load so that
        //  the window knows to report it to the user.
        MediaTracker tracker = new MediaTracker(this);
        int idTracker = 0;

        //  Allocates memory for the file toolbar and loads in all icons for
        //  each button
        fileToolbar         = new JToolBar();
        newFileButton       = (JButton)BatchLoader.initButton( NEW_BUTTON_IMAGE, fileToolbar, tracker, idTracker++, JButton.class, null, NEW_BUTTON_TOOLTIP );
        openFileButton      = (JButton)BatchLoader.initButton( OPEN_BUTTON_IMAGE, fileToolbar, tracker, idTracker++, JButton.class, null, OPEN_BUTTON_TOOLTIP );
        saveFileButton      = (JButton)BatchLoader.initButton( SAVE_BUTTON_IMAGE, fileToolbar, tracker, idTracker++, JButton.class, null, SAVE_BUTTON_TOOLTIP );
        saveAsFileButton    = (JButton)BatchLoader.initButton( SAVEAS_BUTTON_IMAGE, fileToolbar, tracker, idTracker++, JButton.class, null, SAVEAS_BUTTON_TOOLTIP );
        exportSpriteButton  = (JButton)BatchLoader.initButton( EXPORT_BUTTON_IMAGE, fileToolbar, tracker, idTracker++, JButton.class, null, EXPORT_BUTTON_TOOLTIP );
        closeFileButton     = (JButton)BatchLoader.initButton( CLOSE_BUTTON_IMAGE, fileToolbar, tracker, idTracker++, JButton.class, null, CLOSE_BUTTON_TOOLTIP );

        //  Allocates memory for the shape selection toolbar and loads all icons
        selectionToolbar        = new JToolBar();
        undoButton              = (JButton)BatchLoader.initButton( UNDO_BUTTON_IMAGE, selectionToolbar, tracker, idTracker++, JButton.class, null, UNDO_BUTTON_TOOLTIP );
        redoButton              = (JButton)BatchLoader.initButton( REDO_BUTTON_IMAGE, selectionToolbar, tracker, idTracker++, JButton.class, null, REDO_BUTTON_TOOLTIP );
        selectShapeButton       = (JButton)BatchLoader.initButton( SELECTION_BUTTON_IMAGE, selectionToolbar, tracker, idTracker++, JButton.class, null, NEW_BUTTON_TOOLTIP );
        cutShapeButton          = (JButton)BatchLoader.initButton( CUT_BUTTON_IMAGE, selectionToolbar, tracker, idTracker++, JButton.class, null, CUT_BUTTON_TOOLTIP );
        copyShapeButton         = (JButton)BatchLoader.initButton( COPY_BUTTON_IMAGE, selectionToolbar, tracker, idTracker++, JButton.class, null, COPY_BUTTON_TOOLTIP );
        pasteShapeButton        = (JButton)BatchLoader.initButton( PASTE_BUTTON_IMAGE, selectionToolbar, tracker, idTracker++, JButton.class, null, PASTE_BUTTON_TOOLTIP );
        moveShapeToBackButton   = (JButton)BatchLoader.initButton( MOVE_SHAPE_BACK_BUTTON_IMAGE, selectionToolbar, tracker, idTracker++, JButton.class, null, MOVE_SHAPE_BACK_BUTTON_TOOLTIP );
        moveShapeToFrontButton  = (JButton)BatchLoader.initButton( MOVE_SHAPE_FRONT_BUTTON_IMAGE, selectionToolbar, tracker, idTracker++, JButton.class, null, MOVE_SHAPE_FRONT_BUTTON_TOOLTIP );

        //  Allocate memory for the shape toolbar and load all icons
        shapeEditorToolbar          = new JToolBar();
        createLineButton            = (JButton)BatchLoader.initButton( LINE_BUTTON_IMAGE, shapeEditorToolbar, tracker, idTracker++, JButton.class, null, LINE_BUTTON_TOOLTIP );
        createRectButton            = (JButton)BatchLoader.initButton( RECT_BUTTON_IMAGE, shapeEditorToolbar, tracker, idTracker++, JButton.class, null, RECT_BUTTON_TOOLTIP );
        createEllipseButton         = (JButton)BatchLoader.initButton( ELLIPSE_BUTTON_IMAGE, shapeEditorToolbar, tracker, idTracker++, JButton.class, null, ELLIPSE_BUTTON_TOOLTIP );
        createFreeHandShapeButton   = (JButton)BatchLoader.initButton( FREEHAND_SHAPE_BUTTON_IMAGE, shapeEditorToolbar, tracker, idTracker++, JButton.class, null, FREEHAND_SHAPE_BUTTON_TOOLTIP );

        //  SETUP THE LINE THICKNESS COMBOBOX
        DefaultComboBoxModel lineThicknessModel = new DefaultComboBoxModel();
        for( int i = 1; i <= NUMBER_OF_STROKES; ++i ) {
            String imageFilename = STROKE_IMAGE_PREFIX + i + STROKE_IMAGE_POSTFIX;

            Image img = BatchLoader.batchLoadImage( imageFilename, tracker, idTracker++ );
            ImageIcon ii = new ImageIcon( img );
            lineThicknessModel.addElement( ii );
        }
        lineThicknessCombobox = new JComboBox( lineThicknessModel );
        shapeEditorToolbar.add( lineThicknessCombobox );

        //  Allocates memory for the zoom toolbar
        zoomToolbar         = new JToolBar();
        zoomInButton        = (JButton)BatchLoader.initButton( ZOOM_IN_BUTTON_IMAGE, zoomToolbar, tracker, idTracker++, JButton.class, null, ZOOM_IN_BUTTON_TOOLTIP );
        zoomOutButton       = (JButton)BatchLoader.initButton( ZOOM_OUT_BUTTON_IMAGE, zoomToolbar, tracker, idTracker++, JButton.class, null, ZOOM_OUT_BUTTON_TOOLTIP );
        resizeSpriteButton  = (JButton)BatchLoader.initButton( RESIZE_BUTTON_IMAGE, zoomToolbar, tracker, idTracker++, JButton.class, null, RESIZE_SPRITE_BUTTON_TOOLTIP );

        //  Setup the color toolbar
        colorToolbar            = new JToolBar();
        outlineColorButton      = (ColorToggleButton)BatchLoader.initButton( OUTLINE_BUTTON_IMAGE, colorToolbar, tracker, idTracker++, ColorToggleButton.class, null, OUTLINE_BUTTON_TOOLTIP );
        backgroundColorButton   = (ColorToggleButton)BatchLoader.initButton( BACKGROUND_BUTTON_IMAGE, colorToolbar, tracker, idTracker++, ColorToggleButton.class, null, BACKGROUND_BUTTON_TOOLTIP );
        invertColorsButton      = (JButton)BatchLoader.initButton( INVERT_COLORS_BUTTON_IMAGE, colorToolbar, tracker, idTracker++, JButton.class, null, INVERT_COLORS_BUTTON_TOOLTIP );

        //  Setup the color selection tools
        colorPallet             = new ColorPallet();
        colorChooserButton      = (JButton)BatchLoader.initButton( COLOR_CHOOSER_BUTTON_IMAGE, colorToolbar, tracker, idTracker++, JButton.class, null, COLOR_CHOOSER_BUTTON_TOOLTIP );
        //  Setup the alpha color icon
        alphaColorIcon          = new JLabel( ALPHA_LABEL_TEXT );
        alphaColorIcon.setFont( ALPHA_LABEL_FONT );
        alphaColorIcon.setBackground( ALPHA_BACKGROUND_COLOR );
        //  Setup the transparency adjustment slider
        alphaColorSlider        = new JSlider( MINIMUM_SLIDER_RANGE, MAXIMUM_SLIDER_RANGE );
        alphaColorSlider.setMajorTickSpacing( MAJOR_TICK_SPACING );
        alphaColorSlider.setMinorTickSpacing( MINOR_TICK_SPACING );
        alphaColorSlider.setPaintLabels( SET_ALPHA_SLIDER_LABELS );
        alphaColorSlider.setPaintTicks( SET_ALPHA_SLIDER_TICKS );
        alphaColorSlider.setPaintTrack( SET_ALPHA_SLIDER_TRACK );
        alphaColorSlider.setToolTipText( ALPHA_SLIDER_TOOLTIP );
        alphaColorSlider.setSnapToTicks( SNAP_ALPHA_SLIDER_TICKS );

        //  Get the state for the rendering canvas
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        StateManager sm = singleton.getStateManager();
        PoseCanvasState renderingCanvasState = sm.getCanvasState();

        //  Setus up the rendering canvas
        renderingCanvas = new PoseCanvas( renderingCanvasState );
        renderingCanvas.setBackground( Color.BLUE );
        renderingCanvasState.init( renderingCanvas );

        //  Setup the animation sequence editor
        animationPane = new AnimationSequenceTools();
    }



    /**
     * This method will bind all of the buttons on the window with their
     * respective <code>ActionHandler</code>'s.  After all controls have been
     * binded, users will be able to interact with the application in several
     * ways in order to create their desired Sprite-Type image.
     *
     */
    private void initializeHandlers() {
        //  Provides handlers to all buttons within file toolbar
        newFileButton.addActionListener( new NewFileHandler() );
        openFileButton.addActionListener( new OpenFileHandler() );
        saveFileButton.addActionListener( new SaveFileHandler() );
        saveAsFileButton.addActionListener( new SaveFileAsHandler() );
        exportSpriteButton.addActionListener( new ExportSpriteTypeHandler() );
        closeFileButton.addActionListener( new CloseFileHandler() );

        //  Sets up handlers for selection toolbar
        undoButton.addActionListener( new UndoActionHandler() );
        redoButton.addActionListener( new RedoActionHandler() );
        selectShapeButton.addActionListener( new ShapeSelectionHandler() );
        cutShapeButton.addActionListener( new CutShapeHandler() );
        copyShapeButton.addActionListener( new CopyShapeHandler() );
        pasteShapeButton.addActionListener( new PasteShapeHandler() );
        moveShapeToBackButton.addActionListener( new MoveShapeToBackCanvasHandler() );
        moveShapeToFrontButton.addActionListener( new MoveShapeToFrontCanvasHandler() );

        //  Sets up handlers for the shape rendering toolbar
        createLineButton.addActionListener( new CreateLineHandler() );
        createRectButton.addActionListener( new CreateRectangleHandler() );
        createEllipseButton.addActionListener( new CreateEllipseHandler() );
        createFreeHandShapeButton.addActionListener( new CreateFreeHandShapeHandler() );

        //  Sets up handlers for zoom toolbar components
        zoomInButton.addActionListener( new ZoomInHandler() );
        zoomOutButton.addActionListener( new ZoomOutHandler() );
        resizeSpriteButton.addActionListener( new ResizeCanvasHandler() );

        //  Sets up handlers for color toolbar
        outlineColorButton.addActionListener( new OutlineColorHandler() );
        backgroundColorButton.addActionListener( new BackgroundColorHandler() );
        invertColorsButton.addActionListener( new InvertToggleButtonColorsHandler() );
        colorChooserButton.addActionListener( new ColorChooserHandler() );

        //  Sets up handlers for the rendering canvas
        renderingCanvas.addMouseListener( new MouseInteractionHandler() );
        renderingCanvas.addMouseMotionListener( new MouseInteractionHandler() );
    }

    /**
     * Allows the user to define the name of the application.
     *
     * @param   applicationName
     *          The name of the application.
     */
    public void setAppName( String applicationName ) {
        appName = applicationName;
    }

    /**
     * Obtains the name of the application.
     *
     * @return The name of the application.
     */
    public String getAppName() {
        return appName;
    }

    /**
     * Will retrieve the value that should be used to construct the line
     * segments and the outline for misc. shapes.
     *
     * @return  A representation of all line thicknesses.
     */
    public BasicStroke getLineThickness() {
        int thicknessValue = lineThicknessCombobox.getSelectedIndex() + 1;
        return new BasicStroke( thicknessValue );
    }

    /**
     * Will retrieve the value that the user has selected to be the outline
     * color of a shape or line segment.
     *
     * @return  Chosen outline and line segment color.
     */
    public Color getOutlineColor() {
        return outlineColorButton.getBackground();
    }

    /**
     * Will retrieve the value that the user has selected to be the background
     * color for all shapes.
     *
     * @return  The chosen background color.
     */
    public Color getBackgroundColor() {
        return backgroundColorButton.getBackground();
    }

    /*
     * Will retrieve the alpha value that should be assigned to specific shapes.
     * This value will represent the transparancy of a given shape object.
     */
    public int getAlphaColor() {
        return alphaColorSlider.getValue();
    }

    /**
     * This method will place all of the gui controls into their correct
     * locations on the application window.
     *
     */
    private void organizeControllers() {
        JScrollPane sp = new JScrollPane( renderingCanvas );

        //  Laysout controls directly on the interface
        this.setLayout( new BorderLayout() );
        this.add( renderingToolbarContainer, BorderLayout.NORTH );
        this.add( sp, BorderLayout.CENTER );
        this.add( animationPane, BorderLayout.SOUTH );

        //  Adds the northern and southern toolbar containers to the top window
        renderingToolbarContainer.add( northernRenderingToolbarContainer, BorderLayout.NORTH );
        renderingToolbarContainer.add( southernRenderingToolbarContainer, BorderLayout.SOUTH );

        //  Setup the northern rendering toolbar container
        northernRenderingToolbarContainer.add( fileToolbar );
        northernRenderingToolbarContainer.add( selectionToolbar );
        northernRenderingToolbarContainer.add( shapeEditorToolbar );

        //  Position the color pallet onto the color toolbar
        colorToolbar.add( colorPallet );
        colorToolbar.add( colorChooserButton );
        colorToolbar.add( alphaColorIcon );
        colorToolbar.add( alphaColorSlider );

        //  Setup the southern rendering toolbar container
        southernRenderingToolbarContainer.add( zoomToolbar );
        southernRenderingToolbarContainer.add( colorToolbar );
    }

    /**
     * Used for adjusting the size of the window if the information the user
     * set within the XML file makes the window too large or too small.
     *
     * @param   displaySize
     *          Contains information regarding the size of the user display.
     * @return  The dimensions for the application window.
     */
    private Dimension acquireValidWindowSize( Dimension displaySize ) {
        int displayWidth = (int) displaySize.getWidth();
        int displayHeight = (int) displaySize.getHeight();

        //  determine if the width needs to be adjusted
        int windowWidth = WINDOW_WIDTH;
        if( WINDOW_WIDTH > displayWidth || WINDOW_WIDTH < MINIMUM_WINDOW_WIDTH ) {
            windowWidth = (int)( displayWidth * WINDOW_SCALE_PORPORTION );
        }
        //  determine if the height needs to be adjusted
        int windowHeight = WINDOW_HEIGHT;
        if( WINDOW_HEIGHT > displayHeight || WINDOW_HEIGHT < MINIMUM_WINDOW_HEIGHT ) {
            windowHeight = (int)( displayHeight * WINDOW_SCALE_PORPORTION );
        }

        return new Dimension( windowWidth, windowHeight );
    }

    /**
     * Toggles the outline toggle button so that it is in the on position and
     * the user will be able to set the outline color for shapes being created
     * and/or edited.  This will also disable the background color button so
     * that its color value is not changed.
     */
    public void activateOutlineToggleButton() {
        outlineColorButton.select();
        backgroundColorButton.deselect();
    }

    /**
     * Toggles the background toggle button so that it is in the on position
     * and the user will be able to set the background color for shapes being
     * created and/or edited.  This will also disable the outline color button
     * so that its color value is not changed.
     */
    public void activateBackgroundToggleButton() {
        backgroundColorButton.select();
        outlineColorButton.deselect();
    }

    /**
     * Will obtain a reference to the toggle button that is currently selected
     * by the user.
     *
     * @return  The selected color toggle button.
     */
    public ColorToggleButton getSelectedToggleButton() {
        // Returns the currently selected toggle button
        return ( backgroundColorButton.isSelected() ) ? backgroundColorButton :
                                                        outlineColorButton;
    }

    public AnimationSequenceTools getAnimationTools() {
        return animationPane;
    }

    /**
     * Sets the value that the alpha slider will be pointing to.  This method
     * should only be used whenever a new shape has been selected and the
     * slider needs to be updated.
     *
     * @param   alphaValue
     *          The value that the alpha slider should be pointing to.
     *
     * @throws  IllegalArgumentException
     *          Will be thrown if <code>alphaValue</code> is not within the
     *          range specified by the range constants
     *          <code>MINIMUM_SLIDE_RANGE</code> and
     *          <code>MAXIMUM_SLIDER_RANGE</code> provided by the class
     *          <code>TheAnimatedPoseurSettings</code>.
     */
    public void setAlphaSliderValue( int alphaValue )
            throws IllegalArgumentException {

        //  Validate that argument is an acceptable value.
        if( isAlphaValueInRange( alphaValue ) ) {

            //  Sets the value for the alpha slider
            alphaColorSlider.setValue( alphaValue );

        } else {

            //  value of alphaValue is not within range
            throw new IllegalArgumentException( " Illegal value passed to " +
                    "'alphaValue'.  Value must be in range (" +
                    MINIMUM_SLIDER_RANGE + ", " + MAXIMUM_SLIDER_RANGE + ")." );

        }

    }

    /**
     * Determines if the value of <code>alphaValue</code> is within the
     * appropriate range of the alpha slider control.
     *
     * @param   alphaValue
     *          The value that is to be checked.
     *
     * @return  <code>true</code> if the value is within the appropriate range.
     * @return  <code>false</code> if the value is not within range.
     */
    private boolean isAlphaValueInRange( int alphaValue ) {
        return (alphaValue >= MINIMUM_SLIDER_RANGE &&
                alphaValue <= MAXIMUM_SLIDER_RANGE ) ? true : false;
    }

    /**
     * Retrieves the alpha value indicated by the alpha color slider.
     *
     * @return  The value that the alpha slider is currently pointing to.
     */
    public int getAlphaValue() {
        return alphaColorSlider.getValue();
    }

    /**
     * Will swap the color values between the outline and background color
     * buttons.
     */
    public void invertToggleButtonValues() {
        //  Aquires the color values
        Color newOutlineColor = backgroundColorButton.getBackground();
        Color newBackgroundColor = outlineColorButton.getBackground();

        //  Swaps the color values
        backgroundColorButton.setBackground( newBackgroundColor );
        outlineColorButton.setBackground( newOutlineColor );
    }

    /**
     * Updates the user interface depending on the current state of execution.
     * If the program is in the startup state than all controls except for the
     * new/open and close buttons should be disabled until a document has
     * been constructed.  If the user is constructing a new shape, than the
     * program should periodically re-render the rendering canvas so that it
     * appears to be updating constantly.  In other words the user interfaces
     * mode will be updated.
     */
    public final void updateMode() {
        //  Get the program state from the state manager
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        StateManager sm = singleton.getStateManager();
        ApplicationState state = sm.getState();
        FileManager fm = singleton.getFileManager();

        //  Update the gui to the functionality of the current state
        if( state == ApplicationState.STARTUP_STATE ) {
            //  Set the mouse cursor to the classic icon
            selectCursor( Cursor.DEFAULT_CURSOR );

            activateStartupConstrols();
        } else if ( state == ApplicationState.SELECT_SHAPE_STATE ) {
            //  Set the mouse cursor to its classic icon
            selectCursor( Cursor.DEFAULT_CURSOR );

            //  Activate all constrols used for sprite selection
            setEnabledToolbarComponents( selectionToolbar, false);
            //  if undo redo are occupied show buttons
            //  if item in clipboard, show paste button
            setEnabledToolbarComponents( colorToolbar, true );
            setEnabledToolbarComponents( shapeEditorToolbar, true );
            setEnabledToolbarComponents( zoomToolbar, true );

            //  enable animation sequence pane
            animationPane.activateStartupControls( true );

        } else if( state == ApplicationState.CREATE_SHAPE_STATE ) {
            //  Set the shape drawing stencil for mouse
            selectCursor( Cursor.CROSSHAIR_CURSOR );

            //  Turn off all shape selection buttons except for shape selection
            setEnabledToolbarComponents( selectionToolbar, false );
            selectShapeButton.setEnabled( true );

            /* The rest of the job will be completed by state manager when the
             * user presses the mouse button.
             */
        }

        //  enable save button if file not saved.
        saveFileButton.setEnabled( !fm.isFileSaved() );

        renderingCanvas.repaint();
    }

    /**
     * This method will enable any buttons/tool-bars that are necessary for the
     * user to select specific shapes on the rendering canvas.  This will also
     * enable any tools that will edit the shapes on the canvas.
     */
    private void activateShapeEditingTools() {
        //  shape toolbar
        createLineButton.setEnabled( true );
        createRectButton.setEnabled( true );
        createEllipseButton.setEnabled( true );
        createFreeHandShapeButton.setEnabled( true );

        //  shape editing tools
        lineThicknessCombobox.setEnabled( true );
        setEnabledToolbarComponents( colorToolbar, true );
        setEnabledToolbarComponents( zoomToolbar, true );
        animationPane.activateStartupControls( true );
    }

    /**
     * Will disable all controls that are not to be used during the applications
     * initial startup and keep active only those controls that will help the
     * user either setup a project or exit the application.
     */
    private void activateStartupConstrols() {
        //  Disables all buttons within the file toolbar
        setEnabledToolbarComponents( fileToolbar, false );

        //  Keep the new/open and closed buttons active
        newFileButton.setEnabled( true );
        openFileButton.setEnabled( true );
        closeFileButton.setEnabled( true );


        //  No other controls should be active
        setEnabledToolbarComponents( selectionToolbar, false );
        setEnabledToolbarComponents( shapeEditorToolbar, false );
        setEnabledToolbarComponents( zoomToolbar, false );
        setEnabledToolbarComponents( colorToolbar, false );

        //  Disables all controls within the animation sequence
        animationPane.activateStartupControls( false );
    }

    /**
     * This will enable all of the components that are necessary for selecting
     * shapes on the rendering canvas.
     */
    private void activateSelectionControls( boolean canSave ) {
        //  file toolbar tools
        saveFileButton.setEnabled( canSave );
        saveAsFileButton.setEnabled( true );
        exportSpriteButton.setEnabled( true );

        //  selection toolbar tools
        //  TODO-  Check if there is anythin in the UNDO-REDO BUFFERES
        selectShapeButton.setEnabled( true );
        copyShapeButton.setEnabled( false );
        cutShapeButton.setEnabled( false );
        //  TODO-  If CLIPBOARD is filled activate paste button
    }

    /**
     * This helper method will simplify the task of disabling all of the controls
     * within a <code>JToolBar</code>.
     *
     * @param   tb
     *          The tool-bar which contains the controls that are to be
     *          disabled.
     */
    private void setEnabledToolbarComponents( JToolBar tb, boolean enabled ) {
        Component[] controls = tb.getComponents();
        for( Component ctrl : controls ) {
            ctrl.setEnabled( enabled );
        }
    }

    /**
     * Will set the design of the mouse cursor depending on the state of the
     * application
     *
     * Written by Richard McKenna
     *
     * @param   cursorToUse
     *          The type of cursor that is to be used by the application.
     */
    private void selectCursor(int cursorToUse)
    {
        // AND NOW SWITCH TO A CROSSHAIRS CURSOR
        Cursor arrowCursor = Cursor.getPredefinedCursor(cursorToUse);
        setCursor(arrowCursor);
    }

    /**
     * Will update the animation states combo-box to include any newly created
     * <code>AnimationState</code>'s.  After the item is added to the combo-box
     * it will be selected automatically so that all of its frames (should only
     * be one) will be shown in the animation sequence.
     *
     * @param   animationName
     *          Name of the <code>AnimationState</code> added to the box.
     */
    public void updateAnimationStateCombobox( String animationName ) {
        //  Adds the animation to the animation states combobox
        JComboBox animationStates = animationPane.getAnimationStatesCombobox();
        animationStates.addItem( animationName );
        //  Select the newly created animation sequence
        animationStates.setSelectedItem( animationName );
    }

    /**
     * Will update the animation sequence so that it will contain all of the
     * frames that exist for the selected animation state.  The currently
     * selected frame will then be set to the first frame.
     *
     * @param   selectedState
     *          The value of the selected animation state that will be used to
     *          populate the animation sequence.
     */
    public void updateAnimationSequence( AnimationState selectedState ) {
        //  Get the animation sequence and clear it for new information
        AnimationSequence sequenceDisplay = animationPane.getAnimationSequence();
        sequenceDisplay.removeAll();

        //  Add all of the animation frame to the sequence
        Iterator<AnimationStateFrame> frames = selectedState.getAnimationSequenceIterator();
        while( frames.hasNext() ) {
            AnimationStateFrame frame = frames.next();
            sequenceDisplay.addFrame( frame );
        }
    }

}
