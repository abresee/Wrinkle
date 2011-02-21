/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;

import java.awt.Graphics2D;
import java.util.List;
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
public class GameObjectsTest {

    public GameObjectsTest() {
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
     * Test of add method, of class GameObjects.
     */
    @Test
    public void testAdd_Terrain() {
        System.out.println("add");
        Terrain t = null;
        GameObjects instance = new GameObjects();
        instance.add(t);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class GameObjects.
     */
    @Test
    public void testAdd_Actor() {
        System.out.println("add");
        Actor a = null;
        GameObjects instance = new GameObjects();
        instance.add(a);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class GameObjects.
     */
    @Test
    public void testAdd_DieBox() {
        System.out.println("add");
        DieBox d = null;
        GameObjects instance = new GameObjects();
        instance.add(d);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTerrains method, of class GameObjects.
     */
    @Test
    public void testGetTerrains() {
        System.out.println("getTerrains");
        GameObjects instance = new GameObjects();
        List expResult = null;
        List result = instance.getTerrains();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActors method, of class GameObjects.
     */
    @Test
    public void testGetActors() {
        System.out.println("getActors");
        GameObjects instance = new GameObjects();
        List expResult = null;
        List result = instance.getActors();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDieBoxes method, of class GameObjects.
     */
    @Test
    public void testGetDieBoxes() {
        System.out.println("getDieBoxes");
        GameObjects instance = new GameObjects();
        List expResult = null;
        List result = instance.getDieBoxes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of draw method, of class GameObjects.
     */
    @Test
    public void testDraw() {
        System.out.println("draw");
        Graphics2D g = null;
        GameObjects instance = new GameObjects();
        instance.draw(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class GameObjects.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        GameObjects instance = new GameObjects();
        instance.update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}