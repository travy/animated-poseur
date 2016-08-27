/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.shapes;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import org.w3c.dom.Element;

/**
 * The class <code>RectangleShpae</code> is used for keeping track of the
 * geometry involved in rendering rectangular shapes to the screen.  Also, the
 * class will provide mechanisms to allow the shape to be moved around the
 * canvas and be updated (change color) as the user sees fit.
 * 
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012       Initial Release
 */
public class RectangleShape extends PoseurShape {

    private Rectangle2D.Double geometry;
    private static Rectangle2D.Double scaledGeometry = new Rectangle2D.Double();
    
    public RectangleShape( Rectangle2D.Double geo ) {
        super();
        geometry = geo;
    }
    
    public static RectangleShape factoryBuildRectangle( int xpos, int ypos ) {
        Rectangle2D.Double rect = new Rectangle2D.Double( xpos, ypos, 0, 0 );
        return new RectangleShape( rect );
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public PoseurShapeType getShapeType() {
        
        return PoseurShapeType.RECTANGLE;
        
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean containsPoint( Point2D p ) {
        
        return geometry.contains( p );
        
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void render( Graphics2D g2d, int canvasXPos, int canvasYPos,
            float zoomLevel, boolean isSelected ) {
        
        scaledGeometry.x = canvasXPos + (geometry.x * zoomLevel);
        scaledGeometry.y = canvasYPos + (geometry.y * zoomLevel);
        scaledGeometry.width = geometry.width * zoomLevel;
        scaledGeometry.height = geometry.height * zoomLevel;
        
        renderShape( g2d, scaledGeometry, isSelected);
        
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void move( int x, int y ) {
        
        geometry.x = x;
        geometry.y = y;
        
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void moveShape( int dx, int dy, Double geo ) {
        
        geometry.x += dx;
        geometry.y += dy;
        
        if( geometry.x < 0 ) {
            geometry.x = 0;
        }
        
        if( (geometry.x + geometry.width) > geo.width ) {
            geometry.x = geo.width - geometry.width - 1;
        }
        
        if( geometry.y < 0 ) {
            geometry.y = 0;
        }
        
        if( (geometry.y + geometry.height) > geo.height ) {
            geometry.y = geo.height - geometry.height - 1;
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean completesValidShape( int x, int y ) {
        
        return x >= geometry.x && y >= geometry.y;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void updateShapeInProgress( int updateX, int updateY ) {
        
        if( updateX < geometry.x ) {
            geometry.width = 0;
        } else {
            geometry.width = updateX - geometry.x;
        }
        
        if( updateY < geometry.y ) {
            geometry.height = 0;
        } else {
            geometry.height = updateY - geometry.y;
        }
        
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addNodeData( Element node_i ) {
        
        throw new UnsupportedOperationException("Not supported yet.");
        
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public PoseurShape clone() {
        
        Rectangle2D.Double copyGeometry = (Rectangle2D.Double)geometry.clone();
        
        PoseurShape copy = new RectangleShape( copyGeometry );
        copy.backgroundColor = this.backgroundColor;
        copy.outlineColor = this.outlineColor;
        copy.outlineThickness = this.outlineThickness;
        
        return copy;
        
    }
    
}
