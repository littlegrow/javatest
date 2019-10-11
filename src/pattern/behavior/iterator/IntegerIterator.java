package pattern.behavior.iterator;

public class IntegerIterator implements Iterator<Integer> {
    private int[] array;
    private int index;

    IntegerIterator(int[] array) {
        this.array = array;
    }

    @Override
    public Integer next() {
        return array[index++];
    }

    @Override
    public boolean hasNext() {
        return index < array.length;
    }
}
