import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    Map testMap;

    @BeforeEach
    public void before() {
        testMap = new Map();
    }

    @Test
    public void testAddCell() {
        assertEquals(new ArrayList<HashMap<Integer, Boolean>>().size(), testMap.map.values().size());

        assertEquals(0, testMap.blackCells.size());

        testMap.addCell(0,0,false);
        assertEquals(1, testMap.map.values().size());
        assertEquals(1, testMap.map.get(0).values().size());
        assertFalse(testMap.map.get(0).get(0));
        assertEquals(0, testMap.blackCells.size());

        testMap.addCell(1, 1, true);
        assertEquals(2, testMap.map.values().size());
        assertEquals(1, testMap.map.get(1).values().size());
        assertTrue(testMap.map.get(1).get(1));
        assertEquals(1, testMap.blackCells.size());

        testMap.addCell(0, 1, true);
        assertEquals(2, testMap.map.values().size());
        assertEquals(2, testMap.map.get(0).values().size());
        assertTrue(testMap.map.get(0).get(1));
        assertEquals(2, testMap.blackCells.size());

        testMap.addCell(0, 0, true);
        assertEquals(2, testMap.map.values().size());
        assertEquals(2, testMap.map.get(0).values().size());
        assertFalse(testMap.map.get(0).get(0));
        assertEquals(3, testMap.blackCells.size());
    }

    @Test
    public void testInvert() {
        testMap.addCell(0, 0, false);
        testMap.addCell(0, 1, true);
        testMap.addCell(1, 0, true);
        testMap.addCell(1, 1, false);

        assertEquals(2, testMap.map.values().size());
        assertEquals(2, testMap.map.get(0).values().size());
        assertEquals(2, testMap.map.get(1).values().size());
        assertEquals(2, testMap.blackCells.size());

        testMap.invert(0,0);
        assertTrue(testMap.map.get(0).get(0));
        assertEquals(3, testMap.blackCells.size());

        testMap.invert(0,1);
        assertFalse(testMap.map.get(0).get(1));
        assertEquals(2, testMap.blackCells.size());

        testMap.invert(1,0);
        assertFalse(testMap.map.get(1).get(0));
        assertEquals(1, testMap.blackCells.size());

        testMap.invert(1,1);
        assertTrue(testMap.map.get(1).get(1));
        assertEquals(2, testMap.blackCells.size());

        testMap.invert(5,5);
        assertEquals(3, testMap.map.values().size());
        assertEquals(1, testMap.map.get(5).values().size());
        assertTrue(testMap.map.get(5).get(5));
        assertEquals(3, testMap.blackCells.size());
    }
}