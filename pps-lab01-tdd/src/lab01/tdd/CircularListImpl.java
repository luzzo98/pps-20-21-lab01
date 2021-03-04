package lab01.tdd;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CircularListImpl implements CircularList {

    private final List<Integer> integerList = new LinkedList<>();
    private int actualPosition = -1;

    @Override
    public void add(int element) {
        integerList.add(element);
    }

    @Override
    public int size() {
        return integerList.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Optional<Integer> next() {
        return movementInList(true);
    }

    @Override
    public Optional<Integer> previous() {
        return movementInList(false);
    }

    @Override
    public void reset() {
        actualPosition = size() == 0? -1 : 0;
    }

    @Override
    public Optional<Integer> next(SelectStrategy strategy) {
        if (isEmpty()) {
            return Optional.empty();
        }
        int firstControlledPosition = actualPosition;
        while (updatePosition(true) != firstControlledPosition) {
            if (strategy.apply(integerList.get(actualPosition))) {
                return Optional.of(integerList.get(actualPosition));
            }
        }
        return Optional.empty();
    }

    private int updatePosition(boolean isNext){
        if (actualPosition == -1) {
            actualPosition = 0;
        } else {
            if (isNext) {
                actualPosition = actualPosition == size()-1 ? 0 : actualPosition+1;
            } else {
                actualPosition = actualPosition == 0 ? size()-1 : actualPosition-1;
            }
        }
        return actualPosition;
    }

    private Optional<Integer> movementInList(boolean isNext) {
        if (isEmpty()){
            return Optional.empty();
        }
        return Optional.of(integerList.get(updatePosition(isNext)));
    }
}
