import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameDriverTest {

    GameDriver testGameDriver;

    @BeforeEach
    public void before() {
        testGameDriver = new GameDriver();
    }

    @Test
    public void testStep() {
        assertEquals(0, testGameDriver.getGameMap().map.values().size());
        assertEquals(0,testGameDriver.getGameMap().map.values().size());
        assertEquals(Direction.NORTH, testGameDriver.getAnt().getDirection());
        assertEquals(0, testGameDriver.getAnt().getXPos());
        assertEquals(0, testGameDriver.getAnt().getYPos());
        assertEquals(0, testGameDriver.getGameMap().blackCells.size());

        testGameDriver.step();
        assertEquals(1, testGameDriver.getGameMap().map.values().size());
        assertEquals(1, testGameDriver.getGameMap().map.get(0).values().size());
        assertEquals(Direction.WEST, testGameDriver.getAnt().getDirection());
        assertEquals(-1, testGameDriver.getAnt().getXPos());
        assertEquals(0, testGameDriver.getAnt().getYPos());
        assertTrue(testGameDriver.getGameMap().map.get(0).get(0));
        assertEquals(1, testGameDriver.getGameMap().blackCells.size());

        testGameDriver.step();
        assertEquals(2, testGameDriver.getGameMap().map.values().size());
        assertEquals(1, testGameDriver.getGameMap().map.get(0).values().size());
        assertEquals(1, testGameDriver.getGameMap().map.get(-1).values().size());
        assertEquals(Direction.SOUTH, testGameDriver.getAnt().getDirection());
        assertEquals(-1, testGameDriver.getAnt().getXPos());
        assertEquals(-1, testGameDriver.getAnt().getYPos());
        assertTrue(testGameDriver.getGameMap().map.get(-1).get(0));
        assertEquals(2, testGameDriver.getGameMap().blackCells.size());

        testGameDriver.step();
        assertEquals(2, testGameDriver.getGameMap().map.values().size());
        assertEquals(1, testGameDriver.getGameMap().map.get(0).values().size());
        assertEquals(2, testGameDriver.getGameMap().map.get(-1).values().size());
        assertEquals(Direction.EAST, testGameDriver.getAnt().getDirection());
        assertEquals(0, testGameDriver.getAnt().getXPos());
        assertEquals(-1, testGameDriver.getAnt().getYPos());
        assertTrue(testGameDriver.getGameMap().map.get(-1).get(-1));
        assertEquals(3, testGameDriver.getGameMap().blackCells.size());

        testGameDriver.step();
        assertEquals(2, testGameDriver.getGameMap().map.values().size());
        assertEquals(2, testGameDriver.getGameMap().map.get(0).values().size());
        assertEquals(2, testGameDriver.getGameMap().map.get(-1).values().size());
        assertEquals(Direction.NORTH, testGameDriver.getAnt().getDirection());
        assertEquals(0, testGameDriver.getAnt().getXPos());
        assertEquals(0, testGameDriver.getAnt().getYPos());
        assertTrue(testGameDriver.getGameMap().map.get(0).get(-1));
        assertEquals(4, testGameDriver.getGameMap().blackCells.size());

        testGameDriver.step();
        assertEquals(2, testGameDriver.getGameMap().map.values().size());
        assertEquals(2, testGameDriver.getGameMap().map.get(0).values().size());
        assertEquals(2, testGameDriver.getGameMap().map.get(-1).values().size());
        assertEquals(Direction.EAST, testGameDriver.getAnt().getDirection());
        assertEquals(1, testGameDriver.getAnt().getXPos());
        assertEquals(0, testGameDriver.getAnt().getYPos());
        assertFalse(testGameDriver.getGameMap().map.get(0).get(0));
        assertEquals(3, testGameDriver.getGameMap().blackCells.size());

        testGameDriver.step();
        assertEquals(3, testGameDriver.getGameMap().map.values().size());
        assertEquals(2, testGameDriver.getGameMap().map.get(0).values().size());
        assertEquals(2, testGameDriver.getGameMap().map.get(-1).values().size());
        assertEquals(1, testGameDriver.getGameMap().map.get(1).values().size());
        assertEquals(Direction.NORTH, testGameDriver.getAnt().getDirection());
        assertEquals(1, testGameDriver.getAnt().getXPos());
        assertEquals(1, testGameDriver.getAnt().getYPos());
        assertTrue(testGameDriver.getGameMap().map.get(1).get(0));
        assertEquals(4, testGameDriver.getGameMap().blackCells.size());
    }
}