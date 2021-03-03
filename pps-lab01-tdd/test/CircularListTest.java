import lab01.tdd.CircularList;
import lab01.tdd.CircularListImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private static final int EXAMPLE_INTEGER = 1;
    private static final int SECOND_EXAMPLE_INTEGER = 2;
    private static final int THIRD_EXAMPLE_INTEGER = 3;
    private CircularList circularList;

    @BeforeEach
    void beforeEach(){
        circularList = new CircularListImpl();
    }

    @Test
    void testAddElement(){
        assertEquals(0, circularList.size());
        circularList.add(EXAMPLE_INTEGER);
        assertEquals(1, circularList.size());
    }

    @Test
    void testIsEmpty(){
        assertTrue(circularList.isEmpty());
        circularList.add(EXAMPLE_INTEGER);
        assertFalse(circularList.isEmpty());
    }

    @Test
    void testNextElement(){
        addThreeIntegerExample();
        assertEquals(EXAMPLE_INTEGER, circularList.next().get());
        circularList.next();
        assertEquals(THIRD_EXAMPLE_INTEGER, circularList.next().get());
        assertEquals(EXAMPLE_INTEGER, circularList.next().get());
    }

    @Test
    void testNextWithNoElement() {
        assertEquals(Optional.empty(), circularList.next());
    }

    @Test
    void testPreviousElement(){
        addThreeIntegerExample();
        assertEquals(EXAMPLE_INTEGER, circularList.previous().get());
        assertEquals(THIRD_EXAMPLE_INTEGER, circularList.previous().get());
        assertEquals(SECOND_EXAMPLE_INTEGER, circularList.previous().get());
    }

    @Test
    void testPreviousWithNoElement() {
        assertEquals(Optional.empty(), circularList.previous());
    }

    @Test
    void testReset() {
        addThreeIntegerExample();
        circularList.next();
        circularList.reset();
    }

    private void addThreeIntegerExample() {
        circularList.add(EXAMPLE_INTEGER);
        circularList.add(SECOND_EXAMPLE_INTEGER);
        circularList.add(THIRD_EXAMPLE_INTEGER);
    }
}
