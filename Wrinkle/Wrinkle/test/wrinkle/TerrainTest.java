/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;

import java.awt.Graphics2D;
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
public class TerrainTest {

    public TerrainTest() {
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
     * Test of getWidth method, of class Terrain.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        Terrain instance = new Terrain();
        int expResult = 0;
        int result = instance.getWidth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHeight method, of class Terrain.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        Terrain instance = new Terrain();
        int expResult = 0;
        int result = instance.getHeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateBoundingBox method, of class Terrain.
     */
    @Test
    public void testGenerateBoundingBox() {
        System.out.println("generateBoundingBox");
        Terrain instance = new Terrain();
        instance.generateBoundingBox();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of draw method, of class Terrain.
     */
    @Test
    public void testDraw() {
        System.out.println("draw");
        Graphics2D g = null;
        Terrain instance = new Terrain();
        instance.draw(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDeadly method, of class Terrain.
     */
    @Test
    public void testIsDeadly() {
        System.out.println("isDeadly");
        Terrain instance = new Terrain();
        boolean expResult = false;
        boolean result = instance.isDeadly();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}