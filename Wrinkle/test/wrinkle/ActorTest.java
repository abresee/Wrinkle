/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;

import javax.sound.sampled.Clip;
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
public class ActorTest {

    public ActorTest() {
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
     * Test of hurt method, of class Actor.
     */
    @Test
    public void testHurt() throws Exception {
        System.out.println("hurt");
        Actor instance = null;
        instance.hurt();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initSounds method, of class Actor.
     */
    @Test
    public void testInitSounds() {
        System.out.println("initSounds");
        String str = "";
        Actor instance = null;
        instance.initSounds(str);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of jump method, of class Actor.
     */
    @Test
    public void testJump() {
        System.out.println("jump");
        Actor instance = null;
        instance.jump();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleTerrainCollisionX method, of class Actor.
     */
    @Test
    public void testHandleTerrainCollisionX() {
        System.out.println("handleTerrainCollisionX");
        Terrain i = null;
        Actor instance = null;
        instance.handleTerrainCollisionX(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleActorCollisionX method, of class Actor.
     */
    @Test
    public void testHandleActorCollisionX() {
        System.out.println("handleActorCollisionX");
        Actor i = null;
        Actor instance = null;
        instance.handleActorCollisionX(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXtoEdge method, of class Actor.
     */
    @Test
    public void testSetXtoEdge() {
        System.out.println("setXtoEdge");
        Collidable i = null;
        Actor instance = null;
        instance.setXtoEdge(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleTerrainCollisionY method, of class Actor.
     */
    @Test
    public void testHandleTerrainCollisionY() {
        System.out.println("handleTerrainCollisionY");
        Terrain i = null;
        Actor instance = null;
        instance.handleTerrainCollisionY(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleActorCollisionY method, of class Actor.
     */
    @Test
    public void testHandleActorCollisionY() throws Exception {
        System.out.println("handleActorCollisionY");
        Actor i = null;
        Actor instance = null;
        instance.handleActorCollisionY(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateState method, of class Actor.
     */
    @Test
    public void testUpdateState() {
        System.out.println("updateState");
        Actor instance = null;
        instance.updateState();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateAnim method, of class Actor.
     */
    @Test
    public void testUpdateAnim() {
        System.out.println("updateAnim");
        Actor instance = null;
        instance.updateAnim();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateSound method, of class Actor.
     */
    @Test
    public void testUpdateSound() {
        System.out.println("updateSound");
        Actor instance = null;
        instance.updateSound();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateVel method, of class Actor.
     */
    @Test
    public void testUpdateVel() {
        System.out.println("updateVel");
        Actor instance = null;
        instance.updateVel();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isFacing method, of class Actor.
     */
    @Test
    public void testIsFacing() {
        System.out.println("isFacing");
        Collidable c = null;
        Actor instance = null;
        boolean expResult = false;
        boolean result = instance.isFacing(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playClip method, of class Actor.
     */
    @Test
    public void testPlayClip() {
        System.out.println("playClip");
        Clip clip = null;
        Actor instance = null;
        instance.playClip(clip);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ActorImpl extends Actor {

        public ActorImpl() {
            super("", 0.0F, 0.0F);
        }
    }

}