import static org.junit.Assert.fail;

import org.junit.Test;

import be.kuleuven.cs.ogp.project.squares.Square;

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
        assert sq.getColdDmg() == 0;
    }

    @Test
    public void testGetColdDmg_2() {
        sq.setTemp(-7);
        assert sq.getColdDmg() == 0;
    }

    @Test
    public void testGetColdDmg_3() {
        sq.setTemp(-15);
        if (sq.getColdDmg() != 1)
            fail("Cold damage was calculated incorrectly!");
    }

}
