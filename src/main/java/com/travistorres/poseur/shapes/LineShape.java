/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.shapes;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import org.w3c.dom.Element;

/**
 * The class <code>LineShape</code> is used for the construction and
 * maintenance of Line Segments that will be used within The Animated Poseur
 * application.  With this structure the geometry that forms a line segment
 * will be defined and constructed so that the user will have the ability to
 * draw lines within their Sprite Type objects and also be able to move these
 * lines around freely without any corruption being caused to its geometry.
 *
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012       Initial Release
 */
public class LineShape extends PoseurShape {

    private Line2D.Double geometry;
    private static Line2D.Double scaledGeometry = new Line2D.Double();

    /**
     * Will setup the initial geometry required for rendering a line segment
     * to the screen.
     */
    public LineShape( Line2D.Double geo ) {
        super();
        geometry = geo;
    }

    /**
     * Will construct a new <code>LineShape</code> object with some initial
     * geometric value attached to it.
     *
     * @param   scaledXPos
     *          The x-position of the shape.
     * @param   scaledYPos
     *          the y-position for the shape.
     * @return
     */
    public static LineShape factoryBuildLine( int scaledXPos, int scaledYPos) {
        Line2D.Double line = new Line2D.Double( scaledXPos, scaledYPos,
                                                scaledXPos, scaledYPos );
        return new LineShape( line );
    }


    /**
     * {@inheritDoc }
     *
     * In the case of a <code>LineShape</code>, the value that will be returned
     * is <code>PoseurShapeType.LINE</code>.
     *
     * @return  The <code>PoseurShapeType.LINE</code> value.
     */
    @Override
    public PoseurShapeType getShapeType() {
        return PoseurShapeType.LINE;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean containsPoint( Point2D p ) {
        return geometry.intersects(p.getX(), p.getX(), 5, 5);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void render( Graphics2D g2d, int canvasXPos, int canvasYPos,
            float zoomLevel, boolean isSelected ) {

        //  Constructs a scaled version of the current geometry
        scaledGeometry.x1 = canvasXPos + ( geometry.x1 * zoomLevel );
        scaledGeometry.y1 = canvasYPos + ( geometry.y1 * zoomLevel );
        scaledGeometry.x2 = canvasXPos + ( geometry.x2 * zoomLevel );
        scaledGeometry.y2 = canvasYPos + ( geometry.y2 * zoomLevel );

        //  Renders the scaled shape onto the screen
        renderShape( g2d, scaledGeometry, isSelected );

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void move( int x, int y ) {
        //  Calculate the distance between linear points
        double dx = Math.abs( geometry.x2 - geometry.x1 );
        double dy = Math.abs( geometry.y2 - geometry.y1 );

        /* Calculates the x-position based on which point is closest to the
         * left side of the rendering canvas.
         */
        if( geometry.x1 <= geometry.x2 ) {
            geometry.x1 = x;
            geometry.x2 = x + dx;
        } else {
            geometry.x1 = x + dx;
            geometry.x2 = x;
        }

        /* Calculates the y-position based on which point is closest to the
         * norther edge of the rendering canvas.
         */
        if( geometry.y1 <= geometry.y2 ) {
            geometry.y1 = y;
            geometry.y2 = y + dy;
        } else {
            geometry.y1 = y + dy;
            geometry.y2 = y;
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void moveShape( int dx, int dy, Rectangle2D.Double geo ) {
        //  Update the points on the screen
        geometry.x1 += dx;
        geometry.y1 += dy;
        geometry.x2 += dx;
        geometry.y2 += dy;

        //  Validate that the points will stay on the screen.
        keepShapeOnCanvas( geo );
    }

    /**
     * Performs an algorithm that will keep the dimensions of the shape on the
     * rendering canvas, by swapping the values between linear points.
     *
     * @param   geo
     *          The dimensions of the canvas that the shape will be rendered
     *          onto.
     */
    private void keepShapeOnCanvas( Rectangle2D.Double geo ) {
        //  NOTE-  Consider useing abs function on width and height
        double lineWidth = geometry.x2 - geometry.x1;
        double lineHeight = geometry.y2 - geometry.y1;

        if( geometry.x1 < 0 ) {
            geometry.x1 = 0;
            geometry.x2 = lineWidth;
        } else if( geometry.x2 < 0 ) {
            geometry.x1 = -lineWidth;
            geometry.x2 = 0;
        }

        if( geometry.x1 > geo.width ) {
            geometry.x1 = geo.width;
            geometry.x2 = geo.width + lineWidth;
        } else if( geometry.x2 > geo.width ) {
            geometry.x1 = geo.width - lineWidth;
            geometry.x2 = geo.width;
        }

        if( geometry.y1 < 0 ) {
            geometry.y1 = 0;
            geometry.y2 = lineHeight;
        } else if ( geometry.y2 < 0 ) {
            geometry.y1 = -lineHeight;
            geometry.y2 = 0;
        }

        if( geometry.y1 > geo.height ) {
            geometry.y1 = geo.height;
            geometry.y2 = geo.height + lineHeight;
        } else if( geometry.y2 > geo.height ) {
            geometry.y1 = geo.height - lineHeight;
            geometry.y2 = geo.height;
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean completesValidShape( int x, int y ) {
        return true;
    }

    /**
     * Will update the tail of the line segment so that it will be rendered in
     * the current location of the mouse cursor, as long as the cursor is still
     * on the rendering canvas.
     */
    @Override
    public void updateShapeInProgress( int updateX, int updateY ) {
        geometry.x2 = updateX;
        geometry.y2 = updateY;
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
        //  Create a duplicate representation of the geometry
        Line2D.Double duplicateGeo = (Line2D.Double)geometry.clone();

        //  Create a new LineShape object with similar properties
        PoseurShape clonedShape         = new LineShape( duplicateGeo );
        clonedShape.alphaColorValue     = this.alphaColorValue;
        clonedShape.backgroundColor     = this.backgroundColor;
        clonedShape.outlineColor        = this.outlineColor;
        clonedShape.outlineThickness    = this.outlineThickness;

        return clonedShape;
    }

}
