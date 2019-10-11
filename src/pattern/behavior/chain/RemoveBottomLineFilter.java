package pattern.behavior.chain;

public class RemoveBottomLineFilter extends Filter {
    @Override
    protected String doOperator(String content) {
        System.out.println("Before RemoveBottomLineFilter: " + content);
        content = content.replace("_", "");
        System.out.println("After RemoveBottomLineFilter: " + content);
        return content;
    }
}
