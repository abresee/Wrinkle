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
public class FireTest {

    public FireTest() {
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
     * Test of initImages method, of class Fire.
     */
    @Test
    public void testInitImages() {
        System.out.println("initImages");
        Fire instance = null;
        instance.initImages();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class Fire.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        GameObjects go = null;
        Fire instance = null;
        instance.update(go);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of die method, of class Fire.
     */
    @Test
    public void testDie() {
        System.out.println("die");
        Fire instance = null;
        instance.die();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hurt method, of class Fire.
     */
    @Test
    public void testHurt() {
        System.out.println("hurt");
        Fire instance = null;
        instance.hurt();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of draw method, of class Fire.
     */
    @Test
    public void testDraw() {
        System.out.println("draw");
        Graphics2D g = null;
        Fire instance = null;
        instance.draw(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleTerrainCollisionX method, of class Fire.
     */
    @Test
    public void testHandleTerrainCollisionX() {
        System.out.println("handleTerrainCollisionX");
        Terrain i = null;
        Fire instance = null;
        instance.handleTerrainCollisionX(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleActorCollisionX method, of class Fire.
     */
    @Test
    public void testHandleActorCollisionX() throws Exception {
        System.out.println("handleActorCollisionX");
        Actor i = null;
        Fire instance = null;
        instance.handleActorCollisionX(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleTerrainCollisionY method, of class Fire.
     */
    @Test
    public void testHandleTerrainCollisionY() {
        System.out.println("handleTerrainCollisionY");
        Terrain i = null;
        Fire instance = null;
        instance.handleTerrainCollisionY(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleActorCollisionY method, of class Fire.
     */
    @Test
    public void testHandleActorCollisionY() {
        System.out.println("handleActorCollisionY");
        Actor i = null;
        Fire instance = null;
        instance.handleActorCollisionY(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateVel method, of class Fire.
     */
    @Test
    public void testUpdateVel() {
        System.out.println("updateVel");
        Fire instance = null;
        instance.updateVel();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateState method, of class Fire.
     */
    @Test
    public void testUpdateState() {
        System.out.println("updateState");
        Fire instance = null;
        instance.updateState();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateAnim method, of class Fire.
     */
    @Test
    public void testUpdateAnim() {
        System.out.println("updateAnim");
        Fire instance = null;
        instance.updateAnim();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateSound method, of class Fire.
     */
    @Test
    public void testUpdateSound() {
        System.out.println("updateSound");
        Fire instance = null;
        instance.updateSound();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}