/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import org.w3c.dom.Element;
import poseur.TheAnimatedPoseur;
import static poseur.TheAnimatedPoseurSettings.*;
import poseur.gui.UserInterface;

/**
 * The class <code>PoseurShape</code> is an abstract form for how all shapes
 * will act within The Animated Poseur application.
 * 
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012   Initial Release
 */
public abstract class PoseurShape {

    protected BasicStroke outlineThickness;
    protected Color       outlineColor;
    protected Color       backgroundColor;
    protected int         alphaColorValue;
    
    /**
     * Will allocate memory to all of the properties that make up a Shape.
     */
    public PoseurShape() {
        //  Get the user interface
        TheAnimatedPoseur singleton = TheAnimatedPoseur.getAnimatedPoseur();
        UserInterface gui = singleton.getGUI();
        
        //  Extract color information from the gui
        outlineThickness    = gui.getLineThickness();
        outlineColor        = gui.getOutlineColor();
        backgroundColor     = gui.getBackgroundColor();
        alphaColorValue     = gui.getAlphaValue();
    }
    
    /**
     * Will retrieve a representation for how all line thickness's should be.
     * 
     * @return  A representation for the line thickness.
     */
    public final BasicStroke getOutlineThickness() {
        return outlineThickness;
    }
    
    /**
     * Retrieves the color that will be used on all shapes outlines.
     * 
     * @return  The color for the outline of all shapes.
     */
    public final Color getOutlineColor() {
        return outlineColor;
    }
    
    /**
     * Retrieves the color that will be used on all of the shapes backgrounds.
     * 
     * @return  The color for the background of all shapes.
     */
    public final Color getBackgroundColor() {
        return backgroundColor;
    }
    
    /**
     * Retrieves the alpha color value that will represent the transparency
     * level for the shape.
     * 
     * @return  The level of transparency for the shape.
     */
    public final int getAlphaColorValue() {
        return alphaColorValue;
    }
    
    /**
     * Will update the value of the shapes outline thickness to whatever has
     * been passed to the <param>otv</param> field.
     * 
     * @param   otv 
     *          The value that will be set the to the outline thickness.
     */
    public final void setOutlineThicknessValue( BasicStroke otv ) {
        outlineThickness = otv;
    }
    
    /**
     * Will change the current outline color to some new one specified by
     * <param>oc</param>.
     * 
     * @param   oc 
     *          The new outline color for the shape.
     */
    public final void setOutlineColor( Color oc ) {
        outlineColor = oc;
    }
    
    /**
     * Will change the current background color to some new one specified by
     * <param>bc</param>.
     * 
     * @param   bc
     *          The new background color value for the shape.
     */
    public final void setBackgroundColor( Color bc ) {
        backgroundColor = bc;
    }
    
    /**
     * Will change the alpha color value to the value represented by 
     * <param>a</param>.
     * 
     * @param   a
     *          The new transparency value for the shape.
     */
    public final void setAlphaColorValue( int a ) {
        alphaColorValue = a;
    }
    
    /**
     * Will render the geometry of the <code>PoseurShape</code> object onto
     * some graphics rendering device such as a monitor or file.
     * 
     * @param   g2d
     *          The graphics device that the shape is to be rendered onto.
     * @param   geo
     *          The geometry for the shape that is being rendered.
     * @param   isSelected
     *          Determines if the shape is being selected by the user on the
     *          rendering canvas.
     *          
     */
    public final void renderShape( Graphics2D g2d, Shape geo, boolean isSelected ) {
        //  Construct a fill color by merging the background and alpha colors
        Color fillColor = generateColor( backgroundColor );
        //  Draw the shape onto the Graphics device with the fillColor
        g2d.setColor( fillColor );
        g2d.fill( geo );
        
        //  Set the outline thickness value
        g2d.setStroke( outlineThickness );
        //  If the shape is selected, make it red; otherwise, use user color
        Color borderColor = ( isSelected ) ? generateColor( SELECTED_SHAPE_COLOR ) :
                                             generateColor( outlineColor );
        g2d.setColor( borderColor );
        g2d.draw( geo );   
    }
    
    /**
     * Will construct a new <code>Color</code> object that contains both the
     * value of <param>c</code> and the transparency value provided by
     * <code>alphaColorValue</code>.
     * 
     * @param   c
     *          The color that is to be used with the transparent color value.
     * 
     * @return  A transparent representation of the color <param>c</param>.
     */
    private Color generateColor( Color c ) {
        //  Deterime the color spectrum values
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        
        //  Return a new color with the alpha color value
        return new Color( r, g, b, alphaColorValue );
    }
    
