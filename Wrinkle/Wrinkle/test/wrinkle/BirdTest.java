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
public class BirdTest {

    public BirdTest() {
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
     * Test of idleScript method, of class Bird.
     */
    @Test
    public void testIdleScript() {
        System.out.println("idleScript");
        Bird instance = null;
        instance.idleScript();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of activeScript method, of class Bird.
     */
    @Test
    public void testActiveScript() {
        System.out.println("activeScript");
        Bird instance = null;
        instance.activeScript();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of draw method, of class Bird.
     */
    @Test
    public void testDraw() {
        System.out.println("draw");
        Graphics2D g = null;
        Bird instance = null;
        instance.draw(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateVel method, of class Bird.
     */
    @Test
    public void testUpdateVel() {
        System.out.println("updateVel");
        Bird instance = null;
        instance.updateVel();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}