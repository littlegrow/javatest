package pattern.behavior.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        System.out.println("subject change");
        notifyObservers();
    }

    private List<IObserver> observers = new ArrayList<>();

    public void addObserver(IObserver observer) {
        System.out.println("add observer: " + observer.getClass().getSimpleName());
        observers.add(observer);
    }

    public boolean removeObserver(IObserver observer) {
        return observers.remove(observer);
    }

    private void notifyObservers() {
        if (!observers.isEmpty()) {
            for (IObserver observer : observers) {
                observer.update();
            }
        } else {
            System.out.println("no observer");
        }
    }
}
