package pattern.behavior.iterator;

public class Array {
    private int[] array = new int[10];

    public Iterator getIterator() {
        return new IntegerIterator(array);
    }
}
