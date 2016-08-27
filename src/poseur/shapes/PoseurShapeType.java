/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.shapes;

/**
 * A type enumeration that contains all of the shape types that can be constructed
 * using The Animated Poseur Application.  These types are useful whenever
 * certain conditions need to be set before rendering shapes to the screen.
 * For instance in order to render a <code>FREEHAND</code> shape it is important
 * to realize that this class contains a list of pixel coordinates that each
 * need to be rendered to the screen in order to make the custom shape.
 * 
 * @author      Travis Anthony Torres
 * @version     1.0     November 2012       Initial Release
 */
public enum PoseurShapeType {
    RECTANGLE,
    ELLIPSE,
    LINE,
    FREEHAND
}
