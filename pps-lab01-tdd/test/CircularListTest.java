import lab01.tdd.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private static final int EXAMPLE_INTEGER = 1;
    private static final int SECOND_EXAMPLE_INTEGER = 4;
    private static final int THIRD_EXAMPLE_INTEGER = 9;
    private static final int MULTIPLE_OF_FOUR = 16;

    private CircularList circularList;
    private final StrategyFactory strategyFactory = new StrategyFactoryImpl();

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
        circularList.next();
        circularList.reset();
        assertEquals(SECOND_EXAMPLE_INTEGER, circularList.next().get());
    }

    @Test
    void testNextWithPositiveStrategy(){
        addThreeIntegerExample();
        SelectStrategy positiveStrategy = strategyFactory.createPositiveStrategy();
        assertEquals(EXAMPLE_INTEGER, circularList.next(positiveStrategy).get());
        circularList.next(positiveStrategy);
        assertEquals(THIRD_EXAMPLE_INTEGER, circularList.next(positiveStrategy).get());
    }

    @Test
    void testNextWithOddStrategy(){
        addThreeIntegerExample();
        SelectStrategy oddStrategy = strategyFactory.createOddStrategy();
        assertEquals(EXAMPLE_INTEGER, circularList.next(oddStrategy).get());
        assertEquals(THIRD_EXAMPLE_INTEGER, circularList.next(oddStrategy).get());
    }

    @Test
    void testNextWithEvenStrategy(){
        addThreeIntegerExample();
        SelectStrategy evenStrategy = strategyFactory.createEvenStrategy();
        assertEquals(SECOND_EXAMPLE_INTEGER, circularList.next(evenStrategy).get());
        //if there is only the current element that satisfies the strategy, another "next(strategy)" will return the same element
        assertEquals(SECOND_EXAMPLE_INTEGER, circularList.next(evenStrategy).get());
    }

    @Test
    void testNextWithMultiplyStrategy(){
        addThreeIntegerExample();
        circularList.add(MULTIPLE_OF_FOUR);
        SelectStrategy multiplyStrategy = strategyFactory.createMultiplyStrategy(SECOND_EXAMPLE_INTEGER);
        assertEquals(SECOND_EXAMPLE_INTEGER, circularList.next(multiplyStrategy).get());
        assertEquals(MULTIPLE_OF_FOUR, circularList.next(multiplyStrategy).get());
        assertEquals(SECOND_EXAMPLE_INTEGER, circularList.next(multiplyStrategy).get());
    }

    @Test
    void testNextWithEqualStrategy(){
        addThreeIntegerExample();
        SelectStrategy equalStrategy = strategyFactory.createEqualStrategy(THIRD_EXAMPLE_INTEGER);
        assertEquals(THIRD_EXAMPLE_INTEGER, circularList.next(equalStrategy).get());
    }

    @Test
    void testNextWithNoMatch(){
        circularList.add(THIRD_EXAMPLE_INTEGER);
        SelectStrategy multiplyStrategy = strategyFactory.createMultiplyStrategy(SECOND_EXAMPLE_INTEGER);
        assertEquals(Optional.empty(), circularList.next(multiplyStrategy));
        assertEquals(Optional.empty(), circularList.next(multiplyStrategy));
    }

    private void addThreeIntegerExample() {
        circularList.add(EXAMPLE_INTEGER);
        circularList.add(SECOND_EXAMPLE_INTEGER);
        circularList.add(THIRD_EXAMPLE_INTEGER);
    }
}
