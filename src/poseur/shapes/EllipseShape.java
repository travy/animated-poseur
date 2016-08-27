/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.shapes;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D.Double;
import org.w3c.dom.Element;

/**
 *
 * @author travis
 */
public class EllipseShape extends PoseurShape {

    private Ellipse2D.Double geometry;
    private static Ellipse2D.Double scaledGeometry = new Ellipse2D.Double();
    
    public EllipseShape( Ellipse2D.Double geo ) {
        super();
        geometry = geo;
    }
    
    public static EllipseShape factoryBuildEllipse( int xpos, int ypos ) {
        Ellipse2D.Double ellipse = new Ellipse2D.Double( xpos, ypos, 0, 0 );
        return new EllipseShape( ellipse );
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public PoseurShapeType getShapeType() {
        
        return PoseurShapeType.ELLIPSE;
        
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
        
        renderShape( g2d, scaledGeometry, isSelected );
        
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
        
        geometry.width = (updateX < geometry.x) ? 0 : updateX - geometry.x;
        geometry.height = (updateY < geometry.y) ? 0 : updateY - geometry.y;
        
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
        
        Ellipse2D.Double copyGeometry = (Ellipse2D.Double)geometry.clone();
        
        PoseurShape copy        = new EllipseShape( copyGeometry );
        copy.backgroundColor    = this.backgroundColor;
        copy.outlineColor       = this.outlineColor;
        copy.outlineThickness   = this.outlineThickness;
        
        return copy;
        
    }
    
}
