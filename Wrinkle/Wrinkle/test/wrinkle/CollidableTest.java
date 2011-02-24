/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alex
 */
public class CollidableTest {

    public CollidableTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getX method, of class Collidable.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Collidable instance = new CollidableImpl();
        float expResult = 0.0F;
        float result = instance.getX();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getY method, of class Collidable.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        Collidable instance = new CollidableImpl();
        float expResult = 0.0F;
        float result = instance.getY();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWidth method, of class Collidable.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        Collidable instance = new CollidableImpl();
        int expResult = 0;
        int result = instance.getWidth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHeight method, of class Collidable.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        Collidable instance = new CollidableImpl();
        int expResult = 0;
        int result = instance.getHeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of draw method, of class Collidable.
     */
    @Test
    public void testDraw() {
        System.out.println("draw");
        Graphics2D g = null;
        Collidable instance = new CollidableImpl();
        instance.draw(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateBoundingBox method, of class Collidable.
     */
    @Test
    public void testGenerateBoundingBox() {
        System.out.println("generateBoundingBox");
        Collidable instance = new CollidableImpl();
        instance.generateBoundingBox();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getbBox method, of class Collidable.
     */
    @Test
    public void testGetbBox() {
        System.out.println("getbBox");
        Collidable instance = new CollidableImpl();
        Rectangle2D expResult = null;
        Rectangle2D result = instance.getbBox();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class CollidableImpl extends Collidable {

        public int getWidth() {
            return 0;
        }

        public int getHeight() {
            return 0;
        }

        public void draw(Graphics2D g) {
        }

        public void generateBoundingBox() {
        }
    }

}