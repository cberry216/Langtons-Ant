import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AntTest {

    Ant testAnt;

    @BeforeEach
    public void before() {
        testAnt = new Ant();
    }

    @Test
    public void initAnt() {
        assertEquals(0, testAnt.getXPos());
        assertEquals(0, testAnt.getYPos());
        assertEquals(Direction.NORTH, testAnt.getDirection());
    }

    @Test
    public void testRotate() {
        assertEquals(Direction.NORTH, testAnt.getDirection());

        testAnt.rotate(true);
        assertEquals(Direction.EAST, testAnt.getDirection());

        testAnt.rotate(true);
        assertEquals(Direction.SOUTH, testAnt.getDirection());

        testAnt.rotate(true);
        assertEquals(Direction.WEST, testAnt.getDirection());

        testAnt.rotate(true);
        assertEquals(Direction.NORTH, testAnt.getDirection());

        testAnt.rotate(false);
        assertEquals(Direction.WEST, testAnt.getDirection());

        testAnt.rotate(false);
        assertEquals(Direction.SOUTH, testAnt.getDirection());

        testAnt.rotate(false);
        assertEquals(Direction.EAST, testAnt.getDirection());

        testAnt.rotate(false);
        assertEquals(Direction.NORTH, testAnt.getDirection());
    }

    @Test
    public void testMove() {
        assertEquals(Direction.NORTH, testAnt.getDirection());

        testAnt.move();
        assertEquals(0, testAnt.getXPos());
        assertEquals(1, testAnt.getYPos());

        testAnt.rotate(true);
        testAnt.move();
        assertEquals(1, testAnt.getXPos());
        assertEquals(1, testAnt.getYPos());
    }

    @Test
    public void testSetAntPos() {
        assertEquals(Direction.NORTH, testAnt.getDirection());
        assertEquals(0, testAnt.getXPos());
        assertEquals(0, testAnt.getYPos());

        testAnt.setAntPos(5,5);
        assertEquals(Direction.NORTH, testAnt.getDirection());
        assertEquals(5, testAnt.getXPos());
        assertEquals(5, testAnt.getYPos());
    }
}