    //
    // METHODS SPECIFIC TO THE POSEURSHAPE OBJECT BEING CONSTRUCTED
    //
    
    /**
     * Will return the type of <code>PoseurShape</code> object that is being
     * utilized.  This is one of the values provided by the 
     * <code>PoseurShapeType</code> enumeration type.
     * 
     * @return  The type of shape being constructed.
     */
    public abstract PoseurShapeType getShapeType();
    
    /**
     * Will determine if the coordinate-point <param>p</code> is within the
     * collection of points that forms the geometry for the shape.  This method
     * will come in handy when the user is attempting to choose a shape from
     * all of the previously rendered shapes on the screen.
     * 
     * @param   p
     *          The point that is believed to exist within the geometry for
     *          this shape.
     * 
     * @return  <code>true</code> if the point <param>p</code> is within the
     *          <code>PoseurShape</code>'s geometry.
     * @return  <code>false</code> if the point <param>p</code> is not
     *          contained within the geometry for this 
     *          <code>PoseurShape</code>.
     */
    public abstract boolean containsPoint( Point2D p );
    
    /**
     * Will process the steps necessary to render this shapes unique sort of
     * geometry onto the rendering canvas.  Since the geometry for different 
     * shapes is different in some form, it is necessary for each shape type
     * to provide its own algorithm.
     * 
     * @param   g2
     *          The graphics object that will be rendered onto.  This can be
     *          anything from a monitor to a file.
     * @param   canvisXPos
     *          The horizontal position (x-axis) of where the rendering
     *          canvas exist on the screen.
     * @param   canvasYPos
     *          The vertical position (y-axis) for where the rendering canvas
     *          exist on the screen.
     * @param   zoomLevel
     *          The scale that the shape should be drawn at.  A scale greater
     *          than 1.0f will cause the shape to appear larger than it is,
     *          while a scale that is less than 1.0f will appear smaller.
     * @param   isSelected
     *          Determines whether the shape being rendered is selected by the
     *          user or is being constructed.
     */
    public abstract void render(    Graphics2D g2,
                                    int canvasXPos, int canvasYPos,
                                    float zoomLevel,
                                    boolean isSelected );
    
    /**
     * Will move the shape to some position <code>( x, y )</code> on the
     * rendering device.  The point of the shape that is to be moved to this
     * position is to be determined by the implementor.
     * 
     * @param   x
     *          The new position on the x-axis.
     * @param   y 
     *          The new position on the y-axis.
     */
    public abstract void move( int x, int y );
    
    /**
     * Will move the shape from its current position on the graphical plane,
     * to some position <code>dx</code> pixels from its current x-value and
     * <code>dy</code> pixels from its current y-value.
     * 
     * @param   dx
     *          The vertical slope between the current x-position and the new
     *          x-position that the shape will be moved to.
     * @param   dy
     *          The horizontal slope between the current y-position and the new
     *          y-position that the shape will be moved to.
     * @param   geo 
     *          The geometry that makes up the shape that is being moved.
     */
    public abstract void moveShape( int dx, int dy, Rectangle2D.Double geo );
    
    /**
     * Will determine if the shape the geometry for the shape being created is
     * well built or not.  If the shape does not have a valid geometry value,
     * than it will be difficult to render onto the screen and so will thrown
     * away to the garbage collector so that a shape with valid geometry can be
     * constructed.
     * 
     * @param   x
     *          The x-axis coordinate that will be tested.
     * @param   y
     *          The y-axis coordinate that will be tested.
     * @return  <code>true</code> if the geometry for the shape is well
     *          designed.
     * @return  <code>false</code> if the geometry for the shape may cause
     *          complications and the shape should be destroyed.
     */
    public abstract boolean completesValidShape( int x, int y );
    
    /**
     * Will make sure that a shape that is initialy being sized does not
     * attempt to be constructed off of the rendering canvas.
     * 
     * @param   updateX
     *          The new position for the shape
     * @param   updateY 
     *          The new vertical position for the shape.
     */
    public abstract void updateShapeInProgress( int updateX, int updateY );
    
    /**
     * Adds the shape to a tree-node <param>node_i</code> when saving data to
     * HDD.
     * 
     * @param   node_i
     *          Some ith Node that exist within a data tree that will be used
     *          to save the project.
     */
    public abstract void addNodeData( Element node_i );
    
    /**
     * Allows some <code>PoseurShape</code> object to make an exact duplicate
     * of itself in memory so that the integrity of the current object will not
     * be manipulated.
     * 
     * @return An exact duplicate of the current <code>PoseurShape</code>
     * object.
     */
    @Override
    public abstract PoseurShape clone();
    
}
