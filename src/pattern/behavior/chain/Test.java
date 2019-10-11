package pattern.behavior.chain;

public class Test {

    public static void main(String[] args) {
        Filter filter = new RemoveBlankFilter();
        filter.setNextFilter(new RemoveBottomLineFilter()).setNextFilter(new ToUppFilter());
        System.out.println("Filter result: " + filter.filter("abc de_fGHIJK"));
    }
}
