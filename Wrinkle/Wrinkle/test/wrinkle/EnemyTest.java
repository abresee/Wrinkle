/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;

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
public class EnemyTest {

    public EnemyTest() {
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
     * Test of update method, of class Enemy.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        GameObjects go = null;
        Enemy instance = null;
        instance.update(go);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of activeScript method, of class Enemy.
     */
    @Test
    public void testActiveScript() {
        System.out.println("activeScript");
        Enemy instance = null;
        instance.activeScript();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of idleScript method, of class Enemy.
     */
    @Test
    public void testIdleScript() {
        System.out.println("idleScript");
        Enemy instance = null;
        instance.idleScript();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class EnemyImpl extends Enemy {

        public EnemyImpl() {
            super(null, "", 0, 0);
        }

        public void activeScript() {
        }

        public void idleScript() {
        }
    }

}