package pattern.behavior.mediator;

public class Test {
    public static void main(String[] args) {
        User xiaoming = new User("1", "小明");
        User xiaogang = new User("2", "小刚");

        Mediator.sendMessage(xiaoming, xiaogang, new Message("你在家么？"));
        Mediator.sendMessage(xiaogang, xiaoming, new Message("我在家，你要来玩不？"));
    }
}
