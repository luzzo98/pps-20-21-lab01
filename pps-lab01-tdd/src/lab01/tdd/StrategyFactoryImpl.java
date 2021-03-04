package lab01.tdd;

public class StrategyFactoryImpl implements StrategyFactory {

    @Override
    public SelectStrategy createPositiveStrategy() {
        return element -> element>=0;
    }

    @Override
    public SelectStrategy createOddStrategy() {
        return element -> element%2==1;
    }

    @Override
    public SelectStrategy createEvenStrategy() {
        return element -> element%2==0;
    }

    @Override
    public SelectStrategy createMultiplyStrategy(int multiple) {
        return element -> element%multiple==0;
    }

    @Override
    public SelectStrategy createEqualStrategy(int number) {
        return element -> element==number;
    }
}
