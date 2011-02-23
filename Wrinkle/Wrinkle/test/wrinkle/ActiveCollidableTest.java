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
public class ActiveCollidableTest {

    public ActiveCollidableTest() {
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
     * Test of initPhys method, of class ActiveCollidable.
     */
    @Test
    public void testInitPhys() {
        System.out.println("initPhys");
        float X = 0.0F;
        float Y = 0.0F;
        float velX_ = 0.0F;
        float velY_ = 0.0F;
        float accelX_ = 0.0F;
        float accelY_ = 0.0F;
        ActiveCollidable instance = null;
        instance.initPhys(X, Y, velX_, velY_, accelX_, accelY_);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHeight method, of class ActiveCollidable.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        ActiveCollidable instance = null;
        int expResult = 0;
        int result = instance.getHeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWidth method, of class ActiveCollidable.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        ActiveCollidable instance = null;
        int expResult = 0;
        int result = instance.getWidth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of draw method, of class ActiveCollidable.
     */
    @Test
    public void testDraw() {
        System.out.println("draw");
        Graphics2D g = null;
        ActiveCollidable instance = null;
        instance.draw(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateBoundingBox method, of class ActiveCollidable.
     */
    @Test
    public void testGenerateBoundingBox() {
        System.out.println("generateBoundingBox");
        ActiveCollidable instance = null;
        instance.generateBoundingBox();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getbBox method, of class ActiveCollidable.
     */
    @Test
    public void testGetbBox() {
        System.out.println("getbBox");
        ActiveCollidable instance = null;
        Rectangle2D expResult = null;
        Rectangle2D result = instance.getbBox();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of die method, of class ActiveCollidable.
     */
    @Test
    public void testDie() throws Exception {
        System.out.println("die");
        ActiveCollidable instance = null;
        instance.die();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDead method, of class ActiveCollidable.
     */
    @Test
    public void testIsDead() {
        System.out.println("isDead");
        ActiveCollidable instance = null;
        boolean expResult = false;
        boolean result = instance.isDead();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of collidesWith method, of class ActiveCollidable.
     */
    @Test
    public void testCollidesWith() {
        System.out.println("collidesWith");
        Collidable c = null;
        ActiveCollidable instance = null;
        boolean expResult = false;
        boolean result = instance.collidesWith(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class ActiveCollidable.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        GameObjects go = null;
        ActiveCollidable instance = null;
        instance.update(go);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tryMoveX method, of class ActiveCollidable.
     */
    @Test
    public void testTryMoveX() throws Exception {
        System.out.println("tryMoveX");
        float delx = 0.0F;
        GameObjects go = null;
        ActiveCollidable instance = null;
        instance.tryMoveX(delx, go);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tryMoveY method, of class ActiveCollidable.
     */
    @Test
    public void testTryMoveY() throws Exception {
        System.out.println("tryMoveY");
        float delY = 0.0F;
        GameObjects go = null;
        ActiveCollidable instance = null;
        instance.tryMoveY(delY, go);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleTerrainCollisionX method, of class ActiveCollidable.
     */
    @Test
    public void testHandleTerrainCollisionX() {
        System.out.println("handleTerrainCollisionX");
        Terrain i = null;
        ActiveCollidable instance = null;
        instance.handleTerrainCollisionX(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleActorCollisionX method, of class ActiveCollidable.
     */
    @Test
    public void testHandleActorCollisionX() throws Exception {
        System.out.println("handleActorCollisionX");
        Actor i = null;
        ActiveCollidable instance = null;
        instance.handleActorCollisionX(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleTerrainCollisionY method, of class ActiveCollidable.
     */
    @Test
    public void testHandleTerrainCollisionY() {
        System.out.println("handleTerrainCollisionY");
        Terrain i = null;
        ActiveCollidable instance = null;
        instance.handleTerrainCollisionY(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleActorCollisionY method, of class ActiveCollidable.
     */
    @Test
    public void testHandleActorCollisionY() throws Exception {
        System.out.println("handleActorCollisionY");
        Actor i = null;
        ActiveCollidable instance = null;
        instance.handleActorCollisionY(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateVel method, of class ActiveCollidable.
     */
    @Test
    public void testUpdateVel() {
        System.out.println("updateVel");
        ActiveCollidable instance = null;
        instance.updateVel();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateState method, of class ActiveCollidable.
     */
    @Test
    public void testUpdateState() {
        System.out.println("updateState");
        ActiveCollidable instance = null;
        instance.updateState();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateAnim method, of class ActiveCollidable.
     */
    @Test
    public void testUpdateAnim() {
        System.out.println("updateAnim");
        ActiveCollidable instance = null;
        instance.updateAnim();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateSound method, of class ActiveCollidable.
     */
    @Test
    public void testUpdateSound() {
        System.out.println("updateSound");
        ActiveCollidable instance = null;
        instance.updateSound();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ActiveCollidableImpl extends ActiveCollidable {

        public ActiveCollidableImpl() {
            super(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        }

        public void handleTerrainCollisionX(Terrain i) {
        }

        public void handleActorCollisionX(Actor i) throws DeadException {
        }

        public void handleTerrainCollisionY(Terrain i) {
        }

        public void handleActorCollisionY(Actor i) throws DeadException {
        }

        public void updateVel() {
        }

        public void updateState() {
        }

        public void updateAnim() {
        }

        public void updateSound() {
        }
    }

}