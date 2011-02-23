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
public class AnimationSetTest {

    public AnimationSetTest() {
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
     * Test of getNextSprite method, of class AnimationSet.
     */
    @Test
    public void testGetNextSprite() {
        System.out.println("getNextSprite");
        State s = null;
        boolean facingLeft = false;
        AnimationSet instance = null;
        BufferedImage expResult = null;
        BufferedImage result = instance.getNextSprite(s, facingLeft);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}