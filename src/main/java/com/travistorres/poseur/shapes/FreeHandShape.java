/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travistorres.poseur.shapes;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D.Double;
import org.w3c.dom.Element;

/**
 *
 * @author travis
 */
public class FreeHandShape extends PoseurShape {

    /**
     * {@inheritDoc }
     */
    @Override
    public PoseurShapeType getShapeType() {

        return PoseurShapeType.FREEHAND;

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean containsPoint( Point2D p ) {

        throw new UnsupportedOperationException("Not supported yet.");

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void render( Graphics2D g2, int canvasXPos, int canvasYPos,
            float zoomLevel, boolean isSelected ) {

        throw new UnsupportedOperationException("Not supported yet.");

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void move( int x, int y ) {

        throw new UnsupportedOperationException("Not supported yet.");

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void moveShape( int dx, int dy, Double geo ) {

        throw new UnsupportedOperationException("Not supported yet.");

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean completesValidShape( int x, int y ) {

        throw new UnsupportedOperationException("Not supported yet.");

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void updateShapeInProgress( int updateX, int updateY ) {

        throw new UnsupportedOperationException("Not supported yet.");

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

        throw new UnsupportedOperationException("Not supported yet.");

    }

}
