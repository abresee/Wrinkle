/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;

import java.awt.image.BufferedImage;
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
public class LevelTest {

    public LevelTest() {
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
     * Test of getWrinkle method, of class Level.
     */
    @Test
    public void testGetWrinkle() {
        System.out.println("getWrinkle");
        Level instance = new Level();
        Wrinkle expResult = null;
        Wrinkle result = instance.getWrinkle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Jump method, of class Level.
     */
    @Test
    public void testJump1() {
        System.out.println("Jump");
        Level instance = new Level();
        instance.Jump();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of goRight method, of class Level.
     */
    @Test
    public void testGoRight() {
        System.out.println("goRight");
        Level instance = new Level();
        instance.goRight();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of goLeft method, of class Level.
     */
    @Test
    public void testGoLeft() {
        System.out.println("goLeft");
        Level instance = new Level();
        instance.goLeft();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of jump method, of class Level.
     */
    @Test
    public void testJump2() {
        System.out.println("jump");
        Level instance = new Level();
        instance.jump();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of breatheFire method, of class Level.
     */
    @Test
    public void testBreatheFire() {
        System.out.println("breatheFire");
        Level instance = new Level();
        instance.breatheFire();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unBreatheFire method, of class Level.
     */
    @Test
    public void testUnBreatheFire() {
        System.out.println("unBreatheFire");
        Level instance = new Level();
        instance.unBreatheFire();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unGoRight method, of class Level.
     */
    @Test
    public void testUnGoRight() {
        System.out.println("unGoRight");
        Level instance = new Level();
        instance.unGoRight();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unGoLeft method, of class Level.
     */
    @Test
    public void testUnGoLeft() {
        System.out.println("unGoLeft");
        Level instance = new Level();
        instance.unGoLeft();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImage method, of class Level.
     */
    @Test
    public void testGetImage() {
        System.out.println("getImage");
        Level instance = new Level();
        BufferedImage expResult = null;
        BufferedImage result = instance.getImage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawBackground method, of class Level.
     */
    @Test
    public void testDrawBackground() {
        System.out.println("drawBackground");
        Level instance = new Level();
        instance.drawBackground();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawToForeground method, of class Level.
     */
    @Test
    public void testDrawToForeground() {
        System.out.println("drawToForeground");
        Level instance = new Level();
        instance.drawForeground();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of go method, of class Level.
     */
    @Test
    public void testGo() throws Exception {
        System.out.println("go");
        Level instance = new Level();
        instance.go();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}