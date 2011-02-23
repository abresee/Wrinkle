/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;

import java.awt.Graphics2D;
import java.awt.Point;
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
public class WrinkleTest {

    public WrinkleTest() {
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
     * Test of die method, of class Wrinkle.
     */
    @Test
    public void testDie() throws Exception {
        System.out.println("die");
        Wrinkle instance = new Wrinkle();
        instance.die();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isBreathingFire method, of class Wrinkle.
     */
    @Test
    public void testIsBreathingFire() {
        System.out.println("isBreathingFire");
        Wrinkle instance = new Wrinkle();
        boolean expResult = false;
        boolean result = instance.isBreathingFire();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMouseLoc method, of class Wrinkle.
     */
    @Test
    public void testSetMouseLoc() {
        System.out.println("setMouseLoc");
        Point p = null;
        Wrinkle instance = new Wrinkle();
        instance.setMouseLoc(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of goRight method, of class Wrinkle.
     */
    @Test
    public void testGoRight() {
        System.out.println("goRight");
        Wrinkle instance = new Wrinkle();
        instance.goRight();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unGoRight method, of class Wrinkle.
     */
    @Test
    public void testUnGoRight() {
        System.out.println("unGoRight");
        Wrinkle instance = new Wrinkle();
        instance.unGoRight();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of goLeft method, of class Wrinkle.
     */
    @Test
    public void testGoLeft() {
        System.out.println("goLeft");
        Wrinkle instance = new Wrinkle();
        instance.goLeft();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unGoLeft method, of class Wrinkle.
     */
    @Test
    public void testUnGoLeft() {
        System.out.println("unGoLeft");
        Wrinkle instance = new Wrinkle();
        instance.unGoLeft();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of correctOffsets method, of class Wrinkle.
     */
    @Test
    public void testCorrectOffsets() {
        System.out.println("correctOffsets");
        Wrinkle instance = new Wrinkle();
        instance.correctOffsets();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fire method, of class Wrinkle.
     */
    @Test
    public void testFire() {
        System.out.println("fire");
        Wrinkle instance = new Wrinkle();
        instance.fire();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of breatheFire method, of class Wrinkle.
     */
    @Test
    public void testBreatheFire() {
        System.out.println("breatheFire");
        Wrinkle instance = new Wrinkle();
        instance.breatheFire();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unBreatheFire method, of class Wrinkle.
     */
    @Test
    public void testUnBreatheFire() {
        System.out.println("unBreatheFire");
        Wrinkle instance = new Wrinkle();
        instance.unBreatheFire();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class Wrinkle.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        GameObjects go = null;
        Wrinkle instance = new Wrinkle();
        instance.update(go);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of draw method, of class Wrinkle.
     */
    @Test
    public void testDraw() {
        System.out.println("draw");
        Graphics2D g = null;
        Wrinkle instance = new Wrinkle();
        instance.draw(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateState method, of class Wrinkle.
     */
    @Test
    public void testUpdateState() {
        System.out.println("updateState");
        Wrinkle instance = new Wrinkle();
        instance.updateState();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateAnim method, of class Wrinkle.
     */
    @Test
    public void testUpdateAnim() {
        System.out.println("updateAnim");
        Wrinkle instance = new Wrinkle();
        instance.updateAnim();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}