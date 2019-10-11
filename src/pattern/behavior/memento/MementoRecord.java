package pattern.behavior.memento;

import java.util.ArrayList;
import java.util.List;

public class MementoRecord {
    List<Memento> mementos = new ArrayList<>();

    public void addMemento(Memento memento) {
        mementos.add(memento);
    }

    public Memento getMemento(int index) {
        if (index >= 0 && index < mementos.size()) {
            return mementos.get(index);
        }
        return null;
    }

    public void printMementoRecord() {
        System.out.println("Print MementoRecord");
        for (Memento memento : mementos) {
            System.out.println(memento.getState());
        }
    }
}
