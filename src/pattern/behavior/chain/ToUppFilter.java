package pattern.behavior.chain;

public class ToUppFilter extends Filter {
    @Override
    protected String doOperator(String content) {
        System.out.println("Before ToUppFilter: " + content);
        content = content.toUpperCase();
        System.out.println("After ToUppFilter: " + content);
        return content;
    }
}
