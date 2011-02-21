/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author alex
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({wrinkle.BirdTest.class,wrinkle.ActiveCollidableTest.class,wrinkle.StaticCollidableTest.class,wrinkle.GameObjectsTest.class,wrinkle.GameTest.class,wrinkle.DieBoxTest.class,wrinkle.CollidableTest.class,wrinkle.ConfigTest.class,wrinkle.LevelTest.class,wrinkle.WrinkleTest.class,wrinkle.EnemyTest.class,wrinkle.DeadExceptionTest.class,wrinkle.WrinkleAnimationSetTest.class,wrinkle.DragonTest.class,wrinkle.TerrainTest.class,wrinkle.FireTest.class,wrinkle.AnimationSetTest.class,wrinkle.MenuTest.class,wrinkle.GlobalTest.class,wrinkle.MainTest.class,wrinkle.StateTest.class,wrinkle.ActorTest.class})
public class WrinkleSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}