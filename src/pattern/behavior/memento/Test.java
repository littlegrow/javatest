package pattern.behavior.memento;

public class Test {
    public static void main(String[] args) {
        MementoRecord mementoRecord = new MementoRecord();

        SomeObject someObject = new SomeObject();
        someObject.setState("state 1");
        mementoRecord.addMemento(someObject.getMemento());
        someObject.setState("state 2");
        mementoRecord.addMemento(someObject.getMemento());
        someObject.setState("state 3");
        mementoRecord.addMemento(someObject.getMemento());
        someObject.setState("state 4");
        mementoRecord.addMemento(someObject.getMemento());
        someObject.setState("state 5");

        System.out.println("current: " + someObject.getState());
        mementoRecord.printMementoRecord();
    }
}
