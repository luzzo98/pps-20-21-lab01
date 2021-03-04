package lab01.tdd;

/**
 * Interface that provide factory methods to create particular strategy
 */
public interface StrategyFactory {

    /**
     * Create a strategy that recognise positive numbers
     * @return a positive strategy
     */
    SelectStrategy createPositiveStrategy();

    /**
     * Create a strategy that recognise odd numbers
     * @return an odd strategy
     */
    SelectStrategy createOddStrategy();

    /**
     * Create a strategy that recognise even numbers
     * @return an even strategy
     */
    SelectStrategy createEvenStrategy();

    /**
     * Create a strategy that recognise numbers that are multiple of the passed parameter
     * @return a multiply strategy
     */
    SelectStrategy createMultiplyStrategy(int multiple);

    /**
     * Create a strategy that recognise numbers equal to the passed parameter
     * @return an equal strategy
     */
    SelectStrategy createEqualStrategy(int number);
}
