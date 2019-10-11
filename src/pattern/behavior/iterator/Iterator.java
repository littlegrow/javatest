package pattern.behavior.iterator;

public interface Iterator<T> {
    T next();

    boolean hasNext();
}
