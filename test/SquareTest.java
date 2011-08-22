import static org.junit.Assert.fail;

import be.kuleuven.cs.ogp.project.CompositeDungeon;
import be.kuleuven.cs.ogp.project.Direction;
import be.kuleuven.cs.ogp.project.Dungeon;
import be.kuleuven.cs.ogp.project.Square;
import be.kuleuven.cs.ogp.project.tools.Point3D;
import org.junit.Test;

/**
 * This class is a unit test for the class Square to perform a series of tests to validate the integrity of the class.
 *
 * @author Frederic Hannes
 */
public class SquareTest {

    private Square sq = new Square();

    @Test
    public void testGetTemp_Valid() {
        sq.setTemp(25);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTemp_Invalid_LBound() {
        sq.setTemp(-201);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTemp_Invalid_UBound() {
        sq.setTemp(5001);
    }

    @Test
    public void testGetColdDmg_1() {
        sq.setTemp(25);
        if (sq.getColdDmg() != 0)
            fail("Cold damage was calculated incorrectly!");
    }

    @Test
    public void testGetColdDmg_2() {
        sq.setTemp(-7);
        if (sq.getColdDmg() != 0)
            fail("Cold damage was calculated incorrectly!");
    }

    @Test
    public void testGetColdDmg_3() {
        sq.setTemp(-15);
        if (sq.getColdDmg() != 1)
            fail("Cold damage was calculated incorrectly!");
    }

    @Test
    public void testGetHeatDmg_1() {
        sq.setTemp(25);
        if (sq.getHeatDmg() != 0)
            fail("Heat damage was calculated incorrectly!");
    }

    @Test
    public void testGetHeatDmg_2() {
        sq.setTemp(37);
        if (sq.getHeatDmg() != 0)
            fail("Heat damage was calculated incorrectly!");
    }

    @Test
    public void testGetHeatDmg_3() {
        sq.setTemp(50);
        if (sq.getHeatDmg() != 1)
            fail("Heat damage was calculated incorrectly!");
    }

    @Test
    public void testNeighbour() {
        Dungeon d = new Dungeon();
        Square sq = new Square();
        d.addSquare(sq, new Point3D(1, 0, 0));
        Square sq2 = new Square();
        d.addSquare(sq2, new Point3D(2, 0, 0));
        if (!sq.getBorder(Direction.EAST).getAdjacent().getSquare().equals(sq2))
            fail("The square is linked incorrectly!");
    }

    @Test
    public void testParent() {
        Dungeon d = new Dungeon();
        Square sq = new Square();
        d.addSquare(sq, new Point3D(1, 0, 0));
        if (!sq.getDungeon().equals(d))
            fail("The square is not properly assigned to the dungeon!");
    }

    @Test
    public void testPos() {
        Dungeon d = new Dungeon();
        Square sq = new Square();
        Point3D pos = new Point3D(1, 0, 0);
        d.addSquare(sq, pos);
        if (!sq.getPos().equals(pos))
            fail("The square is not properly assigned to the dungeon!");
    }

    @Test
    public void testAbsolutePos() {
        CompositeDungeon cd = new CompositeDungeon();
        Dungeon d = new Dungeon();
        cd.addDungeon(d, new Point3D(1, 2, 3));
        Square sq = new Square();
        d.addSquare(sq, new Point3D(1, 0, 0));
        if (!sq.getAbsolutePos().equals(new Point3D(2, 2, 3)))
            fail("The square is not properly assigned to the dungeon!");
    }

}